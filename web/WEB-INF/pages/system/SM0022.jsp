<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />
<%--
    Chỉnh sửa thông tin cán bộ
--%>
<spring:url value="/system/osp/bank-view" var="updateUrl" />
<spring:url value="/system/osp/bank-list" var="backUrl" />
<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Chi tiết thông tin ngân hàng</span>
</div>


<div class="truong-form-chinhbtt">

    <div class="panel-group" id="accordion">
        <form class="form-horizontal" action="${updateUrl}" method="post">
            <input type="hidden" name="id" value="${bankListForm.bankDetail.sid}">


            <div class="panel panel-default" id="panel1">
                <div class="panel-heading">
                    <h4 class="panel-title">

                            THÔNG TIN NGÂN HÀNG

                    </h4>

                </div>
                <div class="panel-body">

                    <div class="form-group truong-margin-formgroup">
                        <label class="col-md-2 control-label required label-bam-trai text-nowrap">Tên ngân hàng</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control " value="${bankListForm.bankDetail.name}" disabled>
                        </div>
                        <div class="col-md-2"></div>
                        <label class="col-md-2 control-label required label-bam-phai text-nowrap">Mã ngân hàng</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control " value="${bankListForm.bankDetail.code}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label label-bam-trai">Trạng thái </label>
                        <div class="col-md-5 control-label label-bam-trai">
                            <input class="truong-check-fix" type="radio" name="active" value="1" ${bankListForm.bankDetail.active==1?"checked":""} disabled > Đang hoạt động &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                            <input class="truong-check-fix" type="radio" name="active" value="0" ${bankListForm.bankDetail.active==0?"checked":""} disabled> Ngừng hoạt động
                        </div>
                    </div>




                </div>
            </div>

            <div class="truong-prevent-btb">
                <div class="" style="text-align: center">


                        <a href="${backUrl}" class="truong-small-linkbt"><input type="button" class="btn btn-primary btn-stp huybo" name="" value="Quay lại danh sách"></a>

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
            <input type="hidden" name="sid" value="${bank.sid}">
            <div class="panel panel-default">

                <div class="panel-heading" style="background-color: #2e9ad0 ">
                    <h5 class="panel-title truong-text-colorwhite">
                        Xóa Thông tin ngân hàng

                        <button type="button" class="close truong-button-xoa" data-dismiss="modal" style="margin-bottom: 5px"><img
                                src="<%=request.getContextPath()%>/static/image/close.png" class="control-label truong-imagexoa-fix"></button>


                    </h5>

                </div>
                <div class="panel-body">
                    <div class="truong-modal-padding" style="padding-bottom: 7%;">
                        <label class="col-md-12 control-label align-giua">Bạn có thực sự muốn xóa dữ liệu ?</label>
                    </div>
                </div>

            </div>
            <div class="row" style="margin-bottom: 2%;">
                <div class="col-md-3 col-md-offset-3">
                    <a href="${removeUrl}/${bank.sid}" class="truong-small-linkbt"><input type="button" class="form-control luu" name="" value="Xóa"></a>
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
    $(window).on('resize',function(){
        var win = $(this);
        if(win.width() < 1300){
            $('.truong-rs-bt3os').removeClass('col-md-2 col-md-offset-3');
            $('.truong-rs-bt3os').addClass('col-md-4');
            $('.truong-rs-bt3').removeClass('col-md-2');
            $('.truong-rs-bt3').addClass('col-md-4');
        }else {
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
        $("#ngan-hang").addClass("child-menu");
    });

</script>


