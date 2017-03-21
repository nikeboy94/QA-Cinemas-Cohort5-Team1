package com.qa.cinema.service;

public interface UserService {
	String listAllUsers();
	
	String findUser(String email);

	String createNewUser(String user);

	String updateUser(String email, String user);

	String deleteUser(String email);
	
	boolean loginAttempt(String email, String password);
}
