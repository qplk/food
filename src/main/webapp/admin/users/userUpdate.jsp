<%@include file='/admin/header.jsp'%>
<c:set var="entity" value="User" />
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


            <form:form method="PUT" modelAttribute="userForm" class="form-signin">
                <h2 class="form-signin-heading">Update user</h2>

                <spring:bind path="username">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="username" class="form-control" placeholder="Username"
                                    autofocus="true" value="${user.username}"></form:input>
                        <form:errors path="username"></form:errors>
                    </div>
                </spring:bind>


                <spring:bind path="email">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="email" class="form-control" placeholder="Email"
                                    autofocus="true" value="${user.email}"></form:input>
                        <form:errors path="email"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="phoneNumber">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="phoneNumber" class="form-control" placeholder="Phone number"
                                    autofocus="true" value="${user.phoneNumber}"></form:input>
                        <form:errors path="phoneNumber"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="gender">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:select class="form-control" path="gender">
                            <option value="0" <c:if test="${user.gender == 0}">selected</c:if>>Male</option>
                            <option value="1" <c:if test="${user.gender == 1}">selected</c:if>>Female</option>
                        </form:select>
                    </div>
                </spring:bind>

                <spring:bind path="information">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="information" class="form-control" placeholder="Information"
                                    autofocus="true" value="${user.information}"></form:input>
                        <form:errors path="information"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="roles">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <c:if test="${empty roles}">

                            <option>No roles exist</option>

                        </c:if>
                        <c:if test="${not empty roles}">
                            <p>Roles:
                            <c:forEach var="hasRole" items="${user.roles}">
                               <c:out value="${hasRole.name} " /><br />
                            </c:forEach>
                            </p>

                            <c:forEach var="role" items="${roles}">
                                <form:checkbox path="roles" value="${role}" /> ${role.name}
                            </c:forEach>
                        </c:if>

                        <form:errors path="roles"></form:errors>
                    </div>
                </spring:bind>


                <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
            </form:form>

        </div><!--/.col-xs-12.col-sm-9-->

    </div><!--/row-->

    <hr>
</div><!--/.container-->

<%@include file='/admin/footer.jsp'%>
