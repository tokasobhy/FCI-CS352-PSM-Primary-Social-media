<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>
<form action="/social/logout"  method="post">
  <input type="submit" value="Logout">
  </form><br>
  
  <form action="/social/accept"  method="post">
  Enter the friend name to be accepted:<input type="text" name="u_name" />
  <input type="submit" value="accept">
  </form>
  
<p> Welcome b2a ya ${it.name} </p>
<p> This is should be user home page </p>
<p> Current implemented services "http://fci-swe-apps.appspot.com/rest/RegistrationService --- {requires: uname, email, password}" </p>
<p> and "http://fci-swe-apps.appspot.com/rest/LoginService --- {requires: uname,  password}" </p>
<p> you should implement sendFriendRequest service and addFriend service</p>

<br>

<form action="/social/search" method="post">
Enter the user name of a person to send a friend requiest: <input type="text" name="uname" />
  <input type="submit" value="Send">
  </form>
</body>
</html>