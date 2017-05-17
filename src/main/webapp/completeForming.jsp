<%@ include file="header.jsp"%>

<div class="container">

    <div class="jumbotron" style="background-color: #ffffff;">

            <h1 class="form-signin-heading">Forming order</h1>

        <h2>Address</h2>
        <p><b><c:out value="${order.addressByOrderId.cityByAddressId.cityName}" /></b>, <c:out value="${order.addressByOrderId.street}" /> street, building <c:out value="${order.addressByOrderId.buildingNumber}" />, room <c:out value="${order.addressByOrderId.roomNumber}" /><br />
            <c:if test="${not empty order.addressByOrderId.comment}">Comment: <c:out value="${order.addressByOrderId.comment}" /></c:if></p>
        <p>
        <p><a href="${contextPath}/addOrderAddress">Change address</a></p>

        <c:if test="${not empty addressError}"><div class="alert alert-danger" ><c:out value="${addressError}" /></div></c:if>



        <form:form action="${contextPath}/completeForming" modelAttribute="orderForm" method="POST">


        <table class="table table-bordered">

            <tr >

                    <th>Food</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Cost</th>

            </tr>


            <c:set var="i" value="0" />

            <c:forEach var="element" items="${order.orderElements}">
            <tr>
                <td>
                    <b><c:out value="${element.food.foodName}"/></b>

                    <p><img src="${element.food.imgPath}" width="100" /></p>

                    <spring:bind path="orderElements[${i}].orderElementId">
                        <div class="form-group ${status.error ? 'has-error' : ''}" style="color: #aa0000;">
                            <form:errors path="orderElements[${i}].orderElementId"></form:errors>
                        </div>
                    </spring:bind>

                </td>
                <td>
                    <c:out value="${element.food.price}"/> &#8381;
                </td>
                <td>
                    <c:out value="${element.quantity}"/> item<c:if test="${element.quantity > 1}">s</c:if>
                    <spring:bind path="orderElements[${i}].quantity">
                        <div class="form-group ${status.error ? 'has-error' : ''}" style="color: #aa0000;">
                            <form:errors path="orderElements[${i}].quantity"></form:errors>
                        </div>
                    </spring:bind>

                </td>
                <td>
                    <c:out value="${element.elementPrice}"/> &#8381;
                </td>

            </tr>

                <c:set var="i" value="${i + 1}" />

            </c:forEach>
            <tr>
                <td colspan="3" align="right">
                <b>Full cost:</b>
                </td>
                <td><c:out value="${order.fullPrice }"/> &#8381;</td>
            </tr>

</table>





                <spring:bind path="fullPrice">
                    <div class="form-group ${status.error ? 'has-error' : ''}" style="color: #aa0000;">
                        <form:errors path="fullPrice"></form:errors>
                    </div>
                </spring:bind>



        <button class="btn btn-lg btn-primary btn-block" type="submit" style="background-color: #3c3c3c; width: 200pt;">Complete forming</button>
    </form:form>




    </div><!--/.row-->

</div><!--/.container-->

<%@include file='footer.jsp'%>