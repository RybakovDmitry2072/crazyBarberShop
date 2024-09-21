<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Вход в аккаунт</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styleLoginCSS.css">
</head>
<body>

<div class="container">
    <h2>Вход в аккаунт</h2>
    <form action="${pageContext.request.contextPath}/login" method="post" accept-charset="UTF-8">
        <div class="form-group">
            <label for="login">Логин:</label>
            <input type="text" id="login" name="login" required>
        </div>

        <div class="form-group">
            <label for="password">Пароль:</label>
            <input type="password" id="password" name="password" required>
        </div>

        <div class="form-group">
            <button type="submit">Войти</button>
        </div>
    </form>
    <c:if test="${not empty error}">
        <div class="error-message">
            <span>${error}</span>
        </div>
    </c:if>









</div>

</body>
</html>
