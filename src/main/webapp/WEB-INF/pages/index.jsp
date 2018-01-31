<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 16.01.2018
  Time: 11:54
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
</a> | <a href="/indexEx?exit=exit">Выход</a> </p>
<%} else { %>
<br>
<div><p align="right"><a href="/signin">Вход</a> / <a href="/register">Регистрация</a></p></div>
<%}%>

<p  align="center" style="font-size:30px; color:#d8000e"> <b>бла-бла-бла аукцион </b> </p>


<div id="menu">
    <ul>
        <li><b href="/index">Главная</b></li>
        <li><a href="/alllots">Список лотов</a></li>
        <% if (request.getSession().getAttribute("login") != null) {%>
        <li><a href="/mylots">Мои лоты</a></li>
        <%} %>
    </ul>
    <br>
</div>
</body>
</html>
