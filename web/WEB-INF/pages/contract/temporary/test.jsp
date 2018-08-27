<%--
  Created by IntelliJ IDEA.
  User: TienManh
  Date: 7/19/2017
  Time: 5:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div ng-repeat="item in listProperty.properties track by $index">
    <div ng-switch on="item.type_view">
        <div ng-switch-when="0">
            <span class="">Quyền sử dụng đất của bên A đối với thửa đất theo giấy chứng nhận số <span
                    class="inputcontract" editable-text="item.land.land_certificate" placeholder=""
                    contenteditable="true">{{item.land.land_certificate}}</span> &nbsp;do <span
                    class="inputcontract" editable-text="item.land.land_issue_place" placeholder=""
                    contenteditable="true">{{item.land.land_issue_place}}</span> &nbsp; cấp ngày <span
                    class="inputcontract" editable-text="item.land.land_issue_date" placeholder=""
                    contenteditable="true">{{item.land.land_issue_date}}</span>&nbsp;cụ thể như sau:</span>
            <div class="">-Thửa đất số: <span class="inputcontract" editable-text="item.land.land_number"
                                              placeholder="&nbsp;"
                                              contenteditable="true">{{item.land.land_number}}</span>&nbsp;
                , tờ bản đồ số:&nbsp; <span class="inputcontract" editable-text="item.land.land_map_number"
                                            placeholder=""
                                            contenteditable="true">{{item.land.land_map_number}}</span></div>
            <div class="">-Địa chỉ thửa đất: &nbsp; <span class="inputcontract" editable-text="item.land.land_address"
                                                          placeholder=""
                                                          contenteditable="true">{{item.land.land_address}}</span>&nbsp;
            </div>
            <div class="">-Diện tích: <span class="inputcontract" editable-text="item.land.land_area" placeholder=""
                                            contenteditable="true">{{item.land.land_area}}</span> &nbsp;m2 (bằng chữ:
                <span
                        class="inputcontract" editable-text="item.land.land_area_text" placeholder=""
                        contenteditable="true">{{item.land.land_area_text}}</span>
                &nbsp;mét vuông )
            </div>
            <div class="">-Hình thức sử dụng:</div>
            <blockquote style="margin: 0 0 0 40px; border: none; padding: 0px;">
                <blockquote style="margin: 0 0 0 40px; border: none; padding: 0px;">
                    <div class="">+Sử dụng riêng: &nbsp;<span class="inputcontract"
                                                              editable-text="item.land.land_private_area" placeholder=""
                                                              contenteditable="true">{{item.land.land_private_area}}</span>
                        &nbsp;&nbsp;
                    </div>
                    <div class="">+Sử dụng chung: <span class="inputcontract" editable-text="item.land.land_public_area"
                                                        placeholder=" "
                                                        contenteditable="true">{{item.land.land_public_area}}</span>&nbsp;
                        &nbsp;&nbsp;
                    </div>
                </blockquote>
            </blockquote>
            <span style="font-size: 17.5px;">-Mục đích sử dụng:&nbsp;<span class="inputcontract"
                                                                           editable-text="item.land.land_use_purpose"
                                                                           placeholder="" contenteditable="true">{{item.land.land_use_purpose}}</span></span>
            <div class="">-Thời hạn sử dụng: <span class="inputcontract" editable-text="item.land.land_use_period"
                                                   placeholder=""
                                                   contenteditable="true">{{item.land.land_use_period}}</span>
            </div>
            <div class="">-Nguồn gốc sử dụng: <span class="inputcontract" editable-text="item.land.land_use_origin"
                                                    placeholder=""
                                                    contenteditable="true">{{item.land.land_use_origin}}</span></div>
        </div>
        <div ng-switch-when="1">
            <div class="">Căn hộ thuộc quyền sở hữu của bên A theo giấy chứng nhận số <span class="inputcontract"
                                                                                            editable-text="item.land.land_certificate"
                                                                                            placeholder=""
                                                                                            contenteditable="true">{{item.land.land_certificate}}</span>
                &nbsp;do <span class="inputcontract" editable-text="item.land.land_issue_place" placeholder=""
                               contenteditable="true">{{item.land.land_issue_place}}</span> cấp ngày <span
                        class="inputcontract" editable-text="item.land.land_issue_date" placeholder=""
                        contenteditable="true">{{item.land.land_issue_date}}</span> , cụ thể như sau:
            </div>
            <div class="">-Địa chỉ:&nbsp;<span class="inputcontract" editable-text="item.apartment.apartment_address"
                                               placeholder="" contenteditable="true">{{item.apartment.apartment_address}}</span>
            </div>
            <div class="">-Căn hộ số: <span class="inputcontract" editable-text="item.apartment.apartment_number"
                                            placeholder=""
                                            contenteditable="true">{{item.apartment.apartment_number}}</span> tầng:
                <span class="inputcontract" editable-text="item.apartment.apartment_floor" placeholder=""
                      contenteditable="true">{{item.apartment.apartment_floor}}</span></div>
            <div class="">-Tổng diện tích sử dụng:&nbsp;<span class="inputcontract"
                                                              editable-text="item.apartment.apartment_area_use"
                                                              placeholder="" contenteditable="true">{{item.apartment.apartment_area_use}}</span>
            </div>
            <div class="">-Diện tích xây dựng:&nbsp;<span class="inputcontract"
                                                          editable-text="item.apartment.apartment_area_build"
                                                          placeholder="" contenteditable="true">{{item.apartment.apartment_area_build}}</span>
            </div>
            <div class="">-Kết cấu nhà:&nbsp;<span class="inputcontract"
                                                   editable-text="item.apartment.apartment_structure" placeholder=""
                                                   contenteditable="true">{{item.apartment.apartment_structure}}</span>
            </div>
            <div class="">-Số tầng nhà chung cư: <span class="inputcontract"
                                                       editable-text="item.apartment.apartment_total_floor"
                                                       placeholder="" contenteditable="true">{{item.apartment.apartment_total_floor}}</span>
                &nbsp;tầng
            </div>
            <div class="">Căn hộ nêu trên là tài sản gắn liền với thửa đất sau:</div>
            <div class="">-Thửa đất số: <span class="inputcontract" editable-text="item.land.land_number"
                                              placeholder="&nbsp;"
                                              contenteditable="true">{{item.land.land_number}}</span> , tờ bản đồ số:&nbsp;
                <span class="inputcontract" editable-text="item.land.land_map_number" placeholder=""
                      contenteditable="true">{{item.land.land_map_number}}</span></div>
            <div class="">-Địa chỉ thửa đất: &nbsp; <span class="inputcontract" editable-text="item.land.land_address"
                                                          placeholder="" contenteditable="true">{{item.land.land_address}}</span>
            </div>
            <div class="">-Diện tích: <span class="inputcontract" editable-text="item.land.land_area" placeholder=""
                                            contenteditable="true">{{item.land.land_area}}</span> &nbsp;m2 (bằng chữ:
                <span class="inputcontract" editable-text="item.land.land_area_text" placeholder=""
                      contenteditable="true">{{item.land.land_area_text}}</span> &nbsp;mét vuông )
            </div>
            <div class="">-Hình thức sử dụng:</div>
            <blockquote style="margin: 0 0 0 40px; border: none; padding: 0px;">
                <blockquote style="margin: 0 0 0 40px; border: none; padding: 0px;">
                    <div class="">+Sử dụng riêng: &nbsp;<span class="inputcontract"
                                                              editable-text="item.land.land_private_area" placeholder=""
                                                              contenteditable="true">{{item.land.land_private_area}}</span>
                    </div>
                    <div class="">+Sử dụng chung: <span class="inputcontract" editable-text="item.land.land_public_area"
                                                        placeholder=" " contenteditable="true">{{item.land.land_public_area}}</span>
                    </div>
                </blockquote>
            </blockquote>
            <span style="font-size: 17.5px;">-Mục đích sử dụng:&nbsp;<span class="inputcontract"
                                                                           editable-text="item.land.land_use_purpose"
                                                                           placeholder="" contenteditable="true">{{item.land.land_use_purpose}}</span></span>
            <div class="">-Thời hạn sử dụng: <span class="inputcontract" editable-text="item.land.land_use_period"
                                                   placeholder=""
                                                   contenteditable="true">{{item.land.land_use_period}}</span></div>
            <div class="">-Nguồn gốc sử dụng: <span class="inputcontract" editable-text="item.land.land_use_origin"
                                                    placeholder=""
                                                    contenteditable="true">{{item.land.land_use_origin}}</span></div>
            <div class="">Những hạn chế về quyền sử dụng đất(nếu có):&nbsp;<span class="inputcontract"
                                                                                 editable-text="item.restrict"
                                                                                 placeholder="" contenteditable="true">{{item.restrict}}</span>
            </div>
        </div>
        <div ng-switch-default>
            <div class="">Thông tin tài sản:&nbsp;<span class="inputcontract" editable-text="item.property_info"
                                                        placeholder=""
                                                        contenteditable="true">{{item.property_info}}</span></div>
        </div>
    </div>
