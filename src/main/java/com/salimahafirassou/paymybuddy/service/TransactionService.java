package com.salimahafirassou.paymybuddy.service;

import java.util.List;

import com.salimahafirassou.paymybuddy.dto.TransactionTableDto;
import com.salimahafirassou.paymybuddy.exception.UserDoesNotExistsException;

public interface TransactionService {

	public void transferToUserAccount(String user_email, Float amount) throws UserDoesNotExistsException;
	public void transferToBankAccount(String user_email, Float amount) throws UserDoesNotExistsException;
	public void transactionToBuddy(String debited_email, String credited_email, Float amount, String description) throws UserDoesNotExistsException;
	public List<TransactionTableDto> getTransactionsByUser(String user_email) throws UserDoesNotExistsException;
	
}
