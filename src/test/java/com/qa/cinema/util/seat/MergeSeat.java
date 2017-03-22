package com.qa.cinema.util.seat;

import java.util.Collection;
import java.util.List;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.qa.cinema.persistence.Seat;

public class MergeSeat implements Answer<Void> {
	
	private Collection<Seat> seats;
	
	public MergeSeat(Collection<Seat> seats) {
		this.seats = seats;
	}
	
	@Override
	public Void answer(InvocationOnMock invocation) {
		Seat updatedSeat = (Seat) invocation.getArguments()[0];
		for (Seat seat : seats) {
			if (seat.getSeatId() == updatedSeat.getSeatId()) {
				int index = ((List<Seat>) seats).indexOf(seat);
				((List<Seat>) seats).set(index, updatedSeat);
			}
		}
		return null;
	}

}
