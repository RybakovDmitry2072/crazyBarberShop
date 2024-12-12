
<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html lang="ru">--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1">--%>
<%--    <title>Запись на стрижку | Crazy BarberShop</title>--%>
<%--    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">--%>
<%--    <style>--%>
<%--        body {--%>
<%--            background: linear-gradient(to right, #f8f9fa, #e9ecef);--%>
<%--            font-family: 'Arial', sans-serif;--%>
<%--        }--%>
<%--        main.container {--%>
<%--            margin-top: 30px;--%>
<%--            background: #ffffff;--%>
<%--            padding: 20px;--%>
<%--            border-radius: 8px;--%>
<%--            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);--%>
<%--        }--%>
<%--        h1 {--%>
<%--            text-align: center;--%>
<%--            margin-bottom: 20px;--%>
<%--        }--%>
<%--        form .grid {--%>
<%--            margin-top: 20px;--%>
<%--        }--%>
<%--        .profile-card {--%>
<%--            display: flex;--%>
<%--            align-items: center;--%>
<%--            margin-bottom: 15px;--%>
<%--        }--%>
<%--        .profile-pic {--%>
<%--            width: 50px;--%>
<%--            height: 50px;--%>
<%--            border-radius: 50%;--%>
<%--            margin-right: 15px;--%>
<%--            object-fit: cover;--%>
<%--            border: 2px solid #ddd;--%>
<%--        }--%>
<%--        select, input[type="text"], input[type="email"], button {--%>
<%--            width: 100%;--%>
<%--        }--%>
<%--        footer {--%>
<%--            text-align: center;--%>
<%--            margin-top: 30px;--%>
<%--            font-size: 14px;--%>
<%--        }--%>
<%--        footer a {--%>
<%--            color: #007bff;--%>
<%--            text-decoration: none;--%>
<%--        }--%>
<%--    </style>--%>
<%--</head>--%>
<%--<body>--%>
<%--<!-- Навигация -->--%>
<%--<nav class="container-fluid">--%>
<%--    <ul>--%>
<%--        <li><strong>Crazy BarberShop</strong></li>--%>
<%--    </ul>--%>
<%--    <ul>--%>
<%--        <li><a href="/">Главная</a></li>--%>
<%--        <li><a href="/services">Услуги</a></li>--%>
<%--        <li><a href="/appointment" role="button">Записаться</a></li>--%>
<%--    </ul>--%>
<%--</nav>--%>

<%--<!-- Основной блок -->--%>
<%--<main class="container">--%>
<%--    <h1>Запись на стрижку</h1>--%>
<%--    <p>Пожалуйста, выберите тип стрижки, мастера и удобное время. Затем заполните свои данные для подтверждения.</p>--%>

<%--    <form method="post" action="/appointment">--%>
<%--        <div class="grid">--%>
<%--            <!-- Блок выбора стрижки -->--%>
<%--            <section>--%>
<%--                <hgroup>--%>
<%--                    <h2>Тип стрижки</h2>--%>
<%--                    <h3>Выберите желаемую категорию</h3>--%>
<%--                </hgroup>--%>
<%--                <label for="haircutType">Категория</label>--%>
<%--                <select name="haircutType" id="haircutType" required>--%>
<%--                    <c:forEach var="category" items="${categories}">--%>
<%--                        <option value="${category}">${category}</option>--%>
<%--                    </c:forEach>--%>
<%--                </select>--%>
<%--            </section>--%>

<%--            <!-- Блок выбора мастера и времени -->--%>
<%--            <section>--%>
<%--                <hgroup>--%>
<%--                    <h2>Мастер и время</h2>--%>
<%--                    <h3>Выберите мастера и доступное время</h3>--%>
<%--                </hgroup>--%>
<%--                <c:forEach var="employeeEntry" items="${employeeTimeSlots}">--%>
<%--                    <div class="profile-card">--%>
<%--                        <img src="${employeeEntry.key.urlImage}" alt="${employeeEntry.key.name}" class="profile-pic">--%>
<%--                        <div>--%>
<%--                            <strong>${employeeEntry.key.name} ${employeeEntry.key.surname}</strong><br>--%>
<%--                            <small>${employeeEntry.key.position}</small>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <label for="timeSlot_${employeeEntry.key.name}">--%>
<%--                        Доступное время для ${employeeEntry.key.name}:--%>
<%--                    </label>--%>
<%--                    <select name="timeSlot" id="timeSlot_${employeeEntry.key.name}" required>--%>
<%--                        <c:forEach var="timeSlot" items="${employeeEntry.value}">--%>
<%--                            <option value="${timeSlot.startTime}">--%>
<%--                                    ${timeSlot.startTime}--%>
<%--                            </option>--%>
<%--                        </c:forEach>--%>
<%--                    </select>--%>
<%--                </c:forEach>--%>
<%--            </section>--%>
<%--        </div>--%>

<%--        <!-- Блок данных пользователя -->--%>
<%--        <section>--%>
<%--            <hgroup>--%>
<%--                <h2>Ваши данные</h2>--%>
<%--                <h3>Введите имя и email</h3>--%>
<%--            </hgroup>--%>
<%--            <label for="userName">Ваше имя</label>--%>
<%--            <input type="text" id="userName" name="userName" placeholder="Введите ваше имя" required>--%>

<%--            <label for="userEmail">Ваш email</label>--%>
<%--            <input type="email" id="userEmail" name="userEmail" placeholder="Введите ваш email" required>--%>

<%--            <button type="submit">Записаться</button>--%>
<%--        </section>--%>
<%--    </form>--%>
<%--</main>--%>

<%--<!-- Футер -->--%>
<%--<footer>--%>
<%--    <small>--%>
<%--        <a href="/terms">Условия использования</a> • <a href="/privacy">Политика конфиденциальности</a>--%>
<%--    </small>--%>
<%--</footer>--%>
<%--</body>--%>
<%--</html>--%>
