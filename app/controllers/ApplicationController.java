package controllers;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.stream.Materializer;
import play.mvc.Controller;
import play.mvc.Result;

public class ApplicationController extends Controller{
	private Materializer materializer;
	private ActorSystem actorSystem;
	private ActorRef userParentActor;

	public Result getIndex(){
		return ok(views.html.index.render(""));
	}

	public Result pingJs(){
		return ok(views.js.js.ping.render());
	}



	/*public WebSocket socket() {
		return WebSocket.Json.acceptOrResult(request -> {
			final CompletionStage<Flow<JsonNode, JsonNode, NotUsed>> future = this.wsFutureFlow(request);
			final CompletionStage<Either<Result, Flow<JsonNode, JsonNode, ?>>> stage = future.thenApplyAsync(Either::Right);
			//MyWebSocketActor.join(in,out);
			return stage.exceptionally(this::logException);
		});
	}
	public Either<Result, Flow<JsonNode, JsonNode, ?>> logException(final Throwable throwable) {
		final Result result = Results.internalServerError("error");
		return Either.Left(result);
	}

	public CompletionStage<Flow<JsonNode, JsonNode, NotUsed>> wsFutureFlow(final Http.RequestHeader request) {
		// create an actor ref source and associated publisher for sink
		final Pair<ActorRef, Publisher<JsonNode>> pair = this.createWebSocketConnections();
		final ActorRef webSocketOut = pair.first();
		final Publisher<JsonNode> webSocketIn = pair.second();

		final String id = String.valueOf(request._underlyingHeader().id());
		// Create a user actor off the request id and attach it to the source
		final CompletionStage<ActorRef> userActorFuture = MyWebSocketActor.create(id, webSocketOut);

		// Once we have an actor available, create a flow...
		final CompletionStage<Flow<JsonNode, JsonNode, NotUsed>> stage = userActorFuture
				.thenApplyAsync(userActor -> this.createWebSocketFlow(webSocketIn, userActor));

		return stage;
	}

	public CompletionStage<ActorRef> createUserActor(final String id, final ActorRef webSocketOut) {
		// Use guice assisted injection to instantiate and configure the child actor.
		final long timeoutMillis = 100L;
		return FutureConverters.toJava(
				ask(this.userParentActor, new MyWebSocketActor.Create(id, webSocketOut), timeoutMillis)
				).thenApply(stageObj -> (ActorRef) stageObj);
	}

	public Pair<ActorRef, Publisher<JsonNode>> createWebSocketConnections() {
		// Creates a source to be materialized as an actor reference.

		// Creating a source can be done through various means, but here we want
		// the source exposed as an actor so we can send it messages from other
		// actors.
		final Source<JsonNode, ActorRef> source = Source.actorRef(10, OverflowStrategy.dropTail());

		// Creates a sink to be materialized as a publisher.  Fanout is false as we only want
		// a single subscriber here.
		final Sink<JsonNode, Publisher<JsonNode>> sink = Sink.asPublisher(AsPublisher.WITHOUT_FANOUT);

		// Connect the source and sink into a flow, telling it to keep the materialized values,
		// and then kicks the flow into existence.
		final Pair<ActorRef, Publisher<JsonNode>> pair = source.toMat(sink, Keep.both()).run(this.materializer);
		return pair;
	}
	public Flow<JsonNode, JsonNode, NotUsed> createWebSocketFlow(final Publisher<JsonNode> webSocketIn, final ActorRef userActor) {
		// http://doc.akka.io/docs/akka/current/scala/stream/stream-flows-and-basics.html#stream-materialization
		// http://doc.akka.io/docs/akka/current/scala/stream/stream-integrations.html#integrating-with-actors

		// source is what comes in: browser ws events -> play -> publisher -> userActor
		// sink is what comes out:  userActor -> websocketOut -> play -> browser ws events
		final Sink<JsonNode, NotUsed> sink = Sink.actorRef(userActor, new Status.Success("success"));
		final Source<JsonNode, NotUsed> source = Source.fromPublisher(webSocketIn);
		final Flow<JsonNode, JsonNode, NotUsed> flow = Flow.fromSinkAndSource(sink, source);

		// Unhook the user actor when the websocket flow terminates
		// http://doc.akka.io/docs/akka/current/scala/stream/stages-overview.html#watchTermination
		return flow.watchTermination((ignore, termination) -> {
			termination.whenComplete((done, throwable) -> {
				MyWebSocketActor.join(webSocketIn, userActor);
				this.actorSystem.stop(userActor);
			});

			return NotUsed.getInstance();
		});
	}*/


}
