package com.thrymr.modules;

import com.google.inject.AbstractModule;

public class OnStartupModule extends AbstractModule{

	@Override
	protected void configure() {
		this.bind(OnStartup.class).asEagerSingleton();
		this.bind(DoSomethingScheduler.class).asEagerSingleton();

	}


}
