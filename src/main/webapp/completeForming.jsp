
<!--TODO: add new header-->
<%@ include file="profile/header.jsp"%>

<div class="container">

    <div class="jumbotron" style="background-color: #ffffff;">

            <h1 class="form-signin-heading">Forming order</h1>

        <h2>Address</h2>
        <p><b><c:out value="${order.addressByOrderId.cityByAddressId.cityName}" /></b>, <c:out value="${order.addressByOrderId.street}" /> street, building <c:out value="${order.addressByOrderId.buildingNumber}" />, room <c:out value="${order.addressByOrderId.roomNumber}" /><br />
            <c:if test="${not empty order.addressByOrderId.comment}">Comment: <c:out value="${address.comment}" /></c:if></p>
        <p>


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
                    <img src="${element.food.imgPath}" />

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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/addition.js"></script>
<script type="text/javascript">
    function toggle_visibility(id)
    {
        var e = document.getElementById(id);
        if ( e.style.display == 'block' )
            e.style.display = 'none';
        else
            e.style.display = 'block';
    }
</script>
</body>
</html>