<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<header class="main-header">
    <div class="container clearfix">
        <nav class="main-navigation">
            <ul>
                <li><a href="#">Главная</a></li>
                <li><a href="#">Услуги</a></li>
                <li><a href="#">О нас</a></li>
                <li><a href="#">Портфолио</a></li>
                <li><a href="#">Контакты</a></li>
                <li><a href="${pageContext.request.contextPath}/appointment">Записаться</a></li>
            </ul>
        </nav>
        <div class="user-block">
            <a class="user-login" href="${pageContext.request.contextPath}/login">Вход</a>
        </div>
    </div>
</header>