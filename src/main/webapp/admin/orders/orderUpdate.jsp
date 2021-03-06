<%@include file='/admin/header.jsp'%>
<c:set var="entity" value="Order" />
<%@include file='/admin/nav.jsp'%>

<div class="container">

    <div class="row row-offcanvas row-offcanvas-right">
        <div class="jumbotron" style="background-color: #ffffff; padding-bottom: 0; margin-bottom: 0;"><h2>Orders</h2></div>

        <hr>
        <div class="col-xs-6 col-lg-4">


            <p>User: <c:out value="${order.userByOrderId.username}"/></p>
            <p>Full price: <c:out value="${order.fullPrice}"/> &#8381;</p>
            <p>Delivery time: <c:out value="${order.deliveryTime}"/></p>
            <p>Payment info: <c:out value="${order.paymentInfo}"/></p>

            <form:form method="PUT" modelAttribute="orderForm" class="form-signin">
                <h2 class="form-signin-heading">Update order item</h2>


                <spring:bind path="status">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:select class="form-control" path="status">
                            <option <c:if test="${order.status == 'Formed'}">selected</c:if>>Formed</option>
                            <option <c:if test="${order.status == 'Delivering'}">selected</c:if> >Delivering</option>
                            <option <c:if test="${order.status == 'Delivered'}">selected</c:if>>Delivered</option>
                            <option <c:if test="${order.status == 'Cancelled'}">selected</c:if>>Cancelled</option>
                        </form:select>
                    </div>
                </spring:bind>

                <spring:bind path="statusInfo">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="statusInfo" class="form-control" placeholder="Status info"
                                    autofocus="true" value="${order.statusInfo}"></form:input>
                        <form:errors path="statusInfo"></form:errors>
                    </div>
                </spring:bind>

                <button class="btn btn-lg btn-primary btn-block" type="submit"  style="background-color: #3c3c3c">Submit</button>
            </form:form>
        </div><!--/.col-xs-12.col-sm-9-->


    </div><!--/row-->

    <hr>
</div><!--/.container-->

<%@include file='/admin/footer.jsp'%>
