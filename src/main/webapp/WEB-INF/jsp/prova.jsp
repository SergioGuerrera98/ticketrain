<%@page import="com.corso.ticketrain.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%User user = (User) session.getAttribute("UserLoggato"); %>
<h1>Benvenuto <%=user.getUsername() %></h1>
</body>
</html>