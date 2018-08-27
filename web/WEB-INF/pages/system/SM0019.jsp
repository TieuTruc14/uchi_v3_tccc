<%@ page import="com.vn.osp.common.util.ValidationPool" %>
<%@ page import="com.vn.osp.common.global.Constants" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>
<script type="text/javascript" src="/static/js/checkbox.js"></script>
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp"/>
<%--
    phục hồi dữ liệu
--%>
<spring:url value="/system/user-permission" var="permissionUrl"/>
<spring:url value="/system/user-list" var="backUrl"/>
<spring:url value="/system/restore" var="restoreUrl"/>

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Phục hồi dữ liệu</span>
</div>
<div class="truong-form-chinhpq">
    <div class=" col-md-12 panel-group canh-le-trai" id="">
        <form class="form-horizontal" action="${restoreUrl}" method="post">
            <%--<input type="hidden" name="userId" value="">--%>
            <div class="panel-body">
                <table class="table border-table">
                        <th class=" border-table truong-background-clpink">Cảnh báo: Việc phục hồi dữ liệu sẽ làm mất toàn bộ dữ liệu cũ.
                         Hãy chắc chắn rằng hành động này là cần thiết. Bạn sẽ phải chịu toàn bộ trách nhiệm của mình khi thực hiện hành
                        động này.</th>
                </table>
                <div class="form-group">
                <div class="col-md-10 truong-font-chu">
                    <input type="checkbox" class="truong-check-fix"> Tôi đồng ý phúc hồi toàn bộ dữ liệu
                </div>
                </div>
                <div class="form-group">
                    <label class="col-md-2 control-label label-bam-trai">Thư mục lưu trữ</label>
                    <div class="col-md-6">
                        <input type="text" class="form-control" name ="fileRestore" value="C:/VuongHV/npo_base/stp/noo/web/backup">
                    </div>
                    <%
                        if(ValidationPool.checkRoleDetail(request,"07", Constants.AUTHORITY_THEM)){
                    %>
                    <div class="" style="width: 180px;float: left">
                        <input type="submit" class="form-control luu" value="Phục hồi dữ liệu">
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>
        </form>
    </div>
</div>
<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />
<script>
    $(document).ready(function () {
        var parentItem = $("#quan-tri-he-thong");
        $(parentItem).click();
        $("#ds-backup").addClass("child-menu");
    });
</script>