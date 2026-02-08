package com.Rayan.service;

import com.Rayan.model.Order;
import com.Rayan.model.User;
import com.Rayan.model.Wallet;

public interface IWallet {

	Wallet getUserWallet(User user);
	Wallet addBalance(Wallet wallet, Long money);
	Wallet findWalletById(Long id) throws Exception;
	Wallet walletToWalletTransfer(User sender, Wallet receiverWallet, Long amount) throws Exception;
	Wallet payOrderPayement(Order order, User user) throws Exception;
}
