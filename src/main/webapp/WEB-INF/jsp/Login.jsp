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
String formAction = webApp + "/user/login";
String errorLabel = (request.getAttribute("error") != null) ? (String) request.getAttribute("error") : ""; 
%>
<header><jsp:include page="/WEB-INF/jsp/Header.jsp"></jsp:include></header>
<h1>Log-in</h1>
	<form action="<%=formAction %>" method="GET">
		<table style="with: 50%">
				<tr>
					<td>UserName</td>
					<td><input type="text" name="username" placeholder="Username"/></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" placeholder="Password"/></td>
				</tr>
		</table>
		<p class="errorLabel"><%=errorLabel%></p>	
		<input type="submit" value="Submit" /></form>
</body>
</html>