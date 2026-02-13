package com.Rayan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Rayan.model.PaymentDetails;

public interface PaymentDetailsRepo extends JpaRepository<PaymentDetails, Long> {

	PaymentDetails findByUserId(Long userId);
}
