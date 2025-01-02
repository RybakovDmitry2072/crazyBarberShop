<%--
  Created by IntelliJ IDEA.
  User: rybakovdmitry
  Date: 15.12.2024
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mainLayuot title="Портфолио" cssPath="stylePortfolio">
  <%@include file="/WEB-INF/view/parts/_header.jsp"%>
  <div class="container">
    <h1 class="index-content-title">Наше портфолио</h1>
    <div class="portfolio-grid">
      <c:forEach var="portfolioItem" items="${portfolioImgDto}">
        <div class="portfolio-item">
          <img src="${portfolioItem.url}" alt="Фото">
        </div>
      </c:forEach>
    </div>
  </div>
</t:mainLayuot>
<%@include file="/WEB-INF/view/parts/_footer.jsp"%>
</html>
