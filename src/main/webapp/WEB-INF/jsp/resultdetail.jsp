<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!DOCTYPE html>
<html>
<div class="topnav">

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

<h2 style="text-align: center;">
    <c:if test="${score >=6}">
        Pass!
    </c:if>
    <c:if test="${score <6}">
        Fail!
    </c:if>
    <br>
        ${category} Quiz <br>
        <br>
        Name: ${name} <br>
        Total Score: ${score} <br>
        <br>
        Start time: ${startTime} <br>
        End Time: ${endTime} <br>

</h2>

<body style="text-align: center;">
    <c:forEach items="${resultList}" var="r">

        Question ${r.qnum}: ${r.question}
        <br /><br />

        <c:if test="${r.checked == 1}">
            <input type="radio" checked disabled>
        </c:if>
        <c:if test="${r.checked != 1}">
            <input type="radio" disabled>
        </c:if>
        <c:if test="${r.correctOpt == 1}">
            <span style="background-color:#BCEBF5"> ${r.opt1} </span>
        </c:if>
        <c:if test="${r.correctOpt != 1}">
            ${r.opt1}
        </c:if>

        <br>
        <c:if test="${r.checked == 2}">
            <input type="radio" checked disabled> </c:if>
        <c:if test="${r.checked != 2}">
            <input type="radio" name="radioName2" value="2" disabled>
        </c:if>
        <c:if test="${r.correctOpt == 2}">
            <span style="background-color:#BCEBF5"> ${r.opt2} </span>
        </c:if>
        <c:if test="${r.correctOpt != 2}">
            ${r.opt2}
        </c:if>

        <br>
        <c:if test="${r.checked == 3}">
            <input type="radio" checked disabled> </c:if>
        <c:if test="${r.checked != 3}">
            <input type="radio" name="radioName3" value="3" disabled>
        </c:if>
        <c:if test="${r.correctOpt == 3}">
            <span style="background-color:#BCEBF5"> ${r.opt3} </span>
        </c:if>
        <c:if test="${r.correctOpt != 3}">
            ${r.opt3}
        </c:if>

        <br>


        <c:if test="${r.checked == 4}">
            <input type="radio" checked disabled> </c:if>
        <c:if test="${r.checked != 4}">
            <input type="radio" name="radioName4" value="4" disabled>
        </c:if>

        <c:if test="${r.correctOpt == 4}">
            <span style="background-color:#BCEBF5"> ${r.opt4} </span>
        </c:if>
        <c:if test="${r.correctOpt != 4}">
            ${r.opt4}
        </c:if>

        <br>
        <br>

        <p style="color:green;">
        <c:if test="${r.correct}"> Correct </c:if>
        </p>

        <p style="color:red;">
        <c:if test="${!r.correct}"> Incorrect </c:if>
        </p>

        <br>
        <br>

    </c:forEach>
    <br>
    <a href="/homepage">Go to Homepage</a>
</body>

</html>