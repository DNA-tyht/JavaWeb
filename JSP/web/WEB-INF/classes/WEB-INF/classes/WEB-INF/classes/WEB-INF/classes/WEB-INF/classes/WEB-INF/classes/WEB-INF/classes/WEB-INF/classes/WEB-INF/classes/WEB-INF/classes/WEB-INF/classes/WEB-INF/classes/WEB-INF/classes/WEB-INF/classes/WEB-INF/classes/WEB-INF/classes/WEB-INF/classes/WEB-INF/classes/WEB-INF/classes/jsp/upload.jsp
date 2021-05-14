<%--
  User: 脱氧核糖
  Date: 2021/5/14
  Time: 14:58
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
    <base href="http://localhost:8080/JSP/">
</head>
<body>
    <form action="uploadServlet" method="post" enctype="multipart/form-data">
        用户名：<input type="text" name="username"> <br>
        头像：<input type="file" name="photo"> <br>
        <input type="submit" value="上传">
    </form>
</body>
</html>
