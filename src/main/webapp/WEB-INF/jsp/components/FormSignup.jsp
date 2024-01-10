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
            String formAction = webApp + "/Login";
            String errorLabel = (request.getAttribute("error") != null) ? (String) request.getAttribute("error") : ""; 
        %>
<form action="<%=webApp %>/user/registrazione" method="POST">
            <table style="with: 50%">
                    <tr>
                        <td>UserName</td>
                        <td><input type="text" name="username" placeholder="Username"/></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="password" placeholder="Password"/></td>
                    </tr>
                    <tr>
                        <td>Paese</td>
                        <td><input type="text" name="paese" placeholder="Paese"/></td>
                    </tr>
            </table>
            <div class="d-grid gap-2">	
                <input class="btn btn-primary btn-lg" type="submit" value="Registrazione" />
            </div>
        </form>
</body>
</html>