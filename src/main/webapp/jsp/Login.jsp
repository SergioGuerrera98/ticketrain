<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String webApp = request.getContextPath();
String formAction = webApp + "/Login";
%>
<h1>Pagina di login</h1>
	<form action="<%=formAction %>" method="GET">	
	<input type="text" id="username" name="username"> 
	<input type="text" id="password" name="password"> 
	<input type="submit" value="Login">
	</form>
</body>
</html>