<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/checkbox.js"></script>

<%--
    In sổ công chứng
--%>

<spring:url value="/report/contract-certify" var="exportUrl"/>
<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">In sổ công chứng</span>
</div>

<div class="col-md-12">
    <div class="panel-group" id="accordion">
        <form class="form-horizontal" action="${exportUrl}" method="get">
            <div class="panel-body">
                <div class="form-group">

                    <label class="col-md-2 control-label  label-bam-trai text-nowrap">Ngày công chứng</label>
                    <div class="col-md-3">
                        <select class="form-control truong-selectbox-padding select2" name="timeType" id="timeType">
                            <option value="01" ${contractCeritfyWrapper.timeType.equals('01')?"selected":""}>Trong ngày hôm nay</option>
                            <option value="02" ${contractCeritfyWrapper.timeType.equals('02')?"selected":""}>Trong tuần này</option>
                            <option value="03" ${contractCeritfyWrapper.timeType.equals('03')?"selected":""}>Trong tháng này</option>
                            <option value="04" ${contractCeritfyWrapper.timeType.equals('04')?"selected":""}>Trong năm nay</option>
                            <option value="05" ${contractCeritfyWrapper.timeType.equals('05')?"selected":""}>Trong khoảng thời gian</option>
                        </select>
                    </div>

                    <div ${contractCeritfyWrapper.timeType.equals('05')?"":"style='display: none;'"} id="dateTimeFilter">
                        <label class="col-md-1 control-label  label-bam-trai text-nowrap">Từ ngày</label>
                        <div class="col-md-2">
                            <input type="text" value="${contractCeritfyWrapper.fromDate}" class="form-control date-time truong-padding-deleteright" name="fromDate" id="fromDate" >
                            <div style="margin-top: 5px;color: red;" class="error_tooltip">${validate_msg_from_date}</div>
                        </div>
                        <label class="col-md-1 control-label  label-bam-trai text-nowrap">Đến ngày</label>
                        <div class="col-md-2">
                            <input type="text" value="${contractCeritfyWrapper.toDate}" class="form-control date-time truong-padding-deleteright" name="toDate" id="toDate">
                            <div style="margin-top: 5px;color: red;" class="error_tooltip">${validate_msg_to_date}</div>
                        </div>
                    </div>

                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label  label-bam-trai">Loại sổ</label>
                    <div class="col-md-5 control-label label-bam-trai">
                        <input class="truong-check-fix" type="radio" name="sort" value="1" checked> Sắp xếp theo số&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                        <input class="truong-check-fix" type="radio" name="sort" value="2"> Sắp xếp theo ngày
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label  label-bam-trai text-nowrap">Nhóm hợp đồng</label>
                    <div class="col-md-8 control-label label-bam-trai">
                        <input type="checkbox" class="selectAll truong-check-fix" id="selectAll" checked>
                        Tất cả

                        <ul style="list-style:none"  >
                            <li></li>
                            <c:forEach items="${contractCeritfyWrapper.contractKinds}" var="item" varStatus="i">
                                <li><input class="truong-check-fix" type="checkbox" id="cb0${i.index+1}" name="cbChild" value="${item.contract_kind_code}">&nbsp&nbsp&nbsp ${item.name}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="panel-body">
                <div class="form-group">
                    <div class=" col-md-offset-2 truong-buttontfbc-fixwidth">
                        <a href="${exportUrl}" class="truong-small-linkbt"><input type="submit" class="form-control luu  " name="" value="Tải file báo cáo"></a>
                    </div>
                </div>
            </div>
        </form>
    </div>

</div>

<script>
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

</script>
<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />
<script>
    $(document).ready(function () {
        var parentItem = $("#bao-cao-thong-ke");
        $(parentItem).click();
        $("#in-so-cong-chung").addClass("child-menu");
        $(".date-time").keypress(function(key) {
            if(key.charCode < 47 || key.charCode > 57) return false;
        });
        $(".date-time").bind("cut copy paste",function(e) {
            e.preventDefault();
        });
    });
</script>


