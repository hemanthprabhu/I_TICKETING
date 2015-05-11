package com.avnet.ticketing.delegate;

import java.util.List;

import com.avnet.ticketing.DataBeans.Ticket;
import com.avnet.ticketing.DataBeans.UserDetails;
import com.avnet.ticketing.dao.TicketingDAOService;
import com.avnet.ticketing.dao.TicketingDAOServiceImpl;

public class TicketingDAODelegate {
private static TicketingDAOService daoService=new TicketingDAOServiceImpl();
public static boolean createTicket(int customerId,String subject,String description,String priority)
{
	return daoService.createTicket(customerId, subject, description, priority);
}
public static boolean addComment(int ticketId,String comment,int addedBy,String role)
{
	return daoService.addComment(ticketId, comment, addedBy, role);
}
public static boolean changeTicketStatus(int ticketId,String status)
{
	return daoService.changeTicketStatus(ticketId, status);
}
public static  List<Ticket> getTickets(String status)
{
	return daoService.getTickets(status);
}
public static  UserDetails loginValidation(String username,String password,String role)
{
	return daoService.loginValidation(username, password, role);
}
public static List<UserDetails> getAllAgents()
{
	return daoService.getAllAgents();
}
public static boolean updateAgentInTickets(int agentid,List<Integer> ticketList)
{
	return daoService.updateAgentInTickets(agentid, ticketList);
}
public static Ticket getTicketAndComments(int ticketId)
{
	return daoService.getTicketAndComments(ticketId);
}


}
