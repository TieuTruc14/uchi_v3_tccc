<%@ page import="com.vn.osp.common.util.ValidationPool" %>
<%@ page import="com.vn.osp.common.global.Constants" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />
<%--
    Danh sách cán bộ
--%>
<spring:url value="/system/add-user-view" var="addUrl" />
<spring:url value="/system/user-list" var="userListUrl" />
<spring:url value="/system/user-permission-view" var="userPermissionViewUrl" />
<spring:url value="/system/user-update-view" var="updateUrl" />
<spring:url value="/system/synchronize-notary" var="synURL" />

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Danh sách cán bộ</span>
</div>
<div class="truong-form-chinhds panel-group">

    <div id="uchi-status" class="padding-default">
        <c:if test="${users.action_status != null}">
            <div class="status-success"><img class="status-img" src="<%=request.getContextPath()%>/static/image/success.png">${users.action_status}</div>
        </c:if>
        <c:if test="${errorCode != null}">
            <div class="status-error"><img class="status-img" src="<%=request.getContextPath()%>/static/image/error.png">${errorCode}</div>
        </c:if>
        <c:if test="${successCode != null}">
            <div class="status-success"><img class="status-img" src="<%=request.getContextPath()%>/static/image/success.png">${successCode}</div>
        </c:if>
    </div>
    <form class="form-horizontal" action="${userListUrl}" id="search-frm" method="get">
        <input type="hidden" name="page" id="page" value="${users.page}">
        <input type="hidden" name="userTotalPage" id="userTotalPage" value="${users.userTotalPage}">
        <%
            if(ValidationPool.checkRoleDetail(request, "02", Constants.AUTHORITY_TIMKIEM)){
        %>
        <div class="panel-body">
            <div class="form-group">
                <div class="col-md-3 float-left">
                    <span class="truong-label-top">Họ đệm</span>
                            <input type="text" class="form-control" name="family_name" id="family_name" value="${users.family_name}">

                </div>
                <div class="col-md-3 float-left">
                    <span class="truong-label-top">Tên</span>
                    <input type="text" class="form-control" name="first_name" id="first_name" value="${users.first_name}">
                </div>

                <%--<div class="col-md-4">
                    <div class="row">
                        <label class="col-md-3 control-label label-bam-trai">Tên tổ chức</label>
                        <div class="col-md-9">
                            <select class="select2" name="office_id"  id="officeAuthor" style="width:200px;">
                                <option value="" ${item.noid==""?"selected":""}>[Tất cả]</option>
                                <c:forEach items="${users.notaryOffices}" var="item">
                                    <option value="${item.noid}" authorCode="${item.authentication_id}" ${item.noid==users.office_id?"selected":""}>${item.name}</option>
                                </c:forEach>
                            </select>

                        </div>
                    </div>
                </div>--%>
            </div>


            <div class="form-group">
                <div class="col-md-3 float-left">
                    <span class="truong-label-top">Tài khoản đăng nhập</span>
                    <input type="text" class="form-control" name="account" id="account" value="${users.account}">

                </div>
                <div class="col-md-3 float-left">
                    <span class="truong-label-top">Trạng thái hoạt động</span>
                            <select class=" form-control truong-selectbox-padding select2" name="active_flg" id ="active_flg">
                                <option value="0" ${users.active_flg == 0 ?"selected":""}>[Tất cả]</option>
                                <option value="1" ${users.active_flg == 1 ?"selected":""}>Đang hoạt động</option>
                                <option value="2" ${users.active_flg == 2 ?"selected":""}>Ngừng hoạt động</option>
                            </select>

                </div>
                <div class="col-md-5 float-right" style="padding-top: 16px !important;padding-left: 0px !important;">

                    <div class="truong-button-fixwidthright truong-rs-bt2" style="margin-right: 0px !important;">

                        <input type="submit" class="form-control luu" name="" value="Tìm kiếm">
                    </div>
                    <div class="truong-button-fixwidthright truong-rs-bt2">
                        <input type="button" class="form-control huybo" name="" value="Xóa điều kiện" onclick="clearText()">
                    </div>

                </div>
            </div>

        </div>
        <%
            }
        %>
        <div class="panel-body truong-margin-footer0px">
            <%
                if(ValidationPool.checkRoleDetail(request, "02", Constants.AUTHORITY_THEM)){
            %>
            <div class=" export-button truong-image-fix">
                <a class="truong-small-linkbt" href="${synURL}"> <input type="button" class="form-control luu truong-image-bt "  name="" value="Cập nhật" ></a>

            </div>
            <div class=" export-button truong-image-fix">
                <a class="truong-small-linkbt" href="${addUrl}"> <input type="button" class="form-control luu truong-image-bt "  name="" value="Thêm mới" ></a>

            </div>
            <%
                }
            %>
            <table class="table" style="margin-bottom:0%" >

                <tr class="border-table">
                    <th class=" ann-title border-table table-giua truong-rstable-widthper15">Họ tên</th>
                    <th class=" ann-title border-table table-giua truong-rstable-widthper15">Tài khoản đăng nhập</th>
                    <th class=" ann-title border-table table-giua truong-rstable-widthper12">Ngày sinh</th>
                    <th class=" ann-title border-table table-giua ">Chức vụ</th>
                    <th class=" ann-title border-table table-giua truong-rstable-widthper15">Trạng thái hoạt động</th>
                    <%
                        if(ValidationPool.checkRoleDetail(request, "03", Constants.AUTHORITY_THEM)||ValidationPool.checkRoleDetail(request, "04", Constants.AUTHORITY_SUA)){
                    %>
                    <th class=" ann-title border-table table-giua truong-rstable-widthper8">Phân quyền</th>
                    <%
                        }
                    %>
                </tr>
                <c:if test="${users.userListNumber > 0}">
                    <c:forEach items="${users.userList}" var="item">
                        <tr>
                            <td class="border-table truong-text-verticel  "><a href="${updateUrl}/${item.userId}">${item.family_name} ${item.first_name}</a></td>
                            <td class="border-table truong-text-verticel" style="color: black;">${item.account}</td>
                            <td class="border-table align-giua" style="color: black;">${item.birthday}</td>
                            <td class="border-table truong-text-verticel" style="color: black;">${item.role}</td>

                            <td class="border-table " style="color: black;">
                                <c:if test="${item.active_flg==1}"><div class="truong-online-fix"><div class="truong-creat-circlegr" ></div>Đang hoạt động</div></c:if>
                                <c:if test="${item.active_flg==0}"><div class="truong-offline-fix"><div class="truong-creat-circlesv" ></div>Ngừng hoạt động</div></c:if>
                            </td>
                            <%
                                if(ValidationPool.checkRoleDetail(request, "03", Constants.AUTHORITY_THEM)||ValidationPool.checkRoleDetail(request, "04", Constants.AUTHORITY_SUA)){
                            %>
                            <td class="border-table align-giua" style="color: black;"><a href="${userPermissionViewUrl}/${item.userId}"><img class="truong-image-tb"src="<%=request.getContextPath()%>/static/image/phanquyen.png"></a></td>
                            <%
                                }
                            %>
                        </tr>
                    </c:forEach>
                    <tr class="table-tr-xam">
                        <th colspan="7"><div class="truong-padding-foot-table">  Tổng số <span class="truong-text-colorred">${users.userListNumber}</span> dữ
                            liệu </div>
                            <div class="align-phai">
                                <c:if test="${users.page==1}">
                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">
                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                </c:if>
                                <c:if test="${users.page!=1}">
                                    <img onclick="firstPage()"
                                         class="pagging-icon"
                                         src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">
                                    <img onclick="previewPage()"
                                         class="pagging-icon"
                                         src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                </c:if>
                                    ${users.page}
                                trên ${users.userTotalPage}
                                <c:if test="${users.page==users.userTotalPage}">
                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                                </c:if>
                                <c:if test="${users.page!=users.userTotalPage}">
                                    <img onclick="nextPage()"
                                         class="pagging-icon"
                                         src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                                    <img onclick="lastPage()"
                                         class="pagging-icon"
                                         src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                                </c:if>
                            </div>
                        </th>
                    </tr>
                </c:if>
                <c:if test="${users.userListNumber == 0}">
                    <tr>
                        <td colspan="7"
                            style="height: 100%;background-color: #ececec; line-height: 5.429;text-align: center;font-size: 265%">
                            Không có dữ liệu
                        </td>
                    </tr>
                </c:if>

            </table>
        </div>
    </form>
