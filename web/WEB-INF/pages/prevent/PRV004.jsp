<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp"/>
<%--/transaction/search
    Chi tiết thông tin ngăn chặn, trinh lanh dao
--%>
<spring:url value="/transaction/search" var="backUrl"/>
<spring:url value="/transaction/prevent/download-prevent" var="downloadPreventUrl"/>
<style type="text/css">
    fieldset.scheduler-border {
        border: 1px groove #ddd !important;
        padding: 0 1.4em 1.4em 1.4em !important;
        margin: 0 0 1.5em 0 !important;
        -webkit-box-shadow: 0px 0px 0px 0px #000;
        box-shadow: 0px 0px 0px 0px #000;
    }

    legend.scheduler-border {
        width: auto; /* Or auto */
        padding: 0 10px; /* To give a bit of padding on the left and right */
        border-bottom: none;
    }
</style>
<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Chi tiết thông tin ngăn chặn</span>
</div>
<div class="col-md-1"></div>
<div class="truong-form-chinhbtt form-horizontal">
    <form class="form-horizontal" action="${backUrl}" method="get" id="search-frm">
        <input type="hidden" name="page" id="page" value="${page}">
        <input type="hidden" name="tab" id="tab" value="${tab}">
    </form>
    <div id="uchi-status">
        <c:if test="${dataPreventInfor.action_status != null}">
            <div class="status-success">
                <img class="status-img" src="<%=request.getContextPath()%>/static/image/success.png">${dataPreventInfor.action_status}
            </div>
        </c:if>
    </div>
    <div class="panel-group" id="accordion">
        <div class="form-group">
            <div class="col-md-3 col-md-offset-9 label-bam-phai">
                <input type="button" class="btn luu" data-toggle="modal" data-target="#historyChange"
                       value="Lịch sử thay đổi thông tin">
            </div>
        </div>
        <div class="padding-left-right">
            <div class="form-group">
                <label class="col-md-3 control-label  label-bam-trai">Cơ quan đăng ký thông tin ngăn chặn</label>
                <label class="col-md-9 control-label  label-bam-trai"><span style="color: red;"
                                                                            class="control-label">${dataPreventInfor.prevent_regist_agency}</span></label>
            </div>
        </div>
        <fieldset class="scheduler-border">
            <legend class="scheduler-border">
                <a data-toggle="collapse" data-target="#collapseTwo"
                   href="#collapseTwo" style="text-decoration: none">
                    VĂN BẢN NGĂN CHẶN
                </a>
            </legend>

            <div id="collapseTwo" class="panel-collapse collapse in">
                <div class="panel-body">
                    <div class="form-group">
                        <label class="col-md-3 control-label label-bam-trai">Số công văn</label>
                        <div class="col-md-3 control-label label-bam-trai">
                            ${dataPreventInfor.prevent_doc_number}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label label-bam-trai">Số công văn đến</label>
                        <div class="col-md-3 control-label label-bam-trai">
                            ${dataPreventInfor.prevent_doc_number_to}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label label-bam-trai">Ngày công văn</label>
                        <div class="col-md-3 control-label label-bam-trai">
                            ${dataPreventInfor.prevent_doc_date}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label label-bam-trai">Ngày công văn đến</label>
                        <div class="col-md-3 control-label label-bam-trai">
                            ${dataPreventInfor.prevent_doc_receive_date}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label label-bam-trai">Người (đơn vị) gửi yêu cầu</label>
                        <div class="col-md-3 control-label label-bam-trai">
                            ${dataPreventInfor.prevent_person_info}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label label-bam-trai">Trích yếu</label>
                        <div class="col-md-3 control-label label-bam-trai">
                            ${dataPreventInfor.prevent_doc_summary}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label label-bam-trai">Ghi chú</label>
                        <div class="col-md-3 control-label label-bam-trai">
                            ${dataPreventInfor.prevent_note}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label label-bam-trai">File đính kèm</label>
                        <%--<div class="col-md-5 control-label label-bam-trai">
                            <c:if test="${dataPreventInfor.prevent_file_name.equals('') == false}">
                                <a href="${downloadPreventUrl}/${dataPreventInfor.prevent_id}">${dataPreventInfor.prevent_file_name}</a>
                            </c:if>
                            <c:if test="${dataPreventInfor.prevent_file_name.equals('') == true}">
                                Không có file đính kèm
                            </c:if>
                        </div>--%>
                        <div class="form-group">
                            <div class="col-md-4">
                                <c:if test="${dataPreventInfor.prevent_file_name.equals('') == false}">
                                    <c:forEach items="${listFileName}" var="item" varStatus="count">
                                        File ${count.index + 1} : <a
                                            href="${downloadPreventUrl}/${dataPreventInfor.prevent_id}/${count.index}">${item}</a><br>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${dataPreventInfor.prevent_file_name.equals('') == true}">
                                    Không có file đính kèm
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label label-bam-trai">Phân loại</label>
                        <div class="col-md-7 control-label label-bam-trai">
                            <c:if test="${dataPreventInfor.origin_kind=='01'}">
                                <label class="control-label  truong-text-radior col-md-4"><span
                                        class="truong-text-coloryl ">Thông tin ngăn chặn </span></label>
                            </c:if>
                            <c:if test="${dataPreventInfor.origin_kind=='02'}">
                                <label class="control-label  truong-text-radioyl col-md-4"><span
                                        class="truong-text-colorred ">Thông tin tham khảo</span></label>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
        <fieldset class="scheduler-border">
            <legend class="scheduler-border">
                <a data-toggle="collapse" data-target="#collapseOne"
                   href="#collapseOne" style="text-decoration: none">
                    THÔNG TIN TÀI SẢN
                </a>
            </legend>
            <div id="collapseOne" class="panel-collapse collapse in">
                <div class="panel-body">
                    <div class="form-group">
                        <label class="col-md-3 control-label label-bam-trai">Loại tài sản</label>
                        <div class="col-md-3 control-label label-bam-trai">
                            ${dataPreventInfor.type=="01"?"Nhà đất":""}
                            ${dataPreventInfor.type=="02"?"Ô tô - Xe máy":""}
                            ${dataPreventInfor.type=="99"?"Tài sản khác":""}
                        </div>
                    </div>
                    <c:if test="${dataPreventInfor.type == '01'}">
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Địa chỉ</label>
                            <div class="col-md-3 control-label label-bam-trai">
                                    ${dataPreventInfor.land_address}
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Số giấy chứng nhận</label>
                            <div class="col-md-3 control-label label-bam-trai">
                                    ${dataPreventInfor.land_certificate}
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Nơi cấp</label>
                            <div class="col-md-3 control-label label-bam-trai">
                                    ${dataPreventInfor.land_issue_place}
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Ngày cấp</label>
                            <div class="col-md-3 control-label label-bam-trai">
                                    ${dataPreventInfor.land_issue_date}
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Số giấy vào sổ</label>
                            <div class="col-md-3 control-label label-bam-trai">
                                    ${dataPreventInfor.land_paper_number}
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Thửa đất số</label>
                            <div class="col-md-3 control-label label-bam-trai">
                                    ${dataPreventInfor.land_number}
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Tờ bản đồ số</label>
                            <div class="col-md-3 control-label label-bam-trai">
                                    ${dataPreventInfor.land_map_number}
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Diện tích (m2)</label>
                            <div class="col-md-3 control-label label-bam-trai">
                                    ${dataPreventInfor.land_area}
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Hình thức sử dụng</label>
                            <div class="col-md-3 control-label label-bam-trai">
                                Riêng ${dataPreventInfor.land_private_area}
                            </div>
                            <div class="col-md-3 control-label label-bam-trai">
                                Chung ${dataPreventInfor.land_public_area}
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Mục đích sử dụng</label>
                            <div class="col-md-3 control-label label-bam-trai">
                                    ${dataPreventInfor.land_use_purpose}
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Thời hạn sử dụng</label>
                            <div class="col-md-3 control-label label-bam-trai">
                                    ${dataPreventInfor.land_use_period}
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Nguồn gốc sử dụng</label>
                            <div class="col-md-3 control-label label-bam-trai">
                                    ${dataPreventInfor.land_use_origin}
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Tài sản gắn liền với đất</label>
                            <div class="col-md-3 control-label label-bam-trai">
                                    ${dataPreventInfor.land_associate_property}
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Thông tin chủ sở hữu</label>
                            <div class="col-md-3 control-label label-bam-trai">
                                    ${dataPreventInfor.owner_info}
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Thông tin khác</label>
                            <div class="col-md-3 control-label label-bam-trai">
                                    ${dataPreventInfor.other_info}
                            </div>
                        </div>
                    </c:if>
                    <%--</div>--%>
                    <c:if test="${dataPreventInfor.type == '02'}">
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Biển kiểm soát</label>
                            <div class="col-md-3 control-label label-bam-trai">
                                    ${dataPreventInfor.car_license_number}
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Số giấy đăng ký</label>
                            <div class="col-md-3 control-label label-bam-trai">
                                    ${dataPreventInfor.car_regist_number}
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Nơi cấp</label>
                            <div class="col-md-3 control-label label-bam-trai">
                                    ${dataPreventInfor.car_issue_place}
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Ngày cấp</label>
                            <div class="col-md-3 control-label label-bam-trai">
                                    ${dataPreventInfor.car_issue_date}
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Số khung</label>
                            <div class="col-md-3 control-label label-bam-trai">
                                    ${dataPreventInfor.car_frame_number}
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Số máy</label>
                            <div class="col-md-3 control-label label-bam-trai">
                                    ${dataPreventInfor.car_machine_number}
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Thông tin chủ sở hữu</label>
                            <div class="col-md-9  control-label label-bam-trai">
                                    ${dataPreventInfor.owner_info}
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Thông tin khác</label>
                            <div class="col-md-9  control-label label-bam-trai">
                                    ${dataPreventInfor.other_info}
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${dataPreventInfor.type == '99'}">
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Thông tin tài sản</label>
                            <div class="col-md-9  control-label label-bam-trai">
                                    ${dataPreventInfor.property_info}
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Thông tin chủ sở hữu</label>
                            <div class="col-md-9  control-label label-bam-trai">
                                    ${dataPreventInfor.owner_info}
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Thông tin khác</label>
                            <div class="col-md-9  control-label label-bam-trai">
                                    ${dataPreventInfor.other_info}
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </fieldset>
        <c:if test="${dataPreventInfor.release_flg == 1}">
            <fieldset class="scheduler-border">
                <legend class="scheduler-border">
                    <a data-toggle="collapse" data-target="#collapseThree"
                       href="#collapseThree" style="text-decoration: none">
                        CÔNG VĂN GIẢI TỎA
                    </a>
                </legend>

                <div id="collapseThree" class="panel-collapse collapse in">
                    <div class="padding-left-right">
                        <div class="form-group">
                            <label class="col-md-3 control-label  label-bam-trai">Trạng thái giải toả</label>
                            <div class="col-md-9">
                                <label class="control-label  truong-text-radior col-md-2"><span
                                        class="truong-text-coloryl ">Đã giải toả</span></label>
                            </div>
                        </div>
                    </div>
                    <div class="padding-left-right">
                        <div class="form-group">
                            <label class="col-md-3 control-label  label-bam-trai">Cơ quan đăng ký thông tin giải
                                tỏa</label>
                            <label class="col-md-9 control-label  label-bam-trai"><span style="color: red;"
                                                                                        class="control-label">${dataPreventInfor.prevent_regist_agency}</span></label>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Số vào sổ công văn</label>
                            <div class="col-md-3 control-label label-bam-trai">
                                    ${dataPreventInfor.release_in_book_number}
                            </div>
                            <label class="col-md-2 control-label label-bam-trai">Số công văn</label>
                            <div class="col-md-3 control-label label-bam-trai">
                                    ${dataPreventInfor.release_doc_number}
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Người (đơn vị) gửi yêu</label>
                            <div class="col-md-3 control-label label-bam-trai">
                                    ${dataPreventInfor.release_person_info}
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Ngày công văn</label>
                            <div class="col-md-3 control-label label-bam-trai">
                                    ${dataPreventInfor.release_doc_date}
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Ngày nhận</label>
                            <div class="col-md-3 control-label label-bam-trai">
                                    ${dataPreventInfor.release_doc_receive_date}
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Ngày nhập</label>
                            <div class="col-md-3 control-label label-bam-trai">
                                    ${dataPreventInfor.release_input_date}
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Trích yếu</label>
                            <div class="col-md-9 control-label label-bam-trai">
                                    ${dataPreventInfor.release_doc_summary}
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Ghi chú</label>
                            <div class="col-md-9 control-label label-bam-trai">
                                    ${dataPreventInfor.release_note}
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">File đính kèm</label>
                            <div class="form-group">
                                <div class="col-md-4">
                                    <c:if test="${dataPreventInfor.release_file_name.equals('') == false}">
                                        <c:forEach items="${listFileRelease}" var="item" varStatus="count">
                                            File ${count.index + 1} : <a
                                                href="${downloadReleaseFile}/${dataPreventInfor.prevent_id}/${count.index}">${item}</a><br>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${dataPreventInfor.release_file_name.equals('') == true}">
                                        Không có file đính kèm
                                    </c:if>
                                </div>
                            </div>
                                <%--<div class="col-md-4">
                                    <c:if test="${dataPreventInfor.release_file_name.equals('') == false}">
                                        <a href="${downloadPreventUrl}/${dataPreventInfor.prevent_id}">${dataPreventInfor.release_file_name}</a>
                                    </c:if>
                                    <c:if test="${dataPreventInfor.release_file_name.equals('') == true}">
                                        Không có file đính kèm
                                    </c:if>

                                </div>--%>
                        </div>


                    </div>

                </div>
            </fieldset>
        </c:if>


        <div class="row prevent-type-box">
            <div class="form-group">
                <div class="col-md-3 col-md-offset-4">
                    <a <%--href="${backUrl}"--%> onclick="backPage()"><input type="button" class="form-control luu" name=""
                                                value="Quay lại danh sách"></a>
                </div>
            </div>
        </div>

    </div>
