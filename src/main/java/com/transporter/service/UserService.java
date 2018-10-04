package com.transporter.service;

import com.transporter.model.User;

public interface UserService {

	public User findUserByEmail(String email);
	void saveUser(User user);
	User getCurrentUser();
}
