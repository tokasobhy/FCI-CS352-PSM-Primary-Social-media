package com.FCI.SWE.Controller;

import java.io.BufferedReader;
import java.util.Iterator;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;


import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Models.User;
import com.FCI.SWE.Page.Page;
import com.FCI.SWE.Post.Post;
import com.FCI.SWE.Post.UserPost;
import com.FCI.SWE.ServicesModels.UserEntity;

/**
 * This class contains REST services, also contains action function for web
 * application
 * 
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 *
 */
@Path("/")
@Produces("text/html")
public class UserController {
	/**
	 * Action function to render home page, this function will be executed
	 * using url like this /rest/searchService2
	 * 
	 * @return showUsers
	 */
	@POST
	@Path("/doSearch")
	public Response usersList(@FormParam("uname") String uname){
		String serviceUrl = "http://localhost:8888/rest/searchService2";
		String urlParameters = "uname=" + uname;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		Map<String, Vector<User>> passedUsers = new HashMap<String, Vector<User>>();
		JSONParser parser = new JSONParser();
		try{
			JSONArray array = (JSONArray) parser.parse(retJson);
			Vector<User> users = new Vector <User>();
			for (int i = 0; i < array.size(); i++){
				JSONObject object;
				object = (JSONObject) array.get(i);
				users.add(User.parseUserInfo(object.toJSONString()));
				}
//			for (int i = 0; i < users.size(); i++){
//				System.out.println(users.get(i).getName());
//			}
			passedUsers.put("usersList", users);
			return Response.ok(new Viewable("/jsp/showUsers", passedUsers)).build();
		}catch(ParseException e){
				e.printStackTrace();
			}
		return null;
	}
//-----------------------------------------------------------------------------------------------------
	/**
	 * this function called from home page
	 * uses URL like this
	 * @return Response the make group chat page
	 */
	@POST
	@Path("/getAllUsers")
	public Response getAllFriends() {
		String serviceUrl = "http://localhost:8888/rest/getAllUsersService";
		String urlParameters="chatMaker=" + CurrentUser.user1.getName();
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		Map<String, Vector<User>> passedUsers = new HashMap<String, Vector<User>>();
		JSONParser parser = new JSONParser();
		try{
			JSONArray array = (JSONArray) parser.parse(retJson);
			Vector<User> users = new Vector <User>();
			for (int i = 0; i < array.size(); i++){
				JSONObject object;
				object = (JSONObject) array.get(i);
				users.add(User.parseUserInfo(object.toJSONString()));
				}
			passedUsers.put("usersList", users);
			return Response.ok(new Viewable("/jsp/makeGroupChat", passedUsers)).build();
		}catch(ParseException e){
				e.printStackTrace();
			}
		return null;
	}
	
//-----------------------------------------------------------------------------------------------------
	@GET
	@Path("/signup")
	public Response signUp() {
		return Response.ok(new Viewable("/jsp/register")).build();
	}
//------------------------------------------------------------------------------------------------------
	@GET
	@Path("/message")
	public Response message(){
		return Response.ok(new Viewable("/jsp/sendmessage.jsp")).build();
	}
//------------------------------------------------------------------------------------------------------
	
	@GET
	@Path("/showReplyConversation")
	public Response showReplyConversation() {
		return Response.ok(new Viewable("/jsp/register")).build();
	}
//-------------------------------------------------------------------------------------	
	/**
	 * Action function to render home page of application, home page contains
	 * only signup and login buttons
	 * 
	 * @return enty point page (Home page of this application)
	 */
	@GET
	@Path("/")
	public Response index() {
		return Response.ok(new Viewable("/jsp/entryPoint")).build();
	}

