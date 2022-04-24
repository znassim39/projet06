package com.salimahafirassou.paymybuddy.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.servlet.http.Cookie;

import com.salimahafirassou.paymybuddy.service.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

    @Autowired
	UserService userService;

    @Autowired
    MockMvc mvc;

    private Cookie connected_cookie = new Cookie("user_email", "connected_user@test.com");
    private Cookie connected_logout_cookie = new Cookie("user_email", "connected_logout_user@test.com");
    private Cookie disconnected_cookie = new Cookie("user_email", "disconnected_user@test.com");
    private Cookie does_not_exist_cookie = new Cookie("user_email", "user_does_not_exist@test.com");
    private Cookie any_cookie = new Cookie("hello", "world");

    @Test
    public void testRegister() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/register");

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("account/register", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testGetLogin() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/login");

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("account/login", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testPostLoginOK() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.post("/login")
            .param("email", "test_login_controller_user@test.com")
            .param("password", "0000");

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/home", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testPostLoginIncorrectPassword() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.post("/login")
            .param("email", "test_login_controller_user@test.com")
            .param("password", "1111");

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("account/login", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testPostLoginUserDoesNotExist() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.post("/login")
            .param("email", "user_does_not_exist@test.com")
            .param("password", "0000");

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("account/login", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testLogout() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/logout").cookie(connected_logout_cookie);

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/login", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testLogoutNoCookie() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/logout");

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/login", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testLogoutNoToken() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/logout").cookie(any_cookie);

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/login", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testLogoutUserNotConnected() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/logout").cookie(disconnected_cookie);

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/login", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testLogoutUserDoesNotExists() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/logout").cookie(does_not_exist_cookie);

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/login", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testProfile() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/profile").cookie(connected_cookie);

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("account/profile", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testProfileNoCookie() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/profile");

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/login", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testProfileNoToken() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/profile").cookie(any_cookie);

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/login", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testProfileUserNotConnected() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/profile").cookie(disconnected_cookie);

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/login", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testProfileUserDoesNotExists() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/profile").cookie(does_not_exist_cookie);

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/home", result.getModelAndView().getViewName());
        
    }
    
}