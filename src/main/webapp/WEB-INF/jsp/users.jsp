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
<form name="submitForm" method="GET" action="/homeController">
    <A HREF="javascript:document.submitForm.submit()"><span class="glyphicon glyphicon-arrow-left"></span>BACK</A>
</form>
<%@include file="footer.jsp"%>
</body>
</html>
