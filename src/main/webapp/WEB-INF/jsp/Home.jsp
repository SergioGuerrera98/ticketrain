<%@page import="com.corso.ticketrain.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%String tema = (session.getAttribute("tema") != null) ? (String) session.getAttribute("tema") : "dark";  %>
<html id="htmlId" data-bs-theme="<%=tema%>">
    <%
        //Variabili java
        User user = (User) session.getAttribute("UserLoggato"); 
        String errorLabel = (String) request.getAttribute("erroreLabel");
        String webApp = request.getContextPath();
    %>
    <head>
        <meta charset="ISO-8859-1">
        <title>Home</title>
       
        <!-- bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" ></script>
        
        <!-- Stylesheets -->
        <link rel="stylesheet" type="text/css" href="https://raw.githubusercontent.com/IlanZdd/resources/main/styles.css" />
        <link rel="stylesheet" type="text/css" href="https://app.netlify.com/sites/peppy-brioche-826172/deploys/659d72ac64cc75171f413e7c" />
    </head>

    <body>
        <header>
            <jsp:include page="/WEB-INF/jsp/components/Header.jsp"></jsp:include>
          
        </header> 


        <%if (user==null) {
            if (tema.equals("dark")){
        %>  
            <div class="offcanvas offcanvas-end show text-bg-dark" tabindex="-1" id="offcanvasDark" aria-labelledby="offcanvasDarkLabel">
                <div class="offcanvas-header">
                <h5 class="offcanvas-title" id="offcanvasDarkLabel">Accedi</h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="offcanvasDark" aria-label="Close"></button>
            </div>
            <%} else if (tema.equals("light")){%>
            <div class="offcanvas offcanvas-end show text-bg-light" tabindex="-1" id="offcanvasLight" aria-labelledby="offcanvasLightLabel">
                <div class="offcanvas-header">
                <h5 class="offcanvas-title" id="offcanvasLightLabel">Accedi</h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="offcanvasLight" aria-label="Close"></button>
                </div>
              <%}  %>
                <div class="offcanvas-body">
                    <div style="margin : 10px">
                        <p class="text-center">Effettua il login per acquistare dei Ticket:</p>
                        <jsp:include page="/WEB-INF/jsp/components/FormLogin.jsp"></jsp:include>
                    </div>
                    <div style="margin : 10px">
                        <p class="text-center">Non hai ancora un account? Registrati:</p>
                        <jsp:include page="/WEB-INF/jsp/components/FormSignup.jsp"></jsp:include>
                    </div>
                </div>
            </div>
        <%} %>
        <!-- Carosello -->
        <jsp:include page="/WEB-INF/jsp/components/Carousel.jsp"></jsp:include>

        <!-- FILTRO -->
        <jsp:include page="/WEB-INF/jsp/components/Filter.jsp"></jsp:include>

        <footer>
            <jsp:include page="/WEB-INF/jsp/components/Footer.jsp"></jsp:include>
        </footer>    
    </body>
    

</html>