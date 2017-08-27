define(['./module', 'jquery'], function (controllers, $) {
    'use strict';
    controllers.controller('accountList', function ($scope, operaByFunctionName) {

        $scope.dataRole = [];
        operaByFunctionName.get({NAME: "data", METHOD: "getAllRole"}, function (data) {
            $scope.dataRole = data;
        });

        $scope.role = {};
        //控制器的具体js代码
        $scope.addRole = function () {
            operaByFunctionName.add({NAME: "data", METHOD: "addRole"}, $scope.role, function (data) {
                if(data.status == "000001"){
                    alert(data.msg);
                    return ;
                }
            });
        }

        $scope.deleteRole = function (dataRow) {
            if (!confirm("是否确定删除此数据")) {
                return;
            }
            dataRow.NAME = "data";
            dataRow.METHOD = "deleteRole";
            operaByFunctionName.delete(dataRow, function (data) {
                console.log(data);
                for (var i = 0; i < $scope.dataRole.length; i++) {
                    if ($scope.dataRole[i].id == dataRow.id) {
                        $scope.dataRole.splice(i, 1);
                        break;
                    }
                }
            });
        }
    });

    controllers.controller('urlController', function ($scope, operaByFunctionNameService) {

        $scope.dataUrl = [];
        $scope.serviceShow = 1;
        $scope.roleUrlData = [];
        $scope.roleData = [];
        var tempId = null;
        operaByFunctionNameService.get({NAME: "data", METHOD: "getAllUrl"}, {}, function (data) {
            $scope.dataUrl = data;
        });

        $scope.url = {};
        //控制器的具体js代码
        $scope.addUrl = function () {
            if (tempId != null) {
                $scope.updateUrl();
                return;
            }

            operaByFunctionNameService.add({NAME: "data", METHOD: "addUrl"}, $scope.url, function (data) {
                if(data.msg){
                    alert(data.msg);
                    return ;
                }
                $scope.dataUrl.push(data);
                $("#role").modal('hide');
            });
        };

        $scope.cleanUrl = function () {
            //文本框置空
            $scope.url.urlName = "";
            $scope.url.url = "";
            $scope.url.descript = "";
        };

        $scope.deleteUrl = function (dataRow) {
            if (!confirm("是否确定删除此数据")) {
                return;
            }
            /*dataRow.NAME = "data";
             dataRow.METHOD = "deleteUrl";*/
            delete dataRow.roles;
            operaByFunctionNameService.delete({NAME: "data", METHOD: "deleteUrl"}, dataRow, function (data) {
                if (data.status == "000000") {
                    alert("删除成功！");
                    for (var i = 0; i < $scope.dataUrl.length; i++) {
                        if ($scope.dataUrl[i].id == dataRow.id) {
                            $scope.dataUrl.splice(i, 1);
                            break;
                        }
                    }
                }
            });
        };

        $scope.updateUrlEvent = function (data) {
            $scope.url.urlName = data.urlName;
            $scope.url.url = data.url;
            $scope.url.descript = data.descript;
            tempId = data.id;
        };

        $scope.updateUrl = function () {
            $scope.url.id = tempId;
            operaByFunctionNameService.put({NAME: "data", METHOD: "updateUrl"}, $scope.url, function (result) {
                if(result.status == "000001"){
                    alert(result.msg);
                    return ;
                }
                tempId = null;
                if (result.status == "000000") {
                    alert("更新成功！");
                    $("#role").modal('hide');
                    for (var i in $scope.dataUrl) {
                        if ($scope.dataUrl[i].id == $scope.url.id) {
                            $scope.dataUrl[i].urlName = $scope.url.urlName;
                            $scope.dataUrl[i].url = $scope.url.url;
                            $scope.dataUrl[i].descript = $scope.url.descript;

                            break;
                        }
                    }
                }
            });

        };

        var temp;
        $scope.queryRole = function (data) {
            temp = data.id;
            data.roles = null;
            /*data.NAME = "data";
             data.METHOD = "getRoleByUrl";*/
            operaByFunctionNameService.get({NAME: "data", METHOD: "getRoleByUrl"}, data, function (resp) {
                $scope.roleUrlData = resp;
                $scope.serviceShow = 2;
            });
        };

        $scope.backPri = function () {
            $scope.serviceShow = 1;
        };

        $scope.deleteUrlRole = function (data) {
            if (!confirm("是否删除此角色的权限")) {
                return;
            }
            /*data.NAME = "data";
             data.METHOD = "deleteUrlRole";*/
            data.urlId = temp;
            operaByFunctionNameService.delete({NAME: "data", METHOD: "deleteUrlRole"}, data, function (result) {
                if (result.status == "000000") {
                    alert("已删除新角色权限！");
                    for (var n = 0; n < $scope.roleUrlData.length; n++) {
                        if ($scope.roleUrlData[n].id == data.id) {
                            $scope.roleUrlData.splice(n, 1);
                            break;
                        }
                    }
                }
            });
        };
        $scope.addUrlRole = function (data) {
            data.urlId = temp;
            operaByFunctionNameService.save({NAME: "data", METHOD: "addUrlRole"}, data, function (result) {
                if (result.status == "000000") {
                    alert("已添加新角色权限！");
                    for (var n = 0; n < $scope.roleData.length; n++) {
                        if (data.id == $scope.roleData[n].id) {
                            $scope.roleUrlData.push(data);
                            $scope.roleData.splice(n, 1);
                            break;
                        }
                    }
                }
            });
        };

        $scope.loadAllRole = function () {
            operaByFunctionNameService.get({NAME: "data", METHOD: "getAllRole"}, {}, function (data) {
                for (var i = data.length; i > 0; i--) {
                    for (var n = 0; n < $scope.roleUrlData.length; n++) {
                        if (data[i - 1].id == $scope.roleUrlData[n].id) {
                            data.splice(i - 1, 1);
                        }
                    }
                }
                $scope.roleData = data;
            });
        };


        $scope.rowTemp = {};
        $scope.checkInfo = [];
        $scope.params = {checkInfos: [],type:"01"};
        $scope.ci = null;
        $scope.checkParams = function (row) {
            $scope.rowTemp = row;
            $scope.serviceShow = 3;
            operaByFunctionNameService.get({NAME: "checkInfo", METHOD: "getAllCheckInfo"}, {}, function (data) {
                $scope.checkInfo = data;
            });
        };
        $scope.addTemp = function () {
            if (!$scope.ci || $scope.ci == "") {
                return;
            }
            for (var i in $scope.params.checkInfos) {
                if ($scope.params.checkInfos[i].id == $scope.ci.id) {
                    return;
                }
            }
            $scope.params.checkInfos.push($scope.ci);
        };
        $scope.deleteTemp = function (dt) {
            for (var i in $scope.params.checkInfos) {
                if ($scope.params.checkInfos[i].id == dt.id) {
                    $scope.params.checkInfos.splice(i, 1);
                    break;
                }
            }
        };

        $scope.saveParams = function () {
            $scope.rowTemp.fieldCheck.push($scope.params);
            operaByFunctionNameService.put({NAME: "data", METHOD: "updateUrl"}, $scope.rowTemp, function (data) {
                if(data.status == "000001"){
                    alert(data.msg);
                    return ;
                }
                $scope.serviceShow = 9;
                $scope.checkParams(data.systemUrl);
            });

        };
        $scope.deleteParams = function (row) {

            if (!confirm("是否删除此参数验证！")){
                return ;
            }
            var tp = {};
            angular.copy($scope.rowTemp,tp);

            for (var i in tp.fieldCheck){
                if(tp.fieldCheck[i].id == row.id){
                    tp.fieldCheck.splice(i,1);
                    break;
                }
            }
            operaByFunctionNameService.put({NAME: "data", METHOD: "updateUrl"}, tp, function (data) {
                if(data.status == "000001"){
                    alert(data.msg);
                    return ;
                }
                for (var i in $scope.rowTemp.fieldCheck){
                    if($scope.rowTemp.fieldCheck[i].id == row.id){
                        $scope.rowTemp.fieldCheck.splice(i,1);
                        break;
                    }
                }
            });

        };
        $scope.resetData = function () {
            $scope.params.checkInfos.length = 0;
            $scope.params.type = "01";
            $scope.ci = null;
        }
    });

    controllers.controller('product', function ($scope, operaByFunctionNameService, fileUploadService) {
        $scope.productAll = [];
        $scope.product = {};
        $scope.isUpdate = false;
        $scope.isProperties = false;
        $scope.baseProperties = [];
        $scope.propertiesProduct = {};
        $scope.properties = [];
        $scope.backProperties = [];
        $scope.newProperties = "";
        $scope.pro = {};
        var area = null;
        //控制器的具体js代码
        operaByFunctionNameService.get({NAME: "product", METHOD: "getAllData"}, {}, function (data) {
            $scope.productAll = data;
        });

        operaByFunctionNameService.get({NAME: "BaseProperties", METHOD: "getAllBaseProperties"}, {}, function (data) {
            $scope.baseProperties = data;
        });

        $scope.addProduct = function () {
            if (area != null) {
                $scope.product.description = $.base64('encode', $(".nicEdit-main").html());
            }
            if (!$scope.product.isHome) {
                $scope.product.sort = 0;
            }
            if (!$scope.isUpdate) {
                operaByFunctionNameService.add({
                    NAME: "product",
                    METHOD: "addProduct"
                }, $scope.product, function (data) {
                    if(data.status == "000001"){
                        alert(data.msg);
                        return ;
                    }
                    alert("已添加商品！");
                    $scope.productAll.push(data);
                    $("#product").modal('hide');
                });
            } else {
                operaByFunctionNameService.put({
                    NAME: "product",
                    METHOD: "updateProduct"
                }, $scope.product, function (data) {
                    if(data.status == "000001"){
                        alert(data.msg);
                        return ;
                    }
                    alert("已修改商品！");
                    for (var i in $scope.productAll) {
                        if ($scope.productAll[i].id == $scope.product.id) {
                            $scope.productAll[i].name = $scope.product.name;
                            $scope.productAll[i].price = $scope.product.price;
                            $scope.productAll[i].quantity = $scope.product.quantity;
                            $scope.productAll[i].isHome = $scope.product.isHome;
                            $scope.productAll[i].sort = $scope.product.sort;
                            $scope.productAll[i].description = $scope.product.description;
                            break;
                        }
                    }
                    $("#product").modal('hide');
                });
            }

        };
        $scope.deleteProduct = function (rowData) {
            if (!confirm("是否确定删除此商品！")) {
                return;
            }
            delete  rowData.createTime;
            operaByFunctionNameService.delete({NAME: "product", METHOD: "deleteProduct"}, rowData, function (data) {
                for (var i in $scope.productAll) {
                    if ($scope.productAll[i].id == rowData.id) {
                        $scope.productAll.splice(i, 1);
                        break;
                    }
                }
            });
        };

        $scope.showModal = function () {
            if (area == null) {
                area = new nicEditor({fullPanel: true}).panelInstance('description');
            }
            if (!$scope.product.id) {
                delete  $scope.product.id;
            }
            $(".nicEdit-main").empty();
            $scope.product.name = "";
            $scope.product.price = null;
            $scope.product.quantity = null;
            $scope.product.isHome = null;
            $scope.product.sort = null;
        };

        $scope.showData = function (row) {
            if (area == null) {
                area = new nicEditor({fullPanel: true}).panelInstance('description');
            }

            try {
                $(".nicEdit-main").html($.base64('decode', row.description));
            } catch (e) {
                console.log("未编码！");
            }
            $scope.product.id = row.id;
            $scope.product.name = row.name;
            $scope.product.price = row.price;
            $scope.product.quantity = row.quantity;
            $scope.product.isHome = row.isHome;
            $scope.product.sort = row.sort;
            $scope.isUpdate = true;
        };

        $scope.showProperties = function (row) {
            $scope.propertiesProduct = row;
            $scope.properties = [];
            $scope.backProperties = [];
            if ($scope.propertiesProduct.propertiesType.length > 0) {
                for (var t = 0; t < $scope.baseProperties.length; t++) {
                    var temp = {};
                    temp.properties = $scope.baseProperties[t];
                    temp.data = [];
                    for (var i in $scope.propertiesProduct.propertiesType) {
                        if (temp.properties.id == $scope.propertiesProduct.propertiesType[i].baseProperties.id) {
                            var tt = {};
                            angular.copy($scope.propertiesProduct.propertiesType[i], tt);
                            delete tt.baseProperties;
                            temp.data.push(tt);
                        }
                    }
                    if (temp.data.length > 0) {
                        $scope.properties.push(temp);
                    } else {
                        $scope.backProperties.push(temp.properties);
                    }
                }
            } else {
                angular.copy($scope.baseProperties, $scope.backProperties);
            }

            $scope.isProperties = true;
        };

        $scope.backList = function () {
            $scope.isProperties = false;
        };

        $scope.deleteProperties = function (data) {
            for (var temp in $scope.properties) {
                if ($scope.properties[temp].properties.id == data.id) {
                    $scope.backProperties.push($scope.properties[temp].properties);
                    $scope.properties.splice(temp, 1);
                    break;
                }
            }
        };

        $scope.addNewProperties = function (data) {
            if (data == "" || !data) {
                alert('请选择属性！');
                return;
            }
            for (var n in $scope.properties) {
                if ($scope.properties[n].properties.id == data.id) {
                    return;
                }
            }
            var temp = {properties: data, data: []};
            $scope.properties.push(temp);
            for (var n in $scope.backProperties) {
                if ($scope.backProperties[n].id == data.id) {
                    $scope.backProperties.splice(n, 1);

                    break;
                }
            }
        };
        var count = 1;
        $scope.addProperties = function (data) {
            var temp = {};
            temp.description = "";
            temp.id = null;
            temp.name = "";
            temp.parentId = null;
            temp.sort = 0;
            temp.value = "";
            temp.count = count++;
            data.push(temp);
        };

        $scope.dlProperties = function (data) {
            for (var n in $scope.properties) {
                for (var i in $scope.properties[n].data) {
                    if (data.id && $scope.properties[n].data[i].id == data.id) {
                        $scope.properties[n].data.splice(i, 1);
                        break;
                    } else if (!data.id && $scope.properties[n].data[i].count == data.count) {
                        $scope.properties[n].data.splice(i, 1);
                        break;
                    }
                }
            }
        };

        $scope.files = [];
        $scope.saveProperties = function () {

            for (var a in $scope.properties) {
                for (var b in $scope.properties[a].data) {
                    if ($scope.properties[a].properties.target == "file") {
                        if (typeof $scope.properties[a].data[b].value == "object") {
                            $scope.files.push($scope.properties[a].data[b].value);
                            $scope.properties[a].data[b].value = $scope.properties[a].data[b].value.name;
                        }
                    }
                }
            }

            var temp = [];
            for (var i in $scope.properties) {
                for (var n in $scope.properties[i].data) {
                    $scope.properties[i].data[n].baseProperties = $scope.properties[i].properties;
                    temp.push($scope.properties[i].data[n]);
                }
            }
            angular.copy(temp, $scope.propertiesProduct.propertiesType);
            if ($scope.files.length > 0) {
                $("#tip").modal("show");
                fileUploadService.uploads($scope.files, function () {
                    $("#tip").modal("hide");
                    addProperties();
                });
            }else{
                addProperties();
            }

        };

        var addProperties = function () {
            operaByFunctionNameService.add({NAME: "product",METHOD: "addProductProperties"}, $scope.propertiesProduct, function (data) {
                if(data.status == "000001"){
                    alert(data.msg);
                    return ;
                }
                alert("操作成功！");
            });
        };

        $scope.updateNewPro = function (data) {
            if (!data) {
                alert("请选择数据!");
                return;
            }
            $("#baseProperty").modal('show');
            angular.copy(data, $scope.pro);
        };
        $scope.addNewPro = function () {
            $scope.pro = {};
            $("#baseProperty").modal('show');
        };
        $scope.addPro = function () {
            if (!$scope.pro.name) {
                alert("属性名不能为空！");
                return;
            }
            if (!$scope.pro.target) {
                alert("请选择数据类型！");
                return;
            }
            if (!$scope.pro.id) {
                operaByFunctionNameService.add({
                    NAME: "BaseProperties",
                    METHOD: "addProperties"
                }, $scope.pro, function (data) {
                    if(data.status == "000001"){
                        alert(data.msg);
                        return ;
                    }
                    alert("添加成功！");
                    $scope.backProperties.push(data);
                    $scope.baseProperties.push(data);
                });
            } else {
                operaByFunctionNameService.put({
                    NAME: "BaseProperties",
                    METHOD: "updateProperties"
                }, $scope.pro, function (data) {
                    if(data.status == "000001"){
                        alert(data.msg);
                        return ;
                    }
                    alert("修改成功！");
                    for (var i in $scope.baseProperties) {
                        if ($scope.baseProperties[i].id == $scope.pro.id) {
                            $scope.baseProperties[i].name = $scope.pro.name;
                            $scope.baseProperties[i].value = $scope.pro.value;
                            $scope.baseProperties[i].target = $scope.pro.target;
                            break;
                        }
                    }
                });
            }
        };

        $scope.deleteNewPro = function (pd) {
            if (!pd) {
                alert("请选择数据!");
                return;
            }
            if (!confirm("是否确定删除此属性！")) {
                return;
            }
            operaByFunctionNameService.delete({
                NAME: "BaseProperties",
                METHOD: "deleteProperties"
            }, pd, function (data) {
                if (data.status == "000000") {
                    alert("删除成功！");
                    for (var n in $scope.backProperties) {
                        if ($scope.backProperties[n].id == pd.id) {
                            $scope.backProperties.splice(n, 1);
                            break;
                        }
                    }
                } else {
                    alert(data.msg);
                }
            });
        }

    });


    controllers.controller('controller2', function ($scope, operaByFunctionName) {
        //控制器的具体js代码
        $scope.name2 = "2222222";
        console.log(8);
        operaByFunctionName.get({NAME: "test", METHOD: "tt"}, function (data) {
            console.log(data);
        });

        var data = {test: 111, au: "333"};
        operaByFunctionName.add({NAME: "ys", METHOD: "test"}, data, function (data) {
            console.log(data);
        });
    });

    controllers.controller('checkRole', function ($scope, operaByFunctionNameService) {
        //控制器的具体js代码
        $scope.dataRole = [];
        $scope.check = {};
        operaByFunctionNameService.get({NAME: "checkInfo", METHOD: "getAllCheckInfo"}, {}, function (data) {
            $scope.dataRole = data;
        });
        $scope.saveCheck = function () {
            operaByFunctionNameService.add({NAME: "checkInfo", METHOD: "saveCheckInfo"}, $scope.check, function (data) {
                if(data.status == "000001"){
                    alert(data.msg);
                    return ;
                }
                if (!$scope.check.id) {
                    $scope.dataRole.push(data);
                } else {
                    for (var n in $scope.dataRole) {
                        if ($scope.dataRole[n].id == $scope.check.id) {
                            $scope.dataRole[n].name = $scope.check.name;
                            $scope.dataRole[n].description = $scope.check.description;
                            $scope.dataRole[n].value = $scope.check.value;
                        }
                    }
                }

            });
        };
        $scope.updateCheck = function (row) {
            $scope.check = row;
        };
        $scope.addCheck = function () {
            $scope.check = {};
        };
        $scope.deleteCheck = function (row) {
            if (!confirm("是否确定删除此数据！")) {
                return;
            }
            operaByFunctionNameService.delete({NAME: "checkInfo", METHOD: "deleteCheckInfo"}, row, function (data) {
                for (var n in $scope.dataRole) {
                    if ($scope.dataRole[n].id == row.id) {
                        $scope.dataRole.splice(n, 1);
                        break;
                    }
                }

            });
        }
    });

    controllers.controller('systemSetting',function ($scope,$stateParams) {
        
    });
});