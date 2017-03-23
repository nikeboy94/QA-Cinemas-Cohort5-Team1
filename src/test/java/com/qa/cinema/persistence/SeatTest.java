package com.qa.cinema.persistence;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SeatTest {

	private Seat seat;
	private String row = "row";
	private String col = "col";
	private Screen screen;
	
	@Before
	public void setup() {
		seat = new Seat(row, col);
		screen = new Screen();
	}
	@Test
	public void testGetSeatId() {
		assertNull(seat.getSeatId());
	}
	
	@Test
	public void testSetSeatId() {
		String id = "seat id";
		seat.setSeatId(id);
		assertEquals(id, seat.getSeatId());
	}
	
	@Test
	public void testGetRow() {
		assertEquals(row, seat.getRow());
	}
	
	@Test
	public void testSetRow() {
		String newRow = "new row";
		seat.setRow(newRow);
		assertEquals(newRow, seat.getRow());
	}
	
	@Test
	public void testGetColumn() {
		assertEquals(col, seat.getColumn());
	}
	
	@Test
	public void testSetColumn() {
		String newCol = "new col";
		seat.setColumn(newCol);
		assertEquals(newCol, seat.getColumn());
	}
	
	@Test
	public void testGetScreen() {
		assertNull(seat.getScreen());
	}
	
	@Test
	public void testSetScreen() {
		seat.setScreen(screen);
		assertEquals(screen, seat.getScreen());
	}

}
