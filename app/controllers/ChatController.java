package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import actors.ChatRoomActor;
import actors.Publisher;
import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.stream.javadsl.Flow;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;

public class ChatController extends Controller{
	
	 private ActorSystem actorSystem = ActorSystem.create();
	    private ActorRef chatRoomActor = actorSystem.actorOf(Props.create(ChatRoomActor.class));
	    public static final Publisher<JsonNode> publisher = new Publisher<>();

	   

	    public WebSocket chat() {
	        return WebSocket.Json.accept(requestHeader -> {
	            Source<JsonNode, ?> source = publisher.register();
	            Sink<JsonNode, NotUsed> sink = Sink.actorRef(chatRoomActor, "success");
	            Flow<JsonNode, JsonNode, NotUsed> flow = Flow.fromSinkAndSource(sink, source);
	            return flow;
	        });
	} 
	    
	public Result getIndex(){
		return ok(views.html.index.render(""));
	}

	public Result pingJs(){
		return ok(views.js.js.ping.render());
	}

}
