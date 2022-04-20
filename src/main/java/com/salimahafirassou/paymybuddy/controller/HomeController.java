package com.salimahafirassou.paymybuddy.controller;

import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.salimahafirassou.paymybuddy.exception.UserDoesNotExistsException;
import com.salimahafirassou.paymybuddy.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
	UserService userService;

    @GetMapping("/home")
    public String home(HttpServletRequest request) {

        try {
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
            if (!userService.checkConnected(user_token.get())){
                return "redirect:/login";
            }
            return "app/home";
        } catch (UserDoesNotExistsException e){
            return "redirect:/login";
        }
    }

    
    
}
