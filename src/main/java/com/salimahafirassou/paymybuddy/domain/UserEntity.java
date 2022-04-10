package com.salimahafirassou.paymybuddy.domain;

import static javax.persistence.GenerationType.AUTO;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
	name="user_account",
	uniqueConstraints = {
		@UniqueConstraint(name = "user_unique", columnNames = {"email"})
	}
)
public class UserEntity {
	
	@Id
    @GeneratedValue(
            strategy = AUTO
    )
	@Column(name="id")
	private Long id;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="bankAccount")
	private String banckAccount;
	
	@Column(name="balance")
	private Double balance = 0.0;

	@Column(name="connected")
	private Boolean connected;
	
	public UserEntity() {
	}
	
	public UserEntity(Long id, String firstName, String lastName, String email, String password, Boolean connected) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.connected = connected;
	}
	
	public UserEntity(String firstName, String lastName, String email, String password, Boolean connected) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.connected = connected;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBanckAccount() {
		return banckAccount;
	}
	public void setBanckAccount(String banckAccount) {
		this.banckAccount = banckAccount;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Boolean getConnected() {
		return connected;
	}
	public void setConnected(Boolean connected) {
		this.connected = connected;
	}
	

}
