<%@include file='/admin/header.jsp'%>
<c:set var="entity" value="City" />
<%@include file='/admin/nav.jsp'%>


<div class="container">

    <div class="row row-offcanvas row-offcanvas-right">
        <div class="jumbotron" style="background-color: #ffffff; padding-bottom: 0; margin-bottom: 0;"><h2>Cities</h2></div>

        <hr>
        <div class="col-xs-6 col-lg-4">

            <form:form method="POST" modelAttribute="cityForm" class="form-signin">
                <h2 class="form-signin-heading">Add new city</h2>
                <spring:bind path="cityName">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="cityName" class="form-control" placeholder="City name"
                                    autofocus="true"></form:input>
                        <form:errors path="cityName"></form:errors>
                    </div>
                </spring:bind>


                <spring:bind path="deliveryPhone">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="deliveryPhone" class="form-control" placeholder="Delivery Phone"></form:input>
                        <form:errors path="deliveryPhone"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="openTime">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="openTime" class="form-control" placeholder="Open Time"
                                    autofocus="true"></form:input>
                        <form:errors path="openTime"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="closeTime">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="closeTime" class="form-control" placeholder="Close Time"
                                    autofocus="true"></form:input>
                        <form:errors path="closeTime"></form:errors>
                    </div>
                </spring:bind>


                <spring:bind path="deliveryTime">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="deliveryTime" class="form-control" placeholder="Delivery Time"
                                    autofocus="true"></form:input>
                        <form:errors path="deliveryTime"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="minPrice">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="minPrice" class="form-control" placeholder="Min Price"
                                    autofocus="true"></form:input>
                        <form:errors path="minPrice"></form:errors>
                    </div>
                </spring:bind>

                <button class="btn btn-lg btn-primary btn-block" type="submit" style="background-color: #3c3c3c">Submit</button>
            </form:form>

        </div><!--/.col-xs-12.col-sm-9-->

    </div><!--/row-->

    <hr>
</div><!--/.container-->

<%@include file='/admin/footer.jsp'%>

