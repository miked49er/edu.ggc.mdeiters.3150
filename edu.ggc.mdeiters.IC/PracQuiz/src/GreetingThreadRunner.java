/**
 * This program runs two greeting threads in parallel.
 */
public class GreetingThreadRunner {

	public static void main(String[] args) {
		
		Greeting g = new Greeting();
		GreetingRunnable1 r1 = new GreetingRunnable1(g);
		GreetingRunnable2 r2 = new GreetingRunnable2(g);
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
	}
}
