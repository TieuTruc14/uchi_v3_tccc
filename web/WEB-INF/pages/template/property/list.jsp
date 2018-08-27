<%@ page import="com.vn.osp.common.util.SystemProperties" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/contract/uchi.css"/>
<script type="text/javascript">
    var url = '<%=SystemProperties.getProperty("url_config_server_api")%>';
    var contextPath = '<%=request.getContextPath()%>';
    $("#uchi-status").show();
    /*setTimeout(function() { $("#uchi-status").hide(); }, 5000);*/
</script>
<style>
    .panel {
        webkit-box-shadow: none;
        box-shadow: none;
    }
</style>
<script src="<%=request.getContextPath()%>/static/js/template/privy/list.js" type="text/javascript"></script>

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Mẫu hiển thị tài sản</span>
</div>
<div class="truong-form-chinhpq" ng-app="osp" ng-controller="privyController">
    <div id="uchi-status" style="margin: 20px">
        <c:if test="${msg != null}">
            <div class="status-${styleCss}"><img class="status-img"
                                                 src="<%=request.getContextPath()%>/static/image/${styleCss}.png"> ${msg}
            </div>
        </c:if>
    </div>
    <div class="panel-group">
        <a href="<%=request.getContextPath()%>/template/property/property-synch" class="btn btn-success pull-right" style="margin: 0px 15px 10px 0px;">Đồng bộ
            lại</a>
        <%--<a href="<%=request.getContextPath()%>/template/property/add-view" class="btn btn-success pull-right" style="margin: 0px 15px 10px 0px;">Thêm mới</a>--%>
        <form id="list-privytemplate" action="/template/privy/list" method="get">
            <input type="hidden" name="totalRecord" id="totalRecord" value="${paginationHelper.totalRecord}">
            <input type="hidden" name="totalPage" id="totalPage" value="${paginationHelper.totalPage}">
            <input type="hidden" name="currentPage" id="currentPage" value="${paginationHelper.currentPage}">
            <input type="hidden" name="rowPerPage" id="rowPerPage" value="${paginationHelper.rowPerPage}">
        </form>
        <form class="form-horizontal" action="/template/privy-synch" id="search-frm" method="get" name="myForm">

            <div class="col-md-12">

                <table class="table" style="margin-bottom:0%">
                    <tr class="border-table">
                        <th class=" ann-title border-table table-giua " style="width:40px;">Id</th>
                        <th class=" ann-title border-table table-giua " style="width:80px;">Mã mẫu</th>
                        <th class=" ann-title border-table table-giua">Tên mẫu</th>
                        <th class=" ann-title border-table table-giua" style="width:140px;">Ngày cập nhập</th>
                        <th class=" ann-title border-table table-giua" style="width:200px;">Hành động</th>
                    </tr>
                    <c:if test="${propertyTemplateList.size() <= 0}">
                        <tr>
                            <td colspan="7"
                                style="height: 100%;background-color: #ececec; line-height: 5.429;text-align: center;font-size: 265%">
                                Chưa có dữ liệu
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${propertyTemplateList.size() > 0}">
                        <c:forEach items="${propertyTemplateList}" var="item">
                            <tr>
                                <td class="border-table align-giua">${item.id}</td>
                                <td class="border-table align-giua">${item.type}</td>
                                <td class="border-table align-giua">${item.name}</td>
                                <td class="border-table align-giua">
                                    <fmt:formatDate pattern="dd-MM-yyyy"
                                                    value="${item.update_time}"/>
                                </td>
                                <td class="border-table align-giua" style="min-width: 120px"><a href="<%=request.getContextPath()%>/template/property/edit/${item.id}" class="btn btn-primary btn-xs">Cập nhật</a> / <a class="btn btn-danger btn-xs" data-toggle="modal" data-target="#myModal${item.id}">Xóa</a></td>

                                <div class="modal fade" id="myModal${item.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                <h4 class="modal-title" id="myModalLabel" style="color: red">Xóa mẫu tài sản!</h4>
                                            </div>
                                            <div class="modal-body">
                                                Bạn có muốn xóa mẫu tài sản có id là ${item.id}, click xác nhận để tiếp tục xóa.
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
                                                <a href="<%=request.getContextPath()%>/template/property/delete/${item.id}" class="btn btn-danger">Xác nhận</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </tr>
                        </c:forEach>

                        <tr class="table-tr-xam">
                            <th colspan="7">
                                <div class="truong-padding-foot-table">Tổng số <span
                                        class="truong-text-colorred">${paginationHelper.totalRecord}</span> dữ liệu
                                </div>
                                <div class="align-phai">
                                    <c:if test="${paginationHelper.currentPage==1}">
                                        <img
                                                class="truong-pagging-icon"
                                                src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">
                                        <img
                                                class="truong-pagging-icon"
                                                src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                    </c:if>
                                    <c:if test="${paginationHelper.currentPage!=1}">
                                        <img onclick="firstPage()"
                                             class="pagging-icon"
                                             src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                        <img onclick="previewPage()"
                                             class="pagging-icon"
                                             src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                    </c:if>
                                        ${paginationHelper.currentPage}
                                    trên ${paginationHelper.totalPage}

                                    <c:if test="${paginationHelper.currentPage == paginationHelper.totalPage}">
                                        <img
                                                class="truong-pagging-icon"
                                                src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                                        <img
                                                class="truong-pagging-icon"
                                                src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                                    </c:if>
                                    <c:if test="${paginationHelper.currentPage!= paginationHelper.totalPage}">
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
</div>


<script>
    function firstPage() {
        $('#currentPage').val(1);
        $("#list-privytemplate").submit();
    }
    function previewPage() {
        var page = $('#currentPage').val();
        $('#currentPage').val(parseInt(page) - 1);
        $("#list-privytemplate").submit();
    }
    function nextPage() {
        var page = $('#currentPage').val();
        $('#currentPage').val(parseInt(page) + 1);
        $("#list-privytemplate").submit();
    }
    function lastPage() {
        var userTotalPage = $('#totalPage').val();
        $('#currentPage').val(userTotalPage);
        $("#list-privytemplate").submit();
    }
    $(document).ready(function () {
        $("[data-toggle=popover]").popover();

    });
    $(function () {
        $('#fromDate').datepicker({
            format: "dd/mm/yyyy",
            language: 'vi'
        }).on('changeDate', function (ev) {
            $(this).datepicker('hide');
        });
        $('#toDate').datepicker({
            format: "dd/mm/yyyy",
            language: 'vi'
        }).on('changeDate', function (ev) {
            $(this).datepicker('hide');
        });


    });
</script>
<script src="<%=request.getContextPath()%>/static/js/contract/app.js"></script>
<jsp:include page="/WEB-INF/pages/layout/footer.jsp"/>

<script>
    $(document).ready(function () {
        //load menu
        var parentItem = $("#mau-giao-dien");
        $(parentItem).click();
        $("#taisan").addClass("child-menu");

    });
</script>