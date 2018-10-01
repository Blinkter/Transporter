package com.transporter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transporter.entity.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findById(Long id);

	User findByFirstName(String currentUserName);
	User findOneByLogin(String login);
	User findByEmail(String email);
}
