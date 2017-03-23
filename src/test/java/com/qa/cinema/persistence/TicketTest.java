package com.qa.cinema.persistence;

import static org.junit.Assert.*;

import org.junit.Test;

import com.qa.cinema.enums.TicketType;

public class TicketTest {

	private Double price = 1.0;
	private String orderId = "orderId";
	private Seat seat = new Seat();
	private Showing showing = new Showing();
	private User user = new User();
	private TicketType type = TicketType.ADULT;
	private Ticket ticket = new Ticket(price, orderId, seat, showing, user);
	
	@Test
	public void testGetPrice() {
		assertEquals(price, (Double) ticket.getPrice());
	}
	
	@Test
	public void testSetPrice() {
		Double newPrice = 2.0;
		ticket.setPrice(newPrice);
		assertEquals(newPrice, (Double) ticket.getPrice());
	}
	
	@Test
	public void testGetOrderId() {
		assertEquals(orderId, ticket.getOrderId());
	}
	
	@Test
	public void testSetOrderId() {
		String newOrderId = "new order id";
		ticket.setOrderId(newOrderId);
		assertEquals(newOrderId, ticket.getOrderId());
	}
	
	@Test
	public void testGetSeat() {
		assertEquals(seat, ticket.getSeat());
	}
	
	@Test
	public void testSetSeat() {
		Seat newSeat = new Seat();
		ticket.setSeat(newSeat);
		assertEquals(newSeat, ticket.getSeat());
	}
	
	@Test
	public void testGetShowing() {
		assertEquals(showing, ticket.getShowing());
	}
	
	@Test
	public void testSetShowing() {
		Showing newShowing = new Showing();
		ticket.setShowing(newShowing);
		assertEquals(newShowing, ticket.getShowing());
	}
	
	@Test
	public void testGetUser() {
		assertEquals(user, ticket.getUser());
	}
	
	@Test
	public void testSetUser() {
		User newUser = new User();
		ticket.setUser(newUser);
		assertEquals(newUser, ticket.getUser());
	}
	
	@Test
	public void testGetTicketId() {
		assertNull(ticket.getTicketId());
	}
	
	@Test
	public void testUpdateFieldOrderId() {
		Ticket updateTicket = new Ticket();
		String updateOrder = "order";
		updateTicket.setOrderId(updateOrder);
		ticket.updateField(updateTicket);
		
		assertEquals(updateOrder, ticket.getOrderId());
	}
	
	@Test
	public void testUpdateFieldPrice() {
		Ticket updateTicket = new Ticket();
		Double updatePrice = 0.0;
		updateTicket.setPrice(updatePrice);
		ticket.updateField(updateTicket);
		
		assertEquals(updatePrice, (Double) ticket.getPrice());
	}
	
	@Test
	public void testUpdateFieldSeat() {
		Ticket updateTicket = new Ticket();
		Seat updateSeat = new Seat();
		updateTicket.setSeat(updateSeat);
		ticket.updateField(updateTicket);
		
		assertEquals(updateSeat, ticket.getSeat());
	}
	
	@Test
	public void testUpdateFieldShowing() {
		Ticket updateTicket = new Ticket();
		Showing updateShowing = new Showing();
		updateTicket.setShowing(updateShowing);
		ticket.updateField(updateTicket);
		
		assertEquals(updateShowing, ticket.getShowing());
	}
	
	@Test
	public void testGetTicketType() {
		assertNull(ticket.getTicketType());
	}
	
	@Test
	public void testSetTicketType() {
		ticket.setTicketType(type);
		assertEquals(type, ticket.getTicketType());
	}

}
