<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 21.01.2018
  Time: 0:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<script src="/resources/timer/timer.js"></script>
<head>
    <title>Список лотов</title>
    <link rel="stylesheet" type="text/css" href="/resources/style/MenuStyle.css" >
    <link rel="stylesheet" type="text/css" href="/resources/style/timerStyle.css" >
    <%--<link rel="stylesheet" href="assets/css/styles.css" />--%>
    <%--<link rel="stylesheet" href="assets/countdown/jquery.countdown.css" />--%>

</head>
<body>
<% if (request.getSession().getAttribute("login") != null) {%>
<p align="right"><a href="/profile"><%=request.getSession().getAttribute("login")%>
</a> | <a href="/indexEx?exit=exit">Выход</a> </p>
<%} else { %>
<br>
<div><p align="right"><a href="/signin">Вход</a> / <a href="/register">Регистрация</a></p></div>
<%}%>

<div id="menu">
    <ul>
        <li><a href="/index">Главная</a></li>
        <li><b href="/alllots">Список лотов</b></li>
        <% if (request.getSession().getAttribute("login") != null) {%>
        <li><a href="/mylots">Мои лоты</a></li>
        <%} %>
    </ul>
    <br>
</div>
    <p>Список всех лотов</p>
    <table id="timers-table"><tbody></tbody></table>
    <c:forEach items="${requestScope.lots}" var="lot">
        <%--<c:out value="${lot.name}"></c:out>--%>
        <%--<c:out value="${lot.startPrice}"></c:out>--%>
        <%--<c:out value="${lot.startTimeInMs}"></c:out>--%>
        <%--<c:out value="${lot.auctionTimeInMs}"></c:out>--%>
        <p id="time<c:out value="${lot.id}"></c:out>" name = "<c:out value="${lot.name}"></c:out>" dataStart = "<c:out value="${lot.startTimeInMs}"></c:out>"  dataFinish = "<c:out value="${lot.auctionTimeInMs}"></c:out>">

            <script>timers.launch(<c:out value="${lot.id}"></c:out>)</script>
        </p>


    </c:forEach>

<input type="button" value="Удалить завершенные" onclick="timers.removeFired()">

</body>
</html>
