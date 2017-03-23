package com.qa.cinema.service;

import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.cinema.persistence.Screen;
import com.qa.cinema.util.JSONUtil;
import com.qa.cinema.util.screen.PersistScreen;
import com.qa.cinema.util.screen.RemoveScreen;
import com.qa.cinema.util.MockJSONUtil;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doAnswer;

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
	
	Collection<Screen> screens;
	Screen screen;
	
	@Before
	public void setup() {
		screens = new ArrayList<Screen>();
		screen = new Screen("type", "description");
		resetScreens();
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

	@Test
	public void testCreateNewScreen() {
		resetScreens();
		String json = "newScreen json";
		Screen newScreen = new Screen();
		
		when(util.getObjectForJSON(json, Screen.class)).thenReturn(newScreen);
		doAnswer(new PersistScreen(screens)).when(em).persist(newScreen);
		
		ss.createNewScreen(json);
		
		assertTrue(screens.contains(newScreen));
	}
	
	@Test
	public void testDeleteScreen() {
		resetScreens();
		Long id = null;
		
		when(em.find(Screen.class, id)).thenReturn(findScreen(id));
		doAnswer(new RemoveScreen(screens)).when(em).remove(screen);
		
		ss.deleteScreen(id);
		
		assertEquals(0, screens.size());
	}
	
	public void resetScreens() {
		screens.clear();
		screens.add(screen);
	}
	
	public Screen findScreen(Long id) {
		for (Screen screen : screens) {
			if (screen.getId() == id) {
				return screen;
			}
		}
		return null;
	}

}
