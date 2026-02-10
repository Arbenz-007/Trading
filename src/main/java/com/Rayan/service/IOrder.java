package com.Rayan.service;

import java.util.List;

import com.Rayan.domain.OrderType;
import com.Rayan.model.Coins;
import com.Rayan.model.Order;
import com.Rayan.model.OrderItem;
import com.Rayan.model.User;

public interface IOrder {

	Order createOrder(User user,OrderItem orderItem,OrderType orderType);
	
	Order getOrderById(Long orderId) throws Exception;
	
	List<Order> getAllOrderOfUser(Long userId,OrderType ordertype,String assetSymbol);
	
	Order processOrder(Coins coin, double quantity,OrderType orderType,User user) throws Exception;
}
