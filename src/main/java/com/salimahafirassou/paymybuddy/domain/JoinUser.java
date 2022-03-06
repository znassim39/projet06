package com.salimahafirassou.paymybuddy.domain;

public class JoinUser {

	private User credeted;
	private User debited;
	
	public User getCredeted() { return credeted; }
	public void setCredeted(User credeted) { this.credeted = credeted; }
	
	public User getDebited() { return debited; }	
	public void setDebited(User debited) { this.debited = debited; }
	
}
