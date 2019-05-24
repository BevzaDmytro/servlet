<%@ page import="com.kpi.entities.User" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 23.05.2019
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% User user = (User)request.getAttribute("bean"); %>
<h1><%= user.getName()  %></h1>

</body>
</html>
