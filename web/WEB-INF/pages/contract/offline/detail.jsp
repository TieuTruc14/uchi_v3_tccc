<%@ page import="com.vn.osp.context.CommonContext" %>
<%@ page import="com.vn.osp.common.util.SystemProperties" %>
<%@ page import="com.vn.osp.common.util.ValidationPool" %>
<%@ page import="com.vn.osp.common.global.Constants" %><%--
  Created by IntelliJ IDEA.
  User: TienManh
  Date: 5/18/2017
  Time: 2:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />
<script type="text/javascript">var id = '${contract.id}';
var from='${contract.jsonstring}';
var code='${contract.bank_code}';
var template_id='${contract.contract_template_id}';
var notary_id='${contract.notary_id}';
var drafter_id='${contract.drafter_id}';
var url='<%=SystemProperties.getProperty("url_config_server_api")%>';
var contextPath='<%=request.getContextPath()%>';
</script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/contract/uchi.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/contract/editor.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/js/tree/tree.css" type="text/css" />
<script src="<%=request.getContextPath()%>/static/js/contract/exportWord/FileSaver.js"></script>
<script src="<%=request.getContextPath()%>/static/js/contract/exportWord/jquery.wordexport.js"></script>

<script src="<%=request.getContextPath()%>/static/js/tree/tree.js"></script>


<script src="<%=request.getContextPath()%>/static/js/contract/offline/contract.detail.js" type="text/javascript"></script>


