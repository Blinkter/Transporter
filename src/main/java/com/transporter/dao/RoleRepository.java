package com.transporter.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transporter.entity.Role;


public interface RoleRepository extends JpaRepository<Role, Integer> {
	
    Role findByRole(String role);

}
