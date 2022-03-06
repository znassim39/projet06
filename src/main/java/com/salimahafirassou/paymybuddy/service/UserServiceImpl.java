package com.salimahafirassou.paymybuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salimahafirassou.paymybuddy.domain.User;
import com.salimahafirassou.paymybuddy.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public void loginUser(String email, String passWord) {
		
		
	}
	
	@Override
	public void createUserAccount(User user) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void getMyProfilUser(Long id) {
		// TODO Auto-generated method stub
		
	}

	
	
}
