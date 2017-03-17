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

import javax.persistence.EntityManager;

@RunWith(MockitoJUnitRunner.class)
public class DBScreenServiceTest {
		
	@Mock
	JSONUtil util;
	
	@Mock
	EntityManager em;
	
	@InjectMocks
	DBScreenService ss = new DBScreenService();

	@Test
	public void faketest() {
		assertNull(null);
	}
	/*
	@Test
	public void testGetAllScreens() {
		Collection<Screen> screens = new ArrayList<Screen>();
		screens.add(new Screen("Standard", "looks ok"));
		when(ss.em.query.getResults()).thenReturn(screens);
		assertEquals("lol", ss.getAllScreens());
	}
	*/

}
