package com.salimahafirassou.paymybuddy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salimahafirassou.paymybuddy.domain.UserEntity;
import com.salimahafirassou.paymybuddy.repository.UserRepository;

@Service
public class JoinUserServiceImpl implements JoinUserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public void addBuddy(UserEntity user, String email) throws Exception {
			
			if (! userRepository.findUserByEmail(email).isEmpty()) {
				throw new Exception("The User with the given eamil doesn't exists");
			}
			Optional<UserEntity> us =userRepository.findUserByEmail(email);
			// user.getBuddies().add(user);
			Map<String,String> resultMap =new HashMap<String,String>();
			resultMap.put("message", "FireStation created successfully");
	}

	@Override
	public void deleteBuddy(String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UserEntity> listMyBudies(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
