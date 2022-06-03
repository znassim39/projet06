package com.salimahafirassou.paymybuddy.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class ProfileDto implements Serializable {
    @NotNull
    @NotEmpty(message = "firstName can not be empty")
    private String firstName;
    
    @NotNull
    @NotEmpty(message = "lastName can not be empty")
    private String lastName;
    
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
    
    private String email;

	private Double balance;

	public ProfileDto() {
	}

	public ProfileDto(String firstName, String lastName, String email, Double balance, String oldPassword,
			String newPassword, String confirmPassword) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
		this.email = email;
		this.balance = balance;
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

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
    
}