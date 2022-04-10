package com.salimahafirassou.paymybuddy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salimahafirassou.paymybuddy.domain.Buddy;
import com.salimahafirassou.paymybuddy.domain.UserEntity;
import com.salimahafirassou.paymybuddy.repository.BuddyRepository;
import com.salimahafirassou.paymybuddy.repository.UserRepository;

@Service
public class BuddyServiceImpl implements BuddyService{

	@Autowired
	UserRepository userRepository;

	@Autowired
	BuddyRepository buddyRepository;
	
	@Override
	public void addBuddy(String user_email, String buddy_email) throws Exception {
		
		Optional<UserEntity> existing_user = userRepository.findUserByEmail(user_email);
		Optional<UserEntity> existing_buddy = userRepository.findUserByEmail(buddy_email);
		if (existing_user.isEmpty()) {
			throw new Exception("No user with email: " + user_email);
		}
		if (existing_buddy.isEmpty()) {
			throw new Exception("No user with email: " + buddy_email);
		}

		Optional<Buddy> existing_connection = buddyRepository.findConnection(existing_user.get().getId(), existing_buddy.get().getId());
		if (existing_connection.isPresent()) {
			throw new Exception("the connection already exists");
		}

		Buddy new_connection = new Buddy();
		new_connection.setUser(existing_user.get());
		new_connection.setBuddy(existing_buddy.get());
		buddyRepository.save(new_connection);
	}

	@Override
	public void deleteBuddy(String user_email, String buddy_email) throws Exception {
		Optional<UserEntity> existing_user = userRepository.findUserByEmail(user_email);
		Optional<UserEntity> existing_buddy = userRepository.findUserByEmail(buddy_email);
		if (existing_user.isEmpty()) {
			throw new Exception("No user with email: " + user_email);
		}
		if (existing_buddy.isEmpty()) {
			throw new Exception("No user with email: " + buddy_email);
		}

		Optional<Buddy> existing_connection = buddyRepository.findConnection(existing_user.get().getId(), existing_buddy.get().getId());
		if (existing_connection.isEmpty()) {
			throw new Exception("No connection");
		}

		buddyRepository.delete(existing_connection.get());
		
	}

	@Override
	public List<UserEntity> listMyBudies(String email) throws Exception {
		Optional<UserEntity> existing_user = userRepository.findUserByEmail(email);
		return buddyRepository.findConnectionsByUser(existing_user.get().getId());
	}

}
