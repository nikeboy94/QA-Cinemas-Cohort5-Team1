package com.qa.cinema.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Showing {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long showingId;
	
	@ManyToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name="screenId", nullable=false)
	private Screen screen;
	
	@ManyToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name="movieId", nullable=false)
	private Movie movie;
	
	private String dateTime;
	
	public Showing() {
		//Empty constructor
	}
	
	public Showing(Screen screen, Movie movie, String dateTime) {
		this.screen = screen;
		this.movie = movie;
		this.dateTime = dateTime;
	}
	
	public Long getShowingId() {
		return showingId;
	}
	
	public Screen getScreen() {
		return screen;
	}
	
	public void setScreen(Screen newScreen) {
		this.screen = newScreen;
	}
	
	public Movie getMovie() {
		return movie;
	}
	
	public void setMovie(Movie newMovie) {
		this.movie = newMovie;
	}
	
	public String getDateTime() {
		return dateTime;
	}
	
	public void setDateTime(String newDateTime) {
		this.dateTime = newDateTime;
	}

}
