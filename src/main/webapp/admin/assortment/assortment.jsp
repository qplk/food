<%@include file='/admin/header.jsp'%>
<c:set var="entity" value="Assortment" />
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

                <c:if test="${empty restaurantList}">
                    <div class="col-xs-6 col-lg-4">
                        <h2>No restaurants</h2>
                    </div><!--/.col-xs-6.col-lg-4-->
                </c:if>

                <c:forEach var="row" items="${restaurantList}">
                    <div class="col-xs-6 col-lg-4">
                        <h2><c:out value="${row.street}"/>, <c:out value="${row.buildingNumber}"/></h2>
                        <p><c:out value="${row.restaurantPhone}"/></p>
                        <p><c:out value="${row.cityByRestaurantId.cityName}"/></p>
                        <c:if test="${empty row.assortment}">
                            <h2>No assortment</h2>
                        </c:if>
                        <c:if test="${not empty row.assortment}">
                            <h2>Assortment</h2>
                        </c:if>
                    <c:forEach var="assortment" items="${row.assortment}">

                        <p><c:out value="${assortment.food.foodName}"/><c:if test="${assortment.quantity != null}">: <c:out value="${assortment.quantity}"/> items
                    </c:if> <c:if test="${assortment.enable == false}">
                            Disabled
                        </c:if>
                            <a class="btn btn-default" href="${contextPath}/admin/assortment/assortmentUpdate/${row.restaurantId}/${assortment.food.foodId}" role="button">Update</a>
                            <form:form method="DELETE" action="${contextPath}/admin/assortment/assortment/${assortment.assortmentId}">
                        <button class="btn btn-default" type="submit">Delete</button>
                    </form:form></p>

                    </c:forEach>

                        <p><a class="btn btn-default" href="${contextPath}/admin/assortment/assortmentAdd/${row.restaurantId}" role="button">Add item >></a></p>


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

<%@include file='/admin/footer.jsp'%>

