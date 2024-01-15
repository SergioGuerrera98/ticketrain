<%@page import="com.corso.ticketrain.model.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%

        User user = (User) session.getAttribute("UserLoggato");
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
        <%if (displayTema == null || displayTema.equals("dark")){ %>
            <img id="logo" src="https://raw.githubusercontent.com/IlanZdd/resources/main/logoBT.png" style="width: ; height: 40px">
            <%} else{ %>
         <img id="logo" src="https://raw.githubusercontent.com/IlanZdd/resources/main/logoLT.png" style="width: ; height: 40px">
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
                <% if (user == null) { %> <!-- UTENTE NON LOGGATO -->
                    <li class="nav-item">
                        <a style="padding : 5px" class="nav-link" href="<%=webApp%>/login" >
                            <label>Accedi</label>
                        <img src="" style="width: 0px; height : 50px" />
                        </a>
                    </li>
                    <li class="nav-item">
                        <a style="padding : 5px" class="nav-link" href="<%=webApp%>/signup" >
                            <label>Registrati</label>
                        <img src="" style="width: 0px; height : 50px" />
                        </a>
                    </li>

                <% } if (user != null) { %> <!-- UTENTE LOGGATO -->
                    <li class="nav-item">
                        <a style="padding : 5px" class="nav-link" href="<%=webApp%>/account">
                            <label><%=user.getUsername() %></label>

                            <%if (user.getPhoto() == null) {%>
                                <img src="https://raw.githubusercontent.com/IlanZdd/resources/main/areaPersonale.png" style="width: 50px;" />
                            
                            <%} if (user.getPhoto() != null) {%>
                                <c:set var="base64Image" value="<%= new String(Base64.getEncoder().encode(user.getPhoto()), StandardCharsets.UTF_8)%>" />
                                <img src="data:image/jpeg;base64,${base64Image}" alt="Foto utente" style="border-radius: 50%; width: 50px; height: 50px;" />
                            <%} %>
                        </a>
                    </li>
                    <li>
                        <img src="" style="width: 20px; height : 0px" />
                    </li>
                    <li class="nav-item ">
                        <a style="padding : 5px" class="nav-link" href="<%=webApp%>/user/logout">
                            <label >Logout</label>
                                <img src="" style="width: 0px; height : 50px" />
                        </a>
                    </li>
                    <% if (user.isAmministratore()) { %>
                        <li class="nav-item ">
                            <a style="padding : 5px" class="nav-link" href="<%=webApp%>/user/admin">
                                <label>Pagina Amministratore</label>
                                <img src="" style="width: 0px; height : 50px" />
                            </a>
                        </li>
                    <%}
                } %>
                    <li class="nav-item ">
                        <img src="" style="width: 0px; height : 50px" />
                            <img id="imgTema" 
                            <% if (displayTema == null || displayTema.equals("dark")) { %> 
                                src="https://raw.githubusercontent.com/IlanZdd/resources/main/sun.png"
                            <% } else { %>
                                src="https://raw.githubusercontent.com/IlanZdd/resources/main/moon.png"
                            <% } %>
                                style="witdh : 42px; height : 42px; padding : 5px" onclick="cambiaTema()">   
                    </li>
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