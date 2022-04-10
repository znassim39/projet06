package com.salimahafirassou.paymybuddy.controller;


import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import com.salimahafirassou.paymybuddy.dto.CreateTransactionDto;
import com.salimahafirassou.paymybuddy.service.TransactionService;


@Controller
public class TransactionController {
 
	@Autowired
	TransactionService transactionService;

    @PostMapping("/transaction")
    public String sendMoney(HttpServletRequest request, final @Valid CreateTransactionDto createTransactionDto, final BindingResult bindingResult, final Model model){
        if(bindingResult.hasErrors()){
            return "redirect:/home";
        }
        
        Optional<String> user_token = Arrays.stream(request.getCookies())
                    .filter(cookie->"user_email".equals(cookie.getName()))
                    .map(Cookie::getValue)
                    .findAny();
        if (user_token.isEmpty()){
            return "redirect:/login";
        }
        try {
            transactionService.transactionToBuddy(
				user_token.get(), 
				createTransactionDto.getCredited_email(), 
				createTransactionDto.getAmount(),
				createTransactionDto.getDescription());
        } catch (Exception e){
            System.out.println(e);
            model.addAttribute("createTransactionDto", createTransactionDto);
            return "redirect:/home";
        }
        return "redirect:/home";
    }
	
}
