<%--
  Created by IntelliJ IDEA.
  User: rybakovdmitry
  Date: 15.12.2024
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Unauthorized Access</title>
  <style>
    body {
      font-family: "PT Sans Narrow", "Arial", sans-serif;
      text-transform: uppercase;
      background: #000000 url("https://i.imgur.com/UhuqbXb.jpg") no-repeat center top;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
    }
    .unauthorized-container {
      background-color: #fff;
      padding: 20px;
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      text-align: center;
    }
    .unauthorized-container h2 {
      margin-bottom: 20px;
    }
    .unauthorized-container a {
      display: inline-block;
      padding: 10px 20px;
      background-color: #007BFF;
      color: #fff;
      text-decoration: none;
      border-radius: 3px;
      font-size: 16px;
    }
    .unauthorized-container a:hover {
      background-color: #0056b3;
    }
  </style>
</head>
<body>
<div class="unauthorized-container">
  <h2>Упсс... Кажется вы не авторизованны (</h2>
  <p>Пожалуйста, вернить на главную страницу <a href="${pageContext.request.contextPath}/">Главная</a></p>
</div>
</body>
</html>

