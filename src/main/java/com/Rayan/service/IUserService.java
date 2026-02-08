package com.Rayan.service;

import com.Rayan.domain.VerificationType;
import com.Rayan.model.User;

public interface IUserService {

	public User findUserProfileByJwt(String jwt) throws Exception;
	public User findUserByEmail(String email) throws Exception;
	public User findUserById(Long userId) throws Exception;
	
	public User enableTwoFactorAuthentication(VerificationType verificationType,String sendTo ,User user);
	
	User updatePassword(User user, String newPassword);
}