</div>


<%--đươgn sự--%>

<div ng-repeat="item in privys.privy track by $index">
    <div class=""><b style="font-family: Times New Roman; font-size: 14pt;" class="">Bên <span
            editable-text="item.action" class="inputcontract" contenteditable="true">{{item.action}}</span></b> (sau đây
        gọi là {{item.name}}):
    </div>
    <div ng-repeat="user in item.persons track by $index" class="personList">
        <div ng-switch on="user.type">
            <div ng-switch-when="1">
                <div class="">*Công ty:&nbsp;<span class="inputcontract" editable-text="user.org_name" placeholder=""
                                                   contenteditable="true">{{user.org_name}}</span></div>
                <div class="">Địa chỉ: &nbsp;<span class="inputcontract" editable-text="user.org_address" placeholder=""
                                                   contenteditable="true">{{user.org_address}}</span></div>
                <div class="">Mã số doanh nghiệp: <span class="inputcontract" editable-text="user.org_code"
                                                        placeholder="" contenteditable="true">{{user.org_code}}</span>
                    &nbsp; ,đăng ký lần đầu ngày: <span class="inputcontract" editable-text="user.first_registed_date"
                                                        placeholder=" &nbsp;" contenteditable="true">{{user.first_registed_date}}</span>&nbsp;
                    , đăng ký thay đổi lần thứ&nbsp;<span class="inputcontract"
                                                          editable-text="user.number_change_registed" placeholder=""
                                                          contenteditable="true">{{user.number_change_registed}}</span>&nbsp;ngày:
                    <span class="inputcontract" editable-text="user.change_registed_date" placeholder="&nbsp;"
                          contenteditable="true">{{user.change_registed_date}}</span>&nbsp;theo&nbsp; <span
                            class="inputcontract" editable-text="user.department_issue" placeholder=""
                            contenteditable="true">{{user.department_issue}}</span>&nbsp;;
                </div>
                <div class="">Họ và tên người đại diện:&nbsp;<span class="inputcontract" editable-text="user.name"
                                                                   placeholder=""
                                                                   contenteditable="true">{{user.name}}</span></div>
                <div class="">Chức danh:&nbsp;<span class="inputcontract" editable-text="user.position" placeholder=""
                                                    contenteditable="true">{{user.position}}</span></div>
            </div>
            <div ng-switch-default>
                <div class="">*Họ và tên:&nbsp;<span href="#" editable-text="user.name" class="inputcontract">{{user.name}}</span>&nbsp;
                    , sinh năm: <span placeholder="" editable-text="user.birthday" class="inputcontract">{{user.birthday}}</span>
                    ;
                </div>
            </div>
        </div>
        <div class="">Giấy CMND số:&nbsp;<span placeholder="" editable-text="user.passport" class="inputcontract">{{user.passport}}</span>
            cấp ngày:&nbsp; <span placeholder="" editable-text="user.certification_date" class="inputcontract">{{user.certification_date}}</span>
            tại: <span placeholder="" editable-text="user.certification_place" class="inputcontract">{{user.certification_place}}</span>
            ;
        </div>
        <div class="">Địa chỉ:&nbsp;<span placeholder="" editable-text="user.address" class="inputcontract">{{user.address}}</span>;
        </div>
    </div>
