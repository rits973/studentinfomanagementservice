@()
$(function() {
    var WS = window['MozWebSocket'] ? MozWebSocket : WebSocket
   @* var dateSocket = new WS("routes.ApplicationController.socket().webSocketURL(request)")*@
    console.log(dateSocket);
    //dateSocket.send("ritesh");
    var receiveEvent = function(event) {
    	console.log(event);
        $("#ping").html("Last ping: "+event.data);
        
    }

    
    dateSocket.onmessage = receiveEvent
    //dateSocket.send("ritesh");
})