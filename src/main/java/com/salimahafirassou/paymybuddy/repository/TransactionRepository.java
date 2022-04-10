package com.salimahafirassou.paymybuddy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.salimahafirassou.paymybuddy.domain.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	@Query("SELECT t FROM Transaction t join t.credeted c join t.debited d WHERE c.id = :idUser or d.id = :idUser")
	List<Transaction> findTransactionByUser(@Param("idUser") Long idUser);

}