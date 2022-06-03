package com.salimahafirassou.paymybuddy.controller;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.salimahafirassou.paymybuddy.domain.UserEntity;
import com.salimahafirassou.paymybuddy.dto.CreateTransactionDto;
import com.salimahafirassou.paymybuddy.dto.TransferDto;
import com.salimahafirassou.paymybuddy.dto.TransactionTableDto;
import com.salimahafirassou.paymybuddy.exception.NotEnoughBalanceException;
import com.salimahafirassou.paymybuddy.exception.UserDoesNotExistsException;
import com.salimahafirassou.paymybuddy.service.BuddyService;
import com.salimahafirassou.paymybuddy.service.TransactionService;
import com.salimahafirassou.paymybuddy.service.UserService;


@Controller
public class TransactionController {
 
	@Autowired
	TransactionService transactionService;

    @Autowired
	UserService userService;

    @Autowired
	BuddyService buddyService;

    @GetMapping("/transfer")
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
            TransferDto transferDto = new TransferDto();
            transferDto.setConnections(buddies);
            transferDto.setTransactions(transactions);
            CreateTransactionDto createTransactionDto = new CreateTransactionDto();
            model.addAttribute("transferDto", transferDto);
            model.addAttribute("createTransactionDto", createTransactionDto);
            return "app/transfer";
        } catch (UserDoesNotExistsException e){
            return "redirect:/login";
        }
    }

    @PostMapping("/transfer")
    public String sendMoney(HttpServletRequest request, final @Valid CreateTransactionDto createTransactionDto, final BindingResult bindingResult, final Model model){
        if(bindingResult.hasErrors()){
            return "redirect:/transfer";
        }

        if (request.getCookies() == null) {
            return "redirect:/login";
        }
        
        Optional<String> user_token = Arrays.stream(request.getCookies())
                    .filter(cookie->"user_email".equals(cookie.getName()))
                    .map(Cookie::getValue)
                    .findAny();
        if (user_token.isEmpty()){
            return "redirect:/login";
        }

        TransferDto transferDto = new TransferDto();

        try {

            List<UserEntity> buddies = buddyService.listMyBudies(user_token.get());
            List<TransactionTableDto> transactions = transactionService.getTransactionsByUser(user_token.get());

            transferDto.setConnections(buddies);
            transferDto.setTransactions(transactions);

            if (userService.checkAdmin(user_token.get())) {
                transactionService.transactionToUser(
				user_token.get(), 
				createTransactionDto.getCredited_email(), 
				createTransactionDto.getAmount(),
				createTransactionDto.getDescription());
            } else {
                transactionService.transactionToBuddy(
                    user_token.get(), 
                    createTransactionDto.getCredited_email(), 
                    createTransactionDto.getAmount(),
                    createTransactionDto.getDescription());
            }
        } catch (NotEnoughBalanceException e){
            bindingResult.rejectValue("amount", "createTransactionDto.amount", e.getMessage());
            model.addAttribute("transferDto", transferDto);
            model.addAttribute("createTransactionDto", createTransactionDto);
            return "app/transfer";
        } catch (Exception e){
            return "redirect:/transfer";
        }
        return "redirect:/transfer";
    }
	
}
