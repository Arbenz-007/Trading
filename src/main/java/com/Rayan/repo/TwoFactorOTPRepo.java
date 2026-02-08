package com.Rayan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Rayan.model.TwoFactorOTP;

public interface TwoFactorOTPRepo extends JpaRepository<TwoFactorOTP, String> {

	TwoFactorOTP findByUserId(Long userId);
}
