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
	Contact Us
</h1>
<br>
<body style="text-align: center;">
<br />
<table cellpadding="10" style="text-align: center;">
    <tr>
        <th>Name</th><th>Contact Info</th>
    </tr>
    <c:forEach var="admin" items="${contactList}">
        <tr>
            <td>${admin.name} </td>
            <td>${admin.email}</td>
            <td>${admin.country}</td>

        </tr>
    </c:forEach>
</table>
</body>
</html>