<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Chi tiết hợp đồng công chứng</span>
</div>
<div class="truong-form-chinhbtt" ng-app="osp" ng-controller="contractDetailController">

    <div class="panel-group" id="accordion">
        <div class="form-horizontal" action="${editUrl}" method="post">
            <div class="panel panel-default" id="panel1">
                <div class="panel-heading">
                    <h4 class="panel-title">Thông tin hợp đồng</h4>
                </div>
                <div class="panel-body">
                    <input type="hidden" ng-model="contract.entry_user_id"  ng-init="contract.entry_user_id='<%=((CommonContext)request.getSession().getAttribute(request.getSession().getId())).getUser().getUserId()%>'" >
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

                    <div class="row truong-inline-field" ng-if="contractTemplate.period_flag==1" >
                        <div class="form-group">
                            <label class="col-md-3 control-label  label-bam-trai">Thời hạn</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" name="contract.contract_period" ng-model="contract.contract_period" id="periodDate" disabled />
                            </div>
                        </div>
                    </div>

                    <div ng-if="contractTemplate.mortage_cancel_func==1">
                        <div class="row truong-inline-field" >
                            <div class="form-group">
                                <label class="col-sm-3 control-label label-bam-trai">Giải chấp</label>
                                <div class="col-sm-9" ng-switch on="contract.mortage_cancel_flag">
                                    <label ng-switch-when="1">Đã giải chấp <label ng-if="contract.mortage_cancel_date.length>0">ngày {{contract.mortage_cancel_date}}</label></label>
                                    <label ng-switch-default>Chưa giải chấp </label>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>

            <div ng-switch on="contract.kindhtml.length>0">
                <div ng-switch-when="false">
                    <div class="panel panel-default">
                        <header class="panel-heading font-bold" style="margin-bottom: 20px;">
                            <h4 class=" panel-title">Thông tin đương sự</h4>
                        </header>
                        <div class="panel-body">
                            <section class="panel panel-default" ng-repeat="(itemIndex,item) in privys.privy track by $index">
                                <header class="panel-heading font-bold" style="margin-bottom: 20px;">
                                    <label class="control-label  label-bam-trai" style="color:#2a2aff">{{item.name}}:</label>
                                </header>
                                <div class="panel-body" >
                                    <section class="panel panel-default" ng-repeat="item1 in item.persons track by $index">
                                        <header class="panel-heading font-bold" style="margin-bottom: 20px;">
                                            <label class=" control-label  label-bam-trai" style="color:#2a2aff">Đương sự {{$index+1}}</label>
                                        </header>
                                        <div class="panel-body" ng-switch on="item1.template==2">
                                            <div ng-switch-when=true>
                                                <div class="form-group">
                                                    <div class="col-md-3">
                                                        <label class="col-sm-12 control-label label-bam-trai">Tên công ty</label>
                                                    </div>
                                                    <div class="col-sm-9"  >
                                                        <input type="text" class="form-control" ng-model="item1.org_name" disabled />
                                                    </div>

                                                </div>
                                                <div class="form-group">
                                                    <div class="col-md-3">
                                                        <label class="col-sm-12 control-label label-bam-trai">Mã số thuế</label>
                                                    </div>
                                                    <div class="col-sm-9"  >
                                                        <input type="text" class="form-control" ng-model="item1.org_code" disabled />
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-md-3">
                                                        <label class="col-sm-12 control-label label-bam-trai">Địa chỉ công ty</label>
                                                    </div>
                                                    <div class="col-sm-9" >
                                                        <input type="text" class="form-control" ng-model="item1.org_address"  disabled/>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-md-3">
                                                        <label class="col-sm-12 control-label label-bam-trai">Họ tên người đại diện</label>
                                                    </div>
                                                    <div class="col-sm-9"  >
                                                        <input type="text" class="form-control" ng-model="item1.name" disabled />
                                                    </div>

                                                </div>
                                            </div>
                                            <div ng-switch-default>
                                                <div class="form-group">
                                                    <div class="col-md-3">
                                                        <label class="col-sm-12 control-label label-bam-trai">Họ tên</label>
                                                    </div>
                                                    <div class="col-sm-9" >
                                                        <input type="text" class="form-control" ng-model="item1.name"  disabled/>
                                                    </div>

                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-md-3">
                                                    <label class="col-sm-12 control-label label-bam-trai">Ngày sinh</label>
                                                </div>
                                                <div class="col-sm-9"  >
                                                    <input type="text" class="form-control" ng-model="item1.birthday" id="birthday{{itemIndex}}-{{$index}}" disabled />
                                                </div>

                                            </div>
                                            <div class="form-group"  >
                                                <div class="col-md-3">
                                                    <label class="col-sm-12 control-label label-bam-trai">CMT/Hộ chiếu</label>
                                                </div>

                                                <div class="col-sm-9 " >
                                                    <input type="text" class="form-control" ng-model="item1.passport" disabled="true" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-md-3">
                                                    <label class="col-sm-12 control-label label-bam-trai">Ngày cấp</label>
                                                </div>
                                                <div class="col-md-3">
                                                    <input type="text" class="form-control" ng-model="item1.certification_date" id="certification{{itemIndex}}-{{$index}}" disabled="true" />
                                                </div>
                                                <div class="col-md-3">
                                                    <label class="col-sm-12 control-label">Nơi cấp</label>
                                                </div>

                                                <div class="col-md-3">
                                                    <input type="text" class="form-control" ng-model="item1.certification_place" disabled="true" />
                                                </div>


                                            </div>
                                            <div class="form-group" >
                                                <div class="col-md-3">
                                                    <label class="col-sm-12 control-label label-bam-trai">Địa chỉ</label>
                                                </div>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" ng-model="item1.address" disabled="true" />
                                                </div>
                                            </div>
                                            <div class="form-group" >
                                                <div class="col-md-3">
                                                    <label class="col-sm-12 control-label label-bam-trai">Mô tả</label>
                                                </div>
                                                <div class="col-sm-9 ">
                                                    <input type="text" class="form-control" ng-model="item1.description" disabled="true" />
                                                </div>
                                            </div>
                                        </div>
                                    </section>

                                </div>

                            </section>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <header class="panel-heading font-bold" style="margin-bottom: 20px;">
                            <h4 class=" panel-title">Thông tin tài sản</h4>
                        </header>
                        <div class="panel-body">
                            <section class="panel panel-default" ng-repeat="item in listProperty.properties track by $index">
                                <header class="panel-heading font-bold">
                                    <label class="control-label  label-bam-trai" style="color:#2a2aff">Tài sản {{$index+1}}</label>

                                </header>
                                <div class="panel-body">
                                    <div class="form-group" style="padding-right: 20px;padding-left: 20px">
                                        <label class="col-sm-3 control-label  label-bam-trai">Loại tài sản</label>
                                        <div class="col-sm-4 input-group" >
                                            <select name="type" ng-model="item.type" class="form-control" disabled>
                                                <option value="01">Nhà đất</option>
                                                <option value="02">Ôtô-xe máy</option>
                                                <option value="99">Tài sản khác</option>
                                            </select>
                                            <span class="input-group-btn">
                                            <a class="btn btn-primary btn-sm" style="margin-left:10px;"  ng-click="showDetails= !showDetails" type="button">
                                                <div ng-switch on="showDetails">
                                                    <div ng-switch-when=true>
                                                        Thông tin đơn giản
                                                    </div>
                                                    <div ng-switch-default>
                                                        Thông tin chi tiết
                                                    </div>
                                                </div>
                                            </a>
                                            </span>
                                        </div>

                                    </div>

                                    <div  ng-show="!showDetails">
                                        <div class="form-group" style="padding-right: 20px;padding-left: 20px">
                                            <label class="col-sm-3 control-label label-bam-trai">Thông tin tài sản</label>
                                            <div class="col-sm-9 input-group">
                                                <textarea class="form-control" rows="5" name="propertyInfo" ng-model="item.property_info"  disabled="true"></textarea>
                                            </div>
                                        </div>
                                    </div>

                                    <div ng-show="showDetails">
                                        <div ng-show="item.type=='01'">
                                            <div class="form-group" style="padding-right: 20px;padding-left: 20px" >
                                                <label class="col-sm-3 control-label label-bam-trai">Địa chỉ</label>
                                                <div class="col-sm-9 input-group">
                                                    <input type="text" class="form-control" ng-model="item.land.land_address"  disabled="true"/>
                                                </div>
                                            </div>
                                            <div class="form-group" style="padding-right: 20px;padding-left: 20px">
                                                <label class="col-sm-3 control-label label-bam-trai">Số giấy chứng nhận</label>
                                                <div class="col-sm-9 input-group">
                                                    <input type="text" class="form-control"  ng-model="item.land.land_certificate"  disabled="true"/>
                                                </div>
                                            </div>

                                            <div class="form-group" style="padding-right: 20px;padding-left: 20px">
                                                <label  class="col-sm-3 control-label  label-bam-trai">Nơi cấp</label>
                                                <div class="col-sm-3" style="padding: 0px 0px!important;">
                                                    <input type="text"  class="form-control"  ng-model="item.land.land_issue_place" disabled>
                                                </div>

                                                <label class="col-sm-3 control-label ">Ngày cấp</label>
                                                <div class="col-sm-3 " style="padding: 0px 0px!important;">
                                                    <input type="text" class="form-control" ng-model="item.land.land_issue_date" id="landDate{{$index}}"  disabled="true"/>
                                                </div>
                                            </div>


                                            <div class="form-group" style="padding-right: 20px;padding-left: 20px">

                                                <label class="col-md-3 control-label  label-bam-trai">Thửa đất số</label>
                                                <div class="col-md-3" style="padding: 0px 0px!important;">
                                                    <input type="text"class="form-control"   ng-model="item.land.land_number"  disabled="true"/>
                                                </div>

                                                <label  class="col-md-3 control-label ">Tờ bản đồ số</label>
                                                <div class="col-md-3 " style="padding: 0px 0px!important;">
                                                    <input type="text"  class="form-control"  ng-model="item.land.land_map_number"  disabled="true" />
                                                </div>


                                            </div>

                                            <div class="form-group" style="padding-right: 20px;padding-left: 20px">
                                                <label class="col-sm-3 control-label label-bam-trai">Tài sản gắn liền với đất</label>
                                                <div class="col-sm-9 input-group">
                                                    <input type="text" class="form-control" ng-model="item.land.land_associate_property" disabled="true" />
                                                </div>
                                            </div>

                                        </div>
                                        <div ng-show="item.type=='02'">
                                            <div class="form-group" style="padding-right: 20px;padding-left: 20px">
                                                <label class="col-sm-3 control-label label-bam-trai">Biển kiểm soát</label>
                                                <div class="col-sm-9 input-group">
                                                    <input type="text" class="form-control"  ng-model="item.vehicle.car_license_number" disabled="true" />
                                                </div>
                                            </div>
                                            <div class="form-group" style="padding-right: 20px;padding-left: 20px">
                                                <label class="col-sm-3 control-label label-bam-trai">Số giấy đăng ký</label>
                                                <div class="col-sm-9 input-group">
                                                    <input type="text" class="form-control"  ng-model="item.vehicle.car_regist_number" disabled="true"/>
                                                </div>
                                            </div>

                                            <div class="form-group"  style="padding-right: 20px;padding-left: 20px">

                                                <label class="col-md-3 control-label  label-bam-trai">Nơi cấp</label>
                                                <div class="col-md-3" style="padding: 0px 0px!important;">
                                                    <input type="text" class="form-control"   ng-model="item.vehicle.car_issue_place" disabled="true" />
                                                </div>

                                                <label  class="col-md-3 control-label ">Ngày cấp</label>
                                                <div class="col-md-3 " style="padding: 0px 0px!important;">
                                                    <input type="text" class="form-control" ng-model="item.vehicle.car_issue_date" id="carDate{{$index}}"  disabled="true"/>
                                                </div>

                                            </div>


                                            <div class="form-group"  style="padding-right: 20px;padding-left: 20px">

                                                <label  class="col-md-3 control-label  label-bam-trai">Số khung</label>
                                                <div class="col-md-3" style="padding: 0px 0px!important;">
                                                    <input type="text"class="form-control"   ng-model="item.vehicle.car_frame_number"  disabled="true"/>
                                                </div>

                                                <label class="col-md-3 control-label ">Số máy</label>
                                                <div class="col-md-3 " style="padding: 0px 0px!important;">
                                                    <input type="text"  class="form-control"  ng-model="item.vehicle.car_machine_number"  disabled="true"/>
                                                </div>
                                            </div>

                                        </div>

                                        <div ng-show="item.type=='99'">
                                            <div class="form-group" style="padding-right: 20px;padding-left: 20px">
                                                <label class="col-sm-3 control-label label-bam-trai">Thông tin tài sản</label>
                                                <div class="col-sm-9 input-group">
                                                    <textarea type="text" class="form-control" rows="5" ng-model="item.property_info"  disabled="true"></textarea>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group" style="padding-right: 20px;padding-left: 20px">
                                            <label class="col-sm-3 control-label label-bam-trai">Thông tin chủ sở hữu</label>
                                            <div class="col-sm-9 input-group">
                                                <input type="text" class="form-control" ng-model="item.owner_info"  disabled="true"/>
                                            </div>
                                        </div>
                                        <div class="form-group" style="padding-right: 20px;padding-left: 20px">
                                            <label class="col-sm-3 control-label label-bam-trai">Thông tin khác</label>
                                            <div class="col-sm-9 input-group">
                                                <input type="text" class="form-control" ng-model="item.other_info"  disabled="true"/>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </section>
                        </div>
                    </div>

                </div>
                <div ng-switch-default>
                    <div class="panel panel-default">
                        <header class="panel-heading font-bold" style="margin-bottom: 20px;">
                            <h4 class=" col-md-6  panel-title">Nội dung hợp đồng</h4>
                        </header>

                        <div class="panel-body" >
                            <div  id="copyContract"></div>
                            <div class="contractContent" style="margin:auto;width:800px;">
                                <div id="divcontract"  class="divcontract" class=" pull-right " style="margin:auto!important;align-content:center;width:745px;padding:20px 54px;background: #fff;height:800px;overflow: auto; float:left;font-size: 14pt;line-height:1.5;font-family: times new roman;" >
                                    <div dynamic="contract.kindhtml" id="contentKindHtml" ></div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <script>
                        $("#divcontract").bind('click',function(e) {
                            $(e.target).attr("contenteditable", "false");
                            $("#divcontract").removeAttr("contenteditable");
                        });
                    </script>
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
                                            <label class="col-sm-4 control-label label-bam-trai">Tình trạng hợp đồng</label>
                                            <div class="col-sm-8" >
                                                <div class="radio" >
                                                    <label>
                                                        <input type="checkbox"  ng-model="checkBank"   ng-click="showDescrip=!showDescrip" disabled="true"/>
                                                        Hợp đồng cần bổ sung hồ sơ
                                                    </label>
                                                </div>

                                            </div>
                                        </div>
                                        <div class="form-group" ng-show="showDescrip">
                                            <label class="col-md-4 control-label label-bam-trai">Mô tả</label>
                                            <div class="col-md-8">
                                                <textarea  rows="3" ng-model="contract.addition_description" class="form-control" placeholder="" disabled="true"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label label-bam-trai"></label>
                                            <div class="col-sm-8" >

                                                <div class="radio" >
                                                    <label>
                                                        <input type="checkbox"  ng-model="checkError"   ng-click="showError=!showError" disabled>
                                                        Hợp đồng lỗi
                                                    </label>
                                                </div>

                                            </div>
                                        </div>
                                        <div class="form-group" ng-show="showError">
                                            <label class="col-lg-4 control-label label-bam-trai">Mô tả lỗi</label>
                                            <div class="col-lg-8">
                                                <textarea  rows="3" ng-model="contract.error_description" class="form-control" placeholder="" disabled></textarea>
                                            </div>
                                        </div>
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

                <div ng-switch on="contract.kindhtml.length>0">
                    <%--<div ng-if="contract.kindhtml.length>0">--%>
                    <div ng-switch-when="true">
                        <div class="list-buttons" style="text-align: center;">
                            <%
                                if(ValidationPool.checkRoleDetail(request,"11", Constants.AUTHORITY_SUA)){
                            %>
                            <a href="<%=request.getContextPath()%>/contract/edit/{{contract.id}}?from=1" ng-show="checkOnline" class="btn btn-s-md btn-info">Chỉnh sửa</a>
                            <a href="<%=request.getContextPath()%>/contract/edit/{{contract.id}}" ng-show="!checkOnline" class="btn btn-s-md btn-info">Chỉnh sửa</a>
                            <%
                                }
                                if(ValidationPool.checkRoleDetail(request,"11", Constants.AUTHORITY_THEM)){
                            %>
                            <a href="<%=request.getContextPath()%>/contract/addCoppy/{{contract.id}}?from=1" ng-show="checkOnline" class="btn btn-s-md btn-info">Sao chép HĐ</a>
                            <a href="<%=request.getContextPath()%>/contract/addCoppy/{{contract.id}}" ng-show="!checkOnline" class="btn btn-s-md btn-info">Sao chép HĐ</a>
                            <a ng-if="!contract.cancel_status" href="<%=request.getContextPath()%>/contract/cancel/{{contract.id}}?from=1" ng-show="checkOnline" class="btn btn-s-md btn-danger">Hủy hợp đồng</a>
                            <a ng-if="!contract.cancel_status" href="<%=request.getContextPath()%>/contract/cancel/{{contract.id}}" ng-show="!checkOnline" class="btn btn-s-md btn-danger">Hủy hợp đồng</a>
                            <%
                                }
                            %>
                            <a ng-click="viewAsDoc()" class="btn btn-s-md btn-primary">Xem online</a>
                            <a ng-click="downloadWord()" class="btn btn-s-md btn-primary">Xuất file word</a>
                            <a href="<%=request.getContextPath()%>/contract/temporary/list" ng-show="checkOnline" class="btn btn-s-md btn-default">Quay lại danh sách</a>
                            <a href="<%=request.getContextPath()%>/contract/list" ng-show="!checkOnline" class="btn btn-s-md btn-default">Quay lại danh sách</a>
                        </div>


                    </div>
                    <div ng-switch-default>
                        <div class="list-buttons" style="text-align: center;">
                            <%
                                if(ValidationPool.checkRoleDetail(request,"11", Constants.AUTHORITY_SUA)){
                            %>
                            <a href="<%=request.getContextPath()%>/contract/edit/{{contract.id}}?from=1" ng-show="checkOnline" class="btn btn-s-md btn-info">Chỉnh sửa</a>
                            <a href="<%=request.getContextPath()%>/contract/edit/{{contract.id}}" ng-show="!checkOnline" class="btn btn-s-md btn-info">Chỉnh sửa</a>
                            <%
                                }
                                if(ValidationPool.checkRoleDetail(request,"11", Constants.AUTHORITY_THEM)){
                            %>
                            <a href="<%=request.getContextPath()%>/contract/addCoppy/{{contract.id}}?from=1" ng-show="checkOnline" class="btn btn-s-md btn-info">Sao chép HĐ</a>
                            <a href="<%=request.getContextPath()%>/contract/addCoppy/{{contract.id}}" ng-show="!checkOnline" class="btn btn-s-md btn-info">Sao chép HĐ</a>
                            <a href="<%=request.getContextPath()%>/contract/addendum/{{contract.id}}?from=1" ng-show="checkOnline" class="btn btn-s-md btn-info">Tạo phụ lục</a>
                            <a href="<%=request.getContextPath()%>/contract/addendum/{{contract.id}}" ng-show="!checkOnline" class="btn btn-s-md btn-info">Tạo phụ lục</a>
                            <a ng-if="!contract.cancel_status" href="<%=request.getContextPath()%>/contract/cancel/{{contract.id}}?from=1" ng-show="checkOnline" class="btn btn-s-md btn-danger">Hủy hợp đồng</a>
                            <a ng-if="!contract.cancel_status" href="<%=request.getContextPath()%>/contract/cancel/{{contract.id}}" ng-show="!checkOnline" class="btn btn-s-md btn-danger">Hủy hợp đồng</a>
                            <%
                                }
                            %>
                            <a href="<%=request.getContextPath()%>/contract/temporary/list" ng-show="checkOnline" class="btn btn-s-md btn-default">Quay lại danh sách</a>
                            <a href="<%=request.getContextPath()%>/contract/list" ng-show="!checkOnline" class="btn btn-s-md btn-default">Quay lại danh sách</a>
                        </div>

                    </div>
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
        $('#contentKindHtml *').prop("disabled",true);
        $("#contentKindHtml").children().prop('disabled',true);
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
    $(document).ready(function(){
        //load menu
        var parentItem = $("#quan-ly-hop-dong");
        $(parentItem).click();
        $("#ds-hd-cong-chung").addClass("child-menu");

    });

</script>