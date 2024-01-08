<%@page import="com.corso.ticketrain.model.User"%>
<%@page import="com.corso.ticketrain.model.Ticket"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String webApp = request.getContextPath();
String formAction = webApp + "/login";
String errorLabel = (request.getAttribute("error") != null) ? (String) request.getAttribute("error") : ""; 
Ticket ticket = (Ticket) session.getAttribute("ticket");
User user = (User) session.getAttribute("UserLoggato");
%>
<header>
<div class="header">
<h2>Benvenuto <%=user.getUsername() %></h2>
<form action="<%=webApp%>/home" method="post">
<button class="btn">Area Personale</button>
</form>&nbsp;&nbsp;
<form action="<%=webApp%>/user/logout" method="get">
<button class="btn">Logout</button>
</form>&nbsp;&nbsp;
</div>
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
            <button id="postiMinus">-</button><p id="postiSelezionati"></p><button id="postiPlus">+</button>
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

        <div class="card" id="p2" visibility="visible">
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

        <div class="card" id="p3"  visibility="visible">
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

        <div class="card" id="p4"  visibility="visible">
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

        <div class="card" id="p5"  visibility="visible">
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

        <div class="card" id="p6"  visibility="visible">
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

        <div class="card" id="p7"  visibility="visible">
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

        <div class="card" id="p8"  visibility="visible">
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


<footer><jsp:include page="/WEB-INF/jsp/Footer.jsp"></jsp:include></footer>
<script type="text/javascript">

    let c = 1;
    let postiMinus = document.getElementById('postiMinus');
    let postiPlus = document.getElementById('postiPlus');
    let postiSelezionati = document.getElementById('postiSelezionati');
    let divPasseggeri = document.getElementById('postiSelezionati');

    postiMinus.addEventListener('click', () => {
        if (c > 1){
            visibilitaCarta(false, c);    
            c--; 
            postiSelezionati.innerText = `${c}`;
        }
    })
    postiPlus.addEventListener('click', () => {
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