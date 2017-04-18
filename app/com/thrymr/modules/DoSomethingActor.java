package com.thrymr.modules;

import akka.actor.UntypedActor;
import play.Logger;

public class DoSomethingActor extends UntypedActor{

	@Override
	public void onReceive(final Object message) throws Throwable {
		//Logger.info("Write your crone task or simply call your method here that perform your task"+message);
		//Logger.info("----"+Constants.TEST);


	}

}
