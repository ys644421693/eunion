/**
 * Created by yangshuo on 15/11/23.
 */
app.controller('unionCrt', ['$scope','operationDataById','$http', function ($scope,operationDataById,$http) {
    $scope.user={userName:"12132465",yyy:"7898"};
    var files=null;
    operationDataById.query({NAME:"u",ID:"test.ui"},function(data){
        console.log(data);
    });

    $scope.selector=function($file){
        console.log($file);
        files=$file;
    };

    $scope.upload=function(){
        console.log($scope.picFile);
    };

    $scope.submits=function(){
        var data = "";
        for(var x in $scope.user){
            data=data+x+"="+$scope.user[x]+"&";
        }
        $http.post("http://localhost:8080/data/u/tbean?"+data).success(function(data){

        });
        /*operationDataById.save({NAME:"u",ID:"tbean"},data,function(){
            console.log(data);
        });*/
    }
    var websocket;
    $scope.socket=function(){

        if ('WebSocket' in window) {
            alert("WebSocket");
            websocket = new WebSocket("ws://localhost:8089/echo");
        }else if ('MozWebSocket'in window) {
            alert("MozWebSocket");
            websocket = new MozWebSocket("ws://echo");
        } else {
            alert("SockJS");
            websocket = new SockJS("http://127.0.0.1:8089/sockjs/echo");
        }
        websocket.onopen = function (evnt) {
           alert("链接服务器成功!");
        };

        websocket.onclose = function (evnt) {
            alert("与服务器断开了链接!");
        }

        websocket.onmessage = function (evnt) {
            alert(evnt.data);
        };

        websocket.onerror = function (evnt) {
        };
    };

    function send(){
        if (websocket != null) {
            var message = document.getElementById('message').value;
            websocket.send(message);
        } else {
            alert('未与服务器链接.');
        }
    }
}]);