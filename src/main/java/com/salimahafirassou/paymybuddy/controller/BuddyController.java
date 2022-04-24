package com.salimahafirassou.paymybuddy.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.salimahafirassou.paymybuddy.domain.UserEntity;
import com.salimahafirassou.paymybuddy.dto.ConnectionDto;
import com.salimahafirassou.paymybuddy.dto.DeleteConnectionDto;
import com.salimahafirassou.paymybuddy.exception.ConnectionAlreadyExistsException;
import com.salimahafirassou.paymybuddy.exception.ConnectionDoesNotExistException;
import com.salimahafirassou.paymybuddy.exception.UserDoesNotExistsException;
import com.salimahafirassou.paymybuddy.service.BuddyService;
import com.salimahafirassou.paymybuddy.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BuddyController {

    @Autowired
	BuddyService buddyService;

    @Autowired
	UserService userService;

    @GetMapping("/connection")
    public String contacts(HttpServletRequest request, final Model model){
        
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
            model.addAttribute("buddies", buddies);
            model.addAttribute("connectionDto", new ConnectionDto());
            model.addAttribute("deleteConnectionDto", new DeleteConnectionDto());
            return "app/connection";
        } catch (UserDoesNotExistsException e){
            return "redirect:/login";
        }
    }

    @PostMapping("/connection/delete")
    public String deleteConnection(HttpServletRequest request, @RequestParam("email") String email){

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
        
        try {
            if (!userService.checkConnected(user_token.get())){
                return "redirect:/login";
            }
            
            buddyService.deleteBuddy(
				user_token.get(), 
				email);
        } catch (UserDoesNotExistsException e){
            return "redirect:/connection";
        } catch (ConnectionDoesNotExistException e) {
            return "redirect:/connection";
        }
        return "redirect:/connection";
    }
    

    @PostMapping("/connection")
    public String createConnection(HttpServletRequest request, final @Valid ConnectionDto connectionDto, final BindingResult bindingResult, final Model model){
        if(bindingResult.hasErrors()){
            return "redirect:/connection";
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
        List<UserEntity> buddies = null;

        
        try {
            if (!userService.checkConnected(user_token.get())){
                return "redirect:/login";
            }
            
            buddies = buddyService.listMyBudies(user_token.get());
            
            if (user_token.get().equals(connectionDto.getBuddy_email())) {
                bindingResult.rejectValue("buddy_email", "connectionDto.buddy_email", "you cannot add yourself");
                model.addAttribute("connectionDto", connectionDto);
                model.addAttribute("buddies", buddies);
                return "app/connection";
            }
            buddyService.addBuddy(
				user_token.get(), 
				connectionDto.getBuddy_email());
        } catch (UserDoesNotExistsException e){
            bindingResult.rejectValue("buddy_email", "connectionDto.buddy_email", "email does not exist");
            model.addAttribute("connectionDto", connectionDto);
            model.addAttribute("buddies", buddies);
            return "app/connection";
        }
        catch (ConnectionAlreadyExistsException e){
            bindingResult.rejectValue("buddy_email", "connectionDto.buddy_email", e.getMessage());
            model.addAttribute("connectionDto", connectionDto);
            model.addAttribute("buddies", buddies);
            return "app/connection";
        }
        return "redirect:/connection";
    }
    
}
