<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
           
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="/css/pageCss.css" rel="stylesheet" type="text/css">
</head>
<body>

<h1>this is ${it.pageName} page</h1>

<form action="/social/pageLike"  method="post" >
<span style="float:left;">
	<input type="hidden" name="pageName" value="${it.pageName}">
  <input type="submit" value="Like This Page" >
</span>
</form>

<form action="/social/getPagePosts" style="text-align:right" method="post">
<span style="float:right;">
<input type="hidden" value = "${it.pageName}" name="timeLineOwner"  /> <br>
 ${it.pageName}  posts: <input type="submit"  value="posts">
</span>
</form>
<br>
<form action="/social/savePost2"  method="post">
<p>
post:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
<input type="text" name="postContent"  /> <br>
#: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="text" name="hashTag"  /> <br>
privacy:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="text" name="privacy"  /> <br>
custom members:&nbsp;
<input type="text" name="membersToShow"  /> 
<input type="hidden" value = "${it.pageName}" name="timeLineOwner"  /> <br>
<input type="submit" value="Post" />
</p>
 </form>
</body>
</html>