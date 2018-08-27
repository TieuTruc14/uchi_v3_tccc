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
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/contract/uchi.css" />
<script src="<%=request.getContextPath()%>/static/js/contract/offline/contract.add.js" type="text/javascript"></script>
<script type="text/javascript">
    var url='<%=SystemProperties.getProperty("url_config_server_api")%>';
    var contextPath='<%=request.getContextPath()%>';
    var userEntryId=<%=((CommonContext)request.getSession().getAttribute(request.getSession().getId())).getUser().getUserId()%>;
    var org_type=<%=SystemProperties.getProperty("org_type")%>;
</script>

<spring:url value="/contract/list" var="backUrl" />
<spring:url value="/contract/add" var="addUrl" />
<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Thêm mới hợp đồng</span>
</div>
<div class="truong-form-chinhbtt" ng-app="osp" ng-controller="contractAddController">

    <div class="panel-group" id="accordion">
        <form class="form-horizontal" action="${addUrl}" method="post" name="myForm">
            <div class="panel panel-default" id="panel1">
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
                            <div class="col-md-12">
                                <label class="col-md-3 control-label  label-bam-trai required">Nhóm hợp đồng</label>
                                <div class="col-md-6">
                                    <select ng-model="contractKindValue" name="contractKind" class="selectpicker select2 col-md-12 no-padding" ng-change="myFunc(contractKindValue)" required>
                                        <option value="">--Chọn--</option>
                                        <option  ng-repeat="item in contractKinds" value="{{item.contract_kind_code}}">{{item.name}}</option>
                                    </select>
                                    <span class="truong-text-colorred" ng-show="myForm.contractKind.$touched && myForm.contractKind.$invalid">Nhóm hợp đồng không thể bỏ trống.</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <div class="col-md-12">
                                <label class="col-md-3 control-label  label-bam-trai required">Tên hợp đồng</label>
                                <div class="col-md-6">
                                    <select name="template" ng-model="contract.contract_template_id" class="selectpicker select2 col-md-12 no-padding" required>
                                        <option value="">--Chọn--</option>
                                        <option  ng-repeat="item in contractTemplates" value="{{item.code_template}}">{{item.name}}</option>
                                    </select>
                                    <span class="truong-text-colorred" ng-show="myForm.template.$touched && myForm.template.$invalid">Tên hợp đồng không thể bỏ trống.</span>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <div class="col-md-12">
                                <label class="col-md-3 control-label  label-bam-trai required">Số hợp đồng</label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" name="contract_number"  ng-model="contract.contract_number" required>
                                    <span class="truong-text-colorred"  ng-show="myForm.contract_number.$touched && myForm.contract_number.$invalid">Trường không được bỏ trống.</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <div class="col-md-12">
                                <label  class="col-md-3 control-label  label-bam-trai required">Ngày thụ lý</label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" name="received_date"  ng-model="contract.received_date" ng-minlength="10" maxlength="10" onkeypress="return restrictCharacters(this, event, forDate);" id="fromDate" required>
                                    <span class="truong-text-colorred"  ng-show="myForm.received_date.$touched && myForm.received_date.$invalid">Trường không được bỏ trống và theo định dạng dd/MM/yyyy.</span>
                                    <%--<span class="truong-text-colorred" ng-show="received_date.$error.minlength">Not a valid date</span>--%>
                                </div>


                                <label class="col-md-3 control-label required">Ngày công chứng</label>
                                <div class="col-md-3 ">
                                    <input type="text" class="form-control" name="notary_date" ng-model="contract.notary_date" id="toDate" ng-change="changeDateNotary(contract.notary_date)" maxlength="10" minlength="10" onkeypress="return restrictCharacters(this, event, forDate);"  required />
                                    <span class="truong-text-colorred"  ng-show="myForm.notary_date.$touched && myForm.notary_date.$invalid">Trường không được bỏ trống.</span>
                                </div>

                            </div>
                        </div>

                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <div class="col-md-12">
                                <label class="col-md-3 control-label label-bam-trai required">Chuyên viên soạn thảo</label>
                                <div class="col-md-3">
                                    <select name="drafter_id"ng-model="contract.drafter_id" class="selectpicker select2 col-md-12 no-padding" required>
                                        <option value="">--Chọn chuyên viên--</option>
                                        <option  ng-repeat="item in drafters" value="{{item.userId}}">{{item.family_name}} {{item.first_name}}</option>
                                    </select>
                                    <span class="truong-text-colorred"  ng-show="myForm.drafter_id.$touched && myForm.drafter_id.$invalid">Trường không được bỏ trống.</span>
                                </div>
                                <label class="col-md-3 control-label required">Công chứng viên</label>
                                <div class="col-md-3">
                                    <select name="notary_id"ng-model="contract.notary_id" class="selectpicker select2 col-md-12 no-padding" required>
                                        <option value="">--Chọn công chứng viên--</option>
                                        <option  ng-repeat="item in notarys" value="{{item.userId}}">{{item.family_name}} {{item.first_name}}</option>
                                    </select>
                                    <span class="truong-text-colorred"  ng-show="myForm.notary_id.$touched && myForm.notary_id.$invalid">Trường không được bỏ trống.</span>
                                </div>
                        </div>

                        </div>

                    </div>

                    <div class="row truong-inline-field">
                        <div class="form-group" >
                            <div class="col-md-12">
                                <label class="col-md-3 control-label  label-bam-trai">Tóm tắt nội dung</label>
                                <div class="col-md-9" style="padding-right: 30px;">
                                        <textarea class="form-control" name="contract.summary" ng-model="contract.summary"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <div class="col-md-12">
                                <label class="col-md-3 control-label  label-bam-trai">Giá trị hợp đồng</label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" name="contract.contract_value" ng-model="contract.contract_value" onkeypress="return restrictCharacters(this, event, digitsOnly);" format="number">
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-3">
                            <label class="col-sm-12 control-label label-bam-trai">Địa điểm công chứng</label>
                        </div>
                        <div class="col-sm-9"  style="padding-right: 30px;padding-left:22px !important;">

                            <div ng-init="contract.notary_place_flag=1" />
                            <div class="radio">
                                <label>
                                    <input type="radio"  ng-model="contract.notary_place_flag"  ng-value="1">
                                    Tại văn phòng
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio"  ng-model="contract.notary_place_flag"  ng-value="0">
                                    Ngoài văn phòng
                                </label>
                                <input type="text" class="form-control" name="contract.notary_place" ng-model="contract.notary_place">
                            </div>
                        </div>

                    </div>
                </div>
            </div>


            <div class="panel panel-default">
                <header class="panel-heading font-bold" style="margin-bottom: 20px;">
                    <h4 class=" panel-title">Thông tin đương sự</h4>
                    <%--<div class=" pull-right" style="margin-top:-25px;"><button class="btn btn-s-md btn-primary" ng-click="addPrivy()"  type="button">Thêm bên liên quan</button></div>--%>
                    <div class="pull-right" style="margin:-18px -25px 0px 0px;">
                        <a  data-toggle="tooltip" title="Thêm bên liên quan" style="background-image:none;"><i class="fa fa-plus"  ng-click="addPrivy()"  style="font-size:20px;color:#65bd77;margin-right:15px;"></i></a>
                    </div>
                </header>
                <div class="panel-body">
                    <section class="panel panel-default" ng-repeat="(itemIndex,item) in privys.privy track by $index">
                        <header class="panel-heading font-bold" style="margin-bottom: 20px;">
                            <label class="control-label  label-bam-trai" style="color:#2a2aff">{{item.name}}:</label>
                            <div class="pull-right" style="margin-right:-25px;">
                                <%--<a  data-toggle="tooltip" title="Thêm đương sự" style="background-image:none;"><i class="fa fa-plus"  ng-click="addPerson($index)"  style="font-size:20px;color:#65bd77;margin-right:15px;"></i></a>--%>
                                <div class="btn-group" style="margin-top:-8px;margin-right:15px;">
                                    <button class="btn btn-primary btn-sm dropdown-toggle " data-toggle="dropdown"><i class="fa fa-plus"></i>Đương sự<span class="caret"></span></button>
                                    <ul class="dropdown-menu">
                                        <li><a ng-click="addPerson($index)" ><i class="fa fa-plus"></i>Cá nhân</a></li>
                                        <li><a ng-click="addCompany($index)"><i class="fa fa-plus"></i>Công ty</a></li>
                                    </ul>
                                </div>
                                <a class="pull-right" data-toggle="tooltip" title="Xóa bên liên quan" style="background-image:none;"><i class="fa fa-trash-o"  ng-click="removePrivy($index)"  style="font-size:20px;color:red;"></i></a>
                            </div>
                        </header>
                        <div class="panel-body" >
                            <section class="panel panel-default" ng-repeat="item1 in item.persons track by $index">
                                <header class="panel-heading font-bold" style="margin-bottom: 20px;">
                                    <label class=" control-label  label-bam-trai" style="color:#2a2aff">Đương sự {{$index+1}}</label>
                                    <div class="pull-right">
                                        <a data-toggle="tooltip" title="Xóa bên đương sự" style="background-image:none;"><i class="fa fa-trash-o"  ng-click="removePerson(itemIndex,$index)"   style="font-size:20px;color:red;"></i></a>
                                    </div>

                                </header>
                                <div class="panel-body" ng-switch on="item1.template==2">
                                    <div ng-switch-when=true>
                                        <div class="form-group">
                                            <div class="col-md-3">
                                                <label class="col-sm-12 control-label label-bam-trai">Tên công ty</label>
                                            </div>
                                            <div class="col-sm-9"  style="padding-right: 30px;">
                                                <input type="text" class="form-control" ng-model="item1.org_name"  />
                                            </div>

                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-3">
                                                <label class="col-sm-12 control-label label-bam-trai">Mã số thuế</label>
                                            </div>
                                            <div class="col-sm-9"  style="padding-right: 30px;">
                                                <input type="text" class="form-control" ng-model="item1.org_code"  />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-3">
                                                <label class="col-sm-12 control-label label-bam-trai">Địa chỉ công ty</label>
                                            </div>
                                            <div class="col-sm-9"  style="padding-right: 30px;">
                                                <input type="text" class="form-control" ng-model="item1.org_address"  />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-3">
                                                <label class="col-sm-12 control-label label-bam-trai">Họ tên người đại diện</label>
                                            </div>
                                            <div class="col-sm-9"  style="padding-right: 30px;">
                                                <input type="text" class="form-control" ng-model="item1.name"  />
                                            </div>

                                        </div>
                                    </div>
                                    <div ng-switch-default>
                                        <div class="form-group">
                                            <div class="col-md-3">
                                                <label class="col-sm-12 control-label label-bam-trai">Họ tên</label>
                                            </div>
                                            <div class="col-sm-9"  style="padding-right: 30px;">
                                                <input type="text" class="form-control" ng-model="item1.name"  />
                                            </div>

                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-md-3">
                                            <label class="col-sm-12 control-label label-bam-trai">Ngày sinh</label>
                                        </div>
                                        <div class="col-sm-9"  style="padding-right: 30px;">
                                            <input type="text" class="form-control" ng-model="item1.birthday" id="birthday{{itemIndex}}-{{$index}}" />
                                        </div>

                                    </div>
                                    <div class="form-group">
                                        <div class="col-md-3">
                                            <label class="col-sm-12 control-label label-bam-trai">CMT/Hộ chiếu</label>
                                        </div>
                                        <div class="col-sm-9"  style="padding-right: 30px;">
                                            <input type="text" class="form-control" ng-model="item1.passport" />
                                        </div>

                                    </div>

                                    <div class="form-group">
                                        <div class="col-md-6">
                                            <label class="col-md-6 control-label  label-bam-trai">Ngày cấp</label>
                                            <div class="col-md-6">
                                                <input type="text" class="form-control" ng-model="item1.certification_date" id="certification{{itemIndex}}-{{$index}}" />
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <label class="col-md-6 control-label ">Nơi cấp</label>
                                            <div class="col-md-6">
                                                <input type="text" class="form-control" ng-model="item1.certification_place" />
                                            </div>
                                        </div>

                                    </div>
                                    <div class="form-group">
                                        <div class="col-md-3">
                                            <label class="col-sm-12 control-label label-bam-trai">Địa chỉ</label>
                                        </div>
                                        <div class="col-sm-9"  style="padding-right: 30px;">
                                            <input type="text" class="form-control" ng-model="item1.address" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-md-3">
                                            <label class="col-sm-12 control-label label-bam-trai">Mô tả</label>
                                        </div>
                                        <div class="col-sm-9"  style="padding-right: 30px;">
                                            <input type="text" class="form-control" ng-model="item1.description" />
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
                    <h4 class="panel-title">Thông tin tài sản</h4>
                    <%--<div class="pull-right" style="margin-top:-25px"><button class="btn btn-s-md btn-primary " ng-click="addProperty()"  type="button">Thêm bên tài sản</button></div>--%>
                    <div class="pull-right" style="margin:-18px -25px 0px 0px;">
                        <a  data-toggle="tooltip" title="Thêm tài sản" style="background-image:none;"><i class="fa fa-plus"  ng-click="addProperty()"  style="font-size:20px;color:#65bd77;margin-right:15px;"></i></a>
                    </div>
                </header>
                <div class="panel-body">
                    <section class="panel panel-default" ng-repeat="item in listProperty.properties track by $index">
                        <header class="panel-heading font-bold">
                            <label class=" control-label  label-bam-trai" style="color:#2a2aff">Tài sản {{$index+1}}</label>
                            <div class="pull-right">
                                <a data-toggle="tooltip" title="Xóa tài sản" style="background-image:none;"><i class="fa fa-trash-o"  ng-click="removeProperty($index)"   style="font-size:20px;color:red;"></i></a>
                            </div>
                            <%--<div class="pull-right"><button class="btn btn-sm btn-danger" ng-click="removeProperty($index)"  type="button">Xóa</button></div>--%>

                        </header>
                        <div class="panel-body">
                            <div class="form-group" style="padding-right: 20px;padding-left: 20px">
                                <label class="col-sm-3 control-label  label-bam-trai">Loại tài sản</label>
                                <div class="col-sm-4 input-group" >
                                    <select name="type" ng-model="item.type" class="form-control truong-selectbox-padding" >
                                        <option value="01">Nhà đất</option>
                                        <option value="02">Ôtô-xe máy</option>
                                        <option value="99">Tài sản khác</option>
                                    </select>
                                    <span class="input-group-btn">
                                        <button class="btn btn-primary btn-sm" style="margin-left:10px;" ng-click="showDetails= !showDetails" type="button">
                                            <div ng-switch on="showDetails">
                                                <div ng-switch-when=true>
                                                    <a style="color:white;">Thông tin đơn giản</a>
                                                </div>
                                                <div ng-switch-default>
                                                    <a style="color:white;">Thông tin chi tiết</a>
                                                </div>
                                            </div>
                                        </button>
                                    </span>
                                </div>

                            </div>

                            <div  ng-show="!showDetails">
                                <div class="form-group" style="padding-right: 20px;padding-left: 20px">
                                    <label class="col-sm-3 control-label label-bam-trai">Thông tin tài sản</label>
                                    <div class="col-sm-9 input-group">
                                        <textarea class="form-control" rows="5" name="propertyInfo" ng-model="item.property_info"></textarea>
                                    </div>
                                </div>
                            </div>

                            <div ng-show="showDetails">
                                <div ng-show="item.type=='01'">
                                    <div class="form-group" style="padding-right: 20px;padding-left: 20px" >
                                        <label class="col-sm-3 control-label label-bam-trai">Địa chỉ</label>
                                        <div class="col-sm-9 input-group">
                                            <input type="text" class="form-control" ng-model="item.land.land_address"   />
                                        </div>
                                    </div>
                                    <div class="form-group" style="padding-right: 20px;padding-left: 20px">
                                        <label class="col-sm-3 control-label label-bam-trai">Số giấy chứng nhận</label>
                                        <div class="col-sm-9 input-group">
                                            <input type="text" class="form-control"  ng-model="item.land.land_certificate"   />
                                        </div>
                                    </div>

                                    <div class="form-group" style="padding-right: 20px;padding-left: 20px">
                                        <label  class="col-sm-3 control-label  label-bam-trai">Nơi cấp</label>
                                        <div class="col-sm-3" style="padding: 0px 0px!important;">
                                            <input type="text"  class="form-control"  ng-model="item.land.land_issue_place" >
                                        </div>

                                        <label class="col-sm-3 control-label ">Ngày cấp</label>
                                        <div class="col-sm-3 " style="padding: 0px 0px!important;">
                                            <input type="text" class="form-control" ng-model="item.land.land_issue_date" id="landDate{{$index}}"   />
                                        </div>
                                    </div>


                                    <div class="form-group" style="padding-right: 20px;padding-left: 20px">

                                        <label class="col-md-3 control-label  label-bam-trai">Thửa đất số</label>
                                        <div class="col-md-3" style="padding: 0px 0px!important;">
                                            <input type="text"class="form-control"   ng-model="item.land.land_number"   />
                                        </div>

                                        <label  class="col-md-3 control-label ">Tờ bản đồ số</label>
                                        <div class="col-md-3 " style="padding: 0px 0px!important;">
                                            <input type="text"  class="form-control"  ng-model="item.land.land_map_number"    />
                                        </div>


                                    </div>

                                    <div class="form-group" style="padding-right: 20px;padding-left: 20px">
                                        <label class="col-sm-3 control-label label-bam-trai">Tài sản gắn liền với đất</label>
                                        <div class="col-sm-9 input-group">
                                            <input type="text" class="form-control" ng-model="item.land.land_associate_property"   />
                                        </div>
                                    </div>

                                </div>
                                <div ng-show="item.type=='02'">
                                    <div class="form-group" style="padding-right: 20px;padding-left: 20px">
                                        <label class="col-sm-3 control-label label-bam-trai">Biển kiểm soát</label>
                                        <div class="col-sm-9 input-group">
                                            <input type="text" class="form-control"  ng-model="item.vehicle.car_license_number"   />
                                        </div>
                                    </div>
                                    <div class="form-group" style="padding-right: 20px;padding-left: 20px">
                                        <label class="col-sm-3 control-label label-bam-trai">Số giấy đăng ký</label>
                                        <div class="col-sm-9 input-group">
                                            <input type="text" class="form-control"  ng-model="item.vehicle.car_regist_number"  />
                                        </div>
                                    </div>

                                    <div class="form-group"  style="padding-right: 20px;padding-left: 20px">

                                        <label class="col-md-3 control-label  label-bam-trai">Nơi cấp</label>
                                        <div class="col-md-3" style="padding: 0px 0px!important;">
                                            <input type="text" class="form-control"   ng-model="item.vehicle.car_issue_place"   />
                                        </div>

                                        <label  class="col-md-3 control-label ">Ngày cấp</label>
                                        <div class="col-md-3 " style="padding: 0px 0px!important;">
                                            <input type="text" class="form-control" ng-model="item.vehicle.car_issue_date" id="carDate{{$index}}"   />
                                        </div>

                                    </div>


                                    <div class="form-group"  style="padding-right: 20px;padding-left: 20px">

                                        <label  class="col-md-3 control-label  label-bam-trai">Số khung</label>
                                        <div class="col-md-3" style="padding: 0px 0px!important;">
                                            <input type="text"class="form-control"   ng-model="item.vehicle.car_frame_number"   />
                                        </div>

                                        <label class="col-md-3 control-label ">Số máy</label>
                                        <div class="col-md-3 " style="padding: 0px 0px!important;">
                                            <input type="text"  class="form-control"  ng-model="item.vehicle.car_machine_number"   />
                                        </div>
                                    </div>

                                </div>

                                <div ng-show="item.type=='99'">
                                    <div class="form-group" style="padding-right: 20px;padding-left: 20px">
                                        <label class="col-sm-3 control-label label-bam-trai">Thông tin tài sản</label>
                                        <div class="col-sm-9 input-group">
                                            <textarea type="text" class="form-control" rows="5" ng-model="item.property_info"   ></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group" style="padding-right: 20px;padding-left: 20px">
                                    <label class="col-sm-3 control-label label-bam-trai">Thông tin chủ sở hữu</label>
                                    <div class="col-sm-9 input-group">
                                        <input type="text" class="form-control" ng-model="item.owner_info"   />
                                    </div>
                                </div>
                                <div class="form-group" style="padding-right: 20px;padding-left: 20px">
                                    <label class="col-sm-3 control-label label-bam-trai">Thông tin khác</label>
                                    <div class="col-sm-9 input-group">
                                        <input type="text" class="form-control" ng-model="item.other_info"   />
                                    </div>
                                </div>
                            </div>

                        </div>
                    </section>
                </div>
            </div>


            <section class="panel panel-default">
                <header class="panel-heading font-bold">
                    <h4 class="control-label required label-bam-trai">Thông tin khác</h4>
                </header>
                <div class="panel-body">
                    <div class="row">
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
                                                        <input type="checkbox"  ng-model="checkBank"    ng-click="showDescrip=!showDescrip">
                                                        Hợp đồng cần bổ sung hồ sơ
                                                    </label>
                                                </div>

                                            </div>
                                        </div>
                                        <div class="form-group" ng-show="showDescrip">
                                            <label class="col-lg-4 control-label label-bam-trai">Mô tả</label>
                                            <div class="col-lg-8">
                                                <textarea  rows="3" ng-model="contract.addition_description" class="form-control" placeholder=""></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-4 control-label label-bam-trai">Tên ngân hàng</label>
                                            <div class="col-lg-8">
                                                <select ng-model="contract.bank_code" class="selectpicker select2 col-md-12 no-padding" >
                                                    <option value="">--Chọn--</option>
                                                    <option  ng-repeat="item in banks" value="{{item.code}}" >{{item.code}} - {{item.name}}</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-4 control-label label-bam-trai">Cán bộ tín dụng</label>
                                            <div class="col-lg-8">
                                                <input type="text" class="form-control" ng-model="contract.crediter_name" placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-4 control-label label-bam-trai">Chiết khấu</label>
                                            <div class="col-lg-8">
                                                <input type="text" class="form-control" ng-model="contract.bank_service_fee" onkeypress="return restrictCharacters(this, event, digitsOnly);" placeholder="" format="number">
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
                                            <label class="col-lg-4 control-label label-bam-trai">Số tờ/trang HĐ</label>
                                            <div class="col-lg-8">
                                                <input type="text" class="form-control" ng-model="contract.number_of_page" onkeypress="return restrictCharacters(this, event, digitsOnly);" placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-4 control-label label-bam-trai">Số bản công chứng</label>
                                            <div class="col-lg-8">
                                                <input type="text" class="form-control" ng-model="contract.number_copy_of_contract" onkeypress="return restrictCharacters(this, event, digitsOnly);" placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-4 control-label label-bam-trai">File đính kèm (Tối đa 5MB)</label>
                                            <div class="col-lg-8">
                                                <input type="file" file-model="myFile"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-4 control-label label-bam-trai">Lưu trữ bản gốc</label>
                                            <div class="col-lg-8">
                                                <input type="text" class="form-control" ng-model="contract.original_store_place" onkeypress="return restrictCharacters(this, event, digitsOnly);" placeholder="">
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
                                            <label class="col-lg-4 control-label label-bam-trai">Phí công chứng</label>
                                            <div class="col-lg-8">
                                                <input type="text" class="form-control" ng-model="contract.cost_tt91" ng-change="calculateTotal()" onkeypress="return restrictCharacters(this, event, digitsOnly);" format="number">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-4 control-label label-bam-trai">Thù lao công chứng</label>
                                            <div class="col-lg-8">
                                                <input type="text" class="form-control" ng-model="contract.cost_draft"  ng-change="calculateTotal()" onkeypress="return restrictCharacters(this, event, digitsOnly);"  format="number">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-4 control-label label-bam-trai">Dịch vụ công chứng ngoài</label>
                                            <div class="col-lg-8">
                                                <input type="text" class="form-control" ng-model="contract.cost_notary_outsite"  ng-change="calculateTotal()" onkeypress="return restrictCharacters(this, event, digitsOnly);"  format="number">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-4 control-label label-bam-trai">Dịch vụ xác minh khác</label>
                                            <div class="col-lg-8">
                                                <input type="text" class="form-control" ng-model="contract.cost_other_determine"  ng-change="calculateTotal()" onkeypress="return restrictCharacters(this, event, digitsOnly);"  format="number" >
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-4 control-label label-bam-trai">Tổng phí</label>
                                            <div class="col-lg-8">
                                                <input type="text" ng-model="contract.cost_total"  class="form-control" placeholder="" format="number" onkeypress="return restrictCharacters(this, event, digitsOnly);" >
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-4 control-label label-bam-trai">Ghi chú</label>
                                            <div class="col-lg-8">
                                                <textarea rows="4" ng-model="contract.note" class="form-control" placeholder=""></textarea>
                                            </div>
                                        </div>

                                    </form>
                                </div>
                            </section>
                        </div>
                    </div>
                </div>
            </section>

            <div class="panel-body">
                <div class="list-buttons" style="text-align: center;">
                    <%
                        if(ValidationPool.checkRoleDetail(request,"11", Constants.AUTHORITY_THEM)){
                    %>
                    <a data-toggle="modal"  data-target="#addContract"  class="btn btn-s-md btn-info">Lưu</a>
                    <%
                        }
                    %>
                    <a href="<%=request.getContextPath()%>/contract/list" ng-show="!checkOnline" class="btn btn-s-md btn-default">Hủy bỏ</a>
                </div>
            </div>
        </form>
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
    <div class="modal fade" id="checkValidate" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Thiếu thông tin cần thiết!</h4>
                </div>
                <div class="modal-body">
                    <p>Hãy điền đầy đủ thông tin vào các trường bắt buộc(có dấu <span class="truong-text-colorred">*</span>) </p>
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
    <div class="modal fade" id="errorAdd" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Lỗi!</h4>
                </div>
                <div class="modal-body">
                    <p>Có lỗi xảy ra.Bạn hãy thử lại sau. </p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">OK</button>
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
                    <p>Xác nhận thêm hợp đồng công chứng! </p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success" data-dismiss="modal" ng-click="addContractWrapper()">Đồng ý</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Hủy bỏ</button>
                </div>
            </div>

        </div>
    </div>

</div>

<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/contract/xeditable.min.css" />
<script src="<%=request.getContextPath()%>/static/js/contract/xeditable.min.js" type="text/javascript"></script>

<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />
<script>
    $(function () {
        $('#fromDate').datepicker({
            format: "dd/mm/yyyy",
            startDate: "01/01/1900",
            endDate: endDate,
            forceParse : false,
            language: 'vi'
        }).on('changeDate', function (ev) {
            $(this).datepicker('hide');
        });
        $('#toDate').datepicker({
            format: "dd/mm/yyyy",
            startDate: "01/01/1900",
            endDate: endDate,
            forceParse : false,
            language: 'vi'
        }).on('changeDate', function (ev) {
            $(this).datepicker('hide');
        });
        $('#landDate').datepicker({
            format: "dd/mm/yyyy",
            startDate: "01/01/1900",
            endDate: endDate,
            forceParse : false,
            language: 'vi'
        }).on('changeDate', function (ev) {
            $(this).datepicker('hide');
        });
        $('#carDate').datepicker({
            format: "dd/mm/yyyy",
            startDate: "01/01/1900",
            endDate: endDate,
            forceParse : false,
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