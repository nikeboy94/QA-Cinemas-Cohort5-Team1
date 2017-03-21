package com.qa.cinema.service;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doAnswer;
import com.qa.cinema.persistence.Movie;
import com.qa.cinema.persistence.Screen;
import com.qa.cinema.persistence.Showing;
import com.qa.cinema.util.JSONUtil;
import com.qa.cinema.util.answer.MergeShowing;
import com.qa.cinema.util.answer.PersistShowing;
import com.qa.cinema.util.answer.RemoveShowing;
import static com.qa.cinema.util.MockJSONUtil.*;

@RunWith(MockitoJUnitRunner.class)
public class DBShowingServiceTest {

	@Mock
	JSONUtil util;
	
	@Mock
	EntityManager manager;
	
	@InjectMocks
	DBShowingService service = new DBShowingService();
	
	Showing showing;
	Movie movie;
	Screen screen;
	String dateTime = "2017-03-21T12:00:00";
	Collection<Showing> showings;
	Query mockedQuery;
	
	@Before
	public void setup() {
		movie = new Movie("title", "genre", new Date(), "classification", "poster", "trailer", 100);
		screen = new Screen("standard", "standard");
		showing = new Showing(screen, movie, dateTime);
		showings = new ArrayList<Showing>();
		showings.add(showing);
		mockedQuery = mock(Query.class);
	}
	
	@Test
	public void testGetAllShowings() {
		when(manager.createQuery("SELECT s FROM Showing s")).thenReturn(mockedQuery);
		when(mockedQuery.getResultList()).thenReturn((List<Showing>) showings);
		when(util.getJSONForObject(showings)).thenReturn(JSONForShowing((List<Showing>) showings));
		
		assertEquals(JSONForShowing((List<Showing>) showings), service.getAllShowings());
	}
	
	@Test
	public void testFindShowingByScreenId() {
		Long screenId = null;
		when(manager.createQuery("SELECT s FROM Showing s WHERE s.screen.screenId = " + screenId))
			.thenReturn(mockedQuery);
		when(mockedQuery.getResultList()).thenReturn(findByScreenId((List<Showing>) showings, screenId));
		when(util.getJSONForObject(showings)).thenReturn(JSONForShowing((List<Showing>) showings));
		
		assertEquals(JSONForShowing((List<Showing>) showings), service.findShowingByScreenId(screenId));
	}
	
	@Test
	public void testFindShowingByMovieId() {
		Long movieId = null;
		when(manager.createQuery("SELECT s FROM Showing s WHERE s.movie.movieId = " + movieId))
			.thenReturn(mockedQuery);
		when(mockedQuery.getResultList()).thenReturn(findByScreenId((List<Showing>) showings, movieId));
		when(util.getJSONForObject(showings)).thenReturn(JSONForShowing((List<Showing>) showings));
		
		assertEquals(JSONForShowing((List<Showing>) showings), service.findShowingByMovieId(movieId));
	}
	
	@Test
	public void testCreateShowing() {
		Showing aShowing = new Showing();
		when(util.getObjectForJSON(new String(), Showing.class)).thenReturn(aShowing);
		doAnswer(new PersistShowing(showings)).when(manager).persist(aShowing);
		manager.persist(aShowing);
		
		assertEquals(aShowing, ((List<Showing>) showings).get(1));
	}
	
	@Test
	public void testUpdateShowing() {
		Long id = null;
		Showing updatedShowing = new Showing();
		when(util.getObjectForJSON("showingJson", Showing.class)).thenReturn(updatedShowing);
		when(manager.find(Showing.class, id)).thenReturn(findShowing(id));
		doAnswer(new MergeShowing(showings)).when(manager).merge(updatedShowing);
		manager.merge(updatedShowing);
		
		assertEquals(updatedShowing, ((List<Showing>) showings).get(0));
	}
	
	@Test
	public void testDeleteShowing() {
		showings.clear();
		showings.add(showing);
		Long id = null;
		
		when(findShowing(id)).thenReturn(findShowing(id));
		doAnswer(new RemoveShowing(showings)).when(manager).remove(showing);
		manager.remove(showing);
		
		assertEquals(0, showings.size());
	}
	
	public List<Showing> findByScreenId(List<Showing> showings, Long screenId) {
		List<Showing> results = new ArrayList<Showing>();
		for (Showing showing : showings) {
			if (showing.getScreen().getId() == screenId) {
				results.add(showing);
			}
		}
		return results;
	}
	
	public Showing findShowing(Long id) {
		for (Showing showing : showings) {
			if (showing.getShowingId() == id) {
				return showing;
			}
		}
		return null;
	}

}
