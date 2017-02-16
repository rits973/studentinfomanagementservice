package actors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class ChatSocketRouter extends UntypedActor {

	public ChatSocketRouter() {}


	// Stores userIds to websockets
	private final HashMap<Long, List<ActorRef>> senders = new HashMap<>();

	private void addSender(final Long userId, final ActorRef actorRef){
		if (this.senders.containsKey(userId)) {
			final List<ActorRef> actors = this.senders.get(userId);
			actors.add(actorRef);
			this.senders.replace(userId, actors);
		} else {
			final List<ActorRef> l = new ArrayList<>();
			l.add(actorRef);
			this.senders.put(userId, l);
		}
	}


	private void removeSender(final ActorRef actorRef){
		for (final List<ActorRef> refs : this.senders.values()) {
			refs.remove(actorRef);
		}
	}

	@Override
	public void onReceive(final Object message) throws Exception {
		final ActorRef sender = this.getSender();

		// Handle messages sent to this 'router' here

		/*if (message instanceof "") {
			final UserMessage userMessage = (UserMessage) message;
			this.addSender(userMessage.userId, userMessage.actorRef);
			// Watch sender so we can detect when they die.
			this.getContext().watch(sender);
		} else if (message instanceof Terminated) {
			// One of our watched senders has died.
			this.removeSender(sender);

		} else {*/
		this.unhandled(message);
		//}
	}
}
