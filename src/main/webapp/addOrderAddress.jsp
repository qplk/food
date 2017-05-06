<%@include file='profile/header.jsp'%>
<!--TODO: add new header-->

<div class="container">

    <div class="jumbotron" style="background-color: #ffffff;">
        <div class="col-xs-6 col-lg-4">
            <h1 class="form-signin-heading">Form order</h1>


            <form:form method="POST" modelAttribute="addressForm" class="form-signin">
                <h2 class="form-signin-heading">Select address</h2>

                <spring:bind path="addressId">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:select class="form-control" path="addressId">
                            <c:if test="${empty addressList}">

                                <option value="0">No address available</option>

                            </c:if>

                            <c:forEach var="row" items="${addressList}">

                                <option value="${row.addressId}" <c:if test="${addressId == row.addressId}">selected</c:if> ><c:out value="${row.street}, ${row.buildingNumber}"/></option>

                            </c:forEach>
                        </form:select>
                        <form:errors path="addressId"></form:errors>
                    </div>
                </spring:bind>

                <p>
                    <a onclick="toggle_visibility(42)" style="color: #3c3c3c">New address</a></p>


                <div id="42" hidden="true">

                    <spring:bind path="cityId">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:select class="form-control" path="cityId">
                                <c:if test="${empty citiesList}">

                                    <option>No city available</option>

                                </c:if>

                                <c:forEach var="row" items="${citiesList}">

                                    <option value="${row.cityId}"><c:out value="${row.cityName}"/></option>

                                </c:forEach>
                            </form:select>
                            <form:errors path="cityId"></form:errors>
                        </div>
                    </spring:bind>


                    <spring:bind path="street">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="text" path="street" class="form-control" placeholder="Street"
                                        autofocus="true"></form:input>
                            <form:errors path="street"></form:errors>
                        </div>
                    </spring:bind>


                    <spring:bind path="buildingNumber">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="text" path="buildingNumber" class="form-control" placeholder="Building number"></form:input>
                            <form:errors path="buildingNumber"></form:errors>
                        </div>
                    </spring:bind>

                    <spring:bind path="roomNumber">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="text" path="roomNumber" class="form-control" placeholder="Room number"
                                        autofocus="true"></form:input>
                            <form:errors path="roomNumber"></form:errors>
                        </div>
                    </spring:bind>

                    <spring:bind path="comment">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="text" path="comment" class="form-control" placeholder="Comment"
                                        autofocus="true"></form:input>
                            <form:errors path="comment"></form:errors>
                        </div>
                    </spring:bind>

                    <p> <a onclick="toggle_visibility(42)" style="color: #3c3c3c">Hide</a></p>

                </div>


                <button class="btn btn-lg btn-primary btn-block" type="submit" style="background-color: #3c3c3c;">Form order</button>

            </form:form>

        </div>

    </div><!--/.row-->

</div><!--/.container-->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/addition.js"></script>
<script type="text/javascript">
    function toggle_visibility(id)
    {
        var e = document.getElementById(id);
        if ( e.style.display == 'block' )
            e.style.display = 'none';
        else
            e.style.display = 'block';
    }
</script>
</body>
</html>