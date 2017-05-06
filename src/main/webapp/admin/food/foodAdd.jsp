<%@include file='/admin/header.jsp'%>
<c:set var="entity" value="Food" />
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


            <form:form method="POST" modelAttribute="foodForm" class="form-signin">
                <h2 class="form-signin-heading">Add new food item</h2>
                <spring:bind path="foodName">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="foodName" class="form-control" placeholder="Food name"
                                    autofocus="true"></form:input>
                        <form:errors path="foodName"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="description">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="description" class="form-control" placeholder="Description"></form:input>
                        <form:errors path="description"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="portionSize">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="portionSize" class="form-control" placeholder="Portion size"
                                    autofocus="true"></form:input>
                        <form:errors path="portionSize"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="category">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:select class="form-control" path="category">
                            <option>Drink</option>
                            <option>
                            <option>Snack</option>
                        </form:select>
                    </div>
                </spring:bind>

                <spring:bind path="price">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="price" class="form-control" placeholder="Price"
                                    autofocus="true"></form:input>
                        <form:errors path="price"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="imgPath">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="imgPath" class="form-control" placeholder="Image path"
                                    autofocus="true"></form:input>
                        <form:errors path="imgPath"></form:errors>
                    </div>
                </spring:bind>

                <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
            </form:form>

        </div><!--/.col-xs-12.col-sm-9-->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
            <div class="list-group">
                <a href="${contextPath}/admin/food/foodAdd" class="list-group-item active">Add</a>
                <a href="#" class="list-group-item">Link</a>
                <a href="#" class="list-group-item">Link</a>
            </div>
        </div><!--/.sidebar-offcanvas-->
    </div><!--/row-->

    <hr>
</div><!--/.container-->

<%@include file='/admin/footer.jsp'%>

