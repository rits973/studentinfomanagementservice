package com.thrymr.modules;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import scala.concurrent.duration.Duration;

@Singleton
public class DoSomethingScheduler {

	@Inject
	public DoSomethingScheduler(final ActorSystem system,
			@Named("do-something-actor") final ActorRef doSomethingActor) {
		final int timeDelayFromAppStart = 0;
		final int timeGapInSeconds = 1; //Here you provide the time delay for every run
		system.scheduler().schedule(
				Duration.create(timeDelayFromAppStart, TimeUnit.MILLISECONDS), //Initial delay when system start
				Duration.create(timeGapInSeconds, TimeUnit.SECONDS),     //Frequency delay for next run
				doSomethingActor,
				"message for onreceive method of doSomethingActor",
				system.dispatcher(),
				null);
	}


	/*	@Inject
	public DoSomethingScheduler(final ActorSystem system,
			@Named("do-something-actor") final ActorRef doSomethingActor) {

		Logger.info("Product scheduler: ");




		Long delayInSeconds;

		final Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 16);
		c.set(Calendar.MINUTE, 56);
		c.set(Calendar.SECOND, 0);
		final Date plannedStart = c.getTime();
		final Date now = new Date();
		Date nextRun;

		if(now.after(plannedStart)) {
			c.add(Calendar.DATE, 1);
			nextRun = c.getTime();
		} else {
			nextRun = c.getTime();
		}
		delayInSeconds = (nextRun.getTime() - now.getTime()) / 1000; //To convert milliseconds to seconds.



		final Long timeDelayFromAppStart = c.getTime().getTime()/1000;
		//final int timeGapInSeconds = 1; //Here you provide the time delay for every run
		system.scheduler().schedule(
				Duration.create(0, TimeUnit.MILLISECONDS), //Initial delay when system start
				Duration.create(delayInSeconds, TimeUnit.SECONDS),     //Frequency delay for next run
				doSomethingActor,
				"message for onreceive method of doSomethingActor",
				system.dispatcher(),
				null);*/


	/* FiniteDuration delay = FiniteDuration.create(delayInSeconds, TimeUnit.SECONDS);
		  FiniteDuration frequency = FiniteDuration.create(1, TimeUnit.DAYS);
		  Runnable showTime = new Runnable() {
		              @Override
		              public void run() {
		                  System.out.println("Welcome Ritesh" + new Date());
		              }
		          };

		  Akka.system().scheduler().schedule(delay, frequency, showTime, Akka.system().dispatcher());*/








}
