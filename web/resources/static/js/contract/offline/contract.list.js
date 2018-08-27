/**
 * Created by TienManh on 5/23/2017.
 */

var myApp = angular.module('osp', ['ngSanitize']);

myApp.controller('contractListController',['$scope','$http','$filter','$timeout','$q',function ($scope,$http,$filter,$timeout,$q) {
    var now=new Date();
    var endDate=("0" + now.getDate()).slice(-2) + '/' + ("0" + (now.getMonth() + 1)).slice(-2) + '/' +  now.getFullYear();
    // var url="http://localhost:8082/api";

    $scope.listContract={items:"",rowCount:0,numberPerPage:20,pageNumber:1,pageList:[],pageCount:0};
    $scope.listNotSynch={items:"",rowCount:0,numberPerPage:20,pageNumber:1,pageList:[],pageCount:0};
    $scope.search={basic:0,search_basic:"",contract_kind:"",contract_template:"",contract_number:"",relation_object:"",property_info:"",time:"",fromTime:"",toTime:""};
    $scope.tab=1;//1: khi click listContract, 2 khi click listNotSynch
    $scope.showBasic=true;
    var search= JSON.stringify($scope.search);

    /*for listContract*/

    // $http.get(url+"/transaction/countTrans", {params: {stringFilter:"limit 0,1"}})
    //     .then(function (response) {
    //         $scope.listContract.rowCount=response.data;
    //         $scope.listContract.pageCount=getPageCountContract($scope.listContract);
    //         $scope.listContract.pageList=getPageList($scope.listContract);
    //     });
    //
    // $http.get(url+"/transaction/transactionsByFilter", {params: {stringFilter:"ORDER BY entry_date_time desc limit 0,"+$scope.listContract.numberPerPage}})
    //     .then(function (response) {
    //         $scope.listContract.items=response.data;
    //     });

    $http.get(url+"/transaction/countTransBySearch", {params: {search:search,syn_status:2}})
        .then(function (response) {
            $scope.listContract.rowCount=response.data;
            $scope.listContract.pageCount=getPageCountContract($scope.listContract);
            $scope.listContract.pageList=getPageList($scope.listContract);
        });
    $http.get(url+"/transaction/transactionsBySearch", {params: {search:search,syn_status:2,offset:0,number:$scope.listContract.numberPerPage}})
        .then(function (response) {
            $scope.listContract.items=response.data;
            $scope.replaceNewLine(0);
            $scope.tooltip();
        });

    $scope.whenClickTabContract=function () {
        $scope.tab=1;
         search= JSON.stringify($scope.search);

        $http.get(url+"/transaction/transactionsBySearch", {params: {search:search,syn_status:2,offset:$scope.listContract.numberPerPage*($scope.listContract.pageNumber-1),number:$scope.listContract.numberPerPage}})
            .then(function (response) {
                $scope.listContract.items=response.data;
                $scope.replaceNewLine(0);
                $scope.tooltip();
            });
    }
    $scope.loadPageContract=function (index) {
        if(index>=1){
            $scope.listContract.pageNumber=index;
             search= JSON.stringify($scope.search);

            $http.get(url+"/transaction/countTransBySearch", {params: {search:search,syn_status:2}})
                .then(function (response) {
                    $scope.listContract.rowCount=response.data;
                    $scope.listContract.pageCount=getPageCountContract($scope.listContract);
                    $scope.listContract.pageList=getPageList($scope.listContract);
                });
            $http.get(url+"/transaction/transactionsBySearch", {params: {search:search,syn_status:2,offset:$scope.listContract.numberPerPage*($scope.listContract.pageNumber-1),number:$scope.listContract.numberPerPage}})
                .then(function (response) {
                    $scope.listContract.items=response.data;
                    $scope.replaceNewLine(0);
                    $scope.tooltip();
                });
        }

    }

    /*for listContract not synchronize*/


    $http.get(url+"/transaction/countTransBySearch", {params: {search:search,syn_status:0}})
        .then(function (response) {
            $scope.listNotSynch.rowCount=response.data;
            $scope.listNotSynch.pageCount=getPageCountContract($scope.listNotSynch);
            $scope.listNotSynch.pageList=getPageList($scope.listNotSynch);
        });


    $scope.whenClickTabNotSynch=function () {
        $scope.tab=2;
         search= JSON.stringify($scope.search);

        $http.get(url+"/transaction/transactionsBySearch", {params: {search:search,syn_status:0,offset:$scope.listNotSynch.numberPerPage*($scope.listNotSynch.pageNumber-1),number:$scope.listNotSynch.numberPerPage}})
            .then(function (response) {
                $scope.listNotSynch.items=response.data;
                $scope.replaceNewLine(1);
                $scope.tooltip();
            });
    }

    $scope.loadPageNotSynch=function (index) {
        if(index>=1){
            $scope.listNotSynch.pageNumber=index;
             search= JSON.stringify($scope.search);

            $http.get(url+"/transaction/countTransBySearch", {params: {search:search,syn_status:0}})
                .then(function (response) {
                    $scope.listNotSynch.rowCount=response.data;
                    $scope.listNotSynch.pageCount=getPageCountContract($scope.listNotSynch);
                    $scope.listNotSynch.pageList=getPageList($scope.listNotSynch);
                });
            $http.get(url+"/transaction/transactionsBySearch", {params: {search:search,syn_status:0,offset:$scope.listNotSynch.numberPerPage*($scope.listNotSynch.pageNumber-1),number:$scope.listNotSynch.numberPerPage}})
                .then(function (response) {
                    $scope.listNotSynch.items=response.data;
                    $scope.replaceNewLine(1);
                    $scope.tooltip();
                });
        }

    }
    /*load for search*/

    $http.get(url+"/contract/list-contract-kind")
        .then(function(response) {
            $scope.contractKinds=response.data;
        });

    $scope.changeContractKind=function(code){
        $http.get(url+"/contract/list-contract-template-by-contract-kind-code", {params:{code:code}})
            .then(function(response) {
                $scope.contractTemplates=response.data;
            });
    }

    $scope.showTime=false;
    $scope.changTime=function (time) {
        if(time==5){
            $scope.showTime=true;
        }else{
            $scope.showTime=false;
        }
    }

    $scope.download=function () {
         window.open(url+"/transaction/download/contract-by-search?search="+search, '_blank');
    }

    $scope.clearCondition=function () {
        $scope.search.search_basic="";
        $scope.search.contract_kind="";
        $scope.search.contract_template="";
        $scope.search.contract_number="";
        $scope.search.relation_object="";
        $scope.search.property_info="";
        $scope.search.time="";
        $scope.search.fromTime="";
        $scope.search.toTime="";
        $scope.showTime=false;
        $(".select2-selection__rendered").html('--Chọn--');
    }

    //search basic
    $scope.searchBasic=function () {

        $scope.search.basic=0;
        $scope.listContract.pageNumber=1;
        $scope.listNotSynch.pageNumber=1;
         search= JSON.stringify($scope.search);

        if($scope.tab==1){//load cho contract
            $http.get(url+"/transaction/countTransBySearch", {params: {search:search,syn_status:2}})
                .then(function (response) {
                    $scope.listContract.rowCount=response.data;
                    $scope.listContract.pageCount=getPageCountContract($scope.listContract);
                    $scope.listContract.pageList=getPageList($scope.listContract);
                });
            $http.get(url+"/transaction/transactionsBySearch", {params: {search:search,syn_status:2,offset:0,number:$scope.listContract.numberPerPage}})
                .then(function (response) {
                    $scope.listContract.items=response.data;
                    $scope.replaceNewLine(0);
                    $scope.tooltip();
                });
            $http.get(url+"/transaction/countTransBySearch", {params: {search:search,syn_status:0}})
                .then(function (response) {
                    $scope.listNotSynch.rowCount=response.data;
                    $scope.listNotSynch.pageCount=getPageCountContract($scope.listNotSynch);
                    $scope.listNotSynch.pageList=getPageList($scope.listNotSynch);
                });
        }else if($scope.tab==2){//load for list not synch
            $http.get(url+"/transaction/countTransBySearch", {params: {search:search,syn_status:2}})
                .then(function (response) {
                    $scope.listContract.rowCount=response.data;
                    $scope.listContract.pageCount=getPageCountContract($scope.listContract);
                    $scope.listContract.pageList=getPageList($scope.listContract);
                });

            $http.get(url+"/transaction/countTransBySearch", {params: {search:search,syn_status:0}})
                .then(function (response) {
                    $scope.listNotSynch.rowCount=response.data;
                    $scope.listNotSynch.pageCount=getPageCountContract($scope.listNotSynch);
                    $scope.listNotSynch.pageList=getPageList($scope.listNotSynch);
                });
            $http.get(url+"/transaction/transactionsBySearch", {params: {search:search,syn_status:0,offset:0,number:$scope.listNotSynch.numberPerPage}})
                .then(function (response) {
                    $scope.listNotSynch.items=response.data;
                    $scope.replaceNewLine(1);
                    $scope.tooltip();
                });
        }


    }

    //search advance
    $scope.searchAdvance=function () {
        if($scope.checkDate()){
            $scope.search.basic=1;
            $scope.listContract.pageNumber=1;
            $scope.listNotSynch.pageNumber=1;
             search= JSON.stringify($scope.search);

            if($scope.tab==1){//load cho contract
                $http.get(url+"/transaction/countTransBySearch", {params: {search:search,syn_status:2}})
                    .then(function (response) {
                        $scope.listContract.rowCount=response.data;
                        $scope.listContract.pageCount=getPageCountContract($scope.listContract);
                        $scope.listContract.pageList=getPageList($scope.listContract);
                    });
                $http.get(url+"/transaction/transactionsBySearch", {params: {search:search,syn_status:2,offset:0,number:$scope.listContract.numberPerPage}})
                    .then(function (response) {
                        $scope.listContract.items=response.data;
                        $scope.replaceNewLine(0);
                        $scope.tooltip();
                    });
                $http.get(url+"/transaction/countTransBySearch", {params: {search:search,syn_status:0}})
                    .then(function (response) {
                        $scope.listNotSynch.rowCount=response.data;
                        $scope.listNotSynch.pageCount=getPageCountContract($scope.listNotSynch);
                        $scope.listNotSynch.pageList=getPageList($scope.listNotSynch);
                    });

            }else if($scope.tab==2){//load for list not synch
                $http.get(url+"/transaction/countTransBySearch", {params: {search:search,syn_status:2}})
                    .then(function (response) {
                        $scope.listContract.rowCount=response.data;
                        $scope.listContract.pageCount=getPageCountContract($scope.listContract);
                        $scope.listContract.pageList=getPageList($scope.listContract);
                    });

                $http.get(url+"/transaction/countTransBySearch", {params: {search:search,syn_status:0}})
                    .then(function (response) {
                        $scope.listNotSynch.rowCount=response.data;
                        $scope.listNotSynch.pageCount=getPageCountContract($scope.listNotSynch);
                        $scope.listNotSynch.pageList=getPageList($scope.listNotSynch);
                    });
                $http.get(url+"/transaction/transactionsBySearch", {params: {search:search,syn_status:0,offset:0,number:$scope.listNotSynch.numberPerPage}})
                    .then(function (response) {
                        $scope.listNotSynch.items=response.data;
                        $scope.replaceNewLine(1);
                        $scope.tooltip();
                    });
            }
        }else{
            $("#errorFormatDate").modal('show');
        }


    }

    /*format replace \n to <br>*/
    $scope.replaceNewLine=function (index) {
        switch (index){
            case 0:
                if($scope.listContract.items.length>0){
                    for(var i=0;i<$scope.listContract.items.length;i++){
                        var item=$scope.listContract.items[i];
                        if(item.relation_object!=null && item.relation_object!="undefined"&& item.relation_object!=""){
                            $scope.listContract.items[i].relation_object=$scope.listContract.items[i].relation_object.replace(/\\n/g,"<br>");
                        }
                        if(item.transaction_content!=null && item.transaction_content!="undefined"&& item.transaction_content!=""){
                            $scope.listContract.items[i].transaction_content="<p style='color:springgreen;'>Nội dung hợp đồng:</p>"+$scope.listContract.items[i].transaction_content.replace(/\\n/g,"<br>");
                        }
                        if(item.property_info!=null && item.property_info!="undefined"&& item.property_info!=""){
                            $scope.listContract.items[i].property_info="<p style='color:springgreen;'>Thông tin tài sản:<br></p>"+$scope.listContract.items[i].property_info.replace(/\\n/g,"<br>");
                            if(item.transaction_content!=null && item.transaction_content!="undefined"&& item.transaction_content!="" && item.transaction_content!='null'){
                                $scope.listContract.items[i].transaction_content= $scope.listContract.items[i].transaction_content+ $scope.listContract.items[i].property_info;
                            }else{
                                $scope.listContract.items[i].transaction_content=  $scope.listContract.items[i].property_info;
                            }

                        }

                    }
                }
                break;
            case 1:
                if($scope.listNotSynch.items.length>0){
                    for(var i=0;i<$scope.listNotSynch.items.length;i++){
                        var item=$scope.listNotSynch.items[i];
                        if(item.relation_object!=null && item.relation_object!="undefined"&& item.relation_object!=""){
                            $scope.listNotSynch.items[i].relation_object=$scope.listNotSynch.items[i].relation_object.replace(/\\n/g,"<br>");
                        }

                        if(item.transaction_content!=null && item.transaction_content!="undefined"&& item.transaction_content!=""){
                            $scope.listNotSynch.items[i].transaction_content="<p style='color:springgreen;'>Nội dung hợp đồng:</p>"+$scope.listNotSynch.items[i].transaction_content.replace(/\\n/g,"<br>");
                        }
                        if(item.property_info!=null && item.property_info!="undefined"&& item.property_info!=""){
                            $scope.listNotSynch.items[i].property_info="<p style='color:springgreen;'>Thông tin tài sản:<br></p>"+$scope.listNotSynch.items[i].property_info.replace(/\\n/g,"<br>");
                            if(item.transaction_content!=null && item.transaction_content!="undefined"&& item.transaction_content!="" && item.transaction_content!='null'){
                                $scope.listNotSynch.items[i].transaction_content= $scope.listNotSynch.items[i].transaction_content+ $scope.listNotSynch.items[i].property_info;
                            }else{
                                $scope.listNotSynch.items[i].transaction_content=  $scope.listNotSynch.items[i].property_info;
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