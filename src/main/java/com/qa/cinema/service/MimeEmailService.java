package com.qa.cinema.service;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.qa.cinema.persistence.Ticket;
import com.qa.cinema.util.JSONUtil;

@Stateless
@Default
public class MimeEmailService implements EmailService{
	
	static final Logger LOGGER = Logger.getLogger(DBTicketService.class);
	
	@Inject
	private TicketService ticketService;
	
	JSONUtil util = new JSONUtil();
	
	public String sendOrderConfirmation(Ticket ticket) {
		try {
			LOGGER.info("Entered try block. About to setup initial context");
			InitialContext ic = new InitialContext();
			Session session = (Session) ic.lookup("java:jboss/mail/Gmail");
			
			LOGGER.info("About to setup msg");
			Message msg = new MimeMessage(session);
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(ticket.getUser().getEmail()));
			msg.setSubject("QA Cinemas Order Confirmation: " + ticket.getOrderId());
			msg.setText(createOrderMessage(ticket));
		
			Transport.send(msg);
			LOGGER.info("Message sent without error. About to exit method");
			return "{\"message\": \"Email succesfully sent\"}";
		} catch(MessagingException | NamingException e) {
			LOGGER.info("Exception caught:" + e);
			return "{\"message\": \"Email could not be sent\"}";
		}
	}
	
	private String createOrderMessage(Ticket ticket ) {
		String ticketsInOrderJSON = ticketService.getTicketsByOrderId(ticket.getOrderId());
		Ticket[] ticketsInOrder = util.getObjectForJSON(ticketsInOrderJSON, Ticket[].class);
		
		double totalPrice = 0.0;
		int numTickets = ticketsInOrder.length;
		for(Ticket eachTicket : ticketsInOrder) {
			totalPrice += eachTicket.getPrice();
		}
		
		String msg = "Hello " + ticket.getUser().getFName().substring(0,1).toUpperCase() + ticket.getUser().getFName().substring(1) + ",";
		msg += "\n\n";
		msg += "You have booked " + numTickets + " ticket";
		if(numTickets > 1) {
			msg+= "s";
		}
		
		msg += " to see " + ticket.getShowing().getMovie().getTitle() + " on " + ticket.getShowing().getDateTime() + ". \n";
		msg += "£" + totalPrice + " has been taken from your account. \n";
		msg += "You can cancel or change your ticket by visiting your account at least 2 days before the showing. Your order ID is " + ticket.getOrderId() + ". \n\n";
		msg += "Have a great day, \n \n";
		msg += "QA Cinemas";
		return msg;
	}
	
}
