<%@page import="com.corso.ticketrain.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

</head>

<body>
<%
User user = (User) session.getAttribute("UserLoggato"); 
String errorLabel = (String) request.getAttribute("erroreLabel");
String webApp = request.getContextPath();
if (user!=null){
%>	

<header>
<div class="header">
<h2>Benvenuto <%=user.getUsername() %></h2>
<form action="<%=webApp%>/account" method="get">
<button class="btn">Area Personale</button>
</form>&nbsp;&nbsp;
<form action="<%=webApp%>/user/logout" method="get">
<button class="btn">Logout</button>
</form>&nbsp;&nbsp;
</div>
<%	
}
if (user==null){
%>

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

<%	
}
%>
</header>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<a class="navbar-brand" href="<%=webApp%>/home">VeniteAVivereATrani</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="<%=webApp%>/home">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="<%=webApp%>/login">Accedi</a></li>
                    </ul>
			</div>
		</div>
	</nav>
	<div id="carouselExampleAutoplaying" class="carousel slide"
    data-bs-ride="carousel">
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img
                src="https://static.nexilia.it/vologratis/2015/03/sconto-italo-under-30-768x399.jpg"
                class="d-block w-100" alt="...">
                <h5>Tratte aggiornate tutto l'anno</h5>
        		<p>Spostati dove vuoi, quando vuoi.</p>
        </div>
        <div class="carousel-item">
            <img
                src="https://www.newsabruzzo.it/wp-content/uploads/2023/01/Treno-23012023-NewsAbruzzo.it_.jpg"
                class="d-block w-100" alt="...">
                <h5>Visita l'Italia a basso costo</h5>
        		<p>Prezzi scontati nel weekend.</p>
        </div>
        <div class="carousel-item">
            <img
                src="https://getwallpapers.com/wallpaper/full/9/1/d/364210.jpg"
                class="d-block w-100" alt="...">
                <h5>Affidabilit� e puntualit� garantite</h5>
        		<p>Rimborso parziale in caso di ritardo.</p>
        </div>
    </div>
    <button class="carousel-control-prev" type="button"
        data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
            class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button"
        data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span> <span
            class="visually-hidden">Next</span>
    </button>
</div>

<div id="filtro">
	<form action="<%=webApp %>/ticket/getByFilter" method="GET">
		<table style="width: 50%">
				<tr>
					<td>Partenza da:</td>
					<td><input type="text" name="luogoPartenza" placeholder="Partenza"/></td>
				</tr>
				<tr>
					<td>Arrivo a:</td>
					<td><input type="text" name="luogoArrivo" placeholder="Destinazione"/></td>
				</tr>
				<tr>
					<td>Data partenza: </td>
					<td><input type="datetime-local" name="dataPartenza"/></td>
				</tr>
		</table>
		<p class="labelError"><%=errorLabel%></p>	
		<input type="submit" value="Submit" /></form>
</div>
<footer><jsp:include page="/WEB-INF/jsp/Footer.jsp"></jsp:include></footer>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Mrj2HPU3JDb01xZTR7oPzB01LvLVUi5k2pJq0QhD9xmOzlsF7To5YUHoG0XrZ4a2" crossorigin="anonymous"></script>
</body>
</html>