package com.qa.cinema.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.engine.query.spi.EntityGraphQueryHint;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doAnswer;

import com.qa.cinema.persistence.Movie;
import com.qa.cinema.persistence.Screen;
import com.qa.cinema.persistence.Showing;
import com.qa.cinema.util.JSONUtil;
import com.qa.cinema.util.movie.MergeMovie;
import com.qa.cinema.util.movie.PersistMovie;
import com.qa.cinema.util.movie.RemoveMovie;

import static com.qa.cinema.util.MockJSONUtil.*;

@RunWith(MockitoJUnitRunner.class)
public class DBMovieServiceTest {

	@Mock
	EntityManager em;
	
	@Mock
	JSONUtil util;
	
	@InjectMocks
	DBMovieService service;
	
	Query query;
	Collection<Movie> movies;
	Collection<Showing> showings;
	Movie movie;
	Showing showing;
	
	@Before
	public void setup() {
		movies = new ArrayList<Movie>();
		movie = new Movie("title", "genre", new Date(), "classification", "poster", "trailer", 100);
		showings = new ArrayList<Showing>();
		showing = new Showing(new Screen(), new Movie(), "2017-04-22T12:00:00");
		resetShowings();
		query = mock(Query.class);
	}
	
	@Test
	public void testListAllMovies() {
		resetMovies();
		
		when(em.createQuery("SELECT m FROM Movie m")).thenReturn(query);
		when(query.getResultList()).thenReturn((List<Movie>) movies);
		when(util.getJSONForObject(movies)).thenReturn(JSONForMovie((List<Movie>) movies));
		
		assertEquals(JSONForMovie((List<Movie>) movies), service.listAllMovies());
	}
	
	@Test
	public void testListCurrentMovies() {
		resetShowings();

		List<Movie> movies = new ArrayList<Movie>();
		movies.add(showing.getMovie());
		
		when(em.createQuery("SELECT s FROM Showing s")).thenReturn(query);
		when(query.getResultList()).thenReturn((List<Showing>) showings);
		when(util.getJSONForObject(movies)).thenReturn(JSONForMovie(movies));
		
		assertEquals(JSONForMovie(movies), service.listCurrentMovies());
	}
	
	@Test
	public void testCreateNewMovie() {
		resetMovies();
		Movie newMovie = new Movie();
		String json = "newMovie json";
		
		when(util.getObjectForJSON(json, Movie.class)).thenReturn(newMovie);
		doAnswer(new PersistMovie(movies)).when(em).persist(newMovie);
		
		service.createNewMovie(json);
		
		assertEquals(2, movies.size());
	}
	
	@Test
	public void testUpdateMovie() {
		resetMovies();
		Long id = null;
		Movie updatedMovie = new Movie();
		String json = "updatedMovie json";
		
		when(util.getObjectForJSON(json, Movie.class)).thenReturn(updatedMovie);
		when(em.find(Movie.class, id)).thenReturn(findMovie(id));
		doAnswer(new MergeMovie(movies)).when(em).merge(updatedMovie);
		
		service.updateMovie(id, json);
		
		Movie updatedInDB = ((List<Movie>) movies).get(0);
		assertTrue((updatedInDB != updatedMovie) && (updatedInDB.getTitle() == null));
	}
	
	@Test
	public void testUpdateRating() {
		resetMovies();
		Long id = null;
		String json = "updatedRating json";
		Movie updatedRating = new Movie();
		updatedRating.setRating(1.0);
		
		when(util.getObjectForJSON(json, Movie.class)).thenReturn(updatedRating);
		when(em.find(Movie.class, id)).thenReturn(findMovie(id));
		doAnswer(new MergeMovie(movies)).when(em).merge(updatedRating);
		
		service.updateRating(id, json);
		
		assertEquals((Double) 1.0, (Double) ((List<Movie>) movies).get(0).getRating());
	}
	
	@Test
	public void testDeleteMovie() {
		resetMovies();
		Long id = null;
		
		when(em.find(Movie.class, id)).thenReturn(findMovie(id));
		doAnswer(new RemoveMovie(movies)).when(em).remove(movie);
		
		service.deleteMovie(id);
		
		assertEquals(0, movies.size());
	}
	
	public void resetMovies() {
		movies.clear();
		movies.add(movie);
	}
	
	public void resetShowings() {
		showings.clear();
		showings.add(showing);
	}
	
	public Movie findMovie(Long id) {
		for (Movie movie : movies) {
			if (movie.getMovieId() == id) {
				return movie;
			}
		}
		return null;
	}

}
