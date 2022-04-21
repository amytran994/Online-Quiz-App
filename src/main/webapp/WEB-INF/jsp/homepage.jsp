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

    <c:if test="${sessionScope.isAdmin}">
        <a href="/admin"> Admin Page </a>
    </c:if>
</div>

<h1 style="text-align: center;">
	Homepage
	    <br> <h3> ${message} </h3> <br>

</h1>

<body style="text-align: center;">

    <form action="/quiz/mysql" method="get">

        <input type="hidden" value=${id} name="id">
        MySql <input type="submit" name="mysql" value="Start"> <br><br>
    </form>

    <form action="/quiz/java" method="get">

        <input type="hidden" value=${id} name="id">
        Java <input type="submit" name="java" value="Start"> <br><br>
    </form>

    <form action="/quiz/python" method="get">
        <input type="hidden" value=${id} name="id">
        Python <input type="submit" name="python" value="Start"> <br><br>
    </form>

</body>
</html>