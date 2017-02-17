<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    if (session.getAttribute("sName") == null) {
        String redirectURL = "/login.jsp";
        response.sendRedirect(redirectURL);
    }
%>
<%@include file="header.jsp"%>
<%@include file="footer.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Home</title>
    <link href="css/bootstrap.css" type="text/css" rel="stylesheet">
    <style type="text/css">
        body {
            padding-top: 70px;
            padding-bottom: 70px;
        }
        table1 {
            width: 100px; /* ширина */
            padding: 20px; /* отступы внутри */
            background: #F9F9F9; /* фон */
            color: #555555; /* цвет текста */
            text-align: center; /* размещение текста */
        }
    </style>

</head>
    <body>

        <c:if test="${list != null}">

        <table class="table table-striped" border="1">
            <caption>
                <b><span style="color: #3d4bff;">
                   <h1 align="center"> <c:out value = '${TableName}'/></h1>
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
                       <%-- <a href="/users.jsp?id=${items.id}&name=${items.name}&lastName=${items.lastName}&password=${items.password}">Edit</a>--%>
                           <a href="/users?id=${items.id}&name=${items.name}&lastName=${items.lastName}&password=${items.password}">Edit</a>
                    </td>
                    <td><form action="/users">
                        <input type="submit" value="delete">
                        <input type="hidden" name="id" value="${items.id}">
                        <input type="hidden" name="operation" value="delete">
                    </form></td>
                </tr>
            </c:forEach>
            </tbody>
         </table>
            <form action="/users.jsp">
                <input type="submit" value="create">
            </form>
        </c:if>


    </body>
</html>

