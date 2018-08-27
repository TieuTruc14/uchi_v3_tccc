<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />
<%--
    Thông tin chi tiết tỉnh
--%>
<spring:url value="/system/province/update" var="updateUrl" />
<spring:url value="/system/province/delete" var="removeUrl" />
<spring:url value="/system/province/list" var="backUrl" />
<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Chỉnh sửa thông tin tỉnh thành</span>
</div>


<div class="truong-form-chinhbtt">

    <div class="panel-group" id="accordion">
        <form class="form-horizontal" action="${updateUrl}" method="post">
            <input type="hidden" name="sid" value="${provinceListForm.provinceDetail.sid}">

            <div class="panel panel-default" id="panel1">
                <div class="panel-heading">
                    <h4 class="panel-title">

                            THÔNG TIN TỈNH THÀNH

                    </h4>

                </div>
                <div class="panel-body">

                    <div class="form-group truong-margin-formgroup">
                        <label class="col-md-2 control-label required label-bam-trai">Tên tỉnh</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control " name="name" value="${provinceListForm.provinceDetail.name}">
                        </div>
                        <div class="col-md-2"></div>
                        <label class="col-md-2 control-label required label-bam-phai">Mã tỉnh</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control" name="code" value="${provinceListForm.provinceDetail.code}">
                        </div>
                    </div>




                </div>
            </div>

            <div class="truong-prevent-btb">
                <div class="truong-can-button3">
                    <div class="col-md-2 truong-rs-bt3">
                        <a href="${backUrl}" class="truong-small-linkbt"><input type="button" class="form-control huybo" name="" value="Hủy bỏ"></a>
                    </div>
                </div>
            </div>


        </form>
    </div>


</div>


<!-- Modal -->

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
        loadMenu();
    });
    function loadMenu() {
        $(".sidebar-nav > li > #tinh-thanh").addClass("father-menu");
    }
</script>


