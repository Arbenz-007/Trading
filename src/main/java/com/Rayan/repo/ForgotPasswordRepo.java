package com.Rayan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Rayan.model.ForgotPasswordToken;

public interface ForgotPasswordRepo extends JpaRepository<ForgotPasswordToken, String> {
	ForgotPasswordToken findByUserId(Long userId);
}
