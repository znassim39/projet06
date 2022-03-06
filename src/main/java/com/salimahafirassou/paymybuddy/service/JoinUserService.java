package com.salimahafirassou.paymybuddy.service;

import java.util.List;

import com.salimahafirassou.paymybuddy.domain.User;

public interface JoinUserService {

	public void addBuddy( User user, String email) throws Exception;
	public void deleteBuddy(String email);
	public List<User> listMyBudies (Long id);
}
