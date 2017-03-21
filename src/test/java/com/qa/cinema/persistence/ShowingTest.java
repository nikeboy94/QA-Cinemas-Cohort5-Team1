package com.qa.cinema.persistence;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ShowingTest {

	Showing showing;
	Screen screen;
	Movie movie;
	String dateTime;
	
	@Before public void setUp() {
		movie = new Movie();
		screen = new Screen();
		dateTime = "";
		showing = new Showing(screen, movie, dateTime);
	}
	
	@Test
	public void testGetShowingId() {
		assertNull(showing.getShowingId());
	}
	
	@Test
	public void testGetScreen() {
		assertEquals(screen, showing.getScreen());
	}
	
	@Test
	public void testSetScreen() {
		Screen newScreen = new Screen();
		showing.setScreen(newScreen);
		assertEquals(newScreen, showing.getScreen());
	}
	
	@Test
	public void testGetMovie() {
		assertEquals(movie, showing.getMovie());
	}
	
	@Test
	public void testSetMovie() {
		Movie newMovie = new Movie();
		showing.setMovie(newMovie);
		assertEquals(newMovie, showing.getMovie());
	}
	
	@Test
	public void testGetDateTime() {
		assertEquals(dateTime, showing.getDateTime());
	}
	
	@Test
	public void testSetDateTime() {
		String newDateTime = "dateTime";
		showing.setDateTime(newDateTime);
		assertEquals(newDateTime, showing.getDateTime());
	}

}
