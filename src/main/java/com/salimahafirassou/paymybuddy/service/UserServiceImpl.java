package com.salimahafirassou.paymybuddy.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.salimahafirassou.paymybuddy.domain.UserEntity;
import com.salimahafirassou.paymybuddy.dto.UserDto;
import com.salimahafirassou.paymybuddy.dto.UserLoginDto;
import com.salimahafirassou.paymybuddy.exception.UserAlreadyExistException;
import com.salimahafirassou.paymybuddy.exception.UserDoesNotExistsException;
import com.salimahafirassou.paymybuddy.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;

	// @Autowired
    // private PasswordEncoder passwordEncoder;

    @Override
    public void register(UserDto user) throws UserAlreadyExistException {

        //Let's check if user already registered with us
        if(checkIfUserExist(user.getEmail())){
            throw new UserAlreadyExistException("User already exists for this email");
        }
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        // encodePassword(userEntity, user);
        userRepository.save(userEntity);
    }

    @Override
    public boolean login(UserLoginDto user) throws UserDoesNotExistsException {
        
        Optional<UserEntity> existing_user = userRepository.findUserByEmail(user.getEmail());
        if (existing_user.isEmpty()) {
            throw new UserDoesNotExistsException("User does not exist");
        }
        UserEntity loginUser = existing_user.get();

        if (loginUser.getPassword().equals(user.getPassword())) {
            loginUser.setConnected(true);
            userRepository.save(loginUser);
            return true;
        }
        return false;
    }


    @Override
    public boolean checkIfUserExist(String email) {
        return userRepository.findUserByEmail(email).isPresent();
    }

    @Override
    public boolean checkConnected(String email) throws UserDoesNotExistsException {
        Optional<UserEntity> existing_user = userRepository.findUserByEmail(email);
        if (existing_user.isEmpty()) {
            throw new UserDoesNotExistsException("User does not exist");
        }
        return existing_user.get().getConnected();
    }

    @Override
    public void logout(String email) throws UserDoesNotExistsException {
        
        Optional<UserEntity> existing_user = userRepository.findUserByEmail(email);
        if (existing_user.isEmpty()) {
            throw new UserDoesNotExistsException("User does not exist");
        }
        UserEntity loggedinUser = existing_user.get();

        loggedinUser.setConnected(false);
        userRepository.save(loggedinUser);
        
    }

    // private void encodePassword( UserEntity userEntity, UserDto user){
    //     userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
    // }
}
