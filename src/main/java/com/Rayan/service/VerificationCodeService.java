package com.Rayan.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Rayan.domain.VerificationType;
import com.Rayan.model.User;
import com.Rayan.model.VerificationCode;
import com.Rayan.repo.VerificationCodeRepo;
import com.Rayan.utils.OtpUtils;

@Service
public class VerificationCodeService implements IVerificationCode {

	@Autowired
	private VerificationCodeRepo verificationCodeRepo;
	
	@Override
	public VerificationCode sendVerificationCode(User user,VerificationType verificationType) {
		VerificationCode verificationcode1=new VerificationCode();
		verificationcode1.setOtp(OtpUtils.generateOTP());
		verificationcode1.setVerificationType(verificationType);
		verificationcode1.setUser(user);
		
		return verificationCodeRepo.save(verificationcode1);
	}

	@Override
	public VerificationCode getVerificationCode(Long id) throws Exception {
		Optional<VerificationCode> opt= verificationCodeRepo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new Exception("VerificationCode not found");
	}

	@Override
	public VerificationCode getVerificationCodeByUser(Long userId) {
		return verificationCodeRepo.findByUserId(userId);
	}

	@Override
	public void deleteVerificationCodeById(VerificationCode verificationCode) {
		verificationCodeRepo.delete(verificationCode);

	}

}
