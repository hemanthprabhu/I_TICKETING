package com.avnet.ticketing.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.json.java.JSON;

/**
 * Servlet implementation class TestDB
 */
@WebServlet("/TestDB")
public class TestDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestDB() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write("Hello");
		String VCAP_SERVICES = System.getenv("VCAP_SERVICES");
	//	response.getWriter().write(JSON.parse(VCAP_SERVICES).toString());
		try {
			Map env = System.getenv();
			  String vcap = (String) env.get("VCAP_SERVICES");
			  System.out.println("hhi"+vcap);
			  System.out.println("fdsfasd");
			  
		InitialContext ctx = new InitialContext();
		javax.sql.DataSource ds = (javax.sql.DataSource) 
		    ctx.lookup("java:comp/env/jdbc/TicketingDatabase");
			Connection conn = ds.getConnection();
			Statement s = conn.createStatement();
		
//			ResultSet rs = s.executeQuery("select * from "+"\"USER01130\""+".\"csv\"");
//			while(rs.next())
//				response.getWriter().write(rs.getString("name"));
			 PreparedStatement updateemp = conn.prepareStatement
				      ("insert into USER01125.T_AGENT(PK_AGENTID,EMAILID,FIRSTNAME,PASSW0RD) values(NEXT VALUE FOR  agentId,?,?,?)");
			 
				      updateemp.setString(1,"hemanthprabhu33@gmail.com");
				      updateemp.setString(2,"hemanth");
				      updateemp.setString(3, "test");
				      updateemp.executeUpdate();
		response.getWriter().flush();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(NamingException ee) {
			ee.printStackTrace();
		}
		/*
		String databaseHost;
		String databaseName;
		int port;
		if (VCAP_SERVICES != null) {
			BasicDBObject obj = (BasicDBObject) JSON.parse(VCAP_SERVICES);
			String thekey = null;
			Set<String> keys = obj.keySet();
			for (String eachkey : keys) {
				if (eachkey.toUpperCase().contains("SQLDB")) {
					thekey = eachkey;
				}
			}
			BasicDBList list = (BasicDBList) obj.get(thekey);
			obj = (BasicDBObject) list.get("0");
			obj = (BasicDBObject) obj.get("credentials");
			databaseHost = (String) obj.get("host");
			databaseName = (String) obj.get("db");
			port = (int) obj.get("port");
			String user = (String) obj.get("username");
			String password = (String) obj.get("password");
			String url = (String) obj.get("jdbcurl");
		} else {
			// Use the jdbcurl or construct your own
			// String databaseUrl = "jdbc:db2://" + databaseHost + ":" + port +
			// "/" + databaseName;
		}
		*/
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
