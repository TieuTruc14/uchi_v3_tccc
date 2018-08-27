<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.vn.osp.common.util.ValidationPool" %>
<%@ page import="com.vn.osp.common.global.Constants" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp"/>

<%--
    Danh sách tài liệu
--%>
<spring:url value="/manual/list" var="listUrl"/>
<spring:url value="/manual/update-view" var="updateUrl" />
<spring:url value="/manual/download" var="downloadUrl" />
<spring:url value="/manual/add-view" var="addViewUrl" />

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Danh sách tài liệu </span>
</div>
<div class="truong-form-chinhds">
    <div class="panel-group ">
        <div id="uchi-status" class="padding-default">
            <c:if test="${successCode != null}">
                <div class="status-success"><img class="status-img" src="<%=request.getContextPath()%>/static/image/success.png">${successCode}</div>
            </c:if>
            <c:if test="${errorCode != null}">
                <div class="status-error"><img class="status-img" src="<%=request.getContextPath()%>/static/image/error.png">${errorCode}</div>
            </c:if>

        </div>

        <%
            if(ValidationPool.checkRoleDetail(request, "08", Constants.AUTHORITY_TIMKIEM)){
        %>
        <form class="form-horizontal" id="search-frm" action="${listUrl}" method="get">

            <div class="panel-body ">

                <div class="form-group">
                    <input type="hidden" name="page" id="page" value="${manualList.page}">
                    <input type="hidden" name="accessPage" id="accessPage" value="${manualList.totalPage}">
                    <div class="col-md-2">
                        <label class=" control-label  label-bam-trai">Nhập từ khóa tìm kiếm</label>
                    </div>
                    <div class="col-md-3">
                        <form>
                            <input type="text" id="title" class="form-control" name="title" value="${manualList.title}">
                        </form>
                    </div>
                    <div class="truong-button-fixwidthleft " id="timkiem" >
                        <input type="button" value="Tìm kiếm" class="form-control luu" onclick="submitForm()">
                    </div>
                </div>
            </div>
        </form>
        <%
            }
        %>
        <div class="panel-body truong-margin-footer0px">
            <%
                if(ValidationPool.checkRoleDetail(request, "08", Constants.AUTHORITY_THEM)){
            %>
            <div class="truong-re-bt col-md-12 export-button truong-image-fix">
                <a class="truong-small-linkbt" href="${addViewUrl}"> <input type="button" class="form-control luu truong-image-bt "  name="" value="Thêm mới" ></a>
            </div>
            <%
                }
            %>
            <table class="table" style="margin-bottom:0%">
                <tr class="border-table">
                    <th class=" ann-title border-table table-giua">Tiêu đề</th>
                    <th class=" ann-title border-table table-giua col-md-4">Tên tập tin</th>
                    <th class=" ann-title border-table table-giua truong-rstable-widthper12">Ngày tạo</th>
                    <th class=" ann-title border-table table-giua truong-rstable-widthper12">Ngày sửa</th>
                </tr>
                <c:if test="${manualList.total == 0}">
                    <tr>
                        <td colspan="7"
                            style="height: 100%;background-color: #ececec; line-height: 5.429;text-align: center;font-size: 265%">
                            Không có dữ liệu
                        </td>
                    </tr>
                </c:if>
                <c:if test="${manualList.total != 0}">

                    <c:forEach items="${manualList.manualList}" var="item">
                        <tr>
                            <td class="border-table truong-text-verticel  "><a href="${updateUrl}/${item.mid}">${item.title}</a></td>
                            <td class="border-table  truong-text-verticel ">
                                <c:if test="${item.file_path == null}">
                                    Không có file đính kèm
                                </c:if>
                                <c:if test="${item.file_path.trim().equals('')}">
                                    Không có file đính kèm
                                </c:if>
                                <c:if test="${item.file_path != null && item.file_path.trim().equals('') == false}">
                                    <c:forEach items="${item.listFileName}" var="file" varStatus="status">
                                        File ${status.index +1} : <a
                                            href="${downloadUrl}/${item.mid}/${status.index}">${file}</a><br>
                                    </c:forEach>
                                </c:if>
                            </td>
                            <td class="border-table align-giua  ">${item.entry_date_time}</td>
                            <td class="border-table align-giua  ">${item.update_date_time}</td>

                        </tr>
                    </c:forEach>
                    <tr class="table-tr-xam">
                        <th colspan="7"><div class="truong-padding-foot-table"> Tổng số <span class="truong-text-colorred">${manualList.total}</span> dữ liệu </div>
                            <div class="align-phai">
                                <c:if test="${manualList.page==1}">
                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                </c:if>
                                <c:if test="${manualList.page!=1}">
                                    <img onclick="firstPage()"
                                         class="pagging-icon"
                                         src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                    <img onclick="previewPage()"
                                         class="pagging-icon"
                                         src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                </c:if>

                                    ${manualList.page}
                                trên ${manualList.totalPage}
                                <c:if test="${manualList.page== manualList.totalPage}">
                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                                </c:if>
                                <c:if test="${manualList.page!= manualList.totalPage}">
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
        var userPage = $('#accessPage').val();
        $('#page').val(userPage);
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
    function submitForm() {
        $('#page').val(1);
        $('#search-frm').submit();
    };

</script>

<jsp:include page="/WEB-INF/pages/layout/footer.jsp"/>
<script>
    $(document).ready(function () {
        loadMenu();
    });
    function loadMenu() {
        $(".sidebar-nav > li > #huong-dan").addClass("father-menu");
    }
</script>
