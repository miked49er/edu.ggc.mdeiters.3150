import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Greeting {

	private String theGreeting = "Hello World";
	private Lock greetingLock = new ReentrantLock();

	public String getGreeting()
	{

		return theGreeting;
	}
	
	
	public void setGreeting(String setGreeting)
	{
		greetingLock.lock();

		theGreeting = setGreeting;

		greetingLock.unlock();
	}
	
	
}
