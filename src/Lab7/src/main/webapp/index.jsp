<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru" xml:lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Главная страница</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        background-color: #f0f0f0;
      }

      h1 {
        font-size: 32px;
        text-align: center;
      }

      a {
        display: block;
        text-align: center;
        font-size: 24px;
        color: #333;
        text-decoration: none;
        padding: 12px;
        margin: 24px auto;
        border: 2px solid #333;
        width: 200px;
        border-radius: 4px;
        background-color: #fff;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
      }

      a:hover {
        background-color: #333;
        color: #fff;
      }
    </style>
</head>
<body>
<a href="counter-servlet">Перейти</a>
</body>
</html>