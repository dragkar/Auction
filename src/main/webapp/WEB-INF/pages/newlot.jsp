<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 31.01.2018
  Time: 0:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Новый лот</title>
    <link rel="stylesheet" type="text/css" href="/resources/style/MenuStyle.css" >
    <link rel="stylesheet" href="/resources/style/style.css">
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
        <li><a href="/alllots">Список лотов</a></li>
        <% if (request.getSession().getAttribute("login") != null) {%>
        <li><a href="/mylots">Мои лоты</a></li>
        <%} %>
    </ul>
    <br>
</div>

 Я еще не работаю, на кнопку добавить можно не нажимать
<form action="${pageContext.request.contextPath}/newlot" method="post">
    <section class="container one">
        <p align="centre" style="font-size:30px; color:#0e0007">Форма регистрации</p>

        <p><label name="lot_name_label">Название *</label>
            <input type="text" name="user_name" pattern="^[а-яА-ЯёЁa-zA-Z0-9]+$"><span></span></p>

        <p><label name="lot_comment_label">Комментарий</label>
            <input type="textarea" name="comment" pattern="^[а-яА-ЯёЁa-zA-Z0-9]+$"><span></span></p>

        <p><label name="startPrice_label">Начальная цена*</label>
            <input type="text" name="startPrice" pattern="^[0-9]*[.,]?[0-9]+(?:[eE][-+]?[0-9]+)?$"><span></span></p>

        <p><label name="duration_label">Продолжительность</label>
            <select name="duration">
                <option value="neutral" selected>-----</option>
                <option value="one">1</option>
                <option value="two">2</option>
                <option value="five">5</option>
            </select>
        </p>
        <p>Поля отмеченные * обязательны для заполнения</p>
    </section>

    <section class="container two">
        <button type="submit">Создать</button>
    </section>
</form>
</body>
</html>
