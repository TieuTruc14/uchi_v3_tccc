<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />
<%--
    Thông tin chi tiết mẫu hợp đồng
--%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/contract/editor.css" />
<script src="<%=request.getContextPath()%>/static/js/system/synContract.js" type="text/javascript"></script>
<spring:url value="/system/osp/contracttemplate-list" var="backUrl" />
<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Chi tiết thông tin mẫu hợp đồng</span>
</div>


<div class="truong-form-chinhbtt"  ng-app="osp" ng-controller="contractAddController">

    <div class="panel-group" id="accordion">
        <form class="form-horizontal" action="${updateUrl}" method="post">
            <input type="hidden" name="id" value="${contractTempList.contractTempDetail.sid}">

            <div class="panel panel-default" id="panel1">
                <div class="panel-heading">
                    <h4 class="panel-title">

                            THÔNG TIN TỈNH THÀNH

                    </h4>

                </div>
                <div class="panel-body">

                    <div class="form-group">
                        <label class="col-md-2 control-label required label-bam-trai">Tên hợp đồng</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control" name="name" value="${contractTempList.contractTempDetail.name}" disabled>
                            <div class="error_tooltip"></div>
                        </div>

                        <label class="col-md-2 control-label required label-bam-phai">Mã hợp đồng</label>
                        <div class="col-md-5">
                            <select name="code" class="form-control truong-selectbox-padding" disabled>
                                <option value="">Tất cả</option>
                                <c:forEach items="${contractTempList.listContractKind}" var="item">
                                    <option value="${item.contract_kind_code}" ${contractTempList.contractTempDetail.code==item.contract_kind_code?"selected":""}>${item.name}</option>
                                </c:forEach>
                            </select>
                            <div class="error_tooltip"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label label-bam-trai">Trạng thái </label>
                        <div class="col-md-5 control-label label-bam-trai">
                            <input class="truong-check-fix" type="radio" name="active_flg" value="1" ${contractTempList.contractTempDetail.active_flg==1?"checked":""} disabled> Đang hoạt động &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                            <input class="truong-check-fix" type="radio" name="active_flg" value="0" ${contractTempList.contractTempDetail.active_flg==0?"checked":""} disabled> Ngừng hoạt động
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label label-bam-trai">Mô tả</label>

                        <div class="col-md-10">
                            <textarea name="description" rows="4" class="form-control" value="${contractTempList.contractTempDetail.description}" disabled >${contractTempList.contractTempDetail.description}</textarea>
                            <div class="error_tooltip"></div>
                        </div>

                    </div>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-md-2 control-label label-bam-trai">Số bên liên quan</label>--%>
                        <%--<div class="col-md-3">--%>
                            <%--<input type="text" class="form-control " name="relate_object_number" value="${contractTempList.contractTempDetail.relate_object_number} "disabled>--%>
                            <%--<div class="error_tooltip"></div>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <div class="form-group">
                        <div class="col-md-offset-2">
                            <input type="checkbox" class="truong-margin-left15" name="mortage_cancel_func" value="1" ${contractTempList.contractTempDetail.mortage_cancel_func==1?"checked":""} disabled><span class="truong-font-chu" style="vertical-align: 2px;">Giải chấp</span>
                            <input type="hidden" name="mortage_cancel_func" value="0" ${contractTempList.contractTempDetail.mortage_cancel_func==0?"checked":""} disabled>


                            <input type="checkbox" class="truong-margin-left15" name="period_flag" value="1"${contractTempList.contractTempDetail.period_flag==1?"checked":""} disabled><span class="truong-font-chu" style="vertical-align: 2px;" >Thời hạn hợp đồng</span>
                            <input type="hidden" name="period_flag" value="0" ${contractTempList.contractTempDetail.period_flag==0?"checked":""} disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label label-bam-trai">HTML</label>

                        <div class="col-md-10">
                            <div class="btn-toolbar m-b-sm btn-editor" data-role="editor-toolbar"
                                 data-target="#editor">
                                <div id="editor" contenteditable="true" class="form-control" style="font-size:14pt!important;width: 742px!important;height:842px;overflow:scroll;font-family: 'Times New Roman';padding:20px 20px!important;">
                                </div>
                            </div>
                            <div id="sourcecontract" contenteditable="true" style="display:none;font-size:14px!important;width: 742px!important;height:842px;overflow:scroll;font-family: 'Times New Roman';padding:20px 20px!important;"></div>

                        </div>

                        <textarea hidden  class="col-md-12"  id="giatriKindHtml"  name="kind_html"  rows="4" value="${contractTemp.kind_html}" ></textarea>
                    </div>


                </div>
            </div>

            <div class="truong-prevent-btb">

                <div class="col-md-12" style="text-align: center;">
                    <a href="${backUrl}" class="btn btn-default">Quay lại danh sách</a>
                </div>

            </div>


        </form>
    </div>


</div>


<!-- Modal -->

<%--<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/contract/xeditable.min.css" />--%>
<%--<script src="<%=request.getContextPath()%>/static/js/contract/xeditable.min.js" type="text/javascript"></script>--%>
<%--End Modal--%>
<script>
    $(window).on('resize',function(){
        var win = $(this);
        if(win.width() < 1300){
            $('.truong-rs-bt3os').removeClass('col-md-2 col-md-offset-3');
            $('.truong-rs-bt3os').addClass('col-md-4');
            $('.truong-rs-bt3').removeClass('col-md-2');
            $('.truong-rs-bt3').addClass('col-md-4');
        }else {
            $('.truong-rs-bt3os').removeClass('col-md-4');
            $('.truong-rs-bt3os').addClass('col-md-2 col-md-offset-3');
            $('.truong-rs-bt3').removeClass('col-md-4');
            $('.truong-rs-bt3').addClass('col-md-2');

        }
    });
</script>

<script>
    $(document).ready(function () {
        $("#editor").html('${contractTempList.contractTempDetail.kind_html}');
        $('.text-duongsu').html('<a class="btn btn-success">>>Khu vực hiển thị đương sự<<</a>');
        $('.text-taisan').html('<a class="btn btn-primary">>>Khu vực hiển thị tài sản<<</a>');
    });
    $(function () {
        $('#birthday').datepicker({
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
        var parentItem = $("#quan-tri-he-thong");
        $(parentItem).click();
        $("#mau-hop-dong").addClass("child-menu");
    });
</script>


