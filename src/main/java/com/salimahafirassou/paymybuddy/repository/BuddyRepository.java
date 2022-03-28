package com.salimahafirassou.paymybuddy.repository;

import com.salimahafirassou.paymybuddy.domain.Buddy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuddyRepository extends JpaRepository<Buddy, Long> {
    
}
