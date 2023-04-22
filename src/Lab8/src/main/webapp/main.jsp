<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="data" class="com.flock.lab8.CalculationBean" scope="session"/>

<!DOCTYPE html>
<html lang="ru" xml:lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Главная страница</title>
</head>
<body>
<h1>Главная страница</h1>

<%-- Вычисляем сумму и сохраняем ее в бин --%>
<%
    if (request.getParameter("sequence") != null) {
        data.setSequence(request.getParameter("sequence"));
        data.calculateSum();
    }
%>

<form method="get" action="main.jsp">
    <label for="sequence">Последовательность чисел:</label><br>
    <input type="text" id="sequence" name="sequence"><br><br>
    <input type="submit" value="Вычислить сумму">
</form>

<br>

<a href="finish.jsp">Результат</a>
<a href="start.jsp">На стартовую</a>

</body>
</html>