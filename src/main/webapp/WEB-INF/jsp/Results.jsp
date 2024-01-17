<%@page import="com.corso.ticketrain.model.User"%>
<%@page import="com.corso.ticketrain.model.Ticket"%>
<%@page import="java.util.List"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%String tema = (session.getAttribute("tema") != null) ? (String) session.getAttribute("tema") : "dark";  %>
<html id="htmlId" data-bs-theme="<%=tema%>">
<%
    String webApp = request.getContextPath();
    String formAction = webApp + "/ticket/getByFilter";
    String errorLabel = (request.getAttribute("error") != null) ? (String) request.getAttribute("error") : ""; 
    List<Ticket> listaFiltrata = (List<Ticket>) session.getAttribute("filteredTickets");
    User user = (User) session.getAttribute("UserLoggato"); 
%>
    <head>
        <meta charset="ISO-8859-1">
        <title>Risultati</title>
         <link rel="shortcut icon" type="image/png" href="https://raw.githubusercontent.com/IlanZdd/resources/main/topolino.png">
        


        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" ></script>
        <link rel="stylesheet" type="text/css" href="<%=webApp%>/css">
    </head>
    <body>
        <header>
            <jsp:include page="/WEB-INF/jsp/components/Header.jsp"></jsp:include>
        </header>
            <!-- BANNER TODO -->
            <br>
            <jsp:include page="/WEB-INF/jsp/components/Filter.jsp"></jsp:include>
            <br>
            <div style="margin: 20px;">
<h3>Ordina per: </h3>   
<div class="d-flex justify-content-start">
    <div>
        <form action="<%=webApp %>/ticket/filtroPrezzo" method="GET">
            <button class="btn btn-green">Prezzo</button>
        </form>
    </div>
    <div style="margin-left: 10px">
        <form action="<%=webApp %>/ticket/filtroPartenza" method="GET">
            <button class="btn btn-green">Partenza</button>
        </form>
    </div>
    <div style="margin-left: 10px">
        <form action="<%=webApp %>/ticket/filtroArrivo" method="GET">
            <button class="btn btn-green">Arrivo</button>
        </form>
    </div>
</div>
<br>
                <h3>Risultati: </h3>
                <%
            if (!listaFiltrata.isEmpty()) {
            %>
                <table class="table table-striped">
                    <thead class="thead-dark">
                        <tr>
                            <th>Partenza</th>
                            <th>Arrivo</th>
                            <th>Ora di Partenza</th>
                            <th>Ora di Arrivo</th>
                            <th>Prezzo</th>
                            <th>Classe</th>
                            <th>Azione</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                        int counter = 0;
                        for (Ticket ticket : listaFiltrata) {
                            counter++;
                            double prezzoD = (ticket.getPrezzo()*100) / 100.00;
                            int prezzo = (int) prezzoD;
                        %>
                            <tr>
                                <td><%=ticket.getLuogoPartenza() %></td>
                                <td><%=ticket.getLuogoArrivo() %></td>
                                <td><%=ticket.getDataPartenzaStr()%></td>
                                <td><%=ticket.getDataArrivoStr()%></td>
                                <td><%=prezzo%></td>
                                <td><%=ticket.getClasse() %></td>
                                <td>
                                    <% if (counter % 2 == 0) {%>
                                    <button class="btn btn-green" onclick="buyTicket(<%=ticket.getId()%>)">Procedi all'acquisto</button>
                                    <%} else {%>
                                    <button class="btn btn-purple" onclick="buyTicket(<%=ticket.getId()%>)">Procedi all'acquisto</button>
                                    <%}%>
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

        <footer><jsp:include page="/WEB-INF/jsp/components/Footer.jsp"></jsp:include></footer>

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
    </body>        <style>
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
