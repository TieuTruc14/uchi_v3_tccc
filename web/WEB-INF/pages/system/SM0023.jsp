<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />
<%--
    Danh sách Tỉnh
--%>
<spring:url value="/system/osp/province-list" var="provinceListUrl"/>
<spring:url value="/system/osp/province-view" var="provinceView"/>
<spring:url value="/system/osp/province-update" var="updateUrll" />


<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Danh sách tỉnh thành</span>
</div>
<div class="truong-form-chinhds panel-group">

    <form class="form-horizontal" action="${provinceListUrl}" id="search-frm" method="get">
        <input type="hidden" name="page" id="page" value="${provinceListForm.page}">
        <input type="hidden" name="totalPage"  value="${provinceListForm.totalPage}">
        <div class="panel-body">
            <div class="form-group">
                <label class="col-md-2 control-label label-bam-trai">Tên tỉnh</label>
                <div class="col-md-3">
                    <input type="text" class="form-control" name="name" id="name1" value="${provinceListForm.name}">
                </div>



            </div>
            <div class="form-group">


                <label class="col-md-2 control-label label-bam-trai">Mã tỉnh</label>
                <div class="col-md-3">
                    <input type="text" class="form-control" name="code" id="code" value="${provinceListForm.code}">
                </div>
                <div class="col-md-7">
                    <div class="truong-button-fixwidth-right">
                        <input type="button" class="form-control huybo" name="" value="Xóa điều kiện" onclick="clearText()">
                    </div>
                    <div class="truong-button-fixwidth-right">

                        <input type="submit" class="form-control luu" name="" value="Tìm kiếm">
                    </div>

                </div>
            </div>




        </div>

        <div class="col-md-12 truong-margin-footer0px">
            <div class="truong-re-bt col-md-12 export-button truong-image-fix">
                <a class="truong-small-linkbt" href="${updateUrll}"> <input type="button" class="form-control luu truong-image-bt "  name="" value="Cập nhật dữ liệu" ></a>

            </div>
            <table class="table" style="margin-bottom:0%" >

                <tr class="border-table">
                    <th class=" ann-title border-table table-giua col-md-3">Tên tỉnh</th>
                    <th class=" ann-title border-table table-giua col-md-2">Mã tỉnh</th>
                    <th class=" ann-title border-table table-giua">Ngày tạo</th>
                    <th class=" ann-title border-table table-giua">Ngày sửa</th>


                </tr>
                <c:if test="${provinceListForm.total > 0}">
                    <c:forEach items="${provinceListForm.provinceList}" var="item">
                        <tr>
                            <td class="border-table truong-text-verticel"><a href="${provinceView}/${item.sid}">${item.name}</a></td>
                            <td class="border-table truong-rstable-widthper15 truong-text-verticel" style="color: black;">${item.code}</td>
                            <td class="border-table align-giua truong-rstable-widthper8" style="color: black;">${item.entry_date_time}</td>
                            <td class="border-table align-giua truong-rstable-widthper8" style="color: black;">${item.update_date_time}</td>


                        </tr>
                    </c:forEach>
                    <tr class="table-tr-xam">
                        <th colspan="7"><div class="truong-padding-foot-table"> Tổng số <span style="color:red">${provinceListForm.total}</span> dữ
                            liệu</div>
                            <div class="align-phai">
                                <c:if test="${provinceListForm.page==1}">
                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                </c:if>
                                <c:if test="${provinceListForm.page!=1}">
                                    <img onclick="firstPage()"
                                         class="pagging-icon"
                                         src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                    <img onclick="previewPage()"
                                         class="pagging-icon"
                                         src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                </c:if>
                                    ${provinceListForm.page}
                                trên ${provinceListForm.totalPage}
                                <c:if test="${provinceList.page == provinceList.totalPage}">
                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                                </c:if>
                                <c:if test="${provinceListForm.page != provinceListForm.totalPage}">
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
                <c:if test="${provinceListForm.total == 0}">
                    <tr>
                        <td colspan="7"
                            style="height: 100%;background-color: #ececec; line-height: 5.429;text-align: center;font-size: 265%">
                            Chưa có dữ liệu
                        </td>
                    </tr>
                </c:if>

            </table>
        </div>
    </form>
</div>
<script>
    $(window).on('resize', function() {
        var win = $(this);
        if (win.width() < 1371) {
            $('.123').removeClass('col-md-4');
            $('.123').addClass('col-md-6');

        } else {
            $('.123').removeClass('col-md-6');
            $('.123').addClass('col-md-4');
        }
    });
</script>
<%--
<script>
    $(window).on('resize', function() {
        var win = $(this);
        if (win.width() < 1600) {
            $('.truong-re-bt').removeClass('col-md-1');
            $('.truong-re-bt').addClass('col-md-2');
            $('.truong-re-bt').removeClass('col-md-offset-11');
            $('.truong-re-bt').addClass('col-md-offset-10');

        } else {
            $('.truong-re-bt').removeClass('col-md-2');
            $('.truong-re-bt').addClass('col-md-1');
            $('.truong-re-bt').removeClass('col-md-offset-10');
            $('.truong-re-bt').addClass('col-md-offset-11');
        }
    });
</script>
--%>

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
        var provinceTotalPage = $('#provinceTotalPage').val();
        $('#page').val(provinceTotalPage);
        $("#search-frm").submit();
    }
    function clearText(){
        $('#name1').val("");
        $('#code').val("");

    }
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