	/**
	 * Action function to render login page this function will be executed using
	 * url like this /rest/login
	 * 
	 * @return login page
	 */
	@GET
	@Path("/login")
	public Response login() {
		if ( CurrentUser.user1.getName()  == null && CurrentUser.user1.getPassword()== null  ){
		return Response.ok(new Viewable("/jsp/login")).build();
		}else{
		//return Response.ok(new Viewable("/jsp/alredyLogged")).build();
			return Response.ok(new Viewable("/jsp/login")).build();
		}
	}
//--------------------------------------------------------------------------------------
	/**
	 * Action function to response to signup request, This function will act as
	 * a controller part and it will calls RegistrationService to make
	 * registration
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided user password
	 * @return Status string
	 */
	@POST
	@Path("/response")
	@Produces(MediaType.TEXT_PLAIN)
	public String response(@FormParam("uname") String uname,
			@FormParam("email") String email, @FormParam("password") String pass) {

		String serviceUrl = "http://localhost:8888/rest/RegistrationService";
		String urlParameters = "uname=" + uname + "&email=" + email
				+ "&password=" + pass;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			// System.out.println(retJson);
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return "Registered Successfully";

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return "Failed";
	}
//---------------------------------------------------------------------------------
//---------------------------------------------------------------------------------
	/**
	 * Action function to response to login request. This function will act as a
	 * controller part, it will calls login service to check user data and get
	 * user from datastore
	 * 
	 * @param uname
	 *            provided user name
	 * @param pass
	 *            provided user password
	 * @return Home page view
	 */
	@POST
	@Path("/home")
	@Produces("text/html")
	public Response home(@FormParam("uname") String uname,
			@FormParam("password") String pass) {
		String urlParameters = "uname=" + uname + "&password=" + pass;

		String retJson = Connection.connect(
				"http://localhost:8888/rest/LoginService", urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");

		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("Failed"))
				return null;
			Map<String, String> map = new HashMap<String, String>();
			User user = User.getUser(object.toJSONString());
			map.put("name", user.getName());
			map.put("email", user.getEmail());
			/*for (java.util.Map.Entry<String, String>entry:map.entrySet()){
				String name = entry.getKey();
				String pass1 = entry.getValue();*/
				CurrentUser.user1.setName(uname);
				CurrentUser.user1.setPassword(pass);
			//}
			
			return Response.ok(new Viewable("/jsp/home", map)).build();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return null;
	}
//-------------------------------------------------------------------------------------
	/**
	 * Action function to response to search request. This function will act as a
	 * controller part, it will calls search service to check user data and make sure
	 * user in datastore
	 * 
	 * @param uname
	 *            provided user name
	 * @return makesure page view
	 */
	@POST
	@Path("/search")
	@Produces("text/html")
	public Response search(@FormParam("uname") String uname) {
			String urlParameters = "uname=" + uname ;
			String retJson = Connection.connect(
					"http://localhost:8888/rest/searchService", urlParameters,
					"POST", "application/x-www-form-urlencoded;charset=UTF-8");
			JSONParser parser = new JSONParser();
			Object obj;
			try {
				obj = parser.parse(retJson);
				JSONObject object = (JSONObject) obj;
				if (object.get("Status").equals("Failed"))
					return null;
				Map<String, String> map = new HashMap<String, String>();
				User user = User.getUser(object.toJSONString());
				map.put("name", user.getName());
				map.put("email", user.getEmail());
				return Response.ok(new Viewable("/jsp/makeure", map)).build();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
	}
//------------------------------------------------------------------------------------------
	/**
	 * Action function to response to search request. This function will act as a
	 * controller part, it will calls search service to check user data and make sure
	 * user in datastore
	 * 
	 * @param uname
	 *            provided user name
	 * @return makesure page view
	 */
	@POST
	@Path("/normalSearch")
	@Produces("text/html")
	public Response normalSearch(@FormParam("u_name") String uname) {
			String urlParameters = "uname=" + uname ;
			String retJson = Connection.connect(
					"http://localhost:8888/rest/searchService", urlParameters,
					"POST", "application/x-www-form-urlencoded;charset=UTF-8");
			JSONParser parser = new JSONParser();
			Object obj;
			try {
				obj = parser.parse(retJson);
				JSONObject object = (JSONObject) obj;
				if (object.get("Status").equals("Failed"))
					return null;
				Map<String, String> map = new HashMap<String, String>();
				User user = User.getUser(object.toJSONString());
				map.put("name", user.getName());
				map.put("email", user.getEmail());
				return Response.ok(new Viewable("/jsp/profile", map)).build();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
	}
	
//------------------------------------------------------------------------------------------
	/**
	 * Action function to response to add request. This function will act as a
	 * controller part, it will calls add service to check user data and put
	 * user into datastore
	 * 
	 * @param uname
	 *            provided user name
	 * @return String
	 */
	@POST
	@Path("/add")
	@Produces("text/html")
	public String add(@FormParam("uname") String uname) {
	String serviceUrl = "http://localhost:8888/rest/AddService";
	String urlParameter = "Sender=" + CurrentUser.user1.getName() + "&Reciever=" + uname +"&Status= unaccepted";
	String retJson2 = Connection.connect(serviceUrl, urlParameter, "POST",
			"application/x-www-form-urlencoded;charset=UTF-8");
	JSONParser parser2 = new JSONParser();
	Object obj2;
	
		try {
			// System.out.println(retJson);
			obj2 = parser2.parse(retJson2);
			JSONObject object2 = (JSONObject) obj2;
			if (object2.get("Status").equals("OK")){
	
				//System.out.println("3");
				return "request has been sent Successfully";
			  }

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}
//------------------------------------------------------------------------------------------
	@POST
	@Path("/Sendmessage")
	@Produces("text/html")
	public String Sendmessage(@FormParam("uname") String uname,@FormParam("message") String message) {
	String serviceUrl = "http://localhost:8888/rest/Messageservice";
	String urlParameter = "Sender=" + CurrentUser.user1.getName() + "&Reciever=" + uname +"&message= " + message;
	String retJson2 = Connection.connect(serviceUrl, urlParameter, "POST",
			"application/x-www-form-urlencoded;charset=UTF-8");
	JSONParser parser2 = new JSONParser();
	Object obj2;
	
		try {
			// System.out.println(retJson);
			obj2 = parser2.parse(retJson2);
			JSONObject object2 = (JSONObject) obj2;
			if (object2.get("Status").equals("OK")){
				//System.out.println("3");
				return "Message has been sent Successfully";
			  }

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}
//------------------------------------------------------------------------------------------
	/**
	 * Action function to response to logout request. This function will act as a
	 * controller part,  data and remove the current user to give the ability another
	 * one to login
	 * @return entryPoint page view
	 */
	@POST
	@Path("/logout")
	public Response logout() {
		if ( CurrentUser.user1.getName()  == null && CurrentUser.user1.getPassword() == null ){
			return Response.ok(new Viewable("/jsp/logoutWithoutLogin")).build();
		}else{
			CurrentUser.user1.setName(null);
			CurrentUser.user1.setPassword(null);
			return Response.ok(new Viewable("/jsp/entryPoint")).build();
		}
	}
//---------------------------------------------------------------------------------------------
	/*@POST
	@Path("/showSearchResult")
	@Produces("text/html")
	public Vector<String> showRequested() {
		return new UserEntity(null, null, null).getFriendsToShow();
	}*/
//---------------------------------------------------------------------------------------------
	/**
	 * Action function to response to accept request. This function will act as a
	 * controller part, it will calls accept service to put the user request 
	 * into friends table in datastore
	 * 
	 * @param uname
	 *            provided user name
	 * @return String
	 */
	@POST
	@Path("/accept")
	@Produces("text/html")
	public String accept(@FormParam("u_name") String uname) {
		//System.out.println(uname);
		String urlParameters = "Sender=" + uname+ "&Reciever=" + CurrentUser.user1.getName() ;

		String retJson = Connection.connect(
				"http://localhost:8888/rest/acceptService", urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");

		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK")){
			   System.out.println("2");
			   return "accepted Successfully";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
//----------------------------------------------------------------------------------------------
	@POST
	@Path("/makeConversation")
	@Produces("text/html")
	public String addtolist(@FormParam("cname") String cname,@FormParam("message") String message,
			@FormParam("members") String members) {
		String urlParameters = "cname=" + cname
				+ "&message=" +message+"&members="+";"+members+";"+ CurrentUser.user1.getName() ;
		String retJson = Connection.connect(
				"http://localhost:8888/rest/makeConversationService", urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser2 = new JSONParser();
		Object obj2;
		
			try {
				// System.out.println(retJson);
				obj2 = parser2.parse(retJson);
				JSONObject object2 = (JSONObject) obj2;
				if (object2.get("Status").equals("OK")){
					//System.out.println("3");
					return "the conversation created successfuly";
				  }

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return null;
		}
//----------------------------------------------------------------------------------
	/**
	 * Action function to response to save post. This function will act as a
	 * controller part, it will calls save post service to put the post request 
	 * into user post table in datastore
	 * 
	 * @param postConrent
	 *            provided the post
	 *  @param hashTag
	 *            provided if the post has a hash tag
	 *  @param timeLineOwner
	 *            provided the time line owner
	 * @return String
	 */
	@POST
	@Path("/savePost1")
	@Produces("text/html")
	public String SavePost1(@FormParam("postContent") String postContent,@FormParam("hashTag") String hashTag,
					@FormParam("membersToShow") String membersToShow,@FormParam("feeling") String feeling,@FormParam("privacy") String privacy,@FormParam("timeLineOwner") String timeLineOwner) {
		if(postContent.equals("")){postContent = "noData";}
		if(hashTag.equals("")){hashTag = "noData";}
		//if (feeling.equals("")){feeling = "noData";}
		//System.out.println(uname);
		String urlParameters = "postOwner=" +CurrentUser.user1.getName()+"&postContent="+postContent+"&hashTag="+hashTag+"&membersToShow="+membersToShow+"&feeling="+feeling+"&privacy="+privacy+"&timeLineOwner="+ timeLineOwner ;

		String retJson = Connection.connect(
				"http://localhost:8888/rest/savePostService1", urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");

		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK")){
			  // System.out.println("2");
			   return "posted Successfully";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
//----------------------------------------------------------------------------------------------------
	/**
	 * Action function to response to save post. This function will act as a
	 * controller part, it will calls save post service to put the post request 
	 * into user post table in datastore
	 * 
	 * @param postConrent
	 *            provided the post
	 *  @param hashTag
	 *            provided if the post has a hash tag
	 *  @param timeLineOwner
	 *            provided the time line owner
	 * @return String
	 */
	@POST
	@Path("/savePost2")
	@Produces("text/html")
	public String SavePost2(@FormParam("postContent") String postContent,@FormParam("hashTag") String hashTag,
					@FormParam("membersToShow") String membersToShow,@FormParam("feeling") String feeling,@FormParam("privacy") String privacy,@FormParam("timeLineOwner") String timeLineOwner) {
		if(postContent.equals("")){postContent = "noData";}
		if(hashTag.equals("")){hashTag = "noDAta";}
		
		String urlParameters = "postOwner=" +CurrentUser.user1.getName()+"&postContent="+postContent+"&hashTag="+hashTag+"&membersToShow="+membersToShow+"&feeling="+"noData"+"&privacy="+privacy+"&timeLineOwner="+ timeLineOwner ;

		String retJson = Connection.connect(
				"http://localhost:8888/rest/savePostService2", urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");

		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK")){
			  // System.out.println("2");
			   return "posted Successfully";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
//----------------------------------------------------------------------------------------------
	/**
	 * this is social Service, this service will be called to make createPage process
	 * also will check posts data and returns String
	 * @param pageName
	 *            provided the page name
	 *  @param pageOwner
	 *            provided if the page owner
	 *  @param category
	 *            provided the page category
	 *  @param type
	 *            provided the page type
	 * @return Status provided Status
	 */
	@POST
	@Path("/createPage")
	public String createPage(@FormParam("pageName") String pageName,@FormParam("category") String category,@FormParam("type") String type) {
		String urlParameters ="pageName="+pageName+"&pageOwner="+CurrentUser.user1.getName()+"&category="+category+"&type="+type;
		
		String retJson = Connection.connect(
				"http://localhost:8888/rest/createPageService", urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");
		
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK")){
			  // System.out.println("2");
			   return "The page created Successfully";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
//--------------------------------------------------------------------------------------------------
	/**
	 * this is social Service, this service will be called to search for Page process
	 * also will check posts data and returns String
	 * @param pageName
	 *            provided the page name
	 * @return Status provided Status
	 */
	@POST
	@Path("/pageSearch")
	@Produces("text/html")
	public Response pageSearch(@FormParam("pageName") String pageName){
		String urlParameters ="pageName=" + pageName;
		String retJson = Connection.connect("http://localhost:8888/rest/pageSearchService", urlParameters , "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("Failed"))
				return null;
			Map<String, String> map = new HashMap<String, String>();
			Page page = Page.getPage(object.toJSONString());
			map.put("pageName", page.getPageName());
			map.put("PageOwner", page.getPageOwner());
			map.put("PageOwner", page.getCategory());
			map.put("Type", page.getType());
			map.put("number",""+ page.getNumOfLikes());
			return Response.ok(new Viewable("/jsp/Page", map)).build();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
//-----------------------------------------------------------------------------------------------------
	/**
	 * this is social Service, this service will be called to increases likes of the page  process
	 * also will check pages data 
	 * @param pageName
	 *            provided the page name
	 * @return Status provided Status
	 */
	@POST
	@Path("/pageLike")
	@Produces("text/html")
	public String pageLike(@FormParam("pageName") String pageName){
		String urlParameters ="likerName="+CurrentUser.user1.getName()+"&pageName=" + pageName;
		String retJson = Connection.connect("http://localhost:8888/rest/pageLikeService", urlParameters , "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if ((object.get("Status").toString()).equals("OK")){
			  // System.out.println("2");
			   return null;
			}else if ((object.get("Status").toString()).equals("koba")){
				return "you liked this page before";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
//----------------------------------------------------------------------------------------------------
	/**
	 * this is social Service, this service will be called to increases likes of the page  process
	 * also will check pages data 
	 * @param pageName
	 *            provided the page name
	 * @return Status provided Status
	 */
	@POST
	@Path("/likePost")
	@Produces("text/html")
	public String likePost(@FormParam("id") String id){
		System.out.println("controller = "+id);
		String urlParameters ="id="+id+"&liker="+CurrentUser.user1.getName();
		String retJson = Connection.connect("http://localhost:8888/rest/likePostService", urlParameters , "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if ((object.get("Status").toString()).equals("OK")){
			  // System.out.println("2");
			   return null;
			}else if ((object.get("Status").toString()).equals("koba")){
				return "you liked this post before";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
//----------------------------------------------------------------------------------------------------
	/**
	 * 
	 * @return
	 */
	@POST
	@Path("/getAllPosts")
	public Response getAllPosts(){
		String serviceUrl = "http://localhost:8888/rest/getAllPostsService";
		//System.out.println("1111111");
		String retJson = Connection.connect(serviceUrl,"", "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		//System.out.println("1222");
		JSONParser parser = new JSONParser();
		Map<String, Vector<UserPost>> passedPosts = new HashMap<String, Vector<UserPost>>();
		try{
			JSONArray array = (JSONArray) parser.parse(retJson);
			Vector<UserPost> posts = new Vector <UserPost>();
			for (int i = 0; i < array.size(); i++){
				JSONObject object;
				object = (JSONObject) array.get(i);
				posts.add((UserPost) UserPost.parsePostInfo(object.toJSONString()));
				System.out.println("id" + object.get("id"));
				}
			passedPosts.put("postsList", posts);
			return Response.ok(new Viewable("/jsp/posts", passedPosts)).build();
		}catch(ParseException e){
				e.printStackTrace();
			}
		return null;
	}
//-------------------------------------------------------------------------Get Someone posts
	/**
	 * this calling service to get all user posts
	 * @param timeline owner 
	 * @return response posts page
	 */
	@POST
	@Path("/getSomeonePosts")
	public Response getSomeonePosts(@FormParam("timeLineOwner") String timeLineOwner){
		String serviceUrl = "http://localhost:8888/rest/getSomeonePostsService";
		String urlParameters ="timeLineOwner="+timeLineOwner;
		//System.out.println("timeLineOwner from controller="+timeLineOwner);
		String retJson = Connection.connect(serviceUrl,urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		
		JSONParser parser = new JSONParser();
		Map<String, Vector<UserPost>> passedPosts = new HashMap<String, Vector<UserPost>>();
		try{
			JSONArray array = (JSONArray) parser.parse(retJson);
			Vector<UserPost> posts = new Vector <UserPost>();
			for (int i = 0; i < array.size(); i++){
				JSONObject object;
				object = (JSONObject) array.get(i);
				posts.add((UserPost) UserPost.parsePostInfo(object.toJSONString()));
				}
			//System.out.println(posts.size());
			passedPosts.put("postsList", posts);
			return Response.ok(new Viewable("/jsp/posts", passedPosts)).build();
		}catch(ParseException e){
				e.printStackTrace();
			}
		return null;
	}
//----------------------------------------------
	/**
	 * this calling service to get all page posts
	 * @param timeline owner 
	 * @return response posts page
	 */
	@POST
	@Path("/getPagePosts")
	public Response getPagePosts(@FormParam("timeLineOwner") String timeLineOwner){
		String serviceUrl = "http://localhost:8888/rest/getPagePostsService";
		String urlParameters ="timeLineOwner="+timeLineOwner;
		//System.out.println("timeLineOwner from controller="+timeLineOwner);
		String retJson = Connection.connect(serviceUrl,urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		
		JSONParser parser = new JSONParser();
		Map<String, Vector<UserPost>> passedPosts = new HashMap<String, Vector<UserPost>>();
		try{
			JSONArray array = (JSONArray) parser.parse(retJson);
			Vector<UserPost> posts = new Vector <UserPost>();
			for (int i = 0; i < array.size(); i++){
				JSONObject object;
				object = (JSONObject) array.get(i);
				posts.add((UserPost) UserPost.parsePostInfo(object.toJSONString()));
				}
			//System.out.println(posts.size());
			passedPosts.put("postsList", posts);
			return Response.ok(new Viewable("/jsp/posts", passedPosts)).build();
		}catch(ParseException e){
				e.printStackTrace();
			}
		return null;
	}
//---------------------------------------------GetHashTag
	/**
	 * 
	 * @param gethashtag
	 * @return
	 */

		@POST
		@Path("/GetHashTag")
		@Produces("text/html")
		public Response GetHashTag(@FormParam("gethashtag") String gethashtag) {
			String urlParameters = "gethashtag=" + gethashtag;

			String retJson = Connection.connect(
					"http://localhost:8888/rest/GetHashTagService", urlParameters,
					"POST", "application/x-www-form-urlencoded;charset=UTF-8");
			System.out.println("controller");
			JSONParser parser = new JSONParser();
			Object obj;
			try {
				obj = parser.parse(retJson);
				JSONObject object = (JSONObject) obj;
				if (object.get("Status").equals("Failed"))
					return null;
				Map<String , Integer> map = new HashMap<String , Integer>();
				map.put("hashtag", Integer.parseInt(object.get("gethashtag").toString()));
			//System.out.println("Çhhhhhhhhhhhh"+object.get("gethashtag").toString());
				return Response.ok(new Viewable("/jsp/GetHashTag", map)).build();
				
			} catch (ParseException e) {
				e.printStackTrace();
			}

			/*
			 * UserEntity user = new UserEntity(uname, email, pass);
			 * user.saveUser(); return uname;
			 */
			return null;
		}
		
		
		//---------------------------------------------GetTimeLine
		/**
		 * 
		 * @param gettimeline
		 * @return
		 */

			@POST
			@Path("/GetTimeLine")   
			@Produces("text/html")
			public String GitTimeLine(@FormParam("gettimeline") String gettimeline) {
				String urlParameters = "gettimeline=" + gettimeline;
				System.out.println("yaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
				String retJson = Connection.connect(
						"http://localhost:8888/rest/GitTimeLineService", urlParameters,
						"POST", "application/x-www-form-urlencoded;charset=UTF-8");
				JSONParser parser = new JSONParser();
				Object obj;
				try {
					obj = parser.parse(retJson);
					JSONObject object = (JSONObject) obj;
					if (object.get("Status").equals("Failed"))
						return null;
				
		ArrayList<String> map =( ArrayList<String>)  object.get("gettimeline");
		System.out.println("yaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa2");
		
		for(int j=0;j<map.size();j++)
		{
			
			System.out.println(map.get(j));
			
		}
		
		String s="";
		for(int i=0;i<map.size();i++)
		{
			
			 s+="<p>"+ map.get(i) +" </p>";
				     
				   
			
		}
		return s;
		
					
				} catch (ParseException e) {
					e.printStackTrace();
				}

				
				return null;
			}
			
	//----------------------------------------------------------GetMostTenHashTag
		/**
		 * 	
		 * @return
		 */

			@POST
			@Path("/GetMostTenHashTag")   
			@Produces("text/html")
			public String GetMostTenHashTag() {
				String urlParameters = "";
			
				String retJson = Connection.connect(
						"http://localhost:8888/rest/GetMostTenHashTagService", urlParameters,
						"POST", "application/x-www-form-urlencoded;charset=UTF-8");
				
				
				
			
				JSONParser parser = new JSONParser();
				Object obj;
				try {
					obj = parser.parse(retJson);
					JSONObject object = (JSONObject) obj;
					if (object.get("Status").equals("Failed"))
						return null;
				
					Map<String ,Integer> map =( Map<String ,Integer>)  object.get("gettenmosthashtag");
		
		//Map<String ,Integer>map =ob
		
		String s="";
		int i=0;
	   Iterator<Map.Entry<String, Integer>> mapIterator = map.entrySet().iterator();
		while (mapIterator.hasNext()) {
			i++;
		    Map.Entry<String, Integer> entry = mapIterator.next();
		    
		   s+="HashTag= "+entry.getKey() + "----------------------> " +"number #= "+ entry.getValue()  +"</br>";
		   
		   if(i==10){
			   
			   break;
			   
			   
		   }
		}   

		 return s;
		
					
				} catch (ParseException e) {
					e.printStackTrace();
				}

				
				return null;
			}
			
			



}