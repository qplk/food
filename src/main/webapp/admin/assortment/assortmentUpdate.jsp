<%@include file='/admin/header.jsp'%>
<c:set var="entity" value="Assortment" />
<%@include file='/admin/nav.jsp'%>

<div class="container">


    <div class="row row-offcanvas row-offcanvas-right">
        <div class="jumbotron" style="background-color: #ffffff; padding-bottom: 0; margin-bottom: 0;"><h2>Assortment</h2></div>

        <hr>
        <div class="col-xs-6 col-lg-4">
            <h3>Restaurant</h3>
            <h3><c:out value="${restaurant.street}"/>, <c:out value="${restaurant.buildingNumber}"/></h3>
            <p><c:out value="${restaurant.cityByRestaurantId.cityName}"/></p>
            <p><c:out value="${restaurant.restaurantPhone}"/></p>




            <form:form method="PUT" modelAttribute="assortmentForm" class="form-signin">
                <h3 class="form-signin-heading">Update assortment</h3>

              <p>Food name: <b><c:out value="${food.foodName}"/></b></p>


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




                <button class="btn btn-lg btn-primary btn-block" type="submit" style="background-color: #3c3c3c">Submit</button>
            </form:form>

        </div><!--/.col-xs-12.col-sm-9-->


    </div><!--/row-->

    <hr>
</div><!--/.container-->

<%@include file='/admin/footer.jsp'%>
