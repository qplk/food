<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

    <title>Admin page Order</title>

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
                <li><a href="${contextPath}/admin/users/users">Users</a></li>
                <li><a href="${contextPath}/admin/restaurants/restaurants">Restaurants</a></li>
                <li><a href="${contextPath}/admin/cities/cities">Cities</a></li>
                <li class="active"><a href="#">Orders</a></li>
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


                <div class="col-xs-6 col-lg-4">
                        <p>User: <c:out value="${order.user.username}"/></p>
                        <p>Full price: <c:out value="${order.fullPrice}"/></p>
                        <p>Delivery time: <c:out value="${order.deliveryTime}"/></p>

                        <p>Status: <c:out value="${order.status}"/></p>
                        <p>Status info: <c:out value="${order.statusInfo}"/></p>
                        <p>Payment info: <c:out value="${order.paymentInfo}"/></p>

                </div><!--/.col-xs-6.col-lg-4-->



                <c:if test="${empty order.orderElements}">
                    <div class="col-xs-6 col-lg-4">
                        <h2>No order elements</h2>
                    </div><!--/.col-xs-6.col-lg-4-->
                </c:if>


                <c:forEach var="row" items="${order.orderElements}">
                    <div class="col-xs-6 col-lg-4">
                        <p><c:out value="${row.food.foodName}"/> <c:out value="${row.food.price}"/> quantity: <c:out value="${row.quantity}"/></p>
                        <form:form method="DELETE" action="${contextPath}/admin/orders/order/${order.orderId}/${row.orderElementId}">
                            <button class="btn btn-default"type="submit">Delete</button>
                        </form:form>

                    </div><!--/.col-xs-6.col-lg-4-->
                </c:forEach>
                <form:form method="POST" modelAttribute="orderForm" action="${contextPath}/admin/orders/order">

                    <spring:bind path="fullPrice">
                        <form:errors path="fullPrice"></form:errors>

                    </spring:bind>

                <button class="btn btn-default"type="submit">Form</button>
                </form:form>


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
