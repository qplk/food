<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
<body onLoad="welcomeCity()">
<div class="hidden">
    <input type="text" id="city" value="1"/>
    <input type="text" id="userId" value="0">
    <input type="text" id="orderId" value="0">
</div>

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

<div id="ModalBuy" class="modal fade">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <h4>Confirming order</h4>
            </div>
            <div class="modal-body" id="modalBody">
                <table class="table table-bordered" id="modalBodyTable">

                </table>
            </div>
            <div class="modal-footer" id="modalFooter">
                <div id="totalPrice"></div>
                <a href="${contextPath}/addOrderAddress" onclick="completeForming"><span class='glyphicon glyphicon-shopping-cart'>Continue</span></a>
            </div>
        </div>
    </div>
</div>



<div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/welcome">Food zone</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="#contact">Contact</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="aCity"><input type="text">Cities<b class="caret"></b></a>
                    <ul id="cityList" class="dropdown-menu">
                    </ul>
                </li>
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
                <p class="navbar-text navbar-right">Signed in as <a href="${contextPath}/profile/profile" class="navbar-link">${pageContext.request.userPrincipal.name}</a></p>
            </c:if>

            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <form class="navbar-form navbar-right" id="logoutForm" method="POST" action="${contextPath}/logout">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <a onclick="document.forms['logoutForm'].submit()"><button class="btn btn-success">logout</button></a>
                </form>
            </c:if>

            <div class="navbar-form navbar-right">
                <a href="#ModalBuy" class="btn btn-default" data-toggle="modal" onclick="getOrder()"><span class="glyphicon glyphicon-shopping-cart"></span></a>
            </div>


        </div><!-- /.nav-collapse -->
    </div><!-- /.container -->
</div><!-- /.navbar -->