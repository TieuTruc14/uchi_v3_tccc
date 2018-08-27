<%@ page import="com.vn.osp.common.util.ValidationPool" %>
<%@ page import="com.vn.osp.common.global.Constants" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />
<%--
    Danh sách dữ liệu sao lưu
--%>
<spring:url value="/system/backup-list" var="backupListUrl" />
<spring:url value="/system/remove-backup" var="multiDeleteUrl"/>

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Danh sách dữ liệu sao lưu</span>
</div>
<div class="truong-form-chinhds panel-group">
    <div id="uchi-status" class="padding-default">
    </div>
    <form class="form-horizontal" action="${backupListUrl}" id="search-frm" method="get">
        <input type="hidden" name="page" id="page" value="${backupList.page}">
        <input type="hidden" name="totalPage" id="userTotalPage" value="${backupList.totalPage}">
        <div class="panel-body">
            <%
                if(ValidationPool.checkRoleDetail(request,"07", Constants.AUTHORITY_TIMKIEM)){
            %>
            <div class="form-group">
                <label class="col-md-2 control-label label-bam-trai">Tên file dữ liệu</label>
                <div class="col-md-3">
                    <input type="text" class="form-control" name="filename" value="${backupList.filename}">
                </div>

                <div class="col-md-2">
                    <div class="row">
                        <div class="" style="width: 150px !important;float: left">
                            <input type="submit" class="form-control luu truong-rs-bt1 " value="Tìm kiếm">
                        </div>
                    </div>
                </div>
            </div>
            <%
                }
            %>



        </div>

        <div class="panel-body truong-margin-footer0px">
            <%
                if(ValidationPool.checkRoleDetail(request,"07", Constants.AUTHORITY_XOA)){
            %>
            <div class=" col-md-12 export-button truong-image-fixxoa">
                <input type="button" class="form-control huybo truong-image-bttrash "  id="button-xoa" data-toggle="modal" data-target="#myModal" value="Xóa" ></a>

            </div>
            <%
                }
            %>
            <table class="table border-table" id="backupTable">
                <tr class="border-table">
                    <th class="border-table table-giua col-md-1"><input type="checkbox" class="selectAll"></th>
                    <th class="ann-title border-table table-giua col-md-9">Tên file, thư mục</th>
                    <th class="ann-title border-table table-giua col-md-2">Thời gian cập nhật</th>
                </tr>
                <c:forEach items="${backupList.list}" var="item">
                    <tr>
                        <td class="border-table align-giua col-md-1"><input type="checkbox" name="cb11" class="cb11" value="${item.fileName}"></td>
                        <td class="border-table "><div class="truong-image-table"></div>${item.fileName}</td>
                        <td class="border-table truong-text-center">${item.dateFile}</td>
                    </tr>
                </c:forEach>
                <tr class="table-tr-xam">
                    <th colspan="7">Tổng số <span style="color:red">${backupList.total}</span> dữ
                        liệu
                        <div class="align-phai">
                            <c:if test="${backupList.page ==1}">
                            <img
                                 class="truong-pagging-icon"
                                 src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                            <img
                                 class="truong-pagging-icon"
                                 src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                            </c:if>
                            <c:if test="${backupList.page !=1}">
                                <img onclick="firstPage()"
                                     class="pagging-icon"
                                     src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                <img onclick="previewPage()"
                                     class="pagging-icon"
                                     src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                            </c:if>
                            ${backupList.page}
                            trên ${backupList.totalPage}
                            <c:if test="${backupList.page == backupList.totalPage}">
                            <img
                                 class="truong-pagging-icon"
                                 src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                            <img
                                 class="truong-pagging-icon"
                                 src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                            </c:if>
                            <c:if test="${backupList.page != backupList.totalPage}">
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
            </table>

        </div>
    </form>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">

            <div class="panel-heading" style="background-color: #2e9ad0 ">
                <h5 class="panel-title truong-text-colorwhite">
                    Xóa thông tin dữ liệu sao lưu

                    <button type="button" class="close truong-button-xoa" data-dismiss="modal" style="margin-bottom: 5px"><img
                            src="<%=request.getContextPath()%>/static/image/close.png" class="control-label truong-imagexoa-fix"></button>


                </h5>

            </div>

            <div class="panel-body">
                <div class="truong-modal-padding" style="padding-bottom: 7%;">
                    <label class="col-md-12 control-label align-giua"> Bạn có thực sự muốn xóa dữ liệu ?</label>
                </div>
            </div>

            <div class="modal-footer">
                <div class="col-md-3 col-md-offset-3">
                    <input type="button" onclick="multiDelete()" data-toggle="modal" data-target="#myModal"
                           class="form-control luu" name="" value="Đồng ý">
                </div>
                <div class="col-md-3 ">
                    <input type="button" class="form-control huybo" name="" data-toggle="modal" data-target="#myModal"
                           value="Hủy bỏ">
                </div>

            </div>
        </div>

    </div>
