package com.qa.cinema.persistence;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class MovieTest {
	Movie movie;
	Date date;
	
	@Before public void setUp() throws Exception{
		movie = new Movie();
		date = new Date(2017 ,01, 01);
		
	}

	@Test
	public void testGetTitle() {
		assertNull(movie.getTitle());
	}

	@Test
	public void testSetTitle() {
		movie.setTitle("title");
		assertEquals("title", movie.getTitle());
	}

	@Test
	public void testGetGenre() {
		assertNull(movie.getGenre());
	}

	@Test
	public void testSetGenre() {
		movie.setGenre("a genre");
		assertEquals("a genre", movie.getGenre());
	}

	@Test
	public void testGetReleaseDate() {
		assertNull(movie.getReleaseDate());
	}

	@Test
	public void testSetReleaseDate() {
		movie.setReleaseDate(date);
		assertEquals(date, movie.getReleaseDate());
		
	}

	@Test
	public void testGetClassification() {
		assertNull(movie.getClassification());
	}

	@Test
	public void testSetClassification() {
		movie.setClassification("U");
		assertEquals("U", movie.getClassification());
	}

	@Test
	public void testGetPosterUrl() {
		assertNull(movie.getPosterUrl());
	}

	@Test
	public void testSetPosterUrl() {
		movie.setPosterUrl("posterUrl");
		assertEquals("posterUrl", movie.getPosterUrl());
	}

	@Test
	public void testGetTrailerUrl() {
		assertNull(movie.getTrailerUrl());
	}

	@Test
	public void testSetTrailerUrl() {
		movie.setTrailerUrl("trailerUrl");
		assertEquals("trailerUrl", movie.getTrailerUrl());
	}
/*
	@SuppressWarnings("deprecation")
	@Test
	public void testGetRating() {
		assertEquals(0.0, movie.getRating());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testSetRating() {
		movie.setRating(5.0);
		assertEquals(5.0, movie.getRating());
	}

	@Test
	public void testGetRuntime() {
		assertEquals(0, movie.getRuntime());
	}

*/
	@Test
	public void testSetRuntime() {
		movie.setRuntime(1);
		assertEquals(1, movie.getRuntime());
	}

	@Test
	public void testGetDescription() {
		assertNull(movie.getDescription());
	}

	@Test
	public void testSetDescription() {
		movie.setDescription("Description");
		assertEquals("Description", movie.getDescription());
	}

	@Test
	public void testGetMovieId() {
		assertNull(movie.getMovieId());
	}
/*
	@Test
	public void testGetCount() {
		assertNull(movie.getCount());
	}
*/
	@Test
	public void testSetCount() {
		Double d = 5.0;
		movie.setCount(d);
		assertEquals(d, movie.getCount());
	}
}
