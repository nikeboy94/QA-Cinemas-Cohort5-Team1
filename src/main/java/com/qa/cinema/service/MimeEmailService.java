package com.qa.cinema.service;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
	
	@Override
	public String sendGuestOrderConfirmation(String orderId, String email) {
		String ticketsInOrderJSON = ticketService.getTicketsByOrderId(orderId);
		Ticket[] ticketsInOrder = (Ticket[]) util.getObjectForJSON(ticketsInOrderJSON, Ticket[].class);
		
		if (ticketsInOrder == null || ticketsInOrder.length == 0) {
			return "{\"message\": \" No tickets with this order ID was found\"}"; 
		}
		
		Ticket firstTicket = ticketsInOrder[0];
		
		try {
			InitialContext ic = new InitialContext();
			Session session = (Session) ic.lookup("java:jboss/mail/Gmail");
			
			Message msg = new MimeMessage(session);
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			msg.setSubject("QA Cinemas Order Confirmation: " + orderId);
			msg.setText(createOrderMessage(ticketsInOrder));
		
			Transport.send(msg);
			return "{\"message\": \"Email succesfully sent\"}";
		} catch(MessagingException | NamingException e) {
			LOGGER.info("Exception caught:" + e);
			return "{\"message\": \"Email could not be sent\"}";
		}
	}
	
	@Override
	public String sendOrderConfirmation(String orderId) {
		
		String ticketsInOrderJSON = ticketService.getTicketsByOrderId(orderId);
		Ticket[] ticketsInOrder = (Ticket[]) util.getObjectForJSON(ticketsInOrderJSON, Ticket[].class);
		
		if (ticketsInOrder == null || ticketsInOrder.length == 0) {
			return "{\"message\": \" No tickets with this order ID was found\"}"; 
		}
		
		Ticket firstTicket = ticketsInOrder[0];
		
		
		try {
			InitialContext ic = new InitialContext();
			Session session = (Session) ic.lookup("java:jboss/mail/Gmail");
			
			Message msg = new MimeMessage(session);
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(firstTicket.getUser().getEmail()));
			msg.setSubject("QA Cinemas Order Confirmation: " + orderId);
			msg.setText(createOrderMessage(ticketsInOrder));
		
			Transport.send(msg);
			return "{\"message\": \"Email succesfully sent\"}";
		} catch(MessagingException | NamingException e) {
			LOGGER.info("Exception caught:" + e);
			return "{\"message\": \"Email could not be sent\"}";
		}
	}
	
	
	private String createOrderMessage(Ticket[] ticketsInOrder ) {

		double totalPrice = 0.0;
		int numTickets = ticketsInOrder.length;
		for(Ticket eachTicket : ticketsInOrder) {
			totalPrice += eachTicket.getPrice();
		}
		
		Ticket firstTicket = ticketsInOrder[0];
		
		String msg = "";
		if(firstTicket.getUser().getEmail().equals("guestAccount")) {
			msg += "Hello,";
		} else {
			msg += "Hello " + firstTicket.getUser().getFName().substring(0,1).toUpperCase() + firstTicket.getUser().getFName().substring(1) + ",";
		}
		
		
		msg += "\n\n";
		msg += "You have booked " + numTickets + " ticket";
		if(numTickets > 1) {
			msg+= "s";
		}
		
		SimpleDateFormat parseFormat = new SimpleDateFormat("y-MM-dd'T'HH:mm");
		SimpleDateFormat printFormat = new SimpleDateFormat("EEEE F MMMM 'at' h:mm a");
		String msgDate = null;
		
		try {
			Date showingDate = (Date) parseFormat.parse(firstTicket.getShowing().getDateTime());
			msgDate = printFormat.format(showingDate);
		} catch (ParseException e) {
			LOGGER.info(e);
			msgDate = firstTicket.getShowing().getDateTime();
		}

		String printPrice = String.format("%.2f", totalPrice);
		
		msg += " to see " + firstTicket.getShowing().getMovie().getTitle() + " on " + msgDate + ". \n";
		msg += "£" + printPrice + " has been taken from your account. \n";
		
		if(! firstTicket.getUser().getEmail().equals("guestAccount")) {
			msg += "You can cancel your ticket by visiting your account at least 24 hours before the showing. Your order ID is " + firstTicket.getOrderId() + ". \n\n";
		}
		
		msg += "Have a great day, \n \n";
		msg += "QA Cinemas";
		return msg;
	}


	
}