</div>

<script type="text/javascript">
    $(".select2").select2();
</script>
<script>
    $(window).on('load resize',function(){
        var win = $(this);
        if(win.width() < 1260){

            $('.truong-rs-bt2').removeClass('truong-button-fixwidthright');
            $('.truong-rs-bt2').addClass('truong-button-fixwidthrightp5');
        }else {

            $('.truong-rs-bt2').removeClass('truong-button-fixwidthrightp5');
            $('.truong-rs-bt2').addClass('truong-button-fixwidthright');

        }
    });
</script>
<%--
<script>
    $(window).on('resize', function() {
        var win = $(this);
        if (win.width() < 1600) {
            $('.truong-re-bt').removeClass('col-md-1');
            $('.truong-re-bt').addClass('col-md-2');
            $('.truong-re-bt').removeClass('col-md-offset-11');
            $('.truong-re-bt').addClass('col-md-offset-10');

        } else {
            $('.truong-re-bt').removeClass('col-md-2');
            $('.truong-re-bt').addClass('col-md-1');
            $('.truong-re-bt').removeClass('col-md-offset-10');
            $('.truong-re-bt').addClass('col-md-offset-11');
        }
    });
</script>
--%>

<script>
    function firstPage() {
        $('#page').val(1);
        $("#search-frm").submit();
    }
    function previewPage() {
        var page = $('#page').val();
        $('#page').val(parseInt(page) - 1);
        $("#search-frm").submit();
    }
    function nextPage() {
        var page = $('#page').val();
        $('#page').val(parseInt(page) + 1);
        $("#search-frm").submit();
    }
    function lastPage() {
        var userTotalPage = $('#userTotalPage').val();
        $('#page').val(userTotalPage);
        $("#search-frm").submit();
    }
    function clearText(){
        $('#family_name').val("");
        $('#first_name').val("");
        $('#active_flg').select2('val',['0']);
        $('#account').val("");
    }
</script>


<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />
<script>
    $(document).ready(function () {
        var parentItem = $("#quan-tri-he-thong");
        $(parentItem).click();
        $("#ds-can-bo-stp").addClass("child-menu");
    });
</script>


