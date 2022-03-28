package com.salimahafirassou.paymybuddy.service;

import java.util.List;

import com.salimahafirassou.paymybuddy.domain.UserEntity;

public interface JoinUserService {

	public void addBuddy( UserEntity user, String email) throws Exception;
	public void deleteBuddy(String email);
	public List<UserEntity> listMyBudies (Long id);
}
