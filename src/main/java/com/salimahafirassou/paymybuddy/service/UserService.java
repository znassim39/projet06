package com.salimahafirassou.paymybuddy.service;

import com.salimahafirassou.paymybuddy.dto.UserDto;
import com.salimahafirassou.paymybuddy.dto.UserLoginDto;
import com.salimahafirassou.paymybuddy.exception.UserAlreadyExistException;
import com.salimahafirassou.paymybuddy.exception.UserDoesNotExistsException;

public interface UserService {

    void register(final UserDto user) throws UserAlreadyExistException;
    boolean login(final UserLoginDto user) throws UserDoesNotExistsException;
    boolean checkIfUserExist(final String email);
}
