<%@page import="com.corso.ticketrain.model.Ticket"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String webApp = request.getContextPath();
String formAction = webApp + "/ticket/getByFilter";
String errorLabel = (request.getAttribute("error") != null) ? (String) request.getAttribute("error") : ""; 
List<Ticket> listaFiltrata = (List<Ticket>) request.getAttribute("filteredTickets");

%>
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
                window.location.replace(xhr.responseText);
            } else {
                console.log(`Error: ${xhr.status}`);
            }
        };

        xhr.send();

    }
</script>
</body>
</html>
