package com.salimahafirassou.paymybuddy.domain;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
	name="user_account",
	uniqueConstraints = {
		@UniqueConstraint(name = "user_unique", columnNames = {"email"})
	}
)
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "serial")
	private Long id;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="passWord")
	private String passWord;
	
	@Column(name="banckAccount")
	private String banckAccount;
	
	@Column(name="balance")
	private Float balance;
	
	@ManyToMany()
	@JoinTable(name = "joinusers",
				joinColumns=@JoinColumn(
						name = "id"))
	private Set<User> buddies;
	
		
	public Float getBalance() {
		return balance;
	}
	public void setBalance(Float balance) {
		this.balance = balance;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getBanckAccount() {
		return banckAccount;
	}
	public void setBanckAccount(String banckAccount) {
		this.banckAccount = banckAccount;
	}
	public Set<User> getBuddies() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setBuddies(Set<User> buddies) {
		this.buddies = buddies;
	}
	

}
