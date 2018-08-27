<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.vn.osp.common.util.SystemMessageProperties" %>
<%@ page import="com.vn.osp.common.util.ValidationPool" %>
<%@ page import="com.vn.osp.common.global.Constants" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp"/>

<%--
Lịch sử thao tác hệ thống
--%>
<spring:url value="/system/access-history" var="listUrl"/>
<spring:url value="/system/export-access-history" var="exportExcelUrl"/>

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Lịch sử thao tác hệ thống</span>
</div>
<div class="truong-form-chinhds">
    <div class=" panel-group">
        <form class="form-horizontal" id="search-frm" action="${listUrl}" method="get">
            <input type="hidden" name="listNumber" value="${accessHistoryList.total}">
            <input type="hidden" name="totalPage" id="totalPage" value="${accessHistoryList.totalPage}">
            <input type="hidden" name="page" id="page" value="${accessHistoryList.page}">
            <%
                if (ValidationPool.checkRoleDetail(request, "06", Constants.AUTHORITY_TIMKIEM)) {
            %>
            <div class="panel-body">
                <div class="form-group">
                    <label class="col-md-2 control-label  label-bam-trai">Tên người dùng</label>
                    <div class="col-md-5">

                        <select class="form-control truong-selectbox-padding select2" name="userId" id="userId">
                            <option value="">Tất cả</option>

                            <c:forEach items="${accessHistoryList.userList}" var="item">
                                <option value="${item.userId}" ${item.userId==accessHistoryList.userId?"selected":""}>${item.family_name} ${item.first_name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <label class="col-md-2 control-label  label-bam-phai">Hành động</label>
                    <div class="col-md-3">
                        <select class="form-control truong-selectbox-padding select2" name="access_type" id="accessType">
                            <option value="">Tất cả</option>
                            <option value="0" ${accessHistoryList.access_type==0?"selected":""}>đăng nhập</option>
                            <option value="1" ${accessHistoryList.access_type==1?"selected":""}>đăng xuất</option>
                            <option value="2" ${accessHistoryList.access_type==2?"selected":""}>Xem thông tin</option>
                            <%--<option value="3" ${accessHistoryList.access_type==3?"selected":""}>Thêm mới dữ liệu</option>
                            <option value="4" ${accessHistoryList.access_type==4?"selected":""}>Chỉnh sửa dữ liệu</option>
                            <option value="5" ${accessHistoryList.access_type==5?"selected":""}>Xóa dữ liệu</option>--%>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-2 control-label  label-bam-trai">Ngày thực hiện</label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-3 truong-padding-deleteright">
                                <select class="form-control truong-selectbox-padding select2" name="timeType" id="timeType"
                                        onchange="timeTypeChange()">
                                    <option value="01" ${accessHistoryList.timeType.equals('01')?"selected":""}>Trong
                                        ngày hôm nay
                                    </option>
                                    <option value="02" ${accessHistoryList.timeType.equals('02')?"selected":""}>Trong
                                        tuần này
                                    </option>
                                    <option value="03" ${accessHistoryList.timeType.equals('03')?"selected":""}>Trong
                                        tháng này
                                    </option>
                                    <option value="04" ${accessHistoryList.timeType.equals('04')?"selected":""}>Trong
                                        năm nay
                                    </option>
                                    <option value="05" ${accessHistoryList.timeType.equals('05')?"selected":""}>Trong
                                        khoảng thời gian
                                    </option>
                                </select>
                            </div>
                            <div class="col-md-9 ">
                                <div class="row">
                                    <div ${accessHistoryList.timeType.equals('05')?"":"style='display: none;'"}
                                            id="dateTimeFilter">
                                        <div class="col-md-9 " id="to-from">
                                            <div class="row">
                                                <label class="col-md-3 control-label  label-bam-phai required">Từ
                                                    ngày</label>
                                                <div class="col-md-3 truong-padding-delete">
                                                    <input type="text" value="${accessHistoryList.fromDate}"
                                                           class="form-control date-time" name="fromDate" id="fromDate">
                                                    <div style="margin-top: 5px;color: red;"
                                                         class="error_tooltip">${validate_msg_from_date}</div>
                                                </div>
                                                <label class="col-md-3 control-label  label-bam-phai">Đến ngày</label>
                                                <div class="col-md-3 truong-padding-delete">
                                                    <input type="text" value="${accessHistoryList.toDate}"
                                                           class="form-control date-time" name="toDate" id="toDate">
                                                    <div style="margin-top: 5px;color: red;"
                                                         class="error_tooltip">${validate_msg_to_date}</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-3" id="bt-thongke">
                                        <input type="submit" value="Tìm kiếm" class="form-control luu"
                                               onclick="setPage();">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="" id="bt-thongke-small" style="text-align: center;display: none;">
                    <input type="button" value="Tìm kiếm" class="btn btn-primary btn-stp"
                           style="width: 120px !important;" onclick="setPage();">
                </div>
                    <%
                }
            %>
        </form>
    </div>
    <div class="col-md-12 truong-margin-footer0px">
        <%
            if (ValidationPool.checkRoleDetail(request, "06", Constants.AUTHORITY_IN)) {
        %>
        <div class="report-button">
            <a href="${exportExcelUrl}" class="truong-small-linkbt"><input type="button"
                                                                           class="form-control luu truong-image-btexcel"
                                                                           value="Xuất file Excel"></a>
        </div>
        <%
            }
        %>
        <table class="table" style="margin-bottom:0%">
            <tr class="border-table">
                <th class=" ann-title border-table table-giua">Tài khoản thực hiện</th>
                <th class=" ann-title border-table table-giua">Ngày thực hiện</th>
                <th class=" ann-title border-table table-giua">Máy truy cập</th>
                <th class=" ann-title border-table table-giua">Nội dung</th>
                <th class=" ann-title border-table table-giua">Hành động</th>
            </tr>
            <c:if test="${accessHistoryList.total == 0}">
                <tr>
                    <td colspan="7"
                        style="height: 100%;background-color: #ececec; line-height: 5.429;text-align: center;font-size: 265%">
                        Không có dữ liệu
                    </td>
                </tr>
            </c:if>
            <c:if test="${accessHistoryList.total != 0}">
                <c:forEach items="${accessHistoryList.accessHistories}" var="item">
                    <tr>
                        <td class="border-table align-giua">${item.execute_person}</td>
                        <td class="border-table align-giua">${item.execute_date_time}</td>
                        <td class="border-table align-giua">${item.client_info}</td>
                        <td class="border-table align-giua">${item.description}</td>
                        <td class="border-table align-giua">
                            <c:if test="${item.access_type == 0 }">
                                Đăng nhập
                            </c:if>
                            <c:if test="${item.access_type == 1 }">
                                Đăng xuất
                            </c:if>
                            <c:if test="${item.access_type == 2 }">
                                Xem thông tin
                            </c:if>
                            <c:if test="${item.access_type == 3 }">
                                Thêm mới dữ liệu
                            </c:if>
                            <c:if test="${item.access_type == 4 }">
                                Chỉnh sửa dữ liệu
                            </c:if>
                            <c:if test="${item.access_type == 5 }">
                                Xóa dữ liệu
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <tr class="table-tr-xam">
                    <th colspan="7">
                        <div class="truong-padding-foot-table">Tổng số <span
                                class="truong-text-colorred">${accessHistoryList.total}</span> dữ liệu
                        </div>
                        <div class="align-phai">
                            <c:if test="${accessHistoryList.page==1}">
                                <img
                                        class="truong-pagging-icon"
                                        src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">
                                <img
                                        class="truong-pagging-icon"
                                        src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                            </c:if>
                            <c:if test="${accessHistoryList.page!=1}">
                                <img onclick="firstPage()"
                                     class="pagging-icon"
                                     src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">
                                <img onclick="previewPage()"
                                     class="pagging-icon"
                                     src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                            </c:if>
                                ${accessHistoryList.page}
                            trên ${accessHistoryList.totalPage}
                            <c:if test="${accessHistoryList.page== accessHistoryList.totalPage}">
                                <img
                                        class="truong-pagging-icon"
                                        src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                                <img
                                        class="truong-pagging-icon"
                                        src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                            </c:if>
                            <c:if test="${accessHistoryList.page!= accessHistoryList.totalPage}">
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
</form>
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
        var parentItem = $("#quan-tri-he-thong");
        $(parentItem).click();
        $("#ls-truy-cap-he-thong").addClass("child-menu");
        $(".date-time").keypress(function (key) {
            if (key.charCode < 47 || key.charCode > 57) return false;
        });
        $(".date-time").bind("cut copy paste", function (e) {
            e.preventDefault();
        });
    });

    function setPage() {
        $("#page").val('1');
    }
</script>
