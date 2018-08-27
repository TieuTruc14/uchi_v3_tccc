/**
 * Created by TienManh on 6/23/2017.
 */

var myApp = angular.module('osp', ['ngSanitize']);

myApp.controller('contractListController',['$scope','$http','$filter','$timeout','$q',function ($scope,$http,$filter,$timeout,$q) {
    var now=new Date();
    var endDate=("0" + now.getDate()).slice(-2) + '/' + ("0" + (now.getMonth() + 1)).slice(-2) + '/' +  now.getFullYear();


    $scope.users="";
    $scope.listChoKy={items:"",rowCount:0,numberPerPage:20,pageNumber:1,pageList:[],pageCount:0};
    $scope.listDaKy={items:"",rowCount:0,numberPerPage:20,pageNumber:1,pageList:[],pageCount:0};
    $scope.listDongDau={items:"",rowCount:0,numberPerPage:20,pageNumber:1,pageList:[],pageCount:0};
    $scope.listChinhSua={items:"",rowCount:0,numberPerPage:20,pageNumber:1,pageList:[],pageCount:0};
    $scope.listTraVe={items:"",rowCount:0,numberPerPage:20,pageNumber:1,pageList:[],pageCount:0};
    $scope.search={basic:0,search_basic:"",contract_number:"",relation_object:"",property_info:"",time:"",fromTime:"",toTime:""};
    $scope.tab=1;//1: khi click listContract, 2 khi click listNotSynch
    $scope.showBasic=true;
    var search= JSON.stringify($scope.search);

    /*Load default*/
    $http.get(url+"/contract/list-contract-template")
        .then(function(response) {
            $scope.contractTemplates=response.data;
        });

    $http.post(url+"/users/selectByFilter","where true", {headers: {'Content-Type': 'application/json'} })
        .then(function (response) {
            $scope.users=response.data;

        });

    $http.get(url+"/contract/temporarysBySearch", {params: {search:search,type:0,offset:0,number:$scope.listChoKy.numberPerPage}})
        .then(function (response) {
            $scope.listChoKy.items=response.data;
            $scope.replaceNewLine(0);
            $scope.tooltip();
            setTimeout(function() {
                $scope.$apply(function() {
                    $scope.getContractName(0);
                });
            }, 1500);
        });
    $http.get(url+"/contract/countTemporaryBySearch", {params: {search:search,type:0}})
            .then(function (response) {
                $scope.listChoKy.rowCount=response.data;
                $scope.listChoKy.pageCount=getPageCountContract($scope.listChoKy);
                $scope.listChoKy.pageList=getPageList($scope.listChoKy);

            });
        $http.get(url+"/contract/countTemporaryBySearch", {params: {search:search,type:1}})
            .then(function (response) {
                $scope.listDaKy.rowCount=response.data;
                $scope.listDaKy.pageCount=getPageCountContract($scope.listDaKy);
                $scope.listDaKy.pageList=getPageList($scope.listDaKy);

            });
        $http.get(url+"/contract/countTemporaryBySearch", {params: {search:search,type:3}})
            .then(function (response) {
                $scope.listChinhSua.rowCount=response.data;
                $scope.listChinhSua.pageCount=getPageCountContract($scope.listChinhSua);
                $scope.listChinhSua.pageList=getPageList($scope.listChinhSua);

            });
        $http.get(url+"/contract/countTemporaryBySearch", {params: {search:search,type:2}})
            .then(function (response) {
                $scope.listTraVe.rowCount=response.data;
                $scope.listTraVe.pageCount=getPageCountContract($scope.listTraVe);
                $scope.listTraVe.pageList=getPageList($scope.listTraVe);

            });
        $http.get(url+"/transaction/countTransBySearch", {params: {search:search,syn_status:2}})
            .then(function (response) {
                $scope.listDongDau.rowCount=response.data;
                $scope.listDongDau.pageCount=getPageCountContract($scope.listDongDau);
                $scope.listDongDau.pageList=getPageList($scope.listDongDau);
            });


    /*end load default*/

    /*tab cho ky*/
    $scope.whenClickTabChoKy=function () {
        if(!$scope.listDaKy.items.length>0){
            var search= JSON.stringify($scope.search);
            $http.get(url+"/contract/temporarysBySearch", {params: {search:search,type:0,offset:$scope.listChoKy.numberPerPage*($scope.listChoKy.pageNumber-1),number:$scope.listChoKy.numberPerPage}})
                .then(function (response) {
                    $scope.listChoKy.items=response.data;
                    $scope.replaceNewLine(0);
                    $scope.getContractName(0);
                    $scope.tooltip();
                });
        }

    }
    $scope.loadPageChoKy=function (index) {
        if(index>=1){
            $scope.listChoKy.pageNumber=index;
            var search= JSON.stringify($scope.search);
            $http.get(url+"/contract/temporarysBySearch", {params: {search:search,type:0,offset:$scope.listChoKy.numberPerPage*($scope.listChoKy.pageNumber-1),number:$scope.listChoKy.numberPerPage}})
                .then(function (response) {
                    $scope.listChoKy.items=response.data;
                    $scope.getContractName(0);
                    $scope.replaceNewLine(0);
                    $scope.listChoKy.pageList=getPageList($scope.listChoKy);
                    $scope.tooltip();
                });
        }

    }
    /*end tab cho ky*/

    /*tab da ky*/
    $scope.whenClickTabDaKy=function () {
        if(!$scope.listDaKy.items.length>0){
            var search= JSON.stringify($scope.search);
            $http.get(url+"/contract/temporarysBySearch", {params: {search:search,type:1,offset:$scope.listDaKy.numberPerPage*($scope.listDaKy.pageNumber-1),number:$scope.listDaKy.numberPerPage}})
                .then(function (response) {
                    $scope.listDaKy.items=response.data;
                    $scope.getContractName(1);
                    $scope.replaceNewLine(1);
                    $scope.tooltip();
                });
        }

    }
    $scope.loadPageDaKy=function (index) {
        if(index>=1){
            $scope.listDaKy.pageNumber=index;
            var search= JSON.stringify($scope.search);
            $http.get(url+"/contract/temporarysBySearch", {params: {search:search,type:1,offset:$scope.listDaKy.numberPerPage*($scope.listDaKy.pageNumber-1),number:$scope.listDaKy.numberPerPage}})
                .then(function (response) {
                    $scope.listDaKy.items=response.data;
                    $scope.getContractName(1);
                    $scope.replaceNewLine(1);
                    $scope.listDaKy.pageList=getPageList($scope.listDaKy);
                    $scope.tooltip();
                });
        }
    }
    /*end tab da ky*/

    /*tab chinh sua*/
    $scope.whenClickTabChinhSua=function () {
        if(!$scope.listChinhSua.items.length>0){
            var search= JSON.stringify($scope.search);
            $http.get(url+"/contract/temporarysBySearch", {params: {search:search,type:3,offset:$scope.listChinhSua.numberPerPage*($scope.listChinhSua.pageNumber-1),number:$scope.listChinhSua.numberPerPage}})
                .then(function (response) {
                    $scope.listChinhSua.items=response.data;
                    $scope.getContractName(3);
                    $scope.replaceNewLine(3);
                    $scope.tooltip();
                });
        }
    }

    $scope.loadPageChinhSua=function (index) {
        if(index>=1){
            $scope.listChinhSua.pageNumber=index;
            var search= JSON.stringify($scope.search);

            $http.get(url+"/contract/temporarysBySearch", {params: {search:search,type:3,offset:$scope.listChinhSua.numberPerPage*($scope.listChinhSua.pageNumber-1),number:$scope.listChinhSua.numberPerPage}})
                .then(function (response) {
                    $scope.listChinhSua.items=response.data;
                    $scope.getContractName(3);
                    $scope.replaceNewLine(3);
                    $scope.listChinhSua.pageList=getPageList($scope.listChinhSua);
                    $scope.tooltip();
                });
        }

    }
    /*end tab chinh sua*/
    /*tab tra ve*/
    $scope.whenClickTabTraVe=function () {
        if(!$scope.listTraVe.items.length>0){
            var search= JSON.stringify($scope.search);
            $http.get(url+"/contract/temporarysBySearch", {params: {search:search,type:2,offset:$scope.listTraVe.numberPerPage*($scope.listTraVe.pageNumber-1),number:$scope.listTraVe.numberPerPage}})
                .then(function (response) {
                    $scope.listTraVe.items=response.data;
                    $scope.getContractName(2);
                    $scope.replaceNewLine(2);
                    $scope.tooltip();
                });
        }

    }
    $scope.loadPageTraVe=function (index) {
        if(index>=1){
            $scope.listTraVe.pageNumber=index;
            var search= JSON.stringify($scope.search);

            $http.get(url+"/contract/temporarysBySearch", {params: {search:search,type:2,offset:$scope.listTraVe.numberPerPage*($scope.listTraVe.pageNumber-1),number:$scope.listTraVe.numberPerPage}})
                .then(function (response) {
                    $scope.listTraVe.items=response.data;
                    $scope.getContractName(2);
                    $scope.replaceNewLine(2);
                    $scope.listTraVe.pageList=getPageList($scope.listTraVe);
                    $scope.tooltip();
                });
        }

    }
    /*end tab tra ve*/

    /*tab dong dau*/
    $scope.whenClickTabDongDau=function () {
        if(!$scope.listDongDau.items.length>0){
            var search= JSON.stringify($scope.search);
            $http.get(url+"/transaction/transactionsBySearch", {params: {search:search,syn_status:2,offset:0,number:$scope.listDongDau.numberPerPage}})
                .then(function (response) {
                    $scope.listDongDau.items=response.data;
                    $scope.replaceNewLine(5);
                    $scope.tooltip();
                });
        }

    }
    $scope.loadPageDongDau=function (index) {
        if(index>=1){
            $scope.listDongDau.pageNumber=index;
            var search= JSON.stringify($scope.search);

            $http.get(url+"/transaction/transactionsBySearch", {params: {search:search,syn_status:2,offset:$scope.listDongDau.numberPerPage*($scope.listDongDau.pageNumber-1),number:$scope.listDongDau.numberPerPage}})
                .then(function (response) {
                    $scope.listDongDau.items=response.data;
                    $scope.replaceNewLine(5);
                    $scope.listDongDau.pageList=getPageList($scope.listDongDau);
                    $scope.tooltip();
                });
        }

    }
    /*end tab dong dau*/

    /*load for search*/

    $scope.showTime=false;
    $scope.changTime=function (time) {
        if(time==5){
            $scope.showTime=true;
        }else{
            $scope.showTime=false;
        }
    }

    //search basic
    $scope.searchBasic=function () {
        $scope.search.basic=0;
        $scope.listChoKy.pageNumber=1;
        $scope.listDaKy.pageNumber=1;
        $scope.listChinhSua.pageNumber=1;
        $scope.listTraVe.pageNumber=1;
        $scope.listDongDau.pageNumber=1;
        $scope.loadCountContract();
        $scope.loadListContract();

    }

    // //search advance
    $scope.searchAdvance=function () {
        if($scope.checkDate()){
            $scope.search.basic=1;
            $scope.listChoKy.pageNumber=1;
            $scope.listDaKy.pageNumber=1;
            $scope.listChinhSua.pageNumber=1;
            $scope.listTraVe.pageNumber=1;
            $scope.listDongDau.pageNumber=1;
            $scope.loadCountContract();
            $scope.loadListContract();

        }else{
            // $("#errorFormatDate").modal('show');
        }


    }

    $scope.clearCondition=function () {
        $scope.search.search_basic="";
        $scope.search.contract_number="";
        $scope.search.relation_object="";
        $scope.search.property_info="";
        $scope.search.time="";
        $scope.search.fromTime="";
        $scope.search.toTime="";
        $scope.showTime=false;
        $('.select2-selection__rendered').html("--Chọn--");
    }
    //load user name
    $scope.loadUserName=function (userId) {
        var user=$filter('filter')($scope.users,{userId:userId},true);
        if(user!=null && user!='undefined' && user!="") return (user[0].family_name+" "+user[0].first_name);
        return userId;
    }
    $scope.loadContractTemplateName=function (template_id) {
        var temp=$filter('filter')($scope.contractTemplates,{code_template:template_id},true);
        if(temp!=null && temp !='undefined' && temp!='') return (temp[0].name);
        return template_id;
    }


    //get name of template
    $scope.getContractName=function (index) {
        switch (index){
            case 0:
                if($scope.listChoKy.items.length>0){
                    for(var i=0;i<$scope.listChoKy.items.length;i++){
                        var notaryId=$scope.listChoKy.items[i].notary_id;
                        if(notaryId!=null && notaryId!="undefined"&& notaryId!=""){
                            $scope.listChoKy.items[i].notary_id=$scope.loadUserName(notaryId);
                        }
                        var template = $scope.listChoKy.items[i].contract_template_id;
                        if(template!=null && template!="undefined"&& template!=""){
                            $scope.listChoKy.items[i].contract_template_id =$scope.loadContractTemplateName(template);
                        }
                    }
                }
                break;
            case 1:
                if($scope.listDaKy.items.length>0){
                    for(var i=0;i<$scope.listDaKy.items.length;i++){
                        //load name of cong chung vien
                        var notaryId=$scope.listDaKy.items[i].notary_id;
                        if(notaryId!=null && notaryId!="undefined"&& notaryId!=""){
                            $scope.listDaKy.items[i].notary_id=$scope.loadUserName(notaryId);
                        }
                        var template = $scope.listDaKy.items[i].contract_template_id;
                        if(template!=null && template!="undefined"&& template!=""){
                            $scope.listDaKy.items[i].contract_template_id =$scope.loadContractTemplateName(template);
                        }
                    }
                }
                break;
            case 2:
                if($scope.listTraVe.items.length>0){
                    for(var i=0;i<$scope.listTraVe.items.length;i++){
                        var notaryId=$scope.listTraVe.items[i].notary_id;
                        if(notaryId!=null && notaryId!="undefined"&& notaryId!=""){
                            $scope.listTraVe.items[i].notary_id=$scope.loadUserName(notaryId);
                        }
                        var template =$scope.listTraVe.items[i].contract_template_id;
                        if(template!=null && template!="undefined"&& template!=""){
                            $scope.listTraVe.items[i].contract_template_id =$scope.loadContractTemplateName(template);
                        }
                    }
                }
                break;
            case 3:
                if($scope.listChinhSua.items.length>0){
                    for(var i=0;i<$scope.listChinhSua.items.length;i++){
                        var notaryId=$scope.listChinhSua.items[i].notary_id;
                        if(notaryId!=null && notaryId!="undefined"&& notaryId!=""){
                            $scope.listChinhSua.items[i].notary_id=$scope.loadUserName(notaryId);
                        }
                        var template =$scope.listChinhSua.items[i].contract_template_id;
                        if(template!=null && template!="undefined"&& template!=""){
                            template=template[0];
                            $scope.listChinhSua.items[i].contract_template_id =$scope.loadContractTemplateName(template);
                        }
                    }
                }
                break;

            default:
                break;
        }

    }

    /*format replace \n to <br>*/
    $scope.replaceNewLine=function (index) {
        switch (index){
            case 0:
                if($scope.listChoKy.items.length>0){
                    for(var i=0;i<$scope.listChoKy.items.length;i++){
                        var item=$scope.listChoKy.items[i];
                        if(item.relation_object_a!=null && item.relation_object_a!="undefined"&& item.relation_object_a!=""){
                            $scope.listChoKy.items[i].relation_object_a=$scope.listChoKy.items[i].relation_object_a.replace(/\\n/g,"<br>");
                        }
                        if(item.relation_object_b!=null && item.relation_object_b!="undefined"&& item.relation_object_b!=""){
                            $scope.listChoKy.items[i].relation_object_b="<p style='color:springgreen;'>Thông tin tài sản:</p>"+$scope.listChoKy.items[i].relation_object_b.replace(/\\n/g,"<br>");
                        }
                        if(item.summary!=null && item.summary!="undefined"&& item.summary!=""){
                            $scope.listChoKy.items[i].summary="<p style='color:springgreen;'>Nội dung hợp đồng:</p>"+$scope.listChoKy.items[i].summary.replace(/\\n/g,"<br>");
                            if(item.relation_object_b!=null && item.relation_object_b!="undefined"&& item.relation_object_b!=""){
                                $scope.listChoKy.items[i].summary= $scope.listChoKy.items[i].summary+$scope.listChoKy.items[i].relation_object_b;
                            }
                        }else{
                             if(item.relation_object_b!=null && item.relation_object_b!="undefined"&& item.relation_object_b!=""){
                                 $scope.listChoKy.items[i].summary= $scope.listChoKy.items[i].relation_object_b;
                             }else{
                                 $scope.listChoKy.items[i].summary= "";
                             }

                        }
                    }
                }
                break;
            case 1:
                if($scope.listDaKy.items.length>0){
                    for(var i=0;i<$scope.listDaKy.items.length;i++){
                        var item=$scope.listDaKy.items[i];
                        if(item.relation_object_a!=null && item.relation_object_a!="undefined"&& item.relation_object_a!=""){
                            $scope.listDaKy.items[i].relation_object_a=$scope.listDaKy.items[i].relation_object_a.replace(/\\n/g,"<br>");
                        }
                        if(item.relation_object_b!=null && item.relation_object_b!="undefined"&& item.relation_object_b!=""){
                            $scope.listDaKy.items[i].relation_object_b="<p style='color:springgreen;'>Thông tin tài sản:</p>"+$scope.listDaKy.items[i].relation_object_b.replace(/\\n/g,"<br>");
                        }
                        if(item.summary!=null && item.summary!="undefined"&& item.summary!=""){
                            $scope.listDaKy.items[i].summary="<p style='color:springgreen;'>Nội dung hợp đồng:</p>"+$scope.listDaKy.items[i].summary.replace(/\\n/g,"<br>");
                            if(item.relation_object_b!=null && item.relation_object_b!="undefined"&& item.relation_object_b!=""){
                                $scope.listDaKy.items[i].summary= $scope.listDaKy.items[i].summary+$scope.listDaKy.items[i].relation_object_b;
                            }
                        }else{
                            if(item.relation_object_b!=null && item.relation_object_b!="undefined"&& item.relation_object_b!=""){
                                $scope.listDaKy.items[i].summary= $scope.listDaKy.items[i].relation_object_b;
                            }else{
                                $scope.listDaKy.items[i].summary= "";
                            }

                        }


                    }
                }
                break;
            case 2:
                if($scope.listTraVe.items.length>0){
                    for(var i=0;i<$scope.listTraVe.items.length;i++){
                        var item=$scope.listTraVe.items[i];
                        if(item.relation_object_a!=null && item.relation_object_a!="undefined"&& item.relation_object_a!=""){
                            $scope.listTraVe.items[i].relation_object_a=$scope.listTraVe.items[i].relation_object_a.replace(/\\n/g,"<br>");
                        }
                        if(item.relation_object_b!=null && item.relation_object_b!="undefined"&& item.relation_object_b!=""){
                            $scope.listTraVe.items[i].relation_object_b="<p style='color:springgreen;'>Thông tin tài sản:</p>"+$scope.listTraVe.items[i].relation_object_b.replace(/\\n/g,"<br>");
                        }
                        if(item.summary!=null && item.summary!="undefined"&& item.summary!=""){
                            $scope.listTraVe.items[i].summary="<p style='color:springgreen;'>Nội dung hợp đồng:</p>"+$scope.listTraVe.items[i].summary.replace(/\\n/g,"<br>");
                            if(item.relation_object_b!=null && item.relation_object_b!="undefined"&& item.relation_object_b!=""){
                                $scope.listTraVe.items[i].summary= $scope.listTraVe.items[i].summary+$scope.listTraVe.items[i].relation_object_b;
                            }
                        }else{
                            if(item.relation_object_b!=null && item.relation_object_b!="undefined"&& item.relation_object_b!=""){
                                $scope.listTraVe.items[i].summary= $scope.listTraVe.items[i].relation_object_b;
                            }else{
                                $scope.listTraVe.items[i].summary= "";
                            }

                        }

                    }
                }
                break;
            case 3:
                if($scope.listChinhSua.items.length>0){
                    for(var i=0;i<$scope.listChinhSua.items.length;i++){
                        var item=$scope.listChinhSua.items[i];
                        if(item.relation_object_a!=null && item.relation_object_a!="undefined"&& item.relation_object_a!=""){
                            $scope.listChinhSua.items[i].relation_object_a=$scope.listChinhSua.items[i].relation_object_a.replace(/\\n/g,"<br>");
                        }
                        if(item.relation_object_b!=null && item.relation_object_b!="undefined"&& item.relation_object_b!=""){
                            $scope.listChinhSua.items[i].relation_object_b="<p style='color:springgreen;'>Thông tin tài sản:</p>"+$scope.listChinhSua.items[i].relation_object_b.replace(/\\n/g,"<br>");
                        }
                        if(item.summary!=null && item.summary!="undefined"&& item.summary!=""){
                            $scope.listChinhSua.items[i].summary="<p style='color:springgreen;'>Nội dung hợp đồng:</p>"+$scope.listChinhSua.items[i].summary.replace(/\\n/g,"<br>");
                            if(item.relation_object_b!=null && item.relation_object_b!="undefined"&& item.relation_object_b!=""){
                                $scope.listChinhSua.items[i].summary= $scope.listChinhSua.items[i].summary+$scope.listChinhSua.items[i].relation_object_b;
                            }
                        }else{
                            if(item.relation_object_b!=null && item.relation_object_b!="undefined"&& item.relation_object_b!=""){
                                $scope.listChinhSua.items[i].summary= $scope.listChinhSua.items[i].relation_object_b;
                            }else{
                                $scope.listChinhSua.items[i].summary= "";
                            }

                        }

                    }
                }
                break;
            case 5:
                if($scope.listDongDau.items.length>0){
                    for(var i=0;i<$scope.listDongDau.items.length;i++){
                        var item=$scope.listDongDau.items[i];
                        if(item.relation_object!=null && item.relation_object!="undefined"&& item.relation_object!=""){
                            $scope.listDongDau.items[i].relation_object=$scope.listDongDau.items[i].relation_object.replace(/\\n/g,"<br>");
                        }

                        if(item.transaction_content!=null && item.transaction_content!="undefined"&& item.transaction_content!="" && item.transaction_content!='null'){
                            $scope.listDongDau.items[i].transaction_content="<p style='color:springgreen;'>Nội dung hợp đồng:</p>"+$scope.listDongDau.items[i].transaction_content.replace(/\\n/g,"<br>");
                        }
                        if(item.property_info!=null && item.property_info!="undefined"&& item.property_info!=""){
                            $scope.listDongDau.items[i].property_info="<p style='color:springgreen;'>Thông tin tài sản:<br></p>"+$scope.listDongDau.items[i].property_info.replace(/\\n/g,"<br>");
                            if(item.transaction_content!=null && item.transaction_content!="undefined"&& item.transaction_content!="" && item.transaction_content!='null'){
                                $scope.listDongDau.items[i].transaction_content= $scope.listDongDau.items[i].transaction_content+ $scope.listDongDau.items[i].property_info;
                            }else{
                                $scope.listDongDau.items[i].transaction_content=  $scope.listDongDau.items[i].property_info;
                            }

                        }

                    }
                }
                break;
            default:
                //load tat
                break;
        }
    }


    /*load all contract*/
    $scope.loadCountContract=function () {
        var search=JSON.stringify($scope.search);
        $http.get(url+"/contract/countTemporaryBySearch", {params: {search:search,type:0}})
            .then(function (response) {
                $scope.listChoKy.rowCount=response.data;
                $scope.listChoKy.pageCount=getPageCountContract($scope.listChoKy);
                $scope.listChoKy.pageList=getPageList($scope.listChoKy);

            });
        $http.get(url+"/contract/countTemporaryBySearch", {params: {search:search,type:1}})
            .then(function (response) {
                $scope.listDaKy.rowCount=response.data;
                $scope.listDaKy.pageCount=getPageCountContract($scope.listDaKy);
                $scope.listDaKy.pageList=getPageList($scope.listDaKy);

            });
        $http.get(url+"/contract/countTemporaryBySearch", {params: {search:search,type:3}})
            .then(function (response) {
                $scope.listChinhSua.rowCount=response.data;
                $scope.listChinhSua.pageCount=getPageCountContract($scope.listChinhSua);
                $scope.listChinhSua.pageList=getPageList($scope.listChinhSua);

            });
        $http.get(url+"/contract/countTemporaryBySearch", {params: {search:search,type:2}})
            .then(function (response) {
                $scope.listTraVe.rowCount=response.data;
                $scope.listTraVe.pageCount=getPageCountContract($scope.listTraVe);
                $scope.listTraVe.pageList=getPageList($scope.listTraVe);

            });
        $http.get(url+"/transaction/countTransBySearch", {params: {search:search,syn_status:2}})
            .then(function (response) {
                $scope.listDongDau.rowCount=response.data;
                $scope.listDongDau.pageCount=getPageCountContract($scope.listDongDau);
                $scope.listDongDau.pageList=getPageList($scope.listDongDau);
            });
    }
    /*reload contract list*/
    $scope.loadListContract=function () {
        var search=JSON.stringify($scope.search);
        $http.get(url+"/contract/temporarysBySearch", {params: {search:search,type:0,offset:0,number:$scope.listChoKy.numberPerPage}})
            .then(function (response) {
                $scope.listChoKy.items=response.data;
                $scope.getContractName(0);
                $scope.replaceNewLine(0);
                $scope.tooltip();
            });
        $http.get(url+"/contract/temporarysBySearch", {params: {search:search,type:1,offset:0,number:$scope.listDaKy.numberPerPage}})
            .then(function (response) {
                $scope.listDaKy.items=response.data;
                $scope.getContractName(1);
                $scope.replaceNewLine(1);
                $scope.tooltip();
            });
        $http.get(url+"/contract/temporarysBySearch", {params: {search:search,type:3,offset:0,number:$scope.listChinhSua.numberPerPage}})
            .then(function (response) {
                $scope.listChinhSua.items=response.data;
                $scope.getContractName(3);
                $scope.replaceNewLine(3);
                $scope.tooltip();
            });
        $http.get(url+"/contract/temporarysBySearch", {params: {search:search,type:2,offset:0,number:$scope.listTraVe.numberPerPage}})
            .then(function (response) {
                $scope.listTraVe.items=response.data;
                $scope.getContractName(2);
                $scope.replaceNewLine(2);
                $scope.tooltip();
            });
        $http.get(url+"/transaction/transactionsBySearch", {params: {search:search,syn_status:2,offset:0,number:$scope.listDongDau.numberPerPage}})
            .then(function (response) {
                $scope.listDongDau.items=response.data;
                $scope.getContractName(5);
                $scope.replaceNewLine(5);
                $scope.tooltip();
            });
    }

    /*load tooltip*/
    $scope.tooltip=function () {
        var defer = $q.defer();
        $timeout(function(){
            $("[data-toggle=popover]").popover();
            defer.resolve();
        },1000);
    }

    /*load tong trang va danh sach trang*/
    function getPageCountContract(pageResult) {
        var pageCount=Math.ceil(pageResult.rowCount/pageResult.numberPerPage);
        return pageCount;
    }

    function getPageList(pagingResult) {
        var pages=[];
        var from = pagingResult.pageNumber  - 3;
        var to = pagingResult.pageNumber + 5;
        if (from < 0) {
            to -= from;
            from = 1;
        }

        if (from < 1) {
            from = 1;
        }

        if (to > pagingResult.pageCount) {
            to = pagingResult.pageCount;
        }

        for (var i=from; i<=to; i++ ) {
            pages.push(i);
        }
        return pages;
    }

    /*check todate and from date*/
    $scope.checkDate=function () {
        if($scope.search.fromTime !=null && $scope.search.fromTime.length>0){
            if($scope.search.fromTime.length!=10){
                $scope.fromDateError="Trường theo định dạng dd/MM/yyyy";
                return false;
            }else{
                if(moment($scope.search.fromTime,"DD/MM/YYYY",true).isValid()){
                    var dateFrom=$scope.formatDate($scope.search.fromTime);
                    if(dateFrom> new Date()){
                        $scope.fromDateError="Không thể lớn hơn ngày hiện tại!";
                        return false;
                    }
                    //kiem tra luon toTime so voi fromTime neu be hon thi bug
                    if($scope.search.toTime !=null && $scope.search.toTime.length>0){
                        if($scope.search.toTime.length!=10){
                            $scope.toDateError="Trường theo định dạng dd/MM/yyyy";
                            return false;
                        }else{
                            if(moment($scope.search.toTime,"DD/MM/YYYY",true).isValid()){
                                var dateTo=$scope.formatDate($scope.search.toTime);
                                if(dateTo> new Date()){
                                    $scope.toDateError="Không thể lớn hơn ngày hiện tại!";
                                }else{
                                    if(dateFrom>dateTo){
                                        $scope.fromDateError='"Từ ngày" không thể lớn hơn "Đến ngày"!';
                                        return false;
                                    }
                                    $scope.fromDateError="";
                                    $scope.fromDateError='';
                                    return true;
                                }
                            }else{
                                $scope.toDateError="Trường theo định dạng dd/MM/yyyy";
                                return false;
                            }
                        }
                    }

                }else{
                    $scope.fromDateError="Trường theo định dạng dd/MM/yyyy";
                    return false;
                }
            }
        }
        if($scope.search.toTime !=null && $scope.search.toTime.length>0){
            if($scope.search.toTime.length!=10){
                $scope.toDateError="Trường theo định dạng dd/MM/yyyy";
                return false;
            }else{
                if(moment($scope.search.toTime,"DD/MM/YYYY",true).isValid()){
                    var dateFrom=$scope.formatDate($scope.search.toTime);
                    if(dateFrom> new Date()){
                        $scope.toDateError="Không thể lớn hơn ngày hiện tại!";
                    }
                }else{
                    $scope.toDateError="Trường theo định dạng dd/MM/yyyy";
                    return false;
                }
            }
        }
        $scope.fromDateError="";
        $scope.fromDateError='';
        return true;
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
    // Expect input as d/m/y
    function isValidDate(s) {
        var bits = s.split('/');
        var d = new Date(bits[2], bits[1] - 1, bits[0]);
        return d && (d.getMonth() + 1) == bits[1];
    }





}]);

myApp.directive('myEnter', function () {
    return function (scope, element, attrs) {
        element.bind("keydown keypress", function (event) {
            if(event.which === 13) {
                scope.$apply(function (){
                    scope.$eval(attrs.myEnter);
                });

                event.preventDefault();
            }
        });
    };
});

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