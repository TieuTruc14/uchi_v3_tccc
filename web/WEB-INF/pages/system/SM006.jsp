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
Lịch sử thay doi hop dong
--%>
<spring:url value="/system/contract-history" var="listUrl"/>
<spring:url value="/system/contract-history-view" var="detailUrl"/>

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Lịch sử thay đổi hợp đồng </span>
</div>
<div class="panel-group col-md-12">
    <div class=" panel-body">
        <form class="form-horizontal" id="search-frm" action="${listUrl}"  method="get">
            <input type="hidden" name="listNumber"  value="${historyContractList.total}">
            <input type="hidden" name="totalPage" id="totalPage" value="${historyContractList.totalPage}">
            <input type="hidden" name="page" id="page" value="${historyContractList.page}">



            <div class="panel-group">
                <%
                    if(ValidationPool.checkRoleDetail(request,"05", Constants.AUTHORITY_TIMKIEM)){
                %>
                <div class="form-group">
                    <div class="col-md-12">
                        <div class="row">
                            <label class="col-md-2 control-label  label-bam-trai text-nowrap">Số hợp đồng</label>
                            <div class="col-md-2">
                                <input type="text" class="form-control" name="contractNumber" id="contractNumber" value="${historyContractList.contractNumber}">
                            </div>
                            <label class="col-md-1 control-label  label-bam-phai text-nowrap">Nội dung</label>
                            <div class="col-md-4">

                                <input type="text" class="form-control" name="contractContent" id="contractContent" value="${historyContractList.contractContent}">

                            </div>
                            <div class="float-right" style="padding-right:12px" >
                                <input type="button" value="Tìm kiếm" onclick="searchHisContract()" class="form-control luu truong-button-fixwidth">
                            </div>
                        </div>
                    </div>
                </div>
                <%
                    }
                %>



            </div>

        </form>
    </div>

    <div class="col-md-12 truong-margin-footer0px">



        <table class="table" style="margin-bottom:0%">

            <tr class="border-table">
                <th class=" ann-title border-table table-giua">Số hợp đồng</th>
                <th class=" ann-title border-table table-giua">Nội dung</th>
                <th class=" ann-title border-table table-giua">Thao tác </th>
                <th class=" ann-title border-table table-giua">Người thực hiện</th>
                <th class=" ann-title border-table table-giua">Ngày thực hiện</th>
            </tr>
            <c:if test="${historyContractList.total == 0}">
                <tr>
                    <td colspan="7"
                        style="height: 100%;background-color: #ececec; line-height: 5.429;text-align: center;font-size: 265%">
                        Không có dữ liệu
                    </td>
                </tr>
            </c:if>
            <c:if test="${historyContractList.total != 0}">

                <c:forEach items="${historyContractList.historyContractList}" var="item">
                    <tr>
                        <td class="border-table align-giua"><a href="${detailUrl}/${item.contract_id}">${item.contract_number}</a></td>
                        <td class="border-table truong-text-verticel"
                            style="color: black;" >
                            <c:if test="${item.contract_content.length() <= 100}">
                                ${item.contract_content.replace('\\n', '<br>')}
                            </c:if>
                            <c:if test="${item.contract_content.length() > 100}">
                                ${item.contract_content.replace('\\n', '<br>').substring(0,100)} <img data-html="true" data-toggle="popover" data-trigger="click"  data-placement="bottom" data-content="<div class='title-green'>Chi tiết hợp đồng:</div>${item.contract_content.replace('\\n','<br>')}" src="<%=request.getContextPath()%>/static/image/xemthem.png">
                            </c:if>
                        </td>

                        <td class="border-table align-giua">${item.execute_content}</td>
                        <td class="border-table  align-giua ">
                                ${item.family_name} ${item.first_name}
                        </td>
                        <td class="border-table  align-giua ">
                                ${item.execute_date_time}
                        </td>
                    </tr>
                </c:forEach>

                <tr class="table-tr-xam">
                    <th colspan="7"><div class="truong-padding-foot-table">Tổng số <span class="truong-text-colorred">${historyContractList.total}</span>  dữ liệu</div>
                        <div class="align-phai">
                            <c:if test="${historyContractList.page == 1}">
                                <img
                                        class="truong-pagging-icon"
                                        src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                <img
                                        class="truong-pagging-icon"
                                        src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">

                            </c:if>
                            <c:if test="${historyContractList.page!=1}">
                                <img onclick="firstPage()"
                                     class="pagging-icon"
                                     src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                <img onclick="previewPage()"
                                     class="pagging-icon"
                                     src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                            </c:if>
                                ${historyContractList.page}
                            trên ${historyContractList.totalPage}

                            <c:if test="${historyContractList.page == historyContractList.totalPage}">
                                <img
                                        class="truong-pagging-icon"
                                        src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                                <img
                                        class="truong-pagging-icon"
                                        src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                            </c:if>

                            <c:if test="${historyContractList.page != historyContractList.totalPage}">
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
        </table>
    </div>
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
        var userTotalPage = $('#totalPage').val();
        $('#page').val(userTotalPage);
        $("#search-frm").submit();
    }
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
        $("[data-toggle=popover]").popover({
            html: true,
            content: function() {
                return $(this).parent().children("span").html();
            }
        });
        $("[data-toggle=popover]").on('click', function (e) {
            $("[data-toggle=popover]").not(this).popover('hide');
        });

        $("[data-toggle=popover]").click(function (e) {
            e.stopPropagation();
        });

    });

    $(document).ready(function () {
        var text = $('#key-search').val();
        var property = $('#property-information').val();
        var propertyown = $('#property-own').val();
        var keyword = text +" "+property +" "+ propertyown;
        // Determine selected options
        var options = {};
        options[0] = "separateWordSearch";
        // Remove previous marked elements and mark
        // the new keyword inside the context
        $(".highlight-content").unmark({
            done: function() {
                $(".highlight-content").mark(keyword, options);
            }
        });
    });
</script>

<jsp:include page="/WEB-INF/pages/layout/footer.jsp"/>
<script>
    $(document).ready(function () {
        var parentItem = $("#quan-tri-he-thong");
        $(parentItem).click();
        $("#ls-thay-doi-hop-dong").addClass("child-menu");
    });

    function searchHisContract(){
        $('#page').val(1);
        $("#search-frm").submit();
    }
</script>
