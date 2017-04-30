<%@include file='header.jsp'%>

<div class="container">
    <div class="jumbotron" style="background-color: #ffffff;">
                <form:form method="PUT" modelAttribute="addressForm" class="form-signin">
                    <h2 class="form-signin-heading">Add new address</h2>

                    <spring:bind path="cityId">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:select class="form-control" path="cityId">
                                <c:if test="${empty citiesList}">

                                    <option>No city available</option>

                                </c:if>

                                <c:forEach var="row" items="${citiesList}">

                                    <option value="${row.cityId}" <c:if test="${row.cityId == address.cityByAddressId.cityId}">selected</c:if>  ><c:out value="${row.cityName}"/></option>

                                </c:forEach>
                            </form:select>
                            <form:errors path="cityId"></form:errors>
                        </div>
                    </spring:bind>


                    <spring:bind path="street">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="text" path="street" class="form-control" placeholder="Street"
                                        autofocus="true" value="${address.street}"></form:input>
                            <form:errors path="street"></form:errors>
                        </div>
                    </spring:bind>


                    <spring:bind path="buildingNumber">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="text" path="buildingNumber" class="form-control" placeholder="Building number" value="${address.buildingNumber}"></form:input>
                            <form:errors path="buildingNumber"></form:errors>
                        </div>
                    </spring:bind>

                    <spring:bind path="roomNumber">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="text" path="roomNumber" class="form-control" placeholder="Room number"
                                        autofocus="true" value="${address.roomNumber}"></form:input>
                            <form:errors path="roomNumber"></form:errors>
                        </div>
                    </spring:bind>

                    <spring:bind path="comment">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="text" path="comment" class="form-control" placeholder="Comment"
                                        autofocus="true" value="${address.comment}"></form:input>
                            <form:errors path="comment"></form:errors>
                        </div>
                    </spring:bind>


                    <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
                </form:form>

</div>




</div><!--/.container-->

<%@include file='footer.jsp'%>
