package com.qa.cinema.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
	public String findUser(String email) {
		Query query = em.createQuery("SELECT m FROM User m WHERE m.email = :email").setParameter("email", email);
		User user = (User)query.getSingleResult();
		return util.getRedactedJSONForObject(user);
	}

	@Override
	public String createNewUser(String user) {
		User newUser = util.getObjectForJSON(user, User.class);
		newUser.setPassword(hashSHA(newUser.getPassword(), getSalt(newUser.getEmail())));
		em.persist(newUser);
		return "{\"message\": \"User successfully added\"}";
	}

	@Override
	public String updateUser(String email, String user) {
		User updateUser = util.getObjectForJSON(user, User.class);
		User userInDB = findUserInDb(email);
		if (userInDB != null) {
			userInDB = updateUser;
			em.merge(userInDB);
			return "{\"message\": \"User successfully updated\"}";
		}
		else {
			return "{\"message\": \"Updating user failed\"}";
		}
		
	}
	
	public String getPassword(String email) {
		Query query = em.createQuery("SELECT m.password from User m WHERE m.email = :email").setParameter("email", email);
		return (String)query.getSingleResult();
	}
	
	public String getSalt(String email) {
		Query query = em.createQuery("SELECT m.salt from User m WHERE m.email = :email").setParameter("email", email);
		return (String)query.getSingleResult();
	}
	
	@Override
	public boolean loginAttempt(String email, String password) {
		return (hashSHA(password, getSalt(email)).equals(getPassword(email)));
	}

	@Override
	public String deleteUser(String email) {
		User userInDB = findUserInDb(email);
		if (userInDB != null) {
			em.remove(userInDB);
			return "{\"message\": \"User successfully deleted\"}";
		}
		else {
			return "{\"message\": \"Deleting user failed\"}";
		}
		
	}
	
	// Author - Mike Gray
		public String hashSHA(String _pass, String _salt){
			String hash = "";
			StringBuilder sb = new StringBuilder();
			MessageDigest md = null;
			
			try {
				md = MessageDigest.getInstance("SHA-256");
			} 
			catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		
			for(int j = 0; j < 200_000; j++){
				md.update(hash.concat(_pass).concat(_salt).getBytes());
				byte[] byteData = md.digest();
				
				for(int i = 0; i < byteData.length; i++){
					sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
				}
				hash = sb.toString();
				sb.setLength(0);
			}

			return hash;
	}
		

		public User findUserInDb(String email) {
			Query query = em.createQuery("SELECT m FROM User m WHERE m.email = :email").setParameter("email", email);
			return (User)query.getSingleResult();
		}
	
	

}
