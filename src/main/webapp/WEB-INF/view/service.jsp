<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mainLayuot title="Услуги" cssPath="styleService">
    <%@include file="/WEB-INF/view/parts/_header.jsp"%>



    <div class="container">
    <h1 class="index-content-title">Наши услуги</h1>
    <div class="features">
        <c:forEach var="category" items="${categoryDtoList}">
            <div class="features-item">
                <img src="${category.urlImg}" alt="${category.name}">
                <span class="features-name">${category.name}</span>
                <p>Цена: ${category.price} ₽</p>
            </div>
        </c:forEach>
    </div>
</div>
</t:mainLayuot></t>

<%@include file="/WEB-INF/view/parts/_footer.jsp"%>

