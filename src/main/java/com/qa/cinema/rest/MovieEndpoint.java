package com.qa.cinema.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.cinema.service.MovieService;

@Path("/movie")
public class MovieEndpoint {

	@Inject
	private MovieService service;

	@Path("/json")
	@GET
	@Produces({ "application/json" })
	public String getAllMovies() {
		return service.listAllMovies();
	}

	@Path("/json")
	@POST
	@Produces({ "application/json" })
	public String addMovie(String movie) {
		return service.createNewMovie(movie);
	}

	@Path("/json/{id}")
	@PUT
	@Produces({ "application/json" })
	public String updateMovie(@PathParam("id") Long movieId, String movie) {
		return service.updateMovie(movieId, movie);
	}

	@Path("/json/{id}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteMovie(@PathParam("id") Long moviedId) {
		return service.deleteMovie(moviedId);
	}
}
