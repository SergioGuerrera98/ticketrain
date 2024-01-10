<%@page import="com.corso.ticketrain.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%

        User user = (User) session.getAttribute("UserLoggato"); 
        String errorLabel = (String) request.getAttribute("erroreLabel");
        String webApp = request.getContextPath();
    %>

        <!-- Header : 
                        TickeTrain Accedi SignUp                    #se non loggato
                        TicketTrain :) LogOut                       #se loggato
                        TicketTrain :) LogOut PaginaAmminstratore   #se admin-->


<nav class="navbar navbar-expand-lg bg-body-tertiary" style="padding: 10px">
    <div class="container-fluid">
        <!-- TicketTrain -->
        <a class="navbar-brand" href="<%=webApp%>/home">
            <img src="https://raw.githubusercontent.com/IlanZdd/resources/main/nameSiteDark.png" style="width: ; height: 40px">
        </a>
        <button class="navbar-toggler" type="button"
            data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false"
            aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent" style="alignment: right">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <!-- <li class="nav-item"> <a class="nav-link active" aria-current="page" href="<%=webApp%>/home">  Home  </a> </li> -->
                <% if (user == null) { %> <!-- UTENTE NON LOGGATO -->
                    <li class="nav-item">
                        <a class="nav-link" href="<%=webApp%>/login">Accedi</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=webApp%>/signup">Registrati</a>
                    </li>
                <% } if (user != null) { %> <!-- UTENTE LOGGATO -->
                    <li class="nav-item">
                        <a class="nav-link" href="<%=webApp%>/account">
                            <label><%=user.getUsername() %></label>
                        <img src="https://raw.githubusercontent.com/IlanZdd/resources/main/areaPersonale.png" style="width: 30px;"><!-- Area Personale (<%=user.getUsername() %>) -->
                        </a>
                    </li>
                    <li class="nav-item"><a class="nav-link" href="<%=webApp%>/user/logout">Logout</a></li>
                    <% if (user.isAmministratore()) { %>
                        <li class="nav-item"><a class="nav-link" href="<%=webApp%>/user/admin">Pagina Amministratore</a></li>
                    <%}
                } %>
            </ul>
        </div>
    </div>
</nav>