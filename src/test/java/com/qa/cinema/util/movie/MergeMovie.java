package com.qa.cinema.util.movie;

import java.util.Collection;
import java.util.List;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.qa.cinema.persistence.Movie;

public class MergeMovie implements Answer<Void> {
	
	private Collection<Movie> movies;
	
	public MergeMovie(Collection<Movie> movies) {
		this.movies = movies;
	}
	
	@Override
	public Void answer(InvocationOnMock invocation) {
		Movie updatedMovie = (Movie) invocation.getArguments()[0];
		for (Movie movie : movies) {
			if (movie.getMovieId() == updatedMovie.getMovieId()) {
				int index = ((List<Movie>) movies).indexOf(movie);
				((List<Movie>) movies).set(index, updatedMovie);
			}
		}
		return null;
	}

}
