<!DOCTYPE html>
<html>
<head>
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/js/bootstrap.min.js"></script>

    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<h2>商品列表：${pageList.getTotalElements()}</h2>
    <div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h4 class="modal-title" id="myModalLabel">
                                标题
                            </h4>
                        </div>
                        <div class="modal-body">
                            您有新订单
                        </div>
                        <div class="modal-footer">
                            <button onclick="closeTip()" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        </div>
                    </div>

                </div>

            </div>

<audio id="notic" >
    <source  src="/sell/mp3/music.mp3" type="audio/mpeg">
</audio>
<script type="text/javascript">
    $(document).ready(function(){

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
        console.log(ev.data)
        $("#myModal").modal("show");
        setTimeout(function(){ document.getElementById("notic").play(); }, 1000);

    }
    websocket.onerror = function (ev) {
        console.log("出错")
    }

    //关闭浏览器之前关闭socket
    window.onbeforeunload = function (ev) {
        websocket.close()
    }
    });
    function closeTip(){
        document.getElementById("notic").pause();
    }
</script>
</body>
</html>



