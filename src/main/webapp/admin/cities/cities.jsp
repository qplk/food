<%@include file='/admin/header.jsp'%>
<c:set var="entity" value="City" />
<%@include file='/admin/nav.jsp'%>


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

                <c:if test="${empty citiesList}">
                    <div class="col-xs-6 col-lg-4">
                        <h2>No cities</h2>
                    </div><!--/.col-xs-6.col-lg-4-->
                </c:if>

                <c:forEach var="row" items="${citiesList}">
                    <div class="col-xs-6 col-lg-4">
                        <h2><c:out value="${row.cityName}"/></h2>
                        <p><c:out value="${row.openTime}"/> - <c:out value="${row.closeTime}"/></p>
                        <p><c:out value="${row.deliveryPhone}"/></p>
                        <p>Delivery time: <c:out value="${row.deliveryTime}"/></p>
                        <p>Min price: <c:out value="${row.minPrice}"/></p>
                        <p>Restaurants: <c:forEach var="restaurant" items="${row.restaurants}">
                            <c:out value="${restaurant.street}, ${restaurant.buildingNumber}"/><br />
                        </c:forEach></p>
                        <p> <a class="btn btn-default" href="${contextPath}/admin/cities/cityUpdate/${row.cityId}" role="button">Update</a></p>
                        <p><form:form method="DELETE" action="${contextPath}/admin/cities/cities/${row.cityId}">
                            <button class="btn btn-default" type="submit">Delete</button>
                        </form:form></p>

                    </div><!--/.col-xs-6.col-lg-4-->
                </c:forEach>




            </div><!--/row-->
        </div><!--/.col-xs-12.col-sm-9-->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
            <div class="list-group">
                <a href="#" class="list-group-item active">Food</a>
                <!--<a href=".jsp" class="list-group-item">Add new item</a>-->

                <form class="navbar-form navbar-right" action="${contextPath}/admin/cities/cityAdd">
                    <button class="btn btn-success">Add new item</button>
                </form>
            </div>
        </div><!--/.sidebar-offcanvas-->
    </div><!--/row-->

    <hr>
</div><!--/.container-->

<%@include file='/admin/footer.jsp'%>

