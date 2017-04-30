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


            <h2>Restaurant</h2>
            <h2><c:out value="${restaurant.street}"/>, <c:out value="${restaurant.buildingNumber}"/></h2>
            <p><c:out value="${restaurant.restaurantPhone}"/></p>
            <p><c:out value="${restaurant.cityByRestaurantId.cityName}"/></p>



            <form:form method="PUT" modelAttribute="assortmentForm" class="form-signin">
                <h2 class="form-signin-heading">Update assortment</h2>

              <p><c:out value="${food.foodName}"/></p>


                <spring:bind path="enable">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:select class="form-control" path="enable">

                            <option value="0" <c:if test="${false == assortment.enable}">
                                selected

                            </c:if>>Disable</option>
                            <option value="1" <c:if test="${true == assortment.enable}">
                            selected

                        </c:if>>Enable</option>

                        </form:select>
                    </div>
                </spring:bind>

                <spring:bind path="quantity">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="quantity" class="form-control" placeholder="Quantity"
                                    autofocus="true" value="${assortment.quantity}"></form:input>
                        <form:errors path="quantity"></form:errors>
                    </div>
                </spring:bind>




                <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
            </form:form>

        </div><!--/.col-xs-12.col-sm-9-->


    </div><!--/row-->

    <hr>
</div><!--/.container-->

<%@include file='/admin/footer.jsp'%>
