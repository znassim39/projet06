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
public class BuddyControllerTests {

    @Autowired
    MockMvc mvc;

    private Cookie connected_cookie = new Cookie("user_email", "connected_user@test.com");
    private Cookie disconnected_cookie = new Cookie("user_email", "disconnected_user@test.com");
    private Cookie does_not_exist_cookie = new Cookie("user_email", "user_does_not_exist@test.com");
    private Cookie any_cookie = new Cookie("hello", "world");

    @Test
    public void testContacts() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/connection").cookie(connected_cookie);

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("app/connection", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testContactsNoCookie() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/connection");

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/login", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testContactsNoToken() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/connection").cookie(any_cookie);

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/login", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testContactsUserNotConnected() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/connection").cookie(disconnected_cookie);

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/login", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testContactsUserDoesNotExists() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/connection").cookie(does_not_exist_cookie);

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/login", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testDeleteConnection() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.post("/connection/delete").cookie(connected_cookie)
        .param("email", "test_delete_buddy_controller_ok@test.com");

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/connection", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testDeleteConnectionNoCookie() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.post("/connection/delete")
        .param("email", "test_delete_buddy_controller_ok@test.com");

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/login", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testDeleteConnectionNoToken() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.post("/connection/delete").cookie(any_cookie)
        .param("email", "test_delete_buddy_controller_ok@test.com");

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/login", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testDeleteConnectionUserNotConnected() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.post("/connection/delete").cookie(disconnected_cookie)
        .param("email", "test_delete_buddy_controller_ok@test.com");

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/login", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testDeleteConnectionUserDoesNotExists() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.post("/connection/delete").cookie(does_not_exist_cookie)
        .param("email", "test_delete_buddy_controller_ok@test.com");

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/connection", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testDeleteConnectionConnectionDoesNotExist() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.post("/connection/delete").cookie(connected_cookie)
        .param("email", "disconnected_user@test.com");

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/connection", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testCreateConnection() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.post("/connection").cookie(connected_cookie)
        .param("buddy_email", "create_user_ko@test.com");

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/connection", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testCreateConnectionNoCookie() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.post("/connection")
        .param("buddy_email", "create_user_ko@test.com");

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/login", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testCreateConnectionNoToken() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.post("/connection").cookie(any_cookie)
        .param("buddy_email", "create_user_ko@test.com");

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/login", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testCreateConnectionUserNotConnected() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.post("/connection").cookie(disconnected_cookie)
        .param("buddy_email", "create_user_ko@test.com");

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("redirect:/login", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testCreateConnectionUserDoesNotExists() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.post("/connection").cookie(does_not_exist_cookie)
        .param("buddy_email", "create_user_ko@test.com");

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("app/connection", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testCreateConnectionConnectionAlreadyExists() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.post("/connection").cookie(connected_cookie)
        .param("buddy_email", "test_list_buddies_ok@test.com");

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("app/connection", result.getModelAndView().getViewName());
        
    }

    @Test
    public void testCreateConnectionCannotAddYourself() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.post("/connection").cookie(connected_cookie)
        .param("buddy_email", "connected_user@test.com");

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("app/connection", result.getModelAndView().getViewName());
        
    }
    
}
