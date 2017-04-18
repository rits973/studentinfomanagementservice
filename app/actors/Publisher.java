/**
 * 
 */
package actors;

/**
 * @author Ritesh
 *
 */

import akka.actor.ActorRef;
import akka.stream.OverflowStrategy;
import akka.stream.javadsl.Source;

import java.util.HashSet;
import java.util.Set;

public class Publisher<T> {
    public final Set<ActorRef> actorRefs = new HashSet<>();

    public Source<T, ?> register() {
        Source<T, ?> source = Source.<T>actorRef(256, OverflowStrategy.dropHead())
                .mapMaterializedValue(actorRef -> {
                    Publisher.this.actorRefs.add(actorRef);
                    return actorRef;
                })
                .watchTermination((actorRef, termination) -> {
                    termination.whenComplete((done, cause) -> Publisher.this.actorRefs.remove(actorRef));
                    return null;
                });
        return source;

    }

    public void broadcast(final T message) {
        for (ActorRef actorRef: this.actorRefs) {
            actorRef.tell(message, ActorRef.noSender());
        }
    }
}