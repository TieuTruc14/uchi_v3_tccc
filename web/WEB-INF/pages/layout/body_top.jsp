<%@ page import="com.vn.osp.context.CommonContext" %>
<%@ page import="com.vn.osp.common.util.ValidationPool" %>
<%@ page import="com.vn.osp.common.global.Constants" %>
<%@ page import="com.vn.osp.controller.HomeController" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
</head>
<spring:url value="/logout" var="logoutUrl"/>
<spring:url value="/home" var="homeUrl"/>
<spring:url value="/contract/not-sync-list" var="notSynUrl"/>
<header>
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid" style="background-color: #2ca9e0">
			<div class="navbar-header" style="display:flex;align-items: center">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a href="${homeUrl}"><img src="<%=request.getContextPath()%>/static/image/stp_logo.png" class="navbar-brand"/></a>
				<p id="office-department"><%=((CommonContext)request.getSession().getAttribute(request.getSession().getId())).getAgency()%></p>
				<%if(ValidationPool.checkRoleDetail(request, "11", Constants.AUTHORITY_THEM)){%>
				<a href="${notSynUrl}"><img id="bell" src="<%=request.getContextPath()%>/static/image/bell.png">
					<%if(CommonContext.getNotSyncContract() > 0){%>
					<p id="bell-notice"><%=CommonContext.getNotSyncContract()%></p>
					<%}%>
				</a>
				<%
					}
				%>
				<img id="logout" title="Thoát hệ thống" onclick="showModal()" src="<%=request.getContextPath()%>/static/image/logout.png">
			</div>
		</div>
	</div>
	<style type="text/css">
		.popover-content {
			overflow-y : scroll;
			word-wrap: break-word;
			word-break: keep-all;
			min-height: 100px;
			max-height: 300px;
		}
	</style>
</header>