</div>
<div class="col-md-1"></div>

<!-- Modal -->
<div class="modal fade" id="historyChange" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content" style="overflow-y: scroll;height:auto">
            <table class="table" style="margin-bottom:0%">
                <tr class="border-table">
                    <th class=" ann-title border-table table-giua">Thời điểm</th>
                    <th class=" ann-title border-table table-giua">Thao tác</th>
                    <th class=" ann-title border-table table-giua">Người thực hiên</th>
                    <th class=" ann-title border-table table-giua">Người nhận</th>
                    <th class=" ann-title border-table table-giua">
                        Ý kiến xử lý<img class="close" data-dismiss="modal" src="<%=request.getContextPath()%>/static/image/close.png">
                    </th>
                </tr>
                <c:if test="${dataPreventInfor.preventHistories.size()==0}">
                    <tr>
                        <td colspan="7"
                            style="height: 100%;background-color: #ececec; line-height: 5.429;text-align: center;font-size: 365%">
                            Chưa có dữ liệu
                        </td>
                    </tr>
                </c:if>
                <c:if test="${dataPreventInfor.preventHistories.size()!=0}">
                    <c:forEach items="${dataPreventInfor.preventHistories}" var="item">
                        <tr>
                            <td class="border-table"
                                style="color: black;text-align: center">${item.execute_date_time}</td>
                            <td class="border-table"
                                style="color: black;text-align: center">${item.execute_content}</td>
                            <td class="border-table" style="color: black;text-align: center">${item.execute_person}</td>
                            <td class="border-table" style="color: black;text-align: center">${item.recipient}</td>
                            <td class="border-table" style="color: black;text-align: center">${item.handled_info}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <th colspan="5">Tổng <span style="color: red">${dataPreventInfor.preventHistories.size()}</span>
                            dữ liệu
                        </th>
                    </tr>
                </c:if>


            </table>

        </div>
    </div>

