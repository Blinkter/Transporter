package com.transporter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transporter.model.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long>{

	Driver findOneByLogin(String login);
}
