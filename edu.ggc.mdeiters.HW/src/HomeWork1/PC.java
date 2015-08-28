package HomeWork1;

/**
 * Class: PC
 * @author Mike Deiters
 * @version 1.0
 * August 25, 2015
 * ITEC 3150-01
 *
 * Description: Specific type of Game system parameters
 *
 * Purpose: Add the memory and disk requirement fields to the game for a pc
 */
public class PC extends Game {

	private int minMemoryReq;
	private int minDiskReq;

	/**
	 * Constructor: PC
	 *
	 */
	public PC() {
		super();
	}

	/**
	 * Constructor: PC
	 * @param name
	 * @param inventory
	 * @param gameId
	 * @param minMemoryReq
	 * @param minDiskReq
	 */
	public PC( String name, int inventory, int gameId, int minMemoryReq, int minDiskReq ) {
		super(name, inventory, gameId);
		this.minMemoryReq = minMemoryReq;
		this.minDiskReq = minDiskReq;
	}

	/**
	 * Method: getMinMemoryReq
	 * @return minMemoryReq Int arbitrary number that represents the amount of RAM necessary to run game
	 */
	public int getMinMemoryReq() {
		return minMemoryReq;
	}

	/**
	 * Method: setMinMemoryReq
	 * @param minMemoryReq Int arbitrary number that represents the amount of RAM necessary to run game
	 * @return void
	 */
	public void setMinMemoryReq( int minMemoryReq ) {
		this.minMemoryReq = minMemoryReq;
	}

	/**
	 * Method getMinDiskReq
	 * @return minDiskReq Int arbitrary number that represents the amount of Disk space necessary to install the game
	 */
	public int getMinDiskReq() {
		return minDiskReq;
	}

	/**
	 * Method: setMinDiskReq
	 * @param minDiskReq Int arbitrary number that represents the amount of Disk space necessary to install the game
	 */
	public void setMinDiskReq( int minDiskReq ) {
		this.minDiskReq = minDiskReq;
	}

	/**
	 * Method: toFile
	 * @return String to right out to a file
	 * Description: Creates a string to write out to a file and later read back in
	 */
	public String toFile() {
		return "PC," +
				getName() + "," +
				getInventory() + "," +
				getGameId() + "," +
				minMemoryReq + "," +
				minDiskReq;
	}

}
