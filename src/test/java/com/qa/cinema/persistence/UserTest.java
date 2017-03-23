package com.qa.cinema.persistence;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

	private String email = "email";
	private String fName = "fname";
	private String lName = "lname";
	private String role = "role";
	private String password = "pass";
	private String salt = "salt";
	private User user = new User(email, fName, lName, role, password, salt);

	@Test
	public void testGetEmail() {
		assertEquals(email, user.getEmail());
	}
	
	@Test
	public void testSetEmail() {
		String newEmail = "new email";
		user.setEmail(newEmail);
		assertEquals(newEmail, user.getEmail());
	}
	
	@Test
	public void testGetFName() {
		assertEquals(fName, user.getFName());
	}
	
	@Test
	public void testSetFName() {
		String name = "first";
		user.setFName(name);
		assertEquals(name, user.getFName());
	}
	
	@Test
	public void testGetLName() {
		assertEquals(lName, user.getLName());
	}
	
	@Test
	public void testSetLName() {
		String name = "last";
		user.setLName(name);
		assertEquals(name, user.getLName());
	}
	
	@Test
	public void testGetRole() {
		assertEquals(role, user.getRole());
	}
	
	@Test
	public void testSetRole() {
		String newRole = "new role";
		user.setRole(newRole);
		assertEquals(newRole, user.getRole());
	}
	
	@Test
	public void testGetPassword() {
		assertEquals(password, user.getPassword());
	}
	
	@Test
	public void testSetPassword() {
		String newPass = "pass2";
		user.setPassword(newPass);
		assertEquals(newPass, user.getPassword());
	}
	
	@Test
	public void testGetSalt() {
		assertEquals(salt, user.getSalt());
	}
	
	@Test
	public void testSetSalt() {
		String newSalt = "new salt";
		user.setSalt(newSalt);
		assertEquals(newSalt, user.getSalt());
	}

}
