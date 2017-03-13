package com.qa.cinema.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.cinema.service.UserService;

@Path("/user")
public class UserEndpoint {

	@Inject
	private UserService service;

	@Path("/json")
	@GET
	@Produces({ "application/json" })
	public String getAllUsers() {
		return service.listAllUsers();
	}
	
	@Path("/json/login/{email}/{password}")
	@GET
	@Produces({"application/json"})
	public String userAuthAttempt(@PathParam("email") String email, @PathParam("password") String password) {
		return service.findUser(email, password);
	}

	@Path("/json")
	@POST
	@Produces({ "application/json" })
	public String addUser(String email) {
		return service.createNewUser(email);
	}

	@Path("/json/{email}")
	@PUT
	@Produces({ "application/json" })
	public String updateUser(@PathParam("email") String email, String user) {
		return service.updateUser(email, user);
	}

	@Path("/json/{email}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteUser(@PathParam("email") String email) {
		return service.deleteUser(email);
	}
}
