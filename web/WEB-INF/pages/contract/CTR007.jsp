<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.vn.osp.common.util.SystemMessageProperties" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp"/>
<%--
    Báo cáo theo chuyên viên soạn thảo
--%>
<spring:url value="/report/by-user" var="userUrl"/>
<spring:url value="/report/export-by-notary" var="exportUrl"/>

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Báo cáo theo chuyên viên soạn thảo</span>
</div>
<div class="truong-form-chinhds panel-group">
    <form class="form-horizontal" id="search-frm" action="${userUrl}" method="get">
        <input type="hidden" name="page" id="page" value="${reportByUserWrapper.page}">
        <input type="hidden" name="totalPage" id="totalPage" value="${reportByUserWrapper.totalPage}">

        <div class="panel-body ">

            <%--
                                            <div class="form-group">
                                                <label class="col-md-2 control-label  label-bam-trai">Nguồn dữ liệu:</label>
                                                <div class="col-md-3">
                                                    <label class="radio-inline">
                                                        <input type="radio" value="2" name="officeType"  checked ${reportByNotaryOfficeList.officeType==2?"checked":""} onchange="this.form.submit()" >
                                                        <span >Văn phòng công chứng</span>
                                                    </label>

                                                    <label class="radio-inline" >
                                                        <input type="radio" value="3" name="officeType" ${reportByNotaryOfficeList.officeType==3?"checked":""} onchange="this.form.submit()" >
                                                        <span>Phường xã</span>
                                                    </label>


                                                </div>
                                                </div>
            --%>
            <div class="form-group">

                <label class="col-md-2 control-label  label-bam-trai">Công chứng viên:</label>
                <div class="col-md-3">

                    <select class="form-control truong-selectbox-padding" name = "notaryPersonFilter" id="notaryName" >
                        <option value=""> Tất cả </option>
                        <c:forEach items="${reportByUserWrapper.userByRoleLists}" var="item" >
                            <option value = "${item.id}" ${item.id.equals(reportByUserWrapper.notaryPersonFilter)?"selected":""} >${item.family_name} ${item.first_name} </option>
                        </c:forEach>
                    </select>

                </div>


            </div>



            <%--
                                <div class="form-group">
                                                <label class="col-md-2 control-label  label-bam-trai">Văn phòng công chứng:</label>
                                                <div class="col-md-3">
                                                        <select class="form-control" name="notaryOfficeAuthentication" id="notaryOfficeAuthentication" onchange="this.form.submit()" >
                                                            <option value="">Tất cả</option>
                                                            <c:forEach items="${reportByNotaryOfficeList.notaryOffices}" var="item">
                                                                <option value ="${item.authentication_id}" ${item.authentication_id.equals(reportByNotaryOfficeList.notaryOfficeAuthentication)?"selected":""}> ${item.name}</option>

                                                            </c:forEach>
                                                        </select>
                                                </div>

                                                <label class="col-md-2 control-label  label-bam-trai">Công chứng viên:</label>
                                                <div class="col-md-3">

                                                    <select class="form-control" name = "notaryPerson" id="notaryName" >
                                                        <option value=""> Tất cả </option>
                                                        <c:forEach items="${reportByNotaryOfficeList.selectNotaryPersons}" var="item" >
                                                            <option value = "${item.notary_name}" ${item.notary_name.equals(reportByNotaryOfficeList.notaryPerson)?"selected":""} >${item.notary_name}</option>
                                                        </c:forEach>
                                                    </select>

                                                </div>

                                            </div>
            --%>




            <div class="form-group">



                <label class="col-md-2 control-label  label-bam-trai">Ngày truy cập</label>
                <div class="col-md-10">
                    <div class="row">
                        <div class="col-md-3">

                            <select class="form-control truong-selectbox-padding" name="timeType" id="timeType">
                                <option value="01" ${reportByUserWrapper.timeType.equals('01')?"selected":""}>Trong ngày hôm nay</option>
                                <option value="02" ${reportByUserWrapper.timeType.equals('02')?"selected":""}>Trong tuần này</option>
                                <option value="03" ${reportByUserWrapper.timeType.equals('03')?"selected":""}>Trong tháng này</option>
                                <option value="04" ${reportByUserWrapper.timeType.equals('04')?"selected":""}>Trong năm nay</option>
                                <option value="05" ${reportByUserWrapper.timeType.equals('05')?"selected":""}>Trong khoảng thời gian</option>
                            </select>

                        </div>
                        <div class="col-md-9">
                            <div class="row">
                                <div ${reportByUserWrapper.timeType.equals('05')?"":"style='display: none;'"} id="dateTimeFilter">
                                    <div class="col-md-9">
                                        <div class="row">
                                            <label class="col-md-3 control-label  label-bam-phai">Từ ngày</label>
                                            <div class="col-md-3">
                                                <input type="text" value="${reportByUserWrapper.fromDate}" class="form-control date-time" name="fromDate" id="fromDate" >
                                                <div style="margin-top: 5px;color: red;" class="error_tooltip">${validate_msg_from_date}</div>
                                            </div>
                                            <label class="col-md-3 control-label  label-bam-phai">Đến ngày</label>
                                            <div class="col-md-3">
                                                <input type="text" value="${reportByUserWrapper.toDate}" class="form-control date-time" name="toDate" id="toDate">
                                                <div style="margin-top: 5px;color: red;" class="error_tooltip">${validate_msg_to_date}</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <input type="submit" value="Thống kê"  class="form-control luu">
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>



        </div>

    </form>

    <div class="panel-body truong-margin-footer0px">

        <div class=" ">
            <c:if test="${reportByUserWrapper.total >0}">
                <a href="${exportUrl}"><input type="button" class="form-control luu report-button" value="Tải file báo cáo"></a>
            </c:if>
        </div>
        <table class="table" style="margin-bottom:0%">

            <tr class="border-table">
                <th class=" ann-title border-table table-giua truong-rstable-widthper15">Chuyên viên soạn thảo</th>
                <th class=" ann-title border-table table-giua truong-rstable-widthper15">Số hợp đồng</th>
                <th class=" ann-title border-table table-giua truong-rstable-widthper12">Ngày công chứng</th>
                <th class=" ann-title border-table table-giua truong-rstable-widthper8">Tên hợp đồng</th>
                <th class=" ann-title border-table table-giua truong-rstable-widthper15">Bên liên quan</th>
                <th class=" ann-title border-table table-giua truong-rstable-widthper15">Nội dung</th>
                <th class=" ann-title border-table table-giua truong-rstable-widthper15">Công chứng viên</th>
            </tr>
            <c:if test="${reportByUserWrapper.total == 0}">
                <tr>
                    <td colspan="7"
                        style="height: 100%;background-color: #ececec; line-height: 5.429;text-align: center;font-size: 265%">
                        Chưa có dữ liệu
                    </td>
                </tr>
            </c:if>
            <c:if test="${reportByUserWrapper.total != 0}">

                <c:forEach items="${reportByUserWrapper.reportByUsers}" var="item">

                    <tr>
                        <td class="border-table truong-text-verticel">${item.drafter_family_name} ${item.drafter_first_name}</td>
                        <td class="border-table truong-text-verticel">${item.contract_number}</td>
                        <td class="border-table truong-text-verticel">${item.notary_date}</td>
                        <td class="border-table align-giua">${item.contract_name}</td>

                        <c:if test="${(item.relation_object_A.concat(item.relation_object_B).concat(item.relation_object_C).length()) <= 200}">
                            <td class="border-table truong-text-verticel"
                                style="color: black;" >

                                Bên A: &nbsp&nbsp ${(item.relation_object_A)} <br>
                                Bên B: &nbsp&nbsp ${(item.relation_object_B)} <br>

                                <c:if test="${item.relation_object_C != null && !item.relation_object_C.equals('')}">
                                    Bên C: &nbsp&nbsp ${(item.relation_object_C)} <br>
                                </c:if>
                            </td>
                        </c:if>
                        <div id ="relation" style="display: none">
                            <div class='title-green'>Bên A:</div>${item.relation_object_A}<div class='title-green'>Bên B:</div>${item.relation_object_B}
                            <c:if test="${item.relation_object_C != null && !item.relation_object_C.equals('')}">
                                <div class='title-green'>Bên C:</div>${item.relation_object_C}
                            </c:if>

                        </div>
                        <c:if test="${(item.relation_object_A.concat(item.relation_object_B).concat(item.relation_object_C).length()) > 200}">
                            <td class="border-table truong-text-verticel"
                                style="color: black;" >

                                    <%--<c:if test="${item.relation_object_A != null && !item.relation_object_A.equals('')}">
                                        Bên A: &nbsp&nbsp ${(item.relation_object_A)} <br>
                                    </c:if>
                                    <c:if test="${item.relation_object_B != null && !item.relation_object_B.equals('')}">
                                        Bên B: &nbsp&nbsp ${(item.relation_object_B)} <br>
                                    </c:if>--%>
                                    <%--<c:if test="${item.relation_object_C != null && !item.relation_object_C.equals('')}">
                                        Bên C: &nbsp&nbsp ${(item.relation_object_C)} <br>
                                    </c:if>--%>
                                    ${'Bên A: '.concat(item.relation_object_A).concat('<br> Bên B: ').concat(item.relation_object_B).concat('<br> Bên C: ').concat(item.relation_object_C).substring(0,200)} <img data-html="true" data-toggle="popover" data-trigger="hover"  data-placement="top" data-content="<div class='title-green'>Bên A:</div>${item.relation_object_A}<div class='title-green'>Bên B:</div>${item.relation_object_B}<c:if test='${item.relation_object_C != null && !item.relation_object_C.equals("")}'><div class='title-green'>Bên C:</div>${item.relation_object_C}</c:if>" src="<%=request.getContextPath()%>/static/image/xemthem.png">
                            </td>
                        </c:if>

                        <c:if test="${item.summary.length() <= 200}">
                            <td class="border-table truong-text-verticel"
                                style="color: black;" >${item.summary}</td>
                        </c:if>
                        <c:if test="${item.summary.length() > 200}">
                            <td class="border-table truong-text-verticel"
                                style="color: black;" >${item.summary.substring(0,200)} <img data-html="true" data-toggle="popover" data-trigger="hover"  data-placement="top" data-content="<div class='title-green'>Nội dung hợp đồng:</div>${item.summary}" src="<%=request.getContextPath()%>/static/image/xemthem.png">
                            </td>
                        </c:if>
                        <td class="border-table truong-text-verticel">${item.family_name} ${item.first_name}</td>

                    </tr>
                </c:forEach>

                <tr class="table-tr-xam">
                    <th colspan="7"><div class="truong-padding-foot-table">Tổng số <span class="truong-text-colorred">${reportByUserWrapper.total}</span> dữ
                        liệu</div>
                        <div class="align-phai">
                            <c:if test="${reportByUserWrapper.page==1}">
                                <img
                                        class="truong-pagging-icon"
                                        src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                <img
                                        class="truong-pagging-icon"
                                        src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                            </c:if>
                            <c:if test="${reportByUserWrapper.page!=1}">
                                <img onclick="firstPage()"
                                     class="pagging-icon"
                                     src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                <img onclick="previewPage()"
                                     class="pagging-icon"
                                     src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                            </c:if>
                                ${reportByUserWrapper.page}
                            trên ${reportByUserWrapper.totalPage}
                            <c:if test="${reportByUserWrapper.page==reportByUserWrapper.totalPage}">
                                <img
                                        class="truong-pagging-icon"
                                        src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                                <img
                                        class="truong-pagging-icon"
                                        src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                            </c:if>
                            <c:if test="${reportByUserWrapper.page!=reportByUserWrapper.totalPage}">
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
        $('[data-toggle="popover"]').popover();

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

</script>

<jsp:include page="/WEB-INF/pages/layout/footer.jsp"/>
<script>
    $(document).ready(function () {
        var parentItem = $("#bao-cao-thong-ke");
        $(parentItem).click();
        $("#bao-cao-theo-vpcc").addClass("child-menu");
        $(".date-time").keypress(function(key) {
            if(key.charCode < 47 || key.charCode > 57) return false;
        });
        $(".date-time").bind("cut copy paste",function(e) {
            e.preventDefault();
        });
    });
</script>
