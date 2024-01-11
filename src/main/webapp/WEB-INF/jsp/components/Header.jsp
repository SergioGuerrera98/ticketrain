<%@page import="com.corso.ticketrain.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%

        User user = (User) session.getAttribute("UserLoggato"); 
        String errorLabel = (String) request.getAttribute("erroreLabel");
        String webApp = request.getContextPath();
        String displayTema = (String) session.getAttribute("tema");
        if (displayTema == null) displayTema = "dark";

    %>

        <!-- Header : 
                        TickeTrain Accedi SignUp                    #se non loggato
                        TicketTrain :) LogOut                       #se loggato
                        TicketTrain :) LogOut PaginaAmminstratore   #se admin-->


<nav class="navbar navbar-expand-lg bg-body-tertiary" style="padding: 10px">
    <div class="container-fluid">
        <!-- TicketTrain -->
        <a class="navbar-brand" href="<%=webApp%>/home">
        <%if (displayTema.equals("light")){ %>
            <img id="logo" src="https://raw.githubusercontent.com/IlanZdd/resources/main/nameSiteDark.png" style="width: ; height: 40px">
            <%} %>
         <%if (displayTema.equals("dark")){ %>
         <img src="https://raw.githubusercontent.com/IlanZdd/resources/main/nameSite.png" style="width: ; height: 40px">
         <%} %>
        </a>
        <button class="navbar-toggler" type="button"
            data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false"
            aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse position-absolute bottom-50 top-50 end-0"id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <!-- <li class="nav-item"> <a class="nav-link active" aria-current="page" href="<%=webApp%>/home">  Home  </a> </li> -->
                <% if (user == null) { %> <!-- UTENTE NON LOGGATO -->
                    <li class="nav-item">
                        <a class="nav-link" href="<%=webApp%>/login" style="padding : 5px" >Accedi</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=webApp%>/signup" style="padding : 5px" >Registrati</a>
                    </li>
                <% } if (user != null) { %> <!-- UTENTE LOGGATO -->
                    <li class="nav-item">
                        <a  style="padding : 5px" class="nav-link" href="<%=webApp%>/account">
                            <label><%=user.getUsername() %></label>
                         <img src="https://raw.githubusercontent.com/IlanZdd/resources/main/areaPersonale.png" style="width: 30px;"><!-- Area Personale (<%=user.getUsername() %>) -->
                        </a>
                    </li>
                    <li class="nav-item"><a  style="padding : 5px" class="nav-link" href="<%=webApp%>/user/logout">Logout</a></li>
                    <% if (user.isAmministratore()) { %>
                        <li class="nav-item"><a  style="padding : 5px" class="nav-link" href="<%=webApp%>/user/admin">Pagina Amministratore</a></li>
                    <%}
                } %>
                <li class="nav-item">
                        <img id="imgTema" class="bottom-0 top-100" 
                        <% if (displayTema == null || displayTema.equals("dark")) { %> 
                            src="https://raw.githubusercontent.com/IlanZdd/resources/main/sun.png"
                        <% } else { %>
                            src="https://raw.githubusercontent.com/IlanZdd/resources/main/moon.png"
                        <% } %>
                            style="witdh : 32px; height : 32px; padding : 5px" onclick="cambiaTema()">   
                </ul>
        </div>
    </div>
</nav>

<script>
    function cambiaTema() {
        changeTheme();
        if (document.getElementById("htmlId").getAttribute('data-bs-theme') == 'dark') {
            document.getElementById("htmlId").setAttribute('data-bs-theme','light');
            document.getElementById("imgTema").setAttribute('src','https://raw.githubusercontent.com/IlanZdd/resources/main/moon.png');
            document.getElementById("logo").setAttribute('src','https://raw.githubusercontent.com/IlanZdd/resources/main/logoLT.png');
        } else {
            document.getElementById("htmlId").setAttribute('data-bs-theme','dark');
            document.getElementById("imgTema").setAttribute('src','https://raw.githubusercontent.com/IlanZdd/resources/main/sun.png');
            document.getElementById("logo").setAttribute('src','https://raw.githubusercontent.com/IlanZdd/resources/main/logoBT.png');
        }
    }
    
    function changeTheme() {
        const xhr = new XMLHttpRequest();
        xhr.open("POST", "<%=webApp%>/ticket/tema");

        xhr.send();
    }
</script>