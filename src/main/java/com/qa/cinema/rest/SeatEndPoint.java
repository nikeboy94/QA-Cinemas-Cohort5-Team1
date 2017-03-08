package com.qa.cinema.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;

import com.qa.cinema.service.SeatService;

import javax.ws.rs.*;

public class SeatEndPoint {
	
	@Inject
	private SeatService service;
	
	@Path("/json")
	@GET
	@Produces({"application/json"})
	public String getSeatByScreenId(Long screenId){
		return service.findByScreenId(screenId);
		
	}
	
	@Path("/jason")
	@POST
	@Produces({ "application/json"})
	public String addSeat(String seat){
		return service.createSeat(seat);
	}
	
	@Path("/json/{id}")
	@PUT
	@Produces({ "application/json"})
	public String updateSeat(@PathParam("id") Long seatId, String seat){
		return service.updateSeat(seatId, seat);
	}
	
	@Path("/json/{id}")
	@DELETE
	@Produces({"application/json"})
	public String deleteMovie(@PathParam("id") Long seatId){
		return service.deleteSeat(seatId);
	}
	

}
