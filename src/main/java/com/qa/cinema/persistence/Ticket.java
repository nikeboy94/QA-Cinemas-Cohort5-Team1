package com.qa.cinema.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 
 * @author Phil
 * @author Omar
 *
 */

@Entity
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ticketId;
	private String price;
	private String orderId;
	@ManyToOne
	@JoinColumn(name="seatId", nullable=false)
	private Seat seat;
	@ManyToOne
	@JoinColumn(name="showingId", nullable=false)
	private Showing showing;
	@ManyToOne
	@JoinColumn(name="email", nullable=false)
	private User user;
	
	public Ticket() {
		//Empty constructor
	}
		

	public Ticket(String price, String orderId, Seat seat, Showing showing, User user) {
		super();
		this.price = price;
		this.orderId = orderId;
		this.seat = seat;
		this.showing = showing;
		this.user = user;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public Showing getShowing() {
		return showing;
	}

	public void setShowing(Showing showing) {
		this.showing = showing;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void updateField(Ticket updatedTicket) {
		this.orderId = updatedTicket.getOrderId();
		this.price = updatedTicket.getPrice();
		this.seat = updatedTicket.getSeat();
		this.showing = updatedTicket.getShowing();
	}
	

}
