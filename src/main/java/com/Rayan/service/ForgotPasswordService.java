package com.Rayan.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Rayan.domain.VerificationType;
import com.Rayan.model.ForgotPasswordToken;
import com.Rayan.model.User;
import com.Rayan.repo.ForgotPasswordRepo;

@Service
public class ForgotPasswordService implements IForgotPassword {

	@Autowired
	private ForgotPasswordRepo forgotPasswordRepo;
	
	@Override
	public ForgotPasswordToken createToken(User user, String id, String otp, VerificationType verificationType,
			String sendTo) {
		ForgotPasswordToken token=new ForgotPasswordToken();
		token.setUser(user);
		token.setSendTo(sendTo);
		token.setVerificationType(verificationType);
		token.setOtp(otp);
		token.setId(id);
		
		return forgotPasswordRepo.save(token);
	}

	@Override
	public ForgotPasswordToken findById(String Id) {
		Optional<ForgotPasswordToken> opt=forgotPasswordRepo.findById(Id);
		return opt.orElse(null);
	}

	@Override
	public ForgotPasswordToken findByUser(Long userId) {
		return forgotPasswordRepo.findByUserId(userId);
	}

	@Override
	public void deleteToken(ForgotPasswordToken token) {
		forgotPasswordRepo.delete(token);

	}

}
