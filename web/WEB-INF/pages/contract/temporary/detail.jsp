<%--
  Created by IntelliJ IDEA.
  User: TienManh
  Date: 6/4/2017
  Time: 10:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.vn.osp.context.CommonContext" %>
<%@ page import="com.vn.osp.common.util.SystemProperties" %>
<%@ page import="com.vn.osp.common.util.ValidationPool" %>
<%@ page import="com.vn.osp.common.global.Constants" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />
<script type="text/javascript">var id = '${contract.tcid}';
var code='${contract.bank_code}';
var template_id='${contract.contract_template_id}';
var notary_id='${contract.notary_id}';
var drafter_id='${contract.drafter_id}';
var url='<%=SystemProperties.getProperty("url_config_server_api")%>';
var contextPath='<%=request.getContextPath()%>';
</script>


<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/contract/uchi.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/contract/editor.css" />

<script src="<%=request.getContextPath()%>/static/js/contract/exportWord/FileSaver.js"></script>
<script src="<%=request.getContextPath()%>/static/js/contract/exportWord/jquery.wordexport.js"></script>
<script src="<%=request.getContextPath()%>/static/js/contract/temporary/detail.js" type="text/javascript"></script>


<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Chi tiết hợp đồng
        <c:if test="${contract.type==0}">
            chờ ký
        </c:if>
        <c:if test="${contract.type==1}">
            đã ký
        </c:if>
        <c:if test="${contract.type==3}">
            đang chỉnh sửa
        </c:if>
        <c:if test="${contract.type==2}">
            trả về
        </c:if>
    </span>
