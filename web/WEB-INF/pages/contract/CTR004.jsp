<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp"/>
<%--
    Báo cáo theo nhóm hợp đồng
--%>
<spring:url value="/report/group" var="reportUrl"/>
<spring:url value="/report/group/export" var="exportURL"/>
<spring:url value="/report/group/detail" var="detailGroupURL"/>
<spring:url value="/report/loadContractTemplate" var="loadContractTemplate"/>
<spring:url value="/report/loadDetail" var="loadDetail" />

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Báo cáo theo nhóm</span>
</div>
<div class=" truong-form-chinhds panel-group">
    <form class="form-horizontal" action="${reportUrl}" method="get">
        <input type="hidden" name="notaryDateFromFilter" id="notaryDateFromFilter" value="${reportByGroupTotalList.notaryDateFromFilter}">
        <input type="hidden" name="notaryDateToFilter" id="notaryDateToFilter" value="${reportByGroupTotalList.notaryDateToFilter}">

        <div class="panel-body ">
            <div class="form-group ">
                <label class="col-md-2 control-label  label-bam-trai text-nowrap">Nhóm hợp đồng</label>
                <div class="col-md-7">
                    <select id="nhomHD" name="nhomHD" class="form-control truong-selectbox-padding select2"  onchange="loadContractTemplate()">
                        <option value="0">Tất cả</option>
                        <c:forEach items="${reportByGroupTotalList.contractKinds}" var="item">
                            <option value="${item.contract_kind_code}" ${reportByGroupTotalList.nhomHD==item.contract_kind_code?"selected":""}>${item.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label  label-bam-trai">Tên hợp đồng</label>
                <div class="col-md-7">
                    <select id="tenHD" name="tenHD" class="form-control truong-selectbox-padding select2">
                        <option value="0">Tất cả</option>
                        <c:forEach items="${reportByGroupTotalList.contractTemplates}" var="item">
                            <option value="${item.code_template}" ${reportByGroupTotalList.tenHD==item.code_template?"selected":""}>${item.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label  label-bam-trai text-nowrap">Ngày công chứng</label>
                <div class="col-md-10">
                    <div class="row">

                        <div class="col-md-3 truong-padding-deleteright">
                            <select class="form-control truong-selectbox-padding select2" name="timeType" id="timeType" onchange="timeTypeChange()">
                                <option value="01" ${reportByGroupTotalList.timeType.equals('01')?"selected":""}>Trong ngày hôm nay</option>
                                <option value="02" ${reportByGroupTotalList.timeType.equals('02')?"selected":""}>Trong tuần này</option>
                                <option value="03" ${reportByGroupTotalList.timeType.equals('03')?"selected":""}>Trong tháng này</option>
                                <option value="04" ${reportByGroupTotalList.timeType.equals('04')?"selected":""}>Trong năm nay</option>
                                <option value="05" ${reportByGroupTotalList.timeType.equals('05')?"selected":""}>Trong khoảng thời gian</option>
                            </select>
                        </div>
                        <div class="col-md-9">
                            <div class="row">
                                <div ${reportByGroupTotalList.timeType.equals('05')?"":"style='display: none;'"} id="dateTimeFilter">
                                    <div class="col-md-9" id="to-from">
                                        <div class="row">
                                            <label class="col-md-3 control-label  label-phai required">Từ ngày</label>
                                            <div class="col-md-3 truong-padding-delete">
                                                <input type="text" value="${reportByGroupTotalList.fromDate}" class="form-control date-time" name="fromDate" id="fromDate" >
                                                <div style="margin-top: 5px;color: red;" class="error_tooltip">${validate_msg_from_date}</div>
                                            </div>
                                            <label class="col-md-3 control-label  label-bam-phai">Đến ngày</label>
                                            <div class="col-md-3 truong-padding-delete">
                                                <input type="text" value="${reportByGroupTotalList.toDate}" class="form-control date-time" name="toDate" id="toDate">
                                                <div style="margin-top: 5px;color: red;" class="error_tooltip">${validate_msg_to_date}</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-3" id="bt-thongke">
                                    <input type="submit" value="Thống kê" class="form-control luu">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="" id="bt-thongke-small" style="text-align: center;display: none;">
                <input type="submit" value="Thống kê" class="btn btn-primary btn-stp" style="width: 120px !important;">
            </div>
            <div class="panel-body">
                <div class="report-contact" class="col-md-12">
                    <p id="report-title">BÁO CÁO THỐNG KÊ HỢP ĐỒNG CÔNG CHỨNG</p>
                    <p>Từ ngày ${reportByGroupTotalList.printFromDate} đến ngày ${reportByGroupTotalList.printToDate}</p>
                </div>
            </div>
        </div>
        <div class="panel-body truong-margin-footer0px">
            <div class="truong-file-bao-cao export-button">
                <c:if test="${reportByGroupTotalList.total > 0}">
                    <a href="${exportURL}" class="label-bam-phai truong-small-linkbt"><input type="button" class="form-control luu" value="Tải file báo cáo"></a>
                </c:if>

            </div>
            <table class="table" style="margin-bottom:0%">

                <tr class="border-table">
                    <th class=" ann-title border-table table-giua">STT</th>
                    <th class=" ann-title border-table table-giua">Nhóm hợp đồng</th>
                    <th class=" ann-title border-table table-giua">Tên hợp đồng</th>
                    <th class=" ann-title border-table table-giua">Tổng cộng</th>

                </tr>

                <c:if test="${reportByGroupTotalList.total < 1}">
                    <tr>
                        <td colspan="7"
                            style="height: 100%;background-color: #ececec; line-height: 5.429;text-align: center;font-size: 265%">
                            Chưa có dữ liệu
                        </td>
                    </tr>
                </c:if>
                <c:if test="${reportByGroupTotalList.total > 0}">
                    <c:forEach items="${reportByGroupTotalList.reportByGroupTotals}" var="item" varStatus="i"  >
                        <tr>
                            <td class="border-table align-giua  ">${i.index +1}</td>
                            <td class="border-table truong-text-verticel" style="color: black;"><a class="kind-name" href="${detailGroupURL}/${item.kind_id}/1">${item.kind_name}</a></td>
                            <%--kind-id nay la contract_kind_code--%>
                            <input type="hidden" class="kind-code" value="${item.kind_id}">
                            <td class="border-table truong-text-verticel template-name" style="color: black;">${item.template_name}</td>
                            <input type="hidden" class="code-template" value="${item.code_template}">
                            <td class="border-table align-giua" style="color: black;">${item.template_number}</td>
                        </tr>
                    </c:forEach>
                    <tr class="table-tr-xam">
                        <th colspan="7"><div class="truong-padding-foot-table"> Tổng số <span class="truong-text-colorred">${reportByGroupTotalList.total}</span> dữ liệu </div>

                        </th>
                    </tr>
                </c:if>

            </table>
        </div>




    </form>

</div>
<script>
    $(window).on('load resize',function(){
        var win = $(this);
        var timeType = $('#timeType').val();
        if(timeType== "05") {
            if (win.width() < 1114) {
                $('#to-from').removeClass('col-md-9');
                $('#to-from').addClass('col-md-12');
                $('#bt-thongke').css('display', 'none');
                $('#bt-thongke-small').css('display', 'block');
                $('#to-from').addClass('truong-padding-right30');

            } else {
                $('#to-from').removeClass('col-md-12');
                $('#to-from').addClass('col-md-9');
                $('#bt-thongke').css('display', 'block');
                $('#bt-thongke-small').css('display', 'none');
                $('#to-from').removeClass('truong-padding-right30');

            }
        }

    });
</script>
<script>
    function timeTypeChange() {
        var win = $(this);
        var timeType = $('#timeType').val();
        if (timeType == "05") {
            if (win.width() < 1114) {
                $('#to-from').removeClass('col-md-9');
                $('#to-from').addClass('col-md-12');
                $('#bt-thongke').css('display', 'none');
                $('#bt-thongke-small').css('display', 'block');
                $('#to-from').addClass('truong-padding-right30');

            } else {
                $('#to-from').removeClass('col-md-12');
                $('#to-from').addClass('col-md-9');
                $('#bt-thongke').css('display', 'block');
                $('#bt-thongke-small').css('display', 'none');
                $('#to-from').removeClass('truong-padding-right30');

            }
        }
        else {
            $('#to-from').removeClass('col-md-12');
            $('#to-from').addClass('col-md-9');
            $('#bt-thongke').css('display', 'block');
            $('#bt-thongke-small').css('display', 'none');
            $('#to-from').removeClass('truong-padding-right30');
        }
    };
</script>
<script>
    $(".kind-name").on("click", function (e) {
        e.preventDefault();
        var txtTempName = $(e.target).parent().parent().find(".template-name").text();
        var txtKindCode = $(e.target).parent().parent().find(".kind-code").val();
        var notaryDateFromFilter = $('#notaryDateFromFilter').val();
        var notaryDateToFilter = $('#notaryDateToFilter').val();
        var txtTemplateCode = $(e.target).parent().parent().find(".code-template").val();


        var txt = txtTempName+"," +txtKindCode+","+ notaryDateFromFilter+ ","+notaryDateToFilter+","+txtTemplateCode ;
        var url = location.protocol + '//' + location.host +'<%=request.getContextPath()%>';


        $.ajax({
            type: "GET",
            url: '${loadDetail}',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: {
                loadDetail1: txt,
            },
            success: function (response) {

                window.location= url+ '/report/group/detail/contract/1';


            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function (e) {
                console.log("DONE");
            }
        })



    });
</script>
<%--<script>
    $(".kind-name").on("click", function (e) {
        /*e.preventDefault();*/
        var txt = $(e.target).parent().parent().find(".template-name").text();
        alert(txt);

        $('#tenHDChiTiet').val(txt);
        var truongCheck = $('#tenHDChiTiet').val();
        alert("check hd chi tiet" + truongCheck);
        /*window.location.replace("report/group/detail/contract");*/


    });
</script>--%>
<script type="text/javascript">
    $(".select2").select2();
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
        var userPage = $('#userPage').val();
        $('#page').val(userPage);
        $("#search-frm").submit();
    }
</script>
<script>
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
    function loadContractTemplate() {
        var loadContractTemplate=  document.getElementById("nhomHD").value;
        $.ajax({
            type: "GET",
            url: '${loadContractTemplate}',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: {
                contractKind: loadContractTemplate,
            },
            success: function (response) {
                var stringVpcc = response.result;
                var htmlContent1 = '<select name="tenHD" class="form-control truong-selectbox-padding"><option value="0">Tất cả</option>';
                var listVpcc = stringVpcc.split(";")
                for( var i =0;i<listVpcc.length-1;i++){
                    var nameVPCC = listVpcc[i];
                    var arr = nameVPCC.split(",")
                    var idVPCC = arr[0];
                    var tenVPCC = arr[1];

                    htmlContent1 += '<option value ="'+idVPCC+'">'+tenVPCC+'</option>'
                }
                htmlContent1+='</select>'
                $('#tenHD').html(htmlContent1);


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

<jsp:include page="/WEB-INF/pages/layout/footer.jsp"/>
<script>
    $(document).ready(function () {
        var parentItem = $("#bao-cao-thong-ke");
        $(parentItem).click();
        $("#bao-cao-theo-nhom").addClass("child-menu");
        $(".date-time").keypress(function(key) {
            if(key.charCode < 47 || key.charCode > 57) return false;
        });
        $(".date-time").bind("cut copy paste",function(e) {
            e.preventDefault();
        });
    });
</script>

