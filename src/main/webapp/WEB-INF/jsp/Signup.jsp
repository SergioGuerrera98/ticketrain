<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Random"%>
<!DOCTYPE html>
<%String tema = (session.getAttribute("tema") != null) ? (String) session.getAttribute("tema") : "dark";  %>
<html id="htmlId" data-bs-theme="<%=tema%>">
    <head>
        <title>Registrazione</title>
         <link rel="shortcut icon" type="image/png" href="https://raw.githubusercontent.com/IlanZdd/resources/main/topolino.png">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" ></script>
        <meta charset="ISO-8859-1">
        <%
            String webApp = request.getContextPath();
            String formAction = webApp + "/Login";
            String errorLabel = (request.getAttribute("error") != null) ? (String) request.getAttribute("error") : ""; 
        %>
    </head>
    <body>
        <header>
            <jsp:include page="/WEB-INF/jsp/components/Header.jsp"></jsp:include>
        </header>

        <!-- BANNER TODO -->

        
         <div class="position-absolute top-50 start-50 translate-middle border
                        shadow-lg p-3 mb-5 bg-body-tertiary rounded" style="padding : 10px">
            <h3 >Registrati</h3>
        <p>Effettua la registrazione per acquistare biglietti o visitare l'area personale:</p>
            <jsp:include page="/WEB-INF/jsp/components/FormSignup.jsp"></jsp:include>
            <p><a href="<%=webApp%>/login">Sei già registrato? Effettua il log-in</a></p>
   		 </div>
		
        
        <jsp:include page="/WEB-INF/jsp/components/Banner.jsp"></jsp:include>

    </body>
</html>