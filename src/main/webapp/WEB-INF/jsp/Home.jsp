<%@page import="com.corso.ticketrain.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <%
        //Variabili java
        User user = (User) session.getAttribute("UserLoggato"); 
        String errorLabel = (String) request.getAttribute("erroreLabel");
        String webApp = request.getContextPath();
    %>
    <head>
        <meta charset="ISO-8859-1">
        <title>Home</title>
       

        <!-- sbootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" ></script>
        
        <!-- Stylesheets -->
        <link rel="stylesheet" type="text/css" href="https://raw.githubusercontent.com/IlanZdd/resources/main/styles.css" />
        <link rel="stylesheet" type="text/css" href="https://app.netlify.com/sites/peppy-brioche-826172/deploys/659d72ac64cc75171f413e7c" />
    </head>

    <body>
        <header>
            <!-- Header : 
                TickeTrain Accedi SignUp                    #se non loggato
                TicketTrain :) LogOut                       #se loggato
                TicketTrain :) LogOut PaginaAmminstratore   #se admin-->
            <nav class="navbar navbar-expand-lg bg-body-tertiary">
                <div class="container-fluid">
                    <!-- TicketTrain -->
                    <a class="navbar-brand" href="<%=webApp%>/home">
                        <img src="https://raw.githubusercontent.com/IlanZdd/resources/main/nameSite.png" style="width: ; height: 40px">
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
        </header> 

            <!-- Carosello -->
        <div id="carousel-container" style="max-width: 70%; height: 15%; margin-left: 15%; margin-right: 15%; padding: 20px" > 
            <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Sconto30"  data-bs-interval="0.1"></button>
                    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Treno1" data-bs-interval="0.1"></button>
                    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Treno2"></button>
                </div>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                    <img src="https://static.nexilia.it/vologratis/2015/03/sconto-italo-under-30-768x399.jpg" style="height: 25%; width: 50%" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                        <h5>Tratte aggiornate tutto l'anno</h5>
                        <p>Spostati dove vuoi, quando vuoi.</p>
                    </div>
                    </div>
                    <div class="carousel-item">
                    <img src="https://www.newsabruzzo.it/wp-content/uploads/2023/01/Treno-23012023-NewsAbruzzo.it_.jpg" style="height: 25%; width: 50%" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                        <h5>Visita l'Italia a basso costo</h5>
                        <p>Prezzi scontati nel weekend.</p>
                    </div>
                    </div>
                    <div class="carousel-item">
                    <img src="https://getwallpapers.com/wallpap
                    er/full/9/1/d/364210.jpg" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                        <h5>Affidabilità e puntualità garantite</h5 >
                        <p>Rimborso parziale in caso di ritardo.</p>
                    </div>
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
        </div>
            


        <!-- FILTRO -->
        <div id="filtro" class="card" style="max-width: 70%; height: 15%; margin-left: 15%; margin-right: 15%; padding: 20px">
            <form action="<%=webApp %>/ticket/getByFilter" method="GET">
                <div class="row">
                    <div class="col">
                        <input type="text" class="form-control" name="luogoPartenza" class="form-control" placeholder="Luogo Partenza" aria-label="Luogo Partenza">
                    </div>
                    <div class="col">
                        <input type="text" class="form-control" name="luogoArrivo" class="form-control" placeholder="Destinazione" aria-label="Destinazione">
                    </div>
                    <div class="col">
                        <input type="datetime-local" class="form-control" name="dataPartenza" class="form-control" placeholder="Data Partenza" aria-label="Data Partenza">
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <input type="submit" value="Ricerca tratte" />
                    </div>
                </div>
            </form>
        </div>



        <footer><jsp:include page="/WEB-INF/jsp/Footer.jsp"></jsp:include></footer>    
    </body>
</html>