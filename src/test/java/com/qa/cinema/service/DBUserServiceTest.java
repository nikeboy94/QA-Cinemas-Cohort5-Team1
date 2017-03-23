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
import com.qa.cinema.util.user.PersistUser;

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

	@Before
	public void setUp() {
		user = new User("email", "fname", "lname", "role", "password", "salt");
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
	public void testCreateNewUser() {
		resetUsers();
		User newUser = new User("email2", "fname2", "lname2", "role2", "password2", "salt2");
		String json = "newUser json";
		
		when(util.getObjectForJSON(json, User.class)).thenReturn(newUser);
		doAnswer(new PersistUser(users)).when(em).persist(newUser);
		
		service.createNewUser(json);
		
		assertTrue(users.contains(newUser));
	}
	
	public void resetUsers() {
		users.clear();
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
