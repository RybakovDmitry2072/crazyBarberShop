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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
  $(document).ready(function() {
    $('#uploadForm').on('submit', function(event) {
      event.preventDefault();
      var formData = new FormData(this);
      $.ajax({
        url: '${pageContext.request.contextPath}/uploadPhoto',
        type: 'POST',
        data: formData,
        contentType: false,
        processData: false,
        success: function(response) {
          alert('Photo uploaded successfully!');
          // Очистка формы после успешной загрузки
          $('#uploadForm')[0].reset();
        },
        error: function(xhr, status, error) {
          alert('Error uploading photo: ' + error);
        }
      });
    });
  });
</script>
<t:mainLayuot title="Профиль" cssPath="styleProfil">
  <%@include file="/WEB-INF/view/parts/_header.jsp"%>

  <main class="container">
    <div class="profile-container">
      <!-- Левая колонка -->
      <div class="profile-info">
        <h2>Профиль пользователя</h2>
        <h3>${user.name} ${user.surname}</h3>
        <p>Добро пожаловать в ваш личный кабинет.</p>
        <figure class="image-container">
          <img src="${user.urlImg}" alt="Фото пользователя">
        </figure>

          <p>Вы можете загрузить свою фотографию, чтобы она отображалась в вашем профиле, или обновить ее.</p>
          <form action="${pageContext.request.contextPath}/profile" method="post" enctype="multipart/form-data">
            <input type="file" name="file">
            <input type="hidden" name="action" value="addImage">
            <input type="hidden" name="dir" value="userImage">
            <input type="submit" value="Загрузить" >
          </form>

        <h2>Мои записи</h2>
        <c:choose>
          <c:when test="${not empty appointmentDtoIsNotCompleted}">
            <c:forEach var="appointment" items="${appointmentDtoIsNotCompleted}">
              <li>
                <strong><c:out value="${appointment.categoryName}"/></strong>
                <p>Дата: <c:out value="${appointment.timeSlot}"/></p>
                <p>Мастер: <c:out value="${appointment.employeeName}"/></p>
              </li>
              <form action="${pageContext.request.contextPath}/profile" method="POST" style="display:inline;">
                <input type="hidden" name="action" value="cancelAppointment">
                <input type="hidden" name="appointmentId" value="${appointment.id}">
                <button type="submit" class="cancel-button">Отменить бронь</button>
              </form>
              <br>
            </c:forEach>
          </c:when>
          <c:otherwise>
            <p>Вы пока не сделали ни одной записи. <a href="${pageContext.request.contextPath}/appointment">Записаться сейчас</a>.</p>
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
      </div>
    </div>
  </main>

</t:mainLayuot>
<%@include file="/WEB-INF/view/parts/_footer.jsp"%>
</html>
