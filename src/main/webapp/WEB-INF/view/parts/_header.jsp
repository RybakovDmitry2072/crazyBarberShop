<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header class="main-header">
    <div class="container clearfix">
        <nav class="main-navigation">
            <ul>
                <li><a href="${pageContext.request.contextPath}">Главная</a></li>
                <li><a href="#">Услуги</a></li>
                <li><a href="#">О нас</a></li>
                <li><a href="#">Портфолио</a></li>
                <li><a href="#">Контакты</a></li>
                <li><a href="${pageContext.request.contextPath}/appointment">Записаться</a></li>
            </ul>
        </nav>

        <div class="user-block">
            <c:if test="${empty sessionScope.user}">
                <a class="user-login" href="${pageContext.request.contextPath}/login">Вход</a>
            </c:if>
            <c:if test="${not empty sessionScope.user}">
                <a class="user-login" href="${pageContext.request.contextPath}/logout">Выход</a>
                <a class="user-profil" href="${pageContext.request.contextPath}/profile">Профиль</a>

            </c:if>
        </div>
    </div>
</header>



