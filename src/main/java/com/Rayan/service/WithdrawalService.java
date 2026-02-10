package com.Rayan.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Rayan.domain.WithdrawalStatus;
import com.Rayan.model.User;
import com.Rayan.model.Withdrawal;
import com.Rayan.repo.WithdrawalRepo;

@Service
public class WithdrawalService implements IWithdrawal {

	@Autowired
	private WithdrawalRepo withdrawalRepo;
	
	@Override
	public Withdrawal requestWithdrawal(Long amount, User user) {
		Withdrawal withdrawal= new Withdrawal();
		withdrawal.setAmount(amount);
		withdrawal.setUser(user);
		withdrawal.setWithdrawalStatus(WithdrawalStatus.PENDING);
		
		return withdrawalRepo.save(withdrawal);
		
	}

	@Override
	public Withdrawal proceedWithdrawal(Long withdrawalId, boolean accept) throws Exception {
		Optional<Withdrawal> opt=withdrawalRepo.findById(withdrawalId);
		if(opt.isEmpty()) {
			throw new Exception("withdrawal not found");
		}
		Withdrawal withdrawal=opt.get();
		
		withdrawal.setDate(LocalDateTime.now());
		
		if(accept) {
			withdrawal.setWithdrawalStatus(WithdrawalStatus.SUCCESS);
		}
		else {
			withdrawal.setWithdrawalStatus(WithdrawalStatus.DECLINE);
		}
		
		return withdrawalRepo.save(withdrawal);
		
	}

	@Override
	public List<Withdrawal> getUserWithdrawalHistory(User user) {
		return withdrawalRepo.findByUserId(user.getId());
	}

	@Override
	public List<Withdrawal> getAllWithdrawalRequest() {
		return withdrawalRepo.findAll();
	}

}
