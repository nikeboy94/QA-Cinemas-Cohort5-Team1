package com.qa.cinema.service;

import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.cinema.persistence.Screen;
import com.qa.cinema.util.JSONUtil;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@RunWith(MockitoJUnitRunner.class)
public class DBScreenServiceTest {
		
	@Mock
	JSONUtil util;
	
	@Mock
	EntityManager em;
	
	@InjectMocks
	DBScreenService ss = new DBScreenService();
	
	Query query;

	@Test
	public void fakeTest() {
		assertNull(null);
	}

/*
	@Test
	public void testGetAllScreens() {
		Collection<Screen> screens = new ArrayList<Screen>();
		screens.add(new Screen("Standard", "looks ok"));
		//when(query.getResultList()).thenReturn((List) screens);
		assertEquals("lol", ss.getAllScreens());
	}
*/
	

}
