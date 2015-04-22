package com.FCI.SWE.Post;

import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class UserPost extends Post {
	
	
	
	public UserPost(String timeLineOwner,String feeling){
		this.timeLineOwner = timeLineOwner;
		this.feeling = feeling;
		this.postType = "userPost";
		this.pageOwner = "noData";
	}
	
	public UserPost(String postOwner, String postContent, int numOfLikes,
			 int membersShares,String hashtag,String timeLineOwner,String feeling) {
		super(postOwner, postContent, numOfLikes, membersShares,hashtag);
		this.timeLineOwner = timeLineOwner;
		this.feeling = feeling;
		this.postType = "userPost";
	}
	
	
	public String getTimeLineOwner() {
		return timeLineOwner;
	}
	
	public void setTimeLineOwner(String timeLineOwner) {
		this.timeLineOwner = timeLineOwner;
	}
	
	public String getFeeling() {
		return feeling;
	}
	
	public void setFeeling(String feeling) {
		this.feeling = feeling;
	}
	
	public String getPostOwner() {
		return postOwner;
	}


	public void setPostOwner(String postOwner) {
		this.postOwner = postOwner;
	}


	public String getPostContent() {
		return postContent;
	}


	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}


	public int getNumOfLikes() {
		return numOfLikes;
	}


	public void setNumOfLikes(int numOfLikes) {
		this.numOfLikes = numOfLikes;
	}


	public String getpostType() {
		return postType;
	}


	public void setpostType(String postType) {
		this.postType = postType;
	}


	public int getMembersShares() {
		return membersShares;
	}


	public void setMembersShares(int membersShares) {
		this.membersShares = membersShares;
	}
	
	
	@Override
	public boolean writePost() {
		return true;
	}
	
	@Override
	public void setHashTag(String hashTag) {
		this.hashTag = hashTag;
	}

		
	@Override
	public String getHashTag() {
		return this.hashTag;
	}
	public static Post parsePostInfo(String json){
		JSONParser parser = new JSONParser();
		try{
			JSONObject object = (JSONObject) parser.parse(json);
			UserPost post = new UserPost(object.get("postOwner").toString(),object.get("postContent").toString(),
					Integer.parseInt(object.get("numOfLikes").toString()),
					Integer.parseInt(object.get("membersShares").toString()),
					object.get("hashTag").toString(),object.get("timeLineOwner").toString(),
					object.get("feeling").toString());
			post.setpostType(object.get("postType").toString());
			post.pageOwner = object.get("pageOwner").toString();
			post.numOfSeen = Integer.parseInt(object.get("numOfSeen").toString());
			post.ID = Long.parseLong(object.get("id").toString());
			return post;
		}catch(ParseException e){
			e.printStackTrace();
		}
		return null;
	}
//-----------------------------------------------------------------------------------------------------
	/**
	 * 
	 * @return
	 */
	
	public static Vector <UserPost> getAllPosts(){
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		Query gae = new Query("post");
		PreparedQuery pq = dataStore.prepare(gae);
		Vector <UserPost> returnedposts = new Vector<UserPost>();
		for( Entity entity : pq.asIterable() ){
			System.out.println("postOwner"+entity.getProperty("postOwner").toString());
			System.out.println("postContent"+entity.getProperty("postContent").toString());
			System.out.println("numOfLikes"+entity.getProperty("numOfLikes").toString());
			System.out.println("membersShares"+entity.getProperty("membersShares").toString());
			System.out.println("hashtag"+entity.getProperty("hashTag").toString());
			System.out.println("timeLineOwner"+entity.getProperty("timeLineOwner").toString());
			System.out.println("feeling"+entity.getProperty("feeling").toString());
			System.out.println("postType"+entity.getProperty("postType").toString());
			System.out.println("pageOwner"+entity.getProperty("pageOwner").toString());
			System.out.println("numOfSeen"+entity.getProperty("numOfSeen").toString());
			System.out.println("id"+entity.getKey().getId());
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			UserPost post = new UserPost(entity.getProperty("postOwner").toString(),entity.getProperty("postContent").toString(),
					Integer.parseInt(entity.getProperty("numOfLikes").toString()),Integer.parseInt(entity.getProperty("membersShares").toString()),
					entity.getProperty("hashTag").toString(),entity.getProperty("timeLineOwner").toString(),
					entity.getProperty("feeling").toString());
			
			post.setpostType(entity.getProperty("postType").toString());
			post.pageOwner = entity.getProperty("pageOwner").toString();
			post.numOfSeen = Integer.parseInt(entity.getProperty("numOfSeen").toString());
			post.ID = entity.getKey().getId();
			
			returnedposts.add(post);
		}
		return returnedposts;
	}
}
