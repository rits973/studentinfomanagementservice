$(function() {
    var WS = window['MozWebSocket'] ? MozWebSocket : WebSocket
    var dateSocket = new WS("routes.Application.pingWs().webSocketURL(request)")
    console.log("----"+dateSocket);
    var receiveEvent = function(event) {
        $("#ping").html("Last ping: "+event.data);
    }

    dateSocket.onmessage = receiveEvent
})