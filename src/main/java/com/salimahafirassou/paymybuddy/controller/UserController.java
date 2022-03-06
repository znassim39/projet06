package com.salimahafirassou.paymybuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.salimahafirassou.paymybuddy.domain.User;
import com.salimahafirassou.paymybuddy.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("")	
	public void addUserAccount(@RequestBody User user) {
		
	}
	
	@GetMapping("")
	public void getUser(@RequestParam String email) {
		
	}
	
	@GetMapping("/login")
	public Boolean loginUser(@RequestParam String email, @RequestParam String passWord) {
		return null;
		
	}
}
