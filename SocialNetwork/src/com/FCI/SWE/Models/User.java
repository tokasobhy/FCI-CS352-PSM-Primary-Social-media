package com.FCI.SWE.Models;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class User {
	private long id;
	public String name;
	public String email;
	private String password;
	
	private static User currentActiveUser;

	/**
	 * Constructor accepts user data
	 * 
	 * @param name
	 *            user name
	 * @param email
	 *            user email
	 * @param password
	 *            user provided password
	 */
	private User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	private User(){
	}
	
	private void setId(long id){
		this.id = id;
	}
	
	private void setEmail(String email  ){
		this.email = email;
	}
	
	private void setName(String name  ){
		this.name = name;
	}
	
	public long getId(){
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPass() {
		return password;
	}
	
	public static User getCurrentActiveUser(){
		return currentActiveUser;
	}
	
	/**
	 * 
	 * This static method will form UserEntity class using json format contains
	 * user data
	 * 
	 * @param json
	 *            String in json format contains user data
	 * @return Constructed user entity
	 */
	public static User getUser(String json) {

		JSONParser parser = new JSONParser();
		try {
			JSONObject object = (JSONObject) parser.parse(json);
			currentActiveUser = new User(object.get("name").toString(), object.get(
					"email").toString(), object.get("password").toString());
			currentActiveUser.setId(Long.parseLong(object.get("id").toString()));
			return currentActiveUser;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 
	 * This static method will form UserEntity class using json format contains
	 * user data
	 * 
	 * @param json
	 *            String in json format contains user data
	 * @return Constructed user entity
	 */
	public static User parseUserInfo(String json){
		JSONParser parser = new JSONParser();
		try{
			JSONObject object = (JSONObject) parser.parse(json);
			User user = new User();
			user.setEmail(object.get("email").toString());
			user.setName(object.get("name").toString());
			
			user.setId(Long.parseLong(object.get("id").toString()));
			return user;
		}catch(ParseException e){
			e.printStackTrace();
		}
		return null;
	}
}
