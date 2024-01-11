<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%
            String webApp = request.getContextPath();
            String formAction = webApp + "/Login";
            String errorLabel = (request.getAttribute("error") != null) ? (String) request.getAttribute("error") : ""; 
        %>

<div style="padding : 20px">
    <form action="<%=webApp %>/user/registrazione" method="POST">
            <div class="row">
                <div class="col">
                    <p class="text-end align-middle">Username </p>
                </div>
                <div class="col">
                    <input class="align-middle" type="text" name="username" placeholder="Username"/>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <p class="text-end align-middle">Password </p>
                </div>
                <div class="col">
                    <input class="align-middle" type="password" name="password" placeholder="Password"/>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <p class="text-end align-middle">Paese</p>
                </div>
                <div class="col">
                   <input class="align-middle" type="text" name="paese" placeholder="Paese"/>
                </div>
            </div>
            <br>
        <div class="d-grid gap-2">
            <input class="btn btn-outline-success btn-lg" type="submit" value="Registrazione" />
        </div>
    </form>
</div>