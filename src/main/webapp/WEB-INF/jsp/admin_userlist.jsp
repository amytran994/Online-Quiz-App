<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!DOCTYPE html>
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

<h2 style="text-align: center; color: navy;"> User List </h2>

<body style="text-align: center;">

    <table class="center" style="border:1px solid black;margin-left:auto;margin-right:auto;">
        <tr>
            <th>Id </th>
            <th>Name</th>
            <th>Profile</th>
            <th>Status</th>
        </tr>
        <c:forEach var="user" items="${userList}" varStatus="loop">
            <tr>
                <td>${user.id}</td>
                <td>${user.firstname} ${user.lastname}</td>
                <td><a href="/admin/userlist/${user.id}">View</a>
                </td>
              <td>
                <form action="/admin/userlist/changestatus/${user.id}" method="post">
                  <c:if test="${user.active}">
                    <input type ="submit" name ="button" value="Suspend"/>
                  </c:if>
                  <c:if test="${!user.active}">
                    <input type = "submit" name ="button" value="Active"/>
                  </c:if>
                </form>
              </td>
            </tr>
        </c:forEach>
    </table>


</body>