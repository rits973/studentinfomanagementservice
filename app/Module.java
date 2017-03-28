import java.time.Clock;

import com.google.inject.AbstractModule;
import com.thrymr.modules.OnStartup;

import play.Logger;
import play.libs.akka.AkkaGuiceSupport;
import services.ApplicationTimer;
import services.AtomicCounter;
import services.Counter;

/**
 * This class is a Guice module that tells Guice how to bind several
 * different types. This Guice module is created when the Play
 * application starts.
 *
 * Play will automatically use any class called `Module` that is in
 * the root package. You can create modules in other locations by
 * adding `play.modules.enabled` settings to the `application.conf`
 * configuration file.
 */
public class Module extends AbstractModule implements AkkaGuiceSupport{

	@Override
	public void configure() {
		Logger.info("Old Module");
		// Use the system clock as the default implementation of Clock
		this.bind(Clock.class).toInstance(Clock.systemDefaultZone());
		// Ask Guice to create an instance of ApplicationTimer when the
		// application starts.
		this.bind(ApplicationTimer.class).asEagerSingleton();
		// Set AtomicCounter as the implementation for Counter.
		this.bind(Counter.class).to(AtomicCounter.class);


		//this.bindActor(DoSomethingActor.class, "do-something-actor");
		//this.bind(DoSomethingScheduler.class).asEagerSingleton();

		this.bind(OnStartup.class).asEagerSingleton();
	}

}
