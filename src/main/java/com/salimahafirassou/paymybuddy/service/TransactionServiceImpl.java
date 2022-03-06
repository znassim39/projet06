package com.salimahafirassou.paymybuddy.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.salimahafirassou.paymybuddy.domain.User;
import com.salimahafirassou.paymybuddy.repository.TransactionRepository;

public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository transactionRepository;
	
	@Override
	public void transferToUserAccount(User user, Float solt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transferToBankAccount(User use, Float solt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transactionToBuddy(User debitesd, User credited, Float Solt) {
		// TODO Auto-generated method stub
		
	}

}
