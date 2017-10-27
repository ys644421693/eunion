define(['./module', 'jquery'], function (commonDirective) {
    'use strict';

    commonDirective.directive('pagingManage', ['$compile', '$templateCache', function ($compile, $templateCache) {
        function getTemplate($scope) {
            var template = "/page/component/basePaging.html";
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
                $scope.dataTable={};

                //向表格传递数据
                $scope.$emit("pageData", { divName: "Self", description: "向父传播数据" });
            }
        }
    }]);


});
