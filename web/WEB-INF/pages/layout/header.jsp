<%@ page import="com.vn.osp.common.util.SystemProperties" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, shrink-to-fit=no, initial-scale=1">
    <!-- Latest compiled and minified CSS -->

    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/simple-sidebar.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/basic.css">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/bootstrap-datepicker.min.css" />

    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/animate.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/font-awesome.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/bootstrap-tokenfield.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/tokenfield-typeahead.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/contract.css" />

    <!-- jQuery library -->
    <script src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="<%=request.getContextPath()%>/static/js/bootstrap-3.3.7.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/bootstrap-datepicker.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/bootstrap-datepicker.vi.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/bootstrap-tokenfield.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/static/js/main.js" type="text/javascript"></script>
    <!--script type="text/javascript" src="<%=request.getContextPath()%>/static/plugin/mark/mark.js" charset="UTF-8"></script-->
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/plugin/mark/markNew.js" charset="UTF-8"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/select2.min.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/select2.min.js"></script>
    <%--manhpt test--%>
    <script src="<%=request.getContextPath()%>/static/angularjs/angular.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/static/angularjs/angular-sanitize.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/static/js/contract/contract.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/static/note/js/libs/moment.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/static/js/jquery-clockpicker.min.js" type="text/javascript"></script>

    <%--end manhpt--%>
    <script>
        //Check font
        if(!doesFontExist("UTM-Neo-Sans-Intel")) {
            addFont("UTM-Neo-Sans-Intel", "<%=request.getContextPath()%>/static/font/UTM-Neo-Sans-Intel.ttf");
        }
    </script>
<title>UCHI</title>
    <link rel="Shortcut Icon"  href="<%=request.getContextPath()%>/static/image/osp.ico"/>
