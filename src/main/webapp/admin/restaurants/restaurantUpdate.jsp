<%@include file='/admin/header.jsp'%>
<c:set var="entity" value="Restaurant" />
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

            <form:form method="PUT" modelAttribute="restaurantForm" class="form-signin">
                <h2 class="form-signin-heading">Update restaurant</h2>
                <spring:bind path="street">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="street" class="form-control" placeholder="Street"
                                    autofocus="true" value="${restaurant.street}"></form:input>
                        <form:errors path="street"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="buildingNumber">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="buildingNumber" class="form-control" placeholder="Building Number" value="${restaurant.buildingNumber}"></form:input>
                        <form:errors path="buildingNumber"></form:errors>
                    </div>

                </spring:bind>

                <spring:bind path="restaurantPhone">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="restaurantPhone" class="form-control" placeholder="Restaurant Phone"
                                    autofocus="true" value="${restaurant.restaurantPhone}"></form:input>
                        <form:errors path="restaurantPhone"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="cityId">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:select class="form-control" path="cityId">
                            <c:if test="${empty citiesList}">

                                <option>No city available</option>

                            </c:if>

                            <c:forEach var="row" items="${citiesList}">

                                <option value="${row.cityId}" <c:if test="${row.cityId == restaurant.cityByRestaurantId.cityId}">selected</c:if> > <c:out value="${row.cityName}"/></option>

                            </c:forEach>
                        </form:select>
                        <form:errors path="cityId"></form:errors>
                    </div>
                </spring:bind>


                <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
            </form:form>

        </div><!--/.col-xs-12.col-sm-9-->


    </div><!--/row-->

    <hr>
</div><!--/.container-->

<%@include file='/admin/footer.jsp'%>
