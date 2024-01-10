<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <%
        String webApp = request.getContextPath();
        String formAction = webApp + "/user/login";
        String errorLabel = (request.getAttribute("error") != null) ? (String) request.getAttribute("error") : ""; 
    %>
    <head>
        <title>Log-in</title>
        <meta charset="ISO-8859-1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" ></script>
    </head>
    <body>
    <header>
        <jsp:include page="/WEB-INF/jsp/Header.jsp"></jsp:include>
    </header>

    <!-- BANNER TODO -->

    <h1>Log-in</h1>
    <div>
        <jsp:include page="/WEB-INF/jsp/FormLogin.jsp"></jsp:include>
    </div>
    </body>
</html>