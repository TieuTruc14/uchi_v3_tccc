<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/checkbox.js"></script>
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp"/>
<%--
    Thêm mói nhóm quyền
--%>
<spring:url value="/system/update-bank" var="addUrl"/>

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Cập nhật ngân hàng</span>
</div>
<form class="form-horizontal" action="${addUrl}" method="post" id="form-padding">
    <input type="hidden" name="id" value="${createBankForm.id}">
    <input type="hidden" name="code" value="${createBankForm.code}">
    <div class="col-md-10 ">
        <div class="panel-group" id="accordion">
            <div class="panel panel-default" id="panel1">
                <div class="panel-heading">
                    <h4 class="panel-title">

                            THÔNG TIN NGÂN HÀNG

                    </h4>

                </div>
                <div class="panel-body">

                    <div class="form-group">
                        <label class="col-md-3 control-label required label-bam-trai">Tên ngân hàng</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control" name="name" value="${createBankForm.name}">
                        </div>
                        <div class="col-md-2"></div>
                        <label class="col-md-1 control-label required label-bam-phai">Mã ngân hàng</label>
                        <div class="col-md-3">
                            <label class="col-md-3 control-label label-bam-trai">${createBankForm.code}</label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label label-bam-trai">Trạng thái hoạt động</label>
                        <div class="col-md-3">
                            <label class="radio-inline prevent-type">
                                <input type="radio" name="active" value="1" ${createBankForm.active == 1 ?"checked":""}>Đang hoạt động
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="active" value="0" ${createBankForm.active != 1 ?"checked":""}>Ngừng hoạt động
                            </label>
                        </div>
                        <div class="col-md-2"></div>
                        <label class="col-md-1 control-label label-bam-phai">Thứ tự</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control" name="order_number" value="${createBankForm.order_number}">
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <div class="col-md-12 row prevent-type-box">
        <div class="form-group">
            <div class="col-md-2 col-md-offset-4">
                <input type="submit" class="form-control luu" name="" value="Lưu">
            </div>
            <div class="col-md-2">
                <input type="button" class="form-control huybo" name="" value="Hủy bỏ">
            </div>
        </div>
    </div>
</form>


<jsp:include page="/WEB-INF/pages/layout/footer.jsp"/>

<script>
    $(document).ready(function () {
        var parentItem = $("#quan-tri-he-thong");
        $(parentItem).click();
        $("#ds-ngan-hang").addClass("child-menu");
    });
</script>