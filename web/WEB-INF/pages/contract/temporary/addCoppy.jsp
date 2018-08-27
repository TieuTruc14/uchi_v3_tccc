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
var url='<%=SystemProperties.getProperty("url_config_server_api")%>';
var contextPath='<%=request.getContextPath()%>';

var userEntryId=<%=((CommonContext)request.getSession().getAttribute(request.getSession().getId())).getUser().getUserId()%>;
var org_type=<%=SystemProperties.getProperty("org_type")%>;
</script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/contract/uchi.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/contract/editor.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/js/tree/tree.css" type="text/css" />
<script src="<%=request.getContextPath()%>/static/js/tree/tree.js"></script>

<script src="<%=request.getContextPath()%>/static/js/contract/exportWord/FileSaver.js"></script>
<script src="<%=request.getContextPath()%>/static/js/contract/exportWord/jquery.wordexport.js"></script>
<script src="<%=request.getContextPath()%>/static/js/contract/temporary/add.coppy.js" type="text/javascript"></script>

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Thêm hợp đồng </span>
</div>
<div class="truong-form-chinhbtt" ng-app="osp" ng-controller="temporaryDetailController">

    <div class="panel-group" id="accordion">
        <div class="form-horizontal"  method="post">
            <form class="panel panel-default" name="myForm" id="panel1">
                <div class="panel-heading">
                    <h4 class="panel-title">Thông tin hợp đồng</h4>
                </div>
                <div class="panel-body">
                    <input type="hidden" ng-model="contract.entry_user_id"  ng-init="contract.entry_user_id='<%=((CommonContext)request.getSession().getAttribute(request.getSession().getId())).getUser().getUserId()%>'" >
                    <input type="hidden" ng-model="contract.entry_user_name" ng-init="contract.entry_user_name='<%=((CommonContext)request.getSession().getAttribute(request.getSession().getId())).getUser().getName()%>'">
                    <input type="hidden" ng-model="contract.update_user_id" ng-init="contract.update_user_id='<%=((CommonContext)request.getSession().getAttribute(request.getSession().getId())).getUser().getUserId()%>'">
                    <input type="hidden" ng-model="contract.update_user_name" ng-init="contract.update_user_name='<%=((CommonContext)request.getSession().getAttribute(request.getSession().getId())).getUser().getName()%>'">
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <div class="col-md-6">
                                <label class="col-md-6 control-label  label-bam-trai required">Nhóm hợp đồng</label>
                                <div class="col-md-6">
                                    <%--<input type="text" class="form-control" name="contractKind"  ng-model="contractKind.name"  disabled="true" />--%>
                                    <select class="selectpicker select2 col-md-12 no-padding" ng-model="contractKind.contract_kind_code" name="contractKind" ng-change="myFunc(contractKind.contract_kind_code)" required
                                            ng-options="item.contract_kind_code as item.name for item in contractKinds">
                                    </select>
                                    <span class="truong-text-colorred" ng-show="myForm.contractKind.$touched && myForm.contractKind.$invalid">Nhóm hợp đồng không thể bỏ trống.</span>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <div class="col-md-6">
                                <label class="col-md-6 control-label  label-bam-trai required">Tên hợp đồng</label>
                                <div class="col-md-6">
                                    <%--<input type="text" class="form-control" name="template"  ng-model="contractTemplate.name"  disabled="true" />--%>
                                    <select class="selectpicker select2 col-md-12 no-padding" ng-model="contract.contract_template_id" name="template" ng-change="changeTemplate(contract.contract_template_id)" required
                                            ng-options="item.code_template as item.name for item in contractTemplates">
                                    </select>
                                    <span class="truong-text-colorred" ng-show="myForm.template.$touched && myForm.template.$invalid">Tên hợp đồng không thể bỏ trống.</span>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <div class="col-md-6">
                                <label class="col-md-6 control-label  label-bam-trai required">Số hợp đồng</label>
                                <div class="col-md-6">
                                    <input type="text" class="form-control" name="contract_number"  ng-model="contract.contract_number" required />
                                    <span class="truong-text-colorred"  ng-show="myForm.contract_number.$touched && myForm.contract_number.$invalid">Trường không được bỏ trống.</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <div class="col-md-6">
                                <label  class="col-md-6 control-label  label-bam-trai required">Ngày thụ lý</label>
                                <div class="col-md-6">
                                    <input type="text" class="form-control" name="received_date"  ng-model="contract.received_date" id="drafterDate" minlength="10" maxlength="10" onkeypress="return restrictCharacters(this, event, forDate);" required  />
                                    <span class="truong-text-colorred"  ng-show="myForm.received_date.$touched && myForm.received_date.$invalid">Trường không được bỏ trống và theo định dạng dd/MM/yyyy.</span>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label class="col-md-6 control-label required">Ngày công chứng</label>
                                <div class="col-md-6 ">
                                    <input type="text" class="form-control" name="notary_date"  ng-model="contract.notary_date" id="notaryDate" ng-change="changeDateNotary(contract.notary_date)" minlength="10" maxlength="10" onkeypress="return restrictCharacters(this, event, forDate);" required  />
                                    <span class="truong-text-colorred"  ng-show="myForm.notary_date.$touched && myForm.notary_date.$invalid">Trường không được bỏ trống và theo định dạng dd/MM/yyyy.</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <div class="col-md-6">
                                <label class="col-md-6 control-label  label-bam-trai required">Chuyên viên soạn thảo</label>
                                <div class="col-md-6">
                                    <%--<input type="text" class="form-control" name="drafter_id"   ng-model="drafter.family_name" required/>--%>
                                    <select class="selectpicker select2 col-md-12 no-padding" ng-model="contract.drafter_id" required name="drafter_id"
                                            ng-options="item.userId as item.family_name +' ' + item.first_name  for item in drafters">
                                    </select>
                                    <span class="truong-text-colorred"  ng-show="myForm.drafter_id.$touched && myForm.drafter_id.$invalid">Trường không được bỏ trống.</span>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label class="col-md-6 control-label required">Công chứng viên</label>
                                <div class="col-md-6">
                                    <select class="selectpicker select2 col-md-12 no-padding" ng-model="contract.notary_id" required name="notary_id"
                                            ng-options="item.userId as item.family_name +' ' + item.first_name  for item in notarys">
                                    </select>
                                    <%--<input type="text" class="form-control" name="notary_id"  ng-model="notary.family_name" required />--%>
                                    <span class="truong-text-colorred"  ng-show="myForm.notary_id.$touched && myForm.notary_id.$invalid">Trường không được bỏ trống.</span>
                                </div>
                            </div>

                        </div>

                    </div>

                    <div class="row truong-inline-field">
                        <div class="form-group" >
                            <div class="col-md-3">
                                <label class="col-md-12 control-label  label-bam-trai">Tóm tắt nội dung</label>
                            </div>
                            <div class="col-md-9" style="padding-right:30px;">
                                <textarea class="form-control" name="contract.summary" ng-model="contract.summary" ></textarea>
                            </div>
                        </div>

                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <div class="col-md-6">
                                <label class="col-md-6 control-label  label-bam-trai">Giá trị hợp đồng</label>
                                <div class="col-md-6">
                                    <input type="text" class="form-control" name="contract.contract_value" ng-model="contract.contract_value" format="number" onkeypress="return restrictCharacters(this, event, digitsOnly);" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-3">
                            <label class="col-md-12 control-label label-bam-trai">Địa điểm công chứng</label>
                        </div>

                        <div class="col-sm-9" ng-init="contract.notary_place_flag=1">
                            <div class="radio">
                                <label>
                                    <input type="radio"  ng-model="contract.notary_place_flag" ng-value="1"   />
                                    Tại văn phòng
                                </label>
                            </div>
                            <div class="radio" style="padding-right:20px;">
                                <label>
                                    <input type="radio"  ng-model="contract.notary_place_flag"  ng-value="0"/>
                                    Ngoài văn phòng
                                </label>
                                <input type="text" class="form-control" name="contract.notary_place" ng-model="contract.notary_place" />
                            </div>
                        </div>
                    </div>


                </div>
            </form>

            <div class="panel-body" >
                <div  id="copyContract"></div>

                <div class="btn-toolbar m-b-sm btn-editor" data-role="editor-toolbar"
                     data-target="#editor"  style="margin:auto;width:800px;">
                    <div style="margin:auto;text-align: center;width:770px;">

                        <div class="btn-group"><a class="btn btn-default btn-sm" data-edit="bold" title="Bold (Ctrl/Cmd+B)">
                            <i class="fa fa-bold"></i></a>
                            <a class="btn btn-default btn-sm" data-edit="italic" title="Italic (Ctrl/Cmd+I)"><i class="fa fa-italic"></i></a>
                            <a class="btn btn-default btn-sm" data-edit="strikethrough" title="Strikethrough"> <i class="fa fa-strikethrough"></i></a>
                            <a class="btn btn-default btn-sm" data-edit="underline" title="Underline (Ctrl/Cmd+U)"> <i class="fa fa-underline"></i></a>
                        </div>
                        <div class="btn-group">
                            <a class="btn btn-default btn-sm"  data-edit="insertunorderedlist" title="Bullet list">
                                <i class="fa fa-list-ul"></i></a>
                            <a class="btn btn-default btn-sm" data-edit="insertorderedlist" title="Number list">
                                <i class="fa fa-list-ol"></i></a>
                            <a class="btn btn-default btn-sm" data-edit="outdent" title="Reduce indent (Shift+Tab)">
                                <i class="fa fa-dedent"></i></a>
                            <a class="btn btn-default btn-sm" data-edit="indent" title="Indent (Tab)">
                                <i class="fa fa-indent"></i></a>
                        </div>
                        <div class="btn-group">
                            <a class="btn btn-default btn-sm" data-edit="justifyleft" title="Align Left (Ctrl/Cmd+L)">
                                <i class="fa fa-align-left"></i>
                            </a>
                            <a class="btn btn-default btn-sm" data-edit="justifycenter"
                               title="Center (Ctrl/Cmd+E)"><i
                                    class="fa fa-align-center"></i>
                            </a>
                            <a class="btn btn-default btn-sm" data-edit="justifyright"
                               title="Align Right (Ctrl/Cmd+R)"><i
                                    class="fa fa-align-right"></i>
                            </a>
                            <a class="btn btn-default btn-sm" data-edit="justifyfull"
                               title="Justify (Ctrl/Cmd+J)"><i class="fa fa-align-justify"></i>
                            </a>
                        </div>

                        <div class="btn-group"><a class="btn btn-default btn-sm"
                                                  data-edit="undo" title="Undo (Ctrl/Cmd+Z)"><i
                                class="fa fa-undo"></i></a> <a class="btn btn-default btn-sm"
                                                               data-edit="redo"
                                                               title="Redo (Ctrl/Cmd+Y)"><i
                                class="fa fa-repeat"></i></a></div>

                        <div class="btn-group ">
                            <a ng-click="viewAsDoc()" style="margin:0px 0px;" class="btn btn-sm btn-info">Xem online</a>
                            <a ng-click="downloadWord()"style="margin:0px 2px;" class="btn btn-sm btn-info">Xuất file word</a>
                            <div class="btn-group" style="margin-top:0px;">
                                <button class="btn btn-primary btn-sm dropdown-toggle " data-toggle="dropdown"><i class="fa fa-plus"></i>đương sự-tài sản<span class="caret"></span></button>
                                <ul class="dropdown-menu">
                                    <li><a data-toggle="modal"  data-target="#addPrivysDialog" ><i class="fa fa-plus"></i>Đương sự</a></li>
                                    <li><a data-toggle="modal"  data-target="#addPropertyDialog"><i class="fa fa-plus"></i>Tài sản</a></li>
                                </ul>
                            </div>
                        </div>

                        <br><br>
                        <div class="form-group">
                            <div id="textboxofp"></div>
                        </div>

                    </div>
                    <!-- <div id="editor" class="form-control" style="font-size:14px;width: 595px;height:842px;overflow:scroll;line-height: 2.5px;font-family: 'Times New Roman';"> -->
                    <div id="editor"   class="form-control" style="margin:auto;font-size:14pt!important;width: 742px!important;height:842px;overflow:scroll;font-family: 'Times New Roman';padding:20px 20px!important;">
                        <div dynamic="contract.kindhtml" id="contentKindHtml"></div>
                    </div>
                </div>

                <textarea hidden="true" name="contentText" id="contentText"></textarea>

                <div id="sourcecontract" contenteditable="true"  style="display:none;font-size:14px!important;width: 742px!important;height:842px;overflow:scroll;font-family: 'Times New Roman';padding:20px 20px!important;"></div>
            </div>

            <%--<section class="panel panel-default">--%>
                <%--<header class="panel-heading font-bold" style="margin-bottom: 20px;">--%>
                    <%--<h4 class="panel-title ">Nội dung hợp đồng</h4>--%>

                    <%--<div class="btn-group  pull-right" style="margin-top:-25px;">--%>
                        <%--<button class="btn btn-primary btn-md dropdown-toggle " data-toggle="dropdown"><i class="fa fa-plus"></i>Thêm đương sự/tài sản<span class="caret"></span></button>--%>
                        <%--<ul class="dropdown-menu">--%>
                            <%--<li><a data-toggle="modal"  data-target="#addPrivysDialog" ><i class="fa fa-plus"></i>Đương sự</a></li>--%>
                            <%--<li><a data-toggle="modal"  data-target="#addPropertyDialog"><i class="fa fa-plus"></i>Tài sản</a></li>--%>
                        <%--</ul>--%>
                    <%--</div>--%>

                <%--</header>--%>
                <%--<div class="panel-body" style="margin:auto;width:800px;">--%>

                    <%--<div  id="copyContract"></div>--%>

                    <%--<div class="contractContent" style="float:left;">--%>
                        <%--<div id="divcontract"  class="divcontract" class=" pull-right " style="margin:auto!important;align-content:center;width:745px;padding:20px 54px;background: #fff;height:800px;overflow: auto; float:left;font-size: 14pt;line-height:1.5;font-family: times new roman;" >--%>
                            <%--<div dynamic="contract.kindhtml" id="contentKindHtml"></div>--%>

                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</section>--%>



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
                                            <%--<input type="text" class="form-control" ng-model="bank.name" placeholder="" />--%>
                                            <select class="selectpicker select2 col-md-12 no-padding" ng-model="contract.bank_code"
                                                    ng-options="bank.code as bank.name for bank in banks">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label label-bam-trai">Cán bộ tín dụng</label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" ng-model="contract.crediter_name" placeholder=""/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label label-bam-trai">Chiết khấu</label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" ng-model="contract.bank_service_fee" placeholder="" format="number" onkeypress="return restrictCharacters(this, event, digitsOnly);" />
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
                                            <input type="text" class="form-control" ng-model="contract.number_of_page" placeholder="" onkeypress="return restrictCharacters(this, event, digitsOnly);"  />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label label-bam-trai">Số bản công chứng</label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" ng-model="contract.number_copy_of_contract" placeholder="" onkeypress="return restrictCharacters(this, event, digitsOnly);"  />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-4 control-label label-bam-trai">File đính kèm</label>
                                        <div class="col-lg-8">
                                            <input type="file" file-model="myFile"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label label-bam-trai">Lưu trữ bản gốc</label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" ng-model="contract.original_store_place" placeholder="" onkeypress="return restrictCharacters(this, event, digitsOnly);"  />
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
                                            <input type="text" class="form-control" ng-model="contract.cost_tt91" ng-change="calculateTotal()"  format="number" onkeypress="return restrictCharacters(this, event, digitsOnly);"  />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label label-bam-trai">Thù lao công chứng</label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" ng-model="contract.cost_draft"  ng-change="calculateTotal()"  format="number" onkeypress="return restrictCharacters(this, event, digitsOnly);"  />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label label-bam-trai">Dịch vụ công chứng ngoài</label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" ng-model="contract.cost_notary_outsite"  ng-change="calculateTotal()"  format="number" onkeypress="return restrictCharacters(this, event, digitsOnly);" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label label-bam-trai">Dịch vụ xác minh khác</label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" ng-model="contract.cost_other_determine"  ng-change="calculateTotal()"  format="number" onkeypress="return restrictCharacters(this, event, digitsOnly);" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label label-bam-trai">Tổng phí:</label>
                                        <div class="col-md-8">
                                            <input type="text" ng-model="contract.cost_total"  class="form-control" placeholder="" format="number" onkeypress="return restrictCharacters(this, event, digitsOnly);"  />
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

            <div class="panel-body" style="margin-bottom:30px; text-align:center;">
                <div class="list-buttons" style="text-align: center;">
                    <%
                        if(ValidationPool.checkRoleDetail(request,"14", Constants.AUTHORITY_THEM)){
                    %>
                    <a ng-dissable="checkLoad" data-toggle="modal"  data-target="#addContract"  class="btn btn-s-md btn-success">Lưu</a>
                    <a ng-click="viewAsDoc()" class="btn btn-s-md btn-primary">Xem online</a>
                    <a ng-click="downloadWord()" class="btn btn-s-md btn-primary">Xuất file word</a>
                    <%
                        }
                    %>
                    <a href="<%=request.getContextPath()%>/contract/temporary/list" class="btn btn-s-md btn-default">Quay lại sanh sách</a>
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

    <div class="modal fade" id="addContract" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Thêm hợp đồng</h4>
                </div>
                <div class="modal-body">
                    <p>Xác nhận thêm hợp đồng ? </p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success" data-dismiss="modal" ng-click="addTemporary()">Đồng ý</button>
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
                    <%--<h4 class="modal-title">Hợp đồng</h4>--%>
                </div>
                <div class="modal-body">
                    <div id="viewHtmlAsWord" >

                    </div>
                </div>

            </div>
        </div>
    </div>

    <div class="modal fade" id="checkValidate" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Thiếu thông tin</h4>
                </div>
                <div class="modal-body">
                    <p>Hãy nhập đủ thông tin vào các trường bắt buộc(có dấu <span class="truong-text-colorred">*</span>) </p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">OK</button>
                </div>
            </div>

        </div>
    </div>
    <div class="modal fade" id="checkDate" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Nhập thông tin sai!</h4>
                </div>
                <div class="modal-body">
                    <p>Ngày thụ lý và ngày công chứng phải là định dạng dd/MM/yyyy. </p>
                    <p>Ngày thụ lý không thể sau ngày công chứng. Ngày công chứng không thể sau ngày hiện tại. </p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">OK</button>
                </div>
            </div>

        </div>
    </div>

    <div class="modal fade" id="errorMaxFile" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Dung lượng file đính kèm quá lớn!</h4>
                </div>
                <div class="modal-body">
                    <p>File đính kèm không vượt quá 5MB.</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">OK</button>
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


    <div class="modal fade" id="checkContractNumber" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Trùng số hợp đồng!</h4>
                </div>
                <div class="modal-body">
                    <p>Số hợp đồng này đã tồn tại. Bạn hãy thay số hợp đồng khác. </p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">OK</button>
                </div>
            </div>

        </div>
    </div>

    <div class="modal fade" id="addPrivysDialog" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content" style="width:900px;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <button class="btn btn-s-md btn-primary pull-left "  ng-click="addPrivy()"  type="button">Thêm bên liên quan</button>
                </div>
                <div class="modal-body">
                    <div class="tree well" >
                        <ul>
                            <li ng-repeat="(itemIndex,item) in privys.privy track by $index">
                                <span class="spanText"><i class="icon-folder-open"></i> {{item.name}}</span>
                                <a class="btn btn-sm btn-primary " ng-click="addPerson($index)" ><i class="fa fa-plus"></i>Thêm đương sự</a>
                                <a class="btn btn-sm btn-danger " ng-click="removePrivy($index)" ><i class="fa fa-plus"></i>Xóa</a>
                                <label ng-switch on="item.type" class="checkbox m-n i-checks pull-right">
                                    <label ng-switch-when="0" class="checkbox m-n i-checks pull-right"><input style="width:20px;margin-top:0px;" checked  type="checkbox" ng-click="checkRemoveActionPrivy($event,$index)"><i></i>Bỏ hiển thị tên bên liên quan</label>
                                    <label ng-switch-default class="checkbox m-n i-checks pull-right"><input style="width:20px;margin-top:0px;"   type="checkbox" ng-click="checkRemoveActionPrivy($event,$index)"><i></i>Bỏ hiển thị tên bên liên quan</label>
                                </label>
                                <ul>
                                    <li ng-repeat="user in item.persons track by $index">
                                        <div class="treeChild">
                                            <div class="col-md-2">
                                                <a class="btn btn-success btn-sm"><i class="icon-folder-open"></i>
                                                    <span ng-switch on="user.name.length>0"><i class="icon-minus-sign"></i>
                                                    <span ng-switch-when=true>
                                                         {{user.name}}
                                                    </span>
                                                    <span ng-switch-default>
                                                         {{$index+1}}
                                                    </span>
                                                </span>
                                                </a>
                                            </div>

                                            <div class="col-md-7 " >

                                                <select ng-model="user.template" class="form-control" ng-change="changTemplatePrivy(itemIndex,$index,user.template)" >
                                                    <option value="" >---Chọn mẫu hiển thị---</option>
                                                    <option ng-repeat="temp in templatePrivys"  value="{{temp.id}}">{{temp.name}}</option>
                                                </select>

                                            </div>
                                            <div class="col-md-3">
                                                <a id="button-duongsu{{$index}}" class="btn btn-sm btn-info" data-toggle="popover" data-html="true" data-placement="bottom" data-content="<div class='scrollable' style='height:40px'>Hãy chọn loại mẫu hiển thị.</div>" title="" data-original-title='<button type="button" class="close pull-right" data-dismiss="popover">&times;</button>Chi tiết mẫu'>Xem mẫu</a>
                                                <a class="btn btn-sm btn-danger " ng-click="removePerson(itemIndex,$index)" ><i class="fa fa-plus"></i>Xóa</a>
                                            </div>

                                        </div>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success" data-dismiss="modal" >Đóng</button>
                </div>
            </div>
        </div>
    </div>



    <div class="modal fade" id="addPropertyDialog" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content" style="width:900px;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <a class="btn btn-s-md btn-primary pull-left "  ng-click="addProperty()"  type="button"><i class="fa fa-plus"></i>Thêm tài sản</a>
                </div>
                <div class="modal-body">
                    <div class="tree well" >
                        <ul>
                            <li >
                                <a >DANH SÁCH TÀI SẢN</a>
                                <ul>
                                    <li ng-repeat="item in listProperty.properties track by $index">
                                        <div class="treeChild">
                                            <div class="col-md-2">
                                                <a class="btn btn-success btn-sm"><i class="icon-folder-open"></i> Tài sản {{$index+1}}: </a>
                                            </div>

                                            <div class="col-md-7 " style="margin-bottom:10px;">
                                                <select name="type" ng-model="item.type" class="form-control" ng-change="changeTypeProperty($index,item.type)">
                                                    <option value="">--Chọn--</option>
                                                    <option value="01">Nhà đất</option>
                                                    <option value="02">Ôtô-xe máy</option>
                                                    <option value="99">Tài sản khác</option>
                                                </select>
                                            </div>
                                            <div class="col-md-7 col-md-offset-2">
                                                <select  ng-model="item.template" class="form-control" ng-change="changTemplateProperty($index,item.template)" >
                                                    <option value="">---Chọn mẫu hiển thị---</option>
                                                    <option ng-repeat="item in listTypeTaiSan"  value="{{item.id}}">{{item.name}}</option>
                                                </select>
                                            </div>
                                            <div class="col-md-3">
                                                <a id="button-taisan{{$index}}" class="btn btn-sm btn-info" data-toggle="popover" data-html="true" data-placement="bottom" data-content="<div class='scrollable' style='height:40px'>Hãy chọn loại mẫu tài sản hiển thị.</div>" title="" data-original-title='<button type="button" class="close pull-right" data-dismiss="popover">&times;</button>Chi tiết mẫu tài sản'>Xem mẫu</a>
                                                <a class="btn btn-sm  btn-danger deleteTree" ng-click="removeProperty($index)" ><i class="fa fa-plus"></i>Xóa </a>
                                            </div>

                                        </div>
                                    </li>
                                </ul>

                            </li>
                        </ul>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success" data-dismiss="modal" >Đóng</button>
                </div>
            </div>

        </div>
    </div>

