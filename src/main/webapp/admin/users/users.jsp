<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <sec:csrfMetaTags/>

    <title>Admin page Users</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<nav class="navbar navbar-fixed-top navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${contextPath}/admin/admin.jsp">Admin page</a>
        </div>

        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="${contextPath}/admin/food/food">Food</a></li>
                <li class="active"><a href="#">Users</a></li>
                <li><a href="${contextPath}/admin/restaurants/restaurants">Restaurants</a></li>
                <li><a href="${contextPath}/admin/cities/cities">Cities</a></li>
                <li><a href="${contextPath}/admin/orders/orders">Orders</a></li>
                <li><a href="${contextPath}/admin/assortment/assortment">Assortment</a></li>
            </ul>

            <c:if test="${pageContext.request.userPrincipal.name == null}">
                <form class="navbar-form navbar-right" action="${contextPath}/login">
                    <a href="#ModalSign" class="btn btn-success" data-toggle="modal">Sign in</a>
                </form>
            </c:if>

            <c:if test="${pageContext.request.userPrincipal.name == null}">
                <form class="navbar-form navbar-right" action="${contextPath}/registration">
                    <a href="#ModalReg" class="btn btn-success" data-toggle="modal">Registration</a>
                </form>
            </c:if>

            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <p class="navbar-text navbar-right">Signed in as <a href="profile.jsp" class="navbar-link">${pageContext.request.userPrincipal.name}</a></p>
            </c:if>

            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <form class="navbar-form navbar-right" id="logoutForm" method="POST" action="${contextPath}/logout">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <a onclick="document.forms['logoutForm'].submit()"><button class="btn btn-success">logout</button></a>
                </form>
            </c:if>

        </div><!-- /.nav-collapse -->
    </div><!-- /.container -->
</nav><!-- /.navbar -->

<div class="container">

    <div class="row row-offcanvas row-offcanvas-right">

        <div class="col-xs-12 col-sm-9">
            <p class="pull-right visible-xs">
                <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
            </p>
            <div class="jumbotron">
                <h1>Admin page</h1>
                <p>Here you can manage smth</p>
            </div>
            <div class="row">

                <c:if test="${empty usersList}">
                    <div class="col-xs-6 col-lg-4">
                        <h2>No users</h2>
                    </div><!--/.col-xs-6.col-lg-4-->
                </c:if>

                <c:forEach var="user" items="${usersList}">
                    <div class="col-xs-6 col-lg-4">
                        <h2><c:out value="${user.username}"/></h2>
                        <p>Gender:
                        <c:if test="${user.gender == 1}">

                                Female

                        </c:if>
                        <c:if test="${user.gender == 0}">

                                Male

                        </c:if>
                        </p>
                        <p>Email: <c:out value="${user.email}"/></p>
                        <p>Phone number: <c:out value="${user.phoneNumber}"/></p>
                        <p>Roles:
                    <c:forEach var="role" items="${user.roles}">
                        <c:out value="${role.name}" /><br />
                        </c:forEach></p>

                        <p>Adresses:
                            <c:forEach var="address" items="${user.addresses}">
                        <p><c:out value="${address.street}" />, <c:out value="${address.buildingNumber}" />, <c:out value="${address.roomNumber}" /><br />
                            Comment: <c:out value="${address.comment}" /></p>
                        </c:forEach>
                        </p>

                        <p>Information: ${user.information}</p>
                        <p><a class="btn btn-default" href="${contextPath}/admin/users/userUpdate/${user.userId}" role="button">Update</a></p>
                        <p><a class="btn btn-default" href="#" role="button">Delete</a></p>

                    </div><!--/.col-xs-6.col-lg-4-->
                </c:forEach>




            </div><!--/row-->
        </div><!--/.col-xs-12.col-sm-9-->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
            <div class="list-group">
                <a href="#" class="list-group-item active">Food</a>
                <!--<a href=".jsp" class="list-group-item">Add new item</a>-->
                
            </div>
        </div><!--/.sidebar-offcanvas-->
    </div><!--/row-->

    <hr>
</div><!--/.container-->

<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
