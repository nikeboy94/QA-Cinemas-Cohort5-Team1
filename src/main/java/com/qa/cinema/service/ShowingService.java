package com.qa.cinema.service;

public interface ShowingService {
	
	public String getAllShowings();
	
	public String findShowingByMovieId(Long movieId);
	
	public String createShowing(String showing);
	
	public String updateShowing(Long showingId, String showing);
	
	public String deleteShowing(Long showingId);

}
