define(['./module', 'jquery'], function (commonDirective) {
    'use strict';

    commonDirective.directive('tableManage', ['$compile', '$templateCache', function ($compile, $templateCache) {
        function getTemplate($scope) {
            var template = "/page/component/baseTable.html";
            console.log($scope);
            /*switch ($scope.type){
                case 1:
                case 2:
                case 3:
            }*/
            return template;
        }

        return {
            restrict: "AE",
            replace: true,
            scope: false,
            template: '<ng-include src="getTemplateUrl()"/>',
            controller: function ($scope, $element, $http) {
                $scope.getTemplateUrl = function () {
                   return getTemplate($scope);
                };
            },
            link: function ($scope, $element, $attr) {

                if (!$scope.setTable){
                    $scope.setTable = {};
                    $scope.setTable.tableClass = "table";
                }
                if(!$scope.setTable.tableClass){
                    $scope.setTable.tableClass = "table";
                }

                $scope.$on("pageData", function (event, data) {
                    console.log("截获外部传入数据",data);
                    $scope.dataTable = data;
                });

                $scope.$on("setTable", function (event, data) {
                    console.log("截获设置table传入数据",data);
                    $scope.setTable = data;
                });
            }
        }
    }]);


});
