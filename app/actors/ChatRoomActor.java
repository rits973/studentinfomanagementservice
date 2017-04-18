/**
 * 
 */
package actors;

import static controllers.ChatController.publisher;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author Ritesh
 *
 */

import akka.actor.UntypedActor;
import play.libs.Json;

public class ChatRoomActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof JsonNode) {
            JsonNode jsonMessage = (JsonNode) message;
            String type = jsonMessage.get("type").textValue();

            switch (type) {

                case "join":
                    String joinedUser = jsonMessage.get("username").asText();
                    JsonNode joinToClient =
                            Json.newObject()
                                    .put("type", "joined")
                                    .put("username", joinedUser);
                    publisher.broadcast(joinToClient);
                    break;

                case "talk":
                    String talkedUser = jsonMessage.get("username").asText();
                    String chatMessage = jsonMessage.get("chatMessage").asText();
                    JsonNode talkToClient =
                            Json.newObject()
                                    .put("type", "talked")
                                    .put("username", talkedUser)
                                    .put("chatMessage", chatMessage);
                    publisher.broadcast(talkToClient);
                    break;

                default:
                    System.out.println("Json Error: type is not allowed");
                    break;
            }
        } else {
            System.out.println("chatRoomActor received not Json");
        }
    }
}