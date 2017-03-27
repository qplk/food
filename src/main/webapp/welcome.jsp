<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/addition.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<nav class="navbar navbar-fixed-top navbar-inverse">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Food zone</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#about">About</a></li>
            <li><a href="#contact">Contact</a></li>
          </ul>

          <c:if test="${pageContext.request.userPrincipal.name == null}">
            <form class="navbar-form navbar-right" action="${contextPath}/login">
                <a href="#ModalSign" class="btn btn-success" data-toggle="modal">Sign in</a>
            </form>
          </c:if>

          <c:if test="${pageContext.request.userPrincipal.name == null}">
            <form class="navbar-form navbar-right" action="${contextPath}/registration">
                <a href="#ModalReg" class="btn btn-success" data-toggle="modal">Registration</a>
            </form>
          </c:if>

          <c:if test="${pageContext.request.userPrincipal.name != null}">
            <p class="navbar-text navbar-right">Signed in as <a href="profile.jsp" class="navbar-link">${pageContext.request.userPrincipal.name}</a></p>
          </c:if>

          <c:if test="${pageContext.request.userPrincipal.name != null}">
              <form class="navbar-form navbar-right" id="logoutForm" method="POST" action="${contextPath}/logout">
                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                  <a onclick="document.forms['logoutForm'].submit()"><button class="btn btn-success">logout</button></a>
              </form>
          </c:if>


        </div><!-- /.nav-collapse -->
      </div><!-- /.container -->
    </nav><!-- /.navbar -->

    <div id="ModalSign" class="modal fade">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-body">
                    <form method="POST" action="${contextPath}/welcome/login" class="form-signin">
                        <h2 class="text-center">Log in</h2>
                            <div class="form-group ${error != null ? 'has-error' : ''}">
                               <p class="fieldset">
                                <span>${message}</span>
                                <input name="username" type="text" class="form-control" placeholder="Username"
                                       autofocus="true"/>
                               </p>
                               <p class="fieldset">
                                <input name="password" type="password" class="form-control" placeholder="Password"/>
                                <span>${error}</span>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                               </p>

                                <p>
                                 <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
                                </p>
                            </div>
                        </form>
                </div>
            </div>
        </div>
    </div>

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

                                 <spring:bind path="gender">
                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                <form:select class="form-control" path="gender">
                                                      <option>Male</option>
                                                      <option>Female</option>
                                                </form:select>
                                            </div>
                                 </spring:bind>

                                <div id="recaptcha" class="g-recaptcha" data-sitekey="6LesJhgUAAAAAFLSWEr_gKjZEBy3riMNfU3M7CNR"></div>
                                <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button></div>
                            </form:form>
                            <script src="https://www.google.com/recaptcha/api.js"></script>
                        </div>
                </div>
            </div>
    </div>

    <div class="container">

      <div class="row row-offcanvas row-offcanvas-right">

        <div class="col-xs-12 col-sm-9">
          <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
          </p>
          <div class="jumbotron">
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

                  </div>
                </div>
                <div class="item">
                  <img src="http://i069.radikal.ru/1703/73/470db34c4705.jpg">
                  <div class="carousel-caption">

                  </div>
                </div>

              </div>

              <!-- Controls -->
              <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
              </a>
              <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
              </a>
            </div>
          </div>
          <div id="div1" class="row">
            <div class='col-xs-6 col-lg-6'>
              <img src="https://dodopizzaru-a.akamaihd.net/Img/Products/Pizza/c581dcc8-15e7-4a0b-aea2-45bae256c4ec.jpg">
              <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>

              <div class="input-group">
                <div class="input-group-btn">
                 <button type="button" id="plus" class="btn btn-default" onclick="add()"><span class="glyphicon glyphicon-plus"></span></button>
                 <button type="button" id="minus" class="btn btn-default" onclick="remove()"><span class="glyphicon glyphicon-minus"></span></button>
                </div>
                <div class="col-xs-3">
                 <input type="text" value="0" id="res1" class="form-control" disabled>
                </div>
              </div>
            </div><!--/.col-xs-6.col-lg-6-->
            <div class="col-xs-6 col-lg-6">
              <h2>Heading</h2>
              <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>

            </div><!--/.col-xs-6.col-lg-6-->
            <div class="col-xs-6 col-lg-6">
              <h2>Heading</h2>
              <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
              <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
            </div><!--/.col-xs-6.col-lg-6-->
            <div class="col-xs-6 col-lg-6">
              <h2>Heading</h2>
              <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
              <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
            </div><!--/.col-xs-6.col-lg-6-->
          </div><!--/row-->
        </div><!--/.col-xs-12.col-sm-9-->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
          <div class="list-group">
            <a href="#" class="list-group-item active">Sushi</a>
            <a href="#" class="list-group-item">Rolls</a>
            <a href="#" class="list-group-item">Soup</a>
            <a href="#" class="list-group-item">Thai</a>
            <a href="#" class="list-group-item">Salads</a>
            <a href="#" class="list-group-item">Pizza</a>
            <a href="#" class="list-group-item">Sets</a>
            <a href="#" class="list-group-item">Drinks</a>
            <input type="text" id="divNum" class="form-control">
            <button type="button" id="build" class="btn btn-default" onclick="del"></button>
          </div>
        </div><!--/.sidebar-offcanvas-->
      </div><!--/row-->

      <hr>
    </div><!--/.container-->

<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/addition.js"></script>
</body>
</html>
