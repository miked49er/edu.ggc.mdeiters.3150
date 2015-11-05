import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**Class: Player
 * @author Your Name 
 * @version 1.0
 * Course : ITEC 3150 Spring 2012
 * Written: Oct 11, 2012 
 *
 *
 * This class ? The Player class holds the information about a Player
 * and the number of weapons s/he has. The class is thread safe, and includes a lock 
 * and an enoughWeaponsCondition. The number of weapons a play holds
 * cannot drop below 0.
 *
 * Purpose: ? This class creates a new player with 0 weapons, and allows weapons to be added and removed from the
 * player's number of weapons.
 */

public class Player {
    // int that holds the number of weapons the Player has
    private int numOfWeapons;
    // A lock for the Player
    private Lock playerLock;
    // Lock condition for the Player to determine if there are enough weapons
    private Condition enoughWeaponsCondition;

    /**
     * The Player class constructor
     *
     */
    public Player() {
        numOfWeapons = 0;
        playerLock = new ReentrantLock();
        enoughWeaponsCondition = playerLock.newCondition();
    }

    public void addWeapons( int weaponsToAdd ) throws InterruptedException {
        playerLock.lock();
        try {
            numOfWeapons += weaponsToAdd;
            System.out.println("Added " + weaponsToAdd + " weapons. Player has " + numOfWeapons + " weapons.");
            enoughWeaponsCondition.signalAll();
        }
        finally {
            playerLock.unlock();
        }
    }

    public void removeWeapons( int weaponsToRemove ) throws InterruptedException {
		playerLock.lock();
        try {
            while (numOfWeapons < weaponsToRemove) {
                System.out.println("Waiting for more weapons to be added. Trying to remove " + weaponsToRemove + " weapons");
                enoughWeaponsCondition.await();
            }
            numOfWeapons -= weaponsToRemove;
            System.out.println("Removed " + weaponsToRemove + " weapons. Player has " + numOfWeapons + " weapons.");
        }
        finally {
            playerLock.unlock();
        }
    }

    /**
     * Method: getNumOfWeapons
     * @return numOfWeapons int
     */
    public int getNumOfWeapons() {
        return numOfWeapons;
    }
}
