package com.avnet.ticketing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import com.avnet.ticketing.DataBeans.Comment;
import com.avnet.ticketing.DataBeans.Ticket;
import com.avnet.ticketing.DataBeans.UserDetails;
import com.avnet.ticketing.util.DBUtil;

public class TicketingDAOServiceImpl implements TicketingDAOService {

	@Override
	public boolean createTicket(int customerId, String subject,
			String description, String priority) {
		DataSource ds = DBUtil.getDataSource();
		PreparedStatement preparedStatement = null;
		Connection conn = null;
		int result = 0;
		try {
			conn = ds.getConnection();
			preparedStatement = conn
					.prepareStatement("insert into USER01125.T_TICKET(PK_TICKETID,FK_CUSTOMERID,SUBJECT,DESCRIPTION,PRIORITY,STATUS,CREATEDAT) values(NEXT VALUE FOR  ticketIdSequence,?,?,?,?,?,?)");
			Date currentDate = new Date();

			Timestamp currentTimestamp = new Timestamp(currentDate.getTime());
			preparedStatement.setInt(1, customerId);
			preparedStatement.setString(2, subject);
			preparedStatement.setString(3, description);
			preparedStatement.setString(4, priority);
			preparedStatement.setString(5, "NEW");
			preparedStatement.setTimestamp(6, currentTimestamp);
			result = preparedStatement.executeUpdate();
			conn.close();
			preparedStatement.close();
		
		} catch (Exception e) {

		}
		if (result != 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean addComment(int ticketId, String comment, int addedBy,
			String role) {
		DataSource ds = DBUtil.getDataSource();
		PreparedStatement preparedStatement = null;
		Connection conn = null;
		int result = 0;
		try {
			conn = ds.getConnection();
			preparedStatement = conn
					.prepareStatement("insert into USER01125.T_COMMENTS values(NEXT VALUE FOR  commentsIdSequence,?,?,?,?,?)");
			Date currentDate = new Date();

			Timestamp currentTimestamp = new Timestamp(currentDate.getTime());
			preparedStatement.setInt(1, ticketId);
			preparedStatement.setString(2, comment);
			preparedStatement.setString(3,String.valueOf(addedBy));
			preparedStatement.setTimestamp(4, currentTimestamp);
			preparedStatement.setString(5, role);
			result = preparedStatement.executeUpdate();
			conn.close();
			preparedStatement.close();
			} catch (Exception e) {

		}
		if (result != 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean changeTicketStatus(String status,List<Integer> ticketList) {
		
		DataSource ds = DBUtil.getDataSource();
		PreparedStatement preparedStatement = null;
		Connection conn = null;
		int result = 0;
		try {
			conn = ds.getConnection();
			for(Integer ticket : ticketList)
			{
			preparedStatement = conn
					.prepareStatement("update USER01125.T_TICKET set STATUS=?  where PK_TICKETID=?");
			
			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, ticket.intValue());
			
			result = preparedStatement.executeUpdate();
			}
			conn.close();
			preparedStatement.close();
			} catch (Exception e) {

		}
		
		if (result != 0)
			return true;
		else
			return false;
	}

	@Override
	public List<Ticket> getTickets(String status) {
		DataSource ds = DBUtil.getDataSource();
		PreparedStatement preparedStatement = null;
		Connection conn = null;
		ResultSet rs =null;
		List<Ticket> ticketlist=new ArrayList<Ticket>();
		Ticket ticket=null;
		
		try {
			conn = ds.getConnection();
			if(status==null)
			{
				preparedStatement = conn
						.prepareStatement("select ticket.*,customer.FIRSTNAME as customername,agent.FIRSTNAME as agentname from USER01125.T_TICKET  as ticket join USER01125.T_CUSTOMER as customer on ticket.FK_CUSTOMERID=customer.PK_CUSTOMERID left join USER01125.T_AGENT as agent on ticket.FK_AGENTID=agent.PK_AGENTID");
			}
			else
			{
			preparedStatement = conn
					.prepareStatement("select ticket.*,customer.FIRSTNAME as customername,agent.FIRSTNAME as agentname from USER01125.T_TICKET  as ticket join USER01125.T_CUSTOMER as customer on ticket.FK_CUSTOMERID=customer.PK_CUSTOMERID and ticket.STATUS=? left join USER01125.T_AGENT as agent on ticket.FK_AGENTID=agent.PK_AGENTID");
			
			preparedStatement.setString(1, status);
			}
			 rs = preparedStatement.executeQuery();
			 while(rs.next())
			 {
				 System.out.println("Entered in getTickets "+rs.getString("STATUS"));
				ticket=new Ticket();
				ticket.setAgenetName(rs.getString("agentname"));
				ticket.setCustomerName(rs.getString("customername"));
				ticket.setTicketId(rs.getInt("PK_TICKETID"));
				ticket.setDescription(rs.getString("DESCRIPTION"));
				ticket.setSubject(rs.getString("SUBJECT"));
				ticket.setStatus(rs.getString("STATUS"));
				ticket.setPriority(rs.getString("PRIORITY"));
				ticket.setTimestamp(rs.getTimestamp("CREATEDAT"));
				ticketlist.add(ticket);
			 }
			rs.close();
			conn.close();
			preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();

		}
		return ticketlist;
		
	}

	@Override
	public UserDetails loginValidation(String username, String password,
			String role) {
		DataSource ds = DBUtil.getDataSource();
		PreparedStatement preparedStatement = null;
		Connection conn = null;
		ResultSet rs =null;
		System.out.println("role in dao "+role);
		UserDetails user=new UserDetails();
		try {
			conn = ds.getConnection();
			if(role.equalsIgnoreCase("customer"))
			{
				preparedStatement = conn
						.prepareStatement("select * from USER01125.T_CUSTOMER where EMAILID=?");
				preparedStatement.setString(1,username);
				 rs = preparedStatement.executeQuery();
				 while(rs.next())
				 {
					String pass=rs.getString("PASSW0RD");
					System.out.println("From db :"+pass+"user typed :"+password+"compare result :"+password.equalsIgnoreCase(pass));
					if(password.equalsIgnoreCase(pass))
					{
						user.setValid(true);
						user.setName(rs.getString("FIRSTNAME"));
						user.setUserid(rs.getString("PK_CUSTOMERID"));
						user.setEmailId(rs.getString("EMAILID"));
						user.setRole("customer");
						return user;
					}
				 }
			}
			else if(role.equalsIgnoreCase("agent"))
			{
				System.out.println("inside agent section");
			preparedStatement = conn
					.prepareStatement("select * from USER01125.T_AGENT where EMAILID=?");
			
			preparedStatement.setString(1,username);
			 rs = preparedStatement.executeQuery();
			 while(rs.next())
			 {
				String pass=rs.getString("PASSW0RD");
				System.out.println("From db :"+pass+"user typed :"+password+"compare result :"+password.equalsIgnoreCase(pass));
				if(password.equalsIgnoreCase(pass))
				{
					user.setValid(true);
					user.setName(rs.getString("FIRSTNAME"));
					user.setUserid(rs.getString("PK_AGENTID"));
					user.setEmailId(rs.getString("EMAILID"));
					user.setRole("agent");
					return user;
				}
			 }
			}
			
			rs.close();
			conn.close();
			preparedStatement.close();
			} catch (Exception e) {
System.out.println(e.toString());
		}
		user.setValid(false);
		return user;
	}

	@Override
	public List<UserDetails> getAllAgents() {
		DataSource ds = DBUtil.getDataSource();
		PreparedStatement preparedStatement = null;
		Connection conn = null;
		ResultSet rs =null;
		List<UserDetails> agentList=new ArrayList<UserDetails>();
		UserDetails agent=null;
		
		try {
			conn = ds.getConnection();
			
			preparedStatement = conn
					.prepareStatement("select * from USER01125.T_AGENT");
			
			 rs = preparedStatement.executeQuery();
			 while(rs.next())
			 {
				agent=new UserDetails();
				agent.setUserid(rs.getString("PK_AGENTID"));
				agent.setName(rs.getString("FIRSTNAME"));
				agent.setEmailId(rs.getString("EMAILID"));
				
				agentList.add(agent);
			 
			 }
			rs.close();
			conn.close();
			preparedStatement.close();
			} catch (Exception e) {

		}
		return agentList;
	
	}

	@Override
	public boolean updateAgentInTickets(int agentid, List<Integer> ticketList) {
		DataSource ds = DBUtil.getDataSource();
		PreparedStatement preparedStatement = null;
		Connection conn = null;
		int result = 0;
		try {
			conn = ds.getConnection();
			for(Integer ticket : ticketList)
			{
				preparedStatement = conn
						.prepareStatement("update USER01125.T_TICKET set FK_AGENTID=?  where PK_TICKETID=?");
				
				preparedStatement.setInt(1, agentid);
				preparedStatement.setInt(2,ticket.intValue() );
				
				result = preparedStatement.executeUpdate();
			}
			
			conn.close();
			preparedStatement.close();
			} catch (Exception e) {

		}
		
		if (result != 0)
			return true;
		else
			return false;

	}

	@Override
	public Ticket getTicketAndComments(int ticketId) {

				DataSource ds = DBUtil.getDataSource();
		PreparedStatement preparedStatement = null;
		Connection conn = null;
		ResultSet rs =null;
		
		Ticket ticket=null;
		
		try {
			conn = ds.getConnection();
			
			preparedStatement = conn
					.prepareStatement("select ticket.*,customer.FIRSTNAME as customername,agent.FIRSTNAME as agentname from T_TICKET as ticket join T_CUSTOMER as customer on ticket.FK_CUSTOMERID=customer.PK_CUSTOMERID and PK_TICKETID=? left join T_AGENT agent on ticket.FK_AGENTID=agent.PK_AGENTID");
			preparedStatement.setInt(1,ticketId);
			 rs = preparedStatement.executeQuery();
			 while(rs.next())
			 {
				ticket=new Ticket();
				ticket.setAgenetName(rs.getString("agentname"));
				ticket.setCustomerName(rs.getString("customername"));
				ticket.setDescription(rs.getString("description"));
				ticket.setPriority(rs.getString("priority"));
				ticket.setStatus(rs.getString("status"));
				ticket.setSubject(rs.getString("subject"));
				ticket.setTicketId(rs.getInt("PK_TICKETID"));
				ticket.setTimestamp(rs.getTimestamp("CREATEDAT"));
				
				
			 
			 }
			 
			
			
			 ticket.setComments(getCommentsByTicketId(ticketId));
			 System.out.println("comments list "+ticket.getComments());
			rs.close();
			conn.close();
			preparedStatement.close();
			
			} catch (Exception e) {

		}
		return ticket;
			
	}
	private List<Comment> getCommentsByTicketId(int ticketId)
	{
		DataSource ds = DBUtil.getDataSource();
		PreparedStatement preparedStatement = null;
		Connection conn = null;
		ResultSet rs =null;
		List<Comment> commentlist=new ArrayList<Comment>();
		Comment comment=null;
		
		
		try {
			conn = ds.getConnection();
			
			preparedStatement = conn
					.prepareStatement("select * from T_COMMENTS where FK_TICKETID=?");
			preparedStatement.setInt(1,ticketId);
			 rs = preparedStatement.executeQuery();
			 while(rs.next())
			 {
				comment=new Comment();
				comment.setCommentText(rs.getString("COMMENTS"));
				comment.setCreatedAt(rs.getTimestamp("CREATEDAT"));
				comment.setRole(rs.getString("ROLE"));
				commentlist.add(comment);
				
				System.out.println("Comments "+comment+"  Comment list"+commentlist);
			 
			 }
		
			
			rs.close();
			conn.close();
			preparedStatement.close();
			
			} catch (Exception e) {

		}
		return commentlist;
		
	}

}
