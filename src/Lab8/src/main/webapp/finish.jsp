<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="data" class="com.flock.lab8.CalculationBean" scope="session"/>

<!DOCTYPE html>
<html lang="ru" xml:lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Финишная страница</title>
</head>
<body>
<h3>
    <table border="1">
        <tr>
            <th>Результат выполнения функции</th>
        </tr>
        <tr>
            <td><%= data.getResult() %>
            </td>
        </tr>
    </table>
</h3>

<a href="main.jsp">
    На главную
    <%
        data.increaseCounter();
    %>
</a>
</body>
</html>