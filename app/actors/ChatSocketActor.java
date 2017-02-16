package actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class ChatSocketActor extends UntypedActor {

	private final ActorRef out;
	private final Long userId;
	private final ActorRef chatSocketRouter;


	public ChatSocketActor(final ActorRef out, final ActorRef chatSocketRouter, final Long userId) {
		this.out = out;
		this.userId = userId;
		this.chatSocketRouter = chatSocketRouter;
	}

	public static Props props(final ActorRef out, final ActorRef chatSocketRouter, final Long userId) {
		return Props.create(ChatSocketActor.class, out, chatSocketRouter, userId);
	}

	@Override
	public void onReceive(final Object arg0) throws Throwable {
		// TODO Auto-generated method stub

	}

	// Add methods here handling each chat connection...

}