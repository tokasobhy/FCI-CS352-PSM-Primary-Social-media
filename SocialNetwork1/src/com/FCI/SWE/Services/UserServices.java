package com.FCI.SWE.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.lang.Object;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Controller.CurrentUser;
import com.FCI.SWE.Page.Page;
import com.FCI.SWE.Post.CustomPost;
import com.FCI.SWE.Post.PagePost;
import com.FCI.SWE.Post.Post;
import com.FCI.SWE.Post.PrivatePost;
import com.FCI.SWE.Post.PublicPost;
import com.FCI.SWE.Post.UserPost;
import com.FCI.SWE.ServicesModels.UserEntity;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
//import com.google.appengine.repackaged.com.google.common.base.Flag.Boolean;

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
@Produces(MediaType.TEXT_PLAIN)
public class UserServices {
	
	
	/*@GET
	@Path("/index")
	public Response index() {
		return Response.ok(new Viewable("/jsp/entryPoint")).build();
	}*/
	
	/*@POST
	@Path("/SearchService")
	public String searchFriend(@FormParam("uname") String uname){
		
	}*/


		/**
	 * Registration Rest service, this service will be called to make
	 * registration. This function will store user data in data store
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided password
	 * @return Status json
	 */
	@POST
	@Path("/RegistrationService")
	public String registrationService(@FormParam("uname") String uname,
			@FormParam("email") String email, @FormParam("password") String pass) {
		UserEntity user = new UserEntity(uname, email, pass);
		user.saveUser();
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		System.out.print("jtes"+object.toString());
		return object.toString();
		
	}
//----------------------------------------
	/**
	 * Add Rest Service, this service will be called to make add request process
	 * also will check user data and returns String
	 * @param Sender provided Sender name
	 * @param Reciever provided Reciever name
	 * @return Status provided Status
	 */
	@POST
	@Path("/AddService")
	public String AddService(@FormParam("Sender") String Sender,
			@FormParam("Reciever") String Reciever, @FormParam("Status") String Status) {
		UserEntity user2 = new UserEntity(Sender, Reciever, Status);
		user2.saveRequest();
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();
	}	
//--------------------------------------------------
	/**
	 * Login Rest Service, this service will be called to make login process
	 * also will check user data and returns new user from datastore
	 * @param uname provided user name
	 * @param pass provided user password
	 * @return user in json format
	 */
	@POST
	@Path("/LoginService")
	public String loginService(@FormParam("uname") String uname,
			@FormParam("password") String pass) {
		JSONObject object = new JSONObject();
		UserEntity user = UserEntity.getUser(uname, pass);
		if (user == null) {
			object.put("Status", "Failed");

		} else {
			object.put("Status", "OK");
			object.put("name", user.getName());
			object.put("email", user.getEmail());
			object.put("password", user.getPass());
			object.put("id", user.getId());
		}
		return object.toString();

	}
//---------------------------------------------------------------------------------------------------------
	/**
	 * search Rest Service, this service will be called to make search process
	 * also will check user data and returns new user from datastore
	 * @param uname provided user name
	 */
	@POST
	@Path("/searchService")
	public String searchService(@FormParam("uname") String uname) {
		JSONObject object = new JSONObject();
		UserEntity user = UserEntity.getUser(uname);
		if (user == null) {
			object.put("Status", "Failed");

		} else {
			object.put("Status", "OK");
			object.put("name", user.getName());
			object.put("email", user.getEmail());
			object.put("password", user.getPass());
			object.put("id", user.getId());
		}
		return object.toString();
	}
//---------------------------------------------------------------------------------------------------------------
	/**
	 * accept Rest Service, this service will be called to make acceptance process
	 * @param uname provided Sender name
	 * @param Reciever provided Reciever name
	 * @return String
	 */
	@Path("/acceptService")
	public String acceptService(@FormParam("Sender") String uname,@FormParam("Reciever") String Reciever) {
		JSONObject object = new JSONObject();
		boolean user = UserEntity.getAndAcceptFriend(uname,Reciever);
		if (user == true) {
			object.put("Status", "OK");
		//	System.out.println("1");
		} else {
			object.put("Status", "Failed");
				}
		return object.toString();
	}
//----------------------------------------------------------------------------------------------------------
	/**
	 * search Rest Service, this service will be called to make search process
	 * also will check user data and returns new user from datastore
	 * @param uname provided user name
	 */
	@POST
	@Path("/searchService2")
	public String searchService2(@FormParam("uname") String uname) {
		Vector<UserEntity> users = UserEntity.searchUser(uname);
		org.json.simple.JSONArray returnedJson = new org.json.simple.JSONArray();
		for (UserEntity user : users){
			JSONObject object = new JSONObject();
			object.put("id", user.getId());
			object.put("name", user.getName());
			object.put("email", user.getEmail());
			returnedJson.add(object);
		}
		return returnedJson.toString();
	   }
//---------------------------------------------------------------------------------------------------------
	/**
	 * get All Users Rest Service, this service will be called to make load process
	 * also will check friends data and returns friends from datastore
	 * @param 
	 * returns jison object
	 */
	@POST
	@Path("/getAllUsersService")
	public String getAllFriendsService(@FormParam("chatMaker") String chatMaker) {
		Vector<UserEntity> users = UserEntity.getUsers(chatMaker);
		org.json.simple.JSONArray returnedJson = new org.json.simple.JSONArray();
		for (UserEntity user : users){
			JSONObject object = new JSONObject();
			object.put("id", user.getId());
			object.put("name", user.getName());
			object.put("email", user.getEmail());
			returnedJson.add(object);
		}
		return returnedJson.toString();
	}
//--------------------------------------------------------------------------------------------------
	/**
	 * makeConversation Rest Service, this service will be called to make makeConversation process
	 * @param uname provided user name
	 * @param pass provided user password
	 * @return user in json format
	 */
	@POST
	@Path("/makeConversationService")
	public String makeConversationService(@FormParam("cname") String cname,
			@FormParam("message") String message,@FormParam("members") String members) {
		JSONObject object = new JSONObject();
		UserEntity user2 = new UserEntity(cname, message, members);
		user2.makeConv();
		
		object.put("Status", "OK");
		return object.toString();
	}
//-----------------------------------------------------------------------------------------------------
	/**
	 * Add Rest Service, this service will be called to make add request process
	 * also will check user data and returns String
	 * @param Sender provided Sender name
	 * @param Reciever provided Reciever name
	 * @return Status provided Status
	 */
	@POST
	@Path("/Messageservice")
	public String Messageservice(@FormParam("Sender") String Sender,
			@FormParam("Reciever") String Reciever, @FormParam("message") String message) {
		System.out.println(message);
		UserEntity user2 = new UserEntity(Sender, Reciever, message);
		user2.savemessage();
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();
	}
//----------------------------------------------------------------------------------
	/**
	 * Add Rest Service, this service will be called to make add post process
	 * also will check posts data and returns String
	 * @param postConrent
	 *            provided the post
	 *  @param hashTag
	 *            provided if the post has a hash tag
	 *  @param timeLineOwner
	 *            provided the time line owner
	 * @return Status provided Status
	 */
	@POST
	@Path("/savePostService1")
	public String savePostService1(@FormParam("postOwner") String postOwner,@FormParam("postContent") String postContent,@FormParam("hashTag") String hashTag,
					@FormParam("membersToShow") String membersToShow,@FormParam("feeling") String feeling,@FormParam("privacy") String privacy,@FormParam("timeLineOwner") String timeLineOwner) {
		/*System.out.println("postOwner= "+postOwner);
		System.out.println("postContent="+postContent);
		System.out.println("hashTag= "+hashTag);
		System.out.println("membersToShow = "+membersToShow);
		System.out.println("privacy = "+privacy);
		System.out.println("timeLineOwner"+timeLineOwner);*/
		if (privacy.equals("PublicPost")){
			Post p = new PublicPost(postOwner,postContent,0,0,hashTag,"",new UserPost(timeLineOwner,feeling));
			p.writePost();
		}else if (privacy.equals("PrivatePost")){
			Post p = new PrivatePost(postOwner,postContent,0,0,hashTag,"",new UserPost(timeLineOwner,feeling));
			p.writePost();
		}else if(privacy.equals("CustomPost")){
			Post p = new CustomPost(postOwner,postContent,0,0,hashTag,membersToShow,new UserPost(timeLineOwner,feeling));
			p.writePost();
		}
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();
	}
//------------------------------------------------------------------------------------------------
	/**
	 * Add Rest Service, this service will be called to make add post process
	 * also will check posts data and returns String
	 * @param postConrent
	 *            provided the post
	 *  @param hashTag
	 *            provided if the post has a hash tag
	 *  @param timeLineOwner
	 *            provided the time line owner
	 * @return Status provided Status
	 */
	@POST
	@Path("/savePostService2")
	public String savePostService2(@FormParam("postOwner") String postOwner,@FormParam("postContent") String postContent,@FormParam("hashTag") String hashTag,
					@FormParam("membersToShow") String membersToShow,@FormParam("feeling") String feeling,@FormParam("privacy") String privacy,@FormParam("timeLineOwner") String timeLineOwner) {
		/*System.out.println("postOwner= "+postOwner);
		System.out.println("postContent="+postContent);
		System.out.println("hashTag= "+hashTag);
		System.out.println("membersToShow = "+membersToShow);
		System.out.println("privacy = "+privacy);
		System.out.println("timeLineOwner"+timeLineOwner);*/
		if (privacy.equals("PublicPost")){
			Post p = new PublicPost(postOwner,postContent,0,0,hashTag,"",new PagePost(timeLineOwner,0));
			p.writePost();
		}else if (privacy.equals("PrivatePost")){
			Post p = new PrivatePost(postOwner,postContent,0,0,hashTag,"",new PagePost(timeLineOwner,0));
			p.writePost();
		}else if(privacy.equals("CustomPost")){
			Post p = new CustomPost(postOwner,postContent,0,0,hashTag,membersToShow,new PagePost(timeLineOwner,0));
			p.writePost();
		}
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();
	}
//-----------------------------------------------------------------------------------------------------
	/**
	 * Add Rest Service, this service will be called to make createPage process
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
	@Path("/createPageService")
	public String createPageService(@FormParam("pageName") String pageName,@FormParam("pageOwner") String pageOwner,@FormParam("category") String category,@FormParam("type") String type) {
		
		Page p = new Page(pageName,pageOwner,category,type,0);
		p.createPage();
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();
	}
//---------------------------------------------------------------------------------------
	/**
	 * Add Rest Service, this service will be called to make createPage process
	 * also will check posts data and returns String
	 * @param pageName
	 *            provided the page name
	 * @return Status provided Status
	 */
	@POST
	@Path("/pageSearchService")
	public String pageSearchService(@FormParam("pageName") String pageName) {
		
		Page p = Page.getSpecificPage(pageName);
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		object.put("pageName", p.pageName);
		object.put("pageOwner", p.pageOwner);
		object.put("category", p.category);
		object.put("type", p.type);
		object.put("numOfLikes", p.numOfLikes);
		return object.toString();
	}
//----------------------------------------------------------------------------------
	/**
	 * Add Rest Service, this service will be called to make createPage process
	 * also will check posts data and returns String
	 * @param pageName
	 *            provided the page name
	 * @return Status provided Status
	 */
	@POST
	@Path("/pageLikeService")
	public String pageLikeService(@FormParam("likerName") String likerName,@FormParam("pageName") String pageName) {
		
		boolean p = Page.likePage(likerName,pageName);
		JSONObject object = new JSONObject();
		if(p){
				object.put("Status", "OK");
		}else{
				object.put("Status", "koba");
		}
		return object.toString();
	}
//---------------------------------------------------------------------------------------------------------
	/**
	 * Add Rest Service, this service will be called to make createPage process
	 * also will check posts data and returns String
	 * @param pageName
	 *            provided the page name
	 * @return Status provided Status
	 */
	@POST
	@Path("/likePostService")
	public String likePostService(@FormParam("id") String id,@FormParam("liker") String liker) {
		//System.out.println("service = "+id);
		//System.out.println("service liker = "+ liker);
		boolean p = UserPost.likepost(liker,Long.parseLong(id));
		System.out.println("3daaaaaaaaa");
		JSONObject object = new JSONObject();
		if(p){
				object.put("Status", "OK");
		}else{
				object.put("Status", "koba");
		}
		return object.toString();
	}
//---------------------------------------------------------------------------------------------------------
	/**
	 * Add Rest Service, this service will be called to make retrieve all  posts
	 * @return Status provided Status
	 */
	@POST
	@Path("/getAllPostsService")
	public String getAllPostsService() {
		Vector<UserPost> posts = UserPost.getAllPosts();
		System.out.println(posts.size());
		org.json.simple.JSONArray returnedJson = new org.json.simple.JSONArray();
		for (UserPost post : posts){
			JSONObject object = new JSONObject();
			/*UserPost post = new UserPost(object.get("postOwner").toString(),object.get("postContent").toString(),
					Integer.parseInt(object.get("numOfLikes").toString()),
					Integer.parseInt(object.get("membersShares").toString()),
					object.get("hashTag").toString(),object.get("timeLineOwner").toString(),
					object.get("feeling").toString());
			post.setpostType(object.get("postType").toString());
			post.pageOwner = object.get("pageOwner").toString();
			post.numOfSeen = Integer.parseInt(object.get("numOfSeen").toString());
			post.ID = Long.parseLong(object.get("id").toString());*/
			object.put("postOwner", post.getPostOwner());
			object.put("postContent", post.getPostContent());
			object.put("numOfLikes",  post.getNumOfLikes());
			object.put("membersShares", post.getMembersShares());
			object.put("hashTag", post.getHashTag());
			object.put("timeLineOwner", post.getTimeLineOwner());
			object.put("feeling", post.getFeeling());
			object.put("postType", post.getpostType());
			object.put("pageOwner", post.pageOwner);
			object.put("numOfSeen", post.numOfSeen);
			object.put("id", post.ID);
			returnedJson.add(object);
		}
		return returnedJson.toString();
	}
//----------------------------------------------------Get someone posts
	/**
	 * Add Rest Service, this service will be called to make retrieve all someone  posts
	 * @return Status provided Status
	 */
	@POST
	@Path("/getSomeonePostsService")
	public String getSomeonePostsService(@FormParam("timeLineOwner") String timeLineOwner){
		//System.out.println("timeLineOwner from service="+timeLineOwner);
		Vector<UserPost> posts = UserPost.getSomeonePosts(timeLineOwner);
		org.json.simple.JSONArray returnedJson = new org.json.simple.JSONArray();
		for (UserPost post : posts){
			JSONObject object = new JSONObject();
			object.put("postOwner", post.getPostOwner());
			object.put("postContent", post.getPostContent());
			object.put("numOfLikes",  post.getNumOfLikes());
			object.put("membersShares", post.getMembersShares());
			object.put("hashTag", post.getHashTag());
			object.put("timeLineOwner", post.getTimeLineOwner());
			object.put("feeling", post.getFeeling());
			object.put("postType", post.getpostType());
			object.put("pageOwner", post.pageOwner);
			object.put("numOfSeen", post.numOfSeen);
			object.put("id", post.ID);
			returnedJson.add(object);
		}
		return returnedJson.toString();
	}
//-----------------------------------------------------
	/**
	 * Add Rest Service, this service will be called to make retrieve all someone  posts
	 * @param timeline owner providing the page name
	 * @return Status provided Status
	 */
	@POST
	@Path("/getPagePostsService")
	public String getPagePostsService(@FormParam("timeLineOwner") String timeLineOwner){
		//System.out.println("timeLineOwner from service="+timeLineOwner);
		Vector<UserPost> posts = UserPost.getPagePostsService(timeLineOwner);
		org.json.simple.JSONArray returnedJson = new org.json.simple.JSONArray();
		for (UserPost post : posts){
			JSONObject object = new JSONObject();
			object.put("postOwner", post.getPostOwner());
			object.put("postContent", post.getPostContent());
			object.put("numOfLikes",  post.getNumOfLikes());
			object.put("membersShares", post.getMembersShares());
			object.put("hashTag", post.getHashTag());
			object.put("timeLineOwner", post.getTimeLineOwner());
			object.put("feeling", post.getFeeling());
			object.put("postType", post.getpostType());
			object.put("pageOwner", post.pageOwner);
			object.put("numOfSeen", post.numOfSeen);
			object.put("id", post.ID);
			returnedJson.add(object);
		}
		return returnedJson.toString();
	}
//---------------------------------------------------GetHashTag
/**
 * 	
 * @param gethashtag
 * @return
 */
	
