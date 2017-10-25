define(['./module', 'jquery'], function (controllers) {
    'use strict';
    controllers.controller('baseButtonController', function ($scope, operaByFunctionName) {
        $scope.ts = function () {
            alert("123456ok");
        };
    });
});