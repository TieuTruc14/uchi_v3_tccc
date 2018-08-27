myApp.controller('contractAddController',['$scope','$http','$filter','$window','$timeout','$q','fileUpload','$parse',function ($scope,$http,$filter,$window,$timeout,$q,fileUpload,$parse) {

    var now=new Date();
    var endDate=("0" + now.getDate()).slice(-2) + '/' + ("0" + (now.getMonth() + 1)).slice(-2) + '/' +  now.getFullYear();
    $scope.checkLoad=false;
    // var url="http://localhost:8082/api";
    $scope.contract={tcid:"",type:"",contract_template_id:"",contract_number:"",contract_value:"",relation_object_a:"",relation_object_b:"",relation_object_c:"",notary_id:"",drafter_id:"",received_date:"",notary_date:"",user_require_contract:"",property_type:"",property_info:"",owner_info:"",other_info:"",land_certificate:"",land_issue_place:"",land_issue_date:"",land_map_number:"",land_number:"",land_address:"",land_area:"",land_public_area:"",land_private_area:"",land_use_purpose:"",land_use_period:"",land_use_origin:"",land_associate_property:"",land_street:"",land_district:"",land_province:"",land_full_of_area:"",car_license_number:"",car_regist_number:"",car_issue_place:"",car_issue_date:"",car_frame_number:"",car_machine_number:"",number_copy_of_contract:"",number_of_page:"",cost_tt91:"",cost_draft:"",cost_notary_outsite:"",cost_other_determine:"",cost_total:"",notary_place_flag:"",notary_place:"",bank_id:"",bank_service_fee:"",crediter_name:"",file_name:"",file_path:"",original_store_place:"",note:"",summary:"",entry_user_id:"",entry_user_name:"",entry_date_time:"",update_user_id:"",update_user_name:"",update_date_time:"",jsonstring:"",kindhtml:"",json_property:"",json_person:"",bank_code:""};
    $scope.list_load_privy=[{id:1,name:"Cá nhân - Cá nhân"},{id:2,name:"Tổ chức - Cá nhân"},{id:3,name:"Tổ chức - Tổ chức"}, {id:4,name:"Cá nhân - Tổ chức"}];


    $http.get(url+"/contract/list-contract-kind")
        .then(function(response) {
            $scope.contractKinds=response.data;
        });

    $scope.myFunc=function(code){
        $http.get(url+"/contract/list-contract-template-by-contract-kind-code", {params:{code:code}})
            .then(function(response) {
                $scope.contractTemplates=response.data;
            });
    }

    $scope.changeTemplate=function (code) {
        if(code>0){
            $http.get(url+"/contract/get-contract-template-by-code-template", {params:{code_temp:code}})
                .then(function(response) {
                    $scope.contract_template=response.data;
                    $scope.contract.kindhtml=$scope.contract_template.kind_html;
                });
        }

    }

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

    /*load default date notary*/
    var date=new Date();
    $scope.contract.received_date=("0" + date.getDate()).slice(-2) + '/' + ("0" + (date.getMonth() + 1)).slice(-2) + '/' +  date.getFullYear();
    $scope.contract.notary_date=("0" + date.getDate()).slice(-2) + '/' + ("0" + (date.getMonth() + 1)).slice(-2) + '/' +  date.getFullYear();

    /*load template duong su+tai san*/
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


    /*Duong su ben A-B-C*/
    var alphabet=["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];

    // $scope.privys = { name: "Đương sự", privy: [ { name: "Bên A", id: 1,action:"", persons: [ { id: "",name: "",birthday:"",passport:"",certification_date:"",certification_place:"",address:"", description:"" } ] }, { name: "Bên B", id: 2,action:"", persons: [ { id: "",name: "",birthday:"",passport:"",certification_date:"",certification_place:"",address:"", description:"" } ] } ] };
    $scope.privys = { name: "Đương sự", privy: [] };

    $scope.addPrivy=function () {
        if($scope.privys.privy.length<alphabet.length){
            // var object={ name: "Bên "+alphabet[$scope.privys.privy.length], id: $scope.privys.privy.length,action:"", persons: [ {type:"", id: "",name: "",birthday:"",passport:"",certification_date:"",certification_place:"",address:"",position:"", description:"",org_name:"",org_address:"",org_code:"",first_registed_date:"",number_change_registed:"",change_registed_date:"",department_issue:"" } ] };
            var object={ name: "Bên "+alphabet[$scope.privys.privy.length],type:"", id: $scope.privys.privy.length,action:"", persons: [ ] };

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


    /*dia diem cong chung*/
    $scope.contract.notary_place_flag=true;

    /*Thong tin tai san*/
    // $scope.listProperty=	{ name: "property", properties: [ { type: "", id: 1,type_view:"", property_info:"", owner_info:"", other_info:"",restrict:"",apartment:{apartment_address:"",apartment_number:"",apartment_floor:"",apartment_area_use:"",apartment_area_build:"",apartment_structure:"",apartment_total_floor:""}, land: { land_certificate:"", land_issue_place:"", land_issue_date:"", land_map_number:"", land_number:"", land_address:"", land_area:"", land_area_text:"", land_public_area:"", land_private_area:"", land_use_purpose:"", land_use_period:"", land_use_origin:"", land_associate_property:"", land_street:"", land_district:"", land_province:"", land_full_of_area:"" }, vehicle:{ car_license_number:"", car_regist_number:"", car_issue_place:"", car_issue_date:"", car_frame_number:"", car_machine_number:"" } } ] };
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


    });

    $scope.addProperty=function () {

        var object={ type: "", id: $scope.listProperty.properties.length+1,template:0, property_info:"", owner_info:"", other_info:"",restrict:"",apartment:{apartment_address:"",apartment_number:"",apartment_floor:"",apartment_area_use:"",apartment_area_build:"",apartment_structure:"",apartment_total_floor:""}, land: { land_certificate:"", land_issue_place:"", land_issue_date:"", land_map_number:"", land_number:"", land_address:"", land_area:"", land_area_text:"", land_public_area:"", land_private_area:"", land_use_purpose:"", land_use_period:"", land_use_origin:"", land_associate_property:"", land_street:"", land_district:"", land_province:"", land_full_of_area:"" }, vehicle:{ car_license_number:"", car_regist_number:"", car_issue_place:"", car_issue_date:"", car_frame_number:"", car_machine_number:"" } };
        $scope.listProperty.properties.push(object);
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

    /*end property*/
    $scope.viewAsDoc=function () {
        $("#viewHtmlAsWord").html($("#contentKindHtml").html());
        $("#viewContentAsWord").modal('show');
    }

    $scope.downloadWord=function () {
        $("#contentKindHtml").wordExport();
    }

    /*danh sach ngan hang*/
    $http.get(url+"/bank/getAllBank")
        .then(function(response) {
            $scope.banks=response.data;
        });
    $scope.contract.bank_service_fee=0;

    /*Calculate total fee*/
    $scope.contract.cost_draft=0;
    $scope.contract.cost_notary_outsite=0;
    $scope.contract.cost_other_determine=0;
    $scope.contract.cost_total=0;
    $scope.contract.cost_tt91=0;
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


    $scope.checkAdd=function () {
        if($scope.contract.notary_id==null || $scope.contract.notary_id=="" || $scope.contract.notary_id=='underfined'){
            $("#errorLog").modal('show');
        }else{
            $("#addContract").modal('show');
        }
    }

    /*When click Luu thong tin*/
    $scope.addContractWrapper=function () {
        $scope.checkLoad=false;
        if(!$scope.contract.notary_id>0){
            if($scope.checkValidateBasic()){
                $("#errorLog").modal('show');
            }else{
                $("#checkValidate").modal('show');
            }

        }else{
            if($scope.checkValidateAdvance()){
                if($scope.checkDate()){
                    $scope.checkLoad=true;
                    $("#checkDate").modal('show');
                }else{
                    $http.get(url+"/contract/checkContractNumber",{params: {contract_number:$scope.contract.contract_number}})
                        .then(function (response) {
                            if(response.statusText=='OK' && response.data==true){
                                $scope.contract.type="0";
                                /*genaral duong su va tai san*/
                                $scope.contract.json_property="'"+JSON.stringify($scope.listProperty)+"'";
                                $scope.contract.json_person="'"+JSON.stringify($scope.privys)+"'";

                                $scope.contract.entry_date_time=new Date();
                                $scope.contract.update_date_time=new Date();

                                /*gen property info and relation object*/
                                $scope.genInforProAndPrivys();
                                /*format list Duogn su in html*/
                                $scope.contract.kindhtml=$("div #contentKindHtml").html();

                                var contractAdd=JSON.parse(JSON.stringify($scope.contract));
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
                                                        var contractAdd=JSON.parse(JSON.stringify($scope.contract));
                                                        $http.post(url+"/contract/temporary",contractAdd, {headers: {'Content-Type': 'application/json'} })
                                                            .then(function (response) {
                                                                    if(response.statusText=='OK'){
                                                                        $window.location.href = contextPath+'/contract/temporary/list?status=1';
                                                                    }else{
                                                                        $scope.checkLoad=true;
                                                                        $("#errorEdit").modal('show');
                                                                    }
                                                                },
                                                                function(response){
                                                                    // failure callback
                                                                    $scope.checkLoad=true;
                                                                    $("#errorEdit").modal('show');
                                                                }
                                                            );
                                                    }else{
                                                        $scope.checkLoad=true;
                                                        $("#errorEdit").modal('show');
                                                    }
                                                },
                                                function(response){
                                                    // failure callback
                                                    $scope.checkLoad=true;
                                                    $("#errorEdit").modal('show');
                                                }
                                            );
                                    }

                                }else{
                                    $http.post(url+"/contract/temporary",contractAdd, {headers: {'Content-Type': 'application/json'} })
                                        .then(function (response) {
                                                if(response.statusText=='OK'){
                                                    $window.location.href = contextPath+'/contract/temporary/list?status=1';
                                                }else{
                                                    $scope.checkLoad=true;
                                                    $("#errorEdit").modal('show');
                                                }
                                            },
                                            function(response){
                                                // failure callback
                                                $scope.checkLoad=true;
                                                $("#errorEdit").modal('show');
                                            }
                                        );
                                }

                            }else{
                                $scope.checkLoad=true;
                                alert(response.statusText);
                                $("#checkContractNumber").modal('show');
                            }
                        });
                }


            }else{
                $("#checkValidate").modal('show');
            }
        }


    }

    $scope.addContract=function () {
        $scope.checkLoad=false;
        if($scope.checkDate()){
            $scope.checkLoad=true;
            $("#checkDate").modal('show');
        }else{
            $http.get(url+"/contract/checkContractNumber",{params: {contract_number:$scope.contract.contract_number}})
                .then(function (response) {
                    if(response.statusText=='OK' && response.data==true){
                        $scope.contract.type="3";

                        /*genaral duong su va tai san*/
                        $scope.contract.json_property="'"+JSON.stringify($scope.listProperty)+"'";
                        $scope.contract.json_person="'"+JSON.stringify($scope.privys)+"'";

                        $scope.contract.entry_date_time=new Date();
                        $scope.contract.update_date_time=new Date();

                        /*gen property info and relation object*/
                        $scope.genInforProAndPrivys();
                        // $scope.contract.kindhtml=$scope.getKindHtml();
                        $scope.contract.kindhtml=$("div #contentKindHtml").html();

                        /*end format*/
                        var contractAdd=JSON.parse(JSON.stringify($scope.contract));
                        if($scope.myFile!=null && $scope.myFile!='undefined'&& $scope.myFile.size>0){
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
                                                var contractAdd=JSON.parse(JSON.stringify($scope.contract));
                                                $http.post(url+"/contract/temporary",contractAdd, {headers: {'Content-Type': 'application/json'} })
                                                    .then(function (response) {
                                                            if(response.statusText=='OK'){
                                                                $window.location.href = contextPath+'/contract/temporary/list?status=1';
                                                            }else{
                                                                $scope.checkLoad=true;
                                                                $("#errorEdit").modal('show');
                                                            }
                                                        },
                                                        function(response){
                                                            // failure callback
                                                            $scope.checkLoad=true;
                                                            $("#errorEdit").modal('show');
                                                        }
                                                    );
                                            }else{
                                                $scope.checkLoad=true;
                                                $("#errorEdit").modal('show');
                                            }
                                        },
                                        function(response){
                                            // failure callback
                                            $scope.checkLoad=true;
                                            $("#errorEdit").modal('show');
                                        }
                                    );
                            }

                        }else{
                            $http.post(url+"/contract/temporary",contractAdd, {headers: {'Content-Type': 'application/json'} })
                                .then(function (response) {
                                        if(response.statusText=='OK'){
                                            $window.location.href = contextPath+'/contract/temporary/list?status=1';
                                        }else{
                                            $("#errorEdit").modal('show');
                                        }
                                    },
                                    function(response){
                                        // failure callback
                                        $scope.checkLoad=true;
                                        $("#errorEdit").modal('show');
                                    }
                                );
                        }

                    }else{
                        $scope.checkLoad=true;
                        alert(response.statusText);
                        $("#checkContractNumber").modal('show');
                    }
                });
        }



    }

    $scope.getKindHtml=function () {
        $("#copyContract").html($("div #contentKindHtml").html());
        $("#copyContract .duongsu").html(" ");
        $("#copyContract .taisan").html(" ");
        var kindhtml=$("#copyContract").html();
        $("#copyContract").html("  ");
        return kindhtml;
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

    /*gen info property and privys*/
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

    /*check validate form*/
    $scope.checkValidateBasic=function () {
        if(!$scope.contractKindValue>0){
            return false;
        }
        if(!$scope.contract.contract_template_id>0){
            return false;
        }
        if(!$scope.contract.contract_number.toString().length>0){
            return false;
        }
        return true;
    }

    $scope.checkValidateAdvance=function () {
        if(!$scope.contractKindValue>0){
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




