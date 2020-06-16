<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Signup</title>
</head>
<body>
<form action="<%= request.getContextPath() %>/register" method="post">
    Name <input type="text" name="name"></br>
    Password <input type="text" name="password"></br>
    Age <input type="text" name="age"></br>
    <input type="submit" value="Register new user">
</form>
</body>
</html>
