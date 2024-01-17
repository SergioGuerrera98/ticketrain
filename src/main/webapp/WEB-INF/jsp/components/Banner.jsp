<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Random"%>

        <div class="align-items-center">
            <% if (new Random().nextInt() % 2 == 0) {%>
                <img alt="Banner Le tue offerte preferite.. a portata di treno!" style="width : 100%"
                src="https://raw.githubusercontent.com/IlanZdd/resources/main/bannerIslandnoInfo.png">
            <%} else {%>
                <img alt="Banner Scopri i nostri ristoranti etnici!" style="width : 100%"
                src="https://raw.githubusercontent.com/IlanZdd/resources/main/bannerFood.png">
            <%}%>
        </div>