<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
           
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="/css/login.css" rel="stylesheet" type="text/css">
</head>
<body>

<c:forEach items= "${it.usersList}" var="user">
			<p> 
				 User Name: <c:out value = "${user.name}"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</p>
</c:forEach>
<br>
<br>
<br>
<form action="/social/makeConversation" method="post">
Type the conversation name:<input type="text" name="cname" />
<br>
<br>
Type the message :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<TEXTAREA NAME="message" ROWS="5" COLS="50"></TEXTAREA>
<br>
<br>
Type the conversation members sebrated with(;) :<input type="text" name="members" />
<br>
<br>
<br>
  <input type="submit" value="Create a Conversation">
</form>


</body>
</html>