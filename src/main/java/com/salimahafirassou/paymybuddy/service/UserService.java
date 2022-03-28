package com.salimahafirassou.paymybuddy.service;

import com.salimahafirassou.paymybuddy.dto.UserDto;
import com.salimahafirassou.paymybuddy.exception.UserAlreadyExistException;

public interface UserService {

    void register(final UserDto user) throws UserAlreadyExistException;
    boolean checkIfUserExist(final String email);
}
