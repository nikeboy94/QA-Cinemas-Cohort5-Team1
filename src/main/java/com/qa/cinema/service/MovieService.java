package com.qa.cinema.service;

public interface MovieService {

	String listAllMovies();
	
	String listCurrentMovies();
	
	String listComingSoonMovies();
	
	String searchByTitle(String title);
	
	String searchMovies(String title);
	
	String searchByGenre(String genre);

	String createNewMovie(String movie);

	String updateMovie(Long movieId, String movie);
	
	String updateRating(Long movieId, String rating);

	String deleteMovie(Long movieId);

	
}
