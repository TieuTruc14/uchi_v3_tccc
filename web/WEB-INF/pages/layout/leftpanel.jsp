<%@ page import="com.vn.osp.common.util.ValidationPool" %><%--
  Created by IntelliJ IDEA.
  User: TienManh
  Date: 8/7/2017
  Time: 2:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    public boolean isActive(String navPath,HttpServletRequest request) {
        String namespace =(String)request.getAttribute("javax.servlet.forward.request_uri");
        navPath=request.getContextPath()+navPath;
        if (namespace.startsWith(navPath)) {
            return true;
        }

        return false;
    }
%>
<aside class="bg-dark lter aside-md hidden-print hidden-xs" id="nav">
    <section class="vbox">
        <%--<header class="header bg-primary lter text-center clearfix">--%>
            <%--<div class="btn-group">--%>
                <%--<button type="button" class="btn btn-sm btn-dark btn-icon" title="New project"><i class="fa fa-plus"></i></button>--%>
                <%--<div class="btn-group hidden-nav-xs">--%>
                    <%--<button type="button" class="btn btn-sm btn-primary dropdown-toggle" data-toggle="dropdown">--%>
                        <%--Switch Project--%>
                        <%--<span class="caret"></span>--%>
                    <%--</button>--%>
                    <%--<ul class="dropdown-menu text-left">--%>
                        <%--<li><a href="#">Project</a></li>--%>
                        <%--<li><a href="#">Another Project</a></li>--%>
                        <%--<li><a href="#">More Projects</a></li>--%>
                    <%--</ul>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</header>--%>
        <section class="w-f scrollable">
            <div class="slim-scroll" data-height="auto" data-disable-fade-out="true" data-distance="0" data-size="5px" data-color="#333333">

                <!-- nav -->
                <nav class="nav-primary hidden-xs">
                    <ul class="nav">
                        <li class="<%= isActive("/home",request) ? "active" : "" %>"><a href="/dashboard/dashboard.action">
                            <i class="fa fa-dashboard icon">
                                <b class="bg-primary"></b>
                            </i><span>Trang chủ</span></a>
                        </li>
                        <li class="<%= isActive("/transaction/search",request) ? "active" : "" %>"><a href="<%=request.getContextPath()%>/transaction/search" class="<%= isActive("/transaction/search",request) ? "active" : "" %>"> <i class="fa fa-search icon"> <b class="bg-info"></b> </i> <span class="pull-right"> <i class="fa fa-search text-active"></i> </span> <span>Tra cứu nội tỉnh</span> </a>
                        </li>
                        <li class="<%= isActive("/transaction/multi-search",request) ? "active" : "" %>"><a href="<%=request.getContextPath()%>/transaction/multi-search" class="<%= isActive("/transaction/multi-search",request) ? "active" : "" %>"> <i class="fa fa-search icon"> <b class="bg-info"></b> </i> <span class="pull-right"> <i class="fa fa-search text-active"></i> </span> <span>Tra cứu liên tỉnh</span> </a>
                        </li>

                        <li class=" <%= (isActive("/contract",request)==true) ? "active" : "" %>"><a href="<%=request.getContextPath()%>/contract" class="<%= isActive("/contract",request) ? "active" : "" %>"> <i class="fa fa-align-justify icon"> <b class="bg-info"></b> </i> <span class="pull-right"> <i class="fa fa-angle-down text"></i> <i class="fa fa-angle-up text-active"></i> </span> <span>Quản lý hợp đồng</span> </a>
                            <ul class="nav lt">
                                    <li class="<%= isActive("/contract/list",request) ? "active" : "" %>"><a href="<%=request.getContextPath()%>/contract/list"> <i class="fa fa-angle-right"></i> <span>Hợp đồng công chứng</span> </a></li>
                                    <li class="<%= isActive("/contract/temporary/list",request) ? "active" : "" %>"><a href="<%=request.getContextPath()%>/contract/temporary/list"> <i class="fa fa-angle-right"></i> <span>Hợp đồng online</span> </a></li>
                                    <li class="<%= isActive("/contract/temporary/add",request) ? "active" : "noactive" %>"><a href="<%=request.getContextPath()%>/contract/temporary/add"> <i class="fa fa-angle-right"></i> <span>Soạn hợp đồng online</span> </a></li>
                                    <li class="<%= isActive("/contract/not-sync-list",request)==true ? "active" : "noactive" %>"><a href="<%=request.getContextPath()%>/contract/not-sync-list"> <i class="fa fa-angle-right"></i> <span>Hợp đồng chưa đồng bộ</span> </a></li>
                            </ul>
                        </li>

                        <li class="<%= isActive("/report",request) ? "active" : "" %>"><a href="#" class="<%= isActive("/report",request) ? "active" : "" %>"> <i class="fa fa-book icon"> <b class="bg-info"></b> </i> <span class="pull-right"> <i class="fa fa-angle-down text"></i> <i class="fa fa-angle-up text-active"></i> </span> <span>Báo cáo thống kê</span> </a>
                            <ul class="nav lt">

                                    <li class="<%= isActive("/report/group",request) ? "active" : "" %>"><a href="<%=request.getContextPath()%>/report/group"> <i class="fa fa-angle-right"></i> <span>Theo nhóm</span> </a></li>
                                    <li class="<%= isActive("/report/by-notary",request) ? "active" : "" %>"><a href="<%=request.getContextPath()%>/report/by-notary"> <i class="fa fa-angle-right"></i> <span>Theo công chứng viên</span> </a></li>
                                    <li class="<%= isActive("/report/by-tt20",request) ? "active" : "" %>"><a href="<%=request.getContextPath()%>/report/by-tt20"> <i class="fa fa-angle-right"></i> <span>Theo thông tư 20</span> </a></li>
                                    <li class="<%= isActive("/report/group-bank",request) ? "active" : "" %>"><a href="<%=request.getContextPath()%>/report/group-bank"> <i class="fa fa-angle-right"></i> <span>Theo ngân hàng</span> </a></li>
                                    <li class="<%= isActive("/report/general-stastics",request) ? "active" : "" %>"><a href="<%=request.getContextPath()%>/report/general-stastics"> <i class="fa fa-angle-right"></i> <span>Thống kê tổng hợp</span> </a></li>
                                    <li class="<%= isActive("/report/contract-certify",request) ? "active" : "" %>"><a href="<%=request.getContextPath()%>/report/contract-certify"> <i class="fa fa-angle-right"></i> <span>In sổ hợp đồng công chứng</span> </a></li>

                            </ul>
                        </li>

                        <li class="<%= isActive("/system",request) ? "active" : "" %>"><a href="#" class="<%= isActive("/system",request) ? "active" : "" %>"> <i class="fa fa-gear icon"> <b class="bg-info"></b> </i> <span class="pull-right"> <i class="fa fa-angle-down text"></i> <i class="fa fa-angle-up text-active"></i> </span> <span>Quản trị hệ thống</span> </a>
                            <ul class="nav lt">
                                <li class="<%= isActive("/system/user-list",request) ? "active" : "" %>"><a href="<%=request.getContextPath()%>/system/user-list"> <i class="fa fa-angle-right"></i> <span>Danh sách cán bộ</span> </a></li>
                                <li class="<%= isActive("/system/grouprole-list",request) ? "active" : "" %>"><a href="<%=request.getContextPath()%>/system/grouprole-list"> <i class="fa fa-angle-right"></i> <span>Danh sách nhóm quyền</span> </a></li>
                                <li class="<%= isActive("/system/contract-history",request) ? "active" : "" %>"><a href="<%=request.getContextPath()%>/system/contract-history"> <i class="fa fa-angle-right"></i> <span>Lịch sử hợp đồng</span> </a></li>
                                <li class="<%= isActive("/system/access-history",request) ? "active" : "" %>"><a href="<%=request.getContextPath()%>/system/access-history"> <i class="fa fa-angle-right"></i> <span>Lịch sử truy cập</span> </a></li>
                                <li class="<%= isActive("/system/backup-view",request) ? "active" : "" %>"><a href="<%=request.getContextPath()%>/system/backup-view"> <i class="fa fa-angle-right"></i> <span>Quản trị sao lưu</span> </a></li>
                                <li class="<%= isActive("/system/configuration",request) ? "active" : "" %>"><a href="<%=request.getContextPath()%>/system/configuration"> <i class="fa fa-angle-right"></i> <span>Sao lưu dữ liệu từ sở</span> </a></li>
                                <li class="<%= isActive("/manual/list",request) ? "active" : "" %>"><a href="<%=request.getContextPath()%>/manual/list"> <i class="fa fa-angle-right"></i> <span>Quản lý tài liệu</span> </a></li>
                                <li class="<%= isActive("/osp/contracttemplate-list",request) ? "active" : "" %>"><a href="<%=request.getContextPath()%>/osp/contracttemplate-list"> <i class="fa fa-angle-right"></i> <span>Đồng bộ mẫu hợp đồng</span> </a></li>
                                <li class="<%= isActive("/osp/bank-list",request) ? "active" : "" %>"><a href="<%=request.getContextPath()%>/osp/bank-list"> <i class="fa fa-angle-right"></i> <span>Đồng bộ ngân hàng</span> </a></li>

                            </ul>
                        </li>
                        <li class="<%= isActive("/announcement/list",request) ? "active" : "" %>"><a href="<%=request.getContextPath()%>/announcement/list">
                            <i class="fa fa-bullhorn icon"> <b class="bg-warning"></b></i><span>Thông báo</span></a>
                        </li>
                        <li class="<%= isActive("/contact-us",request) ? "active" : "" %>"><a href="<%=request.getContextPath()%>/contact-us">
                            <i class="fa fa-code-fork icon"><b class="bg-success"></b></i><span>Liên hệ</span></a>
                        </li>

                    </ul>
                </nav>
                <!-- / nav -->
            </div>
        </section>

        <footer class="footer lt hidden-xs b-t b-dark">
            <div id="chat" class="dropup">
                <section class="dropdown-menu on aside-md m-l-n">
                    <section class="panel bg-white">
                        <header class="panel-heading b-b b-light">Active chats</header>
                        <div class="panel-body animated fadeInRight">
                            <p class="text-sm">No active chats.</p>
                            <p><a href="#" class="btn btn-sm btn-default">Start a chat</a></p>
                        </div>
                    </section>
                </section>
            </div>
            <div id="invite" class="dropup">
                <section class="dropdown-menu on aside-md m-l-n">
                    <section class="panel bg-white">
                        <header class="panel-heading b-b b-light">
                            John <i class="fa fa-circle text-success"></i>
                        </header>
                        <div class="panel-body animated fadeInRight">
                            <p class="text-sm">No contacts in your lists.</p>
                            <p><a href="#" class="btn btn-sm btn-facebook"><i class="fa fa-fw fa-facebook"></i> Invite from Facebook</a></p>
                        </div>
                    </section>
                </section>
            </div>
            <a href="#nav" data-toggle="class:nav-xs" class="pull-right btn btn-sm btn-dark btn-icon">
                <i class="fa fa-angle-left text"></i>
                <i class="fa fa-angle-right text-active"></i>
            </a>
            <div class="btn-group hidden-nav-xs">
                <button type="button" title="Chats" class="btn btn-icon btn-sm btn-dark" data-toggle="dropdown" data-target="#chat"><i class="fa fa-comment-o"></i></button>
                <button type="button" title="Contacts" class="btn btn-icon btn-sm btn-dark" data-toggle="dropdown" data-target="#invite"><i class="fa fa-facebook"></i></button>
            </div>
        </footer>
    </section>
</aside>