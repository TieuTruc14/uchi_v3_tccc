<%--
  Created by IntelliJ IDEA.
  User: TienManh
  Date: 8/7/2017
  Time: 2:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="app">
<head>
    <meta charset="utf-8"/>
    <title>Uchi</title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/static/image/osp.ico" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/note/css/bootstrap.css" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/note/css/animate.css" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/note/css/font-awesome.min.css" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/note/css/font.css" type="text/css" cache="false"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/note/js/datatables/datatables.css" type="text/css"/>
    <%--<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/contract/uchi.css" />--%>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/note/css/app.css" type="text/css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/note/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/note/css/basic.css">
    <!--[if lt IE 9]>
    <script src="<%=request.getContextPath()%>/static/note/js/ie/html5shiv.js" cache="false"></script>
    <script src="<%=request.getContextPath()%>/static/note/js/ie/respond.min.js" cache="false"></script>
    <script src="<%=request.getContextPath()%>/static/note/js/ie/excanvas.js" cache="false"></script>
    <![endif]-->

    <script src="<%=request.getContextPath()%>/static/note/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/note/js/bootstrap.js"></script>
    <script src="<%=request.getContextPath()%>/static/note/js/app.js"></script>
    <script src="<%=request.getContextPath()%>/static/note/js/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/note/js/datatables/jquery.dataTables.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/note/js/app.plugin.js"></script>
    <script src="<%=request.getContextPath()%>/static/note/js/jquery.spin.js"></script>

    <script type="text/javascript" src="<%=request.getContextPath()%>/static/plugin/mark/markNew.js" charset="UTF-8"></script>
    <%--<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/select2.min.css" />--%>
    <%--<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/select2.min.js"></script>--%>
    <%--<link href="/assets/bootstrap-select/bootstrap-select.min.css" rel="stylesheet">--%>
    <%--<script src="/assets/bootstrap-select/bootstrap-select.min.js"></script>--%>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/js/select2/select2.css" />
    <script src="<%=request.getContextPath()%>/static/js/select2/select2.min.js"></script>

    <script src="<%=request.getContextPath()%>/static/angularjs/angular.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/static/angularjs/angular-sanitize.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/static/js/contract/contract.js" type="text/javascript"></script>

</head>
<body>
<section class="vbox">

    <header class="bg-dark dk header navbar navbar-fixed-top-xs">
        <link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/static/image/osp.ico" />
        <div class="navbar-header aside-md ">
            <a class="btn btn-link visible-xs" data-toggle="class:nav-off-screen,open" data-target="#nav,html">
                <i class="fa fa-bars"></i>
            </a>
            <a href="/index.action" class="navbar-brand" data-toggle="fullscreen1111"><img src="<%=request.getContextPath()%>/static/image/logo-1.png" class="m-r-sm logo_fms"></a>
            <a class="btn btn-link visible-xs" data-toggle="dropdown" data-target=".nav-user">
                <i class="fa fa-cog"></i>
            </a>
        </div>

        <ul class="nav navbar-nav navbar-right hidden-xs nav-user " >
            <li class="hidden-xs">
                <a href="#" class="dropdown-toggle dk" data-toggle="dropdown">
                   Trợ giúp
                </a>
                <section class="dropdown-menu aside-xl">
                    <section class="panel bg-white">
                        <header class="panel-heading b-light bg-light">
                            <strong>Chọn tài liệu</strong>
                        </header>
                        <div class=" list-group-alt animated fadeInRight">
                            <a href="/assets/FMS-HDSD-Admin.pdf" class="media list-group-item">
                              <span class="pull-left thumb-sm">
                                 <i class="fa fa-book fa-3x"></i>
                              </span>
                                <span class="media-body block m-b-none">
                               Hướng dẫn sử dụng
                              </span>
                            </a>
                        </div>

                    </section>
                </section>
            </li>

            <li class="dropdown">
                <a href="#" class=" dropdown-toggle "  data-toggle="dropdown" > <span class="thumb-sm avatar pull-left"> <img src="<%=request.getContextPath()%>/static/note/images/avatar_default.jpg"> </span> <sec:authentication property="principal.name" /> <b class="caret"></b> </a>
                <ul class="dropdown-menu animated fadeInRight "><span class="arrow top"></span>
                    <li><a href="#"><s:property value="getTitleText('fms.user.profile')" /></a></li>
                    <li class="changeMyPassword" data-toggle="modal" data-target="#changeMyPassword"><a href="#">Đổi mật khẩu</a></li>
                    <li class="divider"></li>
                    <li><a href="/j_spring_security_logout">Đăng xuất</a></li>
                </ul>
            </li>

        </ul>
    </header>

<section>
    <section class="hbox stretch">