<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 20.01.2018
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie-edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Форма регистрации</title>
    <link rel="stylesheet" href="../style/style.css">
    <link rel="stylesheet" type="text/css" href="../style/MenuStyle.css">
</head>
<body>
<% if (request.getSession().getAttribute("login") != null) {%>
<p align="right"><a href="profile"><%=request.getSession().getAttribute("login")%>
</a></p>
<%} else { %>
<br>
<div><p align="right"><a href="signIn">Вход</a> / <a href="register">Регистрация</a></p></div>
<%}%>
<br>
<div id="menu">
    <ul>

        <li><a href="home">Главная</a></li>
        <li><a href="allLots">Список лотов</a></li>

        <li><a href="hello?message=bla-bla">Что-нибудь еще</a></li>
    </ul>
    <br>
</div>
<p align="center" style="font-size:30px; color:#47a111">
    <%=request.getParameter("message") != null ? request.getParameter("message") : "" %>
</p>
<% if (request.getSession().getAttribute("login") == null) {%>
<form action="${pageContext.request.contextPath}/register" method="post">
    <section class="container one">
        <p align="centre" style="font-size:30px; color:#0e0007">Форма регистрации</p>

        <p><label name="user_login_label">Логин *</label>
            <input type="text" name="userLogin" pattern="^[a-zA-Z0-9]+$"><span><lt></lt></span></p>

        <p><label name="user_name_label">Имя *</label>
            <input type="text" name="user_name" pattern="^[А-Яа-яЁё\s]+$"><span></span></p>

        <p><label name="user_surname_label">Фамилия</label>
            <input type="text" name="user_surname" pattern="^[А-Яа-яЁё\s]+$"><span></span></p>

        <p><label name="userSecond_label">Отчество</label>
            <input type="text" name="userSecond" pattern="^[А-Яа-яЁё\s]+$"><span></span></p>

        <p><label name="birthday_label">День рождения</label>
            <input type="text" name="birthday" pattern="^[А-Яа-яЁё\s]+$"><span></span></p>

        <p><label name="gender_label">Ваш пол</label>
            <select name="gender">
                <option value="neutral" selected>-----</option>
                <option value="male">Мужской</option>
                <option value="female">Женский</option>
            </select>
        <p><label name="email_label">Эл. почта *</label>
            <input type="email" name="email"><span><lt></lt></span></p>

        <p><label name="password_label">Пароль *</label>
            <input type="password" name="userPassword" pattern="^[a-zA-Z0-9]+$"><span><lt></lt></span></p>
        <p>Поля отмеченные * обязательны для заполнения</p>
    </section>

    <section class="container two">
        <button type="submit">Зарегистрироваться</button>
    </section>
</form>
<% } else { %>
<p align="center" style="font-size:30px; color:#47a111">Вы авторизованы</p>
<% } %>
</body>
</html>
