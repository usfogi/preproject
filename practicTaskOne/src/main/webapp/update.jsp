<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 16/06/2020
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="POST" action="/update">               <%--<%= request.getContextPath() %>--%>
        ID : <input type="text" name="id" value=<c:out value="${user.id}" />> <br/>
        Name : <input type="text" name="name" value=<c:out value="${user.name}" />> <br/>
        Pass : <input type="text" name="password" value=<c:out value="${user.password}" />> <br/>
        Age : <input type="text" name="age" value=<c:out value="${user.age}" />> <br/>

        <input type="submit" value="Submit"/>
    </form>

</body>
</html>
