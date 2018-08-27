<%@ page import="com.vn.osp.common.util.ValidationPool" %>
<%@ page import="com.vn.osp.common.util.TimeUtil" %>
<%@ page import="com.vn.osp.common.global.Constants" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/checkbox.js"></script>

<jsp:include page="/WEB-INF/pages/layout/body_top.jsp"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--Danh sách thông báo--%>
<spring:url value="/announcement/list" var="listUrl"/>
<spring:url value="/announcement/multiDelete" var="multiDeleteUrl"/>
<spring:url value="/announcement/update-view" var="updateViewUrl"/>
<spring:url value="/announcement/add-view" var="addViewUrl"/>
<spring:url value="/announcement/view" var="viewUrl"/>



<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Danh sách thông báo</span>
</div>


<div class="truong-form-chinhds panel-group ">
    <div id="uchi-status" class="padding-default">
        <c:if test="${announcementList.actionStatus != null}">
            <div class="status-success"><img class="status-img" src="<%=request.getContextPath()%>/static/image/success.png">${announcementList.actionStatus}</div>
        </c:if>
        <c:if test="${errorCode != null}">
            <div class="status-error"><img class="status-img" src="<%=request.getContextPath()%>/static/image/error.png">${errorCode}</div>
        </c:if>
    </div>
    <form class="form-horizontal" id="search-frm" action="${listUrl}" method="get">

        <input type="hidden" name="defaultTabOpen" id="defaultTabOpen" value="${announcementList.defaultTabOpen}">

        <input type="hidden" name="internalPage" id="internalPage" value="${announcementList.internalPage}">
        <input type="hidden" name="publicPage" id="publicPage" value="${announcementList.publicPage}">
        <input type="hidden" name="publicListNumber" id="publicListNumber" value="${announcementList.publicListNumber}">
        <input type="hidden" name="publicTotalPage" id="publicTotalPage" value="${announcementList.publicTotalPage}">
        <input type="hidden" name="interalListNumber" id="interalListNumber" value="${announcementList.internalListNumber}">
        <input type="hidden" name="internalTotalPage" id="internalTotalPage" value="${announcementList.internalTotalPage}">

        <%
            if(ValidationPool.checkRoleDetail(request,"18", Constants.AUTHORITY_TIMKIEM)){
        %>
        <div id="advance-frm" class="panel-body">


            <div class="form-group">
                <div class="control-label">

                    <label class="col-md-2 control-label label-bam-trai">Tiêu đề</label>
                    <div class="col-md-5">
                        <input type="text" class="form-control" name="announcementTitleFilter" id ="title"
                               value="${announcementList.announcementTitleFilter}">
                    </div>
                    <div class="" style="width:150px ;float: left">
                        <input type="submit" value="Tìm kiếm"  class="form-control luu">
                    </div>

                </div>
            </div>

        </div>
        <%
            }
        %>

        <div class="panel-body truong-margin-footer0px" id="listTable">
            <ul class="nav nav-tabs">
                <li><a data-toggle="tab" href="#noibo">Thông báo nội bộ (<span class="truong-text-colorred" > ${announcementList.internalListNumber}</span>)</a></li>
                <li><a data-toggle="tab" href="#tuso">Thông báo từ Sở Tư pháp (<span class="truong-text-colorred"> ${announcementList.publicListNumber}</span>)</a></li>
                <div class="control-label">
                    <%
                        if(ValidationPool.checkRoleDetail(request,"18", Constants.AUTHORITY_XOA)){
                    %>
                    <div class="float-right-button-table">
                        <input type="button" class="form-control huybo " onclick="checkDelete()"
                               value="Xóa "/>
                    </div>
                    <%
                        }
                        if(ValidationPool.checkRoleDetail(request,"18", Constants.AUTHORITY_THEM)){
                    %>
                    <div class=" float-right-button-table">
                        <a href="${addViewUrl}" class="truong-small-linkbt"><input class="form-control luu truong-image-bt " name="" value="Thêm mới" type="button"></a>
                    </div>
                    <%
                        }
                    %>
                </div>
            </ul>



            <div class="tab-content">
                <c:if test="${announcementList.defaultTabOpen == 1}">
                <div id="noibo" class="tab-pane fade in active">
                    </c:if>
                    <c:if test="${announcementList.defaultTabOpen != 1}">
                    <div id="noibo" class="tab-pane fade">
                        </c:if>

                        <table class="table" style="margin-bottom:0%">

                            <tr class="border-table">
                                <th class=" ann-title border-table table-giua truong-rstable-widthper5"><input type="checkbox"
                                                                                                               class="selectAll"
                                                                                                               id="selectAll"></th>
                                <th class=" ann-title border-table table-giua ">Tiêu đề</th>
                                <th class=" ann-title border-table table-giua truong-rstable-widthper15">Người gửi
                                </th>
                                <th class=" ann-title border-table table-giua truong-rstable-widthper8">Ngày gửi
                                </th>
                            </tr>
                            <c:if test="${announcementList.internalListNumber == 0}">
                                <tr>
                                    <td colspan="7"
                                        style="height: 100%;background-color: #ececec; line-height: 5.429;text-align: center;font-size: 265%">
                                        Không có dữ liệu
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${announcementList.internalListNumber != 0}">
                                <c:forEach items="${announcementList.announcementInternalList}" var="item">

                                    <tr>
                                        <td class="border-table align-giua  ">
                                            <input type="checkbox" id="cb${item.aid}" name="cbChild"
                                                   value="${item.aid}"/>
                                        </td>
                                        <td class="border-table" style="color: black;width: 30%">
                                            <a href="${updateViewUrl}/${item.aid}">${item.title}</a>
                                            <c:if test="${item.importance_type == 2}">
                                                <img style="height: 21px;float: top;padding-bottom: 7px;" src="<%=request.getContextPath()%>/static/image/flag.png">
                                            </c:if>
                                        </td>
                                        <td class="border-table"
                                            style="color: black;text-align: center">${item.sender_info}</td>
                                        <td class="border-table"
                                            style="color: black;">${item.entry_date_time}</td>
                                    </tr>
                                </c:forEach>
                                <tr class="table-tr-xam">
                                    <th colspan="7"><div class="truong-padding-foot-table"> Tổng số <span class="truong-text-colorred"> ${announcementList.internalListNumber}</span> dữ
                                        liệu</div>

                                        <div class="align-phai">
                                            <c:if test="${announcementList.internalPage==1}">
                                                <img
                                                        class="truong-pagging-icon"
                                                        src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                                <img
                                                        class="truong-pagging-icon"
                                                        src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                            </c:if>
                                            <c:if test="${announcementList.internalPage!=1}">
                                                <img onclick="firstPage(1)"
                                                     class="pagging-icon"
                                                     src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                                <img onclick="previewPage(1)"
                                                     class="pagging-icon"
                                                     src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                            </c:if>
                                                ${announcementList.internalPage}
                                            trên ${announcementList.internalTotalPage}
                                            <c:if test="${announcementList.internalPage==announcementList.internalTotalPage}">
                                                <img
                                                        class="truong-pagging-icon"
                                                        src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                                                <img
                                                        class="truong-pagging-icon"
                                                        src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                                            </c:if>
                                            <c:if test="${announcementList.internalPage!=announcementList.internalTotalPage}">
                                                <img onclick="nextPage(1)"
                                                     class="pagging-icon"
                                                     src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                                                <img onclick="lastPage(1)"
                                                     class="pagging-icon"
                                                     src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                                            </c:if>
                                        </div>
                                    </th>
                                </tr>
                            </c:if>


                        </table>
                    </div>

                    <c:if test="${announcementList.defaultTabOpen == 2}">
                    <div id="tuso" class="tab-pane fade in active">
                        </c:if>
                        <c:if test="${announcementList.defaultTabOpen != 2}">
                        <div id="tuso" class="tab-pane fade">
                            </c:if>

                            <table class="table" style="margin-bottom:0%">
                                <tr class="border-table">
                                    <th class=" ann-title border-table table-giua truong-rstable-widthper5">STT</th>
                                    <th class=" ann-title border-table table-giua">Tiêu đề</th>
                                    <th class=" ann-title border-table table-giua truong-rstable-widthper15">Người gửi
                                    </th>
                                    <th class=" ann-title border-table table-giua truong-rstable-widthper8">Ngày gửi
                                    </th>
                                </tr>
                                <c:if test="${announcementList.publicListNumber == 0}">
                                    <tr>
                                        <td colspan="7"
                                            style="height: 100%;background-color: #ececec; line-height: 5.429;text-align: center;font-size: 265%">
                                            Không có dữ liệu
                                        </td>
                                    </tr>
                                </c:if>
                                <c:if test="${announcementList.publicListNumber != 0}">
                                    <c:forEach items="${announcementList.announcementFromStps}" var="item" varStatus="index">

                                        <tr>
                                            <td class="border-table align-giua  ">${index.index + 1}</td>
                                            <td class="border-table" style="color: black;width: 30%">
                                                <a href="${viewUrl}/${item.aid}">${item.title}</a>
                                                <c:if test="${item.importance_type == 2}">
                                                    <img style="height: 21px;float: top;padding-bottom: 7px;" src="<%=request.getContextPath()%>/static/image/flag.png">
                                                </c:if>
                                            </td>
                                            <td class="border-table"
                                                style="color: black;text-align: center">${item.sender_info}</td>
                                            <td class="border-table"
                                                style="color: black;">${TimeUtil.convertTimeStampToStringMinutes(item.entry_date_time)}</td>
                                        </tr>
                                    </c:forEach>
                                    <tr class="table-tr-xam">
                                        <th colspan="7"><div class="truong-padding-foot-table"> Tổng số <span class="truong-text-colorred"> ${announcementList.publicListNumber}</span> dữ
                                            liệu</div>
                                            <div class="align-phai">
                                                <c:if test="${announcementList.publicPage==1}">
                                                    <img
                                                            class="truong-pagging-icon"
                                                            src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                                    <img
                                                            class="truong-pagging-icon"
                                                            src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                                </c:if>
                                                <c:if test="${announcementList.publicPage!=1}">
                                                    <img onclick="firstPage(2)"
                                                         class="pagging-icon"
                                                         src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                                    <img onclick="previewPage(2)"
                                                         class="pagging-icon"
                                                         src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                                </c:if>
                                                    ${announcementList.publicPage}
                                                trên ${announcementList.publicTotalPage}
                                                <c:if test="${announcementList.publicPage==announcementList.publicTotalPage}">
                                                    <img
                                                            class="truong-pagging-icon"
                                                            src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                                                    <img
                                                            class="truong-pagging-icon"
                                                            src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                                                </c:if>
                                                <c:if test="${announcementList.publicPage!= announcementList.publicTotalPage}">
                                                    <img onclick="nextPage(2)"
                                                         class="pagging-icon"
                                                         src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                                                    <img onclick="lastPage(2)"
                                                         class="pagging-icon"
                                                         src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                                                </c:if>
                                            </div>
                                        </th>
                                    </tr>
                                </c:if>


                            </table>
                        </div>


                    </div>

                </div>
            </div>
        </div>
