package com.salimahafirassou.paymybuddy.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.salimahafirassou.paymybuddy.domain.Transaction;
import com.salimahafirassou.paymybuddy.service.TransactionService;


@RestController
@RequestMapping("/transaction")
public class TransactionController {
 
	@Autowired
	TransactionService transactionService;
	
	@GetMapping("")	
	public Set<Transaction> addUserAccount(@RequestParam Long idUser) {
		return null;
		
	}
}
