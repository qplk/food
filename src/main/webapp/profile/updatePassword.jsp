<%@include file='header.jsp'%>

<div class="container">

    <div class="jumbotron" style="background-color: #ffffff;">
                <h2>${pageContext.request.userPrincipal.name}</h2>
        <div class="col-xs-6 col-lg-4">
                <form:form method="PUT" modelAttribute="passwordForm" class="form-signin">
                    <h3 class="form-signin-heading">Update password</h3>

                    <spring:bind path="password">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                            <form:errors path="password"></form:errors>
                        </div>
                    </spring:bind>

                    <spring:bind path="newPassword">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="password" path="newPassword" class="form-control" placeholder="New password"></form:input>
                            <form:errors path="newPassword"></form:errors>
                        </div>
                    </spring:bind>

                    <spring:bind path="newPasswordConfirm">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="password" path="newPasswordConfirm" class="form-control"
                                        placeholder="Confirm new password"></form:input>
                            <form:errors path="newPasswordConfirm"></form:errors>
                        </div>
                    </spring:bind>


                    <button class="btn btn-lg btn-primary btn-block" type="submit" style="background-color: #3c3c3c">Submit</button>
                </form:form>


</div>



                </div>

</div><!--/.container-->

<%@include file='footer.jsp'%>

