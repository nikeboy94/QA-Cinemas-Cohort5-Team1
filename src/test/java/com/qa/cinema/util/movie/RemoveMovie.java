package com.qa.cinema.util.movie;

import java.util.Collection;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.qa.cinema.persistence.Movie;

public class RemoveMovie implements Answer<Void> {
	
	private Collection<Movie> movies;
	
	public RemoveMovie(Collection<Movie> movies) {
		this.movies = movies;
	}
	
	@Override
	public Void answer(InvocationOnMock invocation) {
		Movie movieToRemove = (Movie) invocation.getArguments()[0];
		movies.remove(movieToRemove);
		return null;
	}

}
