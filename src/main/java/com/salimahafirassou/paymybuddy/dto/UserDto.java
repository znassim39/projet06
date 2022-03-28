package com.salimahafirassou.paymybuddy.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class UserDto implements Serializable {
    @NotNull
    @NotEmpty(message = "firstName can not be empty")
    private String firstName;
    
    @NotNull
    @NotEmpty(message = "lastName can not be empty")
    private String lastName;
    
    @NotNull
    @NotEmpty(message = "password can not be empty")
    private String password;
    //private String matchingPassword;
    
    @NotNull
    @NotEmpty(message = "email can not be empty")
    @Email(message = "email not valid")
    private String email;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}*/

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    
}