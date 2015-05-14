package com.FCI.SWE.Page;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
@Test(groups="Page")
public class PageTest { 	
//	String pageName,String pageOwner, String category, String type,
	//int numOfLikes)
 Page page=new Page(null , "test" , "dasda" , "wewe" , 0);

  @Test
  public void Page() {
   
	  
  }

  @Test 
  public void createPage() {
    
	//   	Assert.assertEquals(page, page.createPage());    //return object 
	  
  Assert.assertEquals(true, page.createPage());     // me4 3arf anhy wa7da se7
	  
  }

  @Test
  public void getCategory() {
    
	  Assert.assertEquals("dasda", page.getCategory());
  }

  @Test
  public void getNumOfLikes() {
   
	  Assert.assertEquals(0, page.getNumOfLikes());
  }

  @Test
  public void getPage() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void getPageName() {
    
	  
	  Assert.assertEquals(null, page.getPageName());
	  
  }

  @Test
  public void getPageOwner() {
   
	  
	  Assert.assertEquals("test", page.getPageOwner());
  }
  @Test
  public void getType() {

	  
	  Assert.assertEquals("wewe", page.getType());
	  
  }

  @Test
  public void likePage() {
    
	  Assert.assertEquals(true, page.likePage("test", "bassem"));
	  
  }
}
