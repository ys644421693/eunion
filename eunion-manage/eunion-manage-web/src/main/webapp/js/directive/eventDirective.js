define(['./module', 'jquery'], function (commonDirective) {
    'use strict';

    commonDirective.directive('eventManage', ['$compile','$templateCache', function ($compile,$templateCache) {

        function getTemplate(attr) {
            var template = "<button type=\"button\" class=\"btn btn-primary btn-sm\" ng-click=\"setData()\"><i class=\"fa fa-cog fa-fw\"></i>设置</button>"
            console.log(attr);
            return template;
        }

        return {
            restrict: "AE",
            replace: true,
            scope: false,
            compile:function(element, attr){

                $templateCache.put('treeTemplate',getTemplate(attr));
                return {
                    pre: function(scope, iElement, iAttrs, controller) {
                        scope.treeTemplate='treeTemplate';
                        scope.dept=attr.dept;
                    },
                    post: function(scope, iElement, iAttrs, controller) {
                    }
                }
            },
            link: function ($scope, $element, $attr) {

            }
        }
    }]);



});