</div>
</form>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="panel-heading" style="background-color: #2e9ad0 ">
            <h5 class="panel-title truong-text-colorwhite ">
                Xóa thông tin thông báo

                <button type="button" class="close truong-button-xoa" data-dismiss="modal" style="margin-bottom: 5px"><img
                        src="<%=request.getContextPath()%>/static/image/close.png" class="control-label truong-imagexoa-fix"></button>
            </h5>

        </div>

            <div class="panel-body">
                <div class="truong-modal-padding" style="padding-bottom: 7%;">
                    <label class="col-md-12 control-label align-giua notification">Bạn có thực sự muốn xóa không ? </label>
                </div>
            </div>
            <div class="modal-footer">
                <div class="col-md-2 col-md-offset-4">
                    <input type="button" onclick="multiDelete()" data-toggle="modal" data-target="#myModal"
                           class="form-control luu" name="" value="Đồng ý">
                </div>
                <div class="col-md-2 ">
                    <input type="button" class="form-control huybo" name="" data-toggle="modal" data-target="#myModal"
                           value="Hủy bỏ">
                </div>

            </div>
        </div>

    </div>
</div>

<div class="modal fade" id="iModal" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="panel-heading" style="background-color: #2e9ad0 ">
                <h5 class="panel-title truong-text-colorwhite ">
                    Xóa thông tin thông báo

                    <button type="button" class="close truong-button-xoa" data-dismiss="modal" style="margin-bottom: 5px"><img
                            src="<%=request.getContextPath()%>/static/image/close.png" class="control-label truong-imagexoa-fix"></button>
                </h5>

            </div>
            <div class="panel-body">
                <div class="truong-modal-padding" style="padding-bottom: 7%;">
                    <label class="col-md-12 control-label align-giua notification">Bạn chưa chọn thông báo nào !</label>
                </div>
            </div>
            <div class="modal-footer">
                <div class="col-md-3 col-md-offset-3">
                </div>
                <div class="col-md-3 ">
                </div>
            </div>
        </div>

    </div>
