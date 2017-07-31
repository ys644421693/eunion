/**
 * Created by ys on 2016/3/3.
 */
define(['./moduleService', 'jquery'], function (serviceRequeste, $) {
    'use strict';
    serviceRequeste.service('dragService', ['$http', function ($http) {

        var dragActive = function ($compile, $scope) {
            $(".lyrow").draggable({
                connectToSortable: ".clayout",
                handle: "span",
                helper: "clone",
                drag: function (e, t) {
                    t.helper.width = 400
                },
                stop: function (event, ui) {
                    $(".setChange .preview").remove();
                    $(".setChange .lyrow a").css({"display": "inline"});
                    var temp = $compile($(".setChange").html())($scope);
                    $(".setChange").empty();
                    $(".setChange").append(temp);
                    $(".setChange .column").sortable({
                        opacity: .50,
                        connectWith: ".column"
                    });
                }
            });

            $(".setChange,.setChange .column").sortable({
                connectWith: ".column",
                opacity: .50,
                handle: ".drag"
            });
        };

        var activeMenu = function ($compile, $scope) {
            $(".menu").draggable({
                connectToSortable: ".column",
                handle: "span",
                helper: "clone",
                drag: function (e, t) {
                    t.helper.width = 400
                },
                stop: function (event, ui) {
                    $(".setChange .preview").remove();
                    $(".setChange .lyrow a").css({"display": "inline"});
                    var temp = $compile($(".setChange").html())($scope);
                    $(".setChange").empty();
                    $(".setChange").append(temp);
                    $(".setChange .column").sortable({
                        opacity: .50,
                        connectWith: ".column"
                    });
                }
            });
        };

        var jsonToHtml = function (jsonData) {
            var all = document.createElement("div");
            var val = document.getElementById("showId");
            for (var i = 0; i < jsonData.rows.length; i++) {
                var div = document.createElement("div");
                div.className = 'row-fluid';
                product(jsonData.rows[i], div, jsonData.size);
                all.appendChild(div);
            }
            return all;
        };

        var product = function (obj, html, size) {
            for (var n = 0; n < obj.length; n++) {
                var div = document.createElement("div");
                div.className = size + obj[n].columns + " column clayouts";
                if (obj[n].subPage.length > 0) {
                    var row = document.createElement("div");
                    row.className = "row-fluid";
                    div.appendChild(row);
                    product(obj[n].subPage, row, size);
                }
                html.appendChild(div);
            }
        };

        //html转json
        var htmlToJson = function (html, size) {
            var json = JSON.parse("{\"size\":\"" + size + "\",\"container\":\"container\",\"rows\":[]}");
            for (var t = 0; t < html.children.length; t++) {
                var row = [];
                jsonProduct(html.children[t], row, size, "row");
                json.rows.push(row);
            }
            return json;
        };
        var index = 1;
        var jsonProduct = function (htmlDiv, json, size, type) {
            var ub = htmlDiv.children.length;
            if (ub < 0) return false;
            for (var t = 0; t < ub; t++) {
                var cla = htmlDiv.children[t].getAttribute("class");
                if (cla && cla.indexOf("column") > 0) {
                    var pageId = htmlDiv.children[t].getAttribute("id");
                    var strClass = htmlDiv.children[t].getAttribute("class");
                    var boots = strClass.substring(strClass.indexOf(size)).replace(' column clayout ui-sortable', '').replace(size, "");
                    var temp = JSON.parse("{\"pageID\":id_" + index + ",\"columns\":\"" + boots + "\",\"index\":" + index + ",\"subPage\":[]}");
                    if(type=="row"){
                        json.push(temp);
                    }else{
                        json.subPage.push(temp);
                        index++;
                    }

                    jsonProduct(htmlDiv.children[t], temp, size, "children");
                } else {
                    jsonProduct(htmlDiv.children[t], json, size, type);
                }
            }
        };

        return {
            jsonToHtml: function (element, $compile, $scope, url) {
                $http.get(url).success(function (data) {
                    $(element).empty();
                    $(element).append($compile(jsonToHtml(data))($scope));
                }).error(function (data) {
                    console.log(data);
                });
            },
            dragContent: function ($compile, $scope) {
            },
            activeDrage: function ($compile, $scope) {
                dragActive($compile, $scope);
            },
            htmlToJson: function (html, size) {
                return htmlToJson(html, size);
            }
        }
    }]);
});
