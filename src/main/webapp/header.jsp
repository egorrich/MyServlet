<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link href="css/bootstrap.css" type="text/css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <%--<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>--%>
            <a class="navbar-brand" href="/home.jsp">My Servlet</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <c:if test="${sName != null}">
        <ul class="nav navbar-nav navbar-left">
            <li class="Account" >
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"
                   role="button" aria-haspopup="true" aria-expanded="false">Menu<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/MyServlet">Users</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="#">DoSmth</a></li>
                </ul>
            </li>
        </ul>
        </c:if>
            <ul class="nav navbar-nav navbar-right">
                <%--<c:if test="${sName == null}">
                    <li><div style="text-align: right">Please, Log In</div></li>
                </c:if>
                <c:if test="${sName != null}">
                <li><div style="text-align: right">Hello, ${sName} </div></li>
                </c:if>--%>
                <li class="Account" >
                    <c:if test="${sName == null}">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-haspopup="true" aria-expanded="false">Please, Log In<span class="caret"></span></a>
                    </c:if>
                    <c:if test="${sName != null}">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                           aria-haspopup="true" aria-expanded="false">Hello, ${sName}<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/login.jsp">Change Account </a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Log Out </a></li>
                        </ul>
                    </c:if>

                    <%--<ul class="dropdown-menu">
                        <li><a href="/login.jsp">Change Account</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="/logout">Log Out</a></li>
                    </ul>--%>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<script type="text/javascript" src="webjars/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<%--<script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>--%>
</body>
</html>
