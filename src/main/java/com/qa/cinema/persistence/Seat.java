package com.qa.cinema.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Seat {
	@Id
	private Long seatId;

	private Long screenId;
	
	public Long getSeatId() {
		return seatId;
	}
	
	public Long getScreenId() {
		return screenId;
	}


	
	
}
