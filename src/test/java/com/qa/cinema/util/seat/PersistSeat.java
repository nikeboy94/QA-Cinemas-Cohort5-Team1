package com.qa.cinema.util.seat;

import java.util.Collection;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.qa.cinema.persistence.Seat;

public class PersistSeat implements Answer<Void> {
	
	private Collection<Seat> seats;
	
	public PersistSeat(Collection<Seat> seats) {
		this.seats = seats;
	}
	
	@Override
	public Void answer(InvocationOnMock invocation) {
		Seat newSeat = (Seat) invocation.getArguments()[0];
		seats.add(newSeat);
		return null;
	}

}