</div>
<%--End Modal--%>


<script>

    $('#checkValidate').click(function () {
        var submitLeader = $('#truong-field-ideal').val();


        if (submitLeader.length > 1000) {

            $("#error-ideal-leader").text("Trường Ý kiến xử lý có độ dài tối đa 1000 kí tự ");

        }
        else {
            $("#form-submit-leader").submit();
        }
    });
</script>

<script>
    $(".modal-wide").on("show.bs.modal", function () {
        var height = $(window).height() - 200;
        $(this).find(".modal-body").css("max-height", height);
    });

    function resetPropertyType() {
        $('#nha-dat').addClass('hidden');
        $('#oto-xemay').addClass('hidden');
        $('#tai-san-khac').addClass('hidden');
    }

    function changePropertyType() {
        resetPropertyType();
        var propertyType = $('#propertyType').val();
        if (propertyType == '01') {
            $('#propertyType option[value=01]').attr('selected', 'selected');
            $('#nha-dat').removeClass('hidden');
        }
        else if (propertyType == '02') {
            $('#propertyType option[value=02]').attr('selected', 'selected');
            $('#oto-xemay').removeClass('hidden');
        }
        else if (propertyType == '99') {
            $('#propertyType option[value=99]').attr('selected', 'selected');
            $('#tai-san-khac').removeClass('hidden');
        }
    }

    $(function () {
        changePropertyType();

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
        //nha dat
        $('#ngay-cap').datepicker({
            format: "dd/mm/yyyy",
            forceParse : false,
            language: 'vi'
        }).on('changeDate', function (ev) {
            $(this).datepicker('hide');
        });
        //oto-xemay
        $('#oto-xemay-ngaycap').datepicker({
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
        var parentItem = $("#ql-ttnc");
        $(parentItem).click();
    });

    function backPage() {
        $("#search-frm").submit();
    }
</script>