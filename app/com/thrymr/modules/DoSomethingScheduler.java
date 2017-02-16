package com.thrymr.modules;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Cancellable;
import scala.concurrent.duration.Duration;

@Singleton
public class DoSomethingScheduler {

	@Inject
	public DoSomethingScheduler(final ActorSystem system,
			@Named("do-something-actor") final ActorRef doSomethingActor) {
		final Cancellable scheduler;
		final int timeDelayFromAppStart = 0;
		final int timeGapInSeconds = 1; //Here you provide the time delay for every run
		system.scheduler().schedule(
				Duration.create(timeDelayFromAppStart, TimeUnit.MILLISECONDS), //Initial delay when system start
				Duration.create(timeGapInSeconds, TimeUnit.HOURS),     //Frequency delay for next run
				doSomethingActor,
				"message for onreceive method of doSomethingActor",
				system.dispatcher(),
				null);
	}
}