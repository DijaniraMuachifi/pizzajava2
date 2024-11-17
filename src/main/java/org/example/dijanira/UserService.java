package org.example.dijanira.service;

import org.example.dijanira.dao.UserManager;
import org.example.dijanira.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserManager userManager;

	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserManager userManager) {
		this.userManager = userManager;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}


	public boolean registerUser(User user) {
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);
		return userManager.addUser(user);
	}


	public boolean isEmailExists(String email) {
		return userManager.isEmailExists(email);
	}
}
