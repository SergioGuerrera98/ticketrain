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
     <link rel="shortcut icon" type="image/png" href="https://raw.githubusercontent.com/IlanZdd/resources/main/topolino.png">
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
                        <td><%=ticket.getDataPartenzaStr()%></td>
                    </tr>
                    <tr>
                        <td>Arrivo a:&nbsp;&nbsp; <%=ticket.getLuogoArrivo()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        <td><%=ticket.getDataArrivoStr()%></td>
                    </tr>
                    <tr>
                        <td >Posti rimanenti:&nbsp;&nbsp;</td><td><p id="nPostiRimanenti"><%=(int)ticket.getVagone_id().getNumeroPosti()%></td>
                    </tr>
                </table>
            </div>


            <br>
            <div id="postiCounter">
                <div class="row">
                    <div class="col col-md-auto">
                        <p>Numero biglietti: </p>
                    </div>
                    <div class="col col-md-auto">
                        <button class="btn btn-green" id="postiMin" onclick="postiMinus()" inert="true">-</button>
                    </div>
                    <div class="col col-md-auto">
                        <h4 id="postiSelezionati">1</h4>
                    </div>
                    <div class="col col-md-auto">
                        <button class="btn btn-green" id="postiPlu" onclick="postiPlus()">+</button>
                    </div>
                </div>
            </div>

            <div id="formPosti" style="padding : 50px">

                <div class="row">

                    <div class="col col-md-auto">

                        <div class="card" id="p1" style="padding:10px">
                            <div class="row">
                                <div class="col col-md-auto">
                                    <p class="text-end"> Nome:  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    <input type="text" onfocus="clearAll()" id="nomeP1" placeholder="Nome passeggero" style="border-color : #874692"/>  </p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col col-md-auto">
                                    <p class="text-end">  Cognome:   <input type="text" onfocus="clearAll()" id="cognomeP1" placeholder="Cognome passeggero" style="border-color : #874692"/>  </p>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="col col-md-auto">

                        <div class="card" id="p2" hidden = "true"  style="padding:10px">
                            <div class="row">
                                <div class="col col-md-auto">
                                    <p>  Nome:  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    <input type="text" onfocus="clearAll()" id="nomeP2" placeholder="Nome passeggero" style="border-color : #874692"/>  </p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col col-md-auto">
                                    <p>  Cognome:   <input type="text" onfocus="clearAll()" id="cognomeP2" placeholder="Cognome passeggero" style="border-color : #874692"/>  </p> 
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
                                    <p>  Nome:  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    <input type="text" onfocus="clearAll()" id="nomeP3" placeholder="Nome passeggero" style="border-color : #874692"/>  </p> 
                                </div>
                            </div>
                            <div class="row">
                                <div class="col col-md-auto">
                                    <p>  Cognome:   <input type="text" onfocus="clearAll()" id="cognomeP3" placeholder="Cognome passeggero" style="border-color : #874692"/>  </p>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="col col-md-auto">

                        <div class="card" id="p4" hidden = "true" style="padding:10px">
                            <div class="row">
                                <div class="col col-md-auto">
                                    <p>  Nome:  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    <input type="text" onfocus="clearAll()" id="nomeP4" placeholder="Nome passeggero" style="border-color : #874692"/>  </p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col col-md-auto">
                                    <p>  Cognome:   <input type="text" onfocus="clearAll()" id="cognomeP4" placeholder="Cognome passeggero" style="border-color : #874692"/>  </p>
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
                                    <p>  Nome:  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    <input type="text" onfocus="clearAll()" id="nomeP5" placeholder="Nome passeggero" style="border-color : #874692"/>  </p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col col-md-auto">
                                    <p>  Cognome:   <input type="text" onfocus="clearAll()" id="cognomeP5" placeholder="Cognome passeggero" style="border-color : #874692"/>  </p>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="col col-md-auto">

                        <div class="card" id="p6" hidden = "true" style="padding:10px">
                            <div class="row">
                                <div class="col col-md-auto">
                                    <p>  Nome:  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    <input type="text" onfocus="clearAll()" id="nomeP6" placeholder="Nome passeggero" style="border-color : #874692"/>  </p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col col-md-auto">
                                    <p>  Cognome:   <input type="text" onfocus="clearAll()" id="cognomeP6" placeholder="Cognome passeggero" style="border-color : #874692"/>  </p>
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
                                    <p>  Nome:  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    <input type="text" onfocus="clearAll()" id="nomeP7" placeholder="Nome passeggero" style="border-color : #874692"/>  </p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col col-md-auto">
                                    <p>  Cognome:   <input type="text" onfocus="clearAll()" id="cognomeP7" placeholder="Cognome passeggero" style="border-color : #874692"/>  </p>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="col col-md-auto">

                        <div class="card" id="p8" hidden = "true" style="padding:10px">
                            <div class="row">
                                <div class="col col-md-auto">
                                    <p>  Nome:  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    <input type="text" onfocus="clearAll()" id="nomeP8" placeholder="Nome passeggero" style="border-color : #874692"/>  </p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col col-md-auto">
                                    <p>  Cognome:   <input type="text" onfocus="clearAll()" id="cognomeP8" placeholder="Cognome passeggero" style="border-color : #874692"/>  </p>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>
            </div>
                <br>

            <div style="padding : 5px">
                <div>
                    <p id="errorLabel" class="text-danger"></p>
                </div>
            </div>
            <div class="row position-relative start-80">
                <div class="col">
                    <button class="btn btn-green" onclick="procedi('<%=ticket.getLuogoPartenza()%>', '<%=ticket.getLuogoArrivo()%>', '<%=ticket.getDataPartenza()%>', '<%=ticket.getDataArrivo()%>', '<%=ticket.getClasse()%>')">Paga</button>
                </div>
            </div>
        </div>
        <footer><jsp:include page="/WEB-INF/jsp/components/Footer.jsp"></jsp:include></footer>
        
        <script>
            function clearAll () {
                for (index = 1; index <= 8; ++index) {
                    if (document.getElementById('p' + index).hidden == false) {
                        document.getElementById('nomeP' + index).style = "border-color: #874692";
                        document.getElementById('cognomeP' + index).style = "border-color: #874692";
                    }
                }
                document.getElementById('errorLabel').innerText="";
            }
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

                clearAll();
            }

            function postiPlus () {
                let rimanenti = Number(document.getElementById('nPostiRimanenti').innerText);
                let c = Number(document.getElementById('postiSelezionati').innerText);
                
                if (c < 8 &&  rimanenti > c){
                    console.log('Clicked plus');
                    visibilitaCarta(true, c + 1);
                    document.getElementById('postiSelezionati').innerText = c + 1;

                    if (c + 1 == 8 || c + 1 == rimanenti)
                        document.getElementById('postiPlu').inert = true;
                    else 
                        document.getElementById('postiMin').inert = false;
                }

                clearAll();
            }

            function visibilitaCarta(vis, index) {
                if (vis == false)
                    document.getElementById('p' + index).hidden = true;
                else
                    document.getElementById('p' + index).hidden = false;
            }
        </script>
        <script>
            function checkEmptyFields() {
                for (index = 1; index <= 8; ++index) {
                    let flag = false;
                    if (document.getElementById('p' + index).hidden == false) {
                        let nomeP = document.getElementById('nomeP' + index);
                        if (nomeP.value == null || nomeP.value == "") {
                            nomeP.style = "border-color : red;";
                            flag = true;
                        }
                        let cognomeP = document.getElementById('cognomeP' + index);
                        if (cognomeP.value == null || cognomeP.value == "") {
                            cognomeP.style = "border-color : red;";
                            flag = true;
                        }
                    }
                    if (flag == true) {
                        document.getElementById('errorLabel').innerText = "Uno o più campi devono essere completati per proseguire.";
                        return false;
                    }
                }
                return true;
            }

            function procedi(partenza, arrivo, oraPartenza, oraArrivo, classe) {
            	if (checkEmptyFields()) {
                    let c = Number(document.getElementById('postiSelezionati').innerText);

                    let message = "Vuoi confermare l\'acquisto di ";
                    if (c > 1)
                        message += c + " biglietti ";
                    else
                        message += "1 biglietto ";
                    
                    message += "per il treno:\n" +
                                    "Classe: " + classe + "\n" +
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
            }

        </script>
        
    </body>  
    <style>
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
            .btn-purple{
                --bs-btn-color:#fff;
                --bs-btn-bg:#874692; !important
                --bs-btn-border-color:#874692; !important
                --bs-btn-hover-color:#fff;
                --bs-btn-hover-bg:#8442a3;
                font-weight: bold;
                --bs-btn-hover-border-color:#672584;
                --bs-btn-focus-shadow-rgb:60,153,110;
                --bs-btn-active-color:#fff;
                --bs-btn-active-bg:#8442a3;
                --bs-btn-active-border-color:#3c105e;
                --bs-btn-active-shadow:inset 0 3px 5px rgba(0, 0, 0, 0.125);
                --bs-btn-disabled-color:#fff;
                --bs-btn-disabled-bg:#874692; !important
                --bs-btn-disabled-border-color:#874692 !important
            }

        </style>
</html>