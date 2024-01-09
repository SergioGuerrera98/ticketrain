<%@page import="com.corso.ticketrain.model.User"%>
<%@page import="com.corso.ticketrain.model.Ticket"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%
String webApp = request.getContextPath();
String formAction = webApp + "/ticket/getByFilter";
String errorLabel = (request.getAttribute("error") != null) ? (String) request.getAttribute("error") : ""; 
List<Ticket> listaFiltrata = (List<Ticket>) request.getAttribute("filteredTickets");
User user = (User) session.getAttribute("UserLoggato"); 
%>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="<%=webApp%>/css">
<title>Risultati</title>
</head>
<body>

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
                        <%} %>
                    </ul>
                    </div>
		</div>
	</nav>
	<div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img
                src="https://static.nexilia.it/vologratis/2015/03/sconto-italo-under-30-768x399.jpg"
                class="d-block w-100" style="max-width: 50%; height: 500px; margin-left: 25%" alt="...">
                <h5>Tratte aggiornate tutto l'anno</h5>
        		<p>Spostati dove vuoi, quando vuoi.</p>
        </div>
        <div class="carousel-item">
            <img
                src="https://www.newsabruzzo.it/wp-content/uploads/2023/01/Treno-23012023-NewsAbruzzo.it_.jpg"
                class="d-block w-100" style="max-width: 50%; height: 500px; margin-left: 25%" alt="...">
                <h5>Visita l'Italia a basso costo</h5>
        		<p>Prezzi scontati nel weekend.</p>
        </div>
        <div class="carousel-item">
            <img
                src="https://getwallpapers.com/wallpaper/full/9/1/d/364210.jpg"
                class="d-block w-100" style="max-width: 50%; height: 500px; " alt="...">
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

    <div style="margin: 20px;">

        <h1>Risultati dei treni</h1>
		<%
		if (!listaFiltrata.isEmpty()){
			%>
			           <table>
                <thead>
                    <tr>
                        <th>Partenza</th>
                        <th>Arrivo</th>
                        <th>Ora di Partenza</th>
                        <th>Ora di Arrivo</th>
                        <th>Azione</th>
                        <th>Prezzo</th>
                    </tr>
                </thead>
                <tbody>
                	<%
                	for (Ticket ticket : listaFiltrata){
                	%>
                		<tr>
                            <td><%=ticket.getDataArrivo() %></td>
                            <td><%=ticket.getDataPartenza()%></td>
                            <td><%=ticket.getLuogoPartenza() %></td>
                            <td><%=ticket.getLuogoArrivo() %></td>
                            <td><%=ticket.getPrezzo() %></td>
                            <td>
                                <button onclick="buyTicket(<%=ticket.getId()%>)">Procedi all'acquisto</button>

                            </td>
                        </tr>
                	<%
                	}
                	%>
        
                </tbody>
            </table>
			
		<%	
		    }
		%>
        <p class="errorLabel"> <%=errorLabel%> </p>
    </div>
<footer><jsp:include page="/WEB-INF/jsp/Footer.jsp"></jsp:include></footer>
<script>
    function buyTicket(id) {

        const xhr = new XMLHttpRequest();
        xhr.open("GET", "<%=webApp %>/ticket/toDetails/" + id);
        xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8")


        xhr.onload = () => {
            if (xhr.status < 400) {
                window.location.replace("<%=webApp %>"+xhr.responseText);
            } else {
                console.log(`Error: ${xhr.status}`);
            }
        };

        xhr.send();

    }
</script>
</body>
</html>
