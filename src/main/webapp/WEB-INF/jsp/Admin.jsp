<%@page import="com.corso.ticketrain.model.Ticket"%>
<%@page import="com.corso.ticketrain.model.Citta"%>
<%@page import="com.corso.ticketrain.treno.model.Vagone"%>
<%@page import="com.corso.ticketrain.treno.model.Treno"%>
<%@page import="java.util.List"%>
<%@page import="com.corso.ticketrain.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%String tema = (session.getAttribute("tema") != null) ? (String) session.getAttribute("tema") : "dark";  %>
<html id="htmlId" data-bs-theme="<%=tema%>">
    <head>
    <%
    User user = (User) session.getAttribute("UserLoggato"); 
    String errorTreno = (request.getAttribute("errorTreno") != null) ? (String) request.getAttribute("errorTreno") : ""; 
    String errorTicket = (request.getAttribute("errorTicket") != null) ? (String) request.getAttribute("errorTicket") : ""; 
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
    <header>
        <jsp:include page="/WEB-INF/jsp/components/Header.jsp"></jsp:include>
    </header>
    
<div class="position-absolute top-50 start-50 translate-middle">

    <!-- Form per aggiungere un treno -->
    <form action="<%=webApp %>/admin/addTrain" method="post" class="mb-4">
        <h5>Aggiungi un Treno:</h5>
        <div class="mb-3">
            <label for="stringaTreno" class="form-label">Nome Treno:</label>
            <input type="text" name="stringaTreno" id="stringaTreno" class="form-control" placeholder="Treno" required>
        </div>
        <button type="submit" class="btn btn-success">Aggiungi</button>
        <%=errorTreno %>
    </form>

    <!-- Form per rimuovere un treno -->
    <form action="<%=webApp %>/admin/deleteTrain" method="post" class="mb-4">
        <h5>Rimuovi un Treno:</h5>
        <div class="mb-3">
            <label for="treno" class="form-label">Seleziona un treno:</label>
            <select name="treno" id="treno" class="form-select" required>
                <% 
                    for (Treno treno : treni) {
                %>
                    <option value="<%= treno.getId() %>"><%= treno.getCodice() %></option>
                <% } %>
            </select>
        </div>
        <button type="submit" class="btn btn-danger">Rimuovi</button>
    </form>

    <!-- Form per aggiungere un biglietto -->
    <form action="<%=webApp %>/admin/addTicket" method="post" class="mb-4">
        <h5>Crea un Biglietto:</h5>
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
    </select>
    	<br>
        <button type="submit" class="btn btn-success">Aggiungi</button>
    </form>
<%=errorTicket %>
    <!-- Form per rimuovere un biglietto -->
    <form action="<%=webApp %>/admin/deleteTicket" method="post">
        <h5>Rimuovi un Ticket:</h5>
        <div class="mb-3">
            <label for="ticket" class="form-label">Seleziona un ticket:</label>
            <select name="ticket" id="ticket" class="form-select" required>
                <% 
                    for (Ticket ticket : tickets) {
                %>
                    <option value="<%= ticket.getId() %>"><%= ticket.getCodice() %></option>
                <% } %>
            </select>
        </div>
        <button type="submit" class="btn btn-danger">Rimuovi</button>
    </form>
</div>
</body>
</html>
