define(['./module', 'jquery'], function (commonDirective) {
    'use strict';

    commonDirective.directive('tableManage', ['$compile', '$templateCache', function ($compile, $templateCache) {
        function getTemplate($scope) {
            var template = "/page/component/baseButton.html";
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
            scope: {type:"="},
            template: '<ng-include src="getTemplateUrl()"/>',
            controller: function ($scope, $element, $http) {
                $scope.getTemplateUrl = function () {
                   return getTemplate($scope);
                };
            },
            link: function ($scope, $element, $attr) {

            }
        }
    }]);


});
