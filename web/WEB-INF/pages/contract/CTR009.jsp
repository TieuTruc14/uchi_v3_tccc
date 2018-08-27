<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp"/>
<%--
    Báo cáo chi tiết nhóm hợp đồng
--%>
<spring:url value="/report/group" var="reportUrl"/>
<spring:url value="/report/group-detail/export" var="exportURL"/>
<spring:url value="/report/group/detail/pagging" var="paggingURL"/>


<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Báo cáo chi tiết nhóm hợp đồng</span>
</div>
<div class="truong-form-chinhbtt panel-group">
    <form class="form-horizontal" action="${paggingURL}" method="get" id="search-frm">
        <input type="hidden" name="notaryDateFromFilter" id="notaryDateFromFilter" value="${reportByGroupTotalList.notaryDateFromFilter}">
        <input type="hidden" name="notaryDateToFilter" id="notaryDateToFilter" value="${reportByGroupTotalList.notaryDateToFilter}">

        <input type="hidden" name="page" value="${reportByGroupTotalList.page}" id="page">
        <input type="hidden" name="totalPage" value="${reportByGroupTotalList.totalPage}" id="totalPage">
        <input type="hidden" name="contractListNumber" value="${reportByGroupTotalList.contractListNumber}" id="contractListNumber">
        <div class="row">
            <div class="panel-group  col-md-12 ">
                    <div class="form-group">

                                <label class="col-md-2 control-label  label-bam-trai text-nowrap" style="float: left">Nhóm hợp đồng :</label>
                                <div class="col-md-4 text-nowrap" style="float: left;">
                                    <%--<input type="text" class="form-control" value="${reportByGroupTotalList.nhomHDChiTiet}" disabled>--%>
                                        <p class="truong-can-the-p"> <b> ${reportByGroupTotalList.nhomHDChiTiet} </b></p>
                                </div>

                                <%--<c:set var = "temp" scope = "session" value = "${0}"/>
                                <c:forEach items="${reportByGroupTotalList.contractTemplates}" var="item">
                                    <c:if test="${reportByGroupTotalList.tenHD == item.contractTemplateId}">
                                        <label class="control-label">${item.name}</label>
                                        <c:set var = "temp" scope = "session" value = "${1}"/>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${temp<1}">
                                    <label class="control-label">Tất cả</label>
                                </c:if>--%>
                            </div>

                    </div>
                <div class="form-group fit-control">


                    <label class="col-md-2 control-label  label-bam-trai text-nowrap" style="float: left">Tên hợp đồng :</label>
                    <div class="col-md-4 text-nowrap" style="float: left">
                        <p class="truong-can-the-p"><b>${reportByGroupTotalList.tenHD}</b></p>
                        <%--<c:set var = "temp" scope = "session" value = "${0}"/>
                        <c:forEach items="${reportByGroupTotalList.contractTemplates}" var="item">
                            <c:if test="${reportByGroupTotalList.tenHD == item.contractTemplateId}">
                                <label class="control-label">${item.name}</label>
                                <c:set var = "temp" scope = "session" value = "${1}"/>
                            </c:if>
                        </c:forEach>
                        <c:if test="${temp<1}">
                            <label class="control-label">Tất cả</label>
                        </c:if>--%>
                    </div>

                </div>

                    <div class="form-group fit-control">


                        <div class="panel-body">
                            <div class="report-contact" class="col-md-12">
                                <p id="report-title">BÁO CÁO THỐNG KÊ HỢP ĐỒNG CÔNG CHỨNG CHI TIẾT</p>
                                <p>Từ ngày ${reportByGroupTotalList.fromDate} đến ngày ${reportByGroupTotalList.toDate}</p>
                            </div>
                        </div>


                    </div>






            <div class="panel-body truong-margin-footer0px truong-padding-top-delete">

                <div class="col-md-2 col-md-offset-10 export-button">

                        <a href="${exportURL}" class="truong-file-bao-cao truong-small-linkbt"><input type="button" class="form-control luu" value="Tải file báo cáo"></a>


                </div>
                    <table class="table" style="margin-bottom:0%">

                        <tr class="border-table">
                            <th class=" ann-title border-table table-giua truong-rstable-widthper12">Số hợp đồng</th>
                            <th class=" ann-title border-table table-giua truong-rstable-widthper12">Ngày công chứng</th>
                            <th class=" ann-title border-table table-giua truong-rstable-widthper12">Tên hợp đồng</th>
                            <th class=" ann-title border-table table-giua truong-rstable-widthper20">Bên liên quan</th>
                            <th class=" ann-title border-table table-giua">Tóm tắt nội dung</th>
                            <%--<th class=" ann-title border-table table-giua truong-rstable-widthper12">Đơn vị</th>--%>
                            <th class=" ann-title border-table table-giua truong-rstable-widthper12">Công chứng viên</th>
                        </tr>

                        <c:if test="${reportByGroupTotalList.contractListNumber < 1}">
                            <tr>
                                <td colspan="7"
                                    style="height: 100%;background-color: #ececec; line-height: 5.429;text-align: center;font-size: 365%">
                                    Chưa có dữ liệu
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${reportByGroupTotalList.contractListNumber > 0}">
                            <c:forEach items="${reportByGroupTotalList.contractList}" var="item">
                                <tr>
                                    <td class="border-table truong-text-verticel" style="color: black;">${item.contract_number}</td>
                                    <td class="border-table align-giua" style="color: black;">${item.notary_date}</td>
                                    <td class="border-table truong-text-verticel" style="color: black;">${item.contract_name}</td>
                                    <td class="border-table truong-text-verticel" style="color: black;max-width: 400px;" >
                                        <c:if test="${item.relation_object.length() <= 200}">
                                            ${item.relation_object.replace('\\n','<br>')}
                                        </c:if>
                                        <c:if test="${item.relation_object.length() > 200}">
                                            ${item.relation_object.replace('\\n','<br>').substring(0,200)} <img data-html="true" data-toggle="popover" data-trigger="hover"  data-placement="top" data-content="<div class='title-green'>Chi tiết hợp đồng:</div>${item.relation_object.replace('\\n','<br>')}" src="<%=request.getContextPath()%>/static/image/xemthem.png">
                                        </c:if>
                                    </td>
                                    <td class="border-table truong-text-verticel" style="color: black;max-width: 400px;" >
                                        <c:if test="${item.transaction_content.length() <= 200}">
                                            ${item.transaction_content.replace('\\n','<br>')}
                                        </c:if>
                                        <c:if test="${item.transaction_content.length() > 200}">
                                            ${item.transaction_content.replace('\\n','<br>').substring(0,200)} <img data-html="true" data-toggle="popover" data-trigger="hover"  data-placement="top" data-content="<div class='title-green'>Chi tiết hợp đồng:</div>${item.transaction_content.replace('\\n','<br>')}" src="<%=request.getContextPath()%>/static/image/xemthem.png">
                                        </c:if>
                                    </td>
                                    <%--<td class="border-table truong-text-verticel" style="color: black;">${item.notary_place}</td>--%>
                                    <td class="border-table truong-text-verticel" style="color: black;">${item.notary_person}</td>
                                </tr>
                            </c:forEach>
                            <tr class="table-tr-xam">
                                <th colspan="7"><div class="truong-padding-foot-table">  Tổng số <span class="truong-text-colorred">${reportByGroupTotalList.contractListNumber}</span> dữ
                                    liệu </div>
                                    <%--<c:if test="${reportByGroupTotalList.totalPage > 1}">--%>
                                    <c:if test="${reportByGroupTotalList.page !=1}">
                                    <div class="align-phai">
                                        <img onclick="firstPage()"
                                             class="pagging-icon"
                                             src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                        <img onclick="previewPage()"
                                             class="pagging-icon"
                                             src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                        </c:if>
                                        <c:if test="${reportByGroupTotalList.page ==1}">
                                        <div class="align-phai">
                                            <img
                                                 class="truong-pagging-icon"
                                                 src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                            <img
                                                 class="truong-pagging-icon"
                                                 src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                            </c:if>

                                                ${reportByGroupTotalList.page}
                                            trên ${reportByGroupTotalList.totalPage}
                                            <c:if test="${reportByGroupTotalList.page < reportByGroupTotalList.totalPage}">
                                            <img onclick="nextPage()"
                                                 class="pagging-icon"
                                                 src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                                            <img onclick="lastPage()"
                                                 class="pagging-icon"
                                                 src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                                        </div>
                                        </c:if>
                                        <c:if test="${reportByGroupTotalList.page == reportByGroupTotalList.totalPage}">
                                        <img
                                             class="truong-pagging-icon"
                                             src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                                        <img
                                             class="truong-pagging-icon"
                                             src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                                    </div>
                                    </c:if>
                                    <%--</c:if>--%>
                                    <%--<c:if test="${reportByGroupTotalList.totalPage <= 1}">
                                        <div class="align-phai">
                                            <img class="pagging-icon"
                                                 src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                            <img class="pagging-icon"
                                                 src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                                ${reportByGroupTotalList.page}
                                            trên ${reportByGroupTotalList.totalPage}
                                            <img class="pagging-icon"
                                                 src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                                            <img class="pagging-icon"
                                                 src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                                        </div>
                                    </c:if>--%>
                                </th>
                            </tr>
                        </c:if>

                    </table>




            </div>
        </div>
    </form>
    <script>
        $('[data-toggle=popover]').popover();
        $(document).ready(function () {
            $( "#timeType" ).change(function() {
                var timeType = $('#timeType option:selected').val();
                if(timeType == '05'){
                    $('#dateTimeFilter').css("display","block");
                }
                if(timeType != '05'){
                    $('#dateTimeFilter').css("display","none");
                }
            });
        });
        $(function () {
            $('#fromDate').datepicker({
                format: "dd/mm/yyyy",
                forceParse : false,
                language: 'vi'
            }).on('changeDate', function (ev) {
                $(this).datepicker('hide');
            });
            $('#toDate').datepicker({
                format: "dd/mm/yyyy",
                forceParse : false,
                language: 'vi'
            }).on('changeDate', function (ev) {
                $(this).datepicker('hide');
            });
        });
        function firstPage(){
            $('#page').val(1);
            $( "#search-frm" ).submit();
        }
        function previewPage(){
            var page = $('#page').val();
            if(page > 1){
                $('#page').val(parseInt(page) -1);
                $( "#search-frm" ).submit();
            }
        }
        function nextPage(){
            var totalpage = '${reportByGroupTotalList.totalPage}';
            var page = $('#page').val();
            if(page < totalpage){
                $('#page').val(parseInt(page) +1);
                $( "#search-frm" ).submit();
            }

        }
        function lastPage(){
            var lastPage = $('#totalPage').val();
            $('#page').val(lastPage);
            $( "#search-frm" ).submit();
        }
    </script>

    <jsp:include page="/WEB-INF/pages/layout/footer.jsp"/>
    <script>
        $(document).ready(function () {
            var parentItem = $("#bao-cao-thong-ke");
            $(parentItem).click();
            $("#bao-cao-theo-nhom").addClass("child-menu");
            $(".date-time").keypress(function(key) {
                if(key.charCode < 47 || key.charCode > 57) return false;
            });
            $(".date-time").bind("cut copy paste",function(e) {
                e.preventDefault();
            });
        });
    </script>

