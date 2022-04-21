<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<!DOCTYPE html>
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
	${category} Quiz
</h1>

<body style="text-align: center;">
	<p> Question ${qnum}:
	${content}
	</p>

	<form action="${qnum}" method="post">


            <input type="hidden" value=${id} name="id">

            <c:set var="c1" value="${check1}"/>
            <c:if test = "${c1}">
            <input type="radio" name="radioName" value="1" checked >
            </c:if>
            <c:if test = "${!c1}">
            <input type="radio" name="radioName" value="1" >
            </c:if>
            ${option1}
            <br>

            <c:set var="c2" value="${check2}"/>
            <c:if test = "${c2}">
            <input type="radio" name="radioName" value="2" checked >
            </c:if>
            <c:if test = "${!c2}">
            <input type="radio" name="radioName" value="2" >
            </c:if>
            ${option2}

            <br>
            <c:set var="c3" value="${check3}"/>
            <c:if test = "${c3}">
            <input type="radio" name="radioName" value="3" checked >
            </c:if>
            <c:if test = "${!c3}">
            <input type="radio" name="radioName" value="3">
            </c:if>
            ${option3}

            <br>
            <c:set var="c4" value="${check4}"/>
            <c:if test = "${c4}">
            <input type="radio" name="radioName" value="4" checked >
            </c:if>
            <c:if test = "${!c4}">
            <input type="radio" name="radioName" value="4">
            </c:if>
            ${option4}

            <br>
            <br>
            <br>
            <br>

            <c:if test = "${qnum > 1}">
                <input type="submit" name="button" value="Prev">
            </c:if>

            <c:if test = "${qnum < 10 }">
                <input type="submit" name="button" value="Next" default>
            </c:if>
            <c:if test = "${qnum == 10 }">
                <br><br>
            	<input type="submit" name="button" value="Submit">
            </c:if>
    </form>


</body>
</html>