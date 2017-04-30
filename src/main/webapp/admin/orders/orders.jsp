<%@include file='/admin/header.jsp'%>
<c:set var="entity" value="Order" />
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

                <c:if test="${empty formed}">
                    <div class="col-xs-6 col-lg-4">
                        <h2>No formed orders</h2>
                    </div><!--/.col-xs-6.col-lg-4-->
                </c:if>


                <c:forEach var="row" items="${formed}">
                    <div class="col-xs-6 col-lg-4">
                        <p>User: <c:out value="${row.userByOrderId.username}"/></p>
                        <p>Full price: <c:out value="${row.fullPrice}"/></p>
                        <p>Delivery time: <c:out value="${row.deliveryTime}"/></p>
                        <p>Status: <c:out value="${row.status}"/></p>
                        <p>Status info: <c:out value="${row.statusInfo}"/></p>
                        <p>Payment info: <c:out value="${row.paymentInfo}"/></p>
                        <p><a class="btn btn-default" href="${contextPath}/admin/orders/orderUpdate/${row.orderId}" role="button">Update</a></p>

                    </div><!--/.col-xs-6.col-lg-4-->
                </c:forEach>


                <c:if test="${empty delivering}">
                    <div class="col-xs-6 col-lg-4">
                        <h2>No delivering orders</h2>
                    </div><!--/.col-xs-6.col-lg-4-->
                </c:if>


                <c:forEach var="row" items="${delivering}">
                    <div class="col-xs-6 col-lg-4">
                        <p>User: <c:out value="${row.userByOrderId.username}"/></p>
                        <p>Full price: <c:out value="${row.fullPrice}"/></p>
                        <p>Delivery time: <c:out value="${row.deliveryTime}"/></p>
                        <p>Status: <c:out value="${row.status}"/></p>
                        <p>Status info: <c:out value="${row.statusInfo}"/></p>
                        <p>Payment info: <c:out value="${row.paymentInfo}"/></p>
                        <p><a class="btn btn-default" href="${contextPath}/admin/orders/orderUpdate/${row.orderId}" role="button">Update</a></p>

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
