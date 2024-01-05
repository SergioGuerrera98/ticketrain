
<%@page import="com.corso.ticketrain.model.User"%>
<% User user = (User) session.getAttribute("userLoggato"); 
String webApp = request.getContextPath();
%>



<div class="header">

<form action="" method="post">
<button class="btn">Home</button>
</form>&nbsp;&nbsp;

<%
if (user!=null){
%>
<h2>Benvenuto <%=user.getUsername() %></h2>
<form action="/ticketUser/getByUsername" method="post">
<button class="btn">Area Personale</button>
</form>&nbsp;&nbsp;
<form action="/ticketUser/getByUsername" method="post">
<button class="btn">Logout</button>
</form>&nbsp;&nbsp;

<%	
}else{
%>
<form action="<%=webApp + "/signup" %>" method="get">
<button class="btn">Registrati</button>
</form>&nbsp;&nbsp;
<form action="<%=webApp + "/login" %>" method="get">
<button class="btn">Accedi</button>
</form>&nbsp;&nbsp;
<% }%>

</div>