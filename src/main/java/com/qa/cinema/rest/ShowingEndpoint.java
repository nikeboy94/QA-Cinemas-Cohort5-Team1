package com.qa.cinema.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.cinema.service.ShowingService;

@Path("/showing")
public class ShowingEndpoint {
	
	@Inject
	private ShowingService service;
	
	@Path("/json")
	@GET
	@Produces({"application/json"})
	public String getAllShowings() {
		return service.getAllShowings();
	}
	
	@Path("/json")
	@POST
	@Produces({"application/json"})
	public String addShowing(String showing) {
		return service.createShowing(showing);
	}
	
	@Path("/json/{showingId}")
	@PUT
	@Produces({"application/json"})
	public String updateShowing(@PathParam("showingId") Long showingId, String showing) {
		return service.updateShowing(showingId, showing);
	}
	
	@Path("/json/{showingId}")
	@DELETE
	@Produces({"application/json"})
	public String deleteShowing(@PathParam("showingId") Long showingId) {
		return service.deleteShowing(showingId);
	}

}
