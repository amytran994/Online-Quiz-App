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
	Register
</h1>
<body style="text-align: center;">
<form action="register" method="post">
    Username : <input type="text" name="username"/> <br><br>
    Password : <input type="password" name="password"/> <br><br>
    Confirm Password :   <input type="password" name="password2"/> <br><br>
    First Name : <input type="text" name="firstname"/> <br><br>
    Last Name : <input type="text" name="lastname"/> <br><br>
    Email : <input type="text" name="email"/> <br><br>
    Address : <input type="text" name="address"/> <br><br>
    Phone Number : <input type="text" name="phoneNumber"/> <br><br>

    <input type="checkbox" id = "isAdmin" name="isAdmin" value="true">
    <label for="isAdmin"> Admin</label><br><br>

<input type="submit" name="register" value="Register">
</form>
</body>
</html>