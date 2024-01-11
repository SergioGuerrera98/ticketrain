<%@page import="com.corso.ticketrain.model.User"%>
<%@page import="com.corso.ticketrain.model.Ticket"%>
<%@page import="java.time.LocalDateTime"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%String tema = (session.getAttribute("tema") != null) ? (String) session.getAttribute("tema") : "dark";  %>
<html id="htmlId" data-bs-theme="<%=tema%>">
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
        <jsp:include page="/WEB-INF/jsp/components/Header.jsp"></jsp:include>
    </header>

    <div style="padding : 30px">

        <h1>Procedi con l'acquisto</h1>
        <br>
            <div id="dettagliTreno">
                <table>
                    <tr>
                        <td>Partenza da:&nbsp;&nbsp; <%=ticket.getLuogoPartenza()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        <td><% LocalDateTime datePartenza = ticket.getDataPartenza();
                                out.print(datePartenza.getDayOfMonth() + "/");
                                out.print(datePartenza.getMonthValue() + "/");
                                out.print(datePartenza.getYear() + " - ");
                                
                                out.print(datePartenza.getHour() + ":");
                                out.print(datePartenza.getMinute()); %></td>
                    </tr>
                    <tr>
                        <td>Arrivo a:&nbsp;&nbsp; <%=ticket.getLuogoArrivo()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        <td><%
                                LocalDateTime dateArrivo = ticket.getDataArrivo();
                                out.print(dateArrivo.getDayOfMonth() + "/");
                                out.print(dateArrivo.getMonthValue() + "/");
                                out.print(dateArrivo.getYear() + " - ");
                                
                                out.print(dateArrivo.getHour() + ":");
                                out.print(dateArrivo.getMinute()); %></td>
                    </tr>
                </table>
            </div>


            <br>
            <div id="postiCounter">
                <div class="row">
                    <div class="col col-md-auto">
                        <p>Numero posti: </p>
                    </div>
                    <div class="col col-md-auto"><button id="postiMin" onclick="postiMinus()" inert="true">-</button>
                    </div>
                    <div class="col col-md-auto">
                        <h4 id="postiSelezionati">1</h4>
                    </div>
                    <div class="col col-md-auto">
                        <button id="postiPlu" onclick="postiPlus()">+</button>
                    </div>
                </div>
            </div>

            <div id="formPosti" style="padding : 50px">

                <div class="row">

                    <div class="col col-md-auto">

                        <div class="card" id="p1" style="padding:10px">
                            <div class="row">
                                <div class="col col-md-auto">
                                    <p class="text-end"> Nome:  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    <input type="text" id="nomeP1" placeholder="Nome passeggero"/>  </p> 
                                </div>
                            </div>
                            <div class="row">
                                <div class="col col-md-auto">
                                    <p class="text-end">  Cognome:   <input type="text" id="cognomeP1" placeholder="Cognome passeggero"/>  </p> 
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="col col-md-auto">

                        <div class="card" id="p2" hidden = "true"  style="padding:10px">
                            <div class="row">
                                <div class="col col-md-auto">
                                    <p>  Nome:  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    <input type="text" id="nomeP2" placeholder="Nome passeggero"/>  </p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col col-md-auto">
                                    <p>  Cognome:   <input type="text" id="cognomeP2" placeholder="Cognome passeggero"/>  </p> 
                                </div>
                            </div>
                        </div>

                    </div>

                </div>
                <br>
                <div class="row">

                    <div class="col col-md-auto">

                        <div class="card" id="p3" hidden = "true" style="padding:10px">
                            <div class="row">
                                <div class="col col-md-auto">
                                    <p>  Nome:  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    <input type="text" id="nomeP3" placeholder="Nome passeggero"/>  </p> 
                                </div>
                            </div>
                            <div class="row">
                                <div class="col col-md-auto">
                                    <p>  Cognome:   <input type="text" id="cognomeP3" placeholder="Cognome passeggero"/>  </p>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="col col-md-auto">

                        <div class="card" id="p4" hidden = "true" style="padding:10px">
                            <div class="row">
                                <div class="col col-md-auto">
                                    <p>  Nome:  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    <input type="text" id="nomeP4" placeholder="Nome passeggero"/>  </p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col col-md-auto">
                                    <p>  Cognome:   <input type="text" id="cognomeP4" placeholder="Cognome passeggero"/>  </p>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>
                <br>
                <div class="row">

                    <div class="col col-md-auto">

                        <div class="card" id="p5" hidden = "true" style="padding:10px">
                            <div class="row">
                                <div class="col col-md-auto">
                                    <p>  Nome:  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    <input type="text" id="nomeP5" placeholder="Nome passeggero"/>  </p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col col-md-auto">
                                    <p>  Cognome:   <input type="text" id="cognomeP5" placeholder="Cognome passeggero"/>  </p>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="col col-md-auto">

                        <div class="card" id="p6" hidden = "true" style="padding:10px">
                            <div class="row">
                                <div class="col col-md-auto">
                                    <p>  Nome:  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    <input type="text" id="nomeP6" placeholder="Nome passeggero"/>  </p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col col-md-auto">
                                    <p>  Cognome:   <input type="text" id="cognomeP6" placeholder="Cognome passeggero"/>  </p>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>
                <br>
                <div class="row">

                    <div class="col col-md-auto">

                        <div class="card" id="p7" hidden = "true" style="padding:10px">
                            <div class="row">
                                <div class="col col-md-auto">
                                    <p>  Nome:  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    <input type="text" id="nomeP7" placeholder="Nome passeggero"/>  </p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col col-md-auto">
                                    <p>  Cognome:   <input type="text" id="cognomeP7" placeholder="Cognome passeggero"/>  </p>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="col col-md-auto">

                        <div class="card" id="p8" hidden = "true" style="padding:10px">
                            <div class="row">
                                <div class="col col-md-auto">
                                    <p>  Nome:  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    <input type="text" id="nomeP8" placeholder="Nome passeggero"/>  </p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col col-md-auto">
                                    <p>  Cognome:   <input type="text" id="cognomeP8" placeholder="Cognome passeggero"/>  </p>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>
                <br>

            </div>
            <button onclick="procedi('<%=ticket.getLuogoPartenza()%>', '<%=ticket.getLuogoArrivo()%>', '<%=ticket.getDataPartenza()%>', '<%=ticket.getDataArrivo()%>')">Paga</button>
        
        </div>
        <footer><jsp:include page="/WEB-INF/jsp/components/Footer.jsp"></jsp:include></footer>
        
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