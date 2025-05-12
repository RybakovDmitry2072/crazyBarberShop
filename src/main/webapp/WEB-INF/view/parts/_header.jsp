<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header class="main-header">
    <div class="container clearfix">
        <nav class="main-navigation">
            <ul>
                <li><a href="${pageContext.request.contextPath}">Главная</a></li>
                <li><a href="${pageContext.request.contextPath}/service">Услуги</a></li>
                <li><a href="${pageContext.request.contextPath}/about">О нас</a></li>
                <li><a href="${pageContext.request.contextPath}/portfolio">Портфолио</a></li>
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
                <c:if test="${sessionScope.user.role == 'ADMIN'}">
                    <a class="user-profil" href="${pageContext.request.contextPath}/admin/users">Админ панель</a>
                </c:if>
            </c:if>
        </div>
    </div>
</header>



