<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 23.01.2018
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
    <link rel="stylesheet" type="text/css" href="../style/styleLogin.css">
    <link rel="stylesheet" type="text/css" href="../style/MenuStyle.css">
</head>
<body>
<br>
<div id="menu">
    <ul>

        <li><a href="home">Главная</a></li>
        <li><a href="allLots">Список лотов</a></li>

        <li><a href="hello?message=bla-bla">Что-нибудь еще</a></li>
    </ul>
    <br>
</div>

<p align="center" style="color: black; font-size: 25px">Авторизация</p>
<form class="form-1" action="${pageContext.request.contextPath}/signIn" method="post">
    <p class="field">
        <input type="text" name="login" placeholder="Логин">
        <i class="icon-user icon-large"></i>
    </p>
    <p class="field">
        <input type="password" name="password" placeholder="Пароль">
        <i class="icon-lock icon-large"></i>
    </p>
    <p  align="right"> <a href="register">Регистрация</a></p>
    <p class="submit">
        <button type="submit" name="submit"><i class="icon-arrow-right icon-large"></i></button>
    </p>

</form>

</body>
</html>
