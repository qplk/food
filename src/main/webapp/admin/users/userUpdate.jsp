<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <title>Admin page User Update</title>

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
                <li><a href="#">Food</a></li>
                <li class="active"><a href="${contextPath}/admin/users/users">Users</a></li>
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


            <form:form method="POST" modelAttribute="userForm" class="form-signin">
                <h2 class="form-signin-heading">Update user</h2>

                <spring:bind path="username">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="username" class="form-control" placeholder="Username"
                                    autofocus="true" value="${user.username}"></form:input>
                        <form:errors path="username"></form:errors>
                    </div>
                </spring:bind>


                <spring:bind path="email">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="email" class="form-control" placeholder="Email"
                                    autofocus="true" value="${user.email}"></form:input>
                        <form:errors path="email"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="phoneNumber">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="phoneNumber" class="form-control" placeholder="Phone number"
                                    autofocus="true" value="${user.phoneNumber}"></form:input>
                        <form:errors path="phoneNumber"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="gender">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:select class="form-control" path="gender">
                            <option value="0" <c:if test="${user.gender == 0}">selected</c:if>>Male</option>
                            <option value="1" <c:if test="${user.gender == 1}">selected</c:if>>Female</option>
                        </form:select>
                    </div>
                </spring:bind>

                <spring:bind path="information">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="information" class="form-control" placeholder="Information"
                                    autofocus="true" value="${user.information}"></form:input>
                        <form:errors path="information"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="roles">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <c:if test="${empty roles}">

                            <option>No roles exist</option>

                        </c:if>
                        <c:if test="${not empty roles}">
                            <p>Roles:
                            <c:forEach var="hasRole" items="${user.roles}">
                               <c:out value="${hasRole.name} " /><br />
                            </c:forEach>
                            </p>

                            <c:forEach var="role" items="${roles}">
                                <form:checkbox path="roles" value="${role}" /> ${role.name}
                            </c:forEach>
                        </c:if>

                        <form:errors path="roles"></form:errors>
                    </div>
                </spring:bind>


                <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
            </form:form>

        </div><!--/.col-xs-12.col-sm-9-->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
            <div class="list-group">
                <a href="#" class="list-group-item">Link</a>
                <a href="#" class="list-group-item">Link</a>
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
