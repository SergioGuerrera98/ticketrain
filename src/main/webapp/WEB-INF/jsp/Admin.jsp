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
     <link rel="shortcut icon" type="image/png" href="https://raw.githubusercontent.com/IlanZdd/resources/main/topolino.png">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" ></script>
        <link rel="stylesheet" type="text/css" href="<%=webApp%>/css">
    </head>
    <body>
    <header>
        <jsp:include page="/WEB-INF/jsp/components/Header.jsp"></jsp:include>
    </header>
   <br>
<div class="card shadow-lg p-3 mb-5 bg-body rounded col-md-auto " style="margin-left: 25%; margin-right: 25%">

    <!-- Form per aggiungere un treno -->
    <div class="card-body" style="margin-left: 50px">
    <div class="mb-3 row">
    <div class="col">
    <form action="<%=webApp %>/admin/addTrain" method="post" class="mb-4">
    	
        <h5>Aggiungi un Treno:</h5>
        <div class="mb-3 ">
            <label for="stringaTreno" class="form-label">Nome Treno:</label>
            <input type="text" name="stringaTreno" id="stringaTreno" class="form-control " placeholder="Treno" required  style="width: 62%">
        </div>
        <button type="submit" class="btn btn-green">Aggiungi</button>
        <%=errorTreno %>
    </form>
    </div>

    <!-- Form per rimuovere un treno -->
    <div class="col">
    <form action="<%=webApp %>/admin/deleteTrain" method="post" class="mb-4">
        <h5>Rimuovi un Treno:</h5>
        <div class="mb-3">
            <label for="treno" class="form-label">Seleziona un treno:</label>
            <select name="treno" id="treno" class="form-select" required style="width: 62%">
                <% 
                    for (Treno treno : treni) {
                %>
                    <option value="<%= treno.getId() %>"><% out.print(treno.getId() + " - " + treno.getCodice()); %></option>
                <% } %>
            </select>
        </div>
        <button type="submit" class="btn btn-purple">Rimuovi</button>
    </form>
    </div>
    </div>
    </div>
    </div>
    <br>
<div class="card shadow-lg p-3 mb-5 bg-body rounded col-md-auto" style="margin-left: 25%; margin-right: 25%">
<div class="card-body" style="margin-left: 50px">
    <!-- Form per aggiungere un biglietto -->
    <form action="<%=webApp %>/admin/addTicket" method="post" class="mb-4">
        <h5>Crea un Biglietto:</h5>
    <div class="mb-3">
        
            <label cfor="codice" class="form-label">Codice:</label>
            <input type="text" name="codice" class="form-control" style="width: 30%" required >
      </div>
      <div class="mb-3 row">
      <div class="col">
            <label for="classe">Classe:</label>
            <select name="classe" class="form-control" style="width: 62%" required>
                <option value="economy">Economy</option>
                <option value="business">Business</option>
                <option value="first-class">First Class</option>
            </select>
       </div> 
       <div class="col">
        
            <label for="prezzo">Prezzo:</label>
            <input type="number" name="prezzo" class="form-control" style="width: 62%" required>
        </div>
         </div> 
        <div class="mb-3 row">
             <div class="col">
                    <label for="dataPartenza" class="form-label">Data Partenza:</label>
                    <input type="datetime-local" name="dataPartenza" class="form-control" style="width: 62%" required>
                </div>
                <div class="col">
                    <label for="dataArrivo" class="form-label">Data Arrivo:</label>
                    <input type="datetime-local" name="dataArrivo" class="form-control" style="width: 62%" required>
                </div>
        </div>
        <div class="mb-3 row">
        	<div class="col">
            <label for="luogoPartenza" class="form-label" >Luogo Partenza:</label>
            <select name="luogoPartenza" class="form-select" style="width: 62%">
            <option value="">Seleziona partenza</option>
            <% 
            for (Citta c : citta) {
            %>
                <option value="<%= c.getNomeCitta() %>"><%= c.getNomeCitta() %></option>
            <% } %>
            </select>
            </div>
       
        <div class="col">
            <label for="luogoArrivo" class="form-label" >Luogo Arrivo:</label>
            <select name="luogoArrivo" class="form-select" style="width: 62%">
            <option value="">Seleziona arrivo</option>
            <% 
            for (Citta c : citta) {
            %>
                <option value="<%= c.getNomeCitta() %>"><%= c.getNomeCitta() %></option>
            <% } %>
            </select>
        </div>
        </div>

        <div class="mb-3 row">
        <div class="col">
            <label for="treno_id" class="form-label" >Treno:</label>
            <select name="treno_id" id="trenoSelect" class="form-select" onchange="updateVagoni()" style="width: 62%">
            <option value="">Seleziona un treno</option>
            <%
            Treno trenoSelezionato = null;
            for (Treno treno : treni) {
            %>
            <option value="<%=treno.getId()%>"><% out.print(treno.getId() + " - " + treno.getCodice()); %> <%trenoSelezionato = treno; %></option>
            
            <%} %>
            </select>
       </div>
        <div class="col">
            <label for="vagone_id" class="form-label" >Vagone:</label>
            <select name="vagone_id" id="vagoneSelect" class="form-select" style="width: 62%">
            <option value="">Seleziona un vagone</option>
            <%
            for (Vagone vagone : trenoSelezionato.getVagoni()){
            %>
            <option value="<%=vagone.getId()%>"><%= vagone.getId() %> - <%=vagone.getCarattere() %> </option>
            <%} %>
            </select>
            </div>
        </div>
    
    	
        <button type="submit" class="btn btn-green">Aggiungi</button>
    </form>
