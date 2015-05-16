package com.avnet.ticketing.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.avnet.ticketing.DataBeans.TweetsBean;
import com.avnet.ticketing.service.CloudantService;
import com.avnet.ticketing.service.TwitterService;
import com.cloudant.client.api.Database;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;

/**
 * Servlet implementation class CloudantTwitterTest
 */
@WebServlet("/CloudantTwitterServlet")
public class CloudantTwitterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CloudantTwitterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		List<TweetsBean> tweetsBeanList = new ArrayList<TweetsBean>();
		PrintWriter out = response.getWriter();
		Database db = null;

		try {
			// TwitterService service=new TwitterService();
			//
			// String searchKey=request.getParameter("search");
			// String result=service.searchTag(searchKey);
			//
			//
			// List<Map<String, Object>>
			// mapList=service.getTweetsHashMap(result);
			//
			// out.print("result====="+result);
			// out.print("mapList====="+mapList);
			//
			// try
			// {
			// db = CloudantService.getDB();
			// for(int i=0;i<mapList.size();i++)
			// {
			// db.save(mapList.get(i));
			//
			// }
			//
			// }
			// catch(Exception re)
			// {
			// //return new ResponseEntity<String>(re.getMessage(),
			// HttpStatus.INTERNAL_SERVER_ERROR);
			// System.out.println("1=================== ");
			// re.printStackTrace();
			// }
			// System.out.println("SUCCESS");
			//
			//
			//
			//
			//
			JsonArray jsonArray = new JsonArray();
			List<HashMap> allDocs = db.view("_all_docs").query(HashMap.class);
			for (HashMap doc : allDocs) {
				HashMap<String, Object> obj = db.find(HashMap.class,
						doc.get("id") + "");
				JsonObject jsonObject = new JsonObject();
				TweetsBean tweet = new TweetsBean();
				tweet.setBody(obj.get("body").toString());
				tweet.setLink(obj.get("link").toString());
				tweet.setLink(obj.get("preferredUsername").toString());
				tweet.setLink(obj.get("image").toString());

				tweetsBeanList.add(tweet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Gson gson = new Gson();

		out.print(gson.toJson(tweetsBeanList));

	}

}
