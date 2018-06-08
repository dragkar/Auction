<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 31.01.2018
  Time: 0:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <link rel="stylesheet" type="text/css" href="/resources/style/MenuStyle.css" >
</head>
<body>
<% if (request.getSession().getAttribute("login") != null) {%>
<p align="right"><a href="/profile"><%=request.getSession().getAttribute("login")%>
</a> | <a href="/logout">Выход</a> </p>
<%} else { %>
<br>
<div><p align="right"><a href="/signin">Вход</a> / <a href="/register">Регистрация</a></p></div>
<%}%>

<div id="menu">
    <ul>
        <li><a href="/index">Главная</a></li>
        <li><a href="/alllots">Список лотов</a></li>
        <% if (request.getSession().getAttribute("login") != null) {%>
        <li><a href="/mylots">Мои лоты</a></li>
        <%} %>
    </ul>
    <br>
</div>
    Я еще не работаю, на кнопку добавить можно нажимать,чтоб полюбоваться моим творением
    <form action="/newlot">
        <button type="submit">создать новый лот</button>
    </form>
</body>
</html>
