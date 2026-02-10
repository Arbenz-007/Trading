package com.Rayan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties.Apiversion.Use;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Rayan.domain.OrderType;
import com.Rayan.model.Coins;
import com.Rayan.model.Order;
import com.Rayan.model.User;
import com.Rayan.request.CreateOrderRequest;
import com.Rayan.service.CoinService;
import com.Rayan.service.OrderService;
import com.Rayan.service.UserService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {


	@Autowired
	public OrderService orderService;
	

	@Autowired
	public UserService userService;
	
	@Autowired
	private CoinService coinService;
	
//	@Autowired
//	private WalletService walletService;
	
	
	@PostMapping("/pay")
	public ResponseEntity<Order> payOrderPayment(@RequestHeader("Authorization") String jwt, @RequestBody CreateOrderRequest req ) throws Exception{
		User user=userService.findUserProfileByJwt(jwt);
		
		Coins coin=coinService.findById(req.getCoinId());
		
		Order order=orderService.processOrder(coin, req.getQuantity(), req.getOrderType(), user);
		
		return ResponseEntity.ok(order); 
		
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<Order> getOrderById(@RequestHeader("Authorisation") String jwtToken,@PathVariable Long orderId) throws Exception{
	
		User user=userService.findUserProfileByJwt(jwtToken);
		Order order=orderService.getOrderById(orderId);
		
		if(order.getUser().getId().equals(user.getId())) {
			return ResponseEntity.ok(order);
		}
		else {
			throw new Exception("You dont have access");
		}
	}
	
	@GetMapping()
	public ResponseEntity<List<Order>> getAllOrderForUser(
			@RequestHeader("Authorization") String jwt,
			@RequestParam(required=false) OrderType order_type,
			@RequestParam(required=false) String asset_symbol
			) throws Exception{
		
		Long userId=userService.findUserProfileByJwt(jwt).getId();
		
		List<Order> userOrders=orderService.getAllOrderOfUser(userId, order_type, asset_symbol);
		return ResponseEntity.ok(userOrders);
	
	}
	
	
	
}
