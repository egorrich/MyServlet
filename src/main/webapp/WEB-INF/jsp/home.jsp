<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%
    if (session.getAttribute("sName") == null) {
        String redirectURL = "/login.jsp";
        response.sendRedirect(redirectURL);
    }
    session.setAttribute("user", null);
%>--%> <% //TODO: session is null. fix it %>
<html>
<head>
    <%@include file="header.jsp" %>
    <%@include file="footer.jsp" %>
    <%@include file="scripts.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Home</title>
    <link href="css/bootstrap.css" type="text/css" rel="stylesheet">
    <style type="text/css">
        body {
            padding-top: 70px;
            padding-bottom: 70px;
        }
    </style>

</head>
<body>

<c:if test="${list != null}">

    <table class="table table-striped" border="1">
        <caption>
            <b><span style="color: #3d4bff;">
                   <h1 align="center"> <c:out value='${TableName}'/></h1>
                    </span>
            </b>
        </caption>
        <thread>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Last Name</th>
                <th>Password</th>
                <th></th>
                <th></th>
            </tr>
        </thread>
        <tbody>
        <c:forEach items="${list}" var="items">
            <tr>
                <td> ${items.id}</td>
                <td> ${items.name}</td>
                <td> ${items.lastName}</td>
                <td> ${items.password}</td>
                <td>
                    <a href="/users?id=${items.id}&name=${items.name}&lastName=${items.lastName}&password=${items.password}">edit</a>
                </td>
                <td>
                    <%--<form action="/users">
                        <input type="submit" value="delete">
                        <input type="hidden" name="id" value="${items.id}">
                        <input type="hidden" name="operation" value="delete">
                    </form>--%>
                    <a href="/users?id=${items.id}&operation=delete">delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="/WEB-INF/jsp/users.jsp" class="btn btn-large btn-primary">Create</a>
</c:if>

</body>
</html>

