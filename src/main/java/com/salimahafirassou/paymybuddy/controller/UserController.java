package com.salimahafirassou.paymybuddy.controller;

import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.salimahafirassou.paymybuddy.dto.ProfileDto;
import com.salimahafirassou.paymybuddy.dto.UserDto;
import com.salimahafirassou.paymybuddy.dto.UserLoginDto;
import com.salimahafirassou.paymybuddy.exception.PasswordDoesNotMatchException;
import com.salimahafirassou.paymybuddy.exception.UserAlreadyExistException;
import com.salimahafirassou.paymybuddy.exception.UserDoesNotExistsException;
import com.salimahafirassou.paymybuddy.exception.WrongPassworException;
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
            model.addAttribute("userDto", userDto);
            return "account/register";
        }
        try {
            userService.register(userDto);
        }catch (PasswordDoesNotMatchException e){
            bindingResult.rejectValue("confirmPassword", "userDto.confirmPassword", "password does not match");
            model.addAttribute("profileDto", userDto);
            return "account/register";
        }catch (UserAlreadyExistException e){
            bindingResult.rejectValue("email", "userDto.email","An account already exists for this email.");
            model.addAttribute("userDto", userDto);
            return "account/register";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(final Model model){
        model.addAttribute("userLoginDto", new UserLoginDto());
        return "account/login";
    }

    @PostMapping("/login")
    public String userLogin(final UserLoginDto userLoginDto, final BindingResult bindingResult, final Model model,
                    HttpServletResponse response){
        if(bindingResult.hasErrors()){
            model.addAttribute("loginForm", userLoginDto);
            return "account/login";
        }
        try {
            if (!userService.login(userLoginDto)){
                bindingResult.rejectValue("password", "userLoginDto.password", "incorrect password");
                model.addAttribute("userLoginDto", userLoginDto);
                return "account/login";
            }
            Cookie token_cookie = new Cookie("user_email", userLoginDto.getEmail());
            response.addCookie(token_cookie);
        }catch (UserDoesNotExistsException e){
            bindingResult.rejectValue("email", "userLoginDto.email","No account with this email.");
            model.addAttribute("userLoginDto", userLoginDto);
            return "account/login";
        }
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){

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

            userService.logout(user_token.get());

            Cookie token_cookie = new Cookie("user_email", null);
            token_cookie.setMaxAge(0);
            response.addCookie(token_cookie);

        } catch (UserDoesNotExistsException e) {
            return "redirect:/login";
        }

        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String update(HttpServletRequest request, final Model model){

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

            ProfileDto profileDto = userService.getUserByEmail(user_token.get());
            model.addAttribute("profileDto", profileDto);
            return "account/profile";
        } catch (UserDoesNotExistsException e) {
            return "redirect:/home";
        }
    }

    @PostMapping("/profile")
    public String userUpdate(final @Valid  ProfileDto profileDto, final BindingResult bindingResult, final Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("profileDto", profileDto);
            return "account/profile";
        }
        try {
            userService.update(profileDto);
        }catch (PasswordDoesNotMatchException e){
            bindingResult.rejectValue("confirmPassword", "profileDto.confirmPassword", "password does not match");
            model.addAttribute("profileDto", profileDto);
            return "account/profile";
        }catch (UserDoesNotExistsException e){
            bindingResult.rejectValue("email", "profileDto.email", "email does not exist");
            model.addAttribute("profileDto", profileDto);
            return "account/profile";
        }catch (WrongPassworException e){
            bindingResult.rejectValue("oldPassword", "profileDto.oldPassword", "incorrect password");
            model.addAttribute("profileDto", profileDto);
            return "account/profile";
        }
        return "redirect:/profile";
    }
}
