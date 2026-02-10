package com.Rayan.model;

import java.time.LocalDateTime;

import com.Rayan.domain.WithdrawalStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Withdrawal {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private WithdrawalStatus withdrawalStatus;
	
	private Long amount;
	
	@ManyToOne
	private User user;

	private LocalDateTime date=LocalDateTime.now();

	public Withdrawal(Long id, WithdrawalStatus withdrawalStatus, Long amount, User user, LocalDateTime date) {
		super();
		this.id = id;
		this.withdrawalStatus = withdrawalStatus;
		this.amount = amount;
		this.user = user;
		this.date = date;
	}

	public Withdrawal() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public WithdrawalStatus getWithdrawalStatus() {
		return withdrawalStatus;
	}

	public void setWithdrawalStatus(WithdrawalStatus withdrawalStatus) {
		this.withdrawalStatus = withdrawalStatus;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
}
