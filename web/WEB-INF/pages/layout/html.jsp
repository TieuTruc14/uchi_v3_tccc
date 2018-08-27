<%--
  Created by IntelliJ IDEA.
  User: TienManh
  Date: 8/7/2017
  Time: 1:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="app">
<head>
    <meta charset="utf-8"/>
    <title>ADMIN.QC31.VN | Quản Lý Khách Hàng</title>
    <link rel="shortcut icon" type="image/x-icon" href="/favicon.ico" />
    <meta name="description" content="gps, eposi, fms, gsht"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <link rel="stylesheet" href="/assets/note/css/bootstrap.css" type="text/css" />
    <link rel="stylesheet" href="/assets/note/css/animate.css" type="text/css" />
    <link rel="stylesheet" href="/assets/note/css/font-awesome.min.css" type="text/css" />
    <link rel="stylesheet" href="/assets/note/css/font.css" type="text/css" cache="false"/>
    <link rel="stylesheet" href="/assets/note/js/datatables/datatables.css" type="text/css"/>
    <link rel="stylesheet" href="/assets/note/css/app.css" type="text/css"/>
    <link rel="stylesheet" href="/assets/note/css/style.css" type="text/css"/>

    <!--[if lt IE 9]>
    <script src="/assets/note/js/ie/html5shiv.js" cache="false"></script>
    <script src="/assets/note/js/ie/respond.min.js" cache="false"></script>
    <script src="/assets/note/js/ie/excanvas.js" cache="false"></script>
    <![endif]-->

    <script src="/assets/note/js/jquery.min.js"></script>
    <script src="/assets/note/js/bootstrap.js"></script>
    <script src="/assets/note/js/app.js"></script>
    <script src="/assets/note/js/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="/assets/note/js/datatables/jquery.dataTables.min.js"></script>
    <script src="/assets/note/js/app.plugin.js"></script>
    <script src="/assets/js/jquery.spin.js"></script>

</head>
<body>
<section class="vbox">
    <tiles:insertAttribute name="header" />
    <section>
        <section class="hbox stretch">
            <tiles:insertAttribute name="leftpanel" />
            <tiles:insertAttribute name="page" />
        </section>
    </section>
</section>


<script>
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
            (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
        m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

    ga('create', 'UA-49035491-1', 'fms.eposi.vn');
    ga('send', 'pageview');

</script>

</body></html>