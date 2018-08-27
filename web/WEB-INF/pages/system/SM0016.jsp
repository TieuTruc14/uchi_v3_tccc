<%@ page import="com.vn.osp.common.util.ValidationPool" %>
<%@ page import="com.vn.osp.common.global.Constants" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />

<%--
    Thông tin cấu hình 1
--%>
<spring:url value="/system/backup-list" var="listUrl" />
<spring:url value="/system/backup-update-view" var="backInitUrl" />
<spring:url value="/system/backup-now" var="backupNowUrl" />
<spring:url value="/system/restore-view" var="restoreUrl" />


<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Cấu hình tham số sao lưu</span>
</div>
<div class="truong-form-chinhbtt">
    <div id="uchi-status" >
        <c:if test="${cfBackup.success == true}">
            <div class="status-success"><img class="status-img" src="<%=request.getContextPath()%>/static/image/success.png">${cfBackup.action_status}</div>
        </c:if>
        <c:if test="${cfBackup.success == false}">
            <div class="status-error"><img class="status-img" src="<%=request.getContextPath()%>/static/image/error.png">${cfBackup.action_status}</div>
        </c:if>
        <c:if test="${successCode != null}">
            <div class="status-success"><img class="status-img" src="<%=request.getContextPath()%>/static/image/success.png">${successCode}</div>
        </c:if>
        <c:if test="${errorCode != null}">
            <div class="status-error"><img class="status-img" src="<%=request.getContextPath()%>/static/image/error.png">${errorCode}</div>
        </c:if>

    </div>
    <div class="panel-group" id="accordion">
        <form class="form-horizontal" action="${addUrl}" method="post">
            <div class="panel panel-default" id="panel1">
                <div class="panel-heading">
                    <h4 class="panel-title">

                            THÔNG TIN CẤU HÌNH LƯU TRỮ DỮ LIỆU SAO LƯU

                    </h4>

                </div>
                <div class="panel-body">
                    <div class="row truong-inline-field">

                        <table class="table " >
                            <tr class="border-table">
                                <th class="border-table col-md-3 ann-title" >Thư mục lưu trữ dữ liệu sao lưu</th>
                                <td class="border-table">${cfBackup.pathBackUp}</td>
                            </tr>
                            <tr class="border-table">
                                <th class="border-table col-md-3 ann-title">Lịch sao lưu</th>
                                <td class="border-table">
                                  ${cfBackup.mon == true?'Thứ 2 ':''}
                                    ${cfBackup.tue == true?'- Thứ 3 ':''}
                                    ${cfBackup.wed == true?'- Thứ 4 ':''}
                                    ${cfBackup.thu == true?'- Thứ 5 ':''}
                                    ${cfBackup.fri == true?'- Thứ 6 ':''}
                                    ${cfBackup.sat == true?'- Thứ 7 ':''}
                                    ${cfBackup.sun == true?'- Chủ nhật':''}
                                </td>
                            </tr>
                            <tr class="border-table">
                                <th class="border-table col-md-3 ann-title">Thời gian sao lưu</th>
                                <td class="border-table">${cfBackup.backupTime}</td>
                            </tr>
                            <tr class="border-table">
                                <th class="border-table col-md-3 ann-title">Danh sách gửi mail</th>
                                <td class="border-table">${cfBackup.email}</td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="truong-panel-body">
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <div class="col-md-4">
                                <div class="row">
                                    <label class="col-md-5 control-label label-bam-trai">Trạng thái sao lưu</label>


                                    <label class="control-label  truong-text-radior col-md-6"><span class="truong-text-coloryl">Cho phép sao lưu dữ liệu</span></label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <div class="truong-prevent-btb">
            <div class="form-group" style="text-align: center; ">
                <%
                    if(ValidationPool.checkRoleDetail(request,"07", Constants.AUTHORITY_SUA)){
                %>
                <a href="${backInitUrl}" class="btn btn-primary btn-stp" >Chỉnh sửa</a>

                <a data-toggle="modal" data-target="#myModal" class="btn btn-primary btn-stp" >Sao lưu dữ liệu đột xuất </a>
                <!-- Modal -->
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header" style="background-color: #2ca9e0">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel" style="color: white" >Sao lưu dữ liệu đột xuất </h4>
                            </div>
                            <div class="modal-body">
                                Việc làm này có thể tốn nhiều thời gian và tốn dung lượng bộ nhớ. Bạn có chắc chắn sao lưu dữ liệu hay không ?
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default btn-stp-default" data-dismiss="modal">Đóng</button>
                                <a href="${backupNowUrl}" id="showNotification" class="btn btn-primary btn-stp" >Sao lưu ngay</a>
                            </div>
                        </div>
                    </div>
                </div>
                <%
                    }

                %>

                        <%
                            if(ValidationPool.checkRoleDetail(request,"07", Constants.AUTHORITY_SUA)){
                        %>

                        <a href="${restoreUrl}" class="btn btn-primary btn-stp" >Phục hồi dữ liệu </a>
                        <%
                            }
                        %>
                        <a href="${listUrl}" class="btn btn-primary btn-stp" >Xem danh sách</a>


            </div>

        </div>

    </div>


</div>







<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />

<script>
    $(document).ready(function () {
        $('#tokenfield').tokenfield();
        var parentItem = $("#quan-tri-he-thong");
        $(parentItem).click();
        $("#ds-backup").addClass("child-menu");
    });
</script>
