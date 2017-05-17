<%@include file='/admin/header.jsp'%>
<c:set var="entity" value="Assortment" />
<%@include file='/admin/nav.jsp'%>

<div class="container">

    <div class="row row-offcanvas row-offcanvas-right">



        <div class="jumbotron" style="background-color: #ffffff; padding-bottom: 0; margin-bottom: 0;"><h2>Assortment
        </h2></div>

        <hr>
        <div class="row">

                <c:if test="${empty restaurantList}">
                    <div class="col-xs-6 col-lg-4">
                        <h3>No restaurants</h3>
                    </div><!--/.col-xs-6.col-lg-4-->
                </c:if>

                <c:forEach var="row" items="${restaurantList}">
                    <div class="col-xs-6 col-lg-4">
                        <h3><c:out value="${row.street}"/>, <c:out value="${row.buildingNumber}"/></h3>
                        <p><c:out value="${row.cityByRestaurantId.cityName}"/></p>
                        <p><c:out value="${row.restaurantPhone}"/></p>

                        <c:if test="${empty row.assortment}">
                            <h3>No assortment</h3>
                        </c:if>
                        <c:if test="${not empty row.assortment}">
                            <h3>Assortment</h3>
                        </c:if>
                    <c:forEach var="assortment" items="${row.assortment}">


                             <form:form method="DELETE" action="${contextPath}/admin/assortment/assortment/${assortment.assortmentId}">
                                 <p><c:out value="${assortment.food.foodName}"/><c:if test="${assortment.quantity != null}">: <c:out value="${assortment.quantity}"/> items
                             </c:if> <c:if test="${assortment.enable == false}">
                                 <span style="color:#880000;"> <b>Disabled</b></span>
                             </c:if>
                                 <a class="btn btn-default" href="${contextPath}/admin/assortment/assortmentUpdate/${row.restaurantId}/${assortment.food.foodId}" role="button">Update</a>

                                 <button class="btn btn-default" type="submit">Delete</button>
                    </form:form></p>

                    </c:forEach>

                        <p><a class="btn btn-default" href="${contextPath}/admin/assortment/assortmentAdd/${row.restaurantId}" role="button">Add item >></a></p>


                    </div><!--/.col-xs-6.col-lg-4-->
                </c:forEach>




            </div><!--/row-->
        </div><!--/.col-xs-12.col-sm-9-->


    <hr>
</div><!--/.container-->

<%@include file='/admin/footer.jsp'%>

