package com.salimahafirassou.paymybuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Utils.TypeTransaction;

import java.util.Date;

import com.salimahafirassou.paymybuddy.domain.Transaction;
import com.salimahafirassou.paymybuddy.domain.UserEntity;
import com.salimahafirassou.paymybuddy.repository.TransactionRepository;
import com.salimahafirassou.paymybuddy.repository.UserRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	UserRepository userRepository;
	
	@Override
	public void transferToUserAccount(Long idUser, Float solt) {
		/*UserEntity user = userRepository.getById(idUser);

		user.setBalance(user.getBalance() + solt);
		userRepository.save(user);

		Transaction transaction = new Transaction();
		transaction.setDebited(user);
		transaction.setCredeted(user);
		transaction.setAmount(solt);
		transaction.setTypeTransaction(TypeTransaction.TRANSFERTOUSERACCOUNT);
		transaction.setPaymentDate(new Date());
		transactionRepository.save(transaction);*/
	}

	@Override
	public void transferToBankAccount(Long idUser, Float solt) {
		/*UserEntity user = userRepository.getById(idUser);

		if (user.getBalance() >= solt){
			
			user.setBalance(user.getBalance() - solt);
			userRepository.save(user);

			Transaction transaction = new Transaction();
			transaction.setDebited(user);
			transaction.setCredeted(user);
			transaction.setAmount(solt);
			transaction.setTypeTransaction(TypeTransaction.TRANSFERTOBANKACCOUNT);
			transaction.setPaymentDate(new Date());
			transactionRepository.save(transaction);
		}*/
	}

	@Override
	public void transactionToBuddy(Long idDebited, Long idCredited, Float Solt) {

		/*UserEntity debited = userRepository.getById(idDebited);
		UserEntity credited = userRepository.getById(idCredited);
		
		if (debited.getBalance() >= Solt){

			debited.setBalance(debited.getBalance() - Solt);
			credited.setBalance(credited.getBalance() + Solt);

			userRepository.save(credited);
			userRepository.save(debited);

			Transaction transaction = new Transaction();
			transaction.setDebited(debited);
			transaction.setCredeted(credited);
			transaction.setAmount(Solt);
			transaction.setTypeTransaction(TypeTransaction.TRANSFERTOBUDDY);
			transaction.setPaymentDate(new Date());
			transactionRepository.save(transaction);
		}*/
	}

}
