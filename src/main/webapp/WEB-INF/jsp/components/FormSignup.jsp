<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%
            String webApp = request.getContextPath();
            String formAction = webApp + "/Login";
            String errorLabel = (request.getAttribute("error") != null) ? (String) request.getAttribute("error") : ""; 
        %>

<div style="padding : 20px">
    <form action="<%=webApp %>/user/registrazione" method="POST">
            
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
                </div>
            </div>
            <div class="row justify-content-md-center">
                <div class="col col-md-auto">
                    <label for="paeseForm" class="text-end align-middle ">Paese&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                    <input id="paeseForm" class="align-middle form-label" type="text" name="paese" placeholder="Paese" onfocus="clean()" required/>
                </div>
            </div>
            <br>
            <p id="errorLabel" class="text-center text-danger"><%=errorLabel %></p>
        <div class="d-grid gap-2">
            <input class="btn btn-outline-success btn-lg" type="submit" value="Registrazione" />
        </div>
    </form>
</div>
<script>
    function clean() {
        document.getElementById("errorLabel").innerText = "";
    }
</script>