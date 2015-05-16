package com.avnet.ticketing.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.avnet.ticketing.DataBeans.DataTableBean;
import com.avnet.ticketing.DataBeans.Ticket;
import com.avnet.ticketing.DataBeans.TicketDataTableBean;
import com.avnet.ticketing.DataBeans.TicketDataTableCount;
import com.avnet.ticketing.DataBeans.UserDetails;
import com.avnet.ticketing.constant.TicketingAction;
import com.avnet.ticketing.delegate.TicketingServiceDelagate;
import com.avnet.ticketing.sendEmail.ConfigParams;
import com.avnet.ticketing.sendEmail.SendGridService;
import com.avnet.ticketing.util.JSONResponseBuilder;
import com.google.gson.Gson;
import com.sendgrid.SendGridException;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		

		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		switch (TicketingAction.valueOf(action)) {
		case createTicket: {

			int customerId = Integer.parseInt(session.getAttribute("userid")
					.toString());
			String subject = request.getParameter("subject");
			String description = request.getParameter("description");
			String priority = request.getParameter("priority");
			String urlString="https://api.eu.apim.ibmcloud.com/hemanthprabhu33gmailcom-examples/sb/ticketingService/CreateTicket?customerId="+customerId+"&subject="+subject+"&description="+description+"&priority="+priority+"&client_id=df38c6ed-e651-4638-80b7-82a3aad5ba25";
			System.out.println("URL construction "+urlString);
//			URL url = new URL(urlString);
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("GET");
//			conn.setRequestProperty("Accept", "application/json");
//
//			if (conn.getResponseCode() != 200) {
//				throw new RuntimeException("Failed : HTTP error code : "
//						+ conn.getResponseCode());
//			}
			boolean result = false;
//			BufferedReader reader = new BufferedReader(new InputStreamReader(
//					(conn.getInputStream())));
//			String line;
//			while ((line = reader.readLine()) != null) {
//				if (line.equalsIgnoreCase("success")) {
//					System.out.println("Line is " + line);
//					result = true;
//					break;
//				}
//			}
			 BufferedReader in = new BufferedReader(
		    	        new InputStreamReader(new URL(urlString).openStream()));

		    	        String inputLine;
		    	        while ((inputLine = in.readLine()) != null)
		    	        {
		    	            System.out.println(inputLine);
		    	            if (inputLine.equalsIgnoreCase("success")) {
		    					System.out.println("Line is " + inputLine);
		    					result = true;
		    					break;
		    				}
		    	        }
		    	        in.close();

			// =TicketingServiceDelagate.createTicket(customerId, subject,
			// description, priority);
			if (result) {
				JSONResponseBuilder responseMessage = new JSONResponseBuilder();
				responseMessage.setResponse("success");
				responseMessage.setMessage("Successfully ticket created");
				Gson gson = new Gson();
				String json = gson.toJson(responseMessage);

				PrintWriter out = response.getWriter();
				out.println(json);
			} else {
				JSONResponseBuilder responseMessage = new JSONResponseBuilder();
				responseMessage.setResponse("failure");
				responseMessage.setMessage("Some internal problem occured");
				Gson gson = new Gson();
				String json = gson.toJson(responseMessage);

				PrintWriter out = response.getWriter();
				out.println(json);

			}
			System.out.println("The result for createTicket is " + result);

		}
			break;
		case addComment: {
			int ticketId = Integer.parseInt(request.getParameter("ticketId"));
			String comment = request.getParameter("comment");
			int addedBy = Integer.parseInt(session.getAttribute("userid")
					.toString());
			String role = session.getAttribute("role").toString();
			boolean result = TicketingServiceDelagate.addComment(ticketId,
					comment, addedBy, role);
			if (result) {
				JSONResponseBuilder responseMessage = new JSONResponseBuilder();
				responseMessage.setResponse("success");
				responseMessage.setMessage("Successfully comment added");
				Gson gson = new Gson();
				String json = gson.toJson(responseMessage);
				HashMap<String, String> user=null;
				try {
					user = _getUser();
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Date currentDate = new Date();

				Timestamp currentTimestamp = new Timestamp(currentDate.getTime());
				String msg="Comment has been added for the ticket "+ ticketId + " At "+currentTimestamp;
				String userName = user.get("username");
				String password1 = user.get("password");
				ConfigParams params = new ConfigParams(session.getAttribute("emailId").toString(), "hemanth.prabhu@avnet.com","Comment Added",
						msg, userName, password1);
				params.set_bccTo("");
				params.set_category("");
				params.set_footerHtml("");
				params.set_footerText("");
				params.set_templateId("");		
				params.set_openTrack(false);
				params.set_clickTrack(false);
				 
				// Add an image file for attachment demo		
				ServletContext servletContext = request.getSession().getServletContext();		
				final String fileName = "bluemix.png";
				final String relativeWebPath = "images" + File.separator + fileName;
				final String filePath = servletContext.getRealPath(relativeWebPath);				
				params.set_fileName(fileName);
				params.set_filePath(filePath);
				
				// Collect the UUID and cookie sent from the browser 
					try {
						SendGridService.send(params);
					} catch (SendGridException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				
				PrintWriter out = response.getWriter();
				out.println(json);
			} else {
				JSONResponseBuilder responseMessage = new JSONResponseBuilder();
				responseMessage.setResponse("failure");
				responseMessage.setMessage("Some internal problem occured");
				Gson gson = new Gson();
				String json = gson.toJson(responseMessage);

				PrintWriter out = response.getWriter();
				out.println(json);

			}
			System.out.println("The result for addComment is " + result);
		}

			break;
		case changeStatus: {

			String data = request.getParameter("Data");
			String status = "";
			List<Integer> tokenList = new ArrayList<Integer>();
			try {
				JSONObject jsonObj = new JSONObject(data);
				status = jsonObj.getString("statusId");
				JSONArray ticketIdlist = jsonObj.getJSONArray("ticketIds");
				System.out.println("agent id" + status);
				for (int i = 0; i < ticketIdlist.length(); i++) {
					System.out.println("ticket id " + ticketIdlist.get(i));
					String ticketId = String.valueOf(ticketIdlist.get(i));
					tokenList.add(new Integer(ticketId));
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			boolean result = TicketingServiceDelagate.changeTicketStatus(
					status, tokenList);
			if (result) {
				JSONResponseBuilder responseMessage = new JSONResponseBuilder();
				responseMessage.setResponse("success");
				responseMessage.setMessage("Successfully status changed");
				Gson gson = new Gson();
				String json = gson.toJson(responseMessage);

				PrintWriter out = response.getWriter();
				out.println(json);
			} else {
				JSONResponseBuilder responseMessage = new JSONResponseBuilder();
				responseMessage.setResponse("failure");
				responseMessage.setMessage("Some internal problem occured");
				Gson gson = new Gson();
				String json = gson.toJson(responseMessage);

				PrintWriter out = response.getWriter();
				out.println(json);

			}
			System.out
					.println("The result for changeTicketStatus is " + result);
		}
			break;
		case getTickets: {
			String status = null;

			if (request.getParameter("status") != null) {
				status = request.getParameter("status");

			}

			List<Ticket> result = TicketingServiceDelagate.getTickets(status);
			TicketDataTableBean ticket = null;
			int size = 0;
			size = result.size();

			List<TicketDataTableBean> ticketlist = new ArrayList<TicketDataTableBean>(
					size);

			String role = session.getAttribute("role").toString();
			System.out.println("role from session is " + role);

			if (role.equalsIgnoreCase("customer")) {
				int customerid = Integer.parseInt(session
						.getAttribute("userid").toString());
				System.out.println("User id from session is " + customerid);
				for (int i = 0; i < size; i++) {
					ticket = new TicketDataTableBean();
					System.out.println("Userid from session is" + customerid
							+ "userid from db is "
							+ result.get(i).getCustomerId() + " result :");
					if (result.get(i).getCustomerId() == customerid) {
						ticket.setTicketId(result.get(i).getTicketId());
						ticket.setSubject(result.get(i).getSubject());
						ticket.setStatus(result.get(i).getStatus());
						ticket.setAgentname(result.get(i).getAgenetName());
						ticket.setPriority(result.get(i).getPriority());
						ticketlist.add(ticket);
					}

				}
			} else if (role.equalsIgnoreCase("agent")) {
				for (int i = 0; i < size; i++) {
					ticket = new TicketDataTableBean();

					ticket.setTicketId(result.get(i).getTicketId());
					ticket.setSubject(result.get(i).getSubject());
					ticket.setStatus(result.get(i).getStatus());
					ticket.setAgentname(result.get(i).getAgenetName());
					ticket.setPriority(result.get(i).getPriority());
					ticketlist.add(ticket);

				}
			}

			TicketDataTableCount ticketlistResponse = new TicketDataTableCount();
			ticketlistResponse.setRecordsFiltered(size);
			ticketlistResponse.setListofTickets(ticketlist);

			DataTableBean bean = new DataTableBean();
			String draw = request.getParameter("draw");
			bean.setDraw(Integer.parseInt(draw));
			bean.setRecordsTotal(size);

			bean.setRecordsFiltered(size);
			bean.setData(ticketlist);
			Gson gson = new Gson();
			String ticketlistResponsejson = gson.toJson(bean);

			PrintWriter out = response.getWriter();
			out.println(ticketlistResponsejson);

			System.out.println("The result for getTickets is "
					+ result.toString());
		}

			break;
		case gettAllAgents: {

			List<UserDetails> result = TicketingServiceDelagate.getAllAgents();
			Gson gson = new Gson();
			String agentList = gson.toJson(result);
			int size = 0;
			size = result.size();
			if (size != 0) {
				JSONResponseBuilder responseMessage = new JSONResponseBuilder();
				responseMessage.setResponse("success");
				responseMessage.setMessage(agentList);

				String json = gson.toJson(responseMessage);

				PrintWriter out = response.getWriter();
				out.println(json);
			} else {
				JSONResponseBuilder responseMessage = new JSONResponseBuilder();
				responseMessage.setResponse("failure");
				responseMessage.setMessage("Some internal problem occured");

				String json = gson.toJson(responseMessage);

				PrintWriter out = response.getWriter();
				out.println(json);

			}

			System.out.println("The result for get allagents is "
					+ result.toString());
		}
			break;
		case getStatusAndCount: {

			Map result = TicketingServiceDelagate.getStatusCount();

			Gson gson = new Gson();
			String statusAndCountList = gson.toJson(result);

			int size = 0;
			size = result.size();
			if (size != 0) {
				JSONResponseBuilder responseMessage = new JSONResponseBuilder();
				responseMessage.setResponse("success");
				responseMessage.setMessage(statusAndCountList);

				String json = gson.toJson(responseMessage);

				PrintWriter out = response.getWriter();
				out.println(json);
			} else {
				JSONResponseBuilder responseMessage = new JSONResponseBuilder();
				responseMessage.setResponse("failure");
				responseMessage.setMessage("Some internal problem occured");

				String json = gson.toJson(responseMessage);

				PrintWriter out = response.getWriter();
				out.println(json);

			}

			System.out.println("The result for get allagents is "
					+ result.toString());
		}
			break;
		case updateAgentInTickets: {

			String status = request.getParameter("Data");
			int agentid = 1;
			List<Integer> tokenList = new ArrayList<Integer>();
			try {
				JSONObject jsonObj = new JSONObject(status);
				agentid = Integer.parseInt(jsonObj.getString("agentId"));
				JSONArray ticketIdlist = jsonObj.getJSONArray("ticketIds");
				System.out.println("agent id" + agentid);
				for (int i = 0; i < ticketIdlist.length(); i++) {
					System.out.println("ticket id " + ticketIdlist.get(i));
					String ticketId = String.valueOf(ticketIdlist.get(i));
					tokenList.add(new Integer(ticketId));
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			boolean result = TicketingServiceDelagate.updateAgentInTickets(
					agentid, tokenList);

			if (result) {
				JSONResponseBuilder responseMessage = new JSONResponseBuilder();
				responseMessage.setResponse("success");
				responseMessage
						.setMessage("Agents names are updated for the ticked");
				Gson gson = new Gson();
				String json = gson.toJson(responseMessage);

				PrintWriter out = response.getWriter();
				out.println(json);
			} else {
				JSONResponseBuilder responseMessage = new JSONResponseBuilder();
				responseMessage.setResponse("failure");
				responseMessage.setMessage("Some internal problem occured");
				Gson gson = new Gson();
				String json = gson.toJson(responseMessage);

				PrintWriter out = response.getWriter();
				out.println(json);

			}
			System.out.println("The result for updateAgentInTickets is "
					+ result);
		}
			break;
		case getTicketAndComments: {

			int ticketId = Integer.parseInt(request.getParameter("ticketId"));

			Ticket result = TicketingServiceDelagate
					.getTicketAndComments(ticketId);

			Gson gson = new Gson();
			String ticket = gson.toJson(result);
			int size = 0;

			if (result != null) {
				JSONResponseBuilder responseMessage = new JSONResponseBuilder();
				responseMessage.setResponse("success");
				responseMessage.setMessage(ticket);

				String json = gson.toJson(responseMessage);

				PrintWriter out = response.getWriter();
				out.println(json);
			} else {
				JSONResponseBuilder responseMessage = new JSONResponseBuilder();
				responseMessage.setResponse("failure");
				responseMessage.setMessage("Some internal problem occured");

				String json = gson.toJson(responseMessage);

				PrintWriter out = response.getWriter();
				out.println(json);

			}

			System.out.println("The result for updateAgentInTickets is "
					+ result);
		}
			break;
		default:
			JSONResponseBuilder responseMessage = new JSONResponseBuilder();
			responseMessage.setResponse("failure");
			responseMessage.setMessage("Action specified is not available");
			Gson gson = new Gson();
			String json = gson.toJson(responseMessage);

			PrintWriter out = response.getWriter();
			out.println(json);

			break;

		}

	}
	private static HashMap<String, String> _getUser() throws JSONException {
		HashMap<String, String> user = new HashMap<String, String>();
		user.put("username", "");
		user.put("password", "");
		final String VCAP_SERVICES = System.getenv("VCAP_SERVICES");
		if (VCAP_SERVICES != null) {
			JSONObject vcap = new JSONObject(VCAP_SERVICES);
			JSONArray sendgrid = (JSONArray) vcap.get("sendgrid");
			JSONObject json = sendgrid.getJSONObject(0);
			JSONObject credentials = json.getJSONObject("credentials");
			String username = credentials.getString("username");
			String password = credentials.getString("password");
			user.put("username", username);
			user.put("password", password);
		}		
		return user;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
