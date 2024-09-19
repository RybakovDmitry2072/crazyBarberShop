<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
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
        <c:forEach var="emploeeDto" items="${employees}">
            <div class="employee-card">
                <img src="${emploeeDto.urlImage}" alt="${emploeeDto.name} ${emploeeDto.surname}" class="employee-photo">
                <h3>${emploeeDto.name} ${emploeeDto.surname}</h3>
                <p><strong>Должность:</strong> ${emploeeDto.position}</p>
                <p><strong>Телефон:</strong> ${emploeeDto.phoneNumber}</p>

            <%--                <a href="book?employeeId=${emploeeDto.id}" class="book-button">Записаться</a>--%>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
