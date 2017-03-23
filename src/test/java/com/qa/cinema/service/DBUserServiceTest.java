package com.qa.cinema.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doAnswer;
import com.qa.cinema.persistence.User;
import com.qa.cinema.util.JSONUtil;
import com.qa.cinema.util.user.MergeUser;
import com.qa.cinema.util.user.PersistUser;
import com.qa.cinema.util.user.RemoveUser;

import static com.qa.cinema.util.MockJSONUtil.*;

@RunWith(MockitoJUnitRunner.class)
public class DBUserServiceTest {

	@Mock
	JSONUtil util;
	
	@Mock
	EntityManager em;
	
	@InjectMocks
	DBUserService service = new DBUserService();

	private User user;
	private Collection<User> users;
	private Query query;
	private String email = "email";

	@Before
	public void setUp() {
		users = new ArrayList<User>();
		query = mock(Query.class);
	}
	
	@Test
	public void testListAllUsers() {
		resetUsers();
		when(em.createQuery("SELECT m FROM User m")).thenReturn(query);
		when(query.getResultList()).thenReturn((List<User>) users);
		when(util.getJSONForObject(users)).thenReturn(JSONForUser((List<User>) users));
		
		assertEquals(JSONForUser((List<User>) users), service.listAllUsers());
	}
	
	@Test
	public void testFindUser() {
		resetUsers();
		List<User> list = new ArrayList<User>();
		list.add(user);
		
		when(em.createQuery("SELECT m FROM User m WHERE m.email = :email")).thenReturn(query);
		when(query.setParameter("email", email)).thenReturn(query);
		when(query.getSingleResult()).thenReturn(findUser(email));
		when(util.getRedactedJSONForObject(user)).thenReturn(JSONForUser(list));
		
		assertEquals(JSONForUser((List<User>) users), service.findUser(email));
	}
	
	@Test
	public void testCreateNewUser() {
		resetUsers();
		User newUser = new User("email2", "fname2", "lname2", "role2", "password2", "salt2");
		String json = "newUser json";
		
		when(util.getObjectForJSON(json, User.class)).thenReturn(newUser);
		doAnswer(new PersistUser(users)).when(em).persist(newUser);
		
		service.createNewUser(json);
		
		assertTrue(users.contains(newUser));
	}
	
	@Test
	public void testUpdateUser() {
		resetUsers();
		User updatedUser = new User();
		String firstName = "name";
		updatedUser.setFName(firstName);
		updatedUser.setEmail(email);
		String json = "updatedUser json";
		
		when(util.getObjectForJSON(json, User.class)).thenReturn(updatedUser);
		when(em.createQuery("SELECT m FROM User m WHERE m.email = :email")).thenReturn(query);
		when(query.setParameter("email", email)).thenReturn(query);
		when(query.getSingleResult()).thenReturn(findUser(email));
		doAnswer(new MergeUser(users)).when(em).merge(updatedUser);
		
		service.updateUser(email, json);
		
		assertTrue(findUser(email).getFName().equals(firstName));
	}
	
	@Test
	public void testDeleteUser() {
		resetUsers();
		
		when(em.createQuery("SELECT m FROM User m WHERE m.email = :email")).thenReturn(query);
		when(query.setParameter("email", email)).thenReturn(query);
		User userInDB = findUser(email);
		when(query.getSingleResult()).thenReturn(userInDB);
		doAnswer(new RemoveUser(users)).when(em).remove(userInDB);
		
		service.deleteUser(email);
		
		assertEquals(0, users.size());
	}
	
	public void resetUsers() {
		users.clear();
		user = new User(email, "fname", "lname", "role", "password", "salt");
		users.add(user);
	}
	
	public User findUser(String email) {
		for (User user : users) {
			if (user.getEmail().equals(email)) {
				return user;
			}
		}
		return null;
	}

}
