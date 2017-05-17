<%@include file='/admin/header.jsp'%>
<c:set var="entity" value="Food" />
<%@include file='/admin/nav.jsp'%>


<div class="container">

    <div class="row row-offcanvas row-offcanvas-right">
        <div class="jumbotron" style="background-color: #ffffff; padding-bottom: 0; margin-bottom: 0;"><h2>Food</h2></div>

        <hr>
        <div class="col-xs-6 col-lg-4">
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
                            <option>Pizza</option>
                            <option>Roll</option>
                            <option>Dessert</option>
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

                <button class="btn btn-lg btn-primary btn-block" type="submit" style="background-color: #3c3c3c">Submit</button>
            </form:form>

        </div><!--/.col-xs-12.col-sm-9-->


    </div><!--/row-->

    <hr>
</div><!--/.container-->

<%@include file='/admin/footer.jsp'%>