</div>
<div class="truong-form-chinhbtt" ng-app="osp" ng-controller="temporaryDetailController">

    <div class="panel-group" id="accordion">
        <div class="form-horizontal" action="${editUrl}" method="post">
            <div class="panel panel-default" id="panel1">
                <div class="panel-heading">
                    <h4 class="panel-title">Thông tin hợp đồng</h4>
                </div>
                <div class="panel-body">
                    <input type="hidden" ng-model="contract.update_user_id"  ng-init="contract.update_user_id='<%=((CommonContext)request.getSession().getAttribute(request.getSession().getId())).getUser().getUserId()%>'" >
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label  label-bam-trai">Nhóm hợp đồng</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" name="contractKind.name"  ng-model="contractKind.name"  disabled="true" />
                            </div>
                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label  label-bam-trai">Tên hợp đồng</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" name="contractTemplate.name"  ng-model="contractTemplate.name"  disabled="true" />

                            </div>
                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label  label-bam-trai">Số hợp đồng</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" name="contract.contract_number"  ng-model="contract.contract_number"  disabled="true"/>
                            </div>

                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">

                            <label  class="col-md-3 control-label  label-bam-trai">Ngày thụ lý</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control"   ng-model="contract.received_date" id="drafterDate"  disabled="true"/>
                            </div>

                            <label class="col-md-3 control-label ">Ngày công chứng</label>
                            <div class="col-md-3 ">
                                <input type="text" class="form-control"  ng-model="contract.notary_date" id="notaryDate"  disabled="true"/>
                            </div>


                        </div>

                    </div>

                    <div class="row truong-inline-field">
                        <div class="form-group">

                            <label class="col-md-3 control-label  label-bam-trai">Chuyên viên soạn thảo</label>
                            <div class="col-md-3">
                                <select class="selectpicker select2 col-md-12 no-padding" ng-model="contract.drafter_id" disabled="true"
                                        ng-options="item.userId as item.family_name  +' ' + item.first_name  for item in drafters">
                                </select>
                            </div>

                            <label class="col-md-3 control-label ">Công chứng viên</label>
                            <div class="col-md-3">
                                <select class="selectpicker select2 col-md-12 no-padding" ng-model="contract.notary_id" disabled="true"
                                        ng-options="item.userId as item.family_name +' ' + item.first_name  for item in notarys">
                                </select>
                            </div>

                        </div>
                    </div>

                    <div class="row truong-inline-field">
                        <div class="form-group" >

                            <div class="">
                                <label class="col-md-3 control-label  label-bam-trai">Tóm tắt nội dung</label>
                                <div class="col-md-9">
                                    <textarea class="form-control" name="contract.summary" ng-model="contract.summary"  disabled="true"></textarea>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label  label-bam-trai">Giá trị hợp đồng</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" name="contract.contract_value" ng-model="contract.contract_value" format="number"  disabled="true"/>
                            </div>

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-bam-trai">Địa điểm công chứng</label>
                        <div class="col-sm-9" ng-init="contract.notary_place_flag=1">
                            <div class="radio">
                                <label>
                                    <input type="radio"  ng-model="contract.notary_place_flag" ng-value="1"  disabled="true" />
                                    Tại văn phòng
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio"  ng-model="contract.notary_place_flag"  ng-value="0"  disabled="true"/>
                                    Ngoài văn phòng
                                </label>
                                <input type="text" class="form-control" name="contract.notary_place" ng-model="contract.notary_place"  disabled="true"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="panel panel-default">
                <header class="panel-heading font-bold" style="margin-bottom: 20px;">
                    <h4 class=" panel-title">Nội dung hợp đồng</h4>
                </header>
                <div class="panel-body" >
                        <div class="contractContent" style="margin:auto;width:800px;">
                            <div id="divcontract"  class="divcontract" class=" pull-right " style="margin:auto!important;align-content:center;width:745px;padding:20px 54px;background: #fff;height:800px;overflow: auto; float:left;font-size: 14pt;line-height:1.5;font-family: times new roman;" >
                                    <div dynamic="contract.kindhtml" id="contentKindHtml"></div>
                            </div>
                        </div>

                </div>
            </div>

            <section class="panel panel-default">
                <header class="panel-heading font-bold">
                    <h4 class="control-label required label-bam-trai">Thông tin khác</h4>
                </header>
                <div class="panel-body">

                    <div class="col-sm-6">
                        <section class="panel panel-default">
                            <header class="panel-heading font-bold">Ngân hàng</header>
                            <div class="panel-body">
                                <form class="bs-example form-horizontal">

                                    <div class="form-group">
                                        <label class="col-md-4 control-label label-bam-trai">Tên ngân hàng</label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" ng-model="bank.name" placeholder="" disabled="true"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label label-bam-trai">Cán bộ tín dụng</label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" ng-model="contract.crediter_name" placeholder="" disabled="true"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label label-bam-trai">Chiết khấu</label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" ng-model="contract.bank_service_fee" placeholder="" format="number" disabled="true"/>
                                        </div>
                                    </div>

                                </form>
                            </div>
                        </section>
                        <section class="panel panel-default">
                            <header class="panel-heading font-bold">Thông tin lưu trữ</header>
                            <div class="panel-body">
                                <form class="bs-example form-horizontal">
                                    <div class="form-group">
                                        <label class="col-md-4 control-label label-bam-trai">Số tờ/trang HĐ</label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" ng-model="contract.number_of_page" placeholder="" disabled="true"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label label-bam-trai">Số bản công chứng</label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" ng-model="contract.number_copy_of_contract" placeholder="" disabled="true"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-4 control-label label-bam-trai">File đính kèm</label>
                                        <div class="col-lg-8" ng-switch on="contract.file_name.length>0">
                                            <div ng-switch-when="true">
                                                <%--<a class="underline" href="{{url}}/contract/temporary/download/{{contract.tcid}}">{{contract.file_name}}</a>--%>
                                                <a class="underline" ng-click="downloadFile()">{{contract.file_name}}</a>
                                            </div>
                                            <div ng-switch-default>
                                                <span>Không có file đính kèm</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label label-bam-trai">Lưu trữ bản gốc</label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" ng-model="contract.original_store_place" placeholder="" disabled="true"/>
                                        </div>
                                    </div>

                                </form>
                            </div>
                        </section>
                    </div>
                    <div class="col-sm-6">
                        <section class="panel panel-default">
                            <header class="panel-heading font-bold">Thu phí (VNĐ)</header>
                            <div class="panel-body">
                                <form class="bs-example form-horizontal">
                                    <div class="form-group">
                                        <label class="col-md-4 control-label label-bam-trai">Phí công chứng</label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" ng-model="contract.cost_tt91" ng-change="calculateTotal()"  format="number" disabled="true"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label label-bam-trai">Thù lao công chứng</label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" ng-model="contract.cost_draft"  ng-change="calculateTotal()"  format="number" disabled="true"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label label-bam-trai">Dịch vụ công chứng ngoài</label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" ng-model="contract.cost_notary_outsite"  ng-change="calculateTotal()"  format="number" disabled="true"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label label-bam-trai">Dịch vụ xác minh khác</label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" ng-model="contract.cost_other_determine"  ng-change="calculateTotal()"  format="number"  disabled="true"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label label-bam-trai">Tổng phí</label>
                                        <div class="col-md-8">
                                            <input type="text" ng-model="contract.cost_total"  class="form-control" placeholder="" format="number"  disabled="true"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label label-bam-trai">Ghi chú</label>
                                        <div class="col-md-8">
                                            <textarea rows="4" ng-model="contract.note" class="form-control" placeholder=""  disabled="true"></textarea>
                                        </div>
                                    </div>

                                </form>
                            </div>
                        </section>
                    </div>

                </div>
            </section>


            <div class="panel-body" style="margin-bottom:30px;">
                <div class="list-buttons" style="text-align: center;">
                    <div ng-switch on="contract.type">
                        <div ng-switch-when=0>
                            <%
                                if(ValidationPool.checkRoleDetail(request,"14", Constants.AUTHORITY_THEM)){
                            %>
                            <a ng-dissable="checkLoad" data-toggle="modal"  data-target="#toSignContract"  class="btn btn-s-md btn-success">Ký hợp đồng</a>
                            <a ng-dissable="checkLoad" data-toggle="modal"  data-target="#backContract" class="btn btn-s-md btn-success">Trả về</a>
                            <%
                                }
                                if(ValidationPool.checkRoleDetail(request,"14", Constants.AUTHORITY_SUA)){
                            %>
                            <a href="<%=request.getContextPath()%>/contract/temporary/edit/{{contract.tcid}}" class="btn btn-s-md btn-info">Chỉnh sửa</a>
                            <%
                                }
                                if(ValidationPool.checkRoleDetail(request,"14", Constants.AUTHORITY_THEM)){
                            %>
                            <a href="<%=request.getContextPath()%>/contract/temporary/addCoppy/{{contract.tcid}}" class="btn btn-s-md btn-info">Sao chép HĐ</a>
                            <%
                                }
                            %>
                            <a ng-click="viewAsDoc()" class="btn btn-s-md btn-info">Xem online</a>
                            <a ng-click="downloadWord()" class="btn btn-s-md btn-info">Xuất file word</a>
                            <%
                                if(ValidationPool.checkRoleDetail(request,"14", Constants.AUTHORITY_XOA)){
                            %>
                            <a ng-dissable="checkLoad" data-toggle="modal"  data-target="#deleteContract" class="btn btn-s-md btn-danger">Xóa</a>
                            <%
                                }
                            %>
                            <a href="<%=request.getContextPath()%>/contract/temporary/list" class="btn btn-s-md btn-default">Quay lại danh sách</a>
                        </div>
                        <div ng-switch-when=1>
                            <%
                                if(ValidationPool.checkRoleDetail(request,"11", Constants.AUTHORITY_THEM)){
                            %>
                            <a ng-dissable="checkLoad" data-toggle="modal"  data-target="#markContract" class="btn btn-s-md btn-success">Đóng dấu</a>
                            <%
                                }
                                if(ValidationPool.checkRoleDetail(request,"14", Constants.AUTHORITY_SUA)){
                            %>
                            <a href="<%=request.getContextPath()%>/contract/temporary/edit/{{contract.tcid}}" class="btn btn-s-md btn-info">Chỉnh sửa</a>
                            <%
                                }
                                if(ValidationPool.checkRoleDetail(request,"14", Constants.AUTHORITY_THEM)){
                            %>
                            <a href="<%=request.getContextPath()%>/contract/temporary/addCoppy/{{contract.tcid}}" class="btn btn-s-md btn-info">Sao chép HĐ</a>
                            <%
                                }
                            %>
                            <a ng-click="viewAsDoc()" class="btn btn-s-md btn-info">Xem online</a>
                            <a ng-click="downloadWord()" class="btn btn-s-md btn-info">Xuất file word</a>
                            <%
                                if(ValidationPool.checkRoleDetail(request,"14", Constants.AUTHORITY_XOA)){
                            %>
                            <a ng-dissable="checkLoad" data-toggle="modal"  data-target="#deleteContract"  class="btn btn-s-md btn-danger">Xóa</a>
                            <%
                                }
                            %>
                            <a href="<%=request.getContextPath()%>/contract/temporary/list" class="btn btn-s-md btn-default">Quay lại danh sách</a>
                        </div>
                        <div ng-switch-default>
                            <%
                                if(ValidationPool.checkRoleDetail(request,"14", Constants.AUTHORITY_SUA)){
                            %>
                            <a href="<%=request.getContextPath()%>/contract/temporary/edit/{{contract.tcid}}" class="btn btn-s-md btn-info">Chỉnh sửa</a>
                            <%
                                }
                                if(ValidationPool.checkRoleDetail(request,"14", Constants.AUTHORITY_THEM)){
                            %>
                            <a href="<%=request.getContextPath()%>/contract/temporary/addCoppy/{{contract.tcid}}" class="btn btn-s-md btn-info">Sao chép HĐ</a>
                            <%
                                }
                            %>
                            <a ng-click="viewAsDoc()" class="btn btn-s-md btn-info">Xem online</a>
                            <a ng-click="downloadWord()" class="btn btn-s-md btn-info">Xuất file word</a>
                            <%
                                if(ValidationPool.checkRoleDetail(request,"14", Constants.AUTHORITY_XOA)){
                            %>
                            <a ng-dissable="checkLoad" data-toggle="modal"  data-target="#deleteContract" class="btn btn-s-md btn-danger">Xóa</a>
                            <%
                                }
                            %>
                            <a href="<%=request.getContextPath()%>/contract/temporary/list" class="btn btn-s-md btn-default">Quay lại danh sách</a>
                        </div>
                    </div>

                </div>

            </div>
        </div>
    </div>

    <div class="modal fade" id="deleteContract" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Xóa Hợp đồng</h4>
                </div>
                <div class="modal-body">
                    <p>Bạn chắc chắn xóa hợp đồng này ? </p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal" ng-click="deleteContract()">Xóa</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Hủy bỏ</button>
                </div>
            </div>

        </div>
    </div>

    <div class="modal fade" id="toSignContract" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Ký hợp đồng</h4>
                </div>
                <div class="modal-body">
                    <p>Xác nhận hợp đồng này được ký ? </p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success" data-dismiss="modal" ng-click="toSign()">Đồng ý</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Hủy bỏ</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="markContract" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Đóng dấu hợp đồng</h4>
                </div>
                <div class="modal-body">
                    <p>Xác nhận hợp đồng được đóng dấu ? </p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success" data-dismiss="modal" ng-click="markContract()">Đồng ý</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Hủy bỏ</button>
                </div>
            </div>

        </div>
    </div>

    <div class="modal fade" id="showThongBao" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Thông báo</h4>
                </div>
                <div class="modal-body">
                    <p>{{thongbao}} </p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success" data-dismiss="modal" >Đồng ý</button>
                </div>
            </div>

        </div>
    </div>

    <div class="modal fade" id="backContract" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Trả lại hợp đồng</h4>
                </div>
                <div class="modal-body">
                    <p>Xác nhận trả hợp đồng? </p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success" data-dismiss="modal" ng-click="backContract()">Đồng ý</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Hủy bỏ</button>
                </div>
            </div>

        </div>
    </div>

    <div class="modal fade" id="viewContentAsWord" role="dialog" style="width:auto;">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content" style="margin:auto!important;align-content:center;width:810px;background: #fff;height:100%;min-height:500px;overflow: auto; float:left;font-size: 14pt;line-height:1.5;font-family: times new roman;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>

                </div>
                <div class="modal-body">
                    <div id="viewHtmlAsWord" >

                    </div>
                </div>

            </div>
        </div>
    </div>
    <div class="modal fade" id="errorEdit" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Error</h4>
                </div>
                <div class="modal-body">
                    <p>Có lỗi xảy ra. Hãy thử lại sau! </p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">OK</button>
                </div>
            </div>

        </div>
    </div>

