package com.Rayan.service;

import com.Rayan.domain.PaymentMethod;
import com.Rayan.model.PaymentOrder;
import com.Rayan.model.User;
import com.Rayan.response.PaymentResponse;
import com.razorpay.RazorpayException;

public interface IPayment {

	
	PaymentOrder createOrder(User user,Long amount, PaymentMethod paymentMethod);
	
	PaymentOrder getPaymentOrderById(Long id) throws Exception;
	
	boolean proceedPaymentOrder(PaymentOrder paymentOrder,String paymentId) throws RazorpayException;
	
	PaymentResponse createRazorpayPaymentLink(User user, Long amount) throws RazorpayException;
	
}
