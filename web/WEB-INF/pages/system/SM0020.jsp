<%@ page import="com.vn.osp.common.util.ValidationPool" %>
<%@ page import="com.vn.osp.common.global.Constants" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />

<%--
    Sao luu dữ liệu từ Sở Tư Pháp
--%>
<spring:url value="/system/backup-from-stp" var="backupUrl" />
<spring:url value="/system/backup-from-stp-handle" var="backupHandleUrl" />
<spring:url value="/system/backup-view" var="backUrl" />

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Sao lưu dữ liệu từ Sở Tư Pháp</span>
</div>
<div class="truong-form-chinhbtt form-horizontal">
    <div id="uchi-status">
        <div style="display: none;" class="status-error"><img class="status-img" src="<%=request.getContextPath()%>/static/image/error.png">Sao lưu dữ liệu từ Sở Tư Pháp không thành công</div>
        <div style="display: none;" class="status-success"><img class="status-img" src="<%=request.getContextPath()%>/static/image/success.png">Sao lưu dữ liệu từ Sở Tư Pháp thành công</div>
    </div>
    <div class="progress" style="display: none;margin-top: 20px">
        <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width:0%">
            0%
        </div>
    </div>
    <div class="form-group" style="margin-top: 20px;">

        <div class="col-md-10">
            <div class="row">
                <div class="col-md-9">
                    <div class="row">
                        <div id="dateTimeFilter">
                            <div class="col-md-9">
                                <div class="row">
                                    <label class="col-md-3 control-label  label-bam-trai">Từ ngày</label>
                                    <div class="col-md-3 truong-padding-delete">
                                        <input type="text"  class="form-control" name="fromDate" id="fromDate" >
                                    </div>
                                    <label class="col-md-3 control-label  label-bam-trai">Đến ngày</label>
                                    <div class="col-md-3 truong-padding-delete">
                                        <input type="text"  class="form-control" name="toDate" id="toDate">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%
                            if(ValidationPool.checkRoleDetail(request,"07", Constants.AUTHORITY_THEM)){
                        %>
                        <div class="col-md-3">
                            <input type="button" onclick="backup()" value="Sao lưu" class="form-control luu">
                        </div>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<script>
    var timer;
    var isRunning = false;
    function backup(){
        var fromDate = $('#fromDate').val();
        var toDate = $('#toDate').val();
        if(isRunning == true) return;
        $.ajax({
            type: "GET",
            url: '${backupUrl}',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data:{
                fromDate: fromDate,
                toDate: toDate
            },
            success: function (response) {
                console.log("1="+response);
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function (e) {
                console.log("DONE");
            }
        })

        timer = setInterval(backupHandle, 1000);
    }
    function backupHandle(){
        isRunning = true;
        $.ajax({
            type: "GET",
            url: '${backupHandleUrl}',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',

            success: function (response) {
                console.log("2="+response);
                $(".progress-bar").attr("aria-valuenow",response);
                $(".progress-bar").text(response+"%");
                $(".progress-bar").css("width",response+"%");
                $('.progress').show(500);
                $('.status-success').hide();
                if(response == "100"){
                    window.clearInterval(timer);
                    $('.progress').hide(500);
                    $('.status-success').show();
                    isRunning = false;
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function (e) {
                console.log("DONE");
            }
        })
    }

</script>
<script>

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
</script>

<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />

<script>
    $(document).ready(function () {
        var parentItem = $("#quan-tri-he-thong");
        $(parentItem).click();
        $("#system-configuration").addClass("child-menu");
    });
</script>