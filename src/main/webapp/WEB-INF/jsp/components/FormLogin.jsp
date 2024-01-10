<%@page import="com.corso.ticketrain.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%

    String errorLabel = (request.getAttribute("error") != null) ? (String) request.getAttribute("error") : ""; 
        String webApp = request.getContextPath();
    %>

<form action="<%=webApp %>/user/login" method="POST">
    <table style="with: 50%">
            <tr>
                <td>UserName</td>
                <td><input type="text" name="username" placeholder="Username"/></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" placeholder="Password"/></td>
            </tr>
    </table>
    <p class="labelError"><%=errorLabel%></p>
    <div class="d-grid gap-2">	
        <input class="btn btn-primary btn-lg" type="submit" value="Login" />
    </div>
</form>