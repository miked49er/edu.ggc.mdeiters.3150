import java.util.Date;

/**
 * A runnable that repeatedly prints a greeting.
 */
public class GreetingRunnable1 implements Runnable {
	private static final int REPETITIONS = 10;
	private static final int DELAY = 1000;

	private Greeting greeting;

	/**
	 * Constructs the runnable object.
	 * 
	 * @param aGreeting
	 *            the greeting to display
	 */
	public GreetingRunnable1(Greeting g) {
		greeting = g;
	}

	public void run() {
		try {
			for (int i = 1; i <= REPETITIONS; i++) {
				Date now = new Date();
				System.out.println(now + " " + greeting.getGreeting());
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException exception) {
		}
	}
}
