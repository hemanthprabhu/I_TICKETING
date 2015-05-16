package com.avnet.ticketing.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ibm.websphere.objectgrid.ClientClusterContext;
import com.ibm.websphere.objectgrid.ObjectGrid;
import com.ibm.websphere.objectgrid.ObjectGridManager;
import com.ibm.websphere.objectgrid.ObjectGridManagerFactory;
import com.ibm.websphere.objectgrid.ObjectMap;
import com.ibm.websphere.objectgrid.Session;
import com.ibm.websphere.objectgrid.security.config.ClientSecurityConfiguration;
import com.ibm.websphere.objectgrid.security.config.ClientSecurityConfigurationFactory;
import com.ibm.websphere.objectgrid.security.plugins.builtins.UserPasswordCredentialGenerator;

/**
 * Servlet implementation class Sample
 */
@WebServlet("/Sample")
public class Sample extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sample() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Session ogSession = null;
 		Map<String, String> env = System.getenv();
 		String vcap=env.get("VCAP_SERVICES");
 		
 		String username=null;
 		String password=null;
 		String endpoint=null;
 		String gridName=null;
 			
        boolean foundService=false;
        if(vcap==null) {
        	System.out.println("No VCAP_SERVICES found");
        } else {
            try {
            	JSONObject obj = new JSONObject(vcap);
                String[] names=JSONObject.getNames(obj);
                if (names!=null) {
					for (String name:names) {
						System.out.println("servic ename is "+name );
                    	if (name.startsWith("Data")) {
             				JSONArray val = obj.getJSONArray(name);
             				JSONObject serviceAttr = val.getJSONObject(0);
             				JSONObject credentials = serviceAttr.getJSONObject("credentials");
             				username = credentials.getString("username");
             				password = credentials.getString("password");             							  
             				endpoint=credentials.getString("catalogEndPoint");
             				gridName= credentials.getString("gridName");
             				System.out.println("Found configured username: " + username);
             				System.out.println("Found configured password: " + password);
             				System.out.println("Found configured endpoint: " + endpoint);
             				System.out.println("Found configured gridname: " + gridName);		
             				foundService = true;
             				break;
             			}                                	                                 	                                 	                                         
                    }
				}
			} catch(Exception e) {}
 		}
        
 		if(!foundService) {
   			System.out.println("Did not find WXS service, using defaults");
 		}
 		
 		try {
 			
			ObjectGridManager ogm = ObjectGridManagerFactory
					.getObjectGridManager();
			ClientSecurityConfiguration csc=null;
			csc=ClientSecurityConfigurationFactory.getClientSecurityConfiguration();
			csc.setCredentialGenerator(new UserPasswordCredentialGenerator(username,password));
			csc.setSecurityEnabled(true);
			
			ClientClusterContext ccc = ogm
					.connect(endpoint, csc, null);
	
			ObjectGrid clientGrid = ogm.getObjectGrid(ccc, gridName);
			ogSession = clientGrid.getSession();
			
 		} catch(Exception e) {
 			System.out.println("Failed to connect to grid!");
 			e.printStackTrace();
 		}


		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
				
			// Use the getMap() method to return a ObjectMap obejct.
			// Once we have this object, we will be able to perform
			// key-value operations on the map.
			ObjectMap map=ogSession.getMap("sample.NONE.P");
			
		    String key = "userid";		
			
			Object retrievedValue;
			  map.upsert(key,"sampledata");
			  retrievedValue=map.get(key);
			  System.out.println("Data cache data is "+retrievedValue.toString());
			
			  
			
			
			  
			  response.getWriter().write("[PUT]");
			  
			
		} catch(Exception e) {
			System.out.println("Failed to perform operation on map");
			e.printStackTrace();
		}
 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
