import java.util.Random;
/**Class: RemoveWeaponsRunnable
 * @author Your Name 
 * @version 1.0 
 * Course : ITEC 3150 Spring 2012
 * Written: Oct 11, 2012  
 * 
 * This class ? The RemoveWeaponsRunnable class removes a randomly generated number
 * of weapons (from 0-4 inclusive) from a player
 * 
 * Purpose - 	The program will run for as many iterations as the user provides for the parameter.
 * While it is running, it will call the removeWeapons() method from Player that will remove from the 
 * number of weapons that a player has, and then print a line stating the number of weapons 
 * removed as well as the total number of weapons that a player has.
 * The method will only remove weapons when the player owns enough weapons to allow this action
 * Then the program will sleep for the DELAY specified.
 */

public class RemoveWeaponsRunnable implements Runnable
{
	private Player p;
	private Random r;
	private int iterations;
	private static final int DELAY = 700;

	/**
	 * Constructor for the RemoveWeaponsRunnable class
	 * @param aPlayer
	 * @param aIterations
	 */
	public RemoveWeaponsRunnable(Player aPlayer, int aIterations)
	{
		iterations = aIterations;
		p = aPlayer;
		r = new Random();
	}

	/**
	 * The program will run for as many iterations as the user provides for the parameter.
	 * While it is running, it will call the removeWeapons() method from Player that will remove from the 
	 * number of weapons that a player has, and then print a line stating the number of weapons 
	 * removed as well as the total number of weapons that a player has.
	 * The method will only remove weapons when the player owns enough weapons to allow this action
	 * Then the program will sleep for the DELAY specified.
	 */
	public void run()
	{
		try
		{
			for (int i = 1; i <= iterations; i++)
			{
				//randomly generate a int from 0-4
				int weaponsToRemove = r.nextInt(5);
				p.removeWeapons(weaponsToRemove);
				Thread.sleep(DELAY);
			}
		}
		catch (InterruptedException exception) {}
	}
}
