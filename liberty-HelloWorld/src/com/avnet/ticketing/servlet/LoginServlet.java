package com.avnet.ticketing.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// TODO Auto-generated method stub
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
				JSONResponseBuilder responseMessage=new JSONResponseBuilder();
				responseMessage.setResponse("success");
				responseMessage.setMessage("login successfully");
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
			System.out.println("The result for login is "+result);
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
