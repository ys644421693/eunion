require.config({
    paths: {
        'jquery': '../lib/jquery-2.0.0.min',
        'angular': '../lib/angular',
        'angular-ui-route': '../lib/angular-ui-router',
        'domReady': '../lib/domReady',
        'angular-route': "../lib/angular-route",
        'app': "../js/app",
        'router': "../js/router",
        'angular-cookies': "../lib/angular-cookies",
        'angular-resource': "../lib/angular-resource",
        'config': "config",
        'jquery-ui': "../lib/jquery-ui",
        'easyTooltip':'../lib/easyTooltip',
        'custom-min':'../lib/jquery-ui-1.7.2.custom.min',
        'wysiwyg':'../lib/jquery.wysiwyg',
        'hoverIntent':'../lib/hoverIntent',
        'superfish':'../lib/superfish',
        'bootstrap': "../lib/bootstrap",
        'custom':'../lib/custom',
        'bootstrapValidator':'../lib/bootstrapValidator',
        'nicEdit':'../lib/nicEdit',
        'base64':'../lib/jquery.base64',
        'upload':'../lib/ng-file-upload.min',
        'socket':"../lib/sockjs-0.3.min",
        'sidebar-menu':"../lib/sidebar-menu"
    },
    shim: {
        'angular': {
            exports: 'angular'
        }, 'jquery-ui': {
            deps: ['jquery'],
            exports: 'jquery-ui'
        },'base64':{
            deps: ['jquery'],
            exports: 'base64'
        },
        'angular-route': {
            deps: ['angular'],
            exports: 'angular-route'
        }, 'upload': {
            deps: ['angular'],
            exports: 'upload'
        }, 'angular-ui-route': {
            deps: ['angular'],
            exports: 'angular-ui-route'
        }, 'angular-cookies': {
            deps: ['angular'],
            exports: 'angular-cookies'
        }, 'angular-resource': {
            deps: ['angular'],
            exports: 'angular-resource'
        },'bootstrap':{
            deps: ['jquery-ui'],
            exports: 'bootstrap'
        },'bootstrapValidator':{
            deps: ['bootstrap'],
            exports: 'bootstrapValidator'
        },'sidebar-menu':{
            deps: ['bootstrap'],
            exports: 'sidebar-menu'
        }
    },
    deps: ['jquery', 'jquery-ui', 'angular','sidebar-menu'],
    urlArgs: "bust=" + (new Date()).getTime()
});

//手动启动angularjs
define(['require',
    'angular',
    'angular-route',
    'angular-ui-route',
    'jquery',
    'bootstrap',
    'app',
    'router',
    "angular-cookies",
    "angular-resource",
    "config", "jquery-ui","nicEdit",'base64','upload','socket'
], function (require, angular) {
    'use strict';
    require(['domReady!'], function (document) {
        angular.bootstrap(document, ['app']);
    });
});