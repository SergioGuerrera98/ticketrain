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
String errorLabel = (request.getAttribute("error") != null) ? (String) request.getAttribute("error") : ""; 
%>
<header>
<div class="header">
<form action="" method="post">
<button class="btn">Home</button>
</form>&nbsp;&nbsp;

<form action="<%=webApp + "/signup" %>" method="get">
<button class="btn">Registrati</button>
</form>&nbsp;&nbsp;
<form action="<%=webApp + "/login" %>" method="get">
<button class="btn">Accedi</button>
</form>&nbsp;&nbsp;

</div>
</header>
<h1>Registrati</h1>
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
				<tr>
					<td>Paese</td>
					<td><input type="text" name="paese" placeholder="Paese"/></td>
				</tr>
		</table>
		<p class="labelError"><%=errorLabel %></p>	
		<input type="submit" value="Submit" /></form>

<footer><jsp:include page="/WEB-INF/jsp/Footer.jsp"></jsp:include></footer>
</body>
</html>