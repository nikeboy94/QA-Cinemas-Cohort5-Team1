package com.qa.cinema.persistence;

import static org.junit.Assert.*;

import org.junit.Test;

public class ScreenTest {
	
	Screen screen = new Screen("Standard", "Description");

	@Test
	public void testGetScreenType() {
		assertEquals("Standard", screen.getScreenType());
	}
	
	@Test
	public void testSetScreenType() {
		screen.setScreenType("Deluxe");
		assertEquals("Deluxe", screen.getScreenType());
	}
	
	@Test
	public void testGetScreenDesc() {
		assertEquals("Description", screen.getScreenDesc());
	}
	
	@Test
	public void testSetScreenDesc() {
		screen.setScreenDesc("Please like this screen");
		assertEquals("Please like this screen", screen.getScreenDesc());
	}
	
	@Test
	public void testGetScreenId() {
		assertNull(screen.getId());
	}

}
