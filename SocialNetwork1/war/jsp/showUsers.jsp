<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
           
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<c:forEach items= "${it.usersList}" var="user">

<p> User Name: <c:out value = "${user.name}"></c:out></p>
<p> User Email:<c:out value = "${user.email}"></c:out></p>

<form action="/social/doSearch" method="post">
Enter the user name to Search and send him a message:<input type="text" name="uname" />
  <input type="submit" value="Search for Sending a message">
</form>

</c:forEach>

</body>
</html>