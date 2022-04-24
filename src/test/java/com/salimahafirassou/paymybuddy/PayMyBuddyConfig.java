package com.salimahafirassou.paymybuddy;

import java.util.List;
import java.util.Date;

import com.salimahafirassou.paymybuddy.domain.Buddy;
import com.salimahafirassou.paymybuddy.domain.Transaction;
import com.salimahafirassou.paymybuddy.domain.UserEntity;
import com.salimahafirassou.paymybuddy.repository.BuddyRepository;
import com.salimahafirassou.paymybuddy.repository.TransactionRepository;
import com.salimahafirassou.paymybuddy.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PayMyBuddyConfig {

	@Bean
	CommandLineRunner commandLineRunner(
			UserRepository userRepository,
			TransactionRepository transactionRepository,
			BuddyRepository buddyRepository) {

		return args -> {

			try {

				UserEntity user_1 = new UserEntity("test", "test", "connected_user@test.com", "0000", 0.0, true, "user_1");
				UserEntity user_2 = new UserEntity("test", "test", "disconnected_user@test.com", "0000", 0.0, false, "user_2");
				UserEntity user_3 = new UserEntity("test", "test", "create_user_ko@test.com", "0000", 0.0, false, "user_3");
				UserEntity user_4 = new UserEntity("test", "test", "login_user_ok@test.com", "0000", 0.0, false, "user_4");
				UserEntity user_5 = new UserEntity("test", "test", "logout_user_ok@test.com", "0000", 0.0, true, "user_5");
				UserEntity user_6 = new UserEntity("test", "test", "update_user_ok_pass_null@test.com", "0000", 0.0,
						true, "user_6");
				UserEntity user_7 = new UserEntity("test", "test", "update_user_ok_pass_empty@test.com", "0000", 0.0,
						true, "user_7");
				UserEntity user_8 = new UserEntity("test", "test", "update_user_ok@test.com", "0000", 0.0, true, "user_8");
				UserEntity user_9 = new UserEntity("test", "test", "update_user_ko_wrong_password@test.com", "0000",
						0.0, true, "user_9");
				UserEntity user_10 = new UserEntity("test", "test", "update_user_ko_password_match@test.com", "0000",
						0.0, true, "user_10");

				UserEntity user_11 = new UserEntity("test", "test", "test_transaction_ok_credeted@test.com", "0000",
						100.0, true, "user_11");
				UserEntity user_12 = new UserEntity("test", "test", "test_transaction_ok_debeted@test.com", "0000",
						100.0, true, "user_12");
				UserEntity user_13 = new UserEntity("test", "test", "test_transaction_ko@test.com", "0000", 10.0, true, "user_13");

				UserEntity user_14 = new UserEntity("test", "test", "test_list_buddies_ok@test.com", "0000", 100.0,
						true, "user_14");
				UserEntity user_15 = new UserEntity("test", "test", "test_add_buddy_ok@test.com", "0000", 10.0, true, "user_15");
				UserEntity user_16 = new UserEntity("test", "test", "test_delete_buddy_ok@test.com", "0000", 100.0,
						true, "user_16");
				UserEntity user_20 = new UserEntity("test", "test", "test_delete_buddy_controller_ok@test.com", "0000", 100.0,
						true, "user_20");
				UserEntity user_17 = new UserEntity("test", "test", "connected_logout_user@test.com", "0000", 0.0, true, "user_17");
				UserEntity user_18 = new UserEntity("test", "test", "test_login_controller_user@test.com", "0000", 0.0, false, "user_18");

				UserEntity user_19 = new UserEntity("test", "test", "test_admin@test.com", "0000", 0.0, false, "admin");
				user_19.setRole("ADMIN");


				userRepository.saveAll(List.of(user_1, user_2, user_3, user_4, user_5, user_6, user_7, user_8, user_9,
						user_10, user_11, user_12, user_13, user_14, user_15, user_16, user_17, user_18, user_19, user_20));

				Transaction transaction_1 = new Transaction(
						userRepository.findUserByEmail("test_transaction_ok_credeted@test.com").get(),
						userRepository.findUserByEmail("test_transaction_ok_debeted@test.com").get(),
						new Date(),
						10.0,
						"hello");

				Transaction transaction_2 = new Transaction(
						userRepository.findUserByEmail("test_transaction_ok_debeted@test.com").get(),
						userRepository.findUserByEmail("test_transaction_ok_credeted@test.com").get(),
						new Date(),
						10.0,
						"hello");

				transactionRepository.saveAll(List.of(transaction_1, transaction_2));

				Buddy buddy_1 = new Buddy(
					userRepository.findUserByEmail("connected_user@test.com").get(),
					userRepository.findUserByEmail("test_list_buddies_ok@test.com").get()
				);
				Buddy buddy_2 = new Buddy(
					userRepository.findUserByEmail("connected_user@test.com").get(),
					userRepository.findUserByEmail("test_delete_buddy_ok@test.com").get()
				);
				Buddy buddy_3 = new Buddy(
					userRepository.findUserByEmail("connected_user@test.com").get(),
					userRepository.findUserByEmail("test_delete_buddy_controller_ok@test.com").get()
				);

				buddyRepository.saveAll(List.of(buddy_1, buddy_2, buddy_3));

			} catch (Exception e) {
				System.out.println(e);
			}
		};

	}

}
