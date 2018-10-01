package com.transporter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transporter.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findByUserId(Long id);
}
