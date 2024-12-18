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
        <h2>Мои записи</h2>
        <c:choose>
          <c:when test="${not empty appointmentDtoIsNotCompleted}">
            <c:forEach var="appointment" items="${appointmentDtoIsNotCompleted}">
              <li>
                <strong><c:out value="${appointment.categoryName}"/></strong>
                <p>Дата: <c:out value="${appointment.timeSlot}"/></p>
                <p>Мастер: <c:out value="${appointment.employeeName}"/></p>
              </li>
              <br>
            </c:forEach>
          </c:when>
          <c:otherwise>
            <p>Вы пока не сделали ни одной записи. <a href="#">Записаться сейчас</a>.</p>
          </c:otherwise>
        </c:choose>
      </div>

      <!-- Правая колонка -->
      <div class="profile-orders">
        <h2>История записей</h2>
        <ul>
          <c:forEach var="appointment" items="${appointmentDtoCompleted}">
            <li>
              <strong><c:out value="${appointment.categoryName}"/></strong>
              <p>Дата: <c:out value="${appointment.timeSlot}"/></p>
              <p>Мастер: <c:out value="${appointment.employeeName}"/></p>
            </li>
          </c:forEach>
        </ul>
        <a href="#">Посмотреть всю историю</a>
      </div>
    </div>
  </main>

</t:mainLayuot>
<%@include file="/WEB-INF/view/parts/_footer.jsp"%>
</html>
