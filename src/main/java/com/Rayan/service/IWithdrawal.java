package com.Rayan.service;

import java.util.List;

import com.Rayan.model.User;
import com.Rayan.model.Withdrawal;

public interface IWithdrawal {

	Withdrawal requestWithdrawal(Long amount , User  user);
	
	Withdrawal proceedWithdrawal(Long withdrawalId,boolean accept) throws Exception;
	
	List<Withdrawal> getUserWithdrawalHistory(User user);
	
	List<Withdrawal> getAllWithdrawalRequest();
}
