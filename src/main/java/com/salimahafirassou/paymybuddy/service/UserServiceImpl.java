package com.salimahafirassou.paymybuddy.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import com.salimahafirassou.paymybuddy.domain.UserEntity;
import com.salimahafirassou.paymybuddy.dto.ProfileDto;
import com.salimahafirassou.paymybuddy.dto.UserDto;
import com.salimahafirassou.paymybuddy.dto.UserLoginDto;
import com.salimahafirassou.paymybuddy.exception.PasswordDoesNotMatchException;
import com.salimahafirassou.paymybuddy.exception.UserAlreadyExistException;
import com.salimahafirassou.paymybuddy.exception.UserDoesNotExistsException;
import com.salimahafirassou.paymybuddy.exception.WrongPassworException;
import com.salimahafirassou.paymybuddy.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;

	// @Autowired
    // private PasswordEncoder passwordEncoder;

    @Override
    public void register(UserDto user) throws UserAlreadyExistException, PasswordDoesNotMatchException {

        if(user.getEmail() == null || user.getEmail().trim().equals("")){
            throw new UserAlreadyExistException("email null!");
        }
        if(user.getFirstName() == null || user.getEmail().trim().equals("")){
            throw new UserAlreadyExistException("FirstName null!");
        }
        if(user.getLastName() == null || user.getEmail().trim().equals("")){
            throw new UserAlreadyExistException("LastName null!");
        }
        if(user.getPassword() == null || user.getEmail().trim().equals("")){
            throw new UserAlreadyExistException("PassWord null!");
        }
        //Let's check if user already registered with us
        if(checkIfUserExist(user.getEmail())){
            throw new UserAlreadyExistException("User already exists for this email");
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new PasswordDoesNotMatchException("password does not match");
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

    @Override
    public void update(ProfileDto profileDto) 
        throws PasswordDoesNotMatchException, UserDoesNotExistsException, WrongPassworException {
        
        Optional<UserEntity> existing_user = userRepository.findUserByEmail(profileDto.getEmail());
        if (existing_user.isEmpty()) {
            throw new UserDoesNotExistsException("User does not exist");
        }
        UserEntity userEntity = existing_user.get();
        
        if (profileDto.getOldPassword() != null && profileDto.getOldPassword() != "") {
            
            if (!userEntity.getPassword().equals(profileDto.getOldPassword())) {
                throw new WrongPassworException("wrong password");
            }
            if (!profileDto.getNewPassword().equals(profileDto.getConfirmPassword())){
                throw new PasswordDoesNotMatchException("password does not match");
            }
            userEntity.setPassword(profileDto.getNewPassword());
        }
        
        userEntity.setFirstName(profileDto.getFirstName());
        userEntity.setLastName(profileDto.getLastName());
        
        userRepository.save(userEntity);
        
    }

    @Override
    public ProfileDto getUserByEmail(String email) throws UserDoesNotExistsException {
        
        Optional<UserEntity> existing_user = userRepository.findUserByEmail(email);
        if (existing_user.isEmpty()) {
            throw new UserDoesNotExistsException("User does not exist");
        }
        UserEntity loggedinUser = existing_user.get();
        ProfileDto profileDto = new ProfileDto();
        profileDto.setFirstName(loggedinUser.getFirstName());
        profileDto.setLastName(loggedinUser.getLastName());
        profileDto.setEmail(loggedinUser.getEmail());
        profileDto.setBalance(loggedinUser.getBalance());
        return profileDto;
    }

    @Override 
    public Boolean checkAdmin(String email) throws UserDoesNotExistsException {
        Optional<UserEntity> existing_user = userRepository.findUserByEmail(email);
        if (existing_user.isEmpty()) {
            throw new UserDoesNotExistsException("User does not exist");
        }
        return existing_user.get().getRole().equals("ADMIN");
    }
    // private void encodePassword( UserEntity userEntity, UserDto user){
    //     userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
    // }
}
