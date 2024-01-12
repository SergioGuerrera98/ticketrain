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
    List<Ticket> listaFiltrata = (List<Ticket>) request.getAttribute("filteredTickets");
    User user = (User) session.getAttribute("UserLoggato"); 
%>
    <head>
        <meta charset="ISO-8859-1">
        <title>Risultati</title>
        <style>
            .btn-purple-outline{
                --bs-btn-color:#874692  !important;
                --bs-btn-border-color:#874692 !important;
                --bs-btn-hover-color:#fff;
                --bs-btn-hover-bg:#874692;
                --bs-btn-hover-border-color:#874692;
                --bs-btn-focus-shadow-rgb:25,135,84;
                --bs-btn-active-color:#fff;
                --bs-btn-active-bg:#874692;
                --bs-btn-active-border-color:#874692;
                --bs-btn-active-shadow:inset 0 3px 5px rgba(0, 0, 0, 0.125);
                --bs-btn-disabled-color:#874692;
                --bs-btn-disabled-border-color:#874692;
            }
            .btn-green-outline{
                --bs-btn-color:#50ba81  !important;
                --bs-btn-border-color:#50ba81 !important;
                --bs-btn-hover-color:#fff;
                --bs-btn-hover-bg:#50ba81;
                --bs-btn-hover-border-color:#50ba81;
                --bs-btn-focus-shadow-rgb:25,135,84;
                --bs-btn-active-color:#fff;
                --bs-btn-active-bg:#50ba81;
                --bs-btn-active-border-color:#50ba81;
                --bs-btn-active-shadow:inset 0 3px 5px rgba(0, 0, 0, 0.125);
                --bs-btn-disabled-color:#50ba81;
                --bs-btn-disabled-border-color:#50ba81;
            }
        </style>

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

                <h3>Risultati per : </h3>
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
                        int counter = -1;
                        for (Ticket ticket : listaFiltrata) {
                            counter++;
                        %>
                            <tr>
                                <td><%=ticket.getLuogoPartenza() %></td>
                                <td><%=ticket.getLuogoArrivo() %></td>
                                <td><%
                                        LocalDateTime datePartenza = ticket.getDataPartenza();
                                        out.print(datePartenza.getDayOfMonth() + "/");
                                        out.print(datePartenza.getMonthValue() + "/");
                                        out.print(datePartenza.getYear() + " - ");
                                        
                                        out.print(datePartenza.getHour() + ":");
                                        out.print(datePartenza.getMinute()); %></td>
                                <td><%
                                        LocalDateTime dateArrivo = ticket.getDataArrivo();
                                        out.print(dateArrivo.getDayOfMonth() + "/");
                                        out.print(dateArrivo.getMonthValue() + "/");
                                        out.print(dateArrivo.getYear() + " - ");
                                        
                                        out.print(dateArrivo.getHour() + ":");
                                        out.print(dateArrivo.getMinute()); %></td>
                            
                                <td><%=ticket.getPrezzo() %></td>
                                <td><%=ticket.getClasse() %></td>
                                <td>
                                    <% if (counter % 2 == 0) {%>
                                    <button class="btn btn-green-outline" onclick="buyTicket(<%=ticket.getId()%>)">Procedi all'acquisto</button>
                                    <%} else {%>
                                    <button class="btn btn-purple-outline" onclick="buyTicket(<%=ticket.getId()%>)">Procedi all'acquisto</button>
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
    </body>
</html>