<%=errorTicket %>
    <!-- Form per rimuovere un biglietto -->
    <form action="<%=webApp %>/admin/deleteTicket" method="post">
    <br>
        <h5>Rimuovi un Biglietto:</h5>
        <div class="mb-3">
            <label for="ticket" class="form-label" class="form-control" style="width: 30%">Seleziona un ticket:</label>
            <select name="ticket" id="ticket" class="form-select" class="form-control" style="width: 30%" required>
                <% 
                    for (Ticket ticket : tickets) {
                %>
                    <option id="<%=ticket.getId()%>" value="<%=ticket.getId()%>"><%= ticket.getCodice() %></option>
                <% } %>
            </select>
        </div>
        <button type="submit" class="btn btn-purple">Rimuovi</button>
    </form>
</div>
</div>

<script>
    function updateVagoni() {
        var trenoSelect = document.getElementById('trenoSelect');
        var vagoneSelect = document.getElementById('vagoneSelect');

        var selectedTrenoId = trenoSelect.value;

        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var vagoni = JSON.parse(xhr.responseText);

                vagoneSelect.innerHTML = '';
                for (var i = 0; i < vagoni.length; i++) {
                    var option = document.createElement('option');
                    option.value = vagoni[i].id;
                    option.text = vagoni[i].id + ' - ' + vagoni[i].carattere;
                    vagoneSelect.appendChild(option);
                }
            }
        };

        xhr.open('GET', '<%=webApp%>/treno/vagoni?trenoId=' + selectedTrenoId, true);
        xhr.send();
    }
</script>

</body>
        <style>
            .btn-green{
                --bs-btn-color:#fff;
                --bs-btn-bg:#50ba81; !important
                --bs-btn-border-color:#50ba81; !important
                --bs-btn-hover-color:#fff;
                --bs-btn-hover-bg:#319e63;
                font-weight: bold;
                --bs-btn-hover-border-color:#146c43;
                --bs-btn-focus-shadow-rgb:60,153,110;
                --bs-btn-active-color:#fff;
                --bs-btn-active-bg:#319e63;
                --bs-btn-active-border-color:#1f8764;
                --bs-btn-active-shadow:inset 0 3px 5px rgba(0, 0, 0, 0.125);
                --bs-btn-disabled-color:#fff;
                --bs-btn-disabled-bg:#50ba81; !important
                --bs-btn-disabled-border-color:#50ba81 !important
            }
            .btn-purple{
                --bs-btn-color:#fff;
                --bs-btn-bg:#874692; !important
                --bs-btn-border-color:#874692; !important
                --bs-btn-hover-color:#fff;
                --bs-btn-hover-bg:#8442a3;
                font-weight: bold;
                --bs-btn-hover-border-color:#672584;
                --bs-btn-focus-shadow-rgb:60,153,110;
                --bs-btn-active-color:#fff;
                --bs-btn-active-bg:#8442a3;
                --bs-btn-active-border-color:#3c105e;
                --bs-btn-active-shadow:inset 0 3px 5px rgba(0, 0, 0, 0.125);
                --bs-btn-disabled-color:#fff;
                --bs-btn-disabled-bg:#874692; !important
                --bs-btn-disabled-border-color:#874692 !important
            }

        </style>

</html>
