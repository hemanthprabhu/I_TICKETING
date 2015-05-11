package com.avnet.ticketing.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.avnet.ticketing.DataBeans.Ticket;
import com.avnet.ticketing.DataBeans.TicketDataTableBean;
import com.avnet.ticketing.DataBeans.TicketDataTableCount;
import com.avnet.ticketing.DataBeans.UserDetails;
import com.avnet.ticketing.constant.TicketingAction;
import com.avnet.ticketing.delegate.TicketingServiceDelagate;
import com.avnet.ticketing.util.JSONResponseBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class CreateTicket
 */
@WebServlet("/Ticketing")
public class Ticketing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ticketing() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String action=request.getParameter("action");
		switch(TicketingAction.valueOf(action))
		{
		case createTicket:
		{
			int customerId=Integer.parseInt(request.getParameter("customerId"));
			String subject=request.getParameter("subject");
			String description=request.getParameter("description");
			String priority=request.getParameter("priority");
			boolean result=TicketingServiceDelagate.createTicket(customerId, subject, description, priority);
			if(result)
			{
				JSONResponseBuilder responseMessage=new JSONResponseBuilder();
				responseMessage.setResponse("success");
				responseMessage.setMessage("Successfully ticket created");
				Gson gson=new Gson();
				String json = gson.toJson(responseMessage);

				PrintWriter out = response.getWriter();
				out.println(json);
			}
			else
			{
				JSONResponseBuilder responseMessage=new JSONResponseBuilder();
				responseMessage.setResponse("failure");
				responseMessage.setMessage("Some internal problem occured");
				Gson gson=new Gson();
				String json = gson.toJson(responseMessage);

				PrintWriter out = response.getWriter();
				out.println(json);
				
			}
			System.out.println("The result for createTicket is "+result);
			
		}
		break;
		case addComment:
		{
			int ticketId=Integer.parseInt(request.getParameter("ticketId"));
			String comment=request.getParameter("comment");
			int addedBy=Integer.parseInt(request.getParameter("addedBy"));
			String role=request.getParameter("role");
			boolean result=TicketingServiceDelagate.addComment(ticketId, comment, addedBy, role);
			if(result)
			{
				JSONResponseBuilder responseMessage=new JSONResponseBuilder();
				responseMessage.setResponse("success");
				responseMessage.setMessage("Successfully comment added");
				Gson gson=new Gson();
				String json = gson.toJson(responseMessage);

				PrintWriter out = response.getWriter();
				out.println(json);
			}
			else
			{
				JSONResponseBuilder responseMessage=new JSONResponseBuilder();
				responseMessage.setResponse("failure");
				responseMessage.setMessage("Some internal problem occured");
				Gson gson=new Gson();
				String json = gson.toJson(responseMessage);

				PrintWriter out = response.getWriter();
				out.println(json);
				
			}
			System.out.println("The result for addComment is "+result);
		}
			
		break;
		case changeStatus:
		{
			int ticketId=Integer.parseInt(request.getParameter("ticketId"));
			String status=request.getParameter("status");
			
			boolean result=TicketingServiceDelagate.changeTicketStatus(ticketId, status);
			if(result)
			{
				JSONResponseBuilder responseMessage=new JSONResponseBuilder();
				responseMessage.setResponse("success");
				responseMessage.setMessage("Successfully status changed");
				Gson gson=new Gson();
				String json = gson.toJson(responseMessage);

				PrintWriter out = response.getWriter();
				out.println(json);
			}
			else
			{
				JSONResponseBuilder responseMessage=new JSONResponseBuilder();
				responseMessage.setResponse("failure");
				responseMessage.setMessage("Some internal problem occured");
				Gson gson=new Gson();
				String json = gson.toJson(responseMessage);

				PrintWriter out = response.getWriter();
				out.println(json);
				
			}
			System.out.println("The result for changeTicketStatus is "+result);
		}
		break;
		case getTickets:
		{
			
			String status=request.getParameter("status");
			
			List<Ticket> result=TicketingServiceDelagate.getTickets(status);
			TicketDataTableBean ticket=null;
			int size=0;
			 size=result.size();
			
			
	
			List<TicketDataTableBean> ticketlist=new ArrayList<TicketDataTableBean>(size);
			
			
			for(int i=0;i<size;i++)
			{
				ticket=new TicketDataTableBean();
				ticket.setTicketId(result.get(i).getTicketId());
				ticket.setSubject(result.get(i).getSubject());
				ticket.setStatus(result.get(i).getStatus());
				ticket.setAgentname(result.get(i).getAgenetName());
				ticket.setPriority(result.get(i).getPriority());
				ticketlist.add(ticket);
				
			}
			TicketDataTableCount ticketlistResponse=new TicketDataTableCount();
			ticketlistResponse.setRecordsFiltered(size);
			ticketlistResponse.setListofTickets(ticketlist);
			Gson gson=new Gson();
			String ticketlistResponsejson = gson.toJson(ticketlistResponse);

			if(size!=0)
			{
				JSONResponseBuilder responseMessage=new JSONResponseBuilder();
				responseMessage.setResponse("success");
				responseMessage.setMessage(ticketlistResponsejson);
				
				String json = gson.toJson(responseMessage);

				PrintWriter out = response.getWriter();
				out.println(json);
			}
			else
			{
				JSONResponseBuilder responseMessage=new JSONResponseBuilder();
				responseMessage.setResponse("failure");
				responseMessage.setMessage("Some internal problem occured");
				
				String json = gson.toJson(responseMessage);

				PrintWriter out = response.getWriter();
				out.println(json);
				
			}

			System.out.println("The result for getTickets is "+result.toString());
		}
		
			
		break;
		case gettAllAgents:
		{
			
			
			
			List<UserDetails> result=TicketingServiceDelagate.getAllAgents();
			Gson gson=new Gson();
			String agentList = gson.toJson(result);
int size=0;
size=result.size();
			if(size!=0)
			{
				JSONResponseBuilder responseMessage=new JSONResponseBuilder();
				responseMessage.setResponse("success");
				responseMessage.setMessage(agentList);
				
				String json = gson.toJson(responseMessage);

				PrintWriter out = response.getWriter();
				out.println(json);
			}
			else
			{
				JSONResponseBuilder responseMessage=new JSONResponseBuilder();
				responseMessage.setResponse("failure");
				responseMessage.setMessage("Some internal problem occured");
				
				String json = gson.toJson(responseMessage);

				PrintWriter out = response.getWriter();
				out.println(json);
				
			}

			System.out.println("The result for get allagents is "+result.toString());
		}
		break;
		case updateAgentInTickets:
		{
			
			String status=request.getParameter("Data");
			int agentid=1;
			List<Integer> tokenList=new ArrayList<Integer>();
			try {
				JSONObject jsonObj = new JSONObject(status);
				agentid=Integer.parseInt(jsonObj.getString("agentId"));
				JSONArray ticketIdlist=jsonObj.getJSONArray("ticketIds");
				System.out.println("agent id"+agentid);
				for(int i=0;i<ticketIdlist.length();i++)
				{
					System.out.println("ticket id "+ticketIdlist.get(i));
					String ticketId=String.valueOf(ticketIdlist.get(i));
					tokenList.add(new Integer(ticketId));
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			boolean result=TicketingServiceDelagate.updateAgentInTickets(agentid, tokenList);
			
			if(result)
			{
				JSONResponseBuilder responseMessage=new JSONResponseBuilder();
				responseMessage.setResponse("success");
				responseMessage.setMessage("Agents names are updated for the ticked");
				Gson gson=new Gson();
				String json = gson.toJson(responseMessage);

				PrintWriter out = response.getWriter();
				out.println(json);
			}
			else
			{
				JSONResponseBuilder responseMessage=new JSONResponseBuilder();
				responseMessage.setResponse("failure");
				responseMessage.setMessage("Some internal problem occured");
				Gson gson=new Gson();
				String json = gson.toJson(responseMessage);

				PrintWriter out = response.getWriter();
				out.println(json);
				
			}
			System.out.println("The result for updateAgentInTickets is "+result);
		}
		break;
		case getTicketAndComments:
		{
			
			int ticketId=Integer.parseInt(request.getParameter("ticketId"));
			
			
			
			
			Ticket result=TicketingServiceDelagate.getTicketAndComments(ticketId);
			
			
			Gson gson=new Gson();
			String ticket = gson.toJson(result);
int size=0;

			if(result!=null)
			{
				JSONResponseBuilder responseMessage=new JSONResponseBuilder();
				responseMessage.setResponse("success");
				responseMessage.setMessage(ticket);
				
				String json = gson.toJson(responseMessage);

				PrintWriter out = response.getWriter();
				out.println(json);
			}
			else
			{
				JSONResponseBuilder responseMessage=new JSONResponseBuilder();
				responseMessage.setResponse("failure");
				responseMessage.setMessage("Some internal problem occured");
				
				String json = gson.toJson(responseMessage);

				PrintWriter out = response.getWriter();
				out.println(json);
				
			}

			System.out.println("The result for updateAgentInTickets is "+result);
		}
		break;
		default:
			JSONResponseBuilder responseMessage=new JSONResponseBuilder();
			responseMessage.setResponse("failure");
			responseMessage.setMessage("Action specified is not available");
			Gson gson=new Gson();
			String json = gson.toJson(responseMessage);

			PrintWriter out = response.getWriter();
			out.println(json);
			
			break;
		
		
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
