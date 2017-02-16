package controllers;

import javax.inject.Inject;
import javax.inject.Named;

import akka.actor.ActorRef;
import akka.stream.Materializer;
import akka.stream.javadsl.Flow;
import play.mvc.Controller;
import play.mvc.WebSocket;


/*public class Application extends Controller {
	@Singleton

	final ActorRef helloActor;

	@Inject public Application(final ActorSystem system) {
		this.helloActor = system.actorOf(HelloActor.props);
	}

	public CompletionStage<Result> sayHello(final String name) {
		return FutureConverters.toJava(ask(this.helloActor, new SayHello(name), 1000))
				.thenApply(response -> ok((String) response));
	}
}







	public LegacyWebSocket<JsonNode> pingWs() {
		final Http.Session session = session();
		return new LegacyWebSocket<JsonNode>() {
			// Called when the Websocket Handshake is done.
			@Override
			public void onReady(final WebSocket.In<JsonNode> in, final WebSocket.Out<JsonNode> out){

				// Join the chat room.
				try {
					AgrogoChat.join(id,in, out);
				} catch (final Exception ex) {
					ex.printStackTrace();
				}
			}
		};
	}

	public  Result pingJs() {
		return ok(views.js.js.ping.render());
	}

	public Result index() {
		return ok(views.html.index.render(""));
	}
}*/




public class ApplicationController extends Controller {
	@Inject
	private Materializer materializer;
	private final ActorRef chatSocketRouter;

	@Inject
	public ApplicationController(@Named("chatSocketRouter") final ActorRef chatInjectedActor) {
		this.chatSocketRouter = chatInjectedActor;
	}




	public WebSocket socket() {
		return WebSocket.Text.accept(requestHeader -> {
			final Flow<String, String, ?> flow = Flow.create()
					.map(s -> s.toUpperCase());
			return flow;
		});
	}



















	/*public WebSocket chatSocket() {

		return WebSocket.Json.acceptOrResult(request -> {

			// Create a function to be run when we initialise a flow.
			// A flow basically links actors together.
			final AbstractFunction1<ActorRef, Props> getWebSocketActor = new AbstractFunction1<ActorRef, Props>() {

				@Override
				public Props apply(final ActorRef arg0) {
					// TODO Auto-generated method stub
					return null;
				}
				@Override
				public Props apply(final ActorRef connectionProperties) {

					// We use the ActorRef provided in the param above to make some properties.
					// An ActorRef is a fancy word for thread reference.
					// The WebSocketActor manages the web socket connection for one user.
					// WebSocketActor.props() means "make one thread (from the WebSocketActor) and return the properties on how to reference it".
					// The resulting Props basically state how to construct that thread.
					final Props properties = ChatSocketActor.props(connectionProperties, Application.this.chatSocketRouter, userId);

					// We can have many connections per user. So we need many ActorRefs (threads) per user. As you can see from the code below, we do exactly that. We have an object called
					// chatSocketRouter which holds a Map of userIds -> connectionsThreads and we "tell"
					// it a lightweight object (UserMessage) that is made up of this connecting user's ID and the connection.
					// As stated above, Props are basically a way of describing an Actor, or dumbed-down, a thread.

					// In this line, we are using the Props above to
					// reference the ActorRef we've just created above
					final ActorRef anotherUserDevice = actorSystem.actorOf(properties);
					// Create a lightweight object...
					final UserMessage routeThisUser = new UserMessage(userId, anotherUserDevice);
					// ... to tell the thread that has our Map that we have a new connection
					// from a user.
					Application.this.chatSocketRouter.tell(routeThisUser, ActorRef.noSender());

					// We return the properties to the thread that will be managing this user's connection
					return properties;
				}
			};

			final Flow<JsonNode, JsonNode, ?> jsonNodeFlow =
					ActorFlow.<JsonNode, JsonNode>actorRef(getWebSocketActor,
							100,
							OverflowStrategy.dropTail(),
							actorSystem,
							this.materializer).asJava();

			final F.Either<Result, Flow<JsonNode, JsonNode, ?>> right = F.Either.Right(jsonNodeFlow);
			return CompletableFuture.completedFuture(right);
		});
	}*/

	// Return this whenever we want to reject a
	// user from connecting to a websocket
	/*private CompletionStage<F.Either<Result, Flow<JsonNode, JsonNode, ?>>> forbiddenResult(final String msg) {
		final Result forbidden = Results.forbidden(msg);
		final F.Either<Result, Flow<JsonNode, JsonNode, ?>> left = F.Either.Left(forbidden);
		return CompletableFuture.completedFuture(left);
	}
	 */

	// Make a chat websocket for a user


}
