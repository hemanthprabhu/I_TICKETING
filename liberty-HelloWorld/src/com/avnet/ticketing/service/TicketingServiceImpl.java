package com.avnet.ticketing.service;

import java.util.List;
import java.util.Map;

import com.avnet.ticketing.DataBeans.Ticket;
import com.avnet.ticketing.DataBeans.UserDetails;
import com.avnet.ticketing.delegate.TicketingDAODelegate;

public class TicketingServiceImpl implements TicketingService{

	@Override
	public boolean createTicket(int customerId, String subject,
			String description, String priority) {
		boolean flag=TicketingDAODelegate.createTicket(customerId, subject, description, priority);
		return flag;
	}

	@Override
	public boolean addComment(int ticketId, String comment, int addedBy,
			String role) {
		boolean flag=TicketingDAODelegate.addComment(ticketId, comment, addedBy, role);
		return flag;
	}

	@Override
	public boolean changeTicketStatus(String status,List<Integer> ticketList) {
		boolean flag=TicketingDAODelegate.changeTicketStatus(status,ticketList);
		return flag;
	}

	
	@Override
	public List<Ticket> getTickets(String status) {
		List<Ticket> ticketlist=TicketingDAODelegate.getTickets(status);
		return ticketlist;
	}

	@Override
	public UserDetails loginValidation(String username, String password, String role) {
		UserDetails userdetails=TicketingDAODelegate.loginValidation(username, password, role);
		return userdetails;
	}

	@Override
	public List<UserDetails> getAllAgents() {
		List<UserDetails> userdetailsList=TicketingDAODelegate.getAllAgents();
		return userdetailsList;
	}

	@Override
	public boolean updateAgentInTickets(int agentid, List<Integer> ticketList) {
		boolean flag=TicketingDAODelegate.updateAgentInTickets(agentid, ticketList);
		return flag;
	}

	@Override
	public Ticket getTicketAndComments(int ticketId) {
		Ticket tiket=TicketingDAODelegate.getTicketAndComments(ticketId);
		return tiket;
	}

	@Override
	public Map getStatusCount() {
		// TODO Auto-generated method stub
		return TicketingDAODelegate.getStatusCount();
	}

}
