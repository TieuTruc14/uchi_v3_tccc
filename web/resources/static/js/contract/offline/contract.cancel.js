/**
 * Created by TienManh on 5/28/2017.
 */

// var myApp = angular.module('osp', ["xeditable","ngSanitize"]);
myApp.run(function(editableOptions) {
    editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'
});

myApp.controller('contractEditController',['$scope','$http','$filter','$window','fileUpload',function ($scope,$http,$filter,$window,fileUpload) {
    $scope.privys={};
    $scope.listProperty={};
    $scope.number_contract_old=number_contract;
    // var url="http://localhost:8082/api";
    $scope.checkOnline=false;
    if(from!=null && from=="1"){
        $scope.checkOnline=true;
    }


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

    $http.get(url+"/contract/list-property-type")
        .then(function (response) {
            $scope.proTypes=response.data;
        });

    /*danh sach ngan hang*/
    $http.get(url+"/bank/getAllBank")
        .then(function(response) {
            $scope.banks=response.data;
        });


    /*test for load html $compile*/
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

    // $scope.duongsu='<div ng-repeat="item in privys.privy track by $index"> <div class=""><b style="font-family: Times New Roman; font-size: 14pt;" class="">Bên <span ng-model="item.action" editspan="item.action" class="inputcontract" contenteditable="true">{{item.action}}</span></b> (sau đây gọi là {{item.name}}): </div> <div ng-repeat="user in item.persons track by $index" class="personList"> <div ng-switch on="user.type"> <div ng-switch-when="1"> <div class="">*Công ty:&nbsp;<span class="inputcontract" editspan="user.org_name" ng-model="user.org_name" placeholder="" contenteditable="true">{{user.org_name}}</span></div> <div class="">Địa chỉ: &nbsp;<span class="inputcontract" editspan="user.org_address" ng-model="user.org_address" placeholder="" contenteditable="true">{{user.org_address}}</span></div> <div class="">Mã số doanh nghiệp: <span class="inputcontract" editspan="user.org_code" ng-model="user.org_code" placeholder="" contenteditable="true">{{user.org_code}}</span> &nbsp; ,đăng ký lần đầu ngày: <span class="inputcontract" editspan="user.first_registed_date" ng-model="user.first_registed_date" placeholder=" &nbsp;" contenteditable="true">{{user.first_registed_date}}</span>&nbsp; , đăng ký thay đổi lần thứ&nbsp;<span class="inputcontract" editspan="user.number_change_registed" ng-model="user.number_change_registed" placeholder="" contenteditable="true">{{user.number_change_registed}}</span>&nbsp;ngày: <span class="inputcontract" editspan="user.change_registed_date" ng-model="user.change_registed_date" placeholder="&nbsp;" contenteditable="true">{{user.change_registed_date}}</span>&nbsp;theo&nbsp; <span class="inputcontract" editspan="user.department_issue" ng-model="user.department_issue" placeholder="" contenteditable="true">{{user.department_issue}}</span>&nbsp;; </div> <div class="">Họ và tên người đại diện:&nbsp;<span class="inputcontract" editspan="user.name" ng-model="user.name" placeholder="" contenteditable="true">{{user.name}}</span></div> <div class="">Chức danh:&nbsp;<span class="inputcontract" editspan="user.position" ng-model="user.position" placeholder="" contenteditable="true">{{user.position}}</span></div> </div> <div ng-switch-default> <div class="">*Họ và tên:&nbsp;<span href="#" editspan="user.name" ng-model="user.name" class="inputcontract" contenteditable="true">{{user.name}}</span>&nbsp; , sinh năm: <span placeholder="" editspan="user.birthday" ng-model="user.birthday" class="inputcontract" contenteditable="true">{{user.birthday}}</span> ; </div> </div> </div> <div class="">Giấy CMND số:&nbsp;<span placeholder="" editspan="user.passport" ng-model="user.passport" class="inputcontract" contenteditable="true">{{user.passport}}</span> cấp ngày:&nbsp; <span placeholder="" editspan="user.certification_date" ng-model="user.certification_date" class="inputcontract" contenteditable="true">{{user.certification_date}}</span> tại: <span placeholder="" editspan="user.certification_place" ng-model="user.certification_place" class="inputcontract" contenteditable="true">{{user.certification_place}}</span> ; </div> <div class="">Địa chỉ:&nbsp;<span placeholder="" editspan="user.address" ng-model="user.address" class="inputcontract" contenteditable="true">{{user.address}}</span>; </div> </div> </div>';
    // $scope.taisan='<div ng-repeat="item in listProperty.properties track by $index"> <div ng-switch on="item.type_view"> <div ng-switch-when="0"><span class="">Quyền sử dụng đất của bên A đối với thửa đất theo giấy chứng nhận số <span class="inputcontract" editspan="item.land.land_certificate" ng-model="item.land.land_certificate" placeholder="" contenteditable="true">{{item.land.land_certificate}}</span> &nbsp;do <span class="inputcontract" editspan="item.land.land_issue_place" ng-model="item.land.land_issue_place" placeholder="" contenteditable="true">{{item.land.land_issue_place}}</span> &nbsp; cấp ngày <span class="inputcontract" editspan="item.land.land_issue_date" ng-model="item.land.land_issue_date" placeholder="" contenteditable="true">{{item.land.land_issue_date}}</span>&nbsp;cụ thể như sau:</span> <div class="">-Thửa đất số: <span class="inputcontract" editspan="item.land.land_number" ng-model="item.land.land_number" placeholder="&nbsp;" contenteditable="true">{{item.land.land_number}}</span>&nbsp; , tờ bản đồ số:&nbsp; <span class="inputcontract" editspan="item.land.land_map_number" ng-model="item.land.land_map_number" placeholder="" contenteditable="true">{{item.land.land_map_number}}</span></div> <div class="">-Địa chỉ thửa đất: &nbsp; <span class="inputcontract" editspan="item.land.land_address" ng-model="item.land.land_address" placeholder="" contenteditable="true">{{item.land.land_address}}</span>&nbsp; </div> <div class="">-Diện tích: <span class="inputcontract" editspan="item.land.land_area" ng-model="item.land.land_area" placeholder="" contenteditable="true">{{item.land.land_area}}</span> &nbsp;m2 (bằng chữ: <span class="inputcontract" editspan="item.land.land_area_text" ng-model="item.land.land_area_text" placeholder="" contenteditable="true">{{item.land.land_area_text}}</span> &nbsp;mét vuông ) </div> <div class="">-Hình thức sử dụng:</div> <blockquote style="margin: 0 0 0 40px; border: none; padding: 0px;"> <blockquote style="margin: 0 0 0 40px; border: none; padding: 0px;"> <div class="">+Sử dụng riêng: &nbsp;<span class="inputcontract" editspan="item.land.land_private_area" ng-model="item.land.land_private_area" placeholder="" contenteditable="true">{{item.land.land_private_area}}</span> &nbsp;&nbsp; </div> <div class="">+Sử dụng chung: <span class="inputcontract" editspan="item.land.land_public_area" ng-model="item.land.land_public_area" placeholder=" " contenteditable="true">{{item.land.land_public_area}}</span>&nbsp; &nbsp;&nbsp; </div> </blockquote> </blockquote> <span style="font-size: 17.5px;">-Mục đích sử dụng:&nbsp;<span class="inputcontract" editspan="item.land.land_use_purpose" ng-model="item.land.land_use_purpose" placeholder="" contenteditable="true">{{item.land.land_use_purpose}}</span></span> <div class="">-Thời hạn sử dụng: <span class="inputcontract" editspan="item.land.land_use_period" ng-model="item.land.land_use_period" placeholder="" contenteditable="true">{{item.land.land_use_period}}</span> </div> <div class="">-Nguồn gốc sử dụng: <span class="inputcontract" editspan="item.land.land_use_origin" ng-model="item.land.land_use_origin" placeholder="" contenteditable="true">{{item.land.land_use_origin}}</span></div> </div> <div ng-switch-when="1"> <div class="">Căn hộ thuộc quyền sở hữu của bên A theo giấy chứng nhận số <span class="inputcontract" editspan="item.land.land_certificate" ng-model="item.land.land_certificate" placeholder="" contenteditable="true">{{item.land.land_certificate}}</span> &nbsp;do <span class="inputcontract" editspan="item.land.land_issue_place" ng-model="item.land.land_issue_place" placeholder="" contenteditable="true">{{item.land.land_issue_place}}</span> cấp ngày <span class="inputcontract" editspan="item.land.land_issue_date" ng-model="item.land.land_issue_date" placeholder="" contenteditable="true">{{item.land.land_issue_date}}</span> , cụ thể như sau: </div> <div class="">-Địa chỉ:&nbsp;<span class="inputcontract" editspan="item.apartment.apartment_address" ng-model="item.apartment.apartment_address" placeholder="" contenteditable="true">{{item.apartment.apartment_address}}</span> </div> <div class="">-Căn hộ số: <span class="inputcontract" editspan="item.apartment.apartment_number" ng-model="item.apartment.apartment_number" placeholder="" contenteditable="true">{{item.apartment.apartment_number}}</span> tầng: <span class="inputcontract" editspan="item.apartment.apartment_floor" ng-model="item.apartment.apartment_floor" placeholder="" contenteditable="true">{{item.apartment.apartment_floor}}</span></div> <div class="">-Tổng diện tích sử dụng:&nbsp;<span class="inputcontract" editspan="item.apartment.apartment_area_use" ng-model="item.apartment.apartment_area_use" placeholder="" contenteditable="true">{{item.apartment.apartment_area_use}}</span> </div> <div class="">-Diện tích xây dựng:&nbsp;<span class="inputcontract" editspan="item.apartment.apartment_area_build" ng-model="item.apartment.apartment_area_build" placeholder="" contenteditable="true">{{item.apartment.apartment_area_build}}</span> </div> <div class="">-Kết cấu nhà:&nbsp;<span class="inputcontract" editspan="item.apartment.apartment_structure" ng-model="item.apartment.apartment_structure" placeholder="" contenteditable="true">{{item.apartment.apartment_structure}}</span> </div> <div class="">-Số tầng nhà chung cư: <span class="inputcontract" editspan="item.apartment.apartment_total_floor" ng-model="item.apartment.apartment_total_floor" placeholder="" contenteditable="true">{{item.apartment.apartment_total_floor}}</span> &nbsp;tầng </div> <div class="">Căn hộ nêu trên là tài sản gắn liền với thửa đất sau:</div> <div class="">-Thửa đất số: <span class="inputcontract" editspan="item.land.land_number" ng-model="item.land.land_number" placeholder="&nbsp;" contenteditable="true">{{item.land.land_number}}</span> , tờ bản đồ số:&nbsp; <span class="inputcontract" editspan="item.land.land_map_number" ng-model="item.land.land_map_number" placeholder="" contenteditable="true">{{item.land.land_map_number}}</span></div> <div class="">-Địa chỉ thửa đất: &nbsp; <span class="inputcontract" editspan="item.land.land_address" ng-model="item.land.land_address" placeholder="" contenteditable="true">{{item.land.land_address}}</span> </div> <div class="">-Diện tích: <span class="inputcontract" editspan="item.land.land_area" ng-model="item.land.land_area" placeholder="" contenteditable="true">{{item.land.land_area}}</span> &nbsp;m2 (bằng chữ: <span class="inputcontract" editspan="item.land.land_area_text" ng-model="item.land.land_area_text" placeholder="" contenteditable="true">{{item.land.land_area_text}}</span> &nbsp;mét vuông ) </div> <div class="">-Hình thức sử dụng:</div> <blockquote style="margin: 0 0 0 40px; border: none; padding: 0px;"> <blockquote style="margin: 0 0 0 40px; border: none; padding: 0px;"> <div class="">+Sử dụng riêng: &nbsp;<span class="inputcontract" editspan="item.land.land_private_area" ng-model="item.land.land_private_area" placeholder="" contenteditable="true">{{item.land.land_private_area}}</span> </div> <div class="">+Sử dụng chung: <span class="inputcontract" editspan="item.land.land_public_area" ng-model="item.land.land_public_area" placeholder=" " contenteditable="true">{{item.land.land_public_area}}</span> </div> </blockquote> </blockquote> <span style="font-size: 17.5px;">-Mục đích sử dụng:&nbsp;<span class="inputcontract" editspan="item.land.land_use_purpose" ng-model="item.land.land_use_purpose" placeholder="" contenteditable="true">{{item.land.land_use_purpose}}</span></span> <div class="">-Thời hạn sử dụng: <span class="inputcontract" editspan="item.land.land_use_period" ng-model="item.land.land_use_period" placeholder="" contenteditable="true">{{item.land.land_use_period}}</span></div> <div class="">-Nguồn gốc sử dụng: <span class="inputcontract" editspan="item.land.land_use_origin" ng-model="item.land.land_use_origin" placeholder="" contenteditable="true">{{item.land.land_use_origin}}</span></div> <div class="">Những hạn chế về quyền sử dụng đất(nếu có):&nbsp;<span class="inputcontract" editspan="item.restrict" ng-model="item.restrict" placeholder="" contenteditable="true">{{item.restrict}}</span> </div> </div> <div ng-switch-default> <div class="">Thông tin tài sản:&nbsp;<span class="inputcontract" editspan="item.property_info" ng-model="item.property_info" placeholder="" contenteditable="true">{{item.property_info}}</span></div> </div> </div> </div>';

    $http.get(url+"/contract/get-contract-by-id",{params:{id:id}})
        .then(function (response) {

            $scope.contract=response.data;
            $scope.contract.summary="Văn bản hủy hợp đồng số "+$scope.contract.contract_number;
            $scope.contract.file_name="";
            $scope.contract.file_path="";

            if($scope.contract.json_property!=null && $scope.contract.json_property!="" && $scope.contract.json_property!= 'undefined'){
                try{
                    var pri=$scope.contract.json_property.substr(1,$scope.contract.json_property.length-2);
                    $scope.listProperty=JSON.parse(pri);

                    /*default date for contract-property. Neu de ngoai thi se ko load dc cung`. khi click vao date lan` dau` ko chon thi se bien' mat*/
                    $(function () {
                       $scope.datePropertyFormat();

                    });
                }catch (e){
                    $scope.listProperty="";
                }

            }
            if($scope.contract.json_person!=null && $scope.contract.json_person!="" && $scope.contract.json_person!= 'undefined'){
                try{
                    var person=$scope.contract.json_person.substr(1,$scope.contract.json_person.length-2);
                    $scope.privys=JSON.parse(person);
                    $(function () {
                        $scope.datePersonFormat();
                    })
                }catch (e){
                    $scope.privys="";
                }

            }
            /*default date for contract-property. Neu de ngoai thi se ko load dc cung`. khi click vao date lan` dau` ko chon thi se bien' mat*/
            $(function () {
                $('#drafterDate').datepicker({
                    format: "dd/mm/yyyy",
                    startDate: "01/01/1900",
                    endDate: endDate,
                    forceParse : false,
                    language: 'vi'
                }).on('changeDate', function (ev) {
                    $(this).datepicker('hide');
                });
                $('#notaryDate').datepicker({
                    format: "dd/mm/yyyy",
                    startDate: "01/01/1900",
                    endDate: endDate,
                    forceParse : false,
                    language: 'vi'
                }).on('changeDate', function (ev) {
                    $(this).datepicker('hide');
                });
            });

            $scope.checkBank = ($scope.contract.addition_status==0)? false:true;
            $scope.showDescrip=$scope.checkBank;

            $scope.checkError=($scope.contract.error_status==0)?false:true;
            $scope.showError=$scope.checkError;

            //set contract_NUMBER
            if(org_type==1){
                $http.get(url+"/contract/contractNumber",{params:{year:now.getFullYear(),userId:userEntryId}})
                    .then(function (response) {
                        $scope.contract.contract_number=now.getFullYear()+"/"+userEntryId+"/"+response.data;
                        return response;
                    });
            }else{
                $http.get(url+"/contract/contractNumber",{params:{year:now.getFullYear(),userId:0}})
                    .then(function (response) {
                        $scope.contract.contract_number=now.getFullYear()+"/"+response.data;
                        return response;
                    });
            }

        });
    $scope.changeDateNotary=function (value) {
        if(value!=null && value.length==10 && moment(value,"DD/MM/YYYY",true).isValid()){
            var dateArray = value.split("/");
            var year=dateArray[2];
            if(year!=now.getFullYear()){
                if(Number(year) && 1900<year && year<2100){
                    //kiem tra xem co phai phuong xa tap trung ko.(Neu la phuong xa tap trung 1 project thi contract_number= year/userId/contract_number)
                    if(org_type==1){
                        $http.get(url+"/contract/contractNumber",{params:{year:year,userId:userEntryId}})
                            .then(function (response) {
                                $scope.contract.contract_number=year+"/"+userEntryId+"/"+response.data;
                                return response;
                            });
                    }else{
                        $http.get(url+"/contract/contractNumber",{params:{year:year,userId:0}})
                            .then(function (response) {
                                $scope.contract.contract_number=year+"/"+response.data;
                                return response;
                            });
                    }

                }
            }
        }
    }

    /*duong su va tai san*/
    var alphabet=["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];

    $scope.addPrivy=function () {
        if($scope.privys.privy.length<alphabet.length){
            var object={id: $scope.privys.privy.length, name: "Bên "+alphabet[$scope.privys.privy.length],action:"",  persons: [ { id: "", name: "", description:"" } ] };
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
        var object= {type:0, id: "",name: "",birthday:"",passport:"",certification_date:"",certification_place:"",address:"",position:"", description:"",org_name:"",org_address:"",org_code:"",first_registed_date:"",number_change_registed:"",change_registed_date:"",department_issue:"" };
        $scope.privys.privy[index].persons.push(object);
        $(function () {
            $scope.datePersonFormat();
        })
    }
    $scope.removePerson=function (parentIndex,index) {
        $scope.privys.privy[parentIndex].persons.splice(index,1);
    }

    $scope.addProperty=function () {
        var object={ type: "01", id: $scope.listProperty.properties.length+1,type_view:"", property_info:"", owner_info:"", other_info:"",restrict:"",apartment:{apartment_address:"",apartment_number:"",apartment_floor:"",apartment_area_use:"",apartment_area_build:"",apartment_structure:"",apartment_total_floor:""}, land: { land_certificate:"", land_issue_place:"", land_issue_date:"", land_map_number:"", land_number:"", land_address:"", land_area:"", land_area_text:"", land_public_area:"", land_private_area:"", land_use_purpose:"", land_use_period:"", land_use_origin:"", land_associate_property:"", land_street:"", land_district:"", land_province:"", land_full_of_area:"" }, vehicle:{ car_license_number:"", car_regist_number:"", car_issue_place:"", car_issue_date:"", car_frame_number:"", car_machine_number:"" } };
        $scope.listProperty.properties.push(object);
        $(function () {
            $scope.datePropertyFormat();
        });
    }

    $scope.removeProperty=function (index) {
        $scope.listProperty.properties.splice(index,1);
    }
    /*set format date for person and property*/
    $scope.datePersonFormat=function () {
        for(var i=0;i<$scope.privys.privy.length;i++){
            for(var j=0;j<$scope.privys.privy[i].persons.length;j++){
                var string="#birthday"+i+"-"+j;
                $(string).datepicker({
                    format: "dd/mm/yyyy",
                    startDate: "01/01/1900",
                    endDate: endDate,
                    forceParse : false,
                    language: 'vi'
                }).on('changeDate', function (ev) {
                    $(this).datepicker('hide');
                });
                var pla="#certification"+i+"-"+j;
                $(pla).datepicker({
                    format: "dd/mm/yyyy",
                    startDate: "01/01/1900",
                    endDate: endDate,
                    forceParse : false,
                    language: 'vi'
                }).on('changeDate', function (ev) {
                    $(this).datepicker('hide');
                });
            }
        }
    }

    $scope.datePropertyFormat=function () {
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
    }


    if(notary_id!=null && notary_id!='underfined' && notary_id!="" && notary_id!="0"){
        $http.get(url+"/users/get-user", {params: { id: notary_id }})
            .then(function(response) {
                $scope.notary=response.data;
            });
    }

    if(drafter_id!=null && drafter_id!='underfined' && drafter_id!="" && drafter_id!="0"){
        $http.get(url+"/users/get-user", {params: { id: drafter_id }})
            .then(function(response) {
                $scope.drafter=response.data;
            });
    }

    /*Calculate total fee*/
    $scope.calculateTotal=function () {
        /*set default =0 de khi parseInt ko bi loi neu' ng dung` xoa tren giao dien dua vao` chuoi~ rong~*/
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


    /*When click Luu thong tin*/
    $scope.cancelContract=function () {
        if($scope.checkValidate()){
            if($scope.checkDate()){
                $("#checkDate").modal('show');
            }else {
                //check contract_number
                $http.get(url+"/contract/checkContractNumber",{params: {contract_number:$scope.contract.contract_number}})
                    .then(function (response) {
                        if(response.statusText=='OK' && response.data==true){
                            var object_bank_by_id = $filter('filter')($scope.banks, {code: $scope.contract.bank_code },true)[0];
                            if(object_bank_by_id!=null && object_bank_by_id!="undefined"){
                                $scope.contract.bank_name=object_bank_by_id.name;
                            }
                            /*genaral duong su va tai san*/
                            $scope.contract.json_property="'"+JSON.stringify($scope.listProperty)+"'";
                            $scope.contract.json_person="'"+JSON.stringify($scope.privys)+"'";

                            $scope.contract.entry_date_time=new Date();
                            $scope.contract.update_date_time=new Date();
                            if($scope.checkBank==true){
                                $scope.contract.addition_status=1;
                            }else{
                                $scope.contract.addition_status=0;
                            }
                            if($scope.checkError==true){$scope.contract.error_status=1;}else{$scope.contract.error_status=0;}

                            $scope.genInforProAndPrivys();
                            var contractCancel=JSON.parse(JSON.stringify($scope.contract));
                            if($scope.myFile!=null && $scope.myFile!='undefined'&&$scope.myFile.size>0){
                                if($scope.myFile.size>5242000){
                                    $("#errorMaxFile").modal('show');
                                }else{
                                    var file = $scope.myFile;
                                    var uploadUrl = url+"/contract/uploadFile";
                                    fileUpload.uploadFileToUrl(file, uploadUrl)
                                        .then(function (response) {
                                                if( response.data!=null && response.data!='undefined' && response.status==200){
                                                    $scope.contract.file_name=response.data.name;
                                                    $scope.contract.file_path=response.data.path;
                                                    var contractCancel=JSON.parse(JSON.stringify($scope.contract));
                                                    $http.post(url+"/contract/cancel",contractCancel, {headers: {'Content-Type': 'application/json'} })
                                                        .then(function (response) {
                                                                if(response.statusText=='OK'){
                                                                    if($scope.checkOnline){
                                                                        $window.location.href = contextPath+'/contract/temporary/list?status=4';
                                                                    }else{
                                                                        $window.location.href = contextPath+'/contract/list?status=3';
                                                                    }
                                                                }else{
                                                                    $scope.checkCancel=false;
                                                                    $("#errorAdd").modal('show');
                                                                }
                                                            },
                                                            function(response){
                                                                // failure callback
                                                                $scope.checkCancel=false;
                                                                $("#errorAdd").modal('show');

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
                                $http.post(url+"/contract/cancel",contractCancel, {headers: {'Content-Type': 'application/json'} })
                                    .then(function (response) {
                                            if(response.statusText=='OK'){
                                                if($scope.checkOnline){
                                                    $window.location.href = contextPath+'/contract/temporary/list?status=4';
                                                }else{
                                                    $window.location.href = contextPath+'/contract/list?status=3';
                                                }
                                            }else{
                                                $scope.checkCancel=false;
                                                $("#errorAdd").modal('show');
                                            }
                                        },
                                        function(response){
                                            // failure callback
                                            $scope.checkCancel=false;
                                            $("#errorAdd").modal('show');

                                        }
                                    );
                            }

                        }else{
                            alert(response.statusText);
                            $("#checkContractNumber").modal('show');
                        }
                    });
            }

        }else{
            $("#checkValidate").modal('show');
        }



    }

    $scope.genInforProAndPrivys=function () {
        $scope.contract.relation_object_a="";
        $scope.contract.relation_object_b="";
        /*gen contract.propertyInfo va relation_object*/
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
        /*end gen info*/
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
    $scope.checkValidate=function () {
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