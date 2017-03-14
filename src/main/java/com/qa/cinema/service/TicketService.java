package com.qa.cinema.service;

/**
 * 
 * @author Omar
 * @author Phil
 *
 */

public interface TicketService {
	String getUserTickets(String email);
	
	String createTicket(String ticket);
	
	String updateTicket(Long ticketId, String newTicket);
	
	String deleteTicket(Long ticketId);
	
	String getAvailableTickets(Long showingId);
	
	String getBookedSeatsByShowing(Long showingId);

	String getTicketsByOrderId(String orderId);
}
