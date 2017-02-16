package com.thrymr.modules;

import com.google.inject.AbstractModule;

import play.libs.akka.AkkaGuiceSupport;

public class SchedulerModule extends AbstractModule implements AkkaGuiceSupport{

	@Override
	protected void configure() {
		this.bindActor(DoSomethingActor.class, "do-something-actor");
		this.bind(DoSomethingScheduler.class).asEagerSingleton();
	}

}
