package com.salimahafirassou.paymybuddy.service;

import com.salimahafirassou.paymybuddy.domain.User;

public interface UserService {
		
	public void loginUser(String email, String passWord);
	public void createUserAccount(User user);
	public void getMyProfilUser(Long id);  // accès profil pour modif ou consultation
}
