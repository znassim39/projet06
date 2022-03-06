package com.salimahafirassou.paymybuddy.domain;

import java.util.Date;

import Utils.TypeTransaction;

public class Transaction {
	
	private Long id;
	private User credeted;
	private User debited;
	private Date paymentDate;
	private Float amount;
	
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	private TypeTransaction typeTransaction;
	
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
	public User getCredeted() { return credeted; }
	public void setCredeted(User credeted) { this.credeted = credeted; }
	
	public User getDebited() { return debited; }
	public void setDebited(User debited) { this.debited = debited; }
	
	public Date getPaymentDate() { return paymentDate; }
	public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }
	
	public TypeTransaction getTypeTransaction() { return typeTransaction; }
	public void setTypeTransaction(TypeTransaction typeTransaction) { this.typeTransaction = typeTransaction; }
	
}
