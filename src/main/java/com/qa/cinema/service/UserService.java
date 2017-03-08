package com.qa.cinema.service;

public interface UserService {
	String listAllUsers();

	String createNewUser(String User);

	String updateUser(String email, String User);

	String deleteUser(String email);
}
