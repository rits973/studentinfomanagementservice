@()
var app = angular.module('groupChatApp',[]);
app.controller('chatController',function chatCtrl($scope){
	$scope.messageArray=[];
	$scope.messageTyped="";
	$scope.userName="";
	$scope.newMsg = function(newMsg,$event){
		if(newMsg!="" && newMsg!=null){	
			 var keycode = ($event.keyCode ? $event.keyCode : $event.which);
		     if(keycode == '13'){
		    	 console.log(newMsg);
		    	 //$scope.messageArray.push(newMsg);
		    	 $scope.sendMessage(newMsg);
		    	 $scope.messageTyped="";
			 
		     }
		};	
	}
	
	
	var WS = window['MozWebSocket'] ? MozWebSocket : WebSocket
			var socketaddress = "@routes.ChatController.chat().webSocketURL(request)";
			    var ws = new WebSocket(socketaddress);
			    var username;
			    ws.onerror = function(error) {
			        console.log('WebSocket Error: ' + error);
			    };
			    ws.onopen = function(event) {
			        $('#status').addClass('text-green').text('You are Connected!');
			    };
			    ws.onmessage = function(event) {
			        var message = event.data;
			        $scope.messageArray.push(angular.fromJson(message));
			    };
			    ws.onclose = function(event) {
			        $('#status').text('Disconnected from WebSocket.');
			    };
			    $('#enterButton').click(function(e) {
			        $('#username-form').addClass('hide');
			        $('#message-form').removeClass('hide');
			        e.preventDefault();
			        username = $('#username').val();
			        $scope.userName=username;
			        console.log(username);
			        ws.send(JSON.stringify({type: 'join', username: username,}));
			        $('#username').val('');
			        return false;
			    });
			    
			   $scope.sendMessage=function(message) {
			        ws.send(JSON.stringify({type: "talk", username: $scope.userName, chatMessage: message}));
			      
			    }
});