package actors;
/*
import actors.HelloActorProtocol.SayHello;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class HelloActor extends UntypedActor {

	public static Props props = Props.create(HelloActor.class);

	@Override
	public void onReceive(final Object msg) throws Exception {
		if (msg instanceof SayHello) {
			this.sender().tell("Hello, " + ((SayHello) msg).name, this.self());
		}
	}
}*/

import java.text.SimpleDateFormat;
import java.util.Calendar;

import akka.actor.UntypedActor;
import play.mvc.WebSocket;

public class Pinger extends UntypedActor {
	WebSocket.In<String> in;
	WebSocket.Out<String> out;

	public Pinger(final WebSocket.In<String> in, final WebSocket.Out<String> out) {
		this.in = in;
		this.out = out;
	}

	@Override
	public void onReceive(final Object message) {
		if (message.equals("Tick")) {
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			final Calendar cal = Calendar.getInstance();
			this.out.write(sdf.format(cal.getTime()));
		} else {
			this.unhandled(message);
		}
	}
}