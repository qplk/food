<%@include file='header.jsp'%>

<div id="ModalReg" class="modal fade">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-body">
                <form:form method="POST" modelAttribute="userForm" class="form-signin">
                    <h2 class="form-signin-heading">Create your account</h2>
                    <spring:bind path="username">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="text" path="username" class="form-control" placeholder="Username"
                                        autofocus="true"></form:input>
                            <form:errors path="username"></form:errors>
                        </div>
                    </spring:bind>

                    <spring:bind path="password">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                            <form:errors path="password"></form:errors>
                        </div>
                    </spring:bind>

                    <spring:bind path="passwordConfirm">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="password" path="passwordConfirm" class="form-control"
                                        placeholder="Confirm your password"></form:input>
                            <form:errors path="passwordConfirm"></form:errors>
                        </div>
                    </spring:bind>

                    <spring:bind path="email">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="text" path="email" class="form-control" placeholder="Email"
                                        autofocus="true"></form:input>
                            <form:errors path="email"></form:errors>
                        </div>
                    </spring:bind>

                    <spring:bind path="phoneNumber">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="text" path="phoneNumber" class="form-control" placeholder="phone"
                                        autofocus="true"></form:input>
                            <form:errors path="phoneNumber"></form:errors>
                        </div>
                    </spring:bind>

                    <spring:bind path="gender">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:select class="form-control" path="gender">
                                <option value="0">Male</option>
                                <option value="1">Female</option>
                            </form:select>
                        </div>
                    </spring:bind>

                    <spring:bind path="recaptchaResponse">
                        <div class="form-group ${status.error ? 'has-error' : ''}">


                            <div class="g-recaptcha" data-sitekey="<c:out value="${recaptchaSiteKey}" />" data-theme="light"></div>

                            <div id="g-recaptcha"></div>
                            <form:hidden path="recaptchaResponse"/>


                            <form:errors path="recaptchaResponse" element="div" class="alert alert-danger"/>
                        </div>
                    </spring:bind>
                    <button class="btn btn-lg btn-primary btn-block" type="submit" id="grecaptcha">Submit</button>
                </form:form>

                <script src="https://www.google.com/recaptcha/api.js"></script>
            </div>
        </div>
    </div>
</div>



<div class="container">

        <div class="row">

        <div class="col-lg-9 col-sm-9">
          <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
          </p>
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
              <!-- Indicators -->
              <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
              </ol>

              <!-- Wrapper for slides -->
              <div class="carousel-inner" role="listbox">
                <div class="item active">
                  <img src="http://i069.radikal.ru/1703/73/470db34c4705.jpg">
                  <div class="carousel-caption">

                  </div><!--Carousel-caption-->
                </div><!--item active-->
                <div class="item">
                  <img src="http://i069.radikal.ru/1703/73/470db34c4705.jpg">
                  <div class="carousel-caption">

                  </div><!--carousel-caption-->
                </div><!--item-->

              </div><!--carousel-inner-->

              <!-- Controls -->
              <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
              </a>
              <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
              </a>
            </div><!--carousel-slide-->
        </div><!--/.col-xs-12.col-sm-9-->

        <div class="col-lg-3 col-sm-3 sidebar-offcanvas" id="sidebarCategories">
            <button class='btn btn-primary btn-lg btn-block' id="category3" onclick="buildPizza()" value="Pizza" style="background-color: #3c3c3c;">Pizza</button>
            <button class='btn btn-primary btn-lg btn-block' id="category4" onclick="buildRoll()" value="Roll" style="background-color: #3c3c3c;">Roll</button>
            <button class='btn btn-primary btn-lg btn-block' id="category2" onclick="buildSnack()" value="Snack" style="background-color: #3c3c3c;">Snack</button>
            <button class='btn btn-primary btn-lg btn-block' id="category5" onclick="buildDessert()" value="Dessert" style="background-color: #3c3c3c;">Dessert</button>
            <button class='btn btn-primary btn-lg btn-block' id="category1" onclick="buildDrink()" value="Drink" style="background-color: #3c3c3c;">Drink</button>
        </div><!--/.sidebar-categories-->

        </div><!--/.row-->

        <div class="clearfix visible-md-block"></div>

        <div class="row" id="div1">

        </div><!--/row-->
    </div><!--/.container-->

<%@include file='footer.jsp'%>


