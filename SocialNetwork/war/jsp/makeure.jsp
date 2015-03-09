<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="/social/add"  method="post">
<p> are you sure you want to add ${it.name} ?</p><bt>
 to make sure Enter your friend name again and press continue  (^_^)<br>
 Name : <input type="text" name="uname" value="${it.name}" /> 
 <input type="submit" value="continue">
  </form>

</body>
</html>