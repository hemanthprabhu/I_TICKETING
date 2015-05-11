package com.avnet.ticketing.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
public class DBUtil {
public static DataSource getDataSource() 
{
	InitialContext ctx=null;
	DataSource ds = null;
	try {
		ctx = new InitialContext();
		ds = (javax.sql.DataSource) 
		    ctx.lookup("java:comp/env/jdbc/TicketingDatabase");
	} catch (NamingException e) {
		//throw e;
	}
	return ds;
}
}
