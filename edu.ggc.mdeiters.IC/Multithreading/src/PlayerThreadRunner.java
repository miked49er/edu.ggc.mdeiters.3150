/**Class: PlayerThreadRunner
 * @author Your Name 
 * @version 1.0 
 * Course : ITEC 3150 Spring 2012
 * Written: Oct 11, 2012  
 * Purpose: The PlayerThreadRunner contains the main method, creates 
 * 2 Runnables, creates 2 threads and starts these threads.
 * The AddWeaponsRunnable will add weapons to a Player
 * The RemoveWeaponsRunnable will remove weapons from a player, when s/he has enough weapons
 * 
 */
public class PlayerThreadRunner
{

	//number of iterations the thread will complete
	private static int ITERATIONS = 25;

	public static void main(String args[])
	{
	
		Player p = new Player();
		AddWeaponsRunnable awr = new AddWeaponsRunnable(p, ITERATIONS);
		RemoveWeaponsRunnable rwr = new RemoveWeaponsRunnable(p, ITERATIONS);
		Thread t1 = new Thread(awr);
		Thread t2 = new Thread(rwr);
		t1.start();
		t2.start();
	}
}
