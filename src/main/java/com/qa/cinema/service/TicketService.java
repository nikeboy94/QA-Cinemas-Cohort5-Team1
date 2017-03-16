package com.qa.cinema.service;

import com.qa.cinema.enums.TicketType;

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
	

	String createMultipleTicket(String ticket);

	String getBookedSeatsByShowing(Long showingId);


	String getTicketsByOrderId(String orderId);

	String getTicketPrice(Long showingId, String ticketType);
}
