<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
    <link href="../../css/bootstrap.css" type="text/css" rel="stylesheet">
    <style type="text/css">
        body {
            padding-top: 70px;
        }
    </style>
</head>
<body>
<%@include file="header.jsp"%>
    <form action="/login" method="post">
        Login
        <input type="text" name="name" placeholder="Type Login">
        Password
        <input type="password" name="password" placeholder="Type Password">
        <input type="submit" value="Login">
    </form>

<%@include file="footer.jsp"%>
</body>
</html>
