<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="lib/sockjs-0.3.min.js"></script>
<script>
    var websocket = null;
    function connect() {

        if ('WebSocket' in window) {
            websocket = new WebSocket("ws://localhost:8080/system/websocket");
        } else if ('MozWebSocket' in window) {
            websocket = new MozWebSocket("ws://localhost:8080/system/websocket");
        } else {
            websocket = new SockJS("http://localhost:8080/system/websocket");
        }
        websocket.onopen = function (evnt) {
            console.log("连接成功。。。。。。。");
        };
        websocket.onmessage = function (evnt) {
            console.log(evnt.data );
        };
        websocket.onerror = function (evnt) {
        };
        websocket.onclose = function (evnt) {
            console.log("连接关闭。。。。。。。");
        };
    }
    function sendMessage(){
        if(websocket!=null){
            websocket.send("2423154321");
        }
    }
</script>
<body>
<h2>Hello World!</h2>
<button value="" onclick="connect()">dianji</button>
<button onclick="sendMessage()">发送消息</button>
</body>
</html>
