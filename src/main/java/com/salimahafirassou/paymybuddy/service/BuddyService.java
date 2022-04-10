package com.salimahafirassou.paymybuddy.service;

import java.util.List;

import com.salimahafirassou.paymybuddy.domain.UserEntity;

public interface BuddyService {

	public void addBuddy( String user_email, String buddy_email) throws Exception;
	public void deleteBuddy(String user_email, String buddy_email) throws Exception;
	public List<UserEntity> listMyBudies (String email) throws Exception;
}
