package com.thrymr.modules;

import com.google.inject.AbstractModule;

public class OnStartupModule extends AbstractModule{

	@Override
	protected void configure() {
		 bind(OnStartup.class).asEagerSingleton();
	}

}
