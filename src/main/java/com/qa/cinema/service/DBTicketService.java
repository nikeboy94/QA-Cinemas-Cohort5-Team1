package com.qa.cinema.service;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.qa.cinema.persistence.Ticket;
import com.qa.cinema.persistence.Showing;
import com.qa.cinema.util.JSONUtil;

/**
 * 
 * @author Omar
 * @author Phil
 *
 */

/*
 * TODO: When Seat class is updated, the query in getAvailableTickets will also need updating
 */

@Stateless
@Default
public class DBTicketService implements TicketService {

	static final Logger LOGGER = Logger.getLogger(DBTicketService.class);
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil util;
	
	@Inject
	private ShowingService showingService;
	
	@Override
	public String getUserTickets(String email) {
		Query query = manager.createQuery("Select t From Ticket t Where t.user.email = :email")
		.setParameter("email", email);		
		Collection<Ticket> tickets = (Collection<Ticket>) query.getResultList();
		return util.getJSONForObject(tickets);
	}

	@Override
	public String createTicket(String ticket) {
		Ticket aTicket = util.getObjectForJSON(ticket, Ticket.class);
		manager.persist(aTicket);
		return "{\"message\": \"ticket successfully added\"}";
	}

	@Override
	public String updateTicket(Long ticketId, String newTicket) {
		LOGGER.info("DBTICKETSERVICE: Entered updateTicket method. About to get updatedTicket object");
		Ticket updatedTicket = util.getObjectForJSON(newTicket, Ticket.class);
		
		LOGGER.info("DBTICKETSERVICE: About to get updatedShowing object");
		Showing updatedShowing = getShowing(updatedTicket.getShowing().getShowingId());
		LOGGER.info("DBTICKETSERVICE: updatedShowing created");
		
		if(updatedShowing == null) {
			return "{\"message\": \"Showing not found\"}";
		}
	
		LOGGER.info("DBTICKETSERVICE: About to call updatedTicket.setShowing");
		updatedTicket.setShowing(updatedShowing);
		
		Ticket ticketInDB = findTicket(ticketId);
		if (ticketInDB != null){
			ticketInDB.updateField(updatedTicket);
			manager.merge(ticketInDB);
			return "{\"message\": \"ticket successfully updated\"}";
		}
		return "{\"message\": \"ticket not found\"}";
	}

	@Override
	public String deleteTicket(Long ticketId) {
		Ticket ticketInDB = findTicket(ticketId);
		if(ticketInDB != null)	{
			manager.remove(ticketInDB);
			return "{\"message\": \"ticket successfully deleted\"}";
		}
		return "{\"message\": \"ticket not found\"}";
	}

	@Override
	public String getAvailableTickets(Long showingId) {
		Query query = manager.createQuery("Select t From Ticket t Where t.showing.showingId = :showingId").setParameter("showingId", showingId);
		int bookedTickets = query.getFirstResult();
		
		Showing s = manager.find(Showing.class, showingId);
		Long screenId = s.getScreen();
		query = manager.createQuery("Select Count(s) From Seat s Where screenId = :screenId")
		.setParameter("screenId", screenId);
		
		int seatsInScreen = query.getFirstResult();
		int availableTickets = seatsInScreen - bookedTickets;
		
		return "{\"availableTickets\": \"" +availableTickets +"\"}";
	}
	
	private Ticket findTicket(Long ticketId) {
		return manager.find(Ticket.class, ticketId);
	}
	
	private Showing getShowing(Long showingId) {
		LOGGER.info("DBTICKETSERVICE entered getShowing with param " + showingId);
		LOGGER.info("DBTICKETSERVICE - getShowing. About to create string from showing service");
		String allShowingsJSON = showingService.getAllShowings();
		
		LOGGER.info("DBTICKETSERVICE - getShowing. About to make a collection of allShowings");
		Showing[]allShowings = (Showing[]) util.getObjectForJSON(allShowingsJSON, Showing[].class);
		
		for(Showing aShowing : allShowings) {
			if(aShowing.getShowingId().equals(showingId)) {
				LOGGER.info("DBTICKETSERVICE - getShowing. Correct showing found, about to return aShowing");
				return aShowing;
			}
		}
		LOGGER.info("DBTICKETSERVICE - getShowing. Loop finished, about to return null");
		return null;
	}

}
