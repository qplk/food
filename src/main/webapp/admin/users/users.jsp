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
            <div class="row">

                <c:if test="${empty usersList}">
                    <div class="col-xs-6 col-lg-4">
                        <h2>No users</h2>
                    </div><!--/.col-xs-6.col-lg-4-->
                </c:if>

                <c:forEach var="user" items="${usersList}">
                    <div class="col-xs-6 col-lg-4">
                        <h2><c:out value="${user.username}"/></h2>
                        <p>Gender:
                        <c:if test="${user.gender == 1}">

                                Female

                        </c:if>
                        <c:if test="${user.gender == 0}">

                                Male

                        </c:if>
                        </p>
                        <p>Email: <c:out value="${user.email}"/></p>
                        <p>Phone number: <c:out value="${user.phoneNumber}"/></p>
                        <p>Roles:
                    <c:forEach var="role" items="${user.roles}">
                        <c:out value="${role.name}" /><br />
                        </c:forEach></p>

                        <p>Information: ${user.information}</p>
                        <p><a class="btn btn-default" href="${contextPath}/admin/users/userUpdate/${user.userId}" role="button">Update</a></p>

                    </div><!--/.col-xs-6.col-lg-4-->
                </c:forEach>




            </div><!--/row-->
        </div><!--/.col-xs-12.col-sm-9-->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
            <div class="list-group">
                <a href="#" class="list-group-item active">Food</a>
                <!--<a href=".jsp" class="list-group-item">Add new item</a>-->
                
            </div>
        </div><!--/.sidebar-offcanvas-->
    </div><!--/row-->

    <hr>
</div><!--/.container-->

<%@include file='/admin/footer.jsp'%>