		@POST
		@Path("/GetHashTagService")
		public String Service(@FormParam("gethashtag") String gethashtag) {
			System.out.println("services");
			JSONObject object = new JSONObject();
			int counter = UserEntity.GetHashTag(gethashtag);
			
			if (counter == 0) {
				object.put("Status", "Failed");

			} else {
				object.put("Status", "OK");
				object.put("gethashtag", counter);
			}
			
			return object.toString();

		}
		

//---------------------------------------------------GetTimeLine
/**
 * 			
 * @param gettimeline
 * @return
 */
			
				@POST
				@Path("/GitTimeLineService")
				public String TimeLineService(@FormParam("gettimeline") String gettimeline) {
			      JSONObject object = new JSONObject();
			   // System.out.println("GetMostTenHashTagService");
				ArrayList<String> m = UserEntity.GetTimeLine(gettimeline);
					
					if (m.size() == 0) {
						object.put("Status", "Failed");

					} else {
						object.put("Status", "OK");
						object.put("gettimeline", m);
					}
					
					return object.toString();

				}
			
//-----------------------------------------------------------------------GetMostTenHashTagService
/**
 * 				
 * @return
 */
				@POST
				@Path("/GetMostTenHashTagService")
				public String GetMostTenHashTagService() {
			      JSONObject object = new JSONObject();
			   // System.out.println("GetMostTenHashTagService");
				Map<String ,Integer> m = UserEntity.GetMostTenHashTag();
					
					if (m.size() == 0) {
						object.put("Status", "Failed");

					} else {
						object.put("Status", "OK");
						object.put("gettenmosthashtag", m);
					}
					
					return object.toString();

				}
			





}	