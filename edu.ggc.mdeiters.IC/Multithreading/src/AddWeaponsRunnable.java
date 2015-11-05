import java.util.Random;
/**Class: AddWeaponsRunnable
 * @author Your Name 
 * @version 1.0 
 * Course : ITEC 3150 Spring 2012
 * Written: Oct 11, 2012  
 * 
 * This class ? The AddWeaponsRunnable class adds a randomly generated number
 * of weapons (from 0-4 inclusive) to a player
 * 
 * Purpose: ? The class will run for as many iterations as the user provides for the parameter.
 * While it is running, it will call the addWeapons() method from Player that will add to the 
 * number of weapons that a player has, and then print a line stating the number of weapons 
 * added as well as the total number of weapons that a player has.
 * The method will only add something when the
 * ArrayList's size is less or equal to 5.
 * Then the program will sleep for the DELAY specified.
 */
public class AddWeaponsRunnable implements Runnable
{
	private int iterations;
	private Player p;
	private Random r;
	private static final int DELAY = 400;

	/**
	 * The AddWeaponsRunnable Constructor
	 * @param aPlayer
	 * @param aIterations
	 */
	public AddWeaponsRunnable(Player aPlayer, int aIterations)
	{
		iterations = aIterations;
		p = aPlayer;
		r = new Random();
	}

	/**
	 * The program will run for as many iterations as the user provides for the parameter.
	 * While it is running, it will call the addWeapons() method from Player that will add to the 
	 * number of weapons that a player has, and then print a line stating the number of weapons 
	 * added as well as the total number of weapons that a player has.
	 * The method will only add something when the
	 * ArrayList's size is less or equal to 5.
	 * Then the program will sleep for the DELAY specified.
	 */
	public void run()
	{
		try
		{
			for (int i = 1; i <= iterations; i++)
			{
				//randomly generate a int from 0-4 
				int weaponsToAdd = r.nextInt(5);
				p.addWeapons(weaponsToAdd);
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException exception) {}
	}
}
