<%@page import="com.corso.ticketrain.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%
User user = (User) session.getAttribute("UserLoggato"); 
String errorLabel = (String) request.getAttribute("erroreLabel");
String webApp = request.getContextPath();
%>	
<head>
    <meta charset="ISO-8859-1">
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="<%=webApp%>/css">
</head>

<body>
<header>
</header>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<a class="navbar-brand" href="<%=webApp%>/home">TickeTrain</a>
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
						<%if (user==null){%>
                        <li class="nav-item"><a class="nav-link" href="<%=webApp%>/login">Accedi</a></li>
                        <li class="nav-item"><a class="nav-link" href="<%=webApp%>/signup">Registrati</a></li>
                        <%} if (user!=null){%>
                        <li class="nav-item"><a class="nav-link" href="<%=webApp%>/account">Area Personale (<%=user.getUsername() %>)</a></li>
                       	<li class="nav-item"><a class="nav-link" href="<%=webApp%>/user/logout">Logout</a></li>
                        <% if (user.isAmministratore()){%>
                        	 <li class="nav-item"><a class="nav-link" href="<%=webApp%>/user/admin">Pagina Amministratore</a></li>
                        	 
                        	
                        <%}} %>
                         
                    </ul>
		</div>
	</nav>
	<div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img
                src="https://static.nexilia.it/vologratis/2015/03/sconto-italo-under-30-768x399.jpg"
                class="d-block w-100" style="max-width: 50%; height: 25%; margin-left: 25%" alt="...">
                <h5>Tratte aggiornate tutto l'anno</h5>
        		<p>Spostati dove vuoi, quando vuoi.</p>
        </div>
        <div class="carousel-item">
            <img
                src="https://www.newsabruzzo.it/wp-content/uploads/2023/01/Treno-23012023-NewsAbruzzo.it_.jpg"
                class="d-block w-100" style="max-width: 50%; height: 25%; margin-left: 25%" alt="...">
                <h5>Visita l'Italia a basso costo</h5>
        		<p>Prezzi scontati nel weekend.</p>
        </div>
        <div class="carousel-item">
            <img
                src="https://getwallpapers.com/wallpaper/full/9/1/d/364210.jpg"
                class="d-block w-100" style="max-width: 50%; height: 25%; margin-left: 25% " alt="...">
                <h5>Affidabilità e puntualità garantite</h5>
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
<img alt="" src="/img1.jpeg">
<img alt="" src="/resources/img1.jpeg">
<img alt="" src="img1.jpeg">
<img alt="" src="resources/img1.jpeg">
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
	
</body>
</html>