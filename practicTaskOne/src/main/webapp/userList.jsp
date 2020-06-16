<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 16/06/2020
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border=1 align="center">
    <thead>
    <tr>
        <td>ID</td>
        <td>Имя</td>
        <td>Пароль</td>
        <td>Возраст</td>
    </tr>
    </thead>
    <tbody>
<c:forEach items="${userList}" var="user">
        <tr>
            <td><c:out value="${user.id}" escapeXml="false"/></td>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.password}"/></td>
            <td><c:out value="${user.age}"/></td>
            <td><a href="update?action=edit&id=${user.id}">Update</a></td>
            <td><a href="delete?action=delete&id=${user.id}">Delete</a></td>
        </tr>
</c:forEach>
    </tbody>
</table>
</body>
</html>
