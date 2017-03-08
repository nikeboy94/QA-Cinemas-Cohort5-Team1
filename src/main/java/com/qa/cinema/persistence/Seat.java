package com.qa.cinema.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Seat {
	@Id
	private Long id;

	public Long getId() {
		return id;
	}

	
	
}
