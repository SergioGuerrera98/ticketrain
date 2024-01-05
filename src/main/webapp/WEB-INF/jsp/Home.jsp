<%@page import="com.corso.ticketrain.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
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
<h1>HOME</h1>
<div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
  </div>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="https://www.fsnews.it/content/dam/fs_news/archivio/focus/ottobre/servizi/19_10_2020_Frecciarossa_1000_apertura.jpg" class="d-block w-100" alt="...">
      <div class="carousel-caption d-none d-md-block">
        <h5>Tratte aggiornate tutto l'anno</h5>
        <p>Spostati dove vuoi, quando vuoi.</p>
      </div>
    </div>
    <div class="carousel-item">
      <img src="https://www.viaggidellosciamano.com/wp-content/uploads/2022/01/ss-delphine-yatch-messina-madonnina-porto-e1599126878597.jpg" class="d-block w-100" alt="...">
      <div class="carousel-caption d-none d-md-block">
        <h5>Visita l'Italia a basso costo</h5>
        <p>Prezzi scontati nel weekend.</p>
      </div>
    </div>
    <div class="carousel-item">
      <img src="https://www.tempoitalia.it/wp-content/uploads/2023/12/Il-Treno-Piu-Veloce-del-Mondo-Addio-a-Lunghi-Viaggi.jpg" class="d-block w-100" alt="...">
      <div class="carousel-caption d-none d-md-block">
        <h5>Affidabilità e puntualità garantite</h5>
        <p>Rimborso parziale in caso di ritardo.</p>
      </div>
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
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
					<td><input type="date" name="dataPartenza"/></td>
				</tr>
		</table>
		<p class="labelError"><%=errorLabel%></p>	
		<input type="submit" value="Submit" /></form>
</div>
<footer><jsp:include page="/WEB-INF/jsp/Footer.jsp"></jsp:include></footer>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Mrj2HPU3JDb01xZTR7oPzB01LvLVUi5k2pJq0QhD9xmOzlsF7To5YUHoG0XrZ4a2" crossorigin="anonymous"></script>
</body>
</html>