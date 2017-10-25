define(['./module', 'jquery'], function (commonDirective) {
    'use strict';
    commonDirective.directive('treeList', ['operaByFunctionName', '$compile', function (operaByFunctionName, $compile) {

        function getTreeRoot(data) {
            var root = document.createElement("ul");
            root.setAttribute("class", "sidebar-menu");

            var li = document.createElement("li");
            li.setAttribute("class", "header");
            root.append(li);

            var span = document.createElement("span");
            $(span).text("Control Panel");
            li.append(span);

            getTree(0, data, root);
            return root;
        }

        function getTree(parentID, data, root) {

            for (var i = 0; i < data.length; i++) {
                if (data[i].parentId == parentID) {

                    var li = document.createElement("li");

                    var a = document.createElement("a");
                    a.setAttribute("href", "#");
                    a.setAttribute("id", "I" + data[i].id);
                    if (data[i].identify != null) {
                        a.setAttribute("ui-sref", "controllerPanel." + data[i].identify);
                    }
                    li.append(a);

                    var icon = document.createElement("i");
                    if (typeof(data[i].icon) != "undefined") {
                        icon.setAttribute("class", "fa " + data[i].icon);
                    }
                    icon.setAttribute("class", "fa fa-circle-o");
                    a.append(icon);

                    var span = document.createElement("span");
                    $(span).text(data[i].treeName);
                    a.append(span);

                    if (parentID == 0) {
                        var iconTwo = document.createElement("i");
                        iconTwo.setAttribute("class", "fa fa-angle-left pull-right");
                        a.append(iconTwo);
                        li.setAttribute("class", "treeview");
                    }

                    root.append(li);

                    var ul = document.createElement("ul");
                    ul.setAttribute("class", "treeview-menu");
                    li.append(ul);

                    getTree(data[i].id, data, ul);
                }
            }

        }

        return {
            restrict: "E",
            replace: true,
            scope: false,
            link: function ($scope, $element, $attr) {
                $scope.data = [];
                operaByFunctionName.get({NAME: "data", METHOD: "nodeList"}, function (data) {
                    $scope.data = data;
                    $($element).after($compile(getTreeRoot(data))($scope));
                    $($element).hide();
                    $.sidebarMenu($('.sidebar-menu'));
                });

                $scope.node = {};

                $scope.addNode = function () {
                    var tempData = {
                        treeName: $scope.node.treeName,
                        parentId: $scope.node.parentNode ? $scope.node.parentNode.id : 0,
                        identify: $scope.node.identify
                    };
                    operaByFunctionName.add({NAME: "data", METHOD: "addNewNode"}, tempData, function (data) {
                        if (data.msg) {
                            alert(data.msg);
                            return;
                        }
                        $scope.data.push(data.treeSystem);
                        $("#node").modal('hide');
                    });
                };

                $scope.deleteNode = function (dataRow) {
                    if (!confirm("是否确定删除此数据")) {
                        return;
                    }
                    dataRow.NAME = "data";
                    dataRow.METHOD = "deleteNode";
                    operaByFunctionName.delete(dataRow, function (data) {
                        for (var i = 0; i < $scope.data.length; i++) {
                            if ($scope.data[i].id == dataRow.id) {
                                $scope.data.splice(i, 1);
                                break;
                            }
                        }
                    });
                }

            }
        }
    }]);

    commonDirective.directive('compoment', ['$compile', function ($compile) {


        return {
            restrict: "E",
            replace: true,
            scope: {type: "=", data: "=", dlFunction: "="},
            link: function ($scope, $element, $attr) {

                var html = "<div><label ng-bind='name'></label><input type='text'></div>";
                if ($scope.type.target == "file") {
                    html = "<div class='form-group'><div class='col-lg-3'><input type='text' ng-model='data.name' class=\"input-sm\" /></div> <div class=\"col-lg-7\"><input type='file'  fileread='data.value'/><label ng-bind='data.value.name?data.value.name:data.value' style='float: right'></label></div><div class=\"col-lg-1\"><input type=\"button\" class=\"btn btn-danger btn-xs\" ng-click='dlFunction(data)' value=\"删除\"></div></div>";
                } else if ($scope.type.target == "number") {
                    html = "<div class='form-group'><label ng-bind='type.name' class=\"col-lg-3 control-label\"></label> <div class=\"col-lg-7\"><input type='number' class=\"form-control\" ng-model='data.value'/></div><div class=\"col-lg-2\"><input type=\"button\" class=\"btn btn-danger btn-xs\" ng-click='dlFunction(data)' value=\"删除\"></div></div>";
                } else if ($scope.type.target == 'text') {
                    html = "<div class='form-group'><label ng-bind='type.name' class=\"col-lg-3 control-label\"></label> <div class=\"col-lg-7\"><input type='text' class=\"form-control\" ng-model='data.value'/></div><div class=\"col-lg-2\"><input type=\"button\" class=\"btn btn-danger btn-xs\" ng-click='dlFunction(data)' value=\"删除\"></div></div>";
                } else if ($scope.type.target == 'checkbox') {
                    html = "<div class='form-group'><label ng-bind='type.name' class=\"col-lg-3 control-label\"></label> <div class=\"col-lg-7\"><input type='checkbox' class=\"form-control\" ng-model='data.value'/></div><div class=\"col-lg-2\"><input type=\"button\" class=\"btn btn-danger btn-xs\" ng-click='dlFunction(data)' value=\"删除\"></div></div>";
                } else if ($scope.type.target == 'email') {
                    html = "<div class='form-group'><label ng-bind='type.name' class=\"col-lg-3 control-label\"></label> <div class=\"col-lg-7\"><input type='email' class=\"form-control\" ng-model='data.value'/></div><div class=\"col-lg-2\"><input type=\"button\" class=\"btn btn-danger btn-xs\" ng-click='dlFunction(data)' value=\"删除\"></div></div>";
                } else if ($scope.type.target == 'password') {
                    html = "<div class='form-group'><label ng-bind='type.name' class=\"col-lg-3 control-label\"></label> <div class=\"col-lg-7\"><input type='password' class=\"form-control\" ng-model='data.value'/></div><div class=\"col-lg-2\"><input type=\"button\" class=\"btn btn-danger btn-xs\" ng-click='dlFunction(data)' value=\"删除\"></div></div>";
                } else if ($scope.type.target == 'radio') {
                    html = "<div class='form-group'><label ng-bind='type.name' class=\"col-lg-3 control-label\"></label> <div class=\"col-lg-7\"><input type='radio' class=\"form-control\" ng-model='data.value'/></div><div class=\"col-lg-2\"><input type=\"button\" class=\"btn btn-danger btn-xs\" ng-click='dlFunction(data)' value=\"删除\"></div></div>";
                }
                $($element).parent().append($compile(html)($scope));
                if ($scope.type.target == "file") {
                    var temp = '#file' + $scope.data.id + '' + ($scope.data.count ? $scope.data.count : 0);

                    $(temp).html($scope.data.value);
                }
            }
        }
    }]);

    commonDirective.directive("fileread", [function () {
        return {
            scope: {
                fileread: "="
            },
            link: function ($scope, element, attributes) {

                element.bind("change", function (changeEvent) {
                    $scope.$apply(function () {
                        $scope.fileread = changeEvent.target.files[0];
                    });
                });
            }
        }
    }]);

    commonDirective.directive("formSetting", ['operaByFunctionName', '$compile', 'operaByFunctionNameService', function (operaByFunctionName, $compile, operaByFunctionNameService) {
        return {
            restrict: "E",
            replace: true,
            scope: false,
            templateUrl: "/views/tableProduct.html",
            link: function ($scope, $element, $attr) {
                $scope.dataTable = [];
                $scope.dataInfo = [];
                $scope.selectedTwo = [];
                $scope.viewSelect = 0;
                $scope.tableClass = "table";
                $scope.serviceObj = {};
                $scope.mapProperties = [];

                operaByFunctionName.get({NAME: "table", METHOD: "getTableInfo"}, function (data) {
                    $scope.dataTable = data;
                });

                $scope.getTableConfig = function () {
                    //var dt = {"tableName": $scope.selected.tableName};
                    if($scope.selectedTwo.length > 0)
                        $scope.selectedTwo.length = 0 ;
                    for(var temp in $scope.selected.columns){
                        var tp ={
                            "columnName":$scope.selected.columns[temp],
                            "alias":"",
                            "type":0
                        };
                        $scope.selectedTwo.push(tp);
                    }
                   /* operaByFunctionNameService.get({NAME: "table", METHOD: "getDataByTableName"}, dt, function (data) {
                        if (data.length <= 0) {
                            if($scope.selectedTwo.length > 0)
                                $scope.selectedTwo.length = 0 ;
                            for(var temp in $scope.selected.columns){
                                var tp ={
                                    "columnName":$scope.selected.columns[temp],
                                    "alias":""
                                };
                                $scope.selectedTwo.push(tp);
                            }
                        }else{
                            $scope.selectedTwo.length = 0;
                            for (var columnsObj in data){
                                $scope.selectedTwo.push(columnsObj.columnName);
                            }
                        }
                    });*/
                };

                $scope.saveTableConfig = function () {

                };

                $scope.viewTable = function(){
                    if(!$scope.serviceObj.serviceSpace && !$scope.serviceObj.serviceSpace){
                        alert("请填写服务空间及服务！");
                        return;
                    }
                    $scope.viewSelect = 1;
                    $scope.selectedTwo.sort(function(a,b){return a.index-b.index});
                    operaByFunctionName.get({NAME: $scope.serviceObj.serviceSpace, METHOD: $scope.serviceObj.serviceName}, function (data) {
                        $scope.dataViewTable = data;
                        console.log(data);
                    });
                };

                $scope.setData = function(){
                    $scope.viewSelect = 0;
                };

                $scope.deleteColumn = function(rowData){
                    for (var temp = 0;temp < $scope.selectedTwo.length ; temp++){
                        if($scope.selectedTwo[temp].index == rowData.index){
                            $scope.selectedTwo.splice(temp,1);
                            return ;
                        }
                    }
                };
                $scope.moveData = function (rowData,direction) {
                    //向下移动一个
                    if(direction == 1){
                        var temp = $scope.selectedTwo[rowData.index + 1];
                        if (temp){
                            $scope.selectedTwo[rowData.index + 1] = rowData;
                            $scope.selectedTwo[rowData.index] = temp;
                        }
                    }

                    if(direction == 2){
                        var temp = $scope.selectedTwo[rowData.index - 1];
                        if (temp){
                            $scope.selectedTwo[rowData.index - 1] = rowData;
                            $scope.selectedTwo[rowData.index] = temp;
                        }

                    }
                }

                $scope.addMapData = function(){
                    var temp = {"value":"","valueOriginal":""};
                    $scope.mapProperties.push(temp);
                };
                var tempRowData = null;
                $scope.transfer = function (rowData) {
                    tempRowData = rowData;
                    $scope.mapProperties.length = 0;
                    var temp = {"value": "", "valueOriginal": ""};
                    $scope.mapProperties.push(temp);
                };
                $scope.saveTableTransfer = function () {
                    tempRowData.transferredMeanings = $scope.mapProperties;
                    console.log(tempRowData);
                };
                $scope.addColumns= function (type) {
                    var tmp = {
                        'type':type,
                        'columnName':''
                    };
                    $scope.selectedTwo.push(tmp);
                };
            }
        }
    }]);
});
