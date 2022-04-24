package com.salimahafirassou.paymybuddy.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.salimahafirassou.paymybuddy.domain.Transaction;
import com.salimahafirassou.paymybuddy.dto.TransactionTableDto;
import com.salimahafirassou.paymybuddy.exception.NotEnoughBalanceException;
import com.salimahafirassou.paymybuddy.exception.UserDoesNotExistsException;
import com.salimahafirassou.paymybuddy.repository.TransactionRepository;
import com.salimahafirassou.paymybuddy.repository.UserRepository;

@SpringBootTest
public class TransactionServiceTests {

    @Autowired
    UserService userService;

    @Autowired 
    UserRepository userRepository;

    @Autowired
    TransactionService transactionService;

    @Autowired
    TransactionRepository transactionRepository;
    
    @Test
    public void testTransferToBuddyOK() throws Exception {

        transactionService.transactionToBuddy(
            "test_transaction_ok_debeted@test.com", 
            "test_transaction_ok_credeted@test.com", 
            10.0, 
            "test_transfer_ok");

        List<Transaction> transactions = transactionRepository.findTransactionByUser(
            userRepository.findUserByEmail("test_transaction_ok_debeted@test.com").get().getId()
            );
        
        assertTrue(transactions.stream().filter( transaction -> 
            transaction.getCredeted().getEmail().equals("test_transaction_ok_credeted@test.com")
            && transaction.getDescription().equals("test_transfer_ok")
            ).findAny().isPresent());
    }

    @Test
    public void testTransferToBuddyOKAdmin() throws Exception {

        transactionService.transactionToBuddy(
            "test_admin@test.com", 
            "test_transaction_ok_credeted@test.com", 
            10.0, 
            "test_transfer_ok");

        List<Transaction> transactions = transactionRepository.findTransactionByUser(
            userRepository.findUserByEmail("test_admin@test.com").get().getId()
            );
        
        assertTrue(transactions.stream().filter( transaction -> 
            transaction.getCredeted().getEmail().equals("test_transaction_ok_credeted@test.com")
            && transaction.getDescription().equals("test_transfer_ok")
            ).findAny().isPresent());
    }
    
    @Test
    public void testTransferToBuddyKONotEnoughBalance() throws Exception {

        assertThrows(NotEnoughBalanceException.class, () -> transactionService.transactionToBuddy(
            "test_transaction_ko@test.com", 
            "test_transaction_ok_credeted@test.com", 
            20.0, 
            "test_transfer_ok"));

    }

    @Test
    public void testTransferToBuddyKODebitedDoesNotExist() throws Exception {

        assertThrows(UserDoesNotExistsException.class, () -> transactionService.transactionToBuddy(
            "user_does_not_exist@test.com", 
            "test_transaction_ok_credeted@test.com", 
            10.0, 
            "test_transfer_ok"));

    }

    @Test
    public void testTransferToBuddyKOCreditedDoesNotExist() throws Exception {

        assertThrows(UserDoesNotExistsException.class, () -> transactionService.transactionToBuddy(
            "test_transaction_ok_debeted@test.com", 
            "user_does_not_exist@test.com", 
            10.0, 
            "test_transfer_ok"));

    }

    @Test
    public void testGetTransactionsByUserOK() throws Exception {

        List<TransactionTableDto> transactions = transactionService.getTransactionsByUser("test_transaction_ok_debeted@test.com");

        assertTrue(transactions.size() > 0);
    }

    @Test
    public void testGetTransactionsByUserOKUserDoesNotExist() throws Exception {

        assertThrows(UserDoesNotExistsException.class, () -> transactionService.getTransactionsByUser("user_does_not_exist@test.com"));
    }
}
