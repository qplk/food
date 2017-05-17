<%@include file='/admin/header.jsp'%>
<c:set var="entity" value="Order" />
<%@include file='/admin/nav.jsp'%>

<div class="container">

    <div class="row row-offcanvas row-offcanvas-right">



        <div class="jumbotron" style="background-color: #ffffff; padding-bottom: 0; margin-bottom: 0;"><h2>Orders
        </h2></div>

        <hr>
        <div class="row">

                <c:if test="${empty formed}">

                        <h2>No formed orders</h2>

                </c:if>
            <c:if test="${not empty formed}">

                    <h2>Formed orders</h2>

            </c:if>


                <c:forEach var="row" items="${formed}">
                    <div class="col-xs-6 col-lg-4">
                        <p>User: <c:out value="${row.userByOrderId.username}"/></p>
                        <p>Full price: <c:out value="${row.fullPrice}"/>  &#8381;</p>
                        <p>Delivery time: <fmt:formatDate value="${row.deliveryTime}" pattern="dd/MM H:mm (zzzz)" /></p>
                        <p>Status: <c:out value="${row.status}"/></p>
                        <p>Status info: <c:out value="${row.statusInfo}"/></p>
                        <p>Payment info: <c:out value="${row.paymentInfo}"/></p>
                        <p><a class="btn btn-default" href="${contextPath}/admin/orders/orderUpdate/${row.orderId}" role="button">Update</a></p>
                    </div>
                </c:forEach>
            </div>
        <div class="row">

                <c:if test="${empty delivering}">

                        <h2>No delivering orders</h2>

                </c:if>
            <c:if test="${not empty delivering}">

                <h2>Delivering orders</h2>

            </c:if>



            <c:forEach var="row" items="${delivering}">

                <div class="col-xs-6 col-lg-4">
                        <p>User: <c:out value="${row.userByOrderId.username}"/></p>
                        <p>Full price: <c:out value="${row.fullPrice}"/> &#8381;</p>
                        <p>Delivery time: <fmt:formatDate value="${row.deliveryTime}" pattern="dd/MM H:mm (zzzz)" /></p>
                        <p>Status: <c:out value="${row.status}"/></p>
                        <p>Status info: <c:out value="${row.statusInfo}"/></p>
                        <p>Payment info: <c:out value="${row.paymentInfo}"/></p>
                        <p><a class="btn btn-default" href="${contextPath}/admin/orders/orderUpdate/${row.orderId}" role="button">Update</a></p>

                </div>
                </c:forEach>




            </div><!--/row-->
        </div><!--/.col-xs-12.col-sm-9-->



    <hr>
</div><!--/.container-->

<%@include file='/admin/footer.jsp'%>
