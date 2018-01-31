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
    <link rel="stylesheet" href="/resources/style/style.css">
    <link rel="stylesheet" type="text/css" href="/resources/style/MenuStyle.css">
</head>
<body>
<% if (request.getSession().getAttribute("login") != null) {%>
<p align="right"><a href="/profile"><%=request.getSession().getAttribute("login")%>
</a> | <a href="/indexEx?exit=exit">Выход</a> </p>
<%} else { %>
<br>
<div><p align="right"><a href="/signin">Вход</a> / <a href="/register">Регистрация</a></p></div>
<%}%>
<br>
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
<p align="center" style="font-size:30px; color:#47a111">
    <%--<%=request.getParameter("message") != null ? request.getParameter("message") : "" %>--%>
<p  align="center" style="font-size:30px; color:#d8000e">${message}</p>
</p>
<% if (request.getSession().getAttribute("login") == null) {%>
<form action="${pageContext.request.contextPath}/register" method="post">
    <section class="container one">
        <p align="centre" style="font-size:30px; color:#0e0007">Форма регистрации</p>

        <p><label name="user_login_label">Логин *</label>
            <input type="text" name="userLogin" pattern="^[a-zA-Z0-9]+$"><span><lt></lt></span></p>

        <p><label name="user_name_label">Имя *</label>
            <input type="text" name="user_name" pattern="^[а-яА-ЯёЁa-zA-Z0-9]+$"><span></span></p>

        <p><label name="user_surname_label">Фамилия</label>
            <input type="text" name="user_surname" pattern="^[а-яА-ЯёЁa-zA-Z0-9]+$"><span></span></p>

        <p><label name="userSecond_label">Отчество</label>
            <input type="text" name="userSecond" pattern="^[а-яА-ЯёЁa-zA-Z0-9]+$"><span></span></p>

        <p><label name="birthday_label">День рождения</label>
            <input type="text" name="birthday" pattern="^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\d\d$"><span></span></p>

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
