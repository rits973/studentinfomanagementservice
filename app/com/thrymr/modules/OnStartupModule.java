package com.thrymr.modules;

import com.google.inject.AbstractModule;

import play.Logger;

public class OnStartupModule extends AbstractModule{

	@Override
	protected void configure() {
		Logger.info("Custom Module");
		this.bind(OnStartup.class).asEagerSingleton();
		//this.bind(DoSomethingScheduler.class).asEagerSingleton();

	}


}
