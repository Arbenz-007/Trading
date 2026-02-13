package com.Rayan.service;

import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.Rayan.domain.PaymentMethod;
import com.Rayan.domain.PaymentOrderStatus;
import com.Rayan.model.PaymentOrder;
import com.Rayan.model.User;
import com.Rayan.repo.PaymentOrderRepo;
import com.Rayan.response.PaymentResponse;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class PaymentService implements IPayment {

	@Autowired
	private PaymentOrderRepo paymentOrderRepo;
	
	@Value("${razorpay.api.key}")
	private String apikey;
	
	@Value("${razorpay.api.secret")
	private String apiSecretKey;
	
	@Override
	public PaymentOrder createOrder(User user, Long amount, PaymentMethod paymentMethod) {
		PaymentOrder paymentOrder=new PaymentOrder();
		paymentOrder.setAmount(amount);
		paymentOrder.setPaymentMethod(paymentMethod);
		paymentOrder.setUser(user);
		
		
		return paymentOrderRepo.save(paymentOrder);
		
	}

	@Override
	public PaymentOrder getPaymentOrderById(Long id) throws Exception {
		return paymentOrderRepo.findById(id).orElseThrow(()-> new Exception("Payment order not found"));
	}

	@Override
	public boolean proceedPaymentOrder(PaymentOrder paymentOrder, String paymentId) throws RazorpayException {
		if(paymentOrder.getStatus().equals(PaymentOrderStatus.PENDING)) {
			if(paymentOrder.getPaymentMethod().equals(PaymentMethod.RAZORPAY)) {
				RazorpayClient razorpay=new RazorpayClient(apikey,apiSecretKey);
				Payment payment=razorpay.payments.fetch(paymentId);
				
				Integer amount=payment.get("amount");
				String status=payment.get("status");
				
				if(status.equals("captured")) {
					paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
					return true;
				}
				paymentOrder.setStatus(PaymentOrderStatus.FAILED);
				paymentOrderRepo.save(paymentOrder);
				return false;
			}
			paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
			paymentOrderRepo.save(paymentOrder);
			
			return true;
		}
		return false;
	}

	@Override
	public PaymentResponse createRazorpayPaymentLink(User user, Long amount) throws RazorpayException {
		Long Amount=amount*100;
		try {
			//Instantite a Razorpay client with your key ID and secret
			RazorpayClient razorpay= new RazorpayClient(apikey, apiSecretKey);
			
			//Create a JSON object with the payment link request parameters
			JSONObject paymentLinkRequest= new JSONObject();
			paymentLinkRequest.put("amount", amount);
			paymentLinkRequest.put("currency", "INR");
			
			//Create a JSON object with the customer details
			JSONObject customer= new JSONObject();
			customer.put("name", user.getFullName());
			customer.put("email", user.getEmail());
			paymentLinkRequest.put("customer", customer);
			
			//create a json object with the notification settings
			JSONObject notify= new JSONObject();
			notify.put("email", true);
			paymentLinkRequest.put("notify", notify);
			
			//set the remainder settings
			paymentLinkRequest.put("remainder_enable", true);
			
			//set the callback url and method
			paymentLinkRequest.put("callback_url","http://localhost:8080/wallet");
			paymentLinkRequest.put("callback_method","get");
			
			//create the payment link using the paymentlink.create() method
			PaymentLink payment=razorpay.paymentLink.create(paymentLinkRequest);
			
			String paymentLinkId=payment.get("id");
			String paymentLinkUrl=payment.get("short_url");
			
			PaymentResponse res= new PaymentResponse();
			res.setPayment_url(paymentLinkUrl);
			
			return res;
		}
		catch(RazorpayException e) {
			System.out.println("Error creating payment Link: "+ e.getMessage());
			throw new RazorpayException(e.getMessage());
		}
		
	}

}
