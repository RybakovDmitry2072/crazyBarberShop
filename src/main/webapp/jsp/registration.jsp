<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация пользователя</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styleRegistratiomCSS.css">
</head>
<body>

<div class="container">
    <h2>Форма регистрации</h2>
    <form action="${pageContext.request.contextPath}/registration" method="post" accept-charset="UTF-8">
        <!-- Поля формы -->

        <div class="form-group">
            <label for="name">Имя:</label>
            <input type="text" id="name" name="name" required>
        </div>

        <div class="form-group">
            <label for="surname">Фамилия:</label>
            <input type="text" id="surname" name="surname" required>
        </div>

        <div class="form-group">
            <label for="login">Логин:</label>
            <input type="text" id="login" name="login" required>
        </div>

        <div class="form-group">
            <label for="email">Электронная почта:</label>
            <input type="email" id="email" name="email" required>
        </div>

        <div class="form-group">
            <label for="phone">Номер телефона:</label>
            <input type="tel" id="phone" name="phone" required pattern="[0-9]{10}">
        </div>

        <div class="form-group">
            <label for="password">Пароль:</label>
            <input type="password" id="password" name="password" required>
        </div>

        <div class="form-group">
            <label for="dob">Дата рождения:</label>
            <input type="date" id="dob" name="dob" required>
        </div>

        <!-- Пол для выбора пола -->
        <div class="radio-group">
            <label>Пол:</label>
            <label><input type="radio" name="gender" value="Мужской" required> Мужской</label>
            <label><input type="radio" name="gender" value="Женский" required> Женский</label>
        </div>

        <div class="form-group">
            <button type="submit">Зарегистрироваться</button>
        </div>
    </form>
</div>

</body>
</html>
