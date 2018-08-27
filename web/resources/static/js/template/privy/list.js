/**
 * Created by TienManh on 8/8/2017.
 */

var myApp = angular.module('osp', ['ngSanitize']);

myApp.controller('privyController',['$scope','$http','$filter','$timeout','$q',function ($scope,$http,$filter,$timeout,$q) {

    $scope.listPrivy={items:"",rowCount:0,numberPerPage:25,pageNumber:1,pageList:[],pageCount:0};
    $scope.search={contract_kind:"",contract_template:"",contract_number:"",relation_object:"",property_info:"",time:"",fromTime:"",toTime:""};
    $scope.tab=1;//1: khi click listContract, 2 khi click listPrivy
    $scope.showBasic=true;
    var search= JSON.stringify($scope.search);

    // $http.get(url+"/ContractTemplate/privy-property-template-get-by-type", {params: {type:0}})
    //     .then(function (response) {
    //         $scope.listPrivy.items=response.data;
    //         $scope.listPrivy.rowCount=$scope.listPrivy.items.length;
    //         $scope.listPrivy.pageCount=getPageCountContract($scope.listPrivy);
    //         $scope.listPrivy.pageList=getPageList($scope.listPrivy);
    //     });

    /*load for search*/

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