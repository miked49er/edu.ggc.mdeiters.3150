import java.util.Date;

/**
 * A runnable that repeatedly prints a greeting.
 */
public class GreetingRunnable2 implements Runnable {
	private static final int REPETITIONS = 10;
	private static final int DELAY = 1000;

	private Greeting greeting;
	private boolean first;

	/**
	 * Constructs the runnable object.
	 * 
	 * @param aGreeting
	 *            the greeting to display
	 */
	public GreetingRunnable2(Greeting g) {
		greeting = g;
		first = true;
	}

	public void run() {
		try {
			for (int i = 1; i <= REPETITIONS; i++) {
				
				if (first)
				{
					greeting.setGreeting("GoodBye World");
					first = false;
				}
				else
				{
					greeting.setGreeting("Hello World");
					first = true;
				}
					
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException exception) {
		}
	}
}
