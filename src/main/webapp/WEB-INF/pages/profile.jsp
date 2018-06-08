<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 23.01.2018
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Форма регистрации</title>
    <link rel="stylesheet" type="text/css" href="/resources/style/MenuStyle.css">
    <link rel="stylesheet" href="/resources/style/style.css">

</head>
<% if (request.getSession().getAttribute("login") != null) {%>
<p align="right"><a href="/profile"><%=request.getSession().getAttribute("login")%>
</a> | <a href="/logout">Выход</a></p>
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
<p>Тут будет личная инфа</p>


<form  action="${pageContext.request.contextPath}/profile" method="post">
    <section class="container one">
        <p align="centre" style="font-size:30px; color:#0e0007">Персональная информация</p>
        <br>
        <p><label name="user_login_label">Логин</label>
            <label name="user_login"><%=request.getAttribute("login")!=null ? request.getAttribute("login"):"" %></label>
        <input type="<%=request.getAttribute("type")%>" name="userLogin" pattern="^[a-zA-Z0-9]+$"><span><lt></lt></span></p>

        <br>
        <p><label name="user_name_label">Имя</label>
            <label name="user_name"><%=request.getAttribute("firstName")!=null ? request.getAttribute("firstName"):"" %></label>
            <input type="<%=request.getAttribute("type")%>" name="user_name" pattern="^[а-яА-ЯёЁa-zA-Z0-9]+$"><span></span></p>


        <br>
        <p><label name="user_surname_label">Фамилия</label>
            <label name="user_surname"><%=request.getAttribute("lastName")!=null ? request.getAttribute("lastName"):"" %></label>
            <input type="<%=request.getAttribute("type")%>" name="user_surname" pattern="^[а-яА-ЯёЁa-zA-Z0-9]+$"><span></span></p>

        <br>
        <p><label name="userSecond_label">Отчество</label>
            <label name="userSecond"><%=request.getAttribute("secondName")!=null ? request.getAttribute("secondName"):"" %></label>
            <input type="<%=request.getAttribute("type")%>" name="userSecond" pattern="^[а-яА-ЯёЁa-zA-Z0-9]+$"><span></span></p>

        <br>
        <p><label name="birthday_label">День рождения</label>
            <label name="birthday"><%=request.getAttribute("birthday")!=null ? request.getAttribute("birthday"):"" %></label>
            <input type="<%=request.getAttribute("type")%>" name="birthday" pattern="^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\d\d$"><span></span></p>

        <br>
        <p><label name="email_label">Эл. почта</label>
            <label name="email"><%=request.getAttribute("email")!=null ? request.getAttribute("email"):"" %></label>
            <input type="<%=request.getAttribute("type")%>" name="email"><span><lt></lt></span></p>
        <% if(request.getAttribute("update")!=null){ %>
        <p><label name="password_label">Пароль </label>
            <input type="<%=request.getAttribute("type")%>" name="userPassword" pattern="^[a-zA-Z0-9]+$"><span><lt></lt></span></p>
<%}%>


    </section>
    <% if(request.getAttribute("update")!=null){ %>
    <section class="container two">
        <button type="submit">Обновить</button>
    </section>
</form>
<%    }else{ %>
</form>
<p align="center"><a href="/profile?update=update"> <button type="submit">Редактировать данные</button> </a> </p>

     <%} %>



</body>
</html>
