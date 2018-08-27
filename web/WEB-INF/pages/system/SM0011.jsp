<%@ page import="com.vn.osp.common.util.ValidationPool" %>
<%@ page import="com.vn.osp.common.global.Constants" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp"/>
<%--
    Danh sách nhóm quyền
--%>
<spring:url value="/system/create-grouprole-view" var="createGroupRoleUrl"/>
<spring:url value="/system/update-grouprole-view" var="updateGroupRoleUrl"/>
<spring:url value="/system/grouprole-list" var="groupRoleUrl"/>
<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Danh sách nhóm quyền</span>
</div>
<style type="text/css">
    .popover-content {
        overflow : scroll;
        min-height: 100px;
        max-height: 300px;
        word-break: keep-all;
    }
</style>
<div class="col-md-12">
    <form class="form-horizontal" action="${groupRoleUrl}" method="get" id="search-frm">
        <input type="hidden" name="listNumber"  value="${groupRoleListForm.listNumber}">
        <input type="hidden" name="totalPage" id="totalPage" value="${groupRoleListForm.totalPage}">
        <input type="hidden" name="page" id="page" value="${groupRoleListForm.page}">
        <div id="uchi-status" style="padding-right: 15px ; padding-left: 15px" >
            <c:if test="${status.length()>0}">
                <div class="status-${code}"><img class="status-img" src="<%=request.getContextPath()%>/static/image/${code}.png">${status} </div>
            </c:if>
        </div>
        <%
            if(ValidationPool.checkRoleDetail(request,"03", Constants.AUTHORITY_TIMKIEM)){
        %>
        <div class="panel-body">
            <div class="form-group">
                <label class="col-md-2 control-label label-bam-trai text-nowrap">Tên nhóm quyền</label>
                <div class="col-md-3">
                    <input type="text" class="form-control textInput"  name="titleFilter" value="${groupRoleListForm.titleFilter}">
                </div>
                <div class="" style="width:150px !important;float: left">

                    <input type="submit" class="form-control luu" name="" value="Tìm kiếm">
                </div>
            </div>
        </div>
        <%
            }
        %>
        <div class="panel-body">
            <%
                if(ValidationPool.checkRoleDetail(request,"03", Constants.AUTHORITY_THEM)){
            %>

            <div class=" export-button truong-image-fix">
                <a class="truong-small-linkbt" href="${createGroupRoleUrl}"> <input type="button" class="form-control luu truong-image-bt "  name="" value="Thêm mới" ></a>

            </div>
            <%
                }
            %>
            <table class="table" style="margin-bottom:0%">

                <tr class="border-table">
                    <th class=" ann-title border-table table-giua">STT</th>
                    <th class=" ann-title border-table table-giua">Tên nhóm quyền</th>
                    <th class=" ann-title border-table table-giua">Mô tả</th>
                    <th class=" ann-title border-table table-giua">Trạng thái hoạt động</th>
                </tr>
                <c:if test="${groupRoleListForm.listNumber ==0}">
                    <tr>
                        <td colspan="7"
                            style="height: 100%;background-color: #ececec; line-height: 5.429;text-align: center;font-size: 365%">
                            Không có dữ liệu
                        </td>
                    </tr>
                </c:if>
                <c:if test="${groupRoleListForm.listNumber > 0}">
                    <c:forEach items="${groupRoleListForm.groupRoles}" var="item" varStatus="index" >
                        <tr>
                            <td class="border-table align-giua  ">${firstRecord+index.index}</td>
                            <td class="border-table" style="color: black;max-width: 300px;word-wrap: break-word;">
                                        <c:if test="${item.grouprolename.length() <= 200}">
                                            <a href="${updateGroupRoleUrl}/${item.groupRoleId}">${item.grouprolename}</a>
                                        </c:if>
                                        <c:if test="${item.grouprolename.length() > 200}">
                                            <a href="${updateGroupRoleUrl}/${item.groupRoleId}">${item.grouprolename.substring(0,200)} </a>
                                            <img data-html="true" data-toggle="popover" data-trigger="click"  data-placement="top"  src="<%=request.getContextPath()%>/static/image/xemthem.png">
                                            <span class="hidden"><div class='title-green'>Tên nhóm quyền:</div>${item.grouprolename}</span>
                                        </c:if>
                           </td>
                            <td class="border-table"style="color: black;max-width: 300px;word-wrap: break-word;">
                                <c:if test="${item.description.length() <= 200}">
                                    ${item.description}
                                </c:if>
                                <c:if test="${item.description.length() > 200}">
                                    ${item.description.substring(0,200)}
                                    <img data-html="true" data-toggle="popover" data-trigger="click"  data-placement="top"  src="<%=request.getContextPath()%>/static/image/xemthem.png">
                                    <span class="hidden"><div class='title-green'>Mô tả:</div>${item.description}</span>
                                </c:if>
                            </td>
                            <c:if test="${item.active_flg == 1}">
                                <td class="border-table " style="color: black;"><div class="truong-online-fix"><div class="truong-creat-circlegr" ></div>Đang hoạt động</div></td>
                            </c:if>
                            <c:if test="${item.active_flg == 0}">
                                <td class="border-table " style="color: black;"><div class="truong-offline-fix"><div class="truong-creat-circlesv" ></div>Ngừng hoạt động</div></td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    <tr class="table-tr-xam">
                        <th colspan="7"><div class="truong-padding-foot-table"> Tổng số <span style="color:red">${groupRoleListForm.listNumber}</span> dữ
                            liệu</div>
                            <div class="align-phai">
                                <c:if test="${groupRoleListForm.page==1}">
                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                </c:if>
                                <c:if test="${groupRoleListForm.page!=1}">
                                    <img onclick="firstPage()"
                                         class="pagging-icon"
                                         src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                    <img onclick="previewPage()"
                                         class="pagging-icon"
                                         src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                </c:if>
                                    ${groupRoleListForm.page}
                                trên ${groupRoleListForm.totalPage}
                                <c:if test="${groupRoleListForm.page == groupRoleListForm.totalPage}">
                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                                </c:if>
                                <c:if test="${groupRoleListForm.page != groupRoleListForm.totalPage}">
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
            </table>
        </div>
    </form>
</div>

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
        var totalPage = $('#totalPage').val();
        $('#page').val(totalPage);
        $("#search-frm").submit();
    }
</script>
<jsp:include page="/WEB-INF/pages/layout/footer.jsp"/>

<script>
    $(document).ready(function () {
        var parentItem = $("#quan-tri-he-thong");

        $(parentItem).click();

        $("#ds-nhom-quyen").addClass("child-menu");

        $("[data-toggle=popover]").popover({
            html: true,
            content: function () {
                return $(this).parent().children("span").html();
            }
        });
        $("[data-toggle=popover]").on('click', function (e) {
            $("[data-toggle=popover]").not(this).popover('hide');
        });
        $("[data-toggle=popover]").click(function (e) {
            e.stopPropagation();
        });
        $(document).click(function (e) {
            if (($('.popover').has(e.target).length == 0) || $(e.target).is('.close')) {
                $("[data-toggle=popover]").popover('hide');
            }
        });
    });
    $('.textInput').keypress(function (e) {
        if (e.which == 13) {
            $('form#search-frm').submit();
            return false;    //<---- Add this line
        }
    });
</script>


