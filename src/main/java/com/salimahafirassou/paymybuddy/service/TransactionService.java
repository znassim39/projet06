package com.salimahafirassou.paymybuddy.service;

import java.util.List;

import com.salimahafirassou.paymybuddy.domain.Transaction;

public interface TransactionService {

	public void transferToUserAccount(String user_email, Float amount);
	public void transferToBankAccount(String user_email, Float amount);
	public void transactionToBuddy(String debited_email, String credited_email, Float amount, String description);
	public List<Transaction> getTransactionsByUser(String user_email);
	
}
