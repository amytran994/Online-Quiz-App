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
<h3 style="text-align: center;"> Question Update </h3>

<body style="text-align: center;">
  <p style="color:blue"> ${message}</p>
    <br>
  <b>Add Question</b>
    <div>
        <form action="/admin/update/add" method="post">
            <p>Question: <input type="text" name="content" /></p>
            <p>Category
                <select name="category">
                    <option value="1">MySQL</option>
                    <option value="2">Java</option>
                    <option value="3">Python</option>
              </select></p>
                <p>Option 1: <input type="text" name="option1" /></p>
                <p>Option 2: <input type="text" name="option2" /></p>
                <p>Option 3: <input type="text" name="option3" /></p>
                <p>Option 4: <input type="text" name="option4" /></p>
                <p>Correct
                <select name="correct">
                    <option value="1">Option 1</option>
                    <option value="2">Option 2</option>
                    <option value="3">Option 3</option>
                    <option value="4">Option 4</option>
                  </select></p>
                <input type="submit" name="addButton" value="Add" />
        </form>
    </div>
  <br><br/>
  <b>Update Question</b>
    <div>
        <form action="/admin/update/update" method="post">
            <p>Question Id: <input type="text" name="id" /></p>
            <p>Question: <input type="text" name="content" /></p>
            <p>Category
                <select name="category">
                    <option value="0">Not Change</option>
                    <option value="1">MySQL</option>
                    <option value="2">Java</option>
                    <option value="3">Python</option>

              </select></p>
                <p>Option 1: <input type="text" name="option1" /></p>
                <p>Option 2: <input type="text" name="option2" /></p>
                <p>Option 3: <input type="text" name="option3" /></p>
                <p>Option 4: <input type="text" name="option4" /></p>
                <p>Correct
                <select name="correct">
                    <option value="0">Not Change</option>
                    <option value="1">Option 1</option>
                    <option value="2">Option 2</option>
                    <option value="3">Option 3</option>
                    <option value="4">Option 4</option>
                </select></p>
                <input type="submit" name="updateButton" value="Update" />
        </form>
    </div>
  <br/>
  <br>
  <b>Disable Question</b>
    <div>
        <form action="/admin/update/disable" method="post">
          <p>Question Id: <input type="text" name="id" /></p>
            <input type="submit" name="disableButton" value="Disable" />
        </form>
    </div>

    <br />
</body>