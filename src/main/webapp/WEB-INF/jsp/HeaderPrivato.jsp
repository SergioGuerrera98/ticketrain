<%@page import="com.corso.ticketrain.model.User"%>
<% User user = (User) session.getAttribute("userLoggato"); 
String webApp = request.getContextPath();
%>


<div class="header">
<h2>Benvenuto <%=user.getUsername() %></h2>
<form action="/ticketUser/getByUsername" method="post">
<button class="btn">Area Personale</button>
</form>&nbsp;&nbsp;
<form action="/user/logout" method="post">
<button class="btn">Logout</button>
</form>&nbsp;&nbsp;
</div>