</div>


<%--duong su 2--%>
<div ng-repeat="item in privys.privy track by $index">
    <div class=""><b style="font-family: Times New Roman; font-size: 14pt;" class="">Bên <span ng-model="item.action"
                                                                                               editspan="item.action"
                                                                                               class="inputcontract"
                                                                                               contenteditable="true">{{item.action}}</span></b>
        (sau đây gọi là {{item.name}}):
    </div>
    <div ng-repeat="user in item.persons track by $index" class="personList">
        <div ng-switch on="user.type">

            <div ng-switch-when="1">
                <div class="">*Công ty:&nbsp;<span class="inputcontract" editspan="user.org_name"
                                                   ng-model="user.org_name" placeholder="" contenteditable="true">{{user.org_name}}</span>
                </div>
                <div class="">Địa chỉ: &nbsp;<span class="inputcontract" editspan="user.org_address"
                                                   ng-model="user.org_address" placeholder="" contenteditable="true">{{user.org_address}}</span>
                </div>
                <div class="">Mã số doanh nghiệp: <span class="inputcontract" editspan="user.org_code"
                                                        ng-model="user.org_code" placeholder="" contenteditable="true">{{user.org_code}}</span>
                    &nbsp; ,đăng ký lần đầu ngày: <span class="inputcontract" editspan="user.first_registed_date"
                                                        ng-model="user.first_registed_date" placeholder=" &nbsp;"
                                                        contenteditable="true">{{user.first_registed_date}}</span>&nbsp;
                    , đăng ký thay đổi lần thứ&nbsp;<span class="inputcontract" editspan="user.number_change_registed"
                                                          ng-model="user.number_change_registed" placeholder=""
                                                          contenteditable="true">{{user.number_change_registed}}</span>&nbsp;ngày:
                    <span class="inputcontract" editspan="user.change_registed_date"
                          ng-model="user.change_registed_date" placeholder="&nbsp;" contenteditable="true">{{user.change_registed_date}}</span>&nbsp;theo&nbsp;
                    <span class="inputcontract" editspan="user.department_issue" ng-model="user.department_issue"
                          placeholder="" contenteditable="true">{{user.department_issue}}</span>&nbsp;;
                </div>
                <div class="">Họ và tên người đại diện:&nbsp;<span class="inputcontract" editspan="user.name"
                                                                   ng-model="user.name" placeholder=""
                                                                   contenteditable="true">{{user.name}}</span></div>
                <div class="">Chức danh:&nbsp;<span class="inputcontract" editspan="user.position"
                                                    ng-model="user.position" placeholder="" contenteditable="true">{{user.position}}</span>
                </div>
            </div>
            <div ng-switch-default>
                <div class="">*Họ và tên:&nbsp;<span href="#" editspan="user.name" ng-model="user.name"
                                                     class="inputcontract" contenteditable="true">{{user.name}}</span>&nbsp;
                    , sinh năm: <span placeholder="" editspan="user.birthday" ng-model="user.birthday"
                                      class="inputcontract" contenteditable="true">{{user.birthday}}</span> ;
                </div>
            </div>
        </div>
        <div class="">Giấy CMND số:&nbsp;<span placeholder="" editspan="user.passport" ng-model="user.passport"
                                               class="inputcontract" contenteditable="true">{{user.passport}}</span> cấp
            ngày:&nbsp; <span placeholder="" editspan="user.certification_date" ng-model="user.certification_date"
                              class="inputcontract" contenteditable="true">{{user.certification_date}}</span> tại: <span
                    placeholder="" editspan="user.certification_place" ng-model="user.certification_place"
                    class="inputcontract" contenteditable="true">{{user.certification_place}}</span> ;
        </div>
        <div class="">Địa chỉ:&nbsp;<span placeholder="" editspan="user.address" ng-model="user.address"
                                          class="inputcontract" contenteditable="true">{{user.address}}</span>;
        </div>
    </div>
