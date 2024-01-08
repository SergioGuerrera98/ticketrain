<%@page import="com.corso.ticketrain.model.User"%>
<%@page import="com.corso.ticketrain.model.Ticket"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%
        String webApp = request.getContextPath();
        String formAction = webApp + "/login";
        String errorLabel = (request.getAttribute("error") != null) ? (String) request.getAttribute("error") : ""; 
        Ticket ticket = (Ticket) session.getAttribute("ticket");
        User user = (User) session.getAttribute("UserLoggato");
    %>
    <head>
    <meta charset="ISO-8859-1">
    <title>Compra biglietti</title>
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="<%=webApp%>/css">
    </head>
<body>
    
<header>
</header>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<a class="navbar-brand" href="<%=webApp%>/home">TickeTrain</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="<%=webApp%>/home">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="<%=webApp%>/account">Area Personale (<%=user.getUsername() %>)</a></li>
                        <li class="nav-item"><a class="nav-link" href="<%=webApp%>/user/logout">Logout</a></li>
                       
                    </ul>
		</div>
	</nav>
<h1>Procedi con l'acquisto</h1>

    <div id="dettagliTreno">
        <table>
            <tr>
                <td>Partenza da <%=ticket.getLuogoPartenza()%></td><td><%=ticket.getDataPartenza()%></td>
            </tr>
            <tr>
                <td>Arrivo a <%=ticket.getLuogoArrivo()%></td><td><%=ticket.getDataPartenza()%></td>
            </tr>
        </table>
    </div>

    <div id="postiCounter">
        <p>Numero posti:</p>
        <div>
            <button onclick="postiMinus()">-</button>
            <p id="postiSelezionati"></p>
            <button onclick="postiPlus()">+</button>
        </div>
    </div>

    <div id="formPosti">
        <div class="card" id="p1"  visibility="visible">
            <table>
                <tr>
                    <td>Nome:</td>
                    <td><input type="text" name="nomeP1" placeholder="Nome passeggero"/>
                </tr>
                <tr>
                    <td>Cognome:</td>
                    <td><input type="text" name="cognomeP1" placeholder="Cognome passeggero"/>
                </tr>
            </table>
        </div>

        <div class="card" id="p2" visibility="hidden">
            <table>
                <tr>
                    <td>Nome:</td>
                    <td><input type="text" name="nomeP2" placeholder="Nome passeggero"/>
                </tr>
                <tr>
                    <td>Cognome:</td>
                    <td><input type="text" name="cognomeP2" placeholder="Cognome passeggero"/>
                </tr>
            </table>
        </div>

        <div class="card" id="p3"  visibility="hidden">
            <table>
                <tr>
                    <td>Nome:</td>
                    <td><input type="text" name="nomeP3" placeholder="Nome passeggero"/>
                </tr>
                <tr>
                    <td>Cognome:</td>
                    <td><input type="text" name="cognomeP3" placeholder="Cognome passeggero"/>
                </tr>
            </table>
        </div>

        <div class="card" id="p4"  visibility="hidden">
            <table>
                <tr>
                    <td>Nome:</td>
                    <td><input type="text" name="nomeP4" placeholder="Nome passeggero"/>
                </tr>
                <tr>
                    <td>Cognome:</td>
                    <td><input type="text" name="cognomeP4" placeholder="Cognome passeggero"/>
                </tr>
            </table>
        </div>

        <div class="card" id="p5"  visibility="hidden">
            <table>
                <tr>
                    <td>Nome:</td>
                    <td><input type="text" name="nomeP5" placeholder="Nome passeggero"/>
                </tr>
                <tr>
                    <td>Cognome:</td>
                    <td><input type="text" name="cognomeP5" placeholder="Cognome passeggero"/>
                </tr>
            </table>
        </div>

        <div class="card" id="p6"  visibility="hidden">
            <table>
                <tr>
                    <td>Nome:</td>
                    <td><input type="text" name="nomeP6" placeholder="Nome passeggero"/>
                </tr>
                <tr>
                    <td>Cognome:</td>
                    <td><input type="text" name="cognomeP6" placeholder="Cognome passeggero"/>
                </tr>
            </table>
        </div>

        <div class="card" id="p7"  visibility="hidden">
            <table>
                <tr>
                    <td>Nome:</td>
                    <td><input type="text" name="nomeP7" placeholder="Nome passeggero"/>
                </tr>
                <tr>
                    <td>Cognome:</td>
                    <td><input type="text" name="cognomeP7" placeholder="Cognome passeggero"/>
                </tr>
            </table>
        </div>

        <div class="card" id="p8"  visibility="hidden">
            <table>
                <tr>
                    <td>Nome:</td>
                    <td><input type="text" name="nomeP8" placeholder="Nome passeggero"/>
                </tr>
                <tr>
                    <td>Cognome:</td>
                    <td><input type="text" name="cognomeP8" placeholder="Cognome passeggero"/>
                </tr>
            </table>
        </div>
    </div>

        <button onclick="procedi(<%=webApp%>, <%=ticket.getLuogoPartenza()%>, <%=ticket.getLuogoArrivo()%>,
                     <%=ticket.getDataPartenza() %>, <%=ticket.getDataArrivo()%>)">Paga</button>

	<div id="carouselExampleAutoplaying" class="carousel slide"
    data-bs-ride="carousel">
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img
                src="https://static.nexilia.it/vologratis/2015/03/sconto-italo-under-30-768x399.jpg"
                class="d-block w-100" style="max-width: 50%; height: 500px; margin-left: 25%" alt="...">
                <h5>Tratte aggiornate tutto l'anno</h5>
        		<p>Spostati dove vuoi, quando vuoi.</p>
        </div>
        <div class="carousel-item">
            <img
                src="https://www.newsabruzzo.it/wp-content/uploads/2023/01/Treno-23012023-NewsAbruzzo.it_.jpg"
                class="d-block w-100" style="max-width: 50%; height: 500px; margin-left: 25%" alt="...">
                <h5>Visita l'Italia a basso costo</h5>
        		<p>Prezzi scontati nel weekend.</p>
        </div>
        <div class="carousel-item">
            <img
                src="https://getwallpapers.com/wallpaper/full/9/1/d/364210.jpg"
                class="d-block w-100" style="max-width: 50%; height: 500px; margin-left: 25%" alt="...">
                <h5>Affidabilità e puntualità garantite</h5>
        		<p>Rimborso parziale in caso di ritardo.</p>
        </div>
    </div>
    <button class="carousel-control-prev" type="button"
        data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
            class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button"
        data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span> <span
            class="visually-hidden">Next</span>
    </button>
