<%@page import="com.corso.ticketrain.model.User"%>
<%@page import="com.corso.ticketrain.model.Ticket"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
    <title>Compra biglietti</title>
    <meta charset="ISO-8859-1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="<%=webApp%>/css">
    </head>
    <body>
        
    <header>
        <jsp:include page="/WEB-INF/jsp/Header.jsp"></jsp:include>
    </header>

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
                <button id="postiMin" onclick="postiMinus()" inert="true">-</button>
                <p id="postiSelezionati">1</p>
                <button id="postiPlu" onclick="postiPlus()">+</button>
            </div>
        </div>


        <div id="formPosti">
            <div class="card" id="p1">
                <table>
                    <tr>
                        <td>Nome:</td>
                        <td><input type="text" id="nomeP1" placeholder="Nome passeggero"/>
                    </tr>
                    <tr>
                        <td>Cognome:</td>
                        <td><input type="text" id="cognomeP1" placeholder="Cognome passeggero"/>
                    </tr>
                </table>
            </div>

            <div class="card" id="p2" hidden = "true">
                <table>
                    <tr>
                        <td>Nome:</td>
                        <td><input type="text" id="nomeP2" placeholder="Nome passeggero"/>
                    </tr>
                    <tr>
                        <td>Cognome:</td>
                        <td><input type="text" id="cognomeP2" placeholder="Cognome passeggero"/>
                    </tr>
                </table>
            </div>

            <div class="card" id="p3" hidden = "true">
                <table>
                    <tr>
                        <td>Nome:</td>
                        <td><input type="text" id="nomeP3" placeholder="Nome passeggero"/>
                    </tr>
                    <tr>
                        <td>Cognome:</td>
                        <td><input type="text" id="cognomeP3" placeholder="Cognome passeggero"/>
                    </tr>
                </table>
            </div>

            <div class="card" id="p4" hidden = "true">
                <table>
                    <tr>
                        <td>Nome:</td>
                        <td><input type="text" id="nomeP4" placeholder="Nome passeggero"/>
                    </tr>
                    <tr>
                        <td>Cognome:</td>
                        <td><input type="text" id="cognomeP4" placeholder="Cognome passeggero"/>
                    </tr>
                </table>
            </div>

            <div class="card" id="p5" hidden = "true">
                <table>
                    <tr>
                        <td>Nome:</td>
                        <td><input type="text" id="nomeP5" placeholder="Nome passeggero"/>
                    </tr>
                    <tr>
                        <td>Cognome:</td>
                        <td><input type="text" id="cognomeP5" placeholder="Cognome passeggero"/>
                    </tr>
                </table>
            </div>

            <div class="card" id="p6" hidden = "true">
                <table>
                    <tr>
                        <td>Nome:</td>
                        <td><input type="text" id="nomeP6" placeholder="Nome passeggero"/>
                    </tr>
                    <tr>
                        <td>Cognome:</td>
                        <td><input type="text" id="cognomeP6" placeholder="Cognome passeggero"/>
                    </tr>
                </table>
            </div>

            <div class="card" id="p7" hidden = "true">
                <table>
                    <tr>
                        <td>Nome:</td>
                        <td><input type="text" id="nomeP7" placeholder="Nome passeggero"/>
                    </tr>
                    <tr>
                        <td>Cognome:</td>
                        <td><input type="text" id="cognomeP7" placeholder="Cognome passeggero"/>
                    </tr>
                </table>
            </div>

            <div class="card" id="p8" hidden = "true">
                <table>
                    <tr>
                        <td>Nome:</td>
                        <td><input type="text" id="nomeP8" placeholder="Nome passeggero"/>
                    </tr>
                    <tr>
                        <td>Cognome:</td>
                        <td><input type="text" id="cognomeP8" placeholder="Cognome passeggero"/>
                    </tr>
                </table>
            </div>
        </div>
		<button onclick="procedi('<%=ticket.getLuogoPartenza()%>', '<%=ticket.getLuogoArrivo()%>', '<%=ticket.getDataPartenza()%>', '<%=ticket.getDataArrivo()%>')">Paga</button>
	
        <footer><jsp:include page="/WEB-INF/jsp/Footer.jsp"></jsp:include></footer>
        
        <script>
            function postiMinus() {
                if (Number(document.getElementById('postiSelezionati').innerText) > 1) {
                    console.log('Clicked min');
                    let c = Number(document.getElementById('postiSelezionati').innerText);

                    visibilitaCarta(false, c);
                    document.getElementById('postiSelezionati').innerText = c - 1;

                    if (c - 1 == 1)
                        document.getElementById('postiMin').inert = true;
                    else 
                        document.getElementById('postiPlu').inert = false;
                }
            }

            function postiPlus () {
                if (Number(document.getElementById('postiSelezionati').innerText) < 8){
                    console.log('Clicked plus');
                    let c = Number(document.getElementById('postiSelezionati').innerText);
                    visibilitaCarta(true, c + 1);
                    document.getElementById('postiSelezionati').innerText = c + 1;

                    if (c + 1 == 8)
                        document.getElementById('postiPlu').inert = true;
                    else 
                        document.getElementById('postiMin').inert = false;
                }
            }

            function visibilitaCarta(vis, index) {
                if (vis == false)
                    document.getElementById('p' + index).hidden = true;
                else
                    document.getElementById('p' + index).hidden = false;
            }
        </script>
        <script>
            function procedi(partenza, arrivo, oraPartenza, oraArrivo) {
                let c = Number(document.getElementById('postiSelezionati').innerText);

                let message = "Vuoi confermare l\'acquisto di ";
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
                xhr.open('POST', '<%=webApp%>/ticket/confirm');
                xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8')
                
                let jsonBody = "[";

                for (index = 1; index <= 8; ++index){
                    if (document.getElementById('p' + index).hidden == false) {
                        jsonBody += '{ \"nome\" : \"' + document.getElementById('nomeP' + index).value + '\",';
                        jsonBody += ' \"cognome\" : \"' + document.getElementById('cognomeP' + index).value + '\"},';
                    }
                }

                jsonBody = jsonBody.slice(0, -1) + "]";

                xhr.onload = () => {
                    if (xhr.status < 400) {
                        window.location.replace( "<%=webApp%>" + xhr.responseText);
                    } else {
                        console.log('Error : ' + xhr.status );
                    }
                };

                if (confirm(message)) {
                    xhr.send(jsonBody);
                }
            }

        </script>
    </body>
</html>