package com.salimahafirassou.paymybuddy.service;

import com.salimahafirassou.paymybuddy.domain.User;

public interface TransactionService {

	public void transferToUserAccount(User user, Float solt);
	public void transferToBankAccount(User use, Float solt);
	public void transactionToBuddy(User debitesd, User credited, Float Solt);
	
}
