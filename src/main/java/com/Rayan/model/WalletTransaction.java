package com.Rayan.model;

import java.time.LocalDate;

import com.Rayan.domain.WalletTransactionType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class WalletTransaction {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private  Long id;
	
	@ManyToOne
	private Wallet wallet;
	
	private WalletTransactionType type;
	
	private LocalDate date;
	
	private String transferId;
	
	private String puprose;
	
	private Long amount;

	public WalletTransaction(Long id, Wallet wallet, WalletTransactionType type, LocalDate date, String transferId,
			String puprose, Long amount) {
		super();
		this.id = id;
		this.wallet = wallet;
		this.type = type;
		this.date = date;
		this.transferId = transferId;
		this.puprose = puprose;
		this.amount = amount;
	}

	public WalletTransaction() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public WalletTransactionType getType() {
		return type;
	}

	public void setType(WalletTransactionType type) {
		this.type = type;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getTransferId() {
		return transferId;
	}

	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}

	public String getPuprose() {
		return puprose;
	}

	public void setPuprose(String puprose) {
		this.puprose = puprose;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	
}
