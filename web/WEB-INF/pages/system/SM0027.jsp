<%@ page import="com.vn.osp.common.global.Constants" %>
<%@ page import="com.vn.osp.common.util.ValidationPool" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp"/>
<%--
    Lịch sử thay đổi hợp đồng chi tiét
--%>
<spring:url value="/announcement/update-view" var="updateViewUrl"/>
<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Chi tiết lịch sử thay đổi hợp đồng </span>
</div>


<div class="truong-form-chinhbtt truong-form-margin0px">

    <div class="col-md-12 truong-margin-footer0px">


        <c:forEach items="${contractHistoryDetail.historyContractList}" var="item">
            <table class="table" style="margin-bottom:1.5%;margin-top: 1.5%">
                <th style="background-color: #ececec " colspan="2"> ID:#${item.hid}</th>
                <tr>
                    <td>Số hợp đồng</td>
                    <td class="border-table align-giua">${item.contract_number}</td>
                </tr>
                <tr>

                    <td>Nội dung</td>
                    <td class="border-table truong-text-verticel"
                        style="color: black;">
                            ${item.contract_content.replace('\\n', '<br>')}
                    </td>
                </tr>
                <tr>
                    <td>Thao tác</td>
                    <td class="border-table align-giua">${item.execute_content}</td>
                </tr>
                <tr>
                    <td>Người thực hiện</td>
                    <td class="border-table  align-giua">
                            ${item.family_name} ${item.first_name}
                    </td>
                </tr>
                <td>Ngày thực hiện</td>
                <td class="border-table  align-giua ">
                        ${item.execute_date_time}
                </td>
                </tr>

            </table>
        </c:forEach>


    </div>
</div>
</form>
</div>


</div>


<!-- Modal -->
<div class="modal fade" id="xoa" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <input type="hidden" name="preventId" value="${dataPreventInfor.prevent_id}">
            <div class="panel-heading" style="background-color: #2e9ad0 ">
                <h5 class="panel-title truong-text-colorwhite">
                    Xóa thông tin cán bộ

                    <button type="button" class="close truong-button-xoa" data-dismiss="modal"
                            style="margin-bottom: 5px"><img
                            src="<%=request.getContextPath()%>/static/image/close.png"
                            class="control-label truong-imagexoa-fix"></button>


                </h5>

            </div>
            <div class="panel-body">
                <div class="truong-modal-padding" style="padding-bottom: 7%;">
                    <label class="col-md-12 control-label align-giua">Bạn có thực sự muốn xóa dữ liệu ?</label>
                </div>
            </div>
            <div class="modal-footer">
                <div class="col-md-3 col-md-offset-3">
                    <a href="${removeUrl}/${user.userId}" class="truong-small-linkbt"><input type="button"
                                                                                             class="form-control luu"
                                                                                             name="" value="Xóa"></a>
                </div>
                <div class="col-md-3 ">
                    <input type="button" class="form-control huybo" data-dismiss="modal" name="" value="Hủy bỏ">
                </div>

            </div>
        </div>
    </div>
</div>

<%--End Modal--%>
<script>
    $(window).on('load resize', function () {
        var win = $(this);
        if (win.width() < 1300) {
            $('.truong-rs-bt3os').removeClass('col-md-2 col-md-offset-3');
            $('.truong-rs-bt3os').addClass('col-md-4');
            $('.truong-rs-bt3').removeClass('col-md-2');
            $('.truong-rs-bt3').addClass('col-md-4');
        } else {
            $('.truong-rs-bt3os').removeClass('col-md-4');
            $('.truong-rs-bt3os').addClass('col-md-2 col-md-offset-3');
            $('.truong-rs-bt3').removeClass('col-md-4');
            $('.truong-rs-bt3').addClass('col-md-2');

        }
    });
</script>

<script>

    $(function () {
        $('#birthday').datepicker({
            format: "dd/mm/yyyy",
            forceParse: false,
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
        $("#ds-can-bo-stp").addClass("child-menu");
    });
</script>


