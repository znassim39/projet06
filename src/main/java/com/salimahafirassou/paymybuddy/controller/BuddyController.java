package com.salimahafirassou.paymybuddy.controller;

import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.salimahafirassou.paymybuddy.dto.ConnectionDto;
import com.salimahafirassou.paymybuddy.exception.UserDoesNotExistsException;
import com.salimahafirassou.paymybuddy.service.BuddyService;
import com.salimahafirassou.paymybuddy.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BuddyController {

    @Autowired
	BuddyService buddyService;

    @Autowired
	UserService userService;

    @GetMapping("/connection")
    public String register(final Model model){
        model.addAttribute("connectionDto", new ConnectionDto());
        return "app/connection";
    }

    @PostMapping("/connection")
    public String createConnection(HttpServletRequest request, final @Valid ConnectionDto connectionDto, final BindingResult bindingResult, final Model model){
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
            if (!userService.checkConnected(user_token.get())){
                return "redirect:/login";
            }
            buddyService.addBuddy(
				user_token.get(), 
				connectionDto.getBuddy_email());
        } catch (UserDoesNotExistsException e){
            return "redirect:/login";
        }
        catch (Exception e){
            model.addAttribute("connectionDto", connectionDto);
            return "app/connection";
        }
        return "redirect:/home";
    }
    
}
