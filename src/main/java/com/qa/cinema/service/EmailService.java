package com.qa.cinema.service;

import com.qa.cinema.persistence.Ticket;

public interface EmailService {
	String sendOrderConfirmation(String orderId); 
}
