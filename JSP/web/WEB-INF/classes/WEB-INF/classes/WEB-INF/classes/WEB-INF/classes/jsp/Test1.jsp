<%--
  User: 脱氧核糖
  Date: 2021/5/2
  Time: 20:44
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        table{
            width: 650px;
        }
    </style>
</head>
<body>
    <h1 align="center">乘法表</h1>
    <table align="center">
        <%
            for (int i = 1; i <= 9; i++) {
        %>
        <tr>
            <% for (int j = 1; j <= i; j++) { %>
            <td><%=j + "*" + i + "=" + (i*j) + " "%></td>
            <% } %>
        </tr>
        <% } %>
    </table>
</body>
</html>
