package com.qa.cinema.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.cinema.service.TicketService;

/**
 * 
 * @author Phil
 * @author Omar
 * 
 */

@Path("/ticket")
public class TicketEndpoint {

	@Inject
	private TicketService service;
	
	@Path("/json/{email}")
	@GET
	@Produces({"application/json"})
	public String getUserTickets(@PathParam("email") String email) {
		return service.getUserTickets(email);
	}
	
	@Path("/json")
	@POST
	@Produces({"application/json"})
	public String createTicket(String ticket) {
		return service.createTicket(ticket);
	}
	
	@Path("/json/{id}")
	@PUT
	@Produces({"application/json"})
	public String updateTicket(@PathParam("id") Long ticketId, String newTicket) {
		return service.updateTicket(ticketId, newTicket);
	}
	
	@Path("/json/{id}")
	@DELETE
	@Produces({"application/json"})
	public String deleteTicket(@PathParam("id") Long ticketId) {
		return service.deleteTicket(ticketId);
	}
	
	@Path("/json/tickets/{showingID}")
	@GET
	@Produces({"application/json"})
	public String getAvailableTickets(@PathParam("showingID") Long showingId) {
		return service.getAvailableTickets(showingId);
	}
	
	@Path("/json/seats/{showingID}")
	@GET
	@Produces({"application/json"})
	public String getBookedSeatsForShowing(@PathParam("showingID") Long showingId) {
		return service.getBookedSeatsByShowing(showingId);
	}
	
	@Path("/json/order/{orderId}")
	@GET
	@Produces({"application/json"})
	public String getTicketsInOrder(@PathParam("orderId") String orderId) {
		return service.getTicketsByOrderId(orderId);
	}
	
	
	
}
