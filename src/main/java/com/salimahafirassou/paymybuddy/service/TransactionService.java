package com.salimahafirassou.paymybuddy.service;

import java.util.List;

import com.salimahafirassou.paymybuddy.dto.TransactionTableDto;

public interface TransactionService {

	public void transferToUserAccount(String user_email, Float amount);
	public void transferToBankAccount(String user_email, Float amount);
	public void transactionToBuddy(String debited_email, String credited_email, Float amount, String description);
	public List<TransactionTableDto> getTransactionsByUser(String user_email);
	
}
