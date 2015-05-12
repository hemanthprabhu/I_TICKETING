package com.avnet.ticketing.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.avnet.ticketing.DataBeans.UserDetails;
import com.avnet.ticketing.constant.TicketingAction;
import com.avnet.ticketing.delegate.TicketingServiceDelagate;
import com.avnet.ticketing.util.JSONResponseBuilder;
import com.google.gson.Gson;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		switch(TicketingAction.valueOf(action))
		{
		case login:
		{

			String username=request.getParameter("username");
			String password=request.getParameter("password");
			String role=request.getParameter("role");
			
			UserDetails result=TicketingServiceDelagate.loginValidation(username, password, role);
			if(result.isValid())
			{
				if(result.getRole().equalsIgnoreCase("agent"))
				{

					response.sendRedirect("dashboard/dashboard.jsp");
				}
				else if(result.getRole().equalsIgnoreCase("customer"))
				{

					response.sendRedirect("viewTickets/viewTickets.jsp");
				}
				HttpSession session=request.getSession();  
		        session.setAttribute("userid",result.getUserid());
		        session.setAttribute("role",result.getRole());
		        session.setAttribute("username",result.getName());
				
			}
			else
			{
				
				HttpSession session=request.getSession();  
		        session.setAttribute("loginFailure",true);
		        response.sendRedirect("login.jsp");
		     
//				JSONResponseBuilder responseMessage=new JSONResponseBuilder();
//				responseMessage.setResponse("failure");
//				responseMessage.setMessage("Some internal problem occured");
//				Gson gson=new Gson();
//				String json = gson.toJson(responseMessage);
//
//				PrintWriter out = response.getWriter();
//				out.println(json);
				
			}
			System.out.println("The result for login is "+result);
		}

		break;
		case logout:
		{
			
		
		HttpSession session=request.getSession();  
		session.invalidate();
		response.sendRedirect("login.jsp");
     
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

}
