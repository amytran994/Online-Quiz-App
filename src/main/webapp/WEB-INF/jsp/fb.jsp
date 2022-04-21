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
	Feedback
	    <br> <h3> ${message} </h3> <br>

</h1>

<body style="text-align: center;">

    <div class="container">
      <form action="/feedback" method="post">

        <label for="feedback">Your Feedback</label>
        <textarea id="feedback" name="feedback" placeholder="Write something.." style="height:200px"></textarea>

        <label for="rating">Rating</label>
        <select id="rating" name="rating">
          <option value="5">5</option>
          <option value="4">4</option>
          <option value="3">3</option>
          <option value="2">2</option>
          <option value="1">1</option>
        </select>

        <input type="submit" value="Submit">

      </form>
    </div>

</body>
</html>