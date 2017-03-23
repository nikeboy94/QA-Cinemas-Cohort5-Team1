package com.qa.cinema.service;

import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.cinema.persistence.Screen;
import com.qa.cinema.util.JSONUtil;
import com.qa.cinema.util.MockJSONUtil;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@RunWith(MockitoJUnitRunner.class)
public class DBScreenServiceTest {
		
	@Mock
	JSONUtil util;
	
	@Mock
	EntityManager em;
	
	@InjectMocks
	DBScreenService ss = new DBScreenService();
	
	
	@Test
	public void fakeTest() {
		assertNull(null);
	}
	
	
	@Test
	public void testGetAllScreens() {
		Collection<Screen> screens = new ArrayList<Screen>();
		screens.add(new Screen("Standard", "looks ok"));
		Query mockedQuery = mock(Query.class);
		when(em.createQuery("SELECT s FROM Screen s")).thenReturn(mockedQuery);
		when(mockedQuery.getResultList()).thenReturn((List<Screen>) screens);
		when(util.getJSONForObject(screens)).thenReturn(MockJSONUtil.JSONForScreen((List<Screen>) screens));
		assertEquals("{\"screenId\": \"null\", \"screenType\": \"Standard\", \"screenDesc\": \"looks ok\"}" , ss.getAllScreens());
	}

	

}
