package com.qa.cinema.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Showing {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long showingId;
	private Long screenId;
	private Long movieId;
	private String dateTime;
	
	public Showing() {
		//Empty constructor
	}
	
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