</div>




<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/contract/xeditable.min.css" />
<script src="<%=request.getContextPath()%>/static/js/contract/xeditable.min.js" type="text/javascript"></script>
<script>
    $("#divcontract").bind('click',function(e) {
        $(e.target).attr("contenteditable", "false");
        $("#divcontract").removeAttr("contenteditable");
    });
    $(function () {

        $('#notaryDate').datepicker({
            format: "dd/mm/yyyy",
            language: 'vi'
        }).on('changeDate', function (ev) {
            $(this).datepicker('hide');
        });
        $('#drafterDate').datepicker({
            format: "dd/mm/yyyy",
            language: 'vi'
        }).on('changeDate', function (ev) {
            $(this).datepicker('hide');
        });
        $('#landDate').datepicker({
            format: "dd/mm/yyyy",
            language: 'vi'
        }).on('changeDate', function (ev) {
            $(this).datepicker('hide');
        });
        $('#carDate').datepicker({
            format: "dd/mm/yyyy",
            language: 'vi'
        }).on('changeDate', function (ev) {
            $(this).datepicker('hide');
        });
    });

$(document).ready(function () {
    var parentItem = $("#quan-ly-hop-dong");
    $(parentItem).click();
    $("#ds-hd-online").addClass("child-menu");
});

</script>