package com.Rayan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.Rayan.domain.PaymentMethod;
import com.Rayan.model.PaymentOrder;
import com.Rayan.model.User;
import com.Rayan.response.PaymentResponse;
import com.Rayan.service.PaymentService;
import com.Rayan.service.UserService;

@RestController
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/api/payment/{paymentMethod}/amount/{amount}")
	public ResponseEntity<PaymentResponse> paymentHandler(
			@PathVariable PaymentMethod paymentMethod,
			@PathVariable Long amount,
			@RequestHeader("Authorization") String jwt) throws Exception{
		
		User user = userService.findUserProfileByJwt(jwt);
		
		PaymentResponse paymentResponse= new PaymentResponse();
		
		PaymentOrder order=paymentService.createOrder(user, amount, paymentMethod);
		if(paymentMethod.equals(PaymentMethod.RAZORPAY)) {
			paymentResponse=paymentService.createRazorpayPaymentLink(user, amount);
			
		}
		
		return new ResponseEntity<>(paymentResponse,HttpStatus.CREATED);
		
	}
}
