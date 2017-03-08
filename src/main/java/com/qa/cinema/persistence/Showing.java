package com.qa.cinema.persistence;

public class Showing {
	
	private Long showingId;
	private Long screenId;
	private Long movieId;
	private String dateTime;
	
	public Showing() {}
	
	public Showing(Long screenId, Long movieId, String dateTime) {
		this.screenId = screenId;
		this.movieId = movieId;
		this.dateTime = dateTime;
	}
	
	public Long getShowingId() {
		return showingId;
	}
	
	public Long getScreen() {
		return screenId;
	}
	
	public void setScreen(Long newScreenId) {
		this.screenId = newScreenId;
	}
	
	public Long getMovie() {
		return movieId;
	}
	
	public void setMovie(Long newMovieId) {
		this.movieId = newMovieId;
	}
	
	public String getDateTime() {
		return dateTime;
	}
	
	public void setDateTime(String newDateTime) {
		this.dateTime = newDateTime;
	}

}