</div>


<div ng-repeat="item in privys.privy track by $index">
    <div class=""><b style="font-family: Times New Roman; font-size: 14pt;" class=""> Bên <span ng-model="item.action"
                                                                                                editspan="item.action"
                                                                                                class="inputcontract"
                                                                                                contenteditable="true">{{item.action}}</span></b>
        (sau đây gọi là {{item.name}}):
    </div>
    <div ng-repeat="user in item.persons track by $index" class="personList">
        <div ng-switch on="user.type">
            <div ng-switch-when="2">
                <div class="">Công ty:<span class="inputcontract" editspan="user.org_name" ng-model="user.org_name"
                                            placeholder="..." contenteditable="true"></span></div>
                <div class="">Địa chỉ: <span class="inputcontract" editspan="user.org_address"
                                             ng-model="user.org_address" placeholder="..."
                                             contenteditable="true"></span></div>
                <div class="">Mã số doanh nghiệp: <span class="inputcontract" editspan="user.org_code"
                                                        ng-model="user.org_code" placeholder="..."
                                                        contenteditable="true"></span> ,đăng ký lần đầu ngày: <span
                        class="inputcontract" editspan="user.first_registed_date" ng-model="user.first_registed_date"
                        placeholder=" ..." contenteditable="true"></span> , đăng ký thay đổi lần thứ<span
                        class="inputcontract" editspan="user.number_change_registed"
                        ng-model="user.number_change_registed" placeholder="..." contenteditable="true"></span> ngày:
                    <span class="inputcontract" editspan="user.change_registed_date"
                          ng-model="user.change_registed_date" placeholder="..." contenteditable="true"></span>theo
                    <span class="inputcontract" editspan="user.department_issue" ng-model="user.department_issue"
                          placeholder="..." contenteditable="true"></span>;
                </div>
                <div class="">Họ và tên người đại diện:<span class="inputcontract" editspan="user.name"
                                                             ng-model="user.name" placeholder="..."
                                                             contenteditable="true"></span></div>
                <div class="">Chức danh:<span class="inputcontract" editspan="user.position" ng-model="user.position"
                                              placeholder="..." contenteditable="true"></span></div>
                <div class="">Giấy CMND số:<span placeholder="..." editspan="user.passport" ng-model="user.passport"
                                                 class="inputcontract" contenteditable="true"></span> cấp ngày: <span
                        placeholder="..." editspan="user.certification_date" ng-model="user.certification_date"
                        class="inputcontract" contenteditable="true"></span> tại: <span placeholder="..."
                                                                                        editspan="user.certification_place"
                                                                                        ng-model="user.certification_place"
                                                                                        class="inputcontract"
                                                                                        contenteditable="true"></span> ;
                </div>
                <div class="">Địa chỉ:<span placeholder="..." editspan="user.address" ng-model="user.address"
                                            class="inputcontract" contenteditable="true"></span>;
                </div>
            </div>
            <div ng-switch-when="1">
                <div class="">Họ và tên:<span href="#" editspan="user.name" ng-model="user.name" class="inputcontract"
                                              contenteditable="true" placeholder="..."></span> , ngày sinh: <span
                        placeholder="..." editspan="user.birthday" ng-model="user.birthday" class="inputcontract"
                        contenteditable="true"></span> ;
                </div>
                <div class="">Giấy CMND số:<span placeholder="..." editspan="user.passport" ng-model="user.passport"
                                                 class="inputcontract" contenteditable="true"></span> cấp ngày: <span
                        placeholder="..." editspan="user.certification_date" ng-model="user.certification_date"
                        class="inputcontract" contenteditable="true"></span> tại: <span placeholder="..."
                                                                                        editspan="user.certification_place"
                                                                                        ng-model="user.certification_place"
                                                                                        class="inputcontract"
                                                                                        contenteditable="true"></span> ;
                </div>
                <div class="">Nơi cư trú:<span placeholder="..." editspan="user.address" ng-model="user.address"
                                               class="inputcontract" contenteditable="true"></span>;
                </div>
            </div>
        </div>
    </div>
