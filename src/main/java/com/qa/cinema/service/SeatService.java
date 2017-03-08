package com.qa.cinema.service;

public interface SeatService {
	String findByScreenId(Long screenId);
	String createSeat(String seat);
	String updateSeat(Long seatId, String seat);
	String deleteSeat(Long seatId);
	

}