</div>
        <footer><jsp:include page="/WEB-INF/jsp/Footer.jsp"></jsp:include></footer>
        <script type="text/javascript">

            let c = 1;
            let postiMinus = document.getElementById('postiMinus');
            let postiPlus = document.getElementById('postiPlus');
            let postiSelezionati = document.getElementById('postiSelezionati');
            let divPasseggeri = document.getElementById('postiSelezionati');

            function postiMinus() {
                if (c > 1){
                    visibilitaCarta(false, c);    
                    c--; 
                    postiSelezionati.innerText = `${c}`;
                }
            })
            function postiPlus () {
                if (c < 8){
                    visibilitaCarta(true, c);    
                    c++; 
                    postiSelezionati.innerText = `${c}`;
                }
            })

            function visibilitaCarta(vis, index) {
                if (vis == true)
                    document.getElementById('p' + index).style.visibility = visibility;
                else
                    document.getElementById('p' + index).style.visibility = hidden;
            }

            function procedi(webapp, partenza, arrivo, oraPartenza, oraArrivo) {
                var message = "Vuoi confermare l'acquisto di ";
                if (c > 1)
                    message += c + " biglietti ";
                else
                    message += "1 biglietto ";
                
                message += "per il treno:\n" +
                                "Partenza: " + partenza + "\n" +
                                "Arrivo: " + arrivo + "\n" +
                                "Ora di Partenza: " + oraPartenza + "\n" +
                                "Ora di Arrivo: " + oraArrivo;
                
                const xhr = new XMLHttpRequest();
                xhr.open("POST", webapp+"/ticketUser/confirm");
                xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8")
                
                String jsonBody = "{[";

                for (index = 1; index <= 8; ++index){
                    if (document.getElementById('p' + index).style.visibility == "visible") {
                        jsonBody += "{ \"ticket\" : \"" + ticket + "\"";
                        jsonBody += "{ \"nome\" : \"" + document.getElementById("nomeP" + index) + "\"";
                        jsonBody += "{ \"cognome\" : \"" + document.getElementById("cognomeP" + index) + "\"";
                    }
                }

                jsonBody += "]}";

                xhr.onload = () => {
                    if (xhr.status < 400) {
                        window.location.replace(xhr.responseURL;);
                    } else {
                        console.log(`Error: ${xhr.status}`);
                    }
                };

                if (confirm(message)) {
                    xhr.send(jsonBody);
                }
            }

        </script>
    </body>
</html>