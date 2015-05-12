package com.avnet.ticketing.dao;

import java.util.List;
import java.util.Map;

import com.avnet.ticketing.DataBeans.Ticket;
import com.avnet.ticketing.DataBeans.UserDetails;

public interface TicketingDAOService {
public boolean createTicket(int customerId,String subject,String description,String priority);
public boolean addComment(int ticketId,String comment,int addedBy,String role);
public boolean changeTicketStatus(String status,List<Integer> ticketList);
public List<Ticket> getTickets(String status);
public  UserDetails loginValidation(String username,String password,String role);
public  List<UserDetails> getAllAgents();
public boolean updateAgentInTickets(int agentid,List<Integer> ticketList);
public Ticket getTicketAndComments(int ticketId);
public Map getStatusCount();

}
