<%@page import="com.corso.ticketrain.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%

    String errorLabel = (request.getAttribute("error") != null) ? (String) request.getAttribute("error") : ""; 
        String webApp = request.getContextPath();
    %>
<div style="padding : 20px">
        <form action="<%=webApp %>/user/login" method="POST">
            <div class="row justify-content-md-center">
                <div class="col col-md-auto">
                    <label for="usernameForm" class="text-end align-middle">Username&nbsp;&nbsp;&nbsp;</label>
                    <input id="usernameForm" class="align-middle form-label" type="text" name="username" placeholder="Username" onfocus="clean()" required/>
                </div>
            </div>
            <div class="row justify-content-md-center">
                <div class="col col-md-auto">
                    <label for="passwordForm" class="text-end align-middle ">Password&nbsp;&nbsp;&nbsp;&nbsp;</label>
                    <input id="passwordForm" class="align-middle form-label" type="password" name="password" placeholder="Password" onfocus="clean()" required/>
                    <div class="row">
                            <a id="passwordDimenticata" class="text-end" href="EHLAMISERIA">Password dimenticata?</a>
                    </div>
                </div>
            </div>
            <br>
            <p id="errorLabel" class="text-center text-danger"><%=errorLabel %></p>
            <div class="d-grid gap-2 justify-content-md-center">
                <input class="btn btn-outline-success btn-lg" type="submit" value="Login" />
            </div>
        </form>
</div>
<script>
    function clean() {
        document.getElementById("errorLabel").innerText = "";
    }
</script>