</div>


<%--mau chung cu--%>\
<div style="text-align: center;" class="ng-scope"><b style="font-size: 14pt;">CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM</b>
</div>
<div style="text-align: center;" class="ng-scope"><b>ĐỘC LẬP- TỰ DO - HẠNH PHÚC</b></div>
<div style="text-align: center;" class="ng-scope"><b>--------------</b></div>
<div style="text-align: center;" class="ng-scope"><b><br></b></div>
<div style="text-align: center;" class="ng-scope"><b>HỢP ĐỒNG MUA BÁN TÀI SẢN</b></div>
<div class="ng-scope"><b><br></b></div>
<div class="ng-scope"><b><br></b></div>
<div class="ng-scope">Chúng tôi gồm có:</div>
<div dynamic="duongsu" class="ng-scope"><!-- ngRepeat: item in privys.privy track by $index -->
    <div ng-repeat="item in privys.privy track by $index" class="ng-scope">
        <div class="" ng-switch="" on="item.type"> <!-- ngSwitchWhen: 0 --> <!-- ngSwitchDefault: -->
            <div ng-switch-default="" class="ng-binding ng-scope"><b
                    style="font-family: Times New Roman; font-size: 14pt;" class=""> Bên <span ng-model="item.action"
                                                                                               editspan="item.action"
                                                                                               class="inputcontract ng-valid ng-binding ng-not-empty ng-dirty ng-valid-parse ng-touched"
                                                                                               contenteditable="true">bán</span></b>
                (sau đây gọi là Bên A):
            </div><!-- end ngSwitchWhen: --> </div> <!-- ngRepeat: user in item.persons track by $index -->
        <div ng-repeat="user in item.persons track by $index" class="personList ng-scope">
            <div ng-switch="" on="user.template"><!-- ngSwitchWhen: 2 --><!-- ngSwitchWhen: 1 -->
                <div ng-switch-when="1" class="ng-scope">
                    <div class="">Họ và tên:<span href="#" editspan="user.name" ng-model="user.name"
                                                  class="inputcontract ng-valid ng-not-empty ng-dirty ng-valid-parse ng-touched"
                                                  contenteditable="true" placeholder="...">Tèo</span> , ngày sinh: <span
                            placeholder="..." editspan="user.birthday" ng-model="user.birthday"
                            class="inputcontract ng-valid ng-not-empty ng-dirty ng-valid-parse ng-touched"
                            contenteditable="true">1990</span> ;
                    </div>
                    <div class="">Giấy CMND số:<span placeholder="..." editspan="user.passport" ng-model="user.passport"
                                                     class="inputcontract ng-valid ng-not-empty ng-dirty ng-valid-parse ng-touched"
                                                     contenteditable="true">72459458</span> cấp ngày: <span
                            placeholder="..." editspan="user.certification_date" ng-model="user.certification_date"
                            class="inputcontract ng-valid ng-not-empty ng-dirty ng-valid-parse ng-touched"
                            contenteditable="true">20/10</span> tại: <span placeholder="..."
                                                                           editspan="user.certification_place"
                                                                           ng-model="user.certification_place"
                                                                           class="inputcontract ng-valid ng-not-empty ng-dirty ng-valid-parse ng-touched"
                                                                           contenteditable="true">HN</span> ;
                    </div>
                    <div class="">Nơi cư trú:<span placeholder="..." editspan="user.address" ng-model="user.address"
                                                   class="inputcontract ng-valid ng-not-empty ng-dirty ng-valid-parse ng-touched"
                                                   contenteditable="true">HN</span>;
                    </div>
                </div><!-- end ngSwitchWhen: --></div>
        </div><!-- end ngRepeat: user in item.persons track by $index --></div>
    <!-- end ngRepeat: item in privys.privy track by $index -->
    <div ng-repeat="item in privys.privy track by $index" class="ng-scope">
        <div class="" ng-switch="" on="item.type"> <!-- ngSwitchWhen: 0 --> <!-- ngSwitchDefault: -->
            <div ng-switch-default="" class="ng-binding ng-scope"><b
                    style="font-family: Times New Roman; font-size: 14pt;" class=""> Bên <span ng-model="item.action"
                                                                                               editspan="item.action"
                                                                                               class="inputcontract ng-valid ng-binding ng-not-empty ng-dirty ng-valid-parse ng-touched"
                                                                                               contenteditable="true">mua</span></b>
                (sau đây gọi là Bên B):
            </div><!-- end ngSwitchWhen: --> </div> <!-- ngRepeat: user in item.persons track by $index -->
        <div ng-repeat="user in item.persons track by $index" class="personList ng-scope">
            <div ng-switch="" on="user.template"><!-- ngSwitchWhen: 2 --><!-- ngSwitchWhen: 1 -->
                <div ng-switch-when="1" class="ng-scope">
                    <div class="">Họ và tên:<span href="#" editspan="user.name" ng-model="user.name"
                                                  class="inputcontract ng-valid ng-not-empty ng-dirty ng-valid-parse ng-touched"
                                                  contenteditable="true" placeholder="...">ty</span> , ngày sinh: <span
                            placeholder="..." editspan="user.birthday" ng-model="user.birthday"
                            class="inputcontract ng-valid ng-not-empty ng-dirty ng-valid-parse ng-touched"
                            contenteditable="true">2000</span> ;
                    </div>
                    <div class="">Giấy CMND số:<span placeholder="..." editspan="user.passport" ng-model="user.passport"
                                                     class="inputcontract ng-valid ng-not-empty ng-dirty ng-valid-parse ng-touched"
                                                     contenteditable="true">79247205</span> cấp ngày: <span
                            placeholder="..." editspan="user.certification_date" ng-model="user.certification_date"
                            class="inputcontract ng-valid ng-not-empty ng-dirty ng-valid-parse ng-touched"
                            contenteditable="true">20/10</span> tại: <span placeholder="..."
                                                                           editspan="user.certification_place"
                                                                           ng-model="user.certification_place"
                                                                           class="inputcontract ng-valid ng-not-empty ng-dirty ng-valid-parse ng-touched"
                                                                           contenteditable="true">tyiwr</span> ;
                    </div>
                    <div class="">Nơi cư trú:<span placeholder="..." editspan="user.address" ng-model="user.address"
                                                   class="inputcontract ng-valid ng-not-empty ng-dirty ng-valid-parse ng-touched"
                                                   contenteditable="true">HN</span>;
                    </div>
                </div><!-- end ngSwitchWhen: --></div>
        </div><!-- end ngRepeat: user in item.persons track by $index --></div>
    <!-- end ngRepeat: item in privys.privy track by $index --></div>
<div class="ng-scope"><br></div>
<div class="ng-scope">Thỏa thuận mua bán theo:</div>
<div class="ng-scope">1. Tài sản là cái chén</div>
<div class="ng-scope"><br></div>
<div class="ng-scope" style="text-align: center;"><b>TÀI SẢN MUA BÁN</b></div>
<div class="spanactive ng-scope"><b><br></b></div>
<div dynamic="taisan" id="taisan" class="ng-scope">
    <!-- ngRepeat: item in listProperty.properties track by $index --></div>
<div class="ng-scope"><b><br></b></div>
<div class="ng-scope">2. Giá mua bán hợp đồng:&nbsp;<span class="simple" contenteditable="true"></span></div>
<div class="ng-scope">3. Lưu <span class="inputcontract ng-scope ng-binding editable editable-click"
                                   editable-text="contract.original_store_place" placeholder=""
                                   contenteditable="true">1</span> &nbsp;tại văn phòng
</div>
<div class="ng-scope"><br></div>
<div style="text-align: right;" class="ng-scope">CÔNG CHỨNG VIÊN</div>