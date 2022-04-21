<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<div class="topnav" style="text-align: center;">

    <a href="/homepage">Homepage</a>
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
<h3 style="text-align: center;"> User Profile </h3>
<body style="text-align: center;">
  <p>Id: ${user.id}</p>
  <p>Username: ${user.username}</p>
  <p>Name: ${user.firstname} ${user.lastname}</p>
  <p>Email: ${user.email}</p>
  <p>Address: ${user.address}</p>
  <p>Phone Number: ${user.phoneNumber}</p>
  <p>Status:
    <c:if test="${user.active}">Active</c:if>
    <c:if test="${!user.active}">Suspended</c:if>
  </p>


</body>