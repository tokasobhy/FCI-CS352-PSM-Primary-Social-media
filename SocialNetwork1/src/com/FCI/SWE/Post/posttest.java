package com.FCI.SWE.Post;

import org.testng.Assert;
import org.testng.annotations.Test;
@Test(groups="post")
public class posttest {
	
CustomPost pos=new CustomPost( "test" , "oitjvoisjo" , 0 , 0 ,"nrviuawnrv" , "all" ,null);
  
  public void getHashTag() {
	  
   
	  Assert.assertEquals("nrviuawnrv",pos.getHashTag());
 
  }

  public void getMembersShares() {
  
  Assert.assertEquals(0, pos.getMembersShares());
  }

  public void getMembersToShow() {
  
	  Assert.assertEquals("all", pos.getMembersToShow());
	  
  }

  public void getMyPost() {
	  
   Assert.assertEquals(null, pos.getMyPost());
  }

  public void getNumOfLikes() {
	  
    Assert.assertEquals(0, pos.getNumOfLikes());
  }

  public void getPostContent() {
    
	  
	  Assert.assertEquals(" oitjvoisjo", pos.getPostContent());
	  
  }

  public void getPostOwner() {
    

	  Assert.assertEquals("test", pos.getPostOwner());
	  
  }

  public void getPrivacy() {
	  
    String s =  pos.getPrivacy();
  // Assert.assertEquals( "publicPost", pos.getPrivacy());
    Assert.assertEquals(s, "publicPost");
	  
  }

  public void getpostType() {
	  
    Assert.assertEquals("pagePost", pos.getpostType());  
    }

  }
