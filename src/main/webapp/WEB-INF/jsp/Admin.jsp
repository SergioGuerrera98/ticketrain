<%@page import="com.corso.ticketrain.model.Ticket"%>
<%@page import="com.corso.ticketrain.model.Citta"%>
<%@page import="com.corso.ticketrain.treno.model.Vagone"%>
<%@page import="com.corso.ticketrain.treno.model.Treno"%>
<%@page import="java.util.List"%>
<%@page import="com.corso.ticketrain.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%
User user = (User) session.getAttribute("UserLoggato"); 
String errorLabel = (String) request.getAttribute("erroreLabel");
List<Treno> treni = (List<Treno>) request.getAttribute("listaTreni");
List<Citta> citta = (List<Citta>) request.getAttribute("listaCitta");
List<Ticket> tickets = (List<Ticket>) request.getAttribute("listaTickets");
String webApp = request.getContextPath();
%>	
<meta charset="ISO-8859-1">
<title>Pagina Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="<%=webApp%>/css">
</head>
<body>
<h1>Pagina amministratore</h1>
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
                        <li class="nav-item"><a class="nav-link" href="<%=webApp%>/account">Area Personale (<%=user.getUsername() %>)</a></li>
                        <li class="nav-item"><a class="nav-link" href="<%=webApp%>/user/admin">Pagina Amministratore</a></li>
                        <li class="nav-item"><a class="nav-link" href="<%=webApp%>/user/logout">Logout</a></li>
                    </ul>
		</div>
	</nav>
	<form action="<%=webApp %>/admin/addTrain" method="post">
		<label>Aggiungi un Treno:</label>
		<input type="text" name="stringaTreno" placeholder="Treno">
		<br>
		<input type="submit" value="Aggiungi" />
	</form>
	<form action="<%=webApp %>/admin/deleteTrain" method="post">
    <label for="treno">Seleziona un treno:</label>
    <select name="treno" id="treno">
        <% 
         
            for (Treno treno : treni) {
        %>
            <option value="<%= treno.getId() %>"><%= treno.getCodice() %></option>
        <% } %>
    </select>
    <br>
    <input type="submit" value="Rimuovi">
</form>
		<form action="<%=webApp %>/admin/addTicket" method="post">
	<p>Crea un biglietto</p>	
		    <label for="codice">Codice:</label>
    <input type="text" name="codice" required><br>

    <label for="dataPartenza">Data Partenza:</label>
    <input type="datetime-local" name="dataPartenza" required><br>

    <label for="dataArrivo">Data Arrivo:</label>
    <input type="datetime-local" name="dataArrivo" required><br>

    <label for="luogoPartenza">Luogo Partenza:</label>
    <select name="luogoPartenza">
        <% 
            
            for (Citta c : citta) {
        %>
            <option value="<%= c.getNomeCitta() %>"><%= c.getNomeCitta() %></option>
            
        <% } %>
    </select><br>

    <label for="luogoArrivo">Luogo Arrivo:</label>
    <select name="luogoArrivo">
        <% 
            
            for (Citta c : citta) {
        %>
            <option value="<%= c.getNomeCitta() %>"><%= c.getNomeCitta() %></option>
            
        <% } %>
    </select><br>

    <label for="prezzo">Prezzo:</label>
    <input type="number" name="prezzo" required><br>

    <label for="treno_id">Treno:</label>
    <select name="treno_id">
        <% 
            
            for (Treno treno : treni) {
        %>
            <option value="<%= treno.getId() %>"><%= treno.getCodice() %></option>
            
        <% } %>
    </select><br>

    <br>

    <br>
    <input type="submit" value="Inserisci Viaggio">
	</form>
	<form action="<%=webApp %>/admin/deleteTicket" method="post">
    <label for="treno">Seleziona un ticket:</label>
    <select name="ticket" id="ticket">
        <% 
         
            for (Ticket ticket : tickets) {
        %>
            <option value="<%= ticket.getId() %>"><%= ticket.getCodice() %></option>
        <% } %>
    </select>
    <br>
    <input type="submit" value="Rimuovi">
</form>

</body>
</html>