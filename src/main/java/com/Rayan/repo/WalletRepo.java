package com.Rayan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Rayan.model.Wallet;

public interface WalletRepo extends JpaRepository<Wallet, Long> {

	Wallet findByUserId(Long userId);
}
