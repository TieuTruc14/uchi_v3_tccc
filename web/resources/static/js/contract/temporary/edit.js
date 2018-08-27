/**
 * Created by TienManh on 6/4/2017.
 */
myApp.controller('temporaryDetailController',['$scope','$http','$filter','$window','$sce','$timeout','$q','fileUpload',function ($scope,$http,$filter,$window,$sce,$timeout,$q,fileUpload) {
    $scope.url=url;
    $scope.myFile={file:""};
    $scope.privys={};
    $scope.listProperty={};
    $scope.bank={};
    $scope.checkdelete=false;

    $scope.removeFile=function () {
        $http.get(url+"/contract/temporary/remove", {params:{id:id}})
            .then(function(response) {
                if(response.data=true){
                    $scope.contract.file_name="";
                    $scope.contract.file_path="";
                }
            });
    }

    if(code!=null && code !='undefined' && code!=""){
        $http.get(url+"/bank/get-by-code", {params:{code:code}})
            .then(function(response) {
                $scope.bank=response.data;
            });
    }
    $scope.genContent=function(string) {
        return $sce.trustAsHtml(string);
    };

    $http.post(url+"/users/selectByFilter","where role=03", {headers: {'Content-Type': 'application/json'} })
        .then(function (response) {
                $scope.drafters=response.data;
                return response;
            }
        );
    $http.post(url+"/users/selectByFilter","where role=02", {headers: {'Content-Type': 'application/json'} })
        .then(function (response) {
                $scope.notarys=response.data;
                return response;
            }
        );
    /*danh sach ngan hang*/
    $http.get(url+"/bank/getAllBank")
        .then(function(response) {
            $scope.banks=response.data;
        });

    $http.get(url+"/contract/list-contract-kind")
        .then(function(response) {
            $scope.contractKinds=response.data;
            $http.get(url+"/contract/get-contract-kind-by-contract-template-code", {params: { code: template_id}})
                .then(function(response) {
                    $scope.contractKind=response.data;
                });

        });
    $scope.myFunc=function(code){
        $http.get(url+"/contract/list-contract-template-by-contract-kind-code", {params:{code:code}})
            .then(function(response) {
                $scope.contractTemplates=response.data;
            });
    }

    $http.get(url+"/contract/list-contract-template-same-kind", {params: { code_temp: template_id }})
        .then(function(response) {
            $scope.contractTemplates=response.data;
        });


    $http.get(url+"/contract/temporary",{params:{id:id}})
        .then(function (response) {

            $scope.contract=response.data;
            if($scope.contract.json_property!=null && $scope.contract.json_property!="" && $scope.contract.json_property!= 'undefined') {
                try{
                    var pri = $scope.contract.json_property.substr(1, $scope.contract.json_property.length - 2);
                    $scope.listProperty = JSON.parse(pri);
                    $http.get(url+"/ContractTemplate/property-template")
                        .then(function (response) {
                            $scope.templateProperties=response.data;
                            $scope.formatTaiSan();
                            for(var i=0;i<$scope.listProperty.properties.length;i++){
                                var object = $filter('filter')($scope.templateProperties, {type: $scope.listProperty.properties[i].type},true);
                                $scope["listTypeTaiSan"+i]=object;
                            }
                        });

                }catch (e){
                    $scope.listProperty="";
                }
            }
            if($scope.contract.json_person!=null && $scope.contract.json_person!="" && $scope.contract.json_person!= 'undefined'){
                try{
                    var person=$scope.contract.json_person.substr(1,$scope.contract.json_person.length-2);
                    // $scope.privys=JSON.parse(person);
                    $scope.privys=JSON.parse(person);
                }catch (e){
                    $scope.privys="";
                }
            }

            $scope.checkBank = ($scope.contract.addition_status==0)? false:true;
            $scope.showDescrip=$scope.checkBank;

            /*default date for contract-property. Neu de ngoai thi se ko load dc cung`. khi click vao date lan` dau` ko chon thi se bien' mat*/
            $(function () {
                $('#drafterDate').datepicker({
                    format: "dd/mm/yyyy",
                    startDate: "01/01/1900",
                    endDate: endDate,
                    language: 'vi'
                }).on('changeDate', function (ev) {
                    $(this).datepicker('hide');
                });
                $('#notaryDate').datepicker({
                    format: "dd/mm/yyyy",
                    startDate: "01/01/1900",
                    endDate: endDate,
                    language: 'vi'
                }).on('changeDate', function (ev) {
                    $(this).datepicker('hide');
                });
            });


        });


/*for view list duong su va tai san*/

    $http.get(url+"/ContractTemplate/privy-template")
        .then(function (response) {
            $scope.templatePrivys=response.data;
            $scope.formatDuongSu();
            return response;
        });
    $http.get(url+"/ContractTemplate/property-template")
        .then(function (response) {
            $scope.templateProperties=response.data;
            $scope.formatTaiSan();
            return response;
        });
    $scope.formatDuongSu=function () {
        $scope.duongsu='';
        $scope.duongsu+=duongsu_pre;
        if($scope.templatePrivys!=null && $scope.templatePrivys!='undefined' && $scope.templatePrivys!=''){
            for(var i=0;i<$scope.templatePrivys.length;i++){
                var item=$scope.templatePrivys[i];
                $scope.duongsu+='<div ng-switch-when="'+item.id+'">';
                $scope.duongsu+=item.html;
                $scope.duongsu+='</div>';
            }
        }

        $scope.duongsu+=duongsu_suff;
    }

    $scope.formatTaiSan=function () {
        $scope.taisan='';
        $scope.taisan+=taisan_pre;
        if($scope.templateProperties!=null && $scope.templateProperties!='undefined' && $scope.templateProperties!=''){
            for(var i=0;i<$scope.templateProperties.length;i++){
                var item=$scope.templateProperties[i];
                $scope.taisan+='<div ng-switch-when="'+item.id+'">';
                $scope.taisan+=item.html;
                $scope.taisan+='</div>';
            }
        }

        $scope.taisan+=taisan_suff;
    }

    $scope.checkRemoveActionPrivy=function ($event,index) {
        var checkbox = $event.target;
        if(checkbox.checked){ //If it is checked
            $scope.privys.privy[index].type=0;
        }else{
            $scope.privys.privy[index].type="";
        }
    }
    $scope.changTemplatePrivy=function (itemIndex,index,template) {
        $scope.privys.privy[itemIndex].persons[index].template=template;
        template=parseInt(template);
        var object = $filter('filter')($scope.templatePrivys, {id:template},true);
        if(object!=null && object!='undefined' && object!=''){
            object = object[0];
            var id="#button-duongsu"+index;
            $(id).attr("data-content", object.html);
        }

    }
    $scope.getList = function(index) {
        return $scope["listTypeTaiSan"+index];
    };

    $scope.changeTypeProperty=function (index,type) {
        $scope.listProperty.properties[index].type=type;
        var object = $filter('filter')($scope.templateProperties, {type: type},true);
        // $scope.listTypeTaiSan=object;
        $scope["listTypeTaiSan"+index]=object;
    }
    $scope.changTemplateProperty=function (index,template) {
        $scope.listProperty.properties[index].template=template;
        template=parseInt(template);
        var object = $filter('filter')($scope.templateProperties, {id: template},true);
        if(object!=null && object!='undefined' && object!=''){
            object = object[0];
            var id="#button-taisan"+index;
            $(id).attr("data-content", object.html);
        }
    }
    // $scope.duongsu='<div ng-repeat="item in privys.privy track by $index"> <div class=""><b style="font-family: Times New Roman; font-size: 14pt;" class="">Bên <span ng-model="item.action" editspan="item.action" class="inputcontract" contenteditable="true">{{item.action}}</span></b> (sau đây gọi là {{item.name}}): </div> <div ng-repeat="user in item.persons track by $index" class="personList"> <div ng-switch on="user.type"> <div ng-switch-when="1"> <div class="">*Công ty:&nbsp;<span class="inputcontract" editspan="user.org_name" ng-model="user.org_name" placeholder="" contenteditable="true">{{user.org_name}}</span></div> <div class="">Địa chỉ: &nbsp;<span class="inputcontract" editspan="user.org_address" ng-model="user.org_address" placeholder="" contenteditable="true">{{user.org_address}}</span></div> <div class="">Mã số doanh nghiệp: <span class="inputcontract" editspan="user.org_code" ng-model="user.org_code" placeholder="" contenteditable="true">{{user.org_code}}</span> &nbsp; ,đăng ký lần đầu ngày: <span class="inputcontract" editspan="user.first_registed_date" ng-model="user.first_registed_date" placeholder=" &nbsp;" contenteditable="true">{{user.first_registed_date}}</span>&nbsp; , đăng ký thay đổi lần thứ&nbsp;<span class="inputcontract" editspan="user.number_change_registed" ng-model="user.number_change_registed" placeholder="" contenteditable="true">{{user.number_change_registed}}</span>&nbsp;ngày: <span class="inputcontract" editspan="user.change_registed_date" ng-model="user.change_registed_date" placeholder="&nbsp;" contenteditable="true">{{user.change_registed_date}}</span>&nbsp;theo&nbsp; <span class="inputcontract" editspan="user.department_issue" ng-model="user.department_issue" placeholder="" contenteditable="true">{{user.department_issue}}</span>&nbsp;; </div> <div class="">Họ và tên người đại diện:&nbsp;<span class="inputcontract" editspan="user.name" ng-model="user.name" placeholder="" contenteditable="true">{{user.name}}</span></div> <div class="">Chức danh:&nbsp;<span class="inputcontract" editspan="user.position" ng-model="user.position" placeholder="" contenteditable="true">{{user.position}}</span></div> </div> <div ng-switch-default> <div class="">*Họ và tên:&nbsp;<span href="#" editspan="user.name" ng-model="user.name" class="inputcontract" contenteditable="true">{{user.name}}</span>&nbsp; , sinh năm: <span placeholder="" editspan="user.birthday" ng-model="user.birthday" class="inputcontract" contenteditable="true">{{user.birthday}}</span> ; </div> </div> </div> <div class="">Giấy CMND số:&nbsp;<span placeholder="" editspan="user.passport" ng-model="user.passport" class="inputcontract" contenteditable="true">{{user.passport}}</span> cấp ngày:&nbsp; <span placeholder="" editspan="user.certification_date" ng-model="user.certification_date" class="inputcontract" contenteditable="true">{{user.certification_date}}</span> tại: <span placeholder="" editspan="user.certification_place" ng-model="user.certification_place" class="inputcontract" contenteditable="true">{{user.certification_place}}</span> ; </div> <div class="">Địa chỉ:&nbsp;<span placeholder="" editspan="user.address" ng-model="user.address" class="inputcontract" contenteditable="true">{{user.address}}</span>; </div> </div> </div>';
    // $scope.taisan='<div ng-repeat="item in listProperty.properties track by $index"> <div ng-switch on="item.type_view"> <div ng-switch-when="0"><span class="">Quyền sử dụng đất của bên A đối với thửa đất theo giấy chứng nhận số <span class="inputcontract" editspan="item.land.land_certificate" ng-model="item.land.land_certificate" placeholder="" contenteditable="true">{{item.land.land_certificate}}</span> &nbsp;do <span class="inputcontract" editspan="item.land.land_issue_place" ng-model="item.land.land_issue_place" placeholder="" contenteditable="true">{{item.land.land_issue_place}}</span> &nbsp; cấp ngày <span class="inputcontract" editspan="item.land.land_issue_date" ng-model="item.land.land_issue_date" placeholder="" contenteditable="true">{{item.land.land_issue_date}}</span>&nbsp;cụ thể như sau:</span> <div class="">-Thửa đất số: <span class="inputcontract" editspan="item.land.land_number" ng-model="item.land.land_number" placeholder="&nbsp;" contenteditable="true">{{item.land.land_number}}</span>&nbsp; , tờ bản đồ số:&nbsp; <span class="inputcontract" editspan="item.land.land_map_number" ng-model="item.land.land_map_number" placeholder="" contenteditable="true">{{item.land.land_map_number}}</span></div> <div class="">-Địa chỉ thửa đất: &nbsp; <span class="inputcontract" editspan="item.land.land_address" ng-model="item.land.land_address" placeholder="" contenteditable="true">{{item.land.land_address}}</span>&nbsp; </div> <div class="">-Diện tích: <span class="inputcontract" editspan="item.land.land_area" ng-model="item.land.land_area" placeholder="" contenteditable="true">{{item.land.land_area}}</span> &nbsp;m2 (bằng chữ: <span class="inputcontract" editspan="item.land.land_area_text" ng-model="item.land.land_area_text" placeholder="" contenteditable="true">{{item.land.land_area_text}}</span> &nbsp;mét vuông ) </div> <div class="">-Hình thức sử dụng:</div> <blockquote style="margin: 0 0 0 40px; border: none; padding: 0px;"> <blockquote style="margin: 0 0 0 40px; border: none; padding: 0px;"> <div class="">+Sử dụng riêng: &nbsp;<span class="inputcontract" editspan="item.land.land_private_area" ng-model="item.land.land_private_area" placeholder="" contenteditable="true">{{item.land.land_private_area}}</span> &nbsp;&nbsp; </div> <div class="">+Sử dụng chung: <span class="inputcontract" editspan="item.land.land_public_area" ng-model="item.land.land_public_area" placeholder=" " contenteditable="true">{{item.land.land_public_area}}</span>&nbsp; &nbsp;&nbsp; </div> </blockquote> </blockquote> <span style="font-size: 17.5px;">-Mục đích sử dụng:&nbsp;<span class="inputcontract" editspan="item.land.land_use_purpose" ng-model="item.land.land_use_purpose" placeholder="" contenteditable="true">{{item.land.land_use_purpose}}</span></span> <div class="">-Thời hạn sử dụng: <span class="inputcontract" editspan="item.land.land_use_period" ng-model="item.land.land_use_period" placeholder="" contenteditable="true">{{item.land.land_use_period}}</span> </div> <div class="">-Nguồn gốc sử dụng: <span class="inputcontract" editspan="item.land.land_use_origin" ng-model="item.land.land_use_origin" placeholder="" contenteditable="true">{{item.land.land_use_origin}}</span></div> </div> <div ng-switch-when="1"> <div class="">Căn hộ thuộc quyền sở hữu của bên A theo giấy chứng nhận số <span class="inputcontract" editspan="item.land.land_certificate" ng-model="item.land.land_certificate" placeholder="" contenteditable="true">{{item.land.land_certificate}}</span> &nbsp;do <span class="inputcontract" editspan="item.land.land_issue_place" ng-model="item.land.land_issue_place" placeholder="" contenteditable="true">{{item.land.land_issue_place}}</span> cấp ngày <span class="inputcontract" editspan="item.land.land_issue_date" ng-model="item.land.land_issue_date" placeholder="" contenteditable="true">{{item.land.land_issue_date}}</span> , cụ thể như sau: </div> <div class="">-Địa chỉ:&nbsp;<span class="inputcontract" editspan="item.apartment.apartment_address" ng-model="item.apartment.apartment_address" placeholder="" contenteditable="true">{{item.apartment.apartment_address}}</span> </div> <div class="">-Căn hộ số: <span class="inputcontract" editspan="item.apartment.apartment_number" ng-model="item.apartment.apartment_number" placeholder="" contenteditable="true">{{item.apartment.apartment_number}}</span> tầng: <span class="inputcontract" editspan="item.apartment.apartment_floor" ng-model="item.apartment.apartment_floor" placeholder="" contenteditable="true">{{item.apartment.apartment_floor}}</span></div> <div class="">-Tổng diện tích sử dụng:&nbsp;<span class="inputcontract" editspan="item.apartment.apartment_area_use" ng-model="item.apartment.apartment_area_use" placeholder="" contenteditable="true">{{item.apartment.apartment_area_use}}</span> </div> <div class="">-Diện tích xây dựng:&nbsp;<span class="inputcontract" editspan="item.apartment.apartment_area_build" ng-model="item.apartment.apartment_area_build" placeholder="" contenteditable="true">{{item.apartment.apartment_area_build}}</span> </div> <div class="">-Kết cấu nhà:&nbsp;<span class="inputcontract" editspan="item.apartment.apartment_structure" ng-model="item.apartment.apartment_structure" placeholder="" contenteditable="true">{{item.apartment.apartment_structure}}</span> </div> <div class="">-Số tầng nhà chung cư: <span class="inputcontract" editspan="item.apartment.apartment_total_floor" ng-model="item.apartment.apartment_total_floor" placeholder="" contenteditable="true">{{item.apartment.apartment_total_floor}}</span> &nbsp;tầng </div> <div class="">Căn hộ nêu trên là tài sản gắn liền với thửa đất sau:</div> <div class="">-Thửa đất số: <span class="inputcontract" editspan="item.land.land_number" ng-model="item.land.land_number" placeholder="&nbsp;" contenteditable="true">{{item.land.land_number}}</span> , tờ bản đồ số:&nbsp; <span class="inputcontract" editspan="item.land.land_map_number" ng-model="item.land.land_map_number" placeholder="" contenteditable="true">{{item.land.land_map_number}}</span></div> <div class="">-Địa chỉ thửa đất: &nbsp; <span class="inputcontract" editspan="item.land.land_address" ng-model="item.land.land_address" placeholder="" contenteditable="true">{{item.land.land_address}}</span> </div> <div class="">-Diện tích: <span class="inputcontract" editspan="item.land.land_area" ng-model="item.land.land_area" placeholder="" contenteditable="true">{{item.land.land_area}}</span> &nbsp;m2 (bằng chữ: <span class="inputcontract" editspan="item.land.land_area_text" ng-model="item.land.land_area_text" placeholder="" contenteditable="true">{{item.land.land_area_text}}</span> &nbsp;mét vuông ) </div> <div class="">-Hình thức sử dụng:</div> <blockquote style="margin: 0 0 0 40px; border: none; padding: 0px;"> <blockquote style="margin: 0 0 0 40px; border: none; padding: 0px;"> <div class="">+Sử dụng riêng: &nbsp;<span class="inputcontract" editspan="item.land.land_private_area" ng-model="item.land.land_private_area" placeholder="" contenteditable="true">{{item.land.land_private_area}}</span> </div> <div class="">+Sử dụng chung: <span class="inputcontract" editspan="item.land.land_public_area" ng-model="item.land.land_public_area" placeholder=" " contenteditable="true">{{item.land.land_public_area}}</span> </div> </blockquote> </blockquote> <span style="font-size: 17.5px;">-Mục đích sử dụng:&nbsp;<span class="inputcontract" editspan="item.land.land_use_purpose" ng-model="item.land.land_use_purpose" placeholder="" contenteditable="true">{{item.land.land_use_purpose}}</span></span> <div class="">-Thời hạn sử dụng: <span class="inputcontract" editspan="item.land.land_use_period" ng-model="item.land.land_use_period" placeholder="" contenteditable="true">{{item.land.land_use_period}}</span></div> <div class="">-Nguồn gốc sử dụng: <span class="inputcontract" editspan="item.land.land_use_origin" ng-model="item.land.land_use_origin" placeholder="" contenteditable="true">{{item.land.land_use_origin}}</span></div> <div class="">Những hạn chế về quyền sử dụng đất(nếu có):&nbsp;<span class="inputcontract" editspan="item.restrict" ng-model="item.restrict" placeholder="" contenteditable="true">{{item.restrict}}</span> </div> </div> <div ng-switch-default> <div class="">Thông tin tài sản:&nbsp;<span class="inputcontract" editspan="item.property_info" ng-model="item.property_info" placeholder="" contenteditable="true">{{item.property_info}}</span></div> </div> </div> </div>';

    // $http.get(url+"/contract/get-contract-kind-by-contract-template-id", {params: { id: template_id}})
    //     .then(function(response) {
    //         $scope.contractKind=response.data;
    //     });
    //
    // $http.get(url+"/contract/get-contract-template-by-id", {params: { id: template_id }})
    //     .then(function(response) {
    //         $scope.contractTemplate=response.data;
    //     });

    /*Duong su ben A-B-C*/
    var alphabet=["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];

    // $scope.privys = { name: "Đương sự", privy: [ { name: "Bên A", id: 1,action:"", persons: [ { id: "",name: "",birthday:"",passport:"",certification_date:"",certification_place:"",address:"", description:"" } ] }, { name: "Bên B", id: 2,action:"", persons: [ { id: "",name: "",birthday:"",passport:"",certification_date:"",certification_place:"",address:"", description:"" } ] } ] };

    $scope.addPrivy=function () {
        if($scope.privys.privy.length<alphabet.length){
            // var object={ name: "Bên "+alphabet[$scope.privys.privy.length], id: $scope.privys.privy.length,action:"", persons: [ { id: "",name: "",birthday:"",passport:"",certification_date:"",certification_place:"",address:"", description:"" } ] };
            var object={ name: "Bên "+alphabet[$scope.privys.privy.length], id: $scope.privys.privy.length,action:"", persons: [ ] };
            $scope.privys.privy.push(object);
        }
    };
    $scope.removePrivy=function (index) {
        $scope.privys.privy.splice(index,1);
        //danh lai ten cho cac ben lien quan khi xoa
        for(var i=0;i<$scope.privys.privy.length;i++){
            $scope.privys.privy[i].name="Bên "+alphabet[i];
            $scope.privys.privy[i].id=i+1;
        }

    }
    $scope.addPerson=function (index) {
        var object= {template:"", id: "",name: "",birthday:"",passport:"",certification_date:"",certification_place:"",address:"",position:"", description:"",org_name:"",org_address:"",org_code:"",first_registed_date:"",number_change_registed:"",change_registed_date:"",department_issue:"" };
        $scope.privys.privy[index].persons.push(object);
        /*load popover*/
        var defer = $q.defer();
        $timeout(function(){
            $("[data-toggle=popover]").popover();
            $(document).on('click', '.popover-title .close', function(e){
                var $target = $(e.target), $popover = $target.closest('.popover').prev();
                $popover && $popover.popover('hide');
            });
            defer.resolve();
        },1000);
    }

    $scope.removePerson=function (parentIndex,index) {
        $scope.privys.privy[parentIndex].persons.splice(index,1);
    }


    // $scope.taisan='<div ng-repeat="item in listProperty.properties track by $index"> <div ng-switch on="item.type_view"> <div ng-switch-when="0"><span class="">Quyền sử dụng đất của bên A đối với thửa đất theo giấy chứng nhận số <span class="inputcontract" editable-text="item.land.land_certificate" placeholder="" contenteditable="true">{{item.land.land_certificate}}</span> &nbsp;do <span class="inputcontract" editable-text="item.land.land_issue_place" placeholder="" contenteditable="true">{{item.land.land_issue_place}}</span> &nbsp; cấp ngày <span class="inputcontract" editable-text="item.land.land_issue_date" placeholder="" contenteditable="true">{{item.land.land_issue_date}}</span>&nbsp;cụ thể như sau:</span> <div class="">-Thửa đất số: <span class="inputcontract" editable-text="item.land.land_number" placeholder="&nbsp;" contenteditable="true">{{item.land.land_number}}</span>&nbsp; , tờ bản đồ số:&nbsp; <span class="inputcontract" editable-text="item.land.land_map_number" placeholder="" contenteditable="true">{{item.land.land_map_number}}</span></div> <div class="">-Địa chỉ thửa đất: &nbsp; <span class="inputcontract" editable-text="item.land.land_address" placeholder="" contenteditable="true">{{item.land.land_address}}</span>&nbsp; </div> <div class="">-Diện tích: <span class="inputcontract" editable-text="item.land.land_area" placeholder="" contenteditable="true">{{item.land.land_area}}</span> &nbsp;m2 (bằng chữ: <span class="inputcontract" editable-text="item.land.land_area_text" placeholder="" contenteditable="true">{{item.land.land_area_text}}</span> &nbsp;mét vuông ) </div> <div class="">-Hình thức sử dụng:</div> <blockquote style="margin: 0 0 0 40px; border: none; padding: 0px;"> <blockquote style="margin: 0 0 0 40px; border: none; padding: 0px;"> <div class="">+Sử dụng riêng: &nbsp;<span class="inputcontract" editable-text="item.land.land_private_area" placeholder="" contenteditable="true">{{item.land.land_private_area}}</span> &nbsp;&nbsp; </div> <div class="">+Sử dụng chung: <span class="inputcontract" editable-text="item.land.land_public_area" placeholder=" " contenteditable="true">{{item.land.land_public_area}}</span>&nbsp; &nbsp;&nbsp; </div> </blockquote> </blockquote> <span style="font-size: 17.5px;">-Mục đích sử dụng:&nbsp;<span class="inputcontract" editable-text="item.land.land_use_purpose" placeholder="" contenteditable="true">{{item.land.land_use_purpose}}</span></span> <div class="">-Thời hạn sử dụng: <span class="inputcontract" editable-text="item.land.land_use_period" placeholder="" contenteditable="true">{{item.land.land_use_period}}</span> </div> <div class="">-Nguồn gốc sử dụng: <span class="inputcontract" editable-text="item.land.land_use_origin" placeholder="" contenteditable="true">{{item.land.land_use_origin}}</span></div> </div> <div ng-switch-when="1"> <div class="">Căn hộ thuộc quyền sở hữu của bên A theo giấy chứng nhận số <span class="inputcontract" editable-text="item.land.land_certificate" placeholder="" contenteditable="true">{{item.land.land_certificate}}</span> &nbsp;do <span class="inputcontract" editable-text="item.land.land_issue_place" placeholder="" contenteditable="true">{{item.land.land_issue_place}}</span> cấp ngày <span class="inputcontract" editable-text="item.land.land_issue_date" placeholder="" contenteditable="true">{{item.land.land_issue_date}}</span> , cụ thể như sau: </div> <div class="">-Địa chỉ:&nbsp;<span class="inputcontract" editable-text="item.apartment.apartment_address" placeholder="" contenteditable="true">{{item.apartment.apartment_address}}</span> </div> <div class="">-Căn hộ số: <span class="inputcontract" editable-text="item.apartment.apartment_number" placeholder="" contenteditable="true">{{item.apartment.apartment_number}}</span> tầng: <span class="inputcontract" editable-text="item.apartment.apartment_floor" placeholder="" contenteditable="true">{{item.apartment.apartment_floor}}</span></div> <div class="">-Tổng diện tích sử dụng:&nbsp;<span class="inputcontract" editable-text="item.apartment.apartment_area_use" placeholder="" contenteditable="true">{{item.apartment.apartment_area_use}}</span> </div> <div class="">-Diện tích xây dựng:&nbsp;<span class="inputcontract" editable-text="item.apartment.apartment_area_build" placeholder="" contenteditable="true">{{item.apartment.apartment_area_build}}</span> </div> <div class="">-Kết cấu nhà:&nbsp;<span class="inputcontract" editable-text="item.apartment.apartment_structure" placeholder="" contenteditable="true">{{item.apartment.apartment_structure}}</span> </div> <div class="">-Số tầng nhà chung cư: <span class="inputcontract" editable-text="item.apartment.apartment_total_floor" placeholder="" contenteditable="true">{{item.apartment.apartment_total_floor}}</span> &nbsp;tầng </div> <div class="">Căn hộ nêu trên là tài sản gắn liền với thửa đất sau:</div> <div class="">-Thửa đất số: <span class="inputcontract" editable-text="item.land.land_number" placeholder="&nbsp;" contenteditable="true">{{item.land.land_number}}</span> , tờ bản đồ số:&nbsp; <span class="inputcontract" editable-text="item.land.land_map_number" placeholder="" contenteditable="true">{{item.land.land_map_number}}</span></div> <div class="">-Địa chỉ thửa đất: &nbsp; <span class="inputcontract" editable-text="item.land.land_address" placeholder="" contenteditable="true">{{item.land.land_address}}</span> </div> <div class="">-Diện tích: <span class="inputcontract" editable-text="item.land.land_area" placeholder="" contenteditable="true">{{item.land.land_area}}</span> &nbsp;m2 (bằng chữ: <span class="inputcontract" editable-text="item.land.land_area_text" placeholder="" contenteditable="true">{{item.land.land_area_text}}</span> &nbsp;mét vuông ) </div> <div class="">-Hình thức sử dụng:</div> <blockquote style="margin: 0 0 0 40px; border: none; padding: 0px;"> <blockquote style="margin: 0 0 0 40px; border: none; padding: 0px;"> <div class="">+Sử dụng riêng: &nbsp;<span class="inputcontract" editable-text="item.land.land_private_area" placeholder="" contenteditable="true">{{item.land.land_private_area}}</span> </div> <div class="">+Sử dụng chung: <span class="inputcontract" editable-text="item.land.land_public_area" placeholder=" " contenteditable="true">{{item.land.land_public_area}}</span> </div> </blockquote> </blockquote> <span style="font-size: 17.5px;">-Mục đích sử dụng:&nbsp;<span class="inputcontract" editable-text="item.land.land_use_purpose" placeholder="" contenteditable="true">{{item.land.land_use_purpose}}</span></span> <div class="">-Thời hạn sử dụng: <span class="inputcontract" editable-text="item.land.land_use_period" placeholder="" contenteditable="true">{{item.land.land_use_period}}</span></div> <div class="">-Nguồn gốc sử dụng: <span class="inputcontract" editable-text="item.land.land_use_origin" placeholder="" contenteditable="true">{{item.land.land_use_origin}}</span></div> <div class="">Những hạn chế về quyền sử dụng đất(nếu có):&nbsp;<span class="inputcontract" editable-text="item.restrict" placeholder="" contenteditable="true">{{item.restrict}}</span> </div> </div> <div ng-switch-default> <div class="">Thông tin tài sản:&nbsp;<span class="inputcontract" editable-text="item.property_info" placeholder="" contenteditable="true">{{item.property_info}}</span></div> </div> </div> </div>';
    // $scope.listTypeTaiSan=[
    //     {id:"0",name:"Mẫu thông tin thửa đất",htmlContent:'<div class="">Quyền sử dụng đất của bên A đối với thửa đất theo cụ thể như sau:</div> <div class="">-Thửa đất số: , tờ bản đồ số: </div> <div class="">-Địa chỉ thửa đất: </div> <div class="">-Diện tích: m2 (bằng chữ: mét vuông )</div> <div class="">-Hình thức sử dụng:</div> <div style="margin: 0 0 0 40px; border: none; padding: 0px;"> <div style="margin: 0 0 0 40px; border: none; padding: 0px;"> <div class="">+Sử dụng riêng: &nbsp;</div> <div class="">+Sử dụng chung: </div> </div></div> <div >-Mục đích sử dụng:&nbsp; </div> <div class="">-Thời hạn sử dụng: </div> <div class="">-Nguồn gốc sử dụng: </div>'},
    //     {id:"1",name:"Mẫu nhà chung cư",htmlContent:'<div class="">Căn hộ thuộc quyền sở hữu của bên A theo giấy chứng nhận số &nbsp;do cấp ngày , cụ thể như sau:</div> <div class="">-Địa chỉ:&nbsp; </div> <div class="">-Căn hộ số: tầng: </div> <div class="">-Tổng diện tích sử dụng:&nbsp; </div> <div class="">-Diện tích xây dựng:&nbsp; </div> <div class="">-Kết cấu nhà:&nbsp; </div> <div class="">-Số tầng nhà chung cư: &nbsp;tầng</div> <div class="">Căn hộ nêu trên là tài sản gắn liền với thửa đất sau:</div> <div class="">-Thửa đất số: , tờ bản đồ số:&nbsp; </div> <div class="">-Địa chỉ thửa đất: &nbsp; </div> <div class="">-Diện tích: &nbsp;m2 (bằng chữ: &nbsp;mét vuông )</div> <div class="">-Hình thức sử dụng:</div> <div style="margin: 0 0 0 40px; border: none; padding: 0px;"> <div style="margin: 0 0 0 40px; border: none; padding: 0px;"> <div class="">+Sử dụng riêng: &nbsp; </div> <div class="">+Sử dụng chung: </div> </div> </div> <div >-Mục đích sử dụng:&nbsp;</div> <div class="">-Thời hạn sử dụng: </div> <div class="">-Nguồn gốc sử dụng: </div> <div class="">Những hạn chế về quyền sử dụng đất(nếu có):&nbsp; </div>'},
    //     {id:"99",name:"Tài sản khác",htmlContent:'<div class="">Thông tin tài sản: </div>'}
    // ]
    /*Thong tin tai san*/
    $scope.listProperty=	{ name: "property", properties: [] };

    $(function () {
        for(var i=0; i<$scope.listProperty.properties.length;i++){
            var string="#landDate"+i;
            $(string).datepicker({
                format: "dd/mm/yyyy",
                startDate: "01/01/1900",
                endDate: endDate,
                forceParse : false,
                language: 'vi'
            }).on('changeDate', function (ev) {
                $(this).datepicker('hide');
            });
            var carDate="#carDate"+i;
            $(carDate).datepicker({
                format: "dd/mm/yyyy",
                startDate: "01/01/1900",
                endDate: endDate,
                forceParse : false,
                language: 'vi'
            }).on('changeDate', function (ev) {
                $(this).datepicker('hide');
            });
        }
        //load tooltip
        var defer = $q.defer();
        $timeout(function(){
            $("[data-toggle=popover]").popover();
            $(document).on('click', '.popover-title .close', function(e){
                var $target = $(e.target), $popover = $target.closest('.popover').prev();
                $popover && $popover.popover('hide');
            });
            defer.resolve();
        },1000);

        // if($scope.listProperty.properties.length>0){
        //     for(var i=0; i<$scope.listProperty.properties.length;i++){
        //         var object = $filter('filter')($scope.listTypeTaiSan, {id: $scope.listProperty.properties[i].type_view})[0];
        //         var id="#button-taisan"+i;
        //         $(id).attr("data-content", object.htmlContent);
        //     }
        // }

    });


    $scope.addProperty=function () {
        var object={ type: "", id: $scope.listProperty.properties.length+1,template:"", property_info:"", owner_info:"", other_info:"",restrict:"",apartment:{apartment_address:"",apartment_number:"",apartment_floor:"",apartment_area_use:"",apartment_area_build:"",apartment_structure:"",apartment_total_floor:""}, land: { land_certificate:"", land_issue_place:"", land_issue_date:"", land_map_number:"", land_number:"", land_address:"", land_area:"", land_area_text:"", land_public_area:"", land_private_area:"", land_use_purpose:"", land_use_period:"", land_use_origin:"", land_associate_property:"", land_street:"", land_district:"", land_province:"", land_full_of_area:"" }, vehicle:{ car_license_number:"", car_regist_number:"", car_issue_place:"", car_issue_date:"", car_frame_number:"", car_machine_number:"" } };
        // var object={ type: "01", id: $scope.listProperty.properties.length+1,type_view:"", property_info:"", owner_info:"", other_info:"",restrict:"",apartment:{apartment_address:"",apartment_number:"",apartment_floor:"",apartment_area_use:"",apartment_area_build:"",apartment_structure:"",apartment_total_floor:""}, land: { land_certificate:"", land_issue_place:"", land_issue_date:"", land_map_number:"", land_number:"", land_address:"", land_area:"", land_area_text:"", land_public_area:"", land_private_area:"", land_use_purpose:"", land_use_period:"", land_use_origin:"", land_associate_property:"", land_street:"", land_district:"", land_province:"", land_full_of_area:"" }, vehicle:{ car_license_number:"", car_regist_number:"", car_issue_place:"", car_issue_date:"", car_frame_number:"", car_machine_number:"" } };
        $scope.listProperty.properties.push(object);

        //load tooltip for button
        var defer = $q.defer();
        $timeout(function(){
            $("[data-toggle=popover]").popover();
            $(document).on('click', '.popover-title .close', function(e){
                var $target = $(e.target), $popover = $target.closest('.popover').prev();
                $popover && $popover.popover('hide');
            });
            defer.resolve();
        },1000);

        $(function () {
            for(var i=0; i<$scope.listProperty.properties.length;i++){
                var string="#landDate"+i;
                $(string).datepicker({
                    format: "dd/mm/yyyy",
                    startDate: "01/01/1900",
                    endDate: endDate,
                    forceParse : false,
                    language: 'vi'
                }).on('changeDate', function (ev) {
                    $(this).datepicker('hide');
                });
                var carDate="#carDate"+i;
                $(carDate).datepicker({
                    format: "dd/mm/yyyy",
                    startDate: "01/01/1900",
                    endDate: endDate,
                    forceParse : false,
                    language: 'vi'
                }).on('changeDate', function (ev) {
                    $(this).datepicker('hide');
                });
            }
        });
    }
    $scope.removeProperty=function (index) {
        $scope.listProperty.properties.splice(index,1);
        for(var i=0;i<$scope.listProperty.properties.length;i++){
            var object = $filter('filter')($scope.templateProperties, {type: $scope.listProperty.properties[index].type},true);
            $scope["listTypeTaiSan"+index]=object;
        }
    }
    /*end property*/

    /*Calculate total fee*/
    $scope.calculateTotal=function () {
        var cost_tt91='0';
        var cost_draft='0';
        var cost_notary_outsite='0';
        var cost_other_determine='0';
        if($scope.contract.cost_tt91.toString()!="" && $scope.contract.cost_tt91.toString()!='underfined') cost_tt91=$scope.contract.cost_tt91.toString().replace(/,/g,"");
        if($scope.contract.cost_draft.toString()!="" && $scope.contract.cost_draft.toString()!='underfined') cost_draft=$scope.contract.cost_draft.toString().replace(/,/g,"");
        if($scope.contract.cost_notary_outsite.toString()!="" && $scope.contract.cost_notary_outsite.toString()!='underfined') cost_notary_outsite=$scope.contract.cost_notary_outsite.toString().replace(/,/g,"");
        if($scope.contract.cost_other_determine.toString()!="" && $scope.contract.cost_other_determine.toString()!='underfined') cost_other_determine=$scope.contract.cost_other_determine.toString().replace(/,/g,"");


        $scope.contract.cost_total=parseInt(cost_tt91)+parseInt(cost_draft)+parseInt(cost_notary_outsite)+parseInt(cost_other_determine);
    }


    $scope.editTemporary=function () {
        if($scope.checkValidate()){
            if($scope.contract.type==2||$scope.contract.type==3){
                $scope.contract.type=0;
            }
            if($scope.checkDate()){
                $("#checkDate").modal('show');
            }else{
                $scope.runEdit();
            }

        }else{
            $("#checkValidate").modal('show');
        }

    }

    $scope.runEdit=function () {
        /*genaral duong su va tai san*/
        $scope.contract.json_property="'"+JSON.stringify($scope.listProperty)+"'";
        $scope.contract.json_person="'"+JSON.stringify($scope.privys)+"'";
        $scope.contract.entry_date_time=new Date();
        $scope.contract.update_date_time=new Date();
        /*format list items html duong su*/
        //gan gia tri cho #copyContract tranh' thay doi gia tri cua divcontract
        $scope.contract.kindhtml=$("div #contentKindHtml").html();

        /*FORMAT GEN INFO PROPERTY AND RELATION_OBJECT*/
        $scope.genInforProAndPrivys();

        var contractAdd=JSON.parse(JSON.stringify($scope.contract));
        if($scope.myFile.file!=null && $scope.myFile.file!='undefined'&&$scope.myFile.file.size>0){
            if($scope.myFile.file.size>5242000){
                $("#errorMaxFile").modal('show');
            }else{
                var file = $scope.myFile.file;
                var uploadUrl = url+"/contract/uploadFile";
                fileUpload.uploadFileToUrl(file, uploadUrl)
                    .then(function (response) {
                            if( response.data!=null && response.data!='undefined' && response.status==200){
                                $scope.contract.file_name=response.data.name;
                                $scope.contract.file_path=response.data.path;
                                var contractAdd=JSON.parse(JSON.stringify($scope.contract));

                                $http.put(url+"/contract/temporary",contractAdd, {headers: {'Content-Type': 'application/json'} })
                                    .then(function (response) {
                                            if(response.statusText=='OK' && response.data>0){
                                                $window.location.href = contextPath+'/contract/temporary/list?status=3';
                                            }else{
                                                $("#errorEdit").modal('show');
                                            }
                                        },
                                        function(response){
                                            // failure callback
                                            $("#errorEdit").modal('show');
                                        }
                                    );
                            }else{
                                $("#errorEdit").modal('show');
                            }
                        },
                        function(response){
                            // failure callback
                            $("#errorEdit").modal('show');
                        }
                    );
            }

        }else{
            $http.put(url+"/contract/temporary",contractAdd, {headers: {'Content-Type': 'application/json'} })
                .then(function (response) {
                        if(response.statusText=='OK' && response.data>0){
                            $window.location.href = contextPath+'/contract/temporary/list?status=3';
                        }else{
                            $("#errorEdit").modal('show');
                        }
                    },
                    function(response){
                        // failure callback
                        $("#errorEdit").modal('show');
                    }
                );
        }


    }

    //when delete contract
    $scope.deleteContract=function () {
        $scope.checkdelete=true;

        $http.delete(url+"/contract/temporary",{params: { id: id}})
            .then(function (response) {
                    if(response.statusText=='OK'){

                        $window.location.href = contextPath+'/contract/temporary/list?status=2';
                    }else{
                        $scope.checkdelete=false;
                        $("#errorEdit").modal('show');
                    }
                },
                function(response){
                    // failure callback
                    $scope.checkdelete=false;
                    $("#errorEdit").modal('show');

                }
            );

    }

    $scope.viewAsDoc=function () {
        $("#viewHtmlAsWord").html($("#contentKindHtml").html());
        $("#viewContentAsWord").modal('show');
    }

    $scope.downloadWord=function () {
        $("#contentKindHtml").wordExport();
    }

    $scope.downloadFile=function () {
        window.open(url+"/contract/temporary/download/"+$scope.contract.tcid, '_blank');
    }

    $scope.genInforProAndPrivys=function () {
        /*gen contract.propertyInfo va relation_object*/
        $scope.contract.relation_object_a="";
        $scope.contract.relation_object_b="";
        for(var i=0;i<$scope.privys.privy.length;i++){
            var item1=$scope.privys.privy[i];
            var string1='';
            for(var j=0;j<item1.persons.length;j++){
                var string="";
                var item=item1.persons[j];

                if(item.org_name!=="" && typeof item.org_name!=='undefined'){string+="Công ty: "+item.org_name+"; ";}
                if(item.org_address!=="" && typeof item.org_address!=='undefined'){string+="Địa chỉ công ty: "+item.org_address+"; ";}
                if(item.org_code!=="" && typeof item.org_code!=='undefined')string+="Mã doanh nghiệp: "+item.org_code+"; ";
                if(item.name!=="" && typeof item.name!=='undefined')string+="Họ tên: "+item.name+"; ";
                if(item.birthday!=="" && typeof item.birthday!=='undefined')string+="Sinh năm: "+item.birthday+"; ";
                if(item.position!=="" && typeof item.position!=='undefined')string+="Chức danh: "+item.position+"; ";
                if(item.passport!=="" &&  typeof item.passport!=='undefined')string+="CMND: "+item.passport+"; ";
                if(item.certification_date!=="" && typeof item.certification_date!=='undefined')string+="Ngày cấp: "+item.certification_date+"; ";
                if(item.certification_place!=="" && typeof item.certification_place!=='undefined')string+="Nơi cấp: "+item.certification_place+"; ";
                if(item.address!=="" && typeof item.address!=='undefined')string+="Địa chỉ: "+item.address+"; ";
                if(item.description!=="" && typeof item.description!=='undefined')string+="Mô tả: "+item.description+"; ";

                if(string.length>0){
                    string=(j+1)+". "+string+"\\n ";
                    string1+=string;
                    // string1+=string.replace("\\n","\n");
                }

            }
            if(string1.length>0){
                $scope.contract.relation_object_a+=item1.name+":\\n"+string1;
                // $scope.contract.relation_object_a=$scope.contract.relation_object_a.replace("\\n","\n");
            }

        }
        /*lay relation_object_b thay the cho property_Info*/
        for(var i=0; i<$scope.listProperty.properties.length;i++){
            var item=$scope.listProperty.properties[i];
            var stringpro="";
            // $scope.contract.relation_object_b+=i+1 + ".";
            if(item.property_info!==""&& typeof item.property_info!=='undefined'){
                stringpro+=item.property_info;
            }
            switch (item.type){
                case "01":
                    if(item.apartment!=null && item.apartment!=='undefined'){
                        if(item.apartment.apartment_number!=="" && typeof item.apartment.apartment_number!=='undefined'){
                            stringpro+="Căn hộ số: "+item.apartment.apartment_number+"; ";
                        }
                        if(item.apartment.apartment_address!=="" && typeof item.apartment.apartment_address!=='undefined'){
                            stringpro+="Địa chỉ căn hộ: "+item.apartment.apartment_address+"; ";
                        }
                        if(item.apartment.apartment_floor!=="" && typeof item.apartment.apartment_floor!=='undefined'){
                            stringpro+="Tầng số: "+item.apartment.apartment_floor+"; ";
                        }
                        if(item.apartment.apartment_area_use!=="" && typeof item.apartment.apartment_area_use!=='undefined'){
                            stringpro+="Diện tích căn hộ: "+item.apartment.apartment_area_use+"; ";
                        }
                        if(item.apartment.apartment_area_build!=="" && typeof item.apartment.apartment_area_build!=='undefined'){
                            stringpro+="Diện tích xây dựng: "+item.apartment.apartment_area_build+"; ";
                        }
                        if(item.apartment.apartment_structure!=="" && typeof item.apartment.apartment_structure!=='undefined'){
                            stringpro+="Kết cấu nhà: "+item.apartment.apartment_structure+"; ";
                        }
                        if(item.apartment.apartment_total_floor!=="" && typeof item.apartment.apartment_total_floor!=='undefined'){
                            stringpro+="Tổng số tầng: "+item.apartment.apartment_total_floor+"; ";
                        }
                    }
                    if(item.land!=null && item.land!=="" && item.land!=='undefined'){
                        if(item.land.land_number!=="" && typeof item.land.land_number!=='undefined'){
                            stringpro+="Thửa đất số: "+item.land.land_number+"; ";
                        }
                        if(item.land.land_map_number!=="" && typeof item.land.land_map_number!=='undefined'){
                            stringpro+="Tờ bản đồ số: "+item.land.land_map_number+"; ";
                        }
                        if(item.land.land_address!=="" && typeof item.land.land_address!=='undefined'){
                            stringpro+="Địa chỉ: "+item.land.land_address+"; ";
                        }
                        if(item.land.land_certificate!=="" && typeof item.land.land_certificate!=='undefined'){
                            stringpro+="Số giấy chứng nhận: "+item.land.land_certificate+"; ";
                        }
                        if(item.land.land_issue_place!=="" && typeof item.land.land_issue_place!=='undefined'){
                            stringpro+="Nơi cấp: "+item.land.land_issue_place+"; ";
                        }
                        if(item.land.land_issue_date!=="" && typeof item.land.land_issue_date!=='undefined'){
                            stringpro+="Ngày cấp: "+item.land.land_issue_date+"; ";
                        }
                        if(item.land.land_associate_property!=="" && typeof item.land.land_associate_property!=='undefined'){
                            stringpro+="Tài sản gắn liền với đất: "+item.land.land_associate_property+"; ";
                        }
                        if(item.land.land_area!=="" && typeof item.land.land_area!=='undefined'){
                            stringpro+="Diện tích: "+item.land.land_area+"; ";
                        }
                        if(item.land.land_private_area!=="" && typeof item.land.land_private_area!=='undefined'){
                            stringpro+="Diện tích sử dụng riêng: "+item.land.land_private_area+"; ";
                        }
                        if(item.land.land_public_area!=="" && typeof item.land.land_public_area!=='undefined'){
                            stringpro+="Diện tích sử dụng chung: "+item.land.land_public_area+"; ";
                        }
                        if(item.land.land_use_purpose!=="" && typeof item.land.land_use_purpose!=='undefined'){
                            stringpro+="Mục đích sử dụng: "+item.land.land_use_purpose+"; ";
                        }
                        if(item.land.land_use_period!=="" && typeof item.land.land_use_period!=='undefined'){
                            stringpro+="Thời hạn: "+item.land.land_use_period+"; ";
                        }
                        if(item.land.land_use_origin!=="" && typeof item.land.land_use_origin!=='undefined'){
                            stringpro+="Nguồn gốc sử dụng: "+item.land.land_use_origin+"; ";
                        }
                        if(item.land.restrict!=="" && typeof item.land.restrict!=='undefined'){
                            stringpro+="Hạn chế quyền nếu có: "+item.land.restrict+"; ";
                        }
                    }
                    break;
                case "02":
                    if(item.vehicle.car_license_number!=="" && typeof item.vehicle.car_license_number!=='undefined'){
                        stringpro+="Biển kiểm soát: "+item.vehicle.car_license_number+"; ";
                    }
                    if(item.vehicle.car_regist_number!=="" && typeof item.vehicle.car_regist_number!=='undefined'){
                        stringpro+="Số đăng ký: "+item.vehicle.car_regist_number+"; ";
                    }
                    if(item.vehicle.car_issue_place!=="" && typeof item.vehicle.car_issue_place!=='undefined'){
                        stringpro+="Nơi cấp: "+item.vehicle.car_issue_place+"; ";
                    }
                    if(item.vehicle.car_issue_date!=="" && typeof item.vehicle.car_issue_date!=='undefined'){
                        stringpro+="Ngày cấp: "+item.vehicle.car_issue_date+"; ";
                    }
                    if(item.vehicle.car_frame_number!=="" && typeof item.vehicle.car_frame_number!=='undefined'){
                        stringpro+="Số khung: "+item.vehicle.car_frame_number+"; ";
                    }
                    if(item.vehicle.car_machine_number!=="" && typeof item.vehicle.car_machine_number!=='undefined'){
                        stringpro+="Số máy: "+item.vehicle.car_machine_number+"; ";
                    }
                    break;
                default:
                    break;
            }

            if(item.owner_info!=="" && typeof item.owner_info!=='undefined'){
                if(stringpro.length > 0){
                    stringpro+="; Chủ sở hữu: "+item.owner_info+"; ";
                }else{
                    stringpro+="Chủ sở hữu: "+item.owner_info+"; ";
                }
            }
            if(item.other_info!=="" && typeof item.other_info!=='undefined'){
                stringpro+="Thông tin khác: "+item.other_info+"; " ;
            }
            if(stringpro.length>0){
                if($scope.listProperty.properties.length>1){
                    $scope.contract.relation_object_b+=i+1 + "."+ stringpro+"\\n";
                }else{
                    $scope.contract.relation_object_b+=stringpro;
                }
                stringpro="";
            }
        }

    }

    //convert date dd/mm/yyyy sang date cua he thong.
    $scope.formatDate= function (strDate) {
        if(strDate==null || strDate.length!=10) return null;
        var dateArray = strDate.split("/");
        var date = dateArray[2] + "-" + dateArray[1] + "-" + dateArray[0];
        if(moment(date,"YYYY/MM/DD",true).isValid()){
            return new Date(date);
        }else{
            return null;
        }

    }
    $scope.checkDate=function () {
        var date1=$scope.formatDate($scope.contract.received_date);
        var date2=$scope.formatDate($scope.contract.notary_date);
        var now=new Date();
        if(date1==null || date2==null || date1>date2 || date1>now || date2>now){
            return true;
        }
        return false;
    }

    /*check validate*/
    $scope.checkValidate=function () {
        if(!$scope.contractKind.contract_kind_code.length>0){
            return false;
        }
        if(!$scope.contract.contract_template_id>0){
            return false;
        }
        if(!$scope.contract.contract_number.toString().length>0){
            return false;
        }
        if(!$scope.contract.received_date.toString().length==10){
            return false;
        }
        if(!$scope.contract.notary_date.toString().length==10){
            return false;
        }
        if(!$scope.contract.drafter_id>0){
            return false;
        }
        if(!$scope.contract.notary_id>0){
            return false;
        }

        return true;
    }

}]);

//giup format cac' so' sang dang tien` te 1,000,000
myApp.$inject = ['$scope'];
myApp.directive('format', ['$filter', function ($filter) {
    return {
        require: '?ngModel',
        link: function (scope, elem, attrs, ctrl) {
            if (!ctrl) return;

            ctrl.$formatters.unshift(function (a) {
                return $filter(attrs.format)(ctrl.$modelValue)
            });

            ctrl.$parsers.unshift(function (viewValue) {
                var plainNumber = viewValue.replace(/[^\d|\-+|\.+]/g, '');
                elem.val($filter(attrs.format)(plainNumber));
                return plainNumber;
            });
        }
    };
}]);

myApp.directive('dynamic', function ($compile) {
    return {
        restrict: 'A',
        replace: true,
        link: function (scope, ele, attrs) {
            scope.$watch(attrs.dynamic, function(html) {
                ele.html(html);
                $compile(ele.contents())(scope);
            });
        }
    };
});
