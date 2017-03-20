package com.qa.cinema.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	private String email;
	private String fName;
	private String lName;
	private String role;
	private String password;
	private String salt;

	public User() {
		//Empty constructor
	}

	public User(String email, String fName, String lName, String role, String password, String salt) {
		super();
		this.email = email;
		this.fName = fName;
		this.lName = lName;
		this.role = role;
		this.password = password;
		this.salt = salt;
	}
	/*
	public User(User u) {
		super();
		this.email = u.email;
		this.fName = u.fName;
		this.lName = u.lName;
		this.role = u.role;
		this.password = u.password;
		this.salt = u.salt;
	}
*/
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getFName() {
		return fName;
	}

	public void setFName(String fName) {
		this.fName = fName;
	}

	public String getLName() {
		return lName;
	}

	public void setLName(String lName) {
		this.lName = lName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
}
	