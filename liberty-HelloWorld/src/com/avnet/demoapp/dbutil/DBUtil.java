package com.avnet.demoapp.dbutil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {

	static DataSource ds;

	static {
		Context ctx;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mydbinstance");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static List<String> getListOfUsers(String username)
			throws SQLException, NamingException {
		Connection conn = ds.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from USER04138.csv"
					+ username);
			while (rs.next()) {
				System.out.println(rs.getString("name"));
				
			}

			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList();
	}

	public static boolean isUser(String username) throws SQLException,
			NamingException {
		Connection conn = ds.getConnection();
		List<String> users=new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from USER04133.mytable"
					+ username);
			while (rs.next()) {
				
				System.out.println(rs.getString("COL1"));
				users.add(rs.getString("COL1"));
			}

			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static List<String> getTodosForUser(String username) {
		// TODO Auto-generated method stub
		return new ArrayList();
	}

	public static void createUser(String username) {
		// TODO Auto-generated method stub

	}

	public static void updateTodos(List<String> todosList, String username) {
		// TODO Auto-generated method stub

	}

}
