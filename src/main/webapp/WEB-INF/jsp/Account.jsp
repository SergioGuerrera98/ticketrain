<%@page import="com.corso.ticketrain.model.User"%>
<%@page import="com.corso.ticketrain.model.TicketUser"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Area Personale</title>
</head>
<body>
<%
User user = (User) session.getAttribute("UserLoggato"); 
String webApp = request.getContextPath();
%>	
<header>
<div class="header">
<h2>Benvenuto <%=user.getUsername() %></h2>
<form action="<%=webApp%>/home" method="post">
<button class="btn">Area Personale</button>
</form>&nbsp;&nbsp;
<form action="<%=webApp%>/user/logout" method="get">
<button class="btn">Logout</button>
</form>&nbsp;&nbsp;
</div>
</header>
<%
String formAction = webApp + "/AreaPersonale";
List<TicketUser> listaTicket = (List<TicketUser>) request.getAttribute("listaTicketUser");
%>
<h1>Area Personale</h1>
	<div class="card" style="width: 18rem;">
  		<img src="..." class="card-img-top" alt="...">
  		<div class="card-body">
    		<h5 class="card-title">Card title</h5>
    		<p class="card-text">Qui puoi trovare i tuoi Ticket o acquistarne di nuovi.</p>
    		<a href="<%=request.getContextPath() + "/home"%>" class="btn btn-primary">
				<i class="bi bi-cart-plus">
					Acquista un nuovo ticket
				</i>
			</a>
  		</div>
	</div>

	<h3>Ticket Acquistati</h3>
	<% if(listaTicket != null){
			for (TicketUser ts : listaTicket){		
	%>
		<ul class="list-group list-group-flush">
  			<li class="list-group-item"><%=ts%></li>
		</ul>	
	<%
		}		
	}	
	%>
	
	<footer><jsp:include page="/WEB-INF/jsp/Footer.jsp"></jsp:include></footer>
</body>
</html>