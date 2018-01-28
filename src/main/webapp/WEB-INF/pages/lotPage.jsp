<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 25.01.2018
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <link rel="stylesheet" type="text/css" href="../style/MenuStyle.css" >
</head>
<body>
    <% if (request.getSession().getAttribute("login") != null) {%>
<p align="right"><a href="profile"><%=request.getSession().getAttribute("login")%>
</a> | <a href="home?exit=exit">Выход</a> </p>
    <%} else { %>
<br>
<div><p align="right"><a href="signIn">Вход</a> / <a href="register">Регистрация</a></p></div>
    <%}%>
<body>


<div id="menu">
    <ul>
        <li><a href="home">Главная</a></li>
        <li><a href="allLots">Список лотов</a></li>

        <li><a href="hello?message=bla-bla">Что-нибудь еще</a></li>
    </ul>
    <br>
</div>


Будет инфа о лоте


</body>
</html>
