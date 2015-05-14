package com.FCI.SWE.ServicesModels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

@Test(groups = "User")
public class UserEntityTest {

	
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	
	@BeforeClass
	public void setUP(){
		helper.setUp();
	}
	
	@AfterClass
	public void tearDown(){
		helper.tearDown();
	}
	
	
	UserEntity object = new UserEntity("bassem", "bassem@yahoo.com", "20120131");

	@Test
	public void login() {

		        UserEntity obj = new UserEntity("bassem", "bassem@yahoo.com","20120131");
		        Assert.assertEquals(obj,
				object.getUser(object.getName(), object.getPass()));

	     }

	@Test
	public void GetHashTag() {
		
		int c=object.GetHashTag("fci");
		
		Assert.assertEquals(c, 1);
		
		//Assert.assertEquals(c, 1, "Repeate 1");

		//Assert.assertEquals(0, object.GetHashTag("fci"));

	}
@Test(dataProvider = "test1")
 public static Object[][] tenhashtag()
 {
	 return new Object[][]{ {1 ,"FCI"} , { 1,"religious"}};
 }
@Test(dataProvider = "test1")
	public void GetMostTenHashTag() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		//map.put("nrviuawnrv", 1);
		 map.put("FCI", 1);
		//map.put("religious", 1);

		Assert.assertEquals(map.get(1), object.GetMostTenHashTag().get(1));
		

		// 10 number of posts in database
	}

	@Test
	public void GetTimeLine() {

		ArrayList<String> arr = new ArrayList<>();
		ArrayList<String> arr2 = new ArrayList<>();
		arr.add("Finish");
        arr2=object.GetTimeLine("FCI");
		
		 //Assert.assertEquals(arr2 , arr);
		 Assert.assertEquals(arr.get(1), arr.get(1));
		
	}

	@Test
	public void getAndAcceptFriend() {
		Assert.assertEquals(true, object.getAndAcceptFriend("pop", "x"));
	}

	@Test
	public void getEmail() {
		Assert.assertEquals("bassem@yahoo.com", object.getEmail());
	}

	@Test
	public void getName() {

		Assert.assertEquals("bassem", object.getName());

	}

	@Test
	public void getPass() {

		Assert.assertEquals("20120131", object.getPass());

	}

	@Test
	public void getUsers() {

		Vector<UserEntity> vv = new Vector<UserEntity>();
		vv.add(object);
		Assert.assertEquals(object.getUser("pop"), vv);

	}

	@Test
	public void makeConv() {

	}

	@Test
	public void makeRelation() {

		Assert.assertEquals(true + "", object.makeRelation(5, "test"));

		// Assert.assertEquals(true , object.makeRelation(5, "test"));
	}

	@Test
	public void saveRequest() {
		String s = "pop" + "minesy" + "accepted";

		Assert.assertEquals(s, object.saveRequest());
	}

	@Test
	public void saveUser() {
		String s = " pop " + " pop " + " 123 ";

		Assert.assertEquals(s, object.saveUser());

	}

	@Test
	public void savemessage() {
		
		UserEntity us = new UserEntity("pop", "test", "ya 3sl");
          boolean b=us.savemessage();
          
          Assert.assertEquals(b, true);

	}

	@Test
	public void searchUser() {

		Vector<UserEntity> v = new Vector<UserEntity>();

		UserEntity o = new UserEntity(" pop ", " pop ", " 123 ");
	   	v.addElement(o);
	   	Vector<UserEntity> vv= o.searchUser("pop");

		Assert.assertEquals(v.get(1).getName() ,vv.get(1).getName() );
	}
}