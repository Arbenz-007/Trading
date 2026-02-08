package com.Rayan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Rayan.model.VerificationCode;

public interface VerificationCodeRepo extends JpaRepository<VerificationCode, Long> {

	public VerificationCode findByUserId(Long userId);
}
