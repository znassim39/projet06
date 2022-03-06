package com.salimahafirassou.paymybuddy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.salimahafirassou.paymybuddy.domain.User;
import com.salimahafirassou.paymybuddy.repository.UserRepository;

public class JoinUserServiceImpl implements JoinUserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public void addBuddy(User user, String email) throws Exception {
			
			if (! userRepository.findUserByEmail(email).isEmpty()) {
				throw new Exception("The User with the given eamil doesn't exists");
			}
			Optional<User> us =userRepository.findUserByEmail(email);
			user.getBuddies().add(user);
			Map<String,String> resultMap =new HashMap<String,String>();
			resultMap.put("message", "FireStation created successfully");
	}

	@Override
	public void deleteBuddy(String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> listMyBudies(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