</div>

<%--End Modal--%>
<script>
    function firstPage(tab){
        if(tab == 1){
            $('#internalPage').val(1);
        }
        if(tab == 2){
            $('#publicPage').val(1);
        }

        $( "#defaultTabOpen" ).val(tab);
        $( "#search-frm" ).submit();


    }
    function previewPage(tab){
        if(tab == 1){
            var daDuyetPage = $('#internalPage').val();
            $('#internalPage').val(parseInt(daDuyetPage) -1);
        }
        if(tab == 2){
            var hopDongPage = $('#publicPage').val();
            $('#publicPage').val(parseInt(hopDongPage) -1);
        }
        $( "#defaultTabOpen" ).val(tab);
        $( "#search-frm" ).submit();

    }
    function nextPage(tab){
        if(tab == 1){
            var daDuyetPage = $('#internalPage').val();
            $('#internalPage').val(parseInt(daDuyetPage) +1);
        }
        if(tab == 2){
            var hopDongPage = $('#publicPage').val();
            $('#publicPage').val(parseInt(hopDongPage) +1);
        }
        $( "#defaultTabOpen" ).val(tab);
        $( "#search-frm" ).submit();

    }
    function lastPage(tab){
        if(tab == 1){
            var lastPage1 = $('#internalTotalPage').val();
            $('#internalPage').val(lastPage1);
        }
        if(tab == 2){
            var lastPage2 = $('#publicTotalPage').val();
            $('#publicPage').val(lastPage2);
        }
        $( "#defaultTabOpen" ).val(tab);
        $( "#search-frm" ).submit();

    }
    var defaultTab = ${announcementList.defaultTabOpen};
    $(document).ready(function () {
        if (defaultTab == 1) activaTab('noibo');
        if (defaultTab == 2) activaTab('tuso');
    });

    function activaTab(tab) {
        $('.nav-tabs a[href="#' + tab + '"]').tab('show');
    }

    function clearText(){
        $('#title').val("");
        $('#unitRequest').hide();
        $('#typeAnnouncement').val("");
        $('#senderName').val("");
        $('#importantFilter').val("0");

    }
