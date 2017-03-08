package com.qa.cinema.rest;

import javax.ws.rs.Produces;

import com.qa.cinema.service.ScreenService;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/screen")
public class ScreenEndpoint {
	
	@Inject
	private ScreenService service;
	
	@Path("/json")
	@GET
	@Produces({"application/json"})
	public String getAllScreens(){
		return service.listAllScreens();
	}
	
	@Path("/json")
	@POST
	@Produces({"application/json"})
	public String addScreen(String screen){
		return service.createNewScreen(screen);
	}
	
	@Path("/json/{id}")
	@DELETE
	@Produces({"application/json"})
	public String deleteScreen(@PathParam("id") Long id){
		return service.deleteScreen(id);
	}

}
