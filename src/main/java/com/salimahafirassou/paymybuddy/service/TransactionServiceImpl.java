package com.salimahafirassou.paymybuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.salimahafirassou.paymybuddy.domain.Transaction;
import com.salimahafirassou.paymybuddy.domain.UserEntity;
import com.salimahafirassou.paymybuddy.dto.TransactionTableDto;
import com.salimahafirassou.paymybuddy.exception.NotEnoughBalanceException;
import com.salimahafirassou.paymybuddy.exception.UserDoesNotExistsException;
import com.salimahafirassou.paymybuddy.repository.TransactionRepository;
import com.salimahafirassou.paymybuddy.repository.UserRepository;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public void transactionToBuddy(String debited_email, String credited_email, Double amount, String description) 
		throws UserDoesNotExistsException, NotEnoughBalanceException {
		
		Optional<UserEntity> existing_debited = userRepository.findUserByEmail(debited_email);
		if (existing_debited.isEmpty()) {
			throw new UserDoesNotExistsException("No user with email: " + debited_email);
		}
		UserEntity debited = existing_debited.get();
		
		Optional<UserEntity> existing_credited = userRepository.findUserByEmail(credited_email);
		if (existing_credited.isEmpty()) {
			throw new UserDoesNotExistsException("No user with email: " + credited_email);
		}
		UserEntity credited = existing_credited.get();

		if (debited.getBalance() >= amount){
			debited.setBalance(debited.getBalance() - amount);
			userRepository.save(debited);
		}else {
			throw new NotEnoughBalanceException("Your balance is not enough for this operation");
		}
		
		credited.setBalance(credited.getBalance() + (amount * 0.95));
		
		userRepository.save(credited);

		Transaction transaction = new Transaction();
		transaction.setDebited(debited);
		transaction.setCredeted(credited);
		transaction.setAmount(amount);
		transaction.setDescription(description);
		transaction.setPaymentDate(new Date());
		transactionRepository.save(transaction);
	}

	@Override
	public void transactionToUser(String debited_email, String credited_email, Double amount, String description) 
		throws UserDoesNotExistsException {
		
		Optional<UserEntity> existing_debited = userRepository.findUserByEmail(debited_email);
		if (existing_debited.isEmpty()) {
			throw new UserDoesNotExistsException("No user with email: " + debited_email);
		}
		UserEntity debited = existing_debited.get();
		
		Optional<UserEntity> existing_credited = userRepository.findUserByEmail(credited_email);
		if (existing_credited.isEmpty()) {
			throw new UserDoesNotExistsException("No user with email: " + credited_email);
		}
		UserEntity credited = existing_credited.get();
		
		credited.setBalance(credited.getBalance() + amount);
		
		userRepository.save(credited);

		Transaction transaction = new Transaction();
		transaction.setDebited(debited);
		transaction.setCredeted(credited);
		transaction.setAmount(amount);
		transaction.setDescription(description);
		transaction.setPaymentDate(new Date());
		transactionRepository.save(transaction);
	}

	@Override
	public List<TransactionTableDto> getTransactionsByUser(String user_email) throws UserDoesNotExistsException {

		Optional<UserEntity> existing_user = userRepository.findUserByEmail(user_email);
		if (existing_user.isEmpty()) {
			throw new UserDoesNotExistsException("No user with email: " + user_email);
		}

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
