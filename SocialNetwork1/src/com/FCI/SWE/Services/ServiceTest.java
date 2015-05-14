package com.FCI.SWE.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.ws.rs.FormParam;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.FCI.SWE.Controller.Connection;
import com.FCI.SWE.Controller.CurrentUser;
import com.FCI.SWE.Page.Page;
import com.FCI.SWE.Post.CustomPost;
import com.FCI.SWE.Post.PagePost;
import com.FCI.SWE.Post.Post;
import com.FCI.SWE.Post.PrivatePost;
import com.FCI.SWE.Post.PublicPost;
import com.FCI.SWE.Post.UserPost;
import com.FCI.SWE.ServicesModels.GroupEntity;
import com.FCI.SWE.ServicesModels.UserEntity;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
public class ServiceTest {
	//UserServices u =new UserServices("pop","pop","123");
	UserServices u=new UserServices();
private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	
	@BeforeClass
	public void setUP(){
		helper.setUp();
	}
	
	@AfterClass
	public void tearDown(){
		helper.tearDown();
	}
	

  
  @Test(groups="Service",dependsOnGroups="User")
  public String  registrationService()
  {
	  
	  String serviceUrl = "http://localhost:8888/rest/RegistrationService";
	  String urlParameters = "une=" + "pop" + "&email=" + "pop"
				+ "&password=" + "123";
	  String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
	     JSONObject object = new JSONObject();
	     object.put("Status", "OK");
	  Assert.assertEquals( object.toString()  , retJson );
      return "done";  
	  
  }
 
