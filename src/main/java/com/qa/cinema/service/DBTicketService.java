package com.qa.cinema.service;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.qa.cinema.persistence.Ticket;
import com.qa.cinema.enums.TicketType;
import com.qa.cinema.persistence.Seat;
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
		Ticket updatedTicket = util.getObjectForJSON(newTicket, Ticket.class);
		
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
		Collection<Ticket> availableTicketList = (Collection<Ticket>)query.getResultList();
		
		int bookedTickets = availableTicketList.size();
		
		Showing s = manager.find(Showing.class, showingId);
		Long screenId = s.getScreen().getId();
		query = manager.createQuery("Select s From Seat s Where screenId = :screenId")
		.setParameter("screenId", screenId);
		Collection<Seat> numberOfSeatsInScreen = (Collection<Seat>)query.getResultList();
		
		int seatsInScreen = numberOfSeatsInScreen.size();
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
	
	@Override
	public String createMultipleTicket(String ticket) {
		Ticket[] aTicket = util.getObjectForJSON(ticket, Ticket[].class);
		for(Ticket t:aTicket){
			manager.persist(t);
		}
		return "{\"message\": \"tickets successfully added\"}";
	}

	@Override
	public String getBookedSeatsByShowing(Long showingId) {
		Query query = manager.createQuery("Select t From Ticket t Where t.showing.showingId = :showingId").setParameter("showingId", showingId);
		Collection<Ticket> bookedTicketList = (Collection<Ticket>)query.getResultList();
		
		Collection<Seat> bookedSeats = new ArrayList<>();
		
		for(Ticket aTicket : bookedTicketList) {
			bookedSeats.add(aTicket.getSeat());
		}
		
		return util.getJSONForObject(bookedSeats);
	}

	@Override
	public String getTicketsByOrderId(String orderId) {
		Query query = manager.createQuery("SELECT t FROM Ticket t WHERE t.orderId = :orderId").setParameter("orderId", orderId);
		Collection<Ticket> ticketsInOrder = (Collection<Ticket>) query.getResultList();
		return util.getJSONForObject(ticketsInOrder);
	}
	

	@Override
	public String getTicketPrice(Long showingId, String stringTicketType) {
		
		TicketType ticketType = null;
		try {
			ticketType = TicketType.valueOf(stringTicketType.toUpperCase());
		} catch (IllegalArgumentException e) {
			LOGGER.info("In getTicketPrice. IllegalArgumentException: " + e);
			return "{\"message\": \"No such ticket type " + stringTicketType + "\"}";
		}
		
		Showing showing = manager.find(Showing.class, showingId);
		double price = 9.0;
		
		/*
		DateFormat formatter;
		Date showingDate;
		formatter = new SimpleDateFormat("YYY-MM-DD HH:MM:SS");
		
		StringBuilder strShowingDate = new StringBuilder(showing.getDateTime());
	
		int tIndex = strShowingDate.indexOf("T");
		strShowingDate.replace(tIndex, tIndex+1, " ");
		
		strShowingDate.deleteCharAt(strShowingDate.indexOf("Z"));
		
		try {
			showingDate = (Date) formatter.parse(strShowingDate.toString());
		} catch (ParseException e) {
			LOGGER.info(e);
			return "{\"message\": \"Could not get price\"}";
		}

		if(showingDate.getDay() == 0 || showingDate.getDay() == 1) {
			price *= 1.3;
		}
		
		if(showingDate.getHours() > 19) {
			price *= 1.3;
		}
		*/
		if(ticketType == TicketType.CHILD) {
			LOGGER.info("In getTicketPrice: Child ticket");
			price *= 0.7;
		}
				
		return "{\"price\": \" " + price + "\"}";
	}	

}
