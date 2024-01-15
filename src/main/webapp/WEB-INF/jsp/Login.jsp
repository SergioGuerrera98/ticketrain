<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%String tema = (session.getAttribute("tema") != null) ? (String) session.getAttribute("tema") : "dark";  %>
<html id="htmlId" data-bs-theme="<%=tema%>">
    <%
        String webApp = request.getContextPath();
        String formAction = webApp + "/user/login";
        String errorLabel = (request.getAttribute("error") != null) ? (String) request.getAttribute("error") : ""; 
    %>
    <head>
        <title>Log-in</title>
         <link rel="shortcut icon" type="image/png" href="https://raw.githubusercontent.com/IlanZdd/resources/main/topolino.png">
        <meta charset="ISO-8859-1">
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" ></script>
    </head>
    <body>

            <header>
                <jsp:include page="/WEB-INF/jsp/components/Header.jsp"></jsp:include>
            </header>

            <div class="position-absolute top-50 start-50 translate-middle border
                        shadow-lg p-3 mb-5 bg-body-tertiary rounded" style="padding : 10px">
            <h3>Log-in</h3>
            <p>Effettua l'accesso per acquistare biglietti o visitare l'area personale:</p>
                <jsp:include page="/WEB-INF/jsp/components/FormLogin.jsp"></jsp:include>
            <p><a href="<%=webApp%>/signup">Non sei ancora registrato?</a></p>
            </div>


    </body>
</html>