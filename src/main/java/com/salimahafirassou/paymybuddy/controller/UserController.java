package com.salimahafirassou.paymybuddy.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.salimahafirassou.paymybuddy.dto.UserDto;
import com.salimahafirassou.paymybuddy.dto.UserLoginDto;
import com.salimahafirassou.paymybuddy.exception.UserAlreadyExistException;
import com.salimahafirassou.paymybuddy.exception.UserDoesNotExistsException;
import com.salimahafirassou.paymybuddy.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/register")
    public String register(final Model model){
        model.addAttribute("userDto", new UserDto());
        return "account/register";
    }

    @PostMapping("/register")
    public String userRegistration(final @Valid  UserDto userDto, final BindingResult bindingResult, final Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("registrationForm", userDto);
            return "account/register";
        }
        try {
            userService.register(userDto);
        }catch (UserAlreadyExistException e){
            bindingResult.rejectValue("email", "userDto.email","An account already exists for this email.");
            model.addAttribute("registrationForm", userDto);
            return "account/register";
        }
        return "redirect:/register";
    }

    @GetMapping("/login")
    public String login(final Model model){
        model.addAttribute("userLoginDto", new UserLoginDto());
        return "account/register";
    }

    @PostMapping("/login")
    public String userLogin(final UserLoginDto userLoginDto, final BindingResult bindingResult, final Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("loginForm", userLoginDto);
            return "account/login";
        }
        try {
            if (!userService.login(userLoginDto)){
                bindingResult.rejectValue("password", "userLoginDto.password", "incorrect password");
            };
        }catch (UserDoesNotExistsException e){
            bindingResult.rejectValue("email", "userLoginDto.email","No account with this email.");
            model.addAttribute("loginForm", userLoginDto);
            return "account/login";
        }
        return "redirect:/register";
    }
}
