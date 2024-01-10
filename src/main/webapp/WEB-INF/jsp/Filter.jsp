<%@page import="com.corso.ticketrain.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%

        User user = (User) session.getAttribute("UserLoggato"); 
        String errorLabel = (String) request.getAttribute("erroreLabel");
        String webApp = request.getContextPath();
    %>

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
            <div class="col">
            <div class="col">
                <input type="submit" value="Ricerca tratte" />
            </div>
            </div>
            </div>
        </div>
    </form>
</div>