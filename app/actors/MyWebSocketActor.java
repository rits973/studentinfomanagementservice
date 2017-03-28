package actors;

import org.reactivestreams.Publisher;

import com.fasterxml.jackson.databind.JsonNode;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class MyWebSocketActor extends UntypedActor {



	public static Props props(final ActorRef out) {
		return Props.create(MyWebSocketActor.class, out);
	}

	private final ActorRef out;
	private final Long id;

	public MyWebSocketActor(final Long id,final ActorRef out) {
		this.out = out;
		this.id = id;
	}

	@Override
	public void onReceive(final Object message) throws Exception {
		if (message instanceof String) {
			this.out.tell("I received your message: " + message, this.self());
		}
	}

	public static void join(final Publisher<JsonNode> webSocketIn, final ActorRef userActor){



	}
	public  class  Create{
		private final ActorRef out;
		private final String id;
		public Create(final String id2,final ActorRef out) {
			super();
			this.out = out;
			this.id = id2;
		}


	}
}