</script>

<script>
    function checkDelete() {
        var numChecked = $('#search-frm :input[type="checkbox"]:checked').length;
        if(numChecked <= 0){
            $("#iModal").modal("show");
        }else{
            $("#myModal").modal("show");
        }
    }

    function multiDelete() {
        var deleteData = "";

        $("input[name='cbChild']").each(function () {
            if ($(this).is(":checked") || $("#selectAll").is(":checked")) {
                deleteData = deleteData + $(this).val() + ",";
            }
        });

        $.ajax({
            type: "GET",
            url: '${multiDeleteUrl}',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: {
                deleteData: deleteData,
            },
            success: function (response) {
                window.location.href = "${listUrl}";
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function (e) {
                console.log("DONE");
            }
        })
    }
    $(document).ready(function () {
        $( "#timeType" ).change(function() {
            var timeType = $('#timeType option:selected').val();
            if(timeType == '05'){
                $('#dateTimeFilter').css("display","block");


            }
            if(timeType != '05'){
                $('#dateTimeFilter').css("display","none");

            }
        });
    });
    /* $('#typeAnnouncement').change(function(){
     alert($('#typeAnnouncement option:selected').val())
     });*/
    $(document).ready(function () {
        $( "#typeAnnouncement" ).change(function() {
            var radio = $('#typeAnnouncement option:selected').val();
            if(radio == 1){
                $('#unitRequest').show();


            }
            if(radio == ''){
                $('#unitRequest').hide();

            }

            if(radio =='0'){
                $('#unitRequest').hide();

            }
        });
    });




    $(function () {
        $('#fromDate').datepicker({
            format: "dd/mm/yyyy",
            forceParse : false,
            language: 'vi'
        }).on('changeDate', function (ev) {
            $(this).datepicker('hide');
        });
        $('#toDate').datepicker({
            format: "dd/mm/yyyy",
            forceParse : false,
            language: 'vi'
        }).on('changeDate', function (ev) {
            $(this).datepicker('hide');
        });
    });
    $(".modal-wide").on("show.bs.modal", function () {
        var height = $(window).height() - 200;
        $(this).find(".modal-body").css("max-height", height);
    });
</script>

<jsp:include page="/WEB-INF/pages/layout/footer.jsp"/>

<script>
    $(document).ready(function () {
        loadMenu();
    });
    function loadMenu() {
        $(".sidebar-nav > li > #thong-bao").addClass("father-menu");
    }
</script>