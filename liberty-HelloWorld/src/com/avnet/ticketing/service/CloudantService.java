package com.avnet.ticketing.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



public class CloudantService {
	
	private static CloudantClient cloudant = null;
	private static Database db = null;
	private static String databaseName = "twitter_tweets";
	private static String user = null;
	private static String password = null;
	private static String host = null;

	
	 
	private static void initClient() {
		
		if ( cloudant == null ) {
			synchronized (CloudantService.class) {
				if ( cloudant != null ) {
					return;
				}				
				cloudant = createClient();			
				
			}// end synchronized
		}
	}
    
	private static CloudantClient createClient() {

		// VCAP_SERVICES is a system environment variable
		// Parse it to obtain the  NoSQL DB connection info
		String VCAP_SERVICES = System.getenv("VCAP_SERVICES");
		String serviceName = null;

    	if (VCAP_SERVICES != null) {

			// parse the VCAP JSON structure
			JsonObject obj =  (JsonObject) new JsonParser().parse(VCAP_SERVICES);
			Entry<String, JsonElement> dbEntry = null;
			Set<Entry<String, JsonElement>> entries = obj.entrySet();
			// Look for the VCAP key that holds the cloudant no sql db information
			for (Entry<String, JsonElement> eachEntry : entries) {				
				if (eachEntry.getKey().equals("cloudantNoSQLDB")) {
					dbEntry = eachEntry;
					break;
				}
			}
			if (dbEntry == null) {			
				throw new RuntimeException("Could not find cloudantNoSQLDB key in VCAP_SERVICES env variable");    					
			}

			obj =(JsonObject) ((JsonArray)dbEntry.getValue()).get(0);		
			serviceName = (String)dbEntry.getKey();
			System.out.println("Service Name - "+serviceName);

			obj = (JsonObject) obj.get("credentials");

			user = obj.get("username").getAsString();
			password = obj.get("password").getAsString();
			host = obj.get("host").getAsString();

		}
		else {
			throw new RuntimeException("VCAP_SERVICES not found");
		}

		try {
			return new CloudantClient(user, user, password);
		}
		catch(org.lightcouch.CouchDbException e) {
			throw new RuntimeException("Unable to connect to repository", e);
		}
	}

	
	public static Database getDB() {
		
		if ( cloudant == null ) {
			initClient();
		}
		
		if(db == null)
		{
			try {
				
				db = cloudant.database(databaseName, true);
			}
			catch(Exception e) {

				throw new RuntimeException("DB Not found", e);
			}
		}
		return db;
	}
	
	public static String getUser()
	{
		return user;
	}
	
	public static String getPassword()
	{
		return password;
	}
	
	public static String getHost()
	{
		return host;
	}
	
	public static String getDatabaseName()
	{
		return databaseName;
	}
	
	private CloudantService() {
	    	
	 }
	
	
//
//	public void insertTweetsInCloudant(String tweetsJsonObj)
//	{
//
//		List<String>  body=new ArrayList<String>();
//		Map<String, Object> map = new HashMap<String, Object>();
//
//		 JSONObject jsonObj;
//		try {
//			jsonObj = new JSONObject(tweetsJsonObj);
//			 final JSONArray tweets = jsonObj.getJSONArray("tweets");
//			 System.out.println("tweets"+tweets);
//			    for(int i=0;i<tweets.length();i++)
//			    {
//			    //	 System.out.println(geodata.get(i));
//			    	 JSONObject tweet = (JSONObject)tweets.get(i);
//			    	
//			    	 JSONObject message = (JSONObject)tweet.get("message");
//			    	
//
//					 System.out.println("message"+message);
//
//					//message.get("body");
//			    	 System.out.println(message.get("body"));
//			    	 
//			    	 body.add(message.get("body").toString());
//			    	 
//			    	long id = System.currentTimeMillis();
//			    	 map.put("_id",  id+"");
//
//			    	 map.put("body", message.get("body"));
//			    	 map.put("link", message.get("link"));
//			 		map.put("creation_date", new Date().toString());
//
//
//			    }
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		db = getDB();
//		db.save(map);	
//		System.out.println("completed======================");
//		
//			
//	}
//	
	
}

