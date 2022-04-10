package com.salimahafirassou.paymybuddy.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.salimahafirassou.paymybuddy.domain.UserEntity;
import com.salimahafirassou.paymybuddy.dto.CreateTransactionDto;
import com.salimahafirassou.paymybuddy.dto.HomeDto;
import com.salimahafirassou.paymybuddy.dto.TransactionTableDto;
import com.salimahafirassou.paymybuddy.exception.UserDoesNotExistsException;
import com.salimahafirassou.paymybuddy.service.BuddyService;
import com.salimahafirassou.paymybuddy.service.TransactionService;
import com.salimahafirassou.paymybuddy.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
	UserService userService;

    @Autowired
	TransactionService transactionService;

    @Autowired
	BuddyService buddyService;

    @GetMapping("/home")
    public String register(HttpServletRequest request, final Model model) throws Exception {
        
        if (request.getCookies() == null) {
            return "redirect:/login";
        }
        Optional<String> user_token = Arrays.stream(request.getCookies())
                    .filter(cookie->"user_email".equals(cookie.getName()))
                    .map(Cookie::getValue)
                    .findAny();
        try {
            if (user_token.isEmpty()){
                return "redirect:/login";
            }
            if (!userService.checkConnected(user_token.get())){
                return "redirect:/login";
            }

            List<UserEntity> buddies = buddyService.listMyBudies(user_token.get());
            List<TransactionTableDto> transactions = transactionService.getTransactionsByUser(user_token.get());
            HomeDto homeDto = new HomeDto();
            homeDto.setConnections(buddies);
            homeDto.setTransactions(transactions);
            CreateTransactionDto createTransactionDto = new CreateTransactionDto();
            model.addAttribute("homeDto", homeDto);
            model.addAttribute("createTransactionDto", createTransactionDto);
            return "app/home";
        } catch (UserDoesNotExistsException e){
            return "redirect:/login";
        }
    }
    
}
