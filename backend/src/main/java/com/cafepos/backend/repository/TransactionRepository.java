package com.cafepos.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafepos.backend.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
}
