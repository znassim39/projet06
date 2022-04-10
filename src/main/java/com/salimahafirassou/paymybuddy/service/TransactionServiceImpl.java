package com.salimahafirassou.paymybuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Utils.TypeTransaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.salimahafirassou.paymybuddy.domain.Transaction;
import com.salimahafirassou.paymybuddy.domain.UserEntity;
import com.salimahafirassou.paymybuddy.dto.TransactionTableDto;
import com.salimahafirassou.paymybuddy.repository.TransactionRepository;
import com.salimahafirassou.paymybuddy.repository.UserRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	UserRepository userRepository;
	
	@Override
	public void transferToUserAccount(String user_email, Float amount) {
		Optional<UserEntity> existing_user = userRepository.findUserByEmail(user_email);

		UserEntity user = existing_user.get();

		user.setBalance(user.getBalance() + amount);
		userRepository.save(user);

		Transaction transaction = new Transaction();
		transaction.setDebited(user);
		transaction.setCredeted(user);
		transaction.setAmount(amount);
		transaction.setTypeTransaction(TypeTransaction.TRANSFERTOUSERACCOUNT);
		transaction.setPaymentDate(new Date());
		transactionRepository.save(transaction);
	}

	@Override
	public void transferToBankAccount(String user_email, Float amount) {
		Optional<UserEntity> existing_user = userRepository.findUserByEmail(user_email);

		UserEntity user = existing_user.get();

		if (user.getBalance() >= amount){
			
			user.setBalance(user.getBalance() - amount);
			userRepository.save(user);

			Transaction transaction = new Transaction();
			transaction.setDebited(user);
			transaction.setCredeted(user);
			transaction.setAmount(amount);
			transaction.setTypeTransaction(TypeTransaction.TRANSFERTOBANKACCOUNT);
			transaction.setPaymentDate(new Date());
			transactionRepository.save(transaction);
		}
	}

	@Override
	public void transactionToBuddy(String debited_email, String credited_email, Float amount, String description) {
		
		Optional<UserEntity> existing_debited = userRepository.findUserByEmail(debited_email);
		UserEntity debited = existing_debited.get();
		
		Optional<UserEntity> existing_credited = userRepository.findUserByEmail(credited_email);
		UserEntity credited = existing_credited.get();
		
		if (debited.getBalance() >= amount){

			debited.setBalance(debited.getBalance() - amount);
			credited.setBalance(credited.getBalance() + amount);

			userRepository.save(credited);
			userRepository.save(debited);

			Transaction transaction = new Transaction();
			transaction.setDebited(debited);
			transaction.setCredeted(credited);
			transaction.setAmount(amount);
			transaction.setDescription(description);
			transaction.setTypeTransaction(TypeTransaction.TRANSFERTOBUDDY);
			transaction.setPaymentDate(new Date());
			transactionRepository.save(transaction);
		}
	}

	@Override
	public List<TransactionTableDto> getTransactionsByUser(String user_email) {

		Optional<UserEntity> existing_user = userRepository.findUserByEmail(user_email);

		UserEntity user = existing_user.get();

		List<TransactionTableDto> transactions = new ArrayList<>();

		transactionRepository.findTransactionByUser(user.getId()).forEach(transactionEntity -> {
			TransactionTableDto transaction = new TransactionTableDto();
			if (transactionEntity.getCredeted().getEmail().equals(user_email)){

				transaction.setBuddy_name(transactionEntity.getDebited().getFirstName() + " " + transactionEntity.getDebited().getLastName());
				transaction.setAmount(transactionEntity.getAmount());
			} else {
				transaction.setBuddy_name(transactionEntity.getCredeted().getFirstName() + " " + transactionEntity.getCredeted().getLastName());
				transaction.setAmount(transactionEntity.getAmount() * -1);
			}
			transaction.setDescription(transactionEntity.getDescription());

			transactions.add(transaction);
		});

		return transactions;
	}

}
