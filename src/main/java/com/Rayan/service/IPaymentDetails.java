package com.Rayan.service;

import com.Rayan.model.PaymentDetails;
import com.Rayan.model.User;

public interface IPaymentDetails {

	public PaymentDetails addPaymentDetails(String accNumber, String accHoldername,String ifsc,String bankName,User user);
	
	public PaymentDetails getUsersPaymentDetails(User user);
}
