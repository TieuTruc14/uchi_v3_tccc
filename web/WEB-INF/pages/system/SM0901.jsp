<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp"/>
<%--
    Danh sách ngân hàng
--%>
<spring:url value="/system/create-office-view" var="createOfficeUrl"/>
<spring:url value="/system/update-office-view" var="updateOfficeUrl"/>
<spring:url value="/system/office-list" var="officeUrl"/>
<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Danh sách tổ chức công chứng / phường xã</span>
</div>
<div class="col-md-12">
    <form class="form-horizontal" action="${officeUrl}" method="get" id="search-frm">
        <input type="hidden" name="listNumber"  value="${officeListForm.listNumber}">
        <input type="hidden" name="totalPage" id="totalPage" value="${officeListForm.totalPage}">
        <input type="hidden" name="page" id="page" value="${officeListForm.page}">
        <div class="panel-body">

            <div class="form-group">
                <label class="col-md-2 control-label label-bam-trai">Tên nhóm ngân hàng</label>
                <div class="col-md-3">
                    <input type="text" class="form-control" name="titleFilter" value="${officeListForm.titleFilter}">
                </div>
                <div class="col-md-2">

                    <input type="submit" class="form-control luu" name="" value="Tìm kiếm">
                </div>
            </div>

        </div>

        <div class="panel-body">
            <div class="col-md-2 col-md-offset-10 export-button">
                <a href="${createOfficeUrl}"><input type="button" class="form-control luu" name="" value="Thêm mới"></a>
            </div>
            <table class="table" style="margin-bottom:0%">

                <tr class="border-table">
                    <th class=" ann-title border-table table-giua">STT</th>
                    <th class=" ann-title border-table table-giua">Tên ngân hàng</th>
                    <th class=" ann-title border-table table-giua">Mã ngân hàng</th>
                    <th class=" ann-title border-table table-giua">Trạng thái hoạt động</th>
                </tr>
                <c:if test="${officeListForm.listNumber ==0}">
                    <tr>
                        <td colspan="7"
                            style="height: 100%;background-color: #ececec; line-height: 5.429;text-align: center;font-size: 365%">
                            Chưa có dữ liệu
                        </td>
                    </tr>
                </c:if>
                <c:if test="${officeListForm.listNumber > 0}">
                    <c:forEach items="${officeListForm.bankList}" var="item" varStatus="index" >
                        <tr>
                            <td class="border-table align-giua  ">${index.index +1}</td>
                            <td class="border-table" style="color: black;"><a href="${updateOfficeUrl}/${item.bid}">${item.name}</a></td>
                            <td class="border-table" style="color: black;">${item.code}</td>
                            <c:if test="${item.active == 1}">
                                <td class="border-table align-giua" style="color: black;">Đang hoạt động</td>
                            </c:if>
                            <c:if test="${item.active != 1}">
                                <td class="border-table align-giua" style="color: black;">Ngừng hoạt động</td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    <tr class="table-tr-xam">
                        <th colspan="4">Tổng số ${officeListForm.listNumber} dữ
                            liệu
                            <div class="align-phai">
                                <img onclick="firstPage()"
                                     class="pagging-icon"
                                     src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                <img onclick="previewPage()"
                                     class="pagging-icon"
                                     src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                    ${officeListForm.page}
                                trên ${officeListForm.totalPage}
                                <img onclick="nextPage()"
                                     class="pagging-icon"
                                     src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                                <img onclick="lastPage()"
                                     class="pagging-icon"
                                     src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                            </div>
                        </th>
                    </tr>
                </c:if>
            </table>
        </div>
    </form>
</div>

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
        var totalPage = $('#totalPage').val();
        $('#page').val(totalPage);
        $("#search-frm").submit();
    }
</script>
<jsp:include page="/WEB-INF/pages/layout/footer.jsp"/>

<script>
    $(document).ready(function () {
        var parentItem = $("#quan-tri-he-thong");
        $(parentItem).click();
        $("#ds-to-chuc").addClass("child-menu");
    });
</script>