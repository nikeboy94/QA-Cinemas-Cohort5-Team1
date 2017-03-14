package com.qa.cinema.persistence;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long movieId;
	
	private String title;
	private String genre;
	private Date releaseDate;
	private String classification;
	private String posterUrl;
	private String trailerUrl;
	private Double rating = 0.0;
	private Integer runtime;
	private String description;
	

	public Movie() {
		//Empty constructor
	}
	
	public Movie(String title, String genre, Date releaseDate, String classification, String posterUrl,
			String trailerUrl, Integer runtime) {
		
		this.title = title;
		this.genre = genre;
		this.releaseDate = releaseDate;
		this.classification = classification;
		this.posterUrl = posterUrl;
		this.trailerUrl = trailerUrl;
		this.runtime = runtime;
		this.description = "Temporary description for: " + title;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public Date getReleaseDate() {
		return releaseDate;
	}


	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}


	public String getClassification() {
		return classification;
	}


	public void setClassification(String classification) {
		this.classification = classification;
	}


	public String getPosterUrl() {
		return posterUrl;
	}


	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}


	public String getTrailerUrl() {
		return trailerUrl;
	}


	public void setTrailerUrl(String trailerUrl) {
		this.trailerUrl = trailerUrl;
	}


	public double getRating() {
		return rating;
	}


	public void setRating(Double rating) {
		this.rating = rating;
	}


	public int getRuntime() {
		return runtime;
	}


	public void setRuntime(Integer runtime) {
		this.runtime = runtime;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Long getMovieId() {
		return movieId;
	}
	
	

}
