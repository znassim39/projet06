package com.salimahafirassou.paymybuddy.service;

import java.util.List;

import com.salimahafirassou.paymybuddy.domain.UserEntity;
import com.salimahafirassou.paymybuddy.exception.ConnectionAlreadyExists;
import com.salimahafirassou.paymybuddy.exception.UserDoesNotExistsException;

public interface BuddyService {

	public void addBuddy( String user_email, String buddy_email) throws UserDoesNotExistsException, ConnectionAlreadyExists;
	public void deleteBuddy(String user_email, String buddy_email) throws UserDoesNotExistsException;
	public List<UserEntity> listMyBudies (String email) throws UserDoesNotExistsException;
}
