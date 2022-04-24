package com.salimahafirassou.paymybuddy.service;

import com.salimahafirassou.paymybuddy.dto.ProfileDto;
import com.salimahafirassou.paymybuddy.dto.UserDto;
import com.salimahafirassou.paymybuddy.dto.UserLoginDto;
import com.salimahafirassou.paymybuddy.exception.PasswordDoesNotMatchException;
import com.salimahafirassou.paymybuddy.exception.UserAlreadyExistException;
import com.salimahafirassou.paymybuddy.exception.UserDoesNotExistsException;
import com.salimahafirassou.paymybuddy.exception.UserNameAlreadyInUseException;
import com.salimahafirassou.paymybuddy.exception.WrongPassworException;

public interface UserService {

    void register(final UserDto user) throws UserAlreadyExistException, PasswordDoesNotMatchException, UserNameAlreadyInUseException;
    boolean login(final UserLoginDto user) throws UserDoesNotExistsException;
    boolean checkIfUserExist(final String email);
    boolean checkConnected(final String email) throws UserDoesNotExistsException;
    void logout(final String email) throws UserDoesNotExistsException;
    void update(final ProfileDto profileDto) throws PasswordDoesNotMatchException, UserDoesNotExistsException, WrongPassworException;
    ProfileDto getUserByEmail(final String email) throws UserDoesNotExistsException;
}
