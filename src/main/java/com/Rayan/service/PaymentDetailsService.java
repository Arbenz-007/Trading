package com.Rayan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Rayan.model.PaymentDetails;
import com.Rayan.model.User;
import com.Rayan.repo.PaymentDetailsRepo;
@Service
public class PaymentDetailsService implements IPaymentDetails {

	@Autowired
	private PaymentDetailsRepo paymentDetailsRepo;
	
	@Override
	public PaymentDetails addPaymentDetails(String accNumber, String accHoldername, String ifsc, String bankName,
			User user) {
		PaymentDetails paymentDetails=new PaymentDetails();
		paymentDetails.setAccountNumber(accNumber);
		paymentDetails.setAccountHolderName(accHoldername);
		paymentDetails.setBankName(bankName);
		paymentDetails.setIfsc(ifsc);
		paymentDetails.setUser(user);
		
		
		return paymentDetailsRepo.save(paymentDetails);
	}

	@Override
	public PaymentDetails getUsersPaymentDetails(User user) {
		return paymentDetailsRepo.findByUserId(user.getId());
	}

}
