package com.avnet.ticketing.service;

import java.io.BufferedReader;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ibm.ws.xs.nosql.api.BasicDBList;
import com.ibm.ws.xs.nosql.api.BasicDBObject;
import com.ibm.ws.xs.nosql.api.util.JSON;

public class TwitterService {

	private String host;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String user;
	private String password;
	
	public TwitterService()
	{
		String VCAP_SERVICES = System.getenv("VCAP_SERVICES");
		//response.getWriter().write(JSON.parse(VCAP_SERVICES).toString());
		System.out.println("VCAP_SERVICES  "+VCAP_SERVICES.toString());
		//		if (VCAP_SERVICES != null) {
		BasicDBObject obj = (BasicDBObject) JSON.parse(VCAP_SERVICES);
	//	System.out.println("obj"+obj);
		String thekey = null;
		Set<String> keys = obj.keySet();
		for (String eachkey : keys) {
			if (eachkey.toLowerCase().contains("twitterinsights")) {
				thekey = eachkey;
			}
		}

		BasicDBList list = (BasicDBList) obj.get(thekey);
		System.out.println("list"+list);
		obj = (BasicDBObject) list.get("0");
		obj = (BasicDBObject) obj.get("credentials");

		String host = (String) obj.get("host");

		String user = (String) obj.get("username");
		String password = (String) obj.get("password");
		this.host=host;
		this.user=user;
		this.password=password;
		setHost(host);
		setUser(user);
		setPassword(password);
	}
//	public String searchData(String searchString)
//	{
//
//		System.out.println("host"+host);
//		
//        String res="json is";
//
//        System.out.println(getUser() +"cred"+ getPassword());
//        
//		try
//		{
//		         String url = "https://cdeservice.mybluemix.net/api/v1/messages/search?q="+searchString;
//				
//				//String url = "https://cdeservice.eu-gb.mybluemix.net/api/v1/messages/search?q="+searchString;
//			
//			//String url = getHost()+"/api/v1/messages/search?q="+searchString;
//			
//		         String userpass = getUser()+":"+getPassword();
//		         System.out.println("userpass"+userpass);
//		         String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
//		         
//		         
//		         URL myurl = null;
//				try {
//					myurl = new URL(url);
//					
//				} catch (MalformedURLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		         URLConnection uc = null;
//				try {
//					uc = myurl.openConnection();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		 
//		         uc.setRequestProperty ("Authorization", basicAuth);
//		 
//		         InputStream ins = null;
//				try {
//					ins = uc.getInputStream();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		         InputStreamReader isr = new InputStreamReader(ins);
//		         BufferedReader in = new BufferedReader(isr);
//		 
//		         String inputLine;
//		         try {
//					while ((inputLine = in.readLine()) != null)
//					 {
//					     System.out.println(inputLine);
//					     res+=inputLine;
//					 }
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		         try {
//					in.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		         return res;
//		     }
//	
//	
//	public  String getResult() {
//		
//		
//		String username=getUser();
//		String password=getPassword();
//		String output="no result";
//	  try {
// 
//		 
//	//	String urlloc="https://"+username+":"+password+"@cdeservice.mybluemix.net:443/api/v1/messages/search?q=vijay&from=1&size=5";
//		String urlloc="https://"+username+":"+password+"@cdeservice.mybluemix.net/api/v1/messages/search?q=vijay&from=1&size=5";
//	
//		
//		System.out.println("urlloc"+urlloc);
//		URL url = new URL(urlloc);
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		conn.setRequestMethod("GET");
//		conn.setRequestProperty("Accept", "application/json");
// 
//		if (conn.getResponseCode() != 200) {
//			throw new RuntimeException("Failed : HTTP error code : "
//					+ conn.getResponseCode());
//		}
// 
//		BufferedReader br = new BufferedReader(new InputStreamReader(
//			(conn.getInputStream())));
// 
//		System.out.println("Output from Server .... \n");
//		while ((output = br.readLine()) != null) {
//			System.out.println(output);
//		}
// 
//		conn.disconnect();
// 
//	  } catch (MalformedURLException e) {
// 
//		e.printStackTrace();
// 
//	  } catch (IOException e) {
// 
//		e.printStackTrace();
// 
//	  }
// return output;
//	}
//	
	
//	public String searchTag(String searchKey)
//	{
//        String inputLine="";
//		 String url = "https://cdeservice.eu-gb.mybluemix.net/api/v1/messages/count?q="+searchKey;
// 
//		 String user = "cd4960c2f6e8bb3862d493a072625b59";
//         String pass = "FPZnZR5oII";
//         
//         String userpass = user + ":" + pass;
//      //   String userpass = getUser() + ":" + getPassword();
//         System.out.println("userpass============"+userpass);
//         String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
//         try
//         {
//        	  URL myurl = new URL(url);
//              URLConnection uc = myurl.openConnection();
//      
//              uc.setRequestProperty ("Authorization", basicAuth);
//      
//              InputStream ins = uc.getInputStream();
//              InputStreamReader isr = new InputStreamReader(ins);
//              BufferedReader in = new BufferedReader(isr);
//      
//      
//              while ((inputLine = in.readLine()) != null)
//              {
//                 // System.out.println(inputLine);
//                  inputLine+=inputLine;
//              }
//              in.close();
//              System.out.println("inputLine*************"+inputLine);
//         }
//         catch(Exception e)
//         {
//        	 e.printStackTrace();
//        	 System.out.println("ss");
//         }
//       return inputLine;
//     }
//	
	
	

	public String searchTag(String searchKey)
	{
        String inputLine=null;
		 String url = "https://"+getHost()+"/api/v1/messages/search?q="+searchKey;
      //   String user = "cd4960c2f6e8bb3862d493a072625b59";
      //   String pass = "FPZnZR5oII";
         String result="";

 
         String userpass = getUser() + ":" + getPassword();
         System.out.println("userpass============"+userpass);
         String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
         try
         {
        	  URL myurl = new URL(url);
              URLConnection uc = myurl.openConnection();
      
              uc.setRequestProperty ("Authorization", basicAuth);
      
              InputStream ins = uc.getInputStream();
              InputStreamReader isr = new InputStreamReader(ins);
              BufferedReader in = new BufferedReader(isr);
      
      
              while ((inputLine = in.readLine()) != null)
              {
            	  result+=inputLine;
              }
              in.close();
              System.out.println("a*************"+result);
         }
         catch(Exception e)
         {
        	 e.printStackTrace();
        	 System.out.println("ss");
         }
       return result;
     }
	
	
	public List<Map<String, Object>> getTweetsHashMap(String tweetsJsonObj)
	{
		System.out.println("kamal==="+tweetsJsonObj);

			List<Map<String, Object>> mapList = new ArrayList<>();
			 JSONObject jsonObj;
			try {
				jsonObj = new JSONObject(tweetsJsonObj);
			
				 final JSONArray tweets = jsonObj.getJSONArray("tweets");
				 System.out.println("tweets"+tweets);
				    for(int i=0;i<tweets.length();i++)
				    {
				    //	 System.out.println(geodata.get(i));
				    	 JSONObject tweet = (JSONObject)tweets.get(i);
				    	
				    	 JSONObject message = (JSONObject)tweet.get("message");
				    	

						 System.out.println("message"+message);

						//message.get("body");
				    	 System.out.println(message.get("body"));
				    	 
				    	 
				    	 Map<String, Object> map =new HashMap<String, Object>();
				    	long id = System.currentTimeMillis();
				    	 map.put("_id",  id+"");

				    	 map.put("body", message.get("body"));
				    	 map.put("link", message.get("link"));
				 		map.put("creation_date", new Date().toString());
				 		
				 		
				    	 JSONObject actor = (JSONObject)message.get("actor");
				    	 map.put("image", actor.get("image"));
				    	 map.put("preferredUsername", actor.get("preferredUsername"));

				    	 

				 		mapList.add(map);

				    }
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return mapList;
	}


}
