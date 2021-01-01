<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "d" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="ninjagoldstyle.css">


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="container">
        <!-- Header div for the gold count -->
        <div id="gold">
            <p> Your Gold: </p>
            <p id="gold-count"> <d:out value="${gold}"/></p>
            <form action="/gold/reset" method="POST">
                <button> Start Over </button>
            </form>
        </div>

        <!-- Main div for different choices -->
            <div class="choice farm">
                <h2> Farm </h2>
                <p> (earns 10-20 golds) </p>
                <form action = "/gold/farm" method = "POST">
                    
                    <button name="farm"> Find Gold! </button>
                </form>
            </div>

            <div class="choice cave">
                <h2> Cave </h2>
                <p> (earns 5-10 golds) </p>
                <form action = "/gold/cave" method = "POST">
                    <button name="cave"> Find Gold! </button>
                </form>
            </div>

            <div class="choice house">
                <h2> House </h2>
                <p> (earns 2-5 golds) </p>
                <form action = "/gold/house" method = "POST">
                    
                    <button name="house"> Find Gold! </button>
                </form>
            </div>

            <div class="choice casino">
                <h2> Casino </h2>
                <p> (earns/takes 0-50 golds) </p>
                <form action = "/gold/casino" method = "POST">
                    <button name="casino"> Find Gold! </button>
                </form>
            </div>
        </div>

        <!-- Div for log display -->
        <div id="activity">
            <p> Activities: </p>
            <div id = "activity-box">
                <d:forEach items="${activities}" var="activity">
                		<p <d:if test="${activity.contains('lost')}">class="text-danger"</d:if><d:if test="${activity.contains('earned')}">class="text-success"</d:if><d:if test="${activity.contains('0 gold')}">class=""</d:if>><d:out value="${activity}"/></p>
                </d:forEach>
            </div>
        </div>
    </div>
</body>
</html>

<%-- Howard's:
<div id="activity">
        <p> Activities: </p>
        <div id="activity-box">
            <c:forEach items="${ logs }" var="log">
                <p class=${log.get(1)}> ${log.get(0)}</p>
            </c:forEach>
        </div>
    </div> --%>
  <!--   [
  ["You earned 10 gold", "green"],
  ["You lost 20 gold", "red"],
  ["You earned 1 gold", "green"]
] -->