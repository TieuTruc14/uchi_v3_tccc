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
    Danh sách hợp đồng chưa đồng bộ
--%>
<spring:url value="/contract/re-sync" var="reSyncUrl"/>

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Danh sách hợp đồng chưa đồng bộ</span>
</div>
<div class="truong-form-chinhds panel-group">
    <form class="form-horizontal" id="search-frm" action="${reSyncUrl}" method="get">
        <div class="panel-body ">
            <%
                if(ValidationPool.checkRoleDetail(request,"11", Constants.AUTHORITY_THEM)){
            %>
            <div class="form-group">
                <div class=" col-md-offset-10 col-md-2">
                    <input type="submit" class="form-control luu  " name="" value="Đồng bộ lại"></a>
                </div>
            </div>
            <%
                }
            %>
        </div>
    </form>

    <div class="panel-body truong-margin-footer0px">
        <table class="table" style="margin-bottom:0%">

            <tr class="border-table">
                <th class=" ann-title border-table table-giua">Số hợp đồng</th>
                <th class=" ann-title border-table table-giua">Ngày công chứng</th>
                <th class=" ann-title border-table table-giua">Tên hợp đồng</th>
                <th class=" ann-title border-table table-giua">Bên liên quan</th>
                <th class=" ann-title border-table table-giua">Nội dung</th>
                <th class=" ann-title border-table table-giua">Công chứng viên</th>

            </tr>
            <c:if test="${properties.size()  < 1}">
                <tr>
                    <td colspan="7"
                        style="height: 100%;background-color: #ececec; line-height: 5.429;text-align: center;font-size: 265%">
                        Chưa có dữ liệu
                    </td>
                </tr>
            </c:if>


            <c:if test="${properties.size() > 0}">
                <c:forEach items="${properties}" var="item">
                    <tr>
                        <td class="border-table align-giua" >${item.contract_number}</td>
                        <td class="border-table align-giua">${item.notary_date}</td>
                        <td class="border-table align-giua">${item.contract_name}</td>
                        <td class="border-table truong-text-verticel">${item.relation_object}</td>
                        <td class="border-table">${item.transaction_content}</td>
                        <td class="border-table align-giua">${item.notary_person}</td>
                    </tr>
                </c:forEach>

            </c:if>

            <tr class="table-tr-xam">
                <th colspan="6"><div class="truong-padding-foot-table"> Tổng số <span style="color: red;">${properties.size()}</span><span class="truong-text-colorred"></span> dữ liệu </div></th>
            </tr>


        </table>
    </div>
</div>



<jsp:include page="/WEB-INF/pages/layout/footer.jsp"/>
<script>
    $(document).ready(function () {
        var parentItem = $("#quan-ly-hop-dong");
        $(parentItem).click();
        $("#ds-hd-chua-db").addClass("child-menu");
    });
</script>
