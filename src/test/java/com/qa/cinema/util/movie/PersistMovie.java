package com.qa.cinema.util.movie;

import java.util.Collection;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.qa.cinema.persistence.Movie;

public class PersistMovie implements Answer<Void> {
	
	private Collection<Movie> movies;
	
	public PersistMovie(Collection<Movie> movies) {
		this.movies = movies;
	}
	
	@Override
	public Void answer(InvocationOnMock invocation) {
		Movie newMovie = (Movie) invocation.getArguments()[0];
		movies.add(newMovie);
		return null;
	}

}