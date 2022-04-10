package com.salimahafirassou.paymybuddy.repository;

import java.util.List;
import java.util.Optional;

import com.salimahafirassou.paymybuddy.domain.Buddy;
import com.salimahafirassou.paymybuddy.domain.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BuddyRepository extends JpaRepository<Buddy, Long> {

    @Query("SELECT b.buddy FROM Buddy b join b.user u WHERE u.id = :idUser")
	List<UserEntity> findConnectionsByUser(@Param("idUser") Long idUser);

    @Query("SELECT b FROM Buddy b join b.user u join b.buddy bb WHERE u.id = :idUser and bb.id = :idBuddy")
	Optional<Buddy> findConnection(@Param("idUser") Long idUser, @Param("idBuddy") Long idBuddy);
    
}
