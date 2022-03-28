package com.salimahafirassou.paymybuddy.service;

public interface TransactionService {

	public void transferToUserAccount(Long idUser, Float solt);
	public void transferToBankAccount(Long idUser, Float solt);
	public void transactionToBuddy(Long idDebited, Long idCredited, Float Solt);
	
}
