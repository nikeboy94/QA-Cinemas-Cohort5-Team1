package com.qa.cinema.service;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.qa.cinema.persistence.User;
import com.qa.cinema.util.JSONUtil;

@Stateless
@Default
public class DBUserService implements UserService {

	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	@Inject
	private JSONUtil util;

	@Override
	public String listAllUsers() {
		Query query = em.createQuery("SELECT m FROM User m");
		Collection<User> users = (Collection<User>) query.getResultList();
		return util.getJSONForObject(users);
	}
	
	@Override 
	public String findUser(String email, String password) {
		Query query = em.createQuery("SELECT m FROM User m WHERE m.email = :email AND m.password = :password").setParameter("email", email).setParameter("password", password);
		Collection<User> users = (Collection<User>) query.getResultList();
		if(users.size() == 1) {
			return util.getJSONForObject(users);
		} else {
			return "[]";
		}
	}

	@Override
	public String createNewUser(String user) {
		User newUser = util.getObjectForJSON(user, User.class);
		em.persist(newUser);
		return "{\"message\": \"User successfully added\"}";
	}

	@Override
	public String updateUser(String email, String user) {
		User updateUser = util.getObjectForJSON(user, User.class);
		User userInDB = findUser(email);
		if (userInDB != null) {
			userInDB = updateUser;
			em.merge(userInDB);
			return "{\"message\": \"User successfully updated\"}";
		}
		else {
			return "{\"message\": \"Updating user failed\"}";
		}
		
	}

	@Override
	public String deleteUser(String email) {
		User userInDB = findUser(email);
		if (userInDB != null) {
			em.remove(userInDB);
			return "{\"message\": \"User successfully deleted\"}";
		}
		else {
			return "{\"message\": \"Deleting user failed\"}";
		}
		
	}

	private User findUser(String email) {
		return em.find(User.class, email);
	}

}
