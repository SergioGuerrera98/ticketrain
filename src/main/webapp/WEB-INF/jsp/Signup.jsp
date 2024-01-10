<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html data-bs-theme="dark">
    <head>
        <title>Registrazione</title>
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

        <h1>Registrati</h1>
        <div class="position-absolute top-50 start-50 translate-middle">
            <jsp:include page="/WEB-INF/jsp/components/FormSignup.jsp"></jsp:include>
        <p class="labelError"><%=errorLabel %></p>
   		 </div>
		
        
    
    </body>
</html>