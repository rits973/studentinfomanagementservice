package com.thrymr.modules;
import javax.inject.Inject;

import com.google.inject.Singleton;

import play.Logger;
import play.api.inject.ApplicationLifecycle;


	@Singleton
	public class OnStartup {

	    @Inject
	    public OnStartup(ApplicationLifecycle application) {
	    	Logger.info("Application start First time");
	    	
	        
	    }
	}

