package actors;

public class HelloActorProtocol {

	public static class SayHello {
		public final String name;

		public SayHello(final String name) {
			this.name = name;
		}
	}
}