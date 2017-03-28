package com.thrymr.modules;

import com.google.inject.AbstractModule;

import play.Logger;
import play.libs.akka.AkkaGuiceSupport;

public class SchedulerModule extends AbstractModule implements AkkaGuiceSupport{

	@Override
	protected void configure() {
		Logger.info("Custom Scheduler");
		this.bindActor(DoSomethingActor.class, "do-something-actor");
		this.bind(DoSomethingScheduler.class).asEagerSingleton();
	}

}
