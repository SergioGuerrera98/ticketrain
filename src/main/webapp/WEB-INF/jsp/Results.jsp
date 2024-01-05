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
String formAction = webApp + "/ticket//getByFilter";
String errorLabel = (request.getAttribute("error") != null) ? (String) request.getAttribute("error") : ""; 
%>
<header><jsp:include page="/WEB-INF/jsp/Header.jsp"></jsp:include></header>
    <div style="margin: 20px;">

        <h1>Risultati dei treni</h1>

        <c:if test="${not empty trains}">
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
                    <c:forEach var="ticket" items="${tickets}">
                        <tr>
                            <td>${ticket.dataArrivo}</td>
                            <td>${ticket.dataPartenza}</td>
                            <td>${ticket.luogoPartenza}</td>
                            <td>${ticket.luogoArrivo}</td>
                            <td>${ticket.prezzo}</td>
                            <td>
                               <a href="<%=webApp %>/ticket/getByFilter">Compra Biglietto</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <p class="errorLabel"> <%=errorLabel%> </p>
    </div>
<footer><jsp:include page="/WEB-INF/jsp/Footer.jsp"></jsp:include></footer>
</body>
</html>
