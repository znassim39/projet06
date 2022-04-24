package com.salimahafirassou.paymybuddy.service;

import java.util.List;

import com.salimahafirassou.paymybuddy.dto.TransactionTableDto;
import com.salimahafirassou.paymybuddy.exception.NotEnoughBalanceException;
import com.salimahafirassou.paymybuddy.exception.UserDoesNotExistsException;

public interface TransactionService {

	public void transactionToBuddy(String debited_email, String credited_email, Double amount, String description) throws UserDoesNotExistsException, NotEnoughBalanceException;
	public List<TransactionTableDto> getTransactionsByUser(String user_email) throws UserDoesNotExistsException;
	
}