<body>
<div id="wrapper">

	<!-- Sidebar -->
	<div id="sidebar-wrapper" style="background-color: #2a2a2a">
		<ul class="sidebar-nav">
			<li class="sidebar-brand" style="background-color: #313131">
				<div class="profile">
					<a href="<%=request.getContextPath()%>/user-info" style="cursor: pointer;">
                        <div class="profile_pic" id="avatarImage">
                            <img style="height: 65px;width: 65px" src="<%=request.getContextPath()%><%=HomeController.checkFileShowView(((CommonContext)request.getSession().getAttribute(request.getSession().getId())).getUser().getUserId()).getFile_name()%>" alt="..." class="img-circle profile_img">
                        </div>
						<div class="profile_info">
							<span style="color :white">  Xin chào </span>
							<img class="edit-profile" src="<%=request.getContextPath()%>/static/image/icon menu/edit-profile.png"/>
							<h2 style="color: white"><%=((CommonContext)request.getSession().getAttribute(request.getSession().getId())).getUser().getFamily_name()%> <%=((CommonContext)request.getSession().getAttribute(request.getSession().getId())).getUser().getFirst_name()%></h2>
						</div>
					</a>
				</div>
			</li>
			<li><a href="<%=request.getContextPath()%>/home" id="trang-chu"><img class="menu-logo"  src="<%=request.getContextPath()%>/static/image/icon menu/1.png">&nbsp&nbsp Trang chủ </a></li>
			<%
				if(ValidationPool.checkRoleDetail(request, "10", Constants.AUTHORITY_XEM)){
			%>
			<li>
				<a href="<%=request.getContextPath()%>/transaction/search" id="tra-cuu-thong-tin"><img class="menu-logo"  src="<%=request.getContextPath()%>/static/image/icon menu/2.png">&nbsp&nbsp Tra cứu thông tin</a>
			</li>
			<%
				}
				if(ValidationPool.checkRoleDetail(request, "25", Constants.AUTHORITY_XEM)){
			%>
			<li>
				<%--<a href="<%=request.getContextPath()%>/transaction/multi-search" id="tra-cuu-thong-tin-lien-tinh"><img class="menu-logo"  src="<%=request.getContextPath()%>/static/image/icon menu/2.png">&nbsp&nbsp Tra cứu thông tin</a>--%>
			</li>
			<%
				}
				if(ValidationPool.checkHasRoleInList(request, "11,14")){
			%>
			<li>
				<a href="#" class="dropdown" id="quan-ly-hop-dong"><img class="menu-logo"  src="<%=request.getContextPath()%>/static/image/icon menu/5.png" >&nbsp &nbsp Quản lý hợp đồng<img class="ar2" src="<%=request.getContextPath()%>/static/image/icon menu/ar2.png"></a>
				<ul style="display: none">
					<%
						if(ValidationPool.checkRoleDetail(request, "11", Constants.AUTHORITY_XEM)){
					%>
					<li class="subitem2"><a href="<%=request.getContextPath()%>/contract/list" id="ds-hd-cong-chung"><img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png">Danh sách hợp đồng công chứng</a></li>
					<%
						}
						if(ValidationPool.checkRoleDetail(request, "14", Constants.AUTHORITY_XEM)){
					%>
					<li class="subitem2"><a href="<%=request.getContextPath()%>/contract/temporary/list" id="ds-hd-online"><img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png">Danh sách hợp đồng online</a></li>
					<li class="subitem2"><a href="<%=request.getContextPath()%>/contract/temporary/add" id="soan-hd-online"><img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png">Soạn hợp đồng online</a></li>
					<%
						}
						if(ValidationPool.checkRoleDetail(request, "11", Constants.AUTHORITY_XEM)){
					%>
					<li class="subitem2"><a href="<%=request.getContextPath()%>/contract/not-sync-list" id="ds-hd-chua-db"><img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png">Danh sách hợp đồng chưa đồng bộ</a></li>
					<%
						}
					%>
					<%--<li class="subitem2"><a href="<%=request.getContextPath()%>/system/office-list" id="ds-to-chuc"><img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png">Danh sách tổ chức công chứng / phường xã</a></li>--%>
				</ul>
			</li>
			<%
				}
				if(ValidationPool.checkHasRoleInList(request, "19,20,21,22,23,24")){
			%>
			<li>
				<a href="#" class="dropdown" id="bao-cao-thong-ke"><img class="menu-logo"  src="<%=request.getContextPath()%>/static/image/icon menu/4.png">&nbsp &nbsp Báo cáo thống kê<img class="ar2" src="<%=request.getContextPath()%>/static/image/icon menu/ar2.png"></a>
				<ul style="display: none">
					<%
						if(ValidationPool.checkRoleDetail(request, "19", Constants.AUTHORITY_XEM)){
					%>
					<li class="subitem2"><a href="<%=request.getContextPath()%>/report/group" id="bao-cao-theo-nhom"><img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png">Báo cáo theo nhóm</a></li>
					<%
						}
						if(ValidationPool.checkRoleDetail(request, "20", Constants.AUTHORITY_XEM)){
					%>
					<li class="subitem2"><a href="<%=request.getContextPath()%>/report/by-notary" id="bao-cao-theo-vpcc"><img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png">Báo cáo theo công chứng viên</a></li>
					<%
						}
						if(ValidationPool.checkRoleDetail(request, "23", Constants.AUTHORITY_XEM)){
					%>
					<li class="subitem2"><a href="<%=request.getContextPath()%>/report/by-tt04" id="bao-cao-theo-tt20"><img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png">Báo cáo theo TT 04</a></li>
					<%
						}
						if(ValidationPool.checkRoleDetail(request, "24", Constants.AUTHORITY_XEM)){
					%>
					<li class="subitem2"><a href="<%=request.getContextPath()%>/report/group-bank" id="bao-cao-theo-ngan-hang"><img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png">Báo cáo theo ngân hàng</a></li>
					<%
						}
						if(ValidationPool.checkRoleDetail(request, "21", Constants.AUTHORITY_XEM)){
					%>
					<li class="subitem2"><a href="<%=request.getContextPath()%>/report/general-stastics" id="tk-tong-hop"><img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png">Thống kê tổng hợp</a></li>
					<%--<li class="subitem2"><a href="<%=request.getContextPath()%>/report/by-user" id="bao-cao-theo-cc-vien"><img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png">Báo cáo theo công chứng viên</a></li>--%>
					<%
						}
						if(ValidationPool.checkRoleDetail(request, "22", Constants.AUTHORITY_XEM)){
					%>
					<li class="subitem2"><a href="<%=request.getContextPath()%>/report/contract-certify" id="in-so-cong-chung"><img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png">In sổ hợp đồng công chứng</a></li>
					<%
						}
					%>
				</ul>
			</li>

			<%
				}
				if(ValidationPool.checkRoleDetail(request, "18", Constants.AUTHORITY_XEM)){
			%>
			<li>
				<a href="<%=request.getContextPath()%>/announcement/list" id="thong-bao"><img class="menu-logo"  src="<%=request.getContextPath()%>/static/image/icon menu/6.png">&nbsp &nbsp Thông báo</a>
			</li>
			<%
				}
			%>
            <%
                if(ValidationPool.checkRoleDetail(request, "08", Constants.AUTHORITY_XEM)){
            %>
            <li>
                <a href="<%=request.getContextPath()%>/manual/list" id="huong-dan" onclick="showImage()"><img class="menu-logo"  src="<%=request.getContextPath()%>/static/image/icon menu/hdsd.png">&nbsp &nbsp Quản lý tài liệu</a>
            </li>
            <%}%>
			<li>
				<a href="#" class="dropdown" id="mau-giao-dien"><img class="menu-logo"  src="<%=request.getContextPath()%>/static/image/icon menu/5.png" >&nbsp &nbsp Giao diện hiển thị<img class="ar2" src="<%=request.getContextPath()%>/static/image/icon menu/ar2.png"></a>
				<ul style="display: none">

					<li class="subitem2"><a href="<%=request.getContextPath()%>/template/contract/list" id="tenhopdong"><img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png">Tên Hợp đồng</a></li>
					<li class="subitem2"><a href="<%=request.getContextPath()%>/template/privy/list" id="duongsu"><img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png">Đương sự</a></li>
					<li class="subitem2"><a href="<%=request.getContextPath()%>/template/property/list" id="taisan"><img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png">Tài sản</a></li>

					<%--<li class="subitem2"><a href="<%=request.getContextPath()%>/system/office-list" id="ds-to-chuc"><img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png">Danh sách tổ chức công chứng / phường xã</a></li>--%>
				</ul>
			</li>
			<%

				if(ValidationPool.checkHasRoleInList(request, "02,03,05,06,07,09,08,12")){
			%>
			<li>
				<a href="#" class="dropdown" id="quan-tri-he-thong"><img class="menu-logo"  src="<%=request.getContextPath()%>/static/image/icon menu/5.png" >&nbsp &nbsp Quản trị hệ thống<img class="ar2" src="<%=request.getContextPath()%>/static/image/icon menu/ar2.png"></a>
				<ul style="display: none">
					<%
						if(ValidationPool.checkRoleDetail(request, "02", Constants.AUTHORITY_XEM)){
					%>
					<li class="subitem2"><a href="<%=request.getContextPath()%>/system/user-list" id="ds-can-bo-stp"><img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png">Danh sách cán bộ</a></li>
					<%
						}
						if(ValidationPool.checkRoleDetail(request, "03", Constants.AUTHORITY_XEM)){
					%>
					<li class="subitem2"><a href="<%=request.getContextPath()%>/system/grouprole-list" id="ds-nhom-quyen"><img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png">Danh sách nhóm quyền</a></li>
					<%
						}
						if(ValidationPool.checkRoleDetail(request, "05", Constants.AUTHORITY_XEM)){
					%>
					<li class="subitem2"><a href="<%=request.getContextPath()%>/system/contract-history" id="ls-thay-doi-hop-dong"><img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png">Lịch sử thay đổi hợp đồng</a></li>
					<%
						}
						if(ValidationPool.checkRoleDetail(request, "06", Constants.AUTHORITY_XEM)){
					%>
					<li class="subitem2"><a href="<%=request.getContextPath()%>/system/access-history" id="ls-truy-cap-he-thong"><img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png">Lịch sử thao tác hệ thống</a></li>
					<%
						}
						if(ValidationPool.checkRoleDetail(request, "07", Constants.AUTHORITY_XEM)){
					%>
					<li class="subitem2"><a href="<%=request.getContextPath()%>/system/backup-view" id="ds-backup"><img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png">Cấu hình quản trị sao lưu</a></li>
					<%
						}
						if(ValidationPool.checkRoleDetail(request, "09", Constants.AUTHORITY_XEM)){
					%>
					<li class="subitem2"><a href="<%=request.getContextPath()%>/system/configuration" id="system-configuration"><img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png">Sao lưu dữ liệu từ Sở Tư Pháp</a></li>
					<%
						}
						if(ValidationPool.checkRoleDetail(request, "12", Constants.AUTHORITY_XEM)){
					%>
					<li class="subitem2"><a href="<%=request.getContextPath()%>/system/osp/contracttemplate-list" id="mau-hop-dong"><img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png">Đồng bộ mẫu hợp đồng</a></li>
					<%
						}
					%>
					<li class="subitem2"><a href="<%=request.getContextPath()%>/system/osp/bank-list" id="ngan-hang"><img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png">Đồng bộ ngân hàng</a></li>
					<%
						}
					%>
				</ul>
			</li>
			<li>
				<a href="<%=request.getContextPath()%>/contact-us" id="lien-he"><img class="menu-logo"  src="<%=request.getContextPath()%>/static/image/icon menu/7.png">&nbsp &nbsp Liên hệ</a>
			</li>
		</ul>
		<div>Uchi Sở Tư Pháp v3.0</div>
	</div>
	<!-- /#sidebar-wrapper -->

	<!-- Page Content -->
	<div id="page-content-wrapper">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12" id="uchi-content">




					<div class="modal fade" id="myModalLogout" role="dialog">
						<div class="modal-dialog">

							<!-- Modal content-->
							<div class="modal-content">
								<div class="panel-heading" style="background-color: #2e9ad0 ">
									<h5 class="panel-title truong-text-colorwhite ">
										Thoát hệ thống

										<button type="button" class="close truong-button-xoa" data-dismiss="modal" style="margin-bottom: 5px"><img
												src="<%=request.getContextPath()%>/static/image/close.png" class="control-label truong-imagexoa-fix"></button>
									</h5>

								</div>

								<div class="panel-body">
									<div class="truong-modal-padding" style="padding-bottom: 7%;">
										<label class="col-md-12 control-label align-giua notification">Bạn có thực sự muốn thoát khỏi hệ thống ? </label>
									</div>
								</div>
								<div class="modal-footer">
									<div class="col-md-2 col-md-offset-4">
										<a href="${logoutUrl}" class="truong-small-linkbt"> <input type="button" data-toggle="modal" data-target="#myModalLogout"
											   class="form-control luu" name="" value="Đồng ý"> </a>
									</div>
									<div class="col-md-2 ">
										<input type="button" class="form-control huybo" name="" data-toggle="modal" data-target="#myModalLogout"
											   value="Hủy bỏ">
									</div>

								</div>
							</div>

						</div>
					</div>


<script>
	function showModal() {
		$("#myModalLogout").modal("show");
    }
</script>