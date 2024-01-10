<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html data-bs-theme="dark">
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
                <jsp:include page="/WEB-INF/jsp/components/Header.jsp"></jsp:include>
            </header>

            <div class="position-absolute top-50 start-50 translate-middle"> <!--style="margin-left: 40%; margin-top: 10%">-->
            <h1>Log-in</h1>
                <jsp:include page="/WEB-INF/jsp/components/FormLogin.jsp"></jsp:include>
            </div>
            

    </body>
</html>