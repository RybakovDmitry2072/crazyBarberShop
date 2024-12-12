<%@tag description="Default Layout Tag" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@attribute name="title" %>
<%@attribute name="cssPath" %>


<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>${title}</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/${cssPath}.css">
</head>
<body>
<jsp:doBody />

</body>
</html>
