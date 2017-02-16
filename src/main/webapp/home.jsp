<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<%--<%--%>
    <%--String redirectURL = "/login.jsp";--%>
    <%--response.sendRedirect(redirectURL);--%>
<%--%>--%>



<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Table</title>
    <style>
        table {
            border-spacing: 0;
        }
        tr:nth-child(2n) {
            background: #f0f0f0;
        }
        tr:nth-child(2n+1) {
            background: #666;
            color: #fff;
        }
        tr:nth-child(1) {
            background: #FFE4E1;
            color: inherit;
        }

    </style>

</head>
    <body>
        <div style="text-align: right">Hello, ${sName}
            <form action="/logout" method="post">
                <input type="submit" value="Log Out">
            </form>
        </div>
        <table border="1">
            <caption>
                <b><span style="color: #ff6600;">
                    <c:out value = '${TableName}'/>
                    </span>
                </b>
            </caption>

            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Last Name</th>
                <th>Password</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${list}" var="items">
                <tr>
                    <th> ${items.id}</th>
                    <th> ${items.name}</th>
                    <th> ${items.lastName}</th>
                    <th> ${items.password}</th>
                    <th><form action="/users">
                        <input type="submit" value="delete">
                        <input type="hidden" name="id" value="${items.id}">
                        <input type="hidden" name="operation" value="delete">
                    </form></th>

                    <th>
                        <a href="/users.jsp?id=${items.id}&name=${items.name}&lastName=${items.lastName}&password=${items.password}">Edit</a>
                    </th>
                </tr>
            </c:forEach>

         </table>
    <form action="users.jsp">
        <input type="submit" value="create">
    </form>

    </body>
</html>

