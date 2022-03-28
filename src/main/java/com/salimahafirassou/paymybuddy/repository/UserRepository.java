package com.salimahafirassou.paymybuddy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.salimahafirassou.paymybuddy.domain.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

	/*
	 * Get the user for a given id.
	 * @param idUser the id of the User to search for.
	 * @return the user for a given id.
	*/
	@Query("SELECT u FROM UserEntity u WHERE email = ?1")
	Optional<UserEntity> findUserByEmail(String email);
	
	/*
	 * Get the user for a given id.
	 * @param idUser the id of the User to search for.
	 * @return the user for a given id.
	*/
	@Query("SELECT u FROM UserEntity u WHERE id = ?1")
	Optional<UserEntity> getUserById(Long idUser);
}
