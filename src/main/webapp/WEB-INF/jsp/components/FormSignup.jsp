<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%
            String webApp = request.getContextPath();
            String formAction = webApp + "/Login";
            String errorSignup = (request.getAttribute("error") != null) ? (String) request.getAttribute("error") : ""; 
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
            <p id="errorSignup" class="text-center text-danger"><%=errorSignup %></p>
        <div class="d-grid gap-2">
            <input class="btn btn-green btn-lg" type="submit" value="Registrazione" />
        </div>
    </form>
</div>
<script>
    function clean() {
        document.getElementById("errorSignup").innerText = "";
    }
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
