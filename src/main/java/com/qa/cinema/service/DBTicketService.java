package com.qa.cinema.service;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.qa.cinema.persistence.Ticket;
import com.qa.cinema.persistence.Showing;
import com.qa.cinema.util.JSONUtil;

@Stateless
@Default
public class DBTicketService implements TicketService {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil util;
	
	
	@Override
	public String getUserTickets(String email) {
		Query query = manager.createQuery("Select t From Ticket t Where t.email = :email")
		.setParameter("email", email);		
		Collection<Ticket> tickets = (Collection<Ticket>) query.getResultList();
		return util.getJSONForObject(tickets);
	}

	@Override
	public String createTicket(String ticket) {
		Ticket aTicket = util.getObjectForJSON(ticket, Ticket.class);
		manager.persist(aTicket);
		return "{\"messege\": \"ticket successfully added\"}";
	}

	@Override
	public String updateTicket(Long ticketId, String newTicket) {
		Ticket updatedTicket = util.getObjectForJSON(newTicket, Ticket.class);
		Ticket ticketInDB = findTicket(ticketId);
		if (ticketInDB != null){
			ticketInDB = updatedTicket;
			manager.merge(ticketInDB);
			return "{\"messege\": \"ticket successfully upadted\"}";
		}
		return "{\"messege\": \"ticket not found\"}";
	}

	@Override
	public String deleteTicket(Long ticketId) {
		Ticket ticketInDB = findTicket(ticketId);
		if(ticketInDB != null)	{
			manager.remove(ticketInDB);
			return "{\"messege\": \"ticket successfully deleted\"}";
		}
		return "{\"messege\": \"ticket not found\"}";
	}

	@Override
	public String getAvailableTickets(Long showingId) {
		Query query = manager.createQuery("Select Count(t) From Ticket t Where showingId = :showingId")
		.setParameter("showingId", showingId);
		int bookedTickets = query.getFirstResult();
		Showing s = manager.find(Showing.class, showingId);
		Long screenId = s.getScreenId();
		query = manager.createQuery("Select Count(s) From Seat s Where showingId = :showingId")
		.setParameter("showingId", showingId);
		int seatsInScreen = query.getFirstResult();
		int availableTickets = seatsInScreen - bookedTickets;
		return "{\"availableTickets\": \"" +availableTickets +"\"}";
	}
	
	public Ticket findTicket(Long ticketId){
		return manager.find(Ticket.class, ticketId);
	}

}
