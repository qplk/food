<%@include file='/admin/header.jsp'%>
<c:set var="entity" value="Food" />
<%@include file='/admin/nav.jsp'%>


<div class="container">

    <div class="row row-offcanvas row-offcanvas-right">



        <div class="jumbotron" style="background-color: #ffffff; padding-bottom: 0; margin-bottom: 0;"><h2>Food
            <form class="navbar-form navbar-right" action="${contextPath}/admin/food/foodAdd">
                <button class="btn btn-success">Add new item</button>
            </form>
        </h2></div>

        <hr>
        <div class="row">

                <c:if test="${empty foodList}">
                    <div class="col-xs-6 col-lg-4">
                        <h2>Food is null</h2>
                    </div><!--/.col-xs-6.col-lg-4-->
                </c:if>

                    <c:forEach var="row" items="${foodList}">
                        <div class="col-xs-6 col-lg-4">
                            <h2><c:out value="${row.foodName}"/></h2>
                            <c:if test="${row.description != null}">
                            <p><c:out value="${row.description}"/></p>
                            </c:if>
                            <p><c:out value="${row.category}"/></p>
                            <p><img src="${row.imgPath}" /></p>
                            <p>Portion size: <c:out value="${row.portionSize}"/></p>
                            <p>Price: <c:out value="${row.price}"/> &#8381;</p>

                            <p><a class="btn btn-default" href="${contextPath}/admin/food/foodUpdate/${row.foodId}" role="button">Update</a></p>
                            <p> <form:form method="DELETE" action="${contextPath}/admin/food/food/${row.foodId}">
                            <button class="btn btn-default"type="submit">Delete</button>
                        </form:form></p>

                        </div><!--/.col-xs-6.col-lg-4-->
                    </c:forEach>




            </div><!--/row-->
        </div>




    <hr>
</div><!--/.container-->

<%@include file='/admin/footer.jsp'%>

