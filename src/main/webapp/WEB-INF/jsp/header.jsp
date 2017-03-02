<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="/homeController">My Servlet</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <c:if test="${sName != null}">
        <ul class="nav navbar-nav navbar-left">
            <li class="Account" >
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"
                   role="button" aria-haspopup="true" aria-expanded="false">Menu<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/homeController">Users</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="#">DoSmth</a></li>
                </ul>
            </li>
        </ul>
        </c:if>
            <ul class="nav navbar-nav navbar-right">
                <li class="Account" >
                    <c:if test="${sName == null}">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-haspopup="true" aria-expanded="false">Please, Log In<span class="caret"></span></a>
                    </c:if>
                    <c:if test="${sName != null}">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                           aria-haspopup="true" aria-expanded="false">Hello, ${sName}<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/login">Change Account </a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Log Out </a></li>
                        </ul>
                    </c:if>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

