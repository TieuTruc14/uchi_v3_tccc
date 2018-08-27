/**
 * Created by TienManh on 7/24/2017.
 */
var myApp = angular.module('osp', []);

myApp.controller('contractAddController',['$scope',function ($scope) {
    $scope.contract={tcid:"",type:"",contract_template_id:"",contract_number:"",contract_value:"",relation_object_a:"",relation_object_b:"",relation_object_c:"",notary_id:"",drafter_id:"",received_date:"",notary_date:"",user_require_contract:"",property_type:"",property_info:"",owner_info:"",other_info:"",land_certificate:"",land_issue_place:"",land_issue_date:"",land_map_number:"",land_number:"",land_address:"",land_area:"",land_public_area:"",land_private_area:"",land_use_purpose:"",land_use_period:"",land_use_origin:"",land_associate_property:"",land_street:"",land_district:"",land_province:"",land_full_of_area:"",car_license_number:"",car_regist_number:"",car_issue_place:"",car_issue_date:"",car_frame_number:"",car_machine_number:"",number_copy_of_contract:"",number_of_page:"",cost_tt91:"",cost_draft:"",cost_notary_outsite:"",cost_other_determine:"",cost_total:"",notary_place_flag:"",notary_place:"",bank_id:"",bank_service_fee:"",crediter_name:"",file_name:"",file_path:"",original_store_place:"",note:"",summary:"",entry_user_id:"",entry_user_name:"",entry_date_time:"",update_user_id:"",update_user_name:"",update_date_time:"",jsonstring:"",kindhtml:"",json_property:"",json_person:"",bank_code:""};

}]);

//khai bao directive dung` gen html
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

