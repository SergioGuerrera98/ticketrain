<%@page import="com.corso.ticketrain.model.Ticket"%>
<%@page import="java.util.Map"%>
<%@page import="com.corso.ticketrain.model.TicketUser"%>
<%@page import="java.util.List"%>
<%@page import="com.corso.ticketrain.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%

        String errorLabel = (String) request.getAttribute("erroreLabel");
        String webApp = request.getContextPath();
        Map<Ticket, List<TicketUser>> mappaTicket = (Map<Ticket, List<TicketUser>>) request.getAttribute("listaTickets");
    %>


<div class="accordion accordion-flush" id="accordionFlushExample">
  <div class="accordion-item">
    <div class="accordion" id="accordionExample">
      <% for (Ticket t : mappaTicket.keySet()) {  %>
        <div class="accordion-item">
          <h2 class="accordion-header">
            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse<%=t.getCodice()%>" aria-expanded="true" aria-controls="collapse<%=t.getCodice()%>">
              <h2><%=t.getCodice() %>  <%=t.getLuogoPartenza()%> - <%=t.getLuogoArrivo() %></h2> 
            </button>
          </h2>
          <div id="collapse<%=t.getCodice()%>" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
            <div class="accordion-body">
              <p>Partenza da : <%=t.getLuogoPartenza() %>, ore : <%=t.getDataPartenza() %></p>
              <p>Arrivo a : <%=t.getLuogoArrivo() %>, ore : <%=t.getDataArrivo() %></p>
              <p>Prezzo: <%=t.getPrezzo()%> euro</p>
              <h3> Biglietti acquistati: </h3>
              <%  for (TicketUser tu : mappaTicket.get(t)) { %>
               <p>Nome: <%=tu.getNome() %> Cognome: <%=tu.getCognome() %></p>
              <%}%>
            </div>
          </div>
        </div>
      <%}%>
    </div>
  </div>
</div>
