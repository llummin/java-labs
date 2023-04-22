<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="data" class="com.flock.lab8.CalculationBean" scope="session"/>

<!DOCTYPE html>
<html lang="ru" xml:lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Главная страница</title>
    <style>
        <%@include file="/WEB-INF/css/main.css" %>
    </style>
</head>
<body>
<h1>Главная страница</h1>
<h3>Счетчик: <%= data.getCounter() %>
</h3>

<%-- Вычисляем сумму и сохраняем ее в бин --%>
<%
    if (request.getParameter("sequence") != null) {
        data.setSequence(request.getParameter("sequence"));
        data.calculateSum();
    }
%>

<form method="get" action="main.jsp">
    <label for="sequence">Последовательность чисел:</label><br>
    <input type="text" id="sequence" name="sequence" style="width: 70%;"/>
    <input type="submit" value="Вычислить" style="display: inline-block; width: 25%;"/>
</form>

<br>

<a href="start.jsp">На стартовую</a>
<a href="finish.jsp">На финишную</a>

</body>
</html>