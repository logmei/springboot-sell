<script>
    var websocket = new WebSocket("ws://localhost:8080/sell/websocket");
    if(typeof websocket == undefined){
        alert("您的浏览器不支持");
    }
    websocket.onopen = function (ev) {
        console.log("websocket 已打开")
    }
    websocket.onclose = function (ev) {
        console.log("websocket 已关闭");
    }
    websocket.onmessage = function (ev) {
        alert(ev.data)
    }
    websocket.onerror = function (ev) {
        console.log("出错")
    }
    websocket.send("要一盘西红柿炒鸡蛋");

    //关闭浏览器之前关闭socket
    window.onbeforeunload = function (ev) {
        websocket.close()
    }

</script>