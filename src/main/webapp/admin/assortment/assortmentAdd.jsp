<%@include file='/admin/header.jsp'%>
<c:set var="entity" value="Assortment" />
<%@include file='/admin/nav.jsp'%>

<div class="container">


    <div class="row row-offcanvas row-offcanvas-right">
        <div class="jumbotron" style="background-color: #ffffff; padding-bottom: 0; margin-bottom: 0;"><h2>Assortment</h2></div>

        <hr>
        <div class="col-xs-6 col-lg-4">

            <h3>Restaurant</h3>
            <h4><c:out value="${restaurant.street}"/>, <c:out value="${restaurant.buildingNumber}"/></h4>
            <p><c:out value="${restaurant.cityByRestaurantId.cityName}"/></p>
            <p><c:out value="${restaurant.restaurantPhone}"/></p>




            <form:form method="POST" modelAttribute="assortmentForm" class="form-signin">
                <h2 class="form-signin-heading">Add assortment</h2>

                <spring:bind path="foodId">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:select class="form-control" path="foodId">
                            <c:if test="${empty foodList}">

                                <option>No food available</option>

                            </c:if>

                            <c:forEach var="row" items="${foodList}">

                                <option value="${row.foodId}"><c:out value="${row.foodName}"/></option>

                            </c:forEach>
                        </form:select>
                        <form:errors path="foodId"></form:errors>
                    </div>
                </spring:bind>



                <spring:bind path="enable">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:select class="form-control" path="enable">

                            <option value="0">Disable</option>
                            <option value="1">Enable</option>

                        </form:select>
                    </div>
                </spring:bind>

                <spring:bind path="quantity">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="quantity" class="form-control" placeholder="Quantity"
                                    autofocus="true"></form:input>
                        <form:errors path="quantity"></form:errors>
                    </div>
                </spring:bind>




                <button class="btn btn-lg btn-primary btn-block" type="submit" style="background-color: #3c3c3c">Submit</button>
            </form:form>

        </div><!--/.col-xs-12.col-sm-9-->


    </div><!--/row-->

    <hr>
</div><!--/.container-->

<%@include file='/admin/footer.jsp'%>
