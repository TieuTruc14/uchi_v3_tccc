<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.vn.osp.common.util.SystemMessageProperties" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp"/>
<%--
    Báo cáo theo ngân hàng
--%>
<spring:url value="/report/group-bank" var="reportBankUrl"/>
<spring:url value="/report/export-bank" var="exportUrl"/>


<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Báo cáo theo ngân hàng</span>
</div>
<div class="truong-form-chinhds panel-group">
    <form class="form-horizontal" id="search-frm" action="${reportBankUrl}" method="get">

        <input type="hidden" name="listNumber" value="${reportByBankForm.total}">
        <input type="hidden" name="totalPage" id="totalPage" value="${reportByBankForm.totalPage}">
        <input type="hidden" name="page" id="page" value="${reportByBankForm.page}">


        <div class="panel-body ">
            <div class="form-group">
                <label class="col-md-2 control-label  label-bam-trai">Ngân hàng</label>
                <div class="col-md-10">
                    <div class="row">
                        <div class="col-md-3 truong-padding-deleteright">
                            <select class="form-control truong-selectbox-padding select2" name="bankFilter"
                                    id="notaryOfficeAuthentication">
                                <option value="">Tất cả</option>
                                <c:forEach items="${reportByBankForm.bankList}" var="item">
                                    <option value="${item.code}" ${item.code.equals(reportByBankForm.bankFilter)?"selected":""}> ${item.code} - ${item.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label  label-bam-trai text-nowrap">Ngày công chứng</label>
                <div class="col-md-10">
                    <div class="row">
                        <div class="col-md-3 truong-padding-deleteright">

                            <select class="form-control truong-selectbox-padding select2" name="timeType" id="timeType"
                                    onchange="timeTypeChange()">
                                <option value="01" ${reportByBankForm.timeType.equals('01')?"selected":""}>Trong ngày
                                    hôm nay
                                </option>
                                <option value="02" ${reportByBankForm.timeType.equals('02')?"selected":""}>Trong tuần
                                    này
                                </option>
                                <option value="03" ${reportByBankForm.timeType.equals('03')?"selected":""}>Trong tháng
                                    này
                                </option>
                                <option value="04" ${reportByBankForm.timeType.equals('04')?"selected":""}>Trong năm
                                    nay
                                </option>
                                <option value="05" ${reportByBankForm.timeType.equals('05')?"selected":""}>Trong khoảng
                                    thời gian
                                </option>
                            </select>

                        </div>
                        <div class="col-md-9">
                            <div class="row">
                                <div ${reportByBankForm.timeType.equals('05')?"":"style='display: none;'"}
                                        id="dateTimeFilter">
                                    <div class="col-md-9" id="to-from">
                                        <div class="row">
                                            <label class="col-md-3 control-label  label-bam-phai required">Từ
                                                ngày</label>
                                            <div class="col-md-3">
                                                <input style="min-width: 100px;" type="text"
                                                       value="${reportByBankForm.fromDate}"
                                                       class="form-control date-time fromDate" name="fromDate"
                                                       id="fromDate">
                                                <div style="margin-top: 5px;color: red;"
                                                     class="error_tooltip">${validate_msg_from_date}</div>
                                            </div>
                                            <label class="col-md-3 control-label  label-bam-phai">Đến ngày</label>
                                            <div class="col-md-3">
                                                <input style="min-width: 100px;" type="text"
                                                       value="${reportByBankForm.toDate}"
                                                       class="form-control date-time toDate" name="toDate" id="toDate">
                                                <div style="margin-top: 5px;color: red;"
                                                     class="error_tooltip">${validate_msg_to_date}</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-3" id="bt-thongke">
                                    <input type="submit" value="Thống kê" class="form-control luu ">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <div class="" id="bt-thongke-small" style="text-align: center;display: none;">
                <input type="submit" value="Thống kê" class="btn btn-primary btn-stp" style="width: 120px !important;">
            </div>


        </div>

    </form>


    <div class="panel-body truong-margin-footer0px">
        <div class=" ">
            <c:if test="${reportByBankForm.total >0}">
                <a href="${exportUrl}"><input type="button" class="form-control luu report-button truong-small-linkbt"
                                              value="Tải file báo cáo"></a>
            </c:if>
        </div>
        <table class="table" style="margin-bottom:0%">

            <tr class="border-table">
                <th class=" ann-title border-table table-giua truong-rstable-widthper12">Ngân hàng</th>
                <th class=" ann-title border-table table-giua truong-rstable-widthper12">Số hợp đồng</th>
                <th class=" ann-title border-table table-giua truong-rstable-widthper12">Ngày công chứng</th>
                <th class=" ann-title border-table table-giua truong-rstable-widthper12">Tên hợp đồng</th>
                <th class=" ann-title border-table table-giua truong-rstable-widthper15">Bên liên quan</th>
                <th class=" ann-title border-table table-giua">Nội dung</th>
                <th class=" ann-title border-table table-giua truong-rstable-widthper15">Công chứng viên</th>

            </tr>
            <c:if test="${reportByBankForm.total == 0}">
                <tr>
                    <td colspan="7"
                        style="height: 100%;background-color: #ececec; line-height: 5.429;text-align: center;font-size: 265%">
                        Chưa có dữ liệu
                    </td>
                </tr>
            </c:if>
            <c:if test="${reportByBankForm.total != 0}">

                <c:forEach items="${reportByBankForm.reportByBankDetails}" var="item" varStatus="index">

                    <tr>
                        <td class="border-table align-giua  ">${item.name}</td>
                        <td class="border-table  align-giua ">${item.contract_number}</td>
                        <td class="border-table align-giua  ">${item.notary_date}</td>
                        <td class="border-table align-giua  ">${item.contract_name}</td>

                        <td class="border-table truong-text-verticel" style="color: black;">
                            <c:if test="${item.relation_object.length() <= 200}">

                            ${item.relation_object.replace('\\n','<br>')}
                        </c:if>
                            <c:if test="${item.relation_object.length() > 200}">${item.relation_object.replace('\\n','<br>').substring(0,200)}
                                <img data-html="true" data-toggle="popover" data-trigger="hover" data-placement="top"
                                     data-content="<div class='title-green'>Chi tiết hợp đồng:</div>${item.relation_object.replace('\\n','<br>')}"
                                     src="<%=request.getContextPath()%>/static/image/xemthem.png"></c:if>
                        </td>
                        <td class="border-table truong-text-verticel"
                            style="color: black;"><c:if
                                test="${item.transaction_content.length() <= 200}">${item.transaction_content}</c:if>
                            <c:if test="${item.transaction_content.length() > 200}">${item.transaction_content.replace('\\n','<br>').substring(0,200)}
                            <img data-html="true" data-toggle="popover" data-trigger="hover" data-placement="top"
                                 data-content="<div class='title-green'>Chi tiết hợp đồng:</div>${item.transaction_content.replace('\\n','<br>').replaceAll("Nội dung hợp đồng: <br>","").replaceAll("<br>Thông tin tài sản:<br>","<div class='title-green'>Thông tin tài sản:</div>")}"
                                 src="<%=request.getContextPath()%>/static/image/xemthem.png">
                        </c:if>
                        </td>
                        <td class="border-table align-giua  ">${item.notary_person}</td>

                    </tr>
                </c:forEach>

                <tr class="table-tr-xam">
                    <th colspan="7">
                        <div class="truong-padding-foot-table"> Tổng số <span
                                class="truong-text-colorred">${reportByBankForm.total}</span> dữ
                            liệu
                        </div>
                        <div class="align-phai">
                            <c:if test="${reportByBankForm.page==1}">

                                <img
                                        class="truong-pagging-icon"
                                        src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                <img
                                        class="truong-pagging-icon"
                                        src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                            </c:if>
                            <c:if test="${reportByBankForm.page!=1}">

                                <img onclick="firstPage()"
                                     class="pagging-icon"
                                     src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                <img onclick="previewPage()"
                                     class="pagging-icon"
                                     src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                            </c:if>
                                ${reportByBankForm.page}
                            trên ${reportByBankForm.totalPage}
                            <c:if test="${reportByBankForm.page==reportByBankForm.totalPage}">

                                <img
                                        class="truong-pagging-icon"
                                        src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                                <img
                                        class="truong-pagging-icon"
                                        src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                            </c:if>
                            <c:if test="${reportByBankForm.page!=reportByBankForm.totalPage}">

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
</div>

<script>
    $(window).on('load resize', function () {
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
    function onChangeFunction() {
        var x = document.getElementById("notaryOffice").value;
        document.getElementById("notaryName").value;
    }

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
        var userTotalPage = $('#totalPage').val();
        $('#page').val(userTotalPage);
        $("#search-frm").submit();
    }

    $(document).ready(function () {
        $("#timeType").change(function () {
            var timeType = $('#timeType option:selected').val();
            if (timeType == '05') {
                $('#dateTimeFilter').css("display", "block");


            }
            if (timeType != '05') {
                $('#dateTimeFilter').css("display", "none");

            }
        });
        $('[data-toggle="popover"]').popover();
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
        $("#bao-cao-theo-ngan-hang").addClass("child-menu");
        $(".date-time").keypress(function (key) {
            var fromDate = $(".fromDate").val().length;
            var toDate = $(".toDate").val().length;
            if (fromDate > 12) {
                return false;
            }
            if (toDate > 12) {
                return false;
            }
            if (key.charCode < 47 || key.charCode > 57) return false;
        });
        $(".date-time").bind("cut copy paste", function (e) {
            e.preventDefault();
        });
    });
</script>
