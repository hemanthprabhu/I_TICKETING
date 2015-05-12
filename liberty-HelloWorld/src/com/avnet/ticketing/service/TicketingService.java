package com.avnet.ticketing.service;

import java.util.List;

import com.avnet.ticketing.DataBeans.Ticket;
import com.avnet.ticketing.DataBeans.UserDetails;

public interface TicketingService {
	public boolean createTicket(int customerId,String subject,String description,String priority);
	public boolean addComment(int ticketId,String comment,int addedBy,String role);
	public boolean changeTicketStatus(String status,List<Integer> ticketList);
	public List<Ticket> getTickets(String status);
	public  UserDetails loginValidation(String username,String password,String role);
	public  List<UserDetails> getAllAgents();
	public boolean updateAgentInTickets(int agentid,List<Integer> ticketList);
	public Ticket getTicketAndComments(int ticketId);

}
