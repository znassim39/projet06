package com.salimahafirassou.paymybuddy.service;

import com.salimahafirassou.paymybuddy.exception.ConnectionAlreadyExistsException;
import com.salimahafirassou.paymybuddy.exception.ConnectionDoesNotExistException;
import com.salimahafirassou.paymybuddy.exception.UserDoesNotExistsException;
import com.salimahafirassou.paymybuddy.repository.BuddyRepository;
import com.salimahafirassou.paymybuddy.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BuddyServiceTests {

    @Autowired
    UserService userService;

    @Autowired 
    UserRepository userRepository;

    @Autowired
    BuddyService buddyService;

    @Autowired
    BuddyRepository buddyRepository;

    @Test
    public void testAddBuddyOK() throws Exception {

        buddyService.addBuddy("connected_user@test.com", "test_add_buddy_ok@test.com");

        assertTrue(buddyRepository.findConnection(
            userRepository.findUserByEmail("connected_user@test.com").get().getId(),
            userRepository.findUserByEmail("test_add_buddy_ok@test.com").get().getId()
        ).isPresent());
    }

    @Test
    public void testAddBuddyKOUserDoesNotExist() throws Exception {

        assertThrows(UserDoesNotExistsException.class, () -> buddyService.addBuddy("user_does_not_exist@test.com", "test_add_buddy_ok@test.com"));
    }

    @Test
    public void testAddBuddyKOBuddyDoesNotExist() throws Exception {

        assertThrows(UserDoesNotExistsException.class, () -> buddyService.addBuddy("connected_user@test.com", "user_does_not_exist@test.com"));
    }

    @Test
    public void testAddBuddyKOConnectionExist() throws Exception {

        assertThrows(ConnectionAlreadyExistsException.class, () -> buddyService.addBuddy("connected_user@test.com", "test_list_buddies_ok@test.com"));
    }

    @Test
    public void testDeleteBuddyOK() throws Exception {

        buddyService.deleteBuddy("connected_user@test.com", "test_delete_buddy_ok@test.com");

        assertTrue(buddyRepository.findConnection(
            userRepository.findUserByEmail("connected_user@test.com").get().getId(),
            userRepository.findUserByEmail("test_delete_buddy_ok@test.com").get().getId()
        ).isEmpty());
    }

    @Test
    public void testDeleteBuddyKOUserDoesNotExist() throws Exception {

        assertThrows(UserDoesNotExistsException.class, () -> buddyService.deleteBuddy("user_does_not_exist@test.com", "test_delete_buddy_ok@test.com"));
    }

    @Test
    public void testDeleteBuddyKOBuddyDoesNotExist() throws Exception {

        assertThrows(UserDoesNotExistsException.class, () -> buddyService.deleteBuddy("connected_user@test.com", "user_does_not_exist@test.com"));
    }

    @Test
    public void testDeleteBuddyKOConnectionDoesNotExist() throws Exception {

        assertThrows(ConnectionDoesNotExistException.class, () -> buddyService.deleteBuddy("connected_user@test.com", "disconnected_user@test.com"));
    }

    @Test
    public void testListBuddiesOK() throws Exception {

        assertTrue(buddyService.listMyBudies("connected_user@test.com").size() > 0);
    }

    @Test
    public void testListBuddiesOKAdmin() throws Exception {

        assertTrue(buddyService.listMyBudies("test_admin@test.com").size() > 0);
    }

    @Test
    public void testListBuddiesKOUserDoesNotExist() throws Exception {

        assertThrows(UserDoesNotExistsException.class, () -> buddyService.listMyBudies("user_does_not_exist@test.com"));
    }
    
}
