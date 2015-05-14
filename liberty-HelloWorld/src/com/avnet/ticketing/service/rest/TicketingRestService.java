package com.avnet.ticketing.service.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.avnet.ticketing.service.TicketingService;
import com.avnet.ticketing.service.TicketingServiceImpl;

@Path("/TicketingService")
public class TicketingRestService {
	private static TicketingService service=new TicketingServiceImpl();
	@GET
	@Path("/CreateTicket")
	@Produces(MediaType.APPLICATION_JSON)
	public  String createTicket(@QueryParam("customerId") int customerId,@QueryParam("subject")String subject,@QueryParam("description")String description,@QueryParam("priority")String priority)
	{
		boolean flag= service.createTicket(customerId, subject, description, priority);
		if(flag)
		return "success";
		else
		return "failure";
			
	}
}
