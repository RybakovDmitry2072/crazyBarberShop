<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mainLayuot title="Вход" cssPath="styleLoginCSS">
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
        <div class="form-group">
            <a href="${pageContext.request.contextPath}/registration" class="btn btn-register">Зарегистрироваться</a>
        </div>
    </form>
    <c:if test="${not empty error}">
        <div class="error-message">
            <span>${error}</span>
        </div>
    </c:if>

</div>
</t:mainLayuot></t>
</html>
