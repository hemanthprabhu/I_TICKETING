package com.avnet.ticketing.delegate;

import java.util.List;
import java.util.Map;

import com.avnet.ticketing.DataBeans.Ticket;
import com.avnet.ticketing.DataBeans.UserDetails;
import com.avnet.ticketing.service.TicketingService;
import com.avnet.ticketing.service.TicketingServiceImpl;

public class TicketingServiceDelagate {
	private static TicketingService service=new TicketingServiceImpl();
	public static boolean createTicket(int customerId,String subject,String description,String priority)
	{
		return service.createTicket(customerId, subject, description, priority);
	}
	public static boolean addComment(int ticketId,String comment,int addedBy,String role)
	{
		return service.addComment(ticketId, comment, addedBy, role);
	}
	public static boolean changeTicketStatus(String status,List<Integer> ticketList)
	{
		return service.changeTicketStatus(status,ticketList);
	}
	public static  List<Ticket> getTickets(String status)
	{
		return service.getTickets(status);
	}
	public static  UserDetails loginValidation(String username,String password,String role)
	{
		return service.loginValidation(username, password, role);
	}
	public static List<UserDetails> getAllAgents()
	{
		return service.getAllAgents();
	}
	public static boolean updateAgentInTickets(int agentid,List<Integer> ticketList)
	{
		return service.updateAgentInTickets(agentid, ticketList);
	}
	public static Ticket getTicketAndComments(int ticketId)
	{
		return service.getTicketAndComments(ticketId);
	}
	public static Map getStatusCount() 
	{
		return service.getStatusCount();
	}

}
 