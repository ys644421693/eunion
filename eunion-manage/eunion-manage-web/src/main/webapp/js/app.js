define(["angular"
        ,'controller/mainController'
        ,'directive/mainDirective',
        'service/mainService'
       ],function(angular){
    return angular.module("app",['app.controllers','app.directive','ui.router','ngResource','app.serviceRequest','ngFileUpload']);
});