</div>
<script>
    $('.selectAll').click(function(){
        $('input:checkbox').not(this).prop('checked', this.checked);
    });
</script>
<script>
    $(window).on('resize load', function() {
        var win = $(this);
        if (win.width() < 1437) {
            $('.truong-rs-bttk').removeClass('col-md-8');
            $('.truong-rs-bttk').addClass('col-md-10');

        } else {
            $('.truong-rs-bttk').removeClass('col-md-10');
            $('.truong-rs-bttk').addClass('col-md-8');
        }
    });
</script>
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
        $('#active_flg').val(0);
        $('#account').val("");
    }
    $('.cb11').change(function() {
        var flag = true;

        if (!$('.cb11').is(':checked')) {
            console.log(!$('.cb11').is(':checked'));
            $('.selectAll').prop('checked', false);
        }

        $('.cb11').each(function() {
            var $self = $(this);
            if (!$self.is(':checked')) {
                flag = false;
                return;
            }
        });

        $('.selectAll').prop('checked', flag);
    });
    $(window).on('load',function () {
        var flag = true;

        if (!$('.cb11').is(':checked')) {
            console.log(!$('.cb11').is(':checked'));
            $('.selectAll').prop('checked', false);
        }

        $('.cb11').each(function() {
            var $self = $(this);
            if (!$self.is(':checked')) {
                flag = false;
                return;
            }
        });
        $('.selectAll').prop('checked', flag);
    });
</script>
<script>
    function multiDelete() {
        var deleteData = "";
        var url = location.protocol + '//' + location.host + '${multiDeleteUrl}';
        $("input[name='cb11']").each(function () {
            if ($(this).is(":checked") || $("#selectAll").is(":checked")) {
                deleteData = deleteData + $(this).val() + ",";

            }

        });
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: {
                deleteData: deleteData,
            },

            success: function (response) {
                if(response.messageCode == "ERROR"){
                    $("#uchi-status").html('<div class="status-error"><img class="status-img" src="<%=request.getContextPath()%>/static/image/error.png">Không có file nào được chọn !!</div>');
                    $("#uchi-status").css({ 'display' : 'block' });
                }else if(response.messageCode == "WARRNNING"){
                    /*$(".warnning").css({ 'display' : 'block' });*/
                    /*alert(response.message);*/
                    /*$("#myModal").modal("show");*/
                }else if(response.messageCode == "SUCCESS"){
                    $('#backupTable').load(location.href + " #backupTable>*","");
                    $("#uchi-status").html('<div class="status-success" id="deletesuccess"><img class="status-img" src="<%=request.getContextPath()%>/static/image/success.png">File đã được xóa thành công</div>');
                }
                //window.location.href = "${listUrl}";
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function (e) {
                console.log("DONE");
            }
        });
    }
</script>


<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />

<script>
    $(document).ready(function () {
        var parentItem = $("#quan-tri-he-thong");
        $(parentItem).click();
        $("#ds-backup").addClass("child-menu");
    });
</script>

