package com.salimahafirassou.paymybuddy.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.salimahafirassou.paymybuddy.domain.Transaction;
import com.salimahafirassou.paymybuddy.domain.User;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	@Query("SELECT t Transaction t join t.credeted c WHERE c.id = :idCredeted")
	Set<Transaction> findTransactionByUser(@Param("idCredeted") Long idCredeted);

}