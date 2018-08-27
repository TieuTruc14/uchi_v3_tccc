<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp"/>
<%--
    Báo cáo theo thông tu 20
--%>
<spring:url value="/report/by-tt20" var="reportUrl"/>
<spring:url value="/report/export-tt20" var="exportURL"/>


<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Báo cáo theo thông tư 20</span>
</div>
<div class=" truong-form-chinhds panel-group">
    <form class="form-horizontal" action="${reportUrl}" method="get">
        <input type="hidden" name="notaryDateFromFilter" id="notaryDateFromFilter" value="${reportByTT20List.notaryDateFromFilter}">
        <input type="hidden" name="notaryDateToFilter" id="notaryDateToFilter" value="${reportByTT20List.notaryDateToFilter}">
            <div class="panel-body ">
                <div class="form-group">
                    <label class="col-md-2 control-label  label-bam-trai text-nowrap">Ngày công chứng</label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-3 truong-padding-deleteright">
                                <select class="form-control truong-selectbox-padding select2" name="timeType" id="timeType" onchange="timeTypeChange()">
                                    <option value="01" ${reportByTT20List.timeType.equals('01')?"selected":""}>Trong ngày hôm nay</option>
                                    <option value="02" ${reportByTT20List.timeType.equals('02')?"selected":""}>Trong tuần này</option>
                                    <option value="03" ${reportByTT20List.timeType.equals('03')?"selected":""}>Trong tháng này</option>
                                    <option value="04" ${reportByTT20List.timeType.equals('04')?"selected":""}>Trong năm nay</option>
                                    <option value="05" ${reportByTT20List.timeType.equals('05')?"selected":""}>Trong khoảng thời gian</option>
                                </select>
                            </div>
                            <div class="col-md-9">
                                <div class="row">
                                    <div ${reportByTT20List.timeType.equals('05')?"":"style='display: none;'"} id="dateTimeFilter">
                                        <div class="col-md-9" id="to-from">
                                            <div class="row">
                                                <label class="col-md-3 control-label  label-bam-trai">Từ ngày</label>
                                                <div class="col-md-3 truong-padding-delete">
                                                    <input type="text" value="${reportByTT20List.fromDate}" class="form-control date-time" name="fromDate" id="fromDate" >
                                                    <div style="margin-top: 5px;color: red;" class="error_tooltip">${validate_msg_from_date}</div>
                                                </div>
                                                <label class="col-md-3 control-label  label-bam-trai">Đến ngày</label>
                                                <div class="col-md-3 truong-padding-delete">
                                                    <input type="text" value="${reportByTT20List.toDate}" class="form-control date-time" name="toDate" id="toDate">
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
                <p id="report-title">SỐ CÔNG CHỨNG VIÊN VÀ TÌNH HÌNH HOẠT ĐỘNG CÔNG CHỨNG</p>
                <p>Từ ngày ${reportByTT20List.printFromDate} đến ngày ${reportByTT20List.printToDate}</p>
            </div>
            </div>
            </div>
                <div class="panel-body truong-margin-footer0px">
                    <div class="truong-file-bao-cao export-button">

                            <a href="${exportURL}" class="label-bam-phai truong-small-linkbt"><input type="button" class="form-control luu" value="Tải file báo cáo"></a>


                    </div>
                    <div>
                        <div>
                            <table class="tbl-list truong-border-table col-md-12">
                                <tr class="ann-title truong-border-table table-giua">
                                    <th rowspan="4" style="width:84px;" class="ann-title truong-border-table table-giua ">
                                        Số công chứng viên
                                        <em>(Người)</em>
                                    </th>
                                    <th colspan="6" style="width:655px;" class="ann-title truong-border-table table-giua">
                                        Tổng số việc đã công chứng <em>(Việc)</em>
                                    </th>
                                    <th rowspan="4" style="width:84px;" class="ann-title truong-border-table table-giua">
                                        Tổng số phí công chứng
                                        &nbsp;
                                        <em>(VND)</em>
                                    </th>
                                    <th rowspan="4" style="width:88px;" class="ann-title truong-border-table table-giua">
                                        Tổng số tiền nộp v&agrave;o ng&acirc;n s&aacute;ch Nh&agrave; nước hoặc nộp thuế
                                        <em>(VND)</em>
                                    </th>
                                </tr>
                                <tr class="border-table">
                                    <th rowspan="3" style="width:60px;" class="ann-title truong-border-table table-giua">
                                        Tổng số
                                    </th>
                                    <th colspan="5" style="width:595px;" class="ann-title truong-border-table table-giua">
                                        Chia ra
                                    </th>
                                </tr>
                                <tr class="border-table">
                                    <th colspan="2" style="width:316px;" class="ann-title truong-border-table table-giua">
                                        C&ocirc;ng chứng hợp đồng chuyển quyền sở hữu, quyền sử dụng t&agrave;i sản
                                    </th>
                                    <th rowspan="2" style="width:96px;:48px;" class="ann-title truong-border-table table-giua">
                                        C&ocirc;ng chứng hợp đồng bảo đảm thực hiện nghĩa vụ d&acirc;n sự
                                    </th>
                                    <th rowspan="2" style="width:92px;" class="ann-title truong-border-table table-giua">
                                        C&ocirc;ng chứng c&aacute;c giao dịch về thừa kế
                                    </th>
                                    <th rowspan="2" style="width:91px;" class="ann-title truong-border-table table-giua">
                                        C&aacute;c loại việc kh&aacute;c
                                    </th>
                                </tr>
                                <tr class="border-table">
                                    <th style="width:168px;" class="ann-title truong-border-table table-giua">
                                        Bằng quyền sử dụng đất, quyền sử dụng đất v&agrave; t&agrave;i sản gắn liền với đất
                                    </th>
                                    <th style="width:148px;" class="ann-title truong-border-table table-giua">
                                        Bằng t&agrave;i sản kh&aacute;c
                                    </th>
                                </tr>

                                <tr>
                                    <td class="ann-title truong-border-table table-giua">(1)</td>
                                    <td class="ann-title truong-border-table table-giua">(2)</td>
                                    <td class="ann-title truong-border-table table-giua">(3)</td>
                                    <td class="ann-title truong-border-table table-giua">(4)</td>
                                    <td class="ann-title truong-border-table table-giua">(5)</td>
                                    <td class="ann-title truong-border-table table-giua">(6)</td>
                                    <td class="ann-title truong-border-table table-giua">(7)</td>
                                    <td class="ann-title truong-border-table table-giua">(8)</td>
                                    <td class="ann-title truong-border-table table-giua">(9)</td>
                                </tr>
                                <tr class="tbl-row0">
                                    <td align="center" class="truong-border-table">${reportByTT20List.numberOfNotaryPerson}</td>
                                    <td align="center" class="truong-border-table">${reportByTT20List.numberOfTotalContract}</td>
                                    <td align="center" class="truong-border-table">${reportByTT20List.numberOfContractLand}</td>
                                    <td align="center" class="truong-border-table">${reportByTT20List.numberOfContractOther}</td>
                                    <td align="center" class="truong-border-table">${reportByTT20List.numberOfContractDanSu}</td>
                                    <td align="center" class="truong-border-table">${reportByTT20List.numberOfThuaKe}</td>
                                    <td align="center" class="truong-border-table">${reportByTT20List.numberOfOther}</td>
                                    <td align="center" class="truong-border-table">${reportByTT20List.tongPhiCongChung} (VND)</td>
                                    <td align="center" class="truong-border-table"></td>
                                </tr>
                            </table>
                        </div>
                    </div>
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
            $('.date-time').val('');
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
    </script>

    <jsp:include page="/WEB-INF/pages/layout/footer.jsp"/>
    <script>
        $(document).ready(function () {
            var parentItem = $("#bao-cao-thong-ke");
            $(parentItem).click();
            $("#bao-cao-theo-tt20").addClass("child-menu");
            $(".date-time").keypress(function(key) {
                if(key.charCode < 47 || key.charCode > 57) return false;
            });
            $(".date-time").bind("cut copy paste",function(e) {
                e.preventDefault();
            });
        });
    </script>

