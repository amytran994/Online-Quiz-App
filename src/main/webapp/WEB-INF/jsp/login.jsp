<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<div class="topnav">

    <a href="/homepage" >Homepage</a>
    <a href="/register">Register</a>
    <c:if test="${sessionScope.username != null}">
        <a href="/logout">Log Out</a>
    </c:if>

    <c:if test="${sessionScope.username == null}">
	 <a href="/login">Log In</a>
    </c:if>

    <a href="/feedback">Feedback</a>
    <a href="/contactus">Contact Us</a>

</div>
<h1 style="text-align: center;">
	Login
</h1>

<body style="text-align: center;">
<form action="login" method="post">
    Username : <input type="text" name="username"/> <br><br>
    Password : <input type="password" name="password"/> <br><br>

<input type="submit" name="login" value="Log in">
</form>

	   <br> <p style="color:red;"> ${message} <p> <br>

</body>
</html>