<%@ page language="java" contentType="text/html; charset=iso-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
           
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Insert title here</title>
<link href="/css/profile.css" rel="stylesheet" type="text/css">
</head>
<body >

<form action="/social/getSomeonePosts" style="text-align:right" method="post">
<input type="hidden" value = "${it.name}" name="timeLineOwner"  /> <br>
<p>
 ${it.name}  posts: <input type="submit"  value="posts">
</p>
</form>

<p><h1>dh 2smh ${it.name}</h1></p><br>
<p><h1>el email bta3h ${it.email}</h1></p>

<form action="/social/savePost1"  method="post">
<p>
post:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
<input type="text" name="postContent"  /> <br>
#: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="text" name="hashTag"  /> <br>
privacy:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="text" name="privacy"  /> <br>
custom members:&nbsp;
<input type="text" name="membersToShow"  /> 
<input type="hidden" value = "${it.name}" name="timeLineOwner"  /> <br>
<input type="submit" value="Post" />
</p>
 </form>
</body>
</html>