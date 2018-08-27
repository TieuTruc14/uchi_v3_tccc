<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp"/>
<%--
    Chi tiết hợp đồng, giao dịch đã công chứng
--%>
<spring:url value="/transaction/search" var="backUrl"/>
<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Chi tiết hợp đồng, giao dịch</span>
</div>
<div class="col-md-1"></div>
<div class="panel-group col-md-10">
    <div class="panel-group" id="accordion">

        <form class="form-horizontal">

            <div class="panel-body">

                <div class="panel panel-default" id="panel2">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-target="#collapseTwo"
                               href="#collapseTwo">
                                THÔNG TIN HỢP ĐỒNG, GIAO DỊCH
                            </a>
                        </h4>

                    </div>


                    <div id="collapseTwo" class="panel-collapse collapse in">
                        <div class="panel-body">
                            <%--<form class="form-horizontal">--%>
                            <div class="row inline-field">
                                <div class="form-group">
                                    <label class="col-md-3 control-label label-bam-trai">Ngày công chứng</label>
                                    <div class="col-md-9 control-label label-bam-trai">
                                        ${transactionProperty.notary_date}
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label label-bam-trai">Số hợp đồng</label>
                                <div class="col-md-9 control-label label-bam-trai">
                                    ${transactionProperty.contract_number}
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label label-bam-trai">Tên hợp đồng</label>
                                <div class="col-md-9 control-label label-bam-trai">
                                    ${transactionProperty.contract_name}
                                </div>
                            </div>
                            <div class="row inline-field">
                                <div class="form-group">
                                    <label class="col-md-3 control-label label-bam-trai">Bên liên quan</label>
                                    <div class="col-md-9 control-label label-bam-trai">
                                        ${transactionProperty.relation_object.replace('\\n', '<br>')}
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label label-bam-trai">Tóm tắt nội dung</label>
                                <div class="col-md-9 control-label label-bam-trai">
                                    ${transactionProperty.transaction_content.replace('\\n', '<br>')}
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label label-bam-trai">Thông tin tài sản</label>
                                <div class="col-md-9 control-label label-bam-trai">
                                    ${transactionProperty.property_info.replace('\\n', '<br>')}
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label label-bam-trai">Công chứng viên</label>
                                <div class="col-md-9 control-label label-bam-trai">
                                    ${transactionProperty.notary_person}
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label label-bam-trai">Tổ chức công chứng</label>
                                <div class="col-md-9 control-label label-bam-trai">
                                    ${transactionProperty.notary_office_name}
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label label-bam-trai">Thời hạn hợp đồng</label>
                                <div class="col-md-9 control-label label-bam-trai">
                                    ${transactionProperty.contract_period}
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label label-bam-trai">Trạng thái hủy hợp đồng</label>
                                <div class="col-md-9 control-label label-bam-trai">
                                    <c:if test="${transactionProperty.cancel_status == 0}">
                                        Chưa hủy hợp đồng
                                    </c:if>
                                    <c:if test="${transactionProperty.cancel_status == 1}">
                                        Đã hủy hợp đồng
                                    </c:if>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label label-bam-trai">Thông tin chi tiết hủy hợp
                                    đồng</label>
                                <div class="col-md-9 control-label label-bam-trai">
                                    ${transactionProperty.cancel_description}
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label label-bam-trai">Ghi chú</label>
                                <div class="col-md-9 control-label label-bam-trai">
                                    ${transactionProperty.note}
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label label-bam-trai">Trạng thái giải chấp</label>
                                <div class="col-md-9 control-label label-bam-trai">
                                    <c:if test="${transactionProperty.mortage_cancel_flag == 0}">
                                        Chưa giải chấp
                                    </c:if>
                                    <c:if test="${transactionProperty.mortage_cancel_flag == 1}">
                                        Đã giải chấp
                                    </c:if>
                                </div>
                            </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label label-bam-trai">Ngày giải chấp</label>
                                    <div class="col-md-9 control-label label-bam-trai">
                                        ${transactionProperty.mortage_cancel_date}
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label label-bam-trai">Lưu ý / Nội dung giải chấp</label>
                                    <div class="col-md-9 control-label label-bam-trai">
                                        ${transactionProperty.mortage_cancel_note}
                                    </div>
                                </div>
                            <%--</form>--%>
                        </div>

                    </div>
                </div>
            </div>


            <div class="row prevent-type-box">
                <div class="form-group" style="text-align: center">

                        <a <%--href="${backUrl}"--%> onclick="backPage()" class="btn btn-primary btn-stp" >Quay lại danh sách </a>

                </div>
            </div>
        </form>
    </div>
</div>
<div class="col-md-1"></div>
<form class="form-horizontal" action="${backUrl}" method="get" id="search-frm">
    <input type="hidden" name="page" id="page" value="${page}">
    <input type="hidden" name="tab" id="tab" value="${tab}">
</form>

<script>
    $(".modal-wide").on("show.bs.modal", function () {
        var height = $(window).height() - 200;
        $(this).find(".modal-body").css("max-height", height);
    });

    $(function () {
        $('#ngay-cv').datepicker({
            format: "dd/mm/yyyy",
            forceParse : false,
            language: 'vi'
        }).on('changeDate', function (ev) {
            $(this).datepicker('hide');
        });
        $('#ngay-nhan').datepicker({
            format: "dd/mm/yyyy",
            forceParse : false,
            language: 'vi'
        }).on('changeDate', function (ev) {
            $(this).datepicker('hide');
        });
        $('#ngay-nhap').datepicker({
            format: "dd/mm/yyyy",
            forceParse : false,
            language: 'vi'
        }).on('changeDate', function (ev) {
            $(this).datepicker('hide');
        });
        loadMenu();
    });

    function loadMenu(){
        $(".sidebar-nav > li > #tra-cuu-thong-tin").addClass("father-menu");

    }

    function backPage() {
        $("#search-frm").submit();
    }
</script>
<jsp:include page="/WEB-INF/pages/layout/footer.jsp"/>
