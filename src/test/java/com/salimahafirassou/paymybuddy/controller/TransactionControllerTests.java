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
public class TransactionControllerTests {

    @Autowired
    MockMvc mvc;
    
    private Cookie connected_cookie = new Cookie("user_email", "connected_user@test.com");
    private Cookie disconnected_cookie = new Cookie("user_email", "disconnected_user@test.com");
    private Cookie does_not_exist_cookie = new Cookie("user_email", "user_does_not_exist@test.com");
    private Cookie any_cookie = new Cookie("hello", "world");
    private Cookie debited_cookie = new Cookie("user_email", "test_transaction_ok_debeted@test.com");
    private Cookie not_enough_balance_cookie = new Cookie("user_email", "test_transaction_ko@test.com");

    @Test
    public void testTransfer() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/transfer").cookie(connected_cookie);

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("app/transfer", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testTransferNoCookie() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/transfer");

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/login", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testTransferNoToken() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/transfer").cookie(any_cookie);

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/login", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testTransferUserNotConnected() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/transfer").cookie(disconnected_cookie);

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/login", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testTransferUserDoesNotExists() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/transfer").cookie(does_not_exist_cookie);

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/login", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testSendMoney() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.post("/transfer").cookie(debited_cookie)
        .param("credited_email", "test_transaction_ok_credeted@test.com")
        .param("amount", "10.0")
        .param("description", "hello")
        ;

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/transfer", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testSendMoneyNoCookie() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.post("/transfer")
        .param("credited_email", "test_transaction_ok_credeted@test.com")
        .param("amount", "10.0")
        .param("description", "hello")
        ;

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/login", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testSendMoneyNoToken() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.post("/transfer").cookie(any_cookie)
        .param("credited_email", "test_transaction_ok_credeted@test.com")
        .param("amount", "10.0")
        .param("description", "hello")
        ;

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/login", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testSendMoneyUserDoesNotExist() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.post("/transfer").cookie(does_not_exist_cookie)
        .param("credited_email", "test_transaction_ok_credeted@test.com")
        .param("amount", "10.0")
        .param("description", "hello")
        ;

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/transfer", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testSendMoneyNotEnoughBalance() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.post("/transfer").cookie(not_enough_balance_cookie)
        .param("credited_email", "test_transaction_ok_credeted@test.com")
        .param("amount", "20.0")
        .param("description", "hello")
        ;

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("app/transfer", result.getModelAndView().getViewName());
        
    }
    
}
