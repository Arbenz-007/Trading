package com.Rayan.service;

import com.Rayan.domain.VerificationType;
import com.Rayan.model.User;
import com.Rayan.model.VerificationCode;

public interface IVerificationCode {

	VerificationCode sendVerificationCode(User user,VerificationType verificationType);
	
	VerificationCode getVerificationCode(Long id) throws Exception;
	
	VerificationCode getVerificationCodeByUser(Long userId);
	
	void deleteVerificationCodeById(VerificationCode verificationCode);
}
