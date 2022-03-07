package com.salimahafirassou.paymybuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salimahafirassou.paymybuddy.domain.Transaction;
import com.salimahafirassou.paymybuddy.domain.User;
import com.salimahafirassou.paymybuddy.repository.TransactionRepository;
import com.salimahafirassou.paymybuddy.repository.UserRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	UserRepository userRepository;
	
	@Override
	public void transferToUserAccount(User user, Float solt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transferToBankAccount(User use, Float solt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transactionToBuddy(Long idDebited, Long idCredited, Float Solt) {

		User debited = userRepository.getById(idDebited);
		User credited = userRepository.getById(idCredited);
		
		if (debited.getBalance() >= Solt){

			debited.setBalance(debited.getBalance() - Solt);
			credited.setBalance(credited.getBalance() + Solt);

			userRepository.save(credited);
			userRepository.save(debited);

			Transaction transaction = new Transaction();
			transaction.setDebited(debited);
			transaction.setCredeted(credited);
			transaction.setAmount(Solt);
			transactionRepository.save(transaction);
		}
	}

}
