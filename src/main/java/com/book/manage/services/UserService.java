package com.book.manage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.book.manage.models.User;
import com.book.manage.repositories.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User registerUser(User user) {
		// Check if username exists
		if (userRepository.findByUsername(user.getUsername()).isPresent()) {
			throw new IllegalArgumentException("Username already exists!");
		}

		// Encode password
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		// Save user
		return userRepository.save(user);
	}

	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found!"));
	}
}