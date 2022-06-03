package com.salimahafirassou.paymybuddy.service;

import java.util.Optional;
import com.salimahafirassou.paymybuddy.domain.UserEntity;
import com.salimahafirassou.paymybuddy.dto.ProfileDto;
import com.salimahafirassou.paymybuddy.dto.UserDto;
import com.salimahafirassou.paymybuddy.dto.UserLoginDto;
import com.salimahafirassou.paymybuddy.exception.PasswordDoesNotMatchException;
import com.salimahafirassou.paymybuddy.exception.UserAlreadyExistException;
import com.salimahafirassou.paymybuddy.exception.UserDoesNotExistsException;
import com.salimahafirassou.paymybuddy.exception.WrongPassworException;
import com.salimahafirassou.paymybuddy.repository.TransactionRepository;
import com.salimahafirassou.paymybuddy.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {
    
    @Autowired
    UserService userService;

    @Autowired 
    UserRepository userRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Test
    public void testCreateUserOK() throws Exception {

        UserDto userDto =new UserDto("test", "test", "create_user_ok@test.com", "0000", "0000");

        userService.register(userDto);

        Optional<UserEntity> existing_user = userRepository.findUserByEmail("create_user_ok@test.com");

        assertTrue(existing_user.isPresent());

    }

	@Test
    public void testCreateUserKOUserExists() throws Exception {

        UserDto userDto =new UserDto("test", "test", "create_user_ko@test.com", "0000", "0000");

		assertThrows(UserAlreadyExistException.class, () -> userService.register(userDto));

    }

    @Test
    public void testCreateUserKOPasswordMatch() throws Exception {

        UserDto userDto =new UserDto("test", "test", "create_user_ko_password_match@test.com", "0000", "1111");

		assertThrows(PasswordDoesNotMatchException.class, () -> userService.register(userDto));

    }

    @Test
    public void testUserLoginOK() throws Exception {

        UserLoginDto userDto =new UserLoginDto("login_user_ok@test.com", "0000");

		assert userService.login(userDto) && userRepository.findUserByEmail("login_user_ok@test.com").get().getConnected();

    }

    @Test
    public void testUserLoginKOWrongPassword() throws Exception {

        UserLoginDto userDto =new UserLoginDto("login_user_ok@test.com", "1111");

		assert !userService.login(userDto);

    }

    @Test
    public void testUserLoginKOUserDoesNotExists() throws Exception {

        UserLoginDto userDto =new UserLoginDto("user_does_not_exists@test.com", "1111");

        assertThrows(UserDoesNotExistsException.class, () -> userService.login(userDto));

    }

    @Test
    public void testCheckIfUserExistsTrue() throws Exception {
        assert userService.checkIfUserExist("login_user_ok@test.com");
    }

    @Test
    public void testCheckIfUserExistsFalse() throws Exception {
        assert !userService.checkIfUserExist("user_does_not_exist@test.com");
    }

    @Test
    public void testCheckIfUserConnectedTrue() throws Exception {
        assert userService.checkConnected("connected_user@test.com");
    }

    @Test
    public void testCheckIfUserConnectedFalse() throws Exception {
        assert !userService.checkConnected("disconnected_user@test.com");
    }

    @Test
    public void testCheckIfUserConnectedKOUserDoesNotExists() throws Exception {
        assertThrows(UserDoesNotExistsException.class, () -> userService.checkConnected("user_does_not_exist@test.com"));
    }

    @Test
    public void testLogoutOK() throws Exception {

        userService.logout("logout_user_ok@test.com");

        assertFalse(userRepository.findUserByEmail("logout_user_ok@test.com").get().getConnected());
    }

    @Test
    public void testLogoutKOUserDoesNotExist() throws Exception {

        userService.logout("logout_user_ok@test.com");

        assertThrows(UserDoesNotExistsException.class, () -> userService.logout("user_does_not_exist@test.com"));
    }

    @Test
    public void testUpdateUserOKWithPasswordNull() throws Exception {

        ProfileDto profileDto = new ProfileDto("test1", "test1", "update_user_ok_pass_null@test.com", 0.0, null, null, null);

        userService.update(profileDto);

        UserEntity userEntity = userRepository.findUserByEmail("update_user_ok_pass_null@test.com").get();

        assertEquals("test1", userEntity.getFirstName());
        assertEquals("test1", userEntity.getLastName());
        assertEquals("0000", userEntity.getPassword());

    }

    @Test
    public void testUpdateUserOKWithPasswordEmpty() throws Exception {

        ProfileDto profileDto = new ProfileDto("test1", "test1", "update_user_ok_pass_empty@test.com", 0.0, "", null, null);

        userService.update(profileDto);

        UserEntity userEntity = userRepository.findUserByEmail("update_user_ok_pass_empty@test.com").get();

        assertEquals("test1", userEntity.getFirstName());
        assertEquals("test1", userEntity.getLastName());
        assertEquals("0000", userEntity.getPassword());

    }

    @Test
    public void testUpdateUserOKWithPassword() throws Exception {

        ProfileDto profileDto = new ProfileDto("test1", "test1", "update_user_ok@test.com", 0.0, "0000", "1111", "1111");

        userService.update(profileDto);

        UserEntity userEntity = userRepository.findUserByEmail("update_user_ok@test.com").get();

        assertEquals("test1", userEntity.getFirstName());
        assertEquals("test1", userEntity.getLastName());
        assertEquals("1111", userEntity.getPassword());

    }

    @Test
    public void testUpdateUserOKWongPassword() throws Exception {

        ProfileDto profileDto = new ProfileDto("test1", "test1", "update_user_ko_wrong_password@test.com", 0.0, "1234", "1111", "1111");

        assertThrows(WrongPassworException.class, () -> userService.update(profileDto));
    }

    @Test
    public void testUpdateUserOKPasswordMatch() throws Exception {

        ProfileDto profileDto = new ProfileDto("test1", "test1", "update_user_ko_password_match@test.com", 0.0, "0000", "1111", "1234");
        assertThrows(PasswordDoesNotMatchException.class, () -> userService.update(profileDto));
        
    }

    @Test
    public void testUpdateUserOKUserDoesNotExists() throws Exception {

        ProfileDto profileDto = new ProfileDto("test1", "test1", "user_does_not_exist@test.com", 0.0, null, null, null);
        assertThrows(UserDoesNotExistsException.class, () -> userService.update(profileDto));
        
    }

    @Test
    public void testGetUserByEmailOK() throws Exception {

        ProfileDto profileDto = userService.getUserByEmail("connected_user@test.com");

        assertEquals("connected_user@test.com", profileDto.getEmail());
    }

    @Test
    public void testGetUserByEmailOKUserDoesNotExists() throws Exception {

        assertThrows(UserDoesNotExistsException.class, () -> userService.getUserByEmail("user_does_not_exist@test.com"));
        
    }
}
