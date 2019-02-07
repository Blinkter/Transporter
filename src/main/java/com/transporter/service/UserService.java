package com.transporter.service;

import com.transporter.entity.User;

public interface UserService {

	public User findUserByEmail(String email);
	void saveUser(User user);
	User getCurrentUser();
}
