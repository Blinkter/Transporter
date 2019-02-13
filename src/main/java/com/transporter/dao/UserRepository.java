package com.transporter.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transporter.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByName(String currentUserName);

	User findByEmail(String email);
}
