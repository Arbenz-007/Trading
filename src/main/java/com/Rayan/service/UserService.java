package com.Rayan.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.Rayan.config.JwtProvider;
import com.Rayan.domain.VerificationType;
import com.Rayan.model.TwoFactorAuth;
import com.Rayan.model.User;
import com.Rayan.repo.UserRepo;

public class UserService implements IUserService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public User findUserProfileByJwt(String jwt) throws Exception {
		String email = JwtProvider.getEmailFromJwtToken(jwt);
		User user = userRepo.findByEmail(email);

		if (user == null) {
			throw new Exception("user not found");
		}
		return user;
	}

	@Override
	public User findUserByEmail(String email) throws Exception {
		User user = userRepo.findByEmail(email);

		if (user == null) {
			throw new Exception("user not found");
		}
		return user;
	}

	@Override
	public User findUserById(Long userId) throws Exception {
		Optional<User> user=userRepo.findById(userId);
		if(user.isEmpty()) {
			throw new Exception("user not found");
		}
		return user.get();
	}


	@Override
	public User updatePassword(User user, String newPassword) {
		user.setPassword(newPassword);
		return userRepo.save(user);
	}

	@Override
	public User enableTwoFactorAuthentication(VerificationType verificationType, String sendTo, User user) {
		TwoFactorAuth twoFactorAuth=new TwoFactorAuth();
		twoFactorAuth.setInEnabled(true);
		twoFactorAuth.setSendTo(verificationType);
		
		user.setTwoFactorAuth(twoFactorAuth);
		
		return userRepo.save(user);
	}

}
