define(['./module', 'jquery'], function (commonDirective) {
    'use strict';
    commonDirective.directive('treeList', ['operaByFunctionName', '$compile', function (operaByFunctionName, $compile) {

        function getTreeRoot(data) {
            var root = document.createElement("ul");
            root.setAttribute("class","sidebar-menu");

            var li = document.createElement("li");
            li.setAttribute("class","header");
            root.append(li);

            var span = document.createElement("span");
            $(span).text("Control Panel");
            li.append(span);

            getTree(0, data,root);
            return root;
        }

        function getTree(parentID, data,root) {

            for (var i = 0; i < data.length; i++) {
                if (data[i].parentId == parentID) {

                    var li = document.createElement("li");

                    var a = document.createElement("a");
                    a.setAttribute("href", "#");
                    a.setAttribute("id", "I" + data[i].id);
                    if (data[i].identify != null){
                        a.setAttribute("ui-sref", "controllerPanel." + data[i].identify);
                    }
                    li.append(a);

                    var icon = document.createElement("i");
                    if(typeof(data[i].icon) != "undefined"){
                        icon.setAttribute("class","fa " + data[i].icon);
                    }
                    icon.setAttribute("class","fa fa-circle-o");
                    a.append(icon);

                    var span = document.createElement("span");
                    $(span).text(data[i].treeName);
                    a.append(span);

                    if (parentID == 0){
                        var iconTwo = document.createElement("i");
                        iconTwo.setAttribute("class","fa fa-angle-left pull-right");
                        a.append(iconTwo);
                        li.setAttribute("class","treeview");
                    }

                    root.append(li);

                    var ul  = document.createElement("ul");
                    ul.setAttribute("class","treeview-menu");
                    li.append(ul);

                    getTree(data[i].id,data,ul);
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
                        if(data.msg){
                            alert(data.msg);
                            return ;
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
});
