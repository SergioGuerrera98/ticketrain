<%@page import="com.corso.ticketrain.model.User"%>
<%@page import="com.corso.ticketrain.model.Ticket"%>
<%@page import="java.util.Map"%>
<%@page import="com.corso.ticketrain.model.TicketUser"%>
<%@page import="java.util.List"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.nio.charset.StandardCharsets" %>

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
    <link href="https://tympanus.net/Tutorials/CustomFileInputs/css/component.css" rel="stylesheet" type="text/css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" ></script>
        <meta charset="ISO-8859-1">
        <title>Area Personale</title>
        <link rel="shortcut icon" type="image/png" href="https://raw.githubusercontent.com/IlanZdd/resources/main/topolino.png">
    </head>
    <body>
        <header>
            <jsp:include page="/WEB-INF/jsp/components/Header.jsp"></jsp:include>
        </header>

        <!-- BANNER TODO -->

        <div class="" style="margin-left: auto; margin-right: auto; width: 50%">
            
        <br><br>
        <div class="card shadow-lg p-3 mb-5 bg-body rounded col-md-auto">
            <div class="card-body col-md-auto" style="padding : 10px">
                <h3 class="card-title text-center">Profilo Personale</h3>
                <div class="container text-center">

                    <div class="col col-md-auto">
                        <div class="row">
                                <label for="usernameText" class="w-auto form-control-plaintext">Username: &nbsp;</label>
                                <input type="text" readonly class="form-control-plaintext w-auto" id="usernameText" value="<%=user.getUsername() %>">
                            </div>
                            <div class="row">
                                <label for="paeseText" class="w-auto form-control-plaintext">&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;Paese: &nbsp;</label>
                                <input type="text" readonly class="form-control-plaintext w-auto" id="paeseText" value="<%=user.getPaese().getNomePaese() %>">
                                <img src="https://flagsapi.com/<%=user.getPaese().getAlpha2()%>/flat/32.png" id="flagPaese" style="width : 64px; height : 32px">
                            </div>
                        </div>
                        <br>
                    <div class="col col-md-auto">
                        <p>Foto: </p>
                            <%if (user.getPhoto() != null){ %>
            
                                <c:set var="base64Image" value="<%= new String(Base64.getEncoder().encode(user.getPhoto()), StandardCharsets.UTF_8) %>" />
                                <img src="data:image/jpeg;base64,${base64Image}" alt="Foto utente" style="border-radius: 50%; width: 100px; height: 100px;" />
                            <%} else {%>
                                <img src="https://raw.githubusercontent.com/IlanZdd/resources/main/addPhotoU.png" alt="Foto utente" style="border-radius: 50%; width: 100px; height: 100px;" />
                            <%}%>

                            <br><br>
                        
                            <form class="" action="<%=webApp %>/user/foto" method="POST" enctype="multipart/form-data">
                                <input type="file" id="file" name="file" class="inputfile inputfile-1" accept="image/*" >
                                    <br>
                                <input class="btn btn-green w-auto" type="submit" id="carica" value="Carica immagine" hidden="true"/>
                            </form>
                    </div>
                </div>
            </div>
        </div>
        <br>
        
        <br><br>

        </div>
            
        <div class="card" style="padding : 10px">
        
            <h3>Ticket Acquistati 
                </h3>
                <a style="width: 18rem; " 
                    href="<%=request.getContextPath() + "/home"%>" class="btn css-button-arrow--blue">
                    <i class="bi bi-outline-success bi-cart-plus">
                        Acquista un nuovo ticket
                    </i>
                </a>
                <br>
            <jsp:include page="/WEB-INF/jsp/components/BigliettiComprati.jsp"></jsp:include>
        </div>

        <footer><jsp:include page="/WEB-INF/jsp/components/Footer.jsp"></jsp:include></footer>

    </body>
    <script>
        document.getElementById("file").addEventListener('input', (evt) => {
            if (document.getElementById("file").value != "") {
                document.getElementById("carica").hidden = false;
            } else {
                document.getElementById("carica").hidden = true;
            }
        });


    </script>
    <style>

            .btn-purple-outline{
                --bs-btn-color:#874692  !important;
                --bs-btn-border-color:#874692 !important;
                --bs-btn-hover-color:#fff;
                --bs-btn-hover-bg:#874692;
                --bs-btn-hover-border-color:#874692;
                --bs-btn-focus-shadow-rgb:25,135,84;
                --bs-btn-active-color:#fff;
                --bs-btn-active-bg:#874692;
                --bs-btn-active-border-color:#874692;
                --bs-btn-active-shadow:inset 0 3px 5px rgba(0, 0, 0, 0.125);
                --bs-btn-disabled-color:#874692;
                --bs-btn-disabled-border-color:#874692;
            }
            .btn-green-outline{
                --bs-btn-color:#50ba81  !important;
                --bs-btn-border-color:#50ba81 !important;
                --bs-btn-hover-color:#fff;
                --bs-btn-hover-bg:#50ba81;
                --bs-btn-hover-border-color:#50ba81;
                --bs-btn-focus-shadow-rgb:25,135,84;
                --bs-btn-active-color:#fff;
                --bs-btn-active-bg:#50ba81;
                --bs-btn-active-border-color:#50ba81;
                --bs-btn-active-shadow:inset 0 3px 5px rgba(0, 0, 0, 0.125);
                --bs-btn-disabled-color:#50ba81;
                --bs-btn-disabled-border-color:#50ba81;
            }

           /* freccia viola */
            .css-button-arrow--blue {
                min-width: 130px;
                height: 40px;
                color: #fff;
                padding: 5px 10px;
                font-weight: bold;
                cursor: pointer;
                transition: all 0.3s ease;
                position: relative;
                display: inline-block;
                outline: none;
                overflow: hidden;
                border-radius: 5px;
                border: none;
                background-color: #874692; !important
            }
            .css-button-arrow--blue:hover {
                border-radius: 5px;
                padding-right: 24px;
                padding-left:8px;
                background-color: #874692; !important
            }
            .css-button-arrow--blue:hover:after {
                opacity: 1;
                right: 10px;
                background-color: #874692; !important
            }
            .css-button-arrow--blue:after {
                ntent: "\00BB";
                position: absolute;
                opacity: 0;
                font-size: 20px;
                line-height: 40px;
                top: 0;
                right: -20px;
                transition: 0.4s;
                background-color: #874692; !important
            }


            .css-button-shadow-border--green {
                min-width: 130px;
                height: 40px;
                color: #fff;
                padding: 5px 10px;
                font-weight: bold;
                cursor: pointer;
                transition: all 0.3s ease;
                position: relative;
                display: inline-block;
                outline: none;
                border-radius: 5px;
                border: none;
                box-shadow:inset 2px 2px 2px 0px rgba(255,255,255,.5), 7px 7px 20px 0px rgba(0,0,0,.1), 4px 4px 5px 0px rgba(0,0,0,.1);
                background: #50ba81;
            }
            .css-button-shadow-border--green:hover {
                background-color: #4ed88e;
            }
            .css-button-shadow-border--green:active {
                top: 2px;
            }


            .btn-green{
                --bs-btn-color:#fff;
                --bs-btn-bg:#50ba81; !important
                --bs-btn-border-color:#50ba81; !important
                --bs-btn-hover-color:#fff;
                --bs-btn-hover-bg:#319e63;
                font-weight: bold;
                --bs-btn-hover-border-color:#146c43;
                --bs-btn-focus-shadow-rgb:60,153,110;
                --bs-btn-active-color:#fff;
                --bs-btn-active-bg:#319e63;
                --bs-btn-active-border-color:#1f8764;
                --bs-btn-active-shadow:inset 0 3px 5px rgba(0, 0, 0, 0.125);
                --bs-btn-disabled-color:#fff;
                --bs-btn-disabled-bg:#50ba81; !important
                --bs-btn-disabled-border-color:#50ba81 !important
            }
        </style>

</html>