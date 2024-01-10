<%@page import="com.corso.ticketrain.model.User"%>
<%@page import="com.corso.ticketrain.model.Ticket"%>
<%@page import="java.util.Map"%>
<%@page import="com.corso.ticketrain.model.TicketUser"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html data-bs-theme="dark">
    <%
        User user = (User) session.getAttribute("UserLoggato"); 
        String webApp = request.getContextPath();
        String formAction = webApp + "/AreaPersonale";
        
        Map<Ticket, List<TicketUser>> mappaTicket = (Map<Ticket, List<TicketUser>>) request.getAttribute("listaTickets");
    %>	
    <head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" ></script>
        <meta charset="ISO-8859-1">
        <title>Area Personale</title>
    </head>
    <body>
        <header>
            <jsp:include page="/WEB-INF/jsp/components/Header.jsp"></jsp:include>
        </header>


        <!-- BANNER TODO -->
        <div class="" style="margin-left: auto; margin-right: auto; width: 50%">
            
    
        <div class="card" style="width: 18rem;">
             <!--<img src="..." class="card-img-top" alt="...">-->
            <div class="card-body">
                <h5 class="card-title"></h5>
                <p class="card-text">Qui puoi trovare i tuoi Ticket o acquistarne di nuovi.</p>
                <a href="<%=request.getContextPath() + "/home"%>" class="btn btn-primary">
                    <i class="bi bi-cart-plus">
                        Acquista un nuovo ticket
                    </i>
                </a>
            </div>
        </div>
        </div>
        <h3>Ticket Acquistati</h3>
        <jsp:include page="/WEB-INF/jsp/components/BigliettiComprati.jsp"></jsp:include>
        
        <footer><jsp:include page="/WEB-INF/jsp/components/Footer.jsp"></jsp:include></footer>
    </body>
</html>