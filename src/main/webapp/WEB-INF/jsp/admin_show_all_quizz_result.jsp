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
<h3 style="text-align: center;"> Submission List </h3>

<body style="text-align: center;">
    <form action="/admin/allsubmissions" method="get">
        <div>
          <input type="checkbox" name="mySQLButton" value="1"/>
          <input type="hidden" name="mySQLButton" value="0"/> mySQL

          <input type="checkbox" name="javaButton" value="2"/>
          <input type="hidden" name="javaLButton" value="0"/> Java

          <input type="checkbox" name="pythonButton" value="3"/>
          <input type="hidden" name="pythonButton" value="0"/> Python

        </div>

        <br>
        Username: <input type="text" name="usernameFilter" />
      <input type="submit" name="filter" value="Filter"/>
    </form>

    <table class="center" style="border:1px solid black;margin-left:auto;margin-right:auto;">
        <tr>
            <th>Date</th>
            <th>Category</th>
            <th>Name</th>
            <th>Score</th>
            <th>View Detail</th>
        </tr>
        <c:forEach var="res" items="${resultList}" varStatus="loop">
            <tr>
                <td>${res.date}</td>
                <td>${res.categoryName}</td>
                <td>${res.userFullname}</td>
                <td>${res.score}</td>
                <td><a href="/admin/allsubmissions/${res.id}">View</a>
                </td>
            </tr>
        </c:forEach>
    </table>
   </body>
</html>