  @Test(groups="Service",dependsOnGroups="User")
  public String AddService() {
	  String serviceUrl = "http://localhost:8888/rest/AddService";
		String urlParameter = "Sender=" + "pop" + "&Reciever=" + "minesy" +"&Status=accepted";	
		String retJson2 = Connection.connect(serviceUrl, urlParameter, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
	     JSONObject object = new JSONObject();
	     object.put("Status", "OK");
		 Assert.assertEquals(object.toString(),retJson2 );    
	      return "done";  
	}
  
 
  @Test(groups="Service",dependsOnGroups="User")
  public String loginService() {
	  	 	 	
	  String serviceUrl = "http://localhost:8888/rest/Messageservice";
		String urlParameter = "Sender=" + "POP" + "&Reciever=" +"test" +"&message= " +"HIIIIIIII" ;
		String retJson2 = Connection.connect(serviceUrl, urlParameter, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		//JSONParser parser2 = new JSONParser();
		//Object obj2;
	    Assert.assertEquals("Message has been sent Successfully", retJson2);
		return "done";  
	}
  
   
  @Test(groups="Service",dependsOnGroups="User")
  public void acceptService() {
	  
	  String urlParameters = "Sender=" + "pop"+ "&Reciever=" + "minesy" ;
		String retJson = Connection.connect(
				"http://localhost:8888/rest/acceptService", urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");
		//JSONParser parser = new JSONParser();
		//Object obj;
		Assert.assertEquals("accepted Successfully",retJson );    

	}
  
  @Test(groups="Service",dependsOnGroups="User")
  public void getAllFriendsService() {
	  
	  String serviceUrl = "http://localhost:8888/rest/getAllUsersService";
		String urlParameters="chatMaker=" + "pop";
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		
	  
	 Assert.assertEquals(retJson, "gg");
		
		 
	}
  
 
  
  @Test(groups="Service",dependsOnGroups="User")
  public void makeConversationService() throws ParseException {
	  
		String urlParameters = "cname=" + "bola conv"
				+ "&message=" +"nnnnnnnn"+"&members="+";"+";pop;bebo;test"+";"+ CurrentUser.user1.getName() ;
		String retJson = Connection.connect(
				"http://localhost:8888/rest/makeConversationService", urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");
	     
	
		JSONParser parser2 = new JSONParser();
		    Object obj2;
	          obj2 = parser2.parse(retJson);
				JSONObject object2 = (JSONObject) obj2;
				
				Assert.assertEquals(retJson, object2.get("Status").equals("OK"));

	}
  
  
  @Test(groups="Service",dependsOnGroups="User")
  public void Messageservice() throws ParseException {
	  
	  
	  
	     String serviceUrl = "http://localhost:8888/rest/Messageservice";
		String urlParameter = "Sender=" +"POP"+ "&Reciever=" + "test" +"&message= " + 	"HIIIIIIII";
		String retJson2 = Connection.connect(serviceUrl, urlParameter, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
	   
		JSONParser parser2 = new JSONParser();
		Object obj2;
	
				
				obj2 = parser2.parse(retJson2);
				JSONObject object2 = (JSONObject) obj2;
					
		     Assert.assertEquals( retJson2 ,object2.get("Status").equals("OK") );    
				}
  
  
  @Test(groups="Service",dependsOnGroups="post")
  public void savePostService1() throws ParseException {
	  
	  String urlParameters = "postOwner=" +"bassem"+"&postContent="+"oitjvoisjo"+"&hashTag="+"nrviuawnrv"+"&membersToShow="+"all"+"&feeling="+"noData"+"&privacy="+ "publicPost"+"&timeLineOwner="+  "noData" ;

	  String retJson = Connection.connect(
				"http://localhost:8888/rest/savePostService1", urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");

		JSONParser parser = new JSONParser();
		Object obj;
	
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
	     object.put("Status", "OK");
		Assert.assertEquals(retJson , object.get("Status").equals("OK"));    
 
	}
  
  
  @Test(groups="Service",dependsOnGroups=" Page")
  public void createPageService() throws ParseException {
	  
	  String urlParameters ="pageName="+"null"+"&pageOwner="+"test"+"&category="+"dasda"+"&type="+"wewe";
		
		String retJson = Connection.connect(
				"http://localhost:8888/rest/createPageService", urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");
		
		JSONParser parser = new JSONParser();
		Object obj;
		obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
		
		Assert.assertEquals(retJson ,	object.get("Status").equals("OK"));    
 
	}
  
  @Test(groups="Service",dependsOnGroups=" Page")
  public void pageSearchService() throws ParseException {
	  
	  String urlParameters ="pageName=" + "me4MwgodFeDataBase";
		String retJson = Connection.connect("http://localhost:8888/rest/pageSearchService", urlParameters , "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			 
		Assert.assertEquals(retJson,object.get("Status").equals("Failed"));    
 
	}
  
  
  @Test(groups="Service",dependsOnGroups=" Page")
  public void pageLikeService() throws ParseException {
	  
	  String urlParameters ="likerName="+"test"+"&pageName=" + "bassem";
		String retJson = Connection.connect("http://localhost:8888/rest/pageLikeService", urlParameters , "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
	
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
		Assert.assertEquals(retJson ,(object.get("Status").toString()).equals("OK"));    
 
	}
  
  @Test(groups="Service",dependsOnGroups=" Page")
  public void likePostService() throws ParseException {
	  
  
	    String urlParameters ="id="+"2 "+"&liker="+"test";
		String retJson = Connection.connect("http://localhost:8888/rest/likePostService", urlParameters , "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
				
		Assert.assertEquals(retJson , (object.get("Status").toString()).equals("OK"));    
 
	} 
  
  
  @Test(groups="Service",dependsOnGroups="post")
  public void getAllPostsService() throws ParseException {
  String serviceUrl = "http://localhost:8888/rest/getAllPostsService";
	String retJson = Connection.connect(serviceUrl,"", "POST",
			"application/x-www-form-urlencoded;charset=UTF-8");
	
	   JSONParser parser = new JSONParser();
	    Object obj;
		obj = parser.parse(retJson);
		JSONObject object = (JSONObject) obj;
			
	Assert.assertEquals(retJson , (object.get("pageOwner").toString()).equals("bassem"));
	      
	
  }
  
  
  
  @Test(groups="Service",dependsOnGroups="post")
  public void getSomeonePostsService() throws ParseException
  {
	  
	  
	    String serviceUrl = "http://localhost:8888/rest/getSomeonePostsService";
		String urlParameters ="timeLineOwner="+"test";
		String retJson = Connection.connect(serviceUrl,urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
	    Object obj;
		obj = parser.parse(retJson);
		JSONObject object = (JSONObject) obj;
		Assert.assertEquals(retJson , (object.get("pageOwner").toString()).equals("test"));
	  
  }
  
  @Test(groups="Service",dependsOnGroups="post")
  public void getPagePostsService() throws ParseException{
	  
	  String serviceUrl = "http://localhost:8888/rest/getPagePostsService";
		String urlParameters ="timeLineOwner="+"test";
		String retJson = Connection.connect(serviceUrl,urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		
		JSONParser parser = new JSONParser();
	    Object obj;
		obj = parser.parse(retJson);
		JSONObject object = (JSONObject) obj;
		Assert.assertEquals(retJson , (object.get("pageOwner").toString()).equals("test"));  
  }
  
  @Test(groups="Service",dependsOnGroups="User")
  public void TimeLineService() throws ParseException{
	  
	  
	  String urlParameters = "gettimeline=" + "FCI";
		String retJson = Connection.connect(
				"http://localhost:8888/rest/GitTimeLineService", urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");
		
		JSONParser parser = new JSONParser();
	    Object obj;
		obj = parser.parse(retJson);
		JSONObject object = (JSONObject) obj;
		Assert.assertEquals(retJson , (object.get("postContent").toString()).equals("Finish"));  
  
  }
  
  
  
  @Test(groups="Service",dependsOnGroups="User")
  public void GetMostTenHashTagService() throws ParseException {
	  
	  String urlParameters = "";
		
		String retJson = Connection.connect(
				"http://localhost:8888/rest/GetMostTenHashTagService", urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
	    Object obj;
		obj = parser.parse(retJson);
		JSONObject object = (JSONObject) obj;
		Assert.assertEquals(retJson , (object.get("postContent").toString()).equals("Finish"));  
		
		     
	  
  }
  
  
  @Test(groups = "group")
  public void createGroup()
  {
	  
	  
	  
	  
  }
 /* 

@Test
public String createGroup(@FormParam("user_id") String userId,
		@FormParam("name") String name,
		@FormParam("desc") String desc,
		@FormParam("privacy") String privacy) {
	
	    GroupEntity groupEntity = new GroupEntity();
	    groupEntity.setDescription(desc);
	    groupEntity.setName(name);
	    groupEntity.setOwnerId(Long.parseLong(userId));
	    groupEntity.setPrivacy(privacy);
	    JSONObject json = new JSONObject();
	    if(groupEntity.saveGroup())
		json.put("Status", "OK");
	    else
		json.put("Status", "Failed");
	    //return json.toJSONString();
	    Assert.assertEquals("Ok", json.toJSONString());    
	    return "done";
        }
*/

  }

