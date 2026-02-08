package com.Rayan.service;

import com.Rayan.domain.VerificationType;
import com.Rayan.model.ForgotPasswordToken;
import com.Rayan.model.User;

public interface IForgotPassword {

	ForgotPasswordToken createToken(User user,String id,String otp,VerificationType verificationType,String sendTo);
	
	ForgotPasswordToken findById(String Id);
	
	ForgotPasswordToken findByUser(Long userId);
	
	void deleteToken(ForgotPasswordToken token);
}
