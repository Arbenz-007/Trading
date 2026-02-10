package com.Rayan.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Rayan.domain.OrderStatus;
import com.Rayan.domain.OrderType;
import com.Rayan.model.Asset;
import com.Rayan.model.Coins;
import com.Rayan.model.Order;
import com.Rayan.model.OrderItem;
import com.Rayan.model.User;
import com.Rayan.repo.OrderItemRepo;
import com.Rayan.repo.OrderRepo;

@Service
public class OrderService implements IOrder {

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private WalletService walletService;

	@Autowired
	private OrderItemRepo orderItemRepo;

	@Autowired
	private AssetService assetService;

	@Override
	public Order createOrder(User user, OrderItem orderItem, OrderType orderType) {
		double price = orderItem.getCoin().getCurrentPrice() * orderItem.getQuantity();

		Order order = new Order();
		order.setUser(user);
		order.setOrderItem(orderItem);
		order.setOrderType(orderType);
		order.setPrice(BigDecimal.valueOf(price));
		order.setTimeStamp(LocalDateTime.now());
		order.setStatus(OrderStatus.PENDING);

		return orderRepo.save(order);

	}

	@Override
	public Order getOrderById(Long orderId) throws Exception {
		return orderRepo.findById(orderId).orElseThrow(() -> new Exception("order not found"));
	}

	@Override
	public List<Order> getAllOrderOfUser(Long userId, OrderType ordertype, String assetSymbol) {
		return orderRepo.findByUserId(userId);
	}

	public OrderItem createOrderItem(Coins coin, double quantity, double buyPrice, double sellPrice) {
		OrderItem orderItem = new OrderItem();
		orderItem.setCoin(coin);
		orderItem.setQuantity(quantity);
		orderItem.setBuyPrice(buyPrice);
		orderItem.setSellPrice(sellPrice);
		return orderItemRepo.save(orderItem);
	}

	@Transactional
	public Order buyAsset(Coins coin, double quantity, User user) throws Exception {
		if (quantity <= 0) {
			throw new Exception("quantity should be greater than 0");
		}

		double buyPrice = coin.getCurrentPrice();

		OrderItem orderItem = createOrderItem(coin, quantity, buyPrice, 0);
		Order order = createOrder(user, orderItem, OrderType.BUY);
		orderItem.setOrder(order);

		walletService.payOrderPayement(order, user);
		order.setStatus(OrderStatus.SUCCESS);
		order.setOrderType(OrderType.BUY);

		Order savedOrder = orderRepo.save(order);

		Asset oldAsset = assetService.findAssetByUserIdAndCoinId(order.getUser().getId(),
				order.getOrderItem().getCoin().getId());
		if (oldAsset == null) {
			oldAsset = assetService.createAsset(user, orderItem.getCoin(), orderItem.getQuantity());
		} else {
			assetService.updateAsset(oldAsset.getId(), quantity);
		}

		return savedOrder;

	}

	@Transactional
	public Order sellAsset(Coins coin, double quantity, User user) throws Exception {
		if (quantity <= 0) {
			throw new Exception("quantity should be greater than 0");
		}

		double sellPrice = coin.getCurrentPrice();

		Asset assetToSell = assetService.findAssetByUserIdAndCoinId(user.getId(), coin.getId());

		double buyPrice = assetToSell.getBuyPrice();
		
		if (assetToSell != null) {

		

			OrderItem orderItem = createOrderItem(coin, quantity, buyPrice, sellPrice);
			Order order = createOrder(user, orderItem, OrderType.SELL);
			orderItem.setOrder(order);

			if (assetToSell.getQuantity() >= quantity) {
				order.setStatus(OrderStatus.SUCCESS);
				order.setOrderType(OrderType.SELL);

				Order savedOrder = orderRepo.save(order);

				walletService.payOrderPayement(order, user);
				

				Asset updatedAsset = assetService.updateAsset(assetToSell.getId(), -quantity);
				if (updatedAsset.getQuantity() * coin.getCurrentPrice() <= 1) {
					assetService.deleteAsset(updatedAsset.getId());
				}

				return savedOrder;

			}
			throw new Exception("Insufficient quantity to sell");
		}
		throw new Exception("Asset not found");
	}

	@Override
	@Transactional
	public Order processOrder(Coins coin, double quantity, OrderType orderType, User user) throws Exception {

		if (orderType.equals(OrderType.BUY)) {
			return buyAsset(coin, quantity, user);
		} else if (orderType.equals(OrderType.SELL)) {
			return sellAsset(coin, quantity, user);
		}

		throw new Exception("invalid Order type");
	}

}