</div>


<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />

<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/contract/xeditable.min.css" />
<script src="<%=request.getContextPath()%>/static/js/contract/xeditable.min.js" type="text/javascript"></script>
<script>
//    $("#divcontract").bind('click',function(e) {
//        if (e.target.className == "inputcontract") {
//            $("#divcontract").removeAttr("contenteditable");
//            $(e.target).focus();
//        } else {
//            $(this).attr("contenteditable", "true");
//            $(e.target).focus();
//        }
//    });
    $("#divcontract").bind('click',function(e) {
        if (e.target.className == "simple" || e.target.className.split(" ")[0] == "inputcontract") {
            $("#divcontract").removeAttr("contenteditable");
            $(e.target).attr("contenteditable", "true");
            $(e.target).focus();
        } else {
            $(this).attr("contenteditable", "true");
            $(e.target).focus();
        }

    });

    $(function () {
//        $('#notaryDate').datepicker({
//            format: "dd/mm/yyyy",
//            language: 'vi'
//        }).on('changeDate', function (ev) {
//            $(this).datepicker('hide');
//        });
//        $('#drafterDate').datepicker({
//            format: "dd/mm/yyyy",
//            language: 'vi'
//        }).on('changeDate', function (ev) {
//            $(this).datepicker('hide');
//        });
        $('#landDate').datepicker({
            format: "dd/mm/yyyy",
            forceParse : false,
            language: 'vi'
        }).on('changeDate', function (ev) {
            $(this).datepicker('hide');
        });
        $('#carDate').datepicker({
            format: "dd/mm/yyyy",
            forceParse : false,
            language: 'vi'
        }).on('changeDate', function (ev) {
            $(this).datepicker('hide');
        });
    });

    $(document).ready(function () {
        var parentItem = $("#quan-ly-hop-dong");
        $(parentItem).click();
        $("#soan-hd-online").addClass("child-menu");
    });

</script>