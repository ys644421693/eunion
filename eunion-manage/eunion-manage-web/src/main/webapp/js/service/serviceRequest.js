/**
 * Created by ys on 2016/2/26.
 */
define(['./moduleService', 'jquery'], function (serviceRequeste, $) {
    'use strict';
    serviceRequeste.factory('operaById', ['$resource', function ($resource) {
        return $resource(config.url + "/" + config.space + '/:NAME/:id',
            {NAME: "@name", ID: "@id"},
            {
                put: {method: "put", params: {}, isArray: false, timeout: config.timeout},
                add: {method: "post", params: {}, isArray: false, timeout: config.timeout},
                get: {method: "GET", params: {}, isArray: false, timeout: config.timeout}
            });
    }]);
    serviceRequeste.factory('operaByFunctionName', ['$resource', function ($resource) {
        return $resource(config.url + "/" + config.space + '/:NAME/:METHOD',
            {NAME: "@name", METHOD: "@method", PARAMS: '@params'},
            {
                put: {method: "PUT", params: {}, isArray: false, timeout: config.timeout},
                add: {method: "POST", params: {}, isArray: false, timeout: config.timeout},
                get: {method: "GET", params: {}, isArray: true, timeout: config.timeout},
                delete: {method: "DELETE", params: {}, isArray: false, timeout: config.timeout}
            });
    }]);

    serviceRequeste.service('operaByFunctionNameService', ['operaByFunctionName', function (operaByFunctionName) {

        var error = function (data) {
            if (data.status == 403) {
                alert("无操作权限，请联系管理员！");
            }
            if (data.status == 400) {
                alert("请更改数据提交方式！");
            }
            if (data.status == 500) {
                alert("系统异常！");
            }
        };

        var merge = function (param, data) {
            for (var tem in param) {
                data[tem] = param[tem];
            }
            return data;
        };

        return {
            get: function (param, data, success) {
                operaByFunctionName.get(merge(param, data), success, error);
            },
            put: function (param, data, success) {
                operaByFunctionName.put(param, data, success, error);
            },
            add: function (param, data, success) {
                operaByFunctionName.add(param, data, success, error);
            },
            delete: function (param, data, success) {
                operaByFunctionName.delete(merge(param, data), success, error);
            },
            save: function (param, data, success) {
                operaByFunctionName.save(param, data, success, error);
            }
        };
    }]);

    serviceRequeste.service('fileUploadService', ['Upload', '$timeout', function (Upload, $timeout) {

        var error = function (data) {
            if (data.status == 403) {
                alert("无操作权限，请联系管理员！");
            }
            if (data.status == 400) {
                alert("请更改数据提交方式！");
            }
            if (data.status == 500) {
                alert("系统异常！");
            }
        };

        var upload = function (files, callback) {
            var count = 0;
            angular.forEach(files, function (file) {
                file.upload = Upload.upload({
                    url: config.url + "/" + config.space + "/product/uploadFile",
                    data: {file: file}
                });
                file.upload.then(function (response) {
                    console.log(response);
                    console.log(files);
                    count ++;
                    if(files.length == count){
                        callback();
                    }
                    $timeout(function () {
                        file.result = response.data;
                    })
                }, function (response) {
                    console.log(response);
                }, function (evt) {
                    file.percent = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
                })
            });
        };

        return {
            uploads: function (files, callback) {
                upload(files, callback);
            }
        };
    }]);
});