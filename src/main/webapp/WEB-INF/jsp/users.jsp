<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
    <link href="css/bootstrap.css" type="text/css" rel="stylesheet">
    <style type="text/css">
        body {
            padding-top: 70px;
        }
    </style>
</head>
<body>
<%@include file="header.jsp"%>
<%@include file="footer.jsp"%>
<%--<div style="text-align: right">Hello, ${sName}
    <form action="/logout" method="post">
        <input type="submit" value="Log Out">
    </form>
</div>--%>
<c:if test="${user == null}">
<form action="/users" method="post">
    <input type="text" name="name" placeholder="Enter Name">
    <input type="text" name="lastName" placeholder="Enter Last Name">
    <input type="password" name="password" placeholder="Enter Password">
    <input type="hidden" name="operation" value="create">
    <input type="submit" value="Create User">
</form>
</c:if>

<c:if test="${user != null}">
<form action="/users" method="get">
    Name
    <input type="text" name="name" value="${user.name}">
    Last name
    <input type="text" name="lastName" value="${user.lastName}" >
    Password
    <input type="text" name="password" value="${user.password}" >
    <input type="hidden" name="id" value="${user.id}" >
    <input type="hidden" name="operation" value="update">
    <input type="submit" value="Update User">
</form>
</c:if>
<a  href="http://localhost:8080/home.jsp"><span class="glyphicon glyphicon-arrow-left"></span> BACK</a>
</body>
</html>
