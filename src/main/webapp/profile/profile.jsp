<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

    <title>${pageContext.request.userPrincipal.name}'s page</title>

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
            <a class="navbar-brand" href="${contextPath}/profile/profile">User page</a>
        </div>


        <div id="navbar" class="collapse navbar-collapse">

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
                <h1>${pageContext.request.userPrincipal.name}'s page</h1>
                <p>Here you can manage your profile</p>
            </div>
            <div class="row">

                    <h2>Common information</h2>
                    <p>Username: <b>${user.username}</b></p>
                    <p>Gender: <b>
                        <c:if test="${user.gender == 1}"><c:out value="Female" /></c:if>
                        <c:if test="${user.gender == 0}"><c:out value="Male" /></c:if> </b></p>
                    <p>
                    <p>E-mail: <b>${user.email}</b></p>
                    <p>Phone number: <b>${user.phoneNumber}</b></p>
                        <a class="btn btn-default" href="${contextPath}/profile/updateInformation" role="button">Update information &raquo;</a></p>


                    <h2>Addresses</h2>

                    <c:if test="${empty user.addresses}">
                    <p><c:out value="You have not added any address yet" /></p>
                    </c:if>
                    <c:forEach var="address" items="${user.addresses}">
                <p><b><c:out value="${address.cityByAddressId.cityName}" /></b>, <c:out value="${address.street}" /> street, building <c:out value="${address.buildingNumber}" />, room <c:out value="${address.roomNumber}" /><br />
                    <c:if test="${not empty address.comment}">Comment: <c:out value="${address.comment}" /></c:if></p>
                        <p>
                         <form:form method="DELETE" action="${contextPath}/profile/deleteAddress/${address.addressId}">
                             <a class="btn btn-default" href="${contextPath}/profile/updateAddress/${address.addressId}" role="button">Update</a>
                             <button class="btn btn-default"type="submit">Delete</button></form:form></p>
                </c:forEach>
                    <a class="btn btn-default" href="${contextPath}/profile/addAddress" role="button">Add address &raquo;</a></p>
                </p>

                    <h2>Current orders</h2>



                <table class="table table-bordered">

                    <tr >
                        <th>
                            Order

                        </th>
                        <th>
                            Delivery time

                        </th>
                        <th>
                            Full price
                        </th>

                        <th>
                            Payment info
                        </th>
                        <th>
                            Status
                        </th>


                    </tr>
                <c:forEach var="order" items="${formed}">


                    <tr>
                        <td>
                            <c:forEach var="element" items="${order.orderElements}">

                                <p><b><c:out value="${element.food.foodName}"/></b> <c:out value="${element.food.price}"/> &#8381; &#xD7; <c:out value="${element.quantity}"/> item<c:if test="${element.quantity > 1}">s</c:if></p>


                            </c:forEach>
                        </td>

                        <td>
                            <fmt:formatDate value="${order.deliveryTime}" pattern="dd/MM H:mm (zzzz)" />
                        </td>
                        <td>
                            <c:out value="${order.fullPrice }"/> &#8381;
                        </td>
                        <td>
                            <c:out value="${order.paymentInfo}"/>
                        </td>
                        <td>
                            <c:out value="${order.status}"/>
                        </td>


                    </tr>





                </c:forEach>


                <c:forEach var="order" items="${delivering}">




                        <tr>
                            <td>
                                <c:forEach var="element" items="${order.orderElements}">

                                    <p><b><c:out value="${element.food.foodName}"/></b> <c:out value="${element.food.price}"/> &#8381; &#xD7; <c:out value="${element.quantity}"/> item<c:if test="${element.quantity > 1}">s</c:if></p>


                                </c:forEach>
                            </td>

                            <td>
                                <fmt:formatDate value="${order.deliveryTime}" pattern="dd/MM H:mm (zzzz)" />
                            </td>
                            <td>
                                <c:out value="${order.fullPrice }"/> &#8381;
                            </td>
                            <td>
                                <c:out value="${order.paymentInfo}"/>
                            </td>
                            <td>
                                <c:out value="${order.status}"/>
                            </td>


                        </tr>


                </c:forEach>
                </table>

                <c:if test="${empty formed and empty delivering}">

                    <p>No current orders</p>

                </c:if>

                <h2>Order history</h2>



                <table class="table table-bordered">

                    <tr >
                        <th>
                            Order

                        </th>
                        <th>
                            Delivery time

                        </th>
                        <th>
                            Full price
                        </th>

                        <th>
                            Payment info
                        </th>
                        <th>
                            Status
                        </th>


                    </tr>
                    <c:forEach var="order" items="${delivered}">


                        <tr>
                            <td>
                                <c:forEach var="element" items="${order.orderElements}">

                                    <p><b><c:out value="${element.food.foodName}"/></b> <c:out value="${element.food.price}"/> &#8381; &#xD7; <c:out value="${element.quantity}"/> item<c:if test="${element.quantity > 1}">s</c:if></p>


                                </c:forEach>
                            </td>

                            <td>
                                <fmt:formatDate value="${order.deliveryTime}" pattern="dd/MM H:mm (zzzz)" />
                            </td>
                            <td>
                                <c:out value="${order.fullPrice }"/> &#8381;
                            </td>
                            <td>
                                <c:out value="${order.paymentInfo}"/>
                            </td>
                            <td>
                                <c:out value="${order.status}"/>
                            </td>


                        </tr>





                    </c:forEach>





                </table>

                <c:if test="${empty delivered}">

                    <p>No history</p>

                </c:if>



            </div><!--/row-->
        </div><!--/.col-xs-12.col-sm-9-->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
            <div class="list-group">
                <a href="#" class="list-group-item active">Link</a>
                <a href="#" class="list-group-item">Link</a>
                <a href="#" class="list-group-item">Link</a>
                <a href="#" class="list-group-item">Link</a>
                <a href="#" class="list-group-item">Link</a>
                <a href="#" class="list-group-item">Link</a>
                <a href="#" class="list-group-item">Link</a>
                <a href="#" class="list-group-item">Link</a>
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
