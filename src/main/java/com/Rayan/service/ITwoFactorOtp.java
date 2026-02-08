package com.Rayan.service;

import com.Rayan.model.TwoFactorOTP;
import com.Rayan.model.User;

public interface ITwoFactorOtp {

	
	TwoFactorOTP createTwoFactorOtp(User user,String otp,String jwt);
	
	TwoFactorOTP findByUser(Long userId);
	
	TwoFactorOTP findById(String id);
	
	boolean verifyTwoFactorOtp(TwoFactorOTP twoFactorOtp,String otp);
	
	void deleteTwoFactorOtp(TwoFactorOTP twoFactorOtp);
	
	
}
