package com.FCI.SWE.Controller;

public class CurrentUser {
	private String name;
	private String email;
	private String password;
	
	public CurrentUser(){
		this.name = null;
		this.email = null;
		this.password = null;
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static CurrentUser user1 = new CurrentUser() ;
	
}
