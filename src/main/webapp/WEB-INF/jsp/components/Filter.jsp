<%@page import="com.corso.ticketrain.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%

        User user = (User) session.getAttribute("UserLoggato"); 
        String errorLabel = (String) request.getAttribute("erroreLabel");
        String webApp = request.getContextPath();
    %>

<div id="filtro" class="card" style="max-width: 70%; height: 15%; margin-left: 15%; margin-right: 15%; padding: 20px">
        <div class="row">
            <div class="col">
                <input type="text" class="form-control" id="luogoPartenza" class="form-control" placeholder="Luogo Partenza" aria-label="Luogo Partenza" onfocus="cleanLabel()">
            </div>
            <div class="col">
                <input type="text" class="form-control" id="luogoArrivo" class="form-control" placeholder="Destinazione" aria-label="Destinazione" onfocus="cleanLabel()">
            </div>
            <div class="col">
                <input type="datetime-local" class="form-control" id="dataPartenza" class="form-control" placeholder="Data Partenza" aria-label="Data Partenza" onfocus="cleanLabel()">
            </div>
        </div>

        <div style="padding : 5px">
            <div>
                <p id="errorLabel" class="text-danger"></p>
            </div>
        </div>
        <div class="row position-relative start-80">
            <div class="col">
                <button type="button" onclick="sendRequest()" value="Ricerca tratte" >Ricerca Tratte </button>
            </div>
        </div>
</div>

<script>
    function cleanLabel() {
        document.getElementById("errorLabel").innerText = "";
    }

    function sendRequest() {
        let luogoPartenza = document.getElementById("luogoPartenza").value;
        let luogoArrivo = document.getElementById("luogoArrivo").value;
        let dataPartenza = document.getElementById("dataPartenza").value;

        if (luogoPartenza == "" && luogoArrivo == "" && dataPartenza == "") {
                let error = document.getElementById("errorLabel");
                error.innerText="Devi inserire almeno un campo.";
        } else {
            const xhr = new XMLHttpRequest();
            xhr.open("GET", "<%=webApp%>/ticket/getByFilter" +
                                            "?luogoPartenza=" + luogoPartenza +
                                            "&luogoArrivo=" + luogoArrivo +
                                            "&dataPartenza=" + dataPartenza);
            xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8")

            xhr.onload = () => {
                if (xhr.status < 400) {
                    window.location.replace( "<%=webApp%>" + xhr.responseText +
                                            "?luogoPartenza=" + luogoPartenza +
                                            "&luogoArrivo=" + luogoArrivo +
                                            "&dataPartenza=" + dataPartenza);
                } else {
                    console.log("Error : " + xhr.status );
                    let error = document.getElementById("errorLabel");
                    error.innerText = xhr.responseText;
                }
            };

            xhr.send();
        }
    }

    
</script>