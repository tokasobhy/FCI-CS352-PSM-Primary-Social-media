<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here
input submit{
background
}
</title>
<link href="/css/styleCss.css" rel="stylesheet" type="text/css">
</head>
<body>

<form action="/social/logout"   method="post">
<span style="float:left;">
  <input type="submit" value="Logout">
  </span>
</form>


<form action="/social/getAllPosts" style="text-align:center"  method="post">
<span style="float:center;"> 
  <input type="submit"  value="news feed">
  </span>
</form>

<form action="/social/getSomeonePosts" style="text-align:right" method="post">
<span style="float:right;"> 
<input type="hidden" value = "${it.name}" name="timeLineOwner"  /> <br>
  <input type="submit"  value="my posts">
  </span>
</form>
 
 
  <p>
  <form action="/social/normalSearch"  method="post">
  Enter the name of friend you want to Search:<input type="text" name="u_name" />
  <input type="submit" value="Search for someone profile">
  </form>
  
  <br>
  
  <form action="/social/pageSearch"  method="post">
  Enter the name of page you want to Search:&nbsp;
  <input type="text" name="pageName" />
  <input type="submit" value="Search for Page Timeline">
  </form>
 </p>
 <p>
 <form action="/social/search" method="post">
Enter the user name of a person to send a friend requiest: <input type="text" name="uname" />
  <input type="submit" value="Send a Friend Request">
</form>
 
 <br>
  
  <form action="/social/accept"  method="post">
  Enter the friend name to be accepted:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="text" name="u_name" />
  <input type="submit" value="accept a Friend Request">
  </form>
</p>
  
<p> 
	Welcome b2a ya ${it.name} 
	This is should be user home page
	Current implemented services "http://fci-swe-apps.appspot.com/rest/RegistrationService --- {requires: uname, email, password}" 
	and "http://fci-swe-apps.appspot.com/rest/LoginService --- {requires: uname,  password}"
	you should implement sendFriendRequest service and addFriend service
</p>


<form action="/social/savePost1"  method="post">
<p>
<h1>Create post</h1>
post:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
<input type="text" name="postContent"  /> <br>
#: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="text" name="hashTag"  /> <br>
feeling:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="text" name="feeling"  /> <br>
privacy:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="text" name="privacy"  /> <br>
custom members:&nbsp;
<input type="text" name="membersToShow"  /> 
<input type="hidden" value = "${it.name}" name="timeLineOwner"  /> <br>
<input type="submit" value="Post" />
</p>
 </form>
 
 
 

 <br>
<form action="/social/GetHashTag"  method="post">
<p>
<h1>GetHashTag</h1>
<input type="text"  name="gethashtag"  /> <br>
<input type="submit" value="GetHashTag" />
</p>
 </form>



<form action="/social/GetTimeLine"  method="post">
<p>
<input type="text"  name="gettimeline"  /> <br>
<input type="submit" value=GetTimeLine />
</p>
 </form>
 
 
 
<form action="/social/GetMostTenHashTag"  method="post">
<p>
<input type="submit" value=MostTenHashTag />
</p>



<form action="/social/createPage" method="post">
<p>
	<h1>Create page</h1>
  Name :&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="pageName" /> <br>
  Category :<input type="text" name="category" /> <br>
  Type :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="type" /> <br>
  <input type="submit" value="Create Page">
</p>
 </form>
 


<p>
link to send a friend message:<a href = "/social/message">Send Message to user</a>
<br>
<form action="/social/getAllUsers" method="post">
  <input type="submit" value="make Conversation group">
</form>

<br>

<form action="/social/showReplyConversation" method="post">
  <input type="submit" value="reply on aConversation group">
</form>
</p>
<br> 

<!-- <form action="/social/doSearch" method="post">
Enter a message:<input type="text" name="uname" />
  <input type="submit" value="Send">
</form> -->


</body>
</html>