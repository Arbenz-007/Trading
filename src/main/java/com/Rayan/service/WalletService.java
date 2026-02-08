package com.Rayan.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.Rayan.domain.OrderType;
import com.Rayan.model.Order;
import com.Rayan.model.User;
import com.Rayan.model.Wallet;
import com.Rayan.repo.WalletRepo;

public class WalletService implements IWallet {

	@Autowired
	private WalletRepo walletRepo;
	@Override
	public Wallet getUserWallet(User user) {
		Wallet wallet=walletRepo.findByUserId(user.getId());
		if(wallet==null) {
			wallet=new Wallet();
			wallet.setUser(user);
		}
		
		return wallet;
	}

	@Override
	public Wallet addBalance(Wallet wallet, Long money) {
		BigDecimal balance=wallet.getBalance();
		BigDecimal newBalance=balance.add(BigDecimal.valueOf(money));
		wallet.setBalance(newBalance);
		return walletRepo.save(wallet);
		
	}

	@Override
	public Wallet findWalletById(Long id) throws Exception {
		Optional<Wallet> opt=walletRepo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new Exception("Wallet not found");
	}

	@Override
	public Wallet walletToWalletTransfer(User sender, Wallet receiverWallet, Long amount) throws Exception {
		Wallet senderWallet=getUserWallet(sender);
		if(senderWallet.getBalance().compareTo(BigDecimal.valueOf(amount))<0) {
			throw new Exception("Insufficient Balance");
		}
		BigDecimal senderBalance=senderWallet.getBalance().subtract(BigDecimal.valueOf(amount));
		senderWallet.setBalance(senderBalance);
		walletRepo.save(senderWallet);
		BigDecimal receiverBalance=receiverWallet.getBalance().add(BigDecimal.valueOf(amount));
		receiverWallet.setBalance(receiverBalance);
		walletRepo.save(receiverWallet);
		
		return senderWallet;
	}

	@Override
	public Wallet payOrderPayement(Order order, User user) throws Exception {
		Wallet wallet=getUserWallet(user);
		
		if(order.getOrderType().equals(OrderType.BUY)) {
			BigDecimal newBalance=wallet.getBalance().subtract(order.getPrice());
			if(newBalance.compareTo(order.getPrice())<0) {
				throw new Exception("Insufficent balance for this transaction");
			}
			wallet.setBalance(newBalance);
		}
		else {
			BigDecimal newBalance=wallet.getBalance().add(order.getPrice());
			wallet.setBalance(newBalance);
		}

		return walletRepo.save(wallet);
		
		
	}
}
