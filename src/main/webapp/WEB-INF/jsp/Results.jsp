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
        <title>Risultati</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" ></script>
        <link rel="stylesheet" type="text/css" href="<%=webApp%>/css">
    </head>
    <body>
        <header>
            <jsp:include page="/WEB-INF/jsp/Header.jsp"></jsp:include>
        </header>

            <!-- BANNER TODO -->
            <jsp:include page="/WEB-INF/jsp/Filter.jsp"></jsp:include>

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
                                    <td><%=ticket.getLuogoPartenza() %></td>
                                    <td><%=ticket.getLuogoArrivo() %></td>
                                    <td><%=ticket.getDataArrivo() %></td>
                                    <td><%=ticket.getDataPartenza()%></td>
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
