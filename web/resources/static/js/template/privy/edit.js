/**
 * Created by TienManh on 8/8/2017.
 */
var myApp = angular.module('osp', []);

myApp.controller('templateController',['$scope','$http','$window','$sce',function ($scope,$http,$window,$sce) {
    $scope.item={};

    $http.get(url+"/ContractTemplate/privy-property-template-by-id",{params:{id:id}})
        .then(function (response) {
            $scope.item=response.data;
            console.log($scope.item);
        });

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
