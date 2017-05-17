<%@include file='/admin/header.jsp'%>
<%@include file='/admin/nav.jsp'%>
<div class="container">

    <div class="row row-offcanvas row-offcanvas-right">

        <div class="col-xs-12 col-sm-9">
            <p class="pull-right visible-xs">
                <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
            </p>
            <div class="jumbotron">
                <h1>Admin page</h1>
                <p>Here you can manage content</p>
            </div>
            <div class="row">
                <div class="col-xs-6 col-lg-4">
                    <h2>Users</h2>
                    <p><b>Capabilities</b>:
                        <ul>
                    <li>Update user information</li>
                    </ul>
                    </p>
                    <p><a class="btn btn-default" href="${contextPath}/admin/users/users" role="button">Begin &raquo;</a></p>
                </div><!--/.col-xs-6.col-lg-4-->
                <div class="col-xs-6 col-lg-4">
                    <h2>Restaurants</h2>
                    <p><b>Capabilities</b>:
                    <ul>
                        <li>Add restaurant</li>
                        <li>Update restaurant information</li>
                        <li>Delete restaurant</li>
                    </ul>
                    </p>
                    <p><a class="btn btn-default" href="${contextPath}/admin/restaurants/restaurants" role="button">Begin &raquo;</a></p>
                </div><!--/.col-xs-6.col-lg-4-->
                <div class="col-xs-6 col-lg-4">
                    <h2>Cities</h2>
                    <p><b>Capabilities</b>:
                    <ul>
                        <li>Add city</li>
                        <li>Update city information</li>
                        <li>Delete city</li>
                    </ul>
                    </p>
                    <p><a class="btn btn-default" href="${contextPath}/admin/cities/cities" role="button">Begin &raquo;</a></p>
                </div><!--/.col-xs-6.col-lg-4-->
                <div class="col-xs-6 col-lg-4">
                    <h2>Food</h2>
                    <p><b>Capabilities</b>:
                    <ul>
                        <li>Add food item</li>
                        <li>Update food item information</li>
                        <li>Delete food item</li>
                    </ul>
                    </p>
                    <p><a class="btn btn-default" href="${contextPath}/admin/food/food" role="button">Begin &raquo;</a></p>
                </div><!--/.col-xs-6.col-lg-4-->
                <div class="col-xs-6 col-lg-4">
                    <h2>Orders</h2>
                    <p><b>Capabilities</b>:
                    <ul>
                        <li>Update order status</li>
                    </ul>
                    </p>
                    <p><a class="btn btn-default" href="${contextPath}/admin/orders/orders" role="button">Begin &raquo;</a></p>
                </div><!--/.col-xs-6.col-lg-4-->
                <div class="col-xs-6 col-lg-4">
                    <h2>Assortment</h2>
                    <p><b>Capabilities</b>:
                    <ul>
                        <li>Add assortment item</li>
                        <li>Update assortment item</li>
                         <li>Delete assortment item</li>
                    </ul>
                    </p>
                    <p><a class="btn btn-default" href="${contextPath}/admin/assortment/assortment" role="button">Begin &raquo;</a></p>
                </div><!--/.col-xs-6.col-lg-4-->

            </div><!--/row-->
        </div><!--/.col-xs-12.col-sm-9-->


    </div><!--/row-->

    <hr>
</div><!--/.container-->

<%@include file='/admin/footer.jsp'%>
