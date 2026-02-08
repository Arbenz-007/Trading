package com.Rayan.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Rayan.model.TwoFactorOTP;
import com.Rayan.model.User;
import com.Rayan.repo.TwoFactorOTPRepo;

@Service
public class TwoFactorOtpService implements ITwoFactorOtp {

	@Autowired
	private TwoFactorOTPRepo twoFactorOtprepo;
	
	@Override
	public TwoFactorOTP createTwoFactorOtp(User user, String otp, String jwt) {
		UUID uuid=UUID.randomUUID();
		
		String id=uuid.toString();
		
		TwoFactorOTP twoFactorOtp= new TwoFactorOTP();
		twoFactorOtp.setOtp(otp);
		twoFactorOtp.setJwt(jwt);
		twoFactorOtp.setId(id);
		twoFactorOtp.setUser(user);
		
		return twoFactorOtprepo.save(twoFactorOtp);
	}

	@Override
	public TwoFactorOTP findByUser(Long userId) {
		return twoFactorOtprepo.findByUserId(userId);
	}

	@Override
	public TwoFactorOTP findById(String id) {
		Optional<TwoFactorOTP> opt=twoFactorOtprepo.findById(id);
		
		return opt.orElse(null);
	}

	@Override
	public boolean verifyTwoFactorOtp(TwoFactorOTP twoFactorOtp, String otp) {
		return twoFactorOtp.getOtp().equals(otp);
	}

	@Override
	public void deleteTwoFactorOtp(TwoFactorOTP twoFactorOtp) {
		twoFactorOtprepo.delete(twoFactorOtp);
	}

}
