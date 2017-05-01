<%@include file='header.jsp'%>

<div class="container">

    <div class="jumbotron" style="background-color: #ffffff;">

                <h2>${pageContext.request.userPrincipal.name}</h2>
                <p><a class="btn btn-default" href="${contextPath}/profile/updatePassword" role="button">Update password</a></p>


                <div class="col-xs-6 col-lg-4">
                <form:form method="PUT" modelAttribute="userForm" class="form-signin">
                    <h3 class="form-signin-heading">Update information</h3>

                    <spring:bind path="gender">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:select class="form-control" path="gender">
                                <option value="0" <c:if test="${user.gender == 0}">selected</c:if>>Male</option>
                                <option value="1" <c:if test="${user.gender == 1}">selected</c:if>>Female</option>
                            </form:select>
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


                    <button class="btn btn-lg btn-primary btn-block" type="submit" style="background-color: #3c3c3c">Submit</button>
                </form:form>

</div>



        </div><!--/.col-xs-12.col-sm-9-->


</div><!--/.container-->

<%@include file='footer.jsp'%>

