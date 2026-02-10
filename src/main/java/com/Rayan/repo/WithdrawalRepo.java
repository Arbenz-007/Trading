package com.Rayan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Rayan.model.Withdrawal;

public interface WithdrawalRepo extends JpaRepository<Withdrawal, Long> {

	List<Withdrawal> findByUserId(Long userId);
}
