package com.qa.cinema.service;

public interface MovieService {

	String listAllMovies();
	
	String searchByTitle(String title);

	String createNewMovie(String movie);

	String updateMovie(Long movieId, String movie);

	String deleteMovie(Long movieId);

	
}
