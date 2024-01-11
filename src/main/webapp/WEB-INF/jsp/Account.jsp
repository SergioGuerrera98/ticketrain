<%@page import="com.corso.ticketrain.model.User"%>
<%@page import="com.corso.ticketrain.model.Ticket"%>
<%@page import="java.util.Map"%>
<%@page import="com.corso.ticketrain.model.TicketUser"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%String tema = (session.getAttribute("tema") != null) ? (String) session.getAttribute("tema") : "dark";  %>
<html id="htmlId" data-bs-theme="<%=tema%>">
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
            
        <br><br>
        <div class="card" style="width: 18rem; margin-left: 35%">
            <div class="card-body">
                <h3 class="card-title">Profilo Personale</h3>
                <p>Username : <%=user.getUsername() %></p>
                <p>Paese : <%=user.getPaese().getNomePaese() %></p>
                <p class="card-text"></p>
                    <i class="bi bi-cart-plus">
                        Carica immagine
                    </i>
                    <br><br>
                    <input type="file" id="photo" name="photo" accept=".jpg,.jpeg,.png">
                <form class="" action="<%=webApp %>/user/foto" method="POST">
                    <br>
                    <div class="d-grid gap-2">
                        <input class="btn btn-outline-success btn-lg" type="submit" value="Carica immagine" />
                    </div>
                </form>
            </div>
        </div>
        <br>
        <a style="width: 18rem; margin-left: 35%" href="<%=request.getContextPath() + "/home"%>" class="btn btn-primary">
                    <i class="bi bi-cart-plus">
                        Acquista un nuovo ticket
                    </i>
                </a>
        <br><br>

        </div>
            
        <div class="card" style="padding : 10px">
            <h3>Ticket Acquistati </h3>
            <jsp:include page="/WEB-INF/jsp/components/BigliettiComprati.jsp"></jsp:include>
        </div>

        <footer><jsp:include page="/WEB-INF/jsp/components/Footer.jsp"></jsp:include></footer>
    </body>
</html>