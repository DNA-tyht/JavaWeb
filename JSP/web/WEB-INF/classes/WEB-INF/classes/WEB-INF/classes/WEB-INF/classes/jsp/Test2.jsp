<%@ page import="java.util.List" %>
<%@ page import="com.DNA.bean.Student" %>
<%@ page import="java.util.ArrayList" %>
<%--
  User: 脱氧核糖
  Date: 2021/5/2
  Time: 21:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        table{
            border: 1px deepskyblue solid;
            width: 600px;
            border-collapse: collapse;
        }
        td, th{
            border: 1px deepskyblue solid;
        }
    </style>
</head>
<body>
    <%
        List<Student> studentList = new ArrayList<Student>();
        for (int i = 0; i < 10; i++) {
            studentList.add(new Student(i + 1, "name" + i, 18 + i, "phone" + 1));
        }
    %>
    <table>
        <tr>
            <td>编号</td>
            <td>姓名</td>
            <td>年龄</td>
            <td>电话</td>
            <td>操作</td>
        </tr>

        <% for (Student student : studentList) { %>
        <tr>
            <td><%=student.getId()%></td>
            <td><%=student.getName()%></td>
            <td><%=student.getAge()%></td>
            <td><%=student.getPhone()%></td>
            <td>删除，修改</td>
        </tr>
        <% } %>
    </table>
</body>
</html>
