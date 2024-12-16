<%--
  Created by IntelliJ IDEA.
  User: rybakovdmitry
  Date: 15.12.2024
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.example.crazybarbershop.dto.UserDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<!-- Хедер -->

<t:mainLayuot title="Профиль" cssPath="styleProfil">
  <%@include file="/WEB-INF/view/parts/_header.jsp"%>

  <main class="container">
  <div class="profile-container">
  <!-- Левая колонка -->
  <div class="profile-info">
    <h2>Профиль пользователя</h2>
    <h3>${user.name} ${user.surname}</h3>
    <p>Добро пожаловать в ваш личный кабинет. Здесь вы можете управлять своими записями, просматривать историю услуг и обновлять данные профиля.</p>
    <figure>
      <img src="https://source.unsplash.com/featured/?person" alt="Фото пользователя">
    </figure>
    <h3>Мои записи</h3>
    <c:forEach var="appointment" items="${ap}"></c:forEach>
    <p>Вы пока не сделали ни одной записи. <a href="#">Записаться сейчас</a>.</p>
  </div>

  <!-- Правая колонка -->
  <div class="profile-orders">
  <h2>История заказов</h2>
  <ul>
  <li>
    <strong>Стрижка "Классическая"</strong>
    <p>Дата: 10.12.2024</p>
    <p>Мастер: Андрей</p>
  </li>
  <li>
    <strong>Укладка волос</strong>
    <p>Дата: 01.12.2024</p>
    <p>Мастер: Виктор</p>
  </li>
  <li>
  <strong>Бритье</strong>
  <p>Дата: 20.11.2024</p>
    <p>Мастер: Сергей</p>
  </li>
  </ul>
    <a href="#">Посмотреть всю историю</a>
  </div>
  </div>
  </main>



</t:mainLayuot>
<%@include file="/WEB-INF/view/parts/_footer.jsp"%>

</html>