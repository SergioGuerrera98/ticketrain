<%@page import="com.corso.ticketrain.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%

        User user = (User) session.getAttribute("UserLoggato"); 
        String errorLabel = (String) request.getAttribute("erroreFilter");
       	if (errorLabel == null) errorLabel = "";
        String webApp = request.getContextPath();
    %>

<div id="filtro" class="card shadow-lg p-3 mb-5 bg-body rounded" style="max-width: 70%; height: 15%; margin-left: 15%; margin-right: 15%; padding: 20px">
        <div class="row">
            <h5>Cerca tratta:</h5>
        </div>
        <div class="row">
            <div class="col">
                <h7>Partenza da:</h7>
                <input type="text" class="form-control" id="luogoPartenza" class="form-control" placeholder="Luogo Partenza" aria-label="Luogo Partenza" onfocus="cleanLabel()">
            </div>
            <div class="col">
                <h7>Arrivo a:</h7>
                <input type="text" class="form-control" id="luogoArrivo" class="form-control" placeholder="Destinazione" aria-label="Destinazione" onfocus="cleanLabel()">
            </div>
            <div class="col">
                <h7>Data di partenza:</h7>
                <input type="datetime-local" class="form-control" id="dataPartenza" class="form-control" placeholder="Data Partenza" aria-label="Data Partenza" value="" onfocus="cleanLabel()">
            </div>
        </div>

        <div style="padding : 5px">
            <div>
                <p id="errorLabel" class="text-danger"><%=errorLabel%></p>
            </div>
        </div>
        <div class="row position-relative start-80">
            <div class="col">
                <button type="button" class="btn btn-green" onclick="sendRequest()" value="Ricerca tratte" style="margin-left: 78%">Ricerca Tratte </button>
            </div>
        </div>
</div>

<script>
    const dataPagina = new Date();

    function cleanLabel() {
        document.getElementById("errorLabel").innerText = "";
        document.getElementById("luogoPartenza").style = "";
        document.getElementById("luogoArrivo").style = "";
        document.getElementById("dataPartenza").style = "";
    }

    function wrongLuogoPartenza(errore) {
        let errorePartenza = document.getElementById("errorLabel");
        console.log("sgegtrsef : " + errore);

        document.getElementById("luogoPartenza").style = "border-color : red";
        errorePartenza.innerText = errore;
    }
    function wrongLuogoArrivo(errore) {
        let erroreArrivo = document.getElementById("errorLabel");

        document.getElementById("luogoArrivo").style = "border-color : red";        
        erroreArrivo.innerText = errore;
    }
    function wrongData(errore) {
        let erroreData = document.getElementById("errorLabel");

        document.getElementById("dataPartenza").style = "border-color : red";
        erroreData.innerText = errore;
    }
    function allWrong(alsoDate) {
        document.getElementById("luogoPartenza").style = "border-color : red";
        document.getElementById("luogoArrivo").style = "border-color : red";
        if (alsoDate == true)
            document.getElementById("dataPartenza").style = "border-color : red";
    }

    function oneHourAgo(date) {
        let yy = date.getFullYear();
        let mm = date.getMonth() + 1;
        let dd = date.getDate();

        let hh = date.getHours() - 1;
        let min = date.getMinutes();

        if (hh < 0) {
            hh = 0;
            min = 0;
        }
        let localDatetime = yy + "-" +
                        (mm < 10 ? "0" + mm.toString() : mm) + "-" +
                        (dd < 10 ? "0" + dd.toString() : dd) + "T" +
                        (hh < 10 ? "0" + hh.toString() : hh) + ":" +
                        (min < 10 ? "0" + min.toString() : min);

        return new Date(localDatetime);
    }

    function sixMonthsLater(date) {
        let yy = date.getFullYear();
        let mm = date.getMonth() + 1 + 6;
        let dd = date.getDate();

        let hh = date.getHours();
        let min = date.getMinutes();

        if (mm > 12) {
            hh = mm - 12;
        }
        let localDatetime = yy + "-" +
                        (mm < 10 ? "0" + mm.toString() : mm) + "-" +
                        (dd < 10 ? "0" + dd.toString() : dd) + "T" +
                        (hh < 10 ? "0" + hh.toString() : hh) + ":" +
                        (min < 10 ? "0" + min.toString() : min);

        return new Date(localDatetime);
    }

    function sendRequest() {
        let luogoPartenza = document.getElementById("luogoPartenza").value;
        let luogoArrivo = document.getElementById("luogoArrivo").value;
        let dataPartenza = document.getElementById("dataPartenza").value;

        //controlli richiesta:
        //tutti campi vuoti
        if (luogoPartenza == "" && luogoArrivo == "") {
            let erroreOvunque = document.getElementById("errorLabel");
            allWrong(false);
            erroreOvunque.innerText = "Devi inserire almeno un luogo di partenza e/o destinazione.";

            //data oltre 6 mesi
        } else if (new Date(dataPartenza) > sixMonthsLater(new Date())) {
            wrongData("Non sono previsti treni oltre i 6 mesi dalla data corrente.");

            //data prima 1 h
        } else if (new Date(dataPartenza) < oneHourAgo(new Date())) {
            wrongData("Non sono possibili ricerche precedenti alla data corrente.");

            //tutto corretto, invia
        } else {
            if (new Date(dataPartenza) < new Date()) {
                dataPartenza = dateNow();
            }
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
                    console.log("Error : " + xhr.status + ", errore: " + xhr.responseText);
                    let errore = xhr.responseText;

                    if (xhr.responseText.search("Partenza") > -1) {
                        wrongLuogoPartenza(errore.substring(errore.indexOf("-")+1, 200000));
                    }
                    if (xhr.responseText.search("Arrivo") > -1) {
                        wrongLuogoArrivo(errore.substring(errore.indexOf("-")+1, 200000));
                    }
                    if (xhr.responseText.search("Data") > -1) {
                        wrongData("La data selezionata non è valida.");
                    }
                }
            };

            xhr.send();
        }
    }

    function dateNow() {
        var now = new Date();
        var year = now.getFullYear();
        var month = now.getMonth() + 1;
        var day = now.getDate();
        var hour = now.getHours();
        var minute = now.getMinutes();
        return year + "-" +
                        (month < 10 ? "0" + month.toString() : month) + "-" +
                        (day < 10 ? "0" + day.toString() : day) + "T" +
                        (hour < 10 ? "0" + hour.toString() : hour) + ":" +
                        (minute < 10 ? "0" + minute.toString() : minute);
    }

    window.addEventListener("load", function() {
        let localDatetime = dateNow();
        let datetimeField = document.getElementById("dataPartenza");
        datetimeField.value = localDatetime;
        datetimeField.min = localDatetime;
        datetimeField.max = sixMonthsLater(localDateTime);
        
    });
	
    


</script>
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

</style>