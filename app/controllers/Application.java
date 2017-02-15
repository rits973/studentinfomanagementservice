package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.LegacyWebSocket;
import play.mvc.Result;
import play.mvc.WebSocket;


public class Application extends Controller {
	/*@Singleton

	final ActorRef helloActor;

	@Inject public Application(final ActorSystem system) {
		this.helloActor = system.actorOf(HelloActor.props);
	}

	public CompletionStage<Result> sayHello(final String name) {
		return FutureConverters.toJava(ask(this.helloActor, new SayHello(name), 1000))
				.thenApply(response -> ok((String) response));
	}
}*/

	/*public  LegacyWebSocket<String> pingWs() {

		return new LegacyWebSocket<String>() {

			public void onReady(WebSocket.In<String> in, WebSocket.Out<String> out) {
				final ActorRef pingActor = Akka.system().actorOf(Props.create(Pinger.class, in, out));
				final Cancellable cancellable = Akka.system().scheduler().schedule(Duration.create(1, SECONDS),
						Duration.create(1, SECONDS),
						pingActor,
						"Tick",
						Akka.system().dispatcher(),
						null
						);

				in.onClose(new Callback0() {
					@Override
					public void invoke() throws Throwable {
						cancellable.cancel();
					}
				});
			}

		};
	}*/



	public LegacyWebSocket<JsonNode> pingWs() {
		final Http.Session session = session();
		return new LegacyWebSocket<JsonNode>() {
			// Called when the Websocket Handshake is done.
			@Override
			public void onReady(final WebSocket.In<JsonNode> in, final WebSocket.Out<JsonNode> out){

				// Join the chat room.
				/*try {
					AgrogoChat.join(id,in, out);
				} catch (final Exception ex) {
					ex.printStackTrace();
				}*/
			}
		};
	}

	public  Result pingJs() {
		return ok(views.html.ping.render());
	}

	public Result index() {
		return ok(views.html.index.render(""));
	}
}