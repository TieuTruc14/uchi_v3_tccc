<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/checkbox.js"></script>

<%--
    Thống kê tổng hợp
--%>

<%--<spring:url value="/report/contract-stastics" var="contractStasticsUrl"/>--%>
<spring:url value="/report/general-stastics" var="contractStasticsUrl"/>
<spring:url value="/report/export-general-stastics" var="exportUrl"/>

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Thống kê tổng hợp</span>
</div>

<div class="col-md-12">
    <div class="panel-group" id="accordion">
        <form class="form-horizontal" action="${contractStasticsUrl}" method="get">
            <div class="panel-body">
                <div class="form-group">
                    <label class="col-md-2 control-label  label-bam-trai text-nowrap">Ngày công chứng</label>
                    <div class="col-md-10">
                        <div class="row">

                            <div class="col-md-3 truong-padding-deleteright">
                                <select class="form-control truong-selectbox-padding select2" name="timeType" id="timeType" onchange="timeTypeChange()">
                                    <option value="01" ${contractStasticsWrapper.timeType.equals('01')?"selected":""}>Trong ngày hôm nay</option>
                                    <option value="02" ${contractStasticsWrapper.timeType.equals('02')?"selected":""}>Trong tuần này</option>
                                    <option value="03" ${contractStasticsWrapper.timeType.equals('03')?"selected":""}>Trong tháng này</option>
                                    <option value="04" ${contractStasticsWrapper.timeType.equals('04')?"selected":""}>Trong năm nay</option>
                                    <option value="05" ${contractStasticsWrapper.timeType.equals('05')?"selected":""}>Trong khoảng thời gian</option>
                                </select>
                            </div>
                            <div class="col-md-9">
                                <div class="row">
                                    <div ${contractStasticsWrapper.timeType.equals('05')?"":"style='display: none;'"} id="dateTimeFilter">
                                        <div class="col-md-9" id="to-from">
                                            <div class="row">
                                                <label class="col-md-3 control-label  label-bam-phai">Từ ngày</label>
                                                <div class="col-md-3 truong-padding-delete">
                                                    <input type="text" value="${contractStasticsWrapper.fromDate}" class="form-control date-time" name="fromDate" id="fromDate" >
                                                    <div style="margin-top: 5px;color: red;" class="error_tooltip">${validate_msg_from_date}</div>
                                                </div>
                                                <label class="col-md-3 control-label  label-bam-phai">Đến ngày</label>
                                                <div class="col-md-3 truong-padding-delete">
                                                    <input type="text" value="${contractStasticsWrapper.toDate}" class="form-control date-time" name="toDate" id="toDate">
                                                    <div style="margin-top: 5px;color: red;" class="error_tooltip">${validate_msg_to_date}</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-3" id="bt-thongke"  >
                                        <input type="submit" value="Thống kê"  class="form-control luu ">
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
                    <p id="report-title">THỐNG KÊ SỐ LƯỢNG HỢP ĐỒNG ĐÃ CÔNG CHỨNG</p>
                    <p>Từ ngày ${contractStasticsWrapper.printFromDate} đến ngày ${contractStasticsWrapper.printToDate}</p>
                </div>
                </div>
                <%--<div class="form-group">
                    <label class="col-md-6 col-md-offset-3 control-label  label-bam-trai"> THỐNG KÊ SỐ LƯỢNG HỢP ĐỒNG ĐÃ CÔNG CHỨNG</label>
                </div>
                <div class="form-group">
                    <label class="col-md-3 col-md-offset-3 control-label  label-bam-trai"><p>Từ ngày ${contractStasticsWrapper.fromDate} đến ngày ${contractStasticsWrapper.toDate}</p></label>
                </div>--%>


                <div class="form-group">
                    <label class="col-md-12 control-label  label-bam-trai"><p>Tổng số hợp đồng văn phòng đã công chứng: <span style="color: red">${contractStasticsWrapper.totalContractByNotary}</span> </p></label>
                </div>

                <div class="form-group">
                    <label class="col-md-12  control-label  label-bam-trai"><p>Tổng số hợp đồng lỗi: <span style="color: red"> ${contractStasticsWrapper.totalErrorContractByNotary} </span> </p></label>
                </div>
            </div>

        </form>
        <div class="panel-body truong-margin-footer0px">

            <%--
                    <div class=" ">
                        <c:if test="${contractErrorWrapper.total >0}">
                            <a href="${exportUrl}"><input type="button" class="form-control luu report-button" value="Tải file báo cáo"></a>
                        </c:if>
                    </div>
            --%>
                <div class="form-group">
                    <label class="col-md-12  control-label  label-bam-trai"><p> I) Số lượng hợp đồng theo công chứng viên   </p></label>
                </div>
                <table class="table" style="margin-bottom:0%">

                <tr class="border-table">
                    <th class=" ann-title border-table table-giua truong-rstable-widthper15">STT</th>
                    <th class=" ann-title border-table table-giua truong-rstable-widthper15">Tên công chứng viên</th>
                    <th class=" ann-title border-table table-giua truong-rstable-widthper15">Số hợp đồng đã công chứng</th>
                    <th class=" ann-title border-table table-giua truong-rstable-widthper12">Số hợp đồng lỗi</th>
                </tr>
                    <c:forEach items="${contractStasticsWrapper.contractStasticsNotary}" var="item" varStatus="index">
                        <tr>
                            <td class="border-table truong-text-verticel">${index.index+1}</td>
                        <td class="border-table truong-text-verticel">${item.family_name} ${item.first_name}</td>
                        <td class="border-table truong-text-verticel">${item.number_of_contract}</td>
                        <td class="border-table truong-text-verticel">${item.number_of_error_contract} </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <th colspan="2"> Tổng số </th>
                        <td style="font-weight: bold">${contractStasticsWrapper.totalContractByNotary}</td>
                        <td style="font-weight: bold">${contractStasticsWrapper.totalErrorContractByNotary}</td>
                    </tr>
    </table>

                <div class="form-group">
                    <label class="col-md-12  control-label  label-bam-trai"><p>  II) Số lượng hợp đồng theo chuyên viên soạn thảo    </p></label>
                </div>
                <table class="table" style="margin-bottom:0%">

                    <tr class="border-table">
                        <th class=" ann-title border-table table-giua truong-rstable-widthper15">STT</th>
                        <th class=" ann-title border-table table-giua truong-rstable-widthper15">Tên chuyên viên soạn thảo</th>
                        <th class=" ann-title border-table table-giua truong-rstable-widthper15">Số hợp đồng đã soạn thảo</th>
                        <th class=" ann-title border-table table-giua truong-rstable-widthper12">Số hợp đồng lỗi</th>
                    </tr>
                    <c:forEach items="${contractStasticsWrapper.contractStasticsDrafter}" var="item" varStatus="index">
                        <tr>
                        <td class="border-table truong-text-verticel">${index.index+1}</td>
                        <td class="border-table truong-text-verticel">${item.family_name} ${item.first_name}</td>
                        <td class="border-table truong-text-verticel">${item.number_of_contract}</td>
                        <td class="border-table truong-text-verticel">${item.number_of_error_contract} </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <th colspan="2"> Tổng số </th>
                        <td style="font-weight: bold">${contractStasticsWrapper.totalContractByDrafter}</td>
                        <td style="font-weight: bold">${contractStasticsWrapper.totalErrorContractByDrafter}</td>
                    </tr>

                </table>

                <div class="form-group">
                    <label class="col-md-12  control-label  label-bam-trai"><p>  III) Số lượng hợp đồng theo ngân hàng    </p></label>
                </div>
                <table class="table" style="margin-bottom:0%">

                    <tr class="border-table">
                        <th class=" ann-title border-table table-giua truong-rstable-widthper15">STT</th>
                        <th class=" ann-title border-table table-giua truong-rstable-widthper15">Tên ngân hàng</th>
                        <th class=" ann-title border-table table-giua truong-rstable-widthper15">Số hợp đồng đã giới thiệu</th>
                    </tr>
                    <c:forEach items="${contractStasticsWrapper.contractStasticsBanks}" var="item" varStatus="index">
                        <tr>
                        <td class="border-table truong-text-verticel">${index.index+1}</td>
                        <td class="border-table truong-text-verticel">${item.bank_name}</td>
                        <td class="border-table truong-text-verticel">${item.number_of_contract}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <th colspan="2"> Tổng số </th>
                         <td style="font-weight: bold">${contractStasticsWrapper.totalContractBank}</td>
                    </tr>
                </table>


        </div>
        <div class=" ">
                <a href="${exportUrl}"><input type="button" class="form-control luu report-button" value="Tải file báo cáo"></a>

        </div>

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

</script>
<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />
<script>
    $(document).ready(function () {
        var parentItem = $("#bao-cao-thong-ke");
        $(parentItem).click();
        $("#tk-tong-hop").addClass("child-menu");
        $(".date-time").keypress(function(key) {
            if(key.charCode < 47 || key.charCode > 57) return false;
        });
        $(".date-time").bind("cut copy paste",function(e) {
            e.preventDefault();
        });
    });
</script>


