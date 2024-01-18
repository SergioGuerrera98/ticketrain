<%@page import="com.corso.ticketrain.model.Ticket"%>
<%@page import="java.util.Map"%>
<%@page import="com.corso.ticketrain.model.TicketUser"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
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
      <% if (mappaTicket != null) {
        for (Ticket t : mappaTicket.keySet()) {  %>
        <div class="accordion-item">
          <h2 class="accordion-header">
            <button class="accordion-button collapsed " type="button" data-bs-toggle="collapse" data-bs-target="#collapse<%=t.getCodice()%>" aria-expanded="false" aria-controls="collapse<%=t.getCodice()%>">
              <div class="row">
                <div class="col">
                  <p class="fw-lighter">Codice</p>
                  <p class="fw-semibold"><%=t.getCodice()%></p>
                </div>
                <div class="col">
                  <p class="fw-lighter">Partenza</p>
                  <p class="fw-semibold"><%=t.getLuogoPartenza()%></p>
                </div>
                <div class="col">
                  <p class="fw-lighter">Destinazione</p>
                  <p class="fw-semibold"><%=t.getLuogoArrivo()%></p>
                </div>
              </div>
            </button>
          </h2>
          <div id="collapse<%=t.getCodice()%>" class="accordion-collapse collapse " data-bs-parent="#accordionExample">
            <div class="accordion-body">
              <div class="col">
                <div class="row">
                  <p>Partenza da : <%=t.getLuogoPartenza() %>, ore : <%=t.getDataPartenzaStr()%></p>
                          
                </div>
                <div class="row">
                  <p>Arrivo a : <%=t.getLuogoArrivo() %>, ore : <%=t.getDataArrivoStr()%></p>
                </div>
                <div class="row">
                  <p>Prezzo: <% double money = t.getPrezzo() * mappaTicket.get(t).size();
                                NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
                                String moneyString = formatter.format(money);
                                out.print(moneyString.substring(0, moneyString.length()-2)); %> euro</p>
                </div>
                <div class="row">
                  <p>Classe: <%=t.getClasse()%> </p>
                </div>
              </div>
              <h6> Biglietti acquistati: </h6>
                <%  for (TicketUser tu : mappaTicket.get(t)) { %>
                      <div class="col">
                        <div class="row">
                          <label class="fw-normal">&nbsp;&nbsp;&nbsp;&nbsp;Nome: <%=tu.getNome() %> </label>
                        </div>
                        <div class="row">
                          <label class="fw-normal">&nbsp;&nbsp;&nbsp;&nbsp;Cognome: <%=tu.getCognome() %> </label>
                        </div>
                        <div class="row">
                          <label class="fw-normal">&nbsp;&nbsp;&nbsp;&nbsp;Vagone: <%=tu.getTicket().getVagone_id().getId()%> Posto: <%=tu.getPosto()%> </label>
                        </div>
                        <br>
                      </div>
                <%}%>
            </div>
          </div>
        </div>
      <%}}%>
    </div>
  </div>
</div>

<style>
  .accordion-button:focus {
    background-color: #28a745; 
    color: #fff; 
    border-color: #28a745; 
    box-shadow: 0 0 0 0.25rem rgba(40, 167, 69, 0.25);
    z-index: 1;
  }
</style>
