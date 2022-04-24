package com.salimahafirassou.paymybuddy.controller;

import javax.servlet.http.Cookie;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
public class HomeControllerTests {

    @Autowired
    MockMvc mvc;

    private Cookie connected_cookie = new Cookie("user_email", "connected_user@test.com");
    private Cookie disconnected_cookie = new Cookie("user_email", "disconnected_user@test.com");
    private Cookie does_not_exist_cookie = new Cookie("user_email", "user_does_not_exist@test.com");
    private Cookie any_cookie = new Cookie("hello", "world");

    @Test
    public void testHome() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/home").cookie(connected_cookie);

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("app/home", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testHomeNoCookie() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/home");

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/login", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testHomeNoToken() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/home").cookie(any_cookie);

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/login", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testHomeUserNotConnected() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/home").cookie(disconnected_cookie);

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/login", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testHomeUserDoesNotExists() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/home").cookie(does_not_exist_cookie);

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/login", result.getModelAndView().getViewName());
        
    }
    
}
