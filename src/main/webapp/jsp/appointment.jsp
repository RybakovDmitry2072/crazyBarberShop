<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.crazybarbershop.dto.TimeSlotDto" %> <!-- Добавьте импорт для TimeSlotDto -->
<%@ page import="org.example.crazybarbershop.dto.EmploeeDto" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Запись на прием</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styleAppointmentCSS.css">
</head>
<body>

<div class="container">
    <h2>Запись на прием</h2>

    <div class="employee-list">
        <c:forEach var="entry" items="${employeeTimeSlots}">
            <c:set var="emploeeDto" value="${entry.key}"/>
            <c:set var="timeSlots" value="${entry.value}"/>

            <div class="employee-card">
                <img src="${emploeeDto.urlImage}" alt="${emploeeDto.name} ${emploeeDto.surname}" class="employee-photo">
                <h3>${emploeeDto.name} ${emploeeDto.surname}</h3>
                <p><strong>Должность:</strong> ${emploeeDto.position}</p>
                <p><strong>Телефон:</strong> ${emploeeDto.phoneNumber}</p>

                <form action="${pageContext.request.contextPath}/appointment" method="POST" class="form-container">
                        <%-- Категория --%>
                    <%--@declare id="category"--%><%--@declare id="timeslot"--%><label for="category">Выберите категорию:</label>
                    <select name="category" required>
                        <option value="">-- Выберите категорию --</option>
                        <c:forEach var="category" items="${categories}">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>

                        <%-- Время --%>
                    <input type="hidden" name="employeeId" value="${emploeeDto.id}">
                    <label for="timeSlot">Выберите время:</label>
                    <select name="timeSlot" required>
                        <option value="">-- Выберите время --</option>
                        <c:forEach var="slot" items="${timeSlots}">
                            <option value="${slot.id}">${slot.startTime}</option>
                        </c:forEach>
                    </select>

                        <%-- Кнопка отправки --%>
                    <button type="submit" class="book-button">Записаться</button>
                </form>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
