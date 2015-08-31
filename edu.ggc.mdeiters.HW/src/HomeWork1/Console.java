package HomeWork1;

/**
 * Class: Console
 * @author Mike Deiters
 * @version 1.0
 * August 25, 2015
 * ITEC 3150-01
 *
 * Description: Specific type of Game system parameters
 *
 * Purpose: Adds the platform and generation fields to the game for a console
 */
public class Console extends Game {

	private String platform;
	private String generation;

	/**
	 * Constructor: Console
	 *
	 */
	public Console() {
		super();
	}

	/**
	 * Constructor: Console
	 * @param name
	 * @param inventory
	 * @param gameId
	 * @param platform
	 * @param generation
	 */
	public Console( String name, int inventory, int gameId, String platform, String generation ) {
		super(name, inventory, gameId);
		this.platform = platform;
		this.generation = generation;
	}

	/**
	 * Method: getPlatform
	 * @return platform String name of the platform system
	 */
	public String getPlatform() {
		return platform;
	}

	/**
	 * Method: setPlatform
	 * @param platform String name of the platform system
	 * @return void
	 */
	public void setPlatform( String platform ) {
		this.platform = platform;
	}

	/**
	 * Method: getGeneration
	 * @return generation String version number of the platform
	 */
	public String getGeneration() {
		return generation;
	}

	/**
	 * Method: setGeneration
	 * @param generation String version number of the platform
	 * @return void
	 */
	public void setGeneration( String generation ) {
		this.generation = generation;
	}

	/**
	 * Method: getSystem
	 * @return String of the type of system
	 */
	@Override
	public String getSystem() {
		return platform;
	}

	/**
	 * Method: toString
	 * @return String
	 * Description: Exporting the child's class info into a string to be read by the user
	 */
	@Override
	public String toString() {
		return "" + getName() +
				"\n\nGame Inventory:\t" + getInventory() +
				"\nGame ID:\t\t\t" + getGameId() +
				"\n\nPlatform:\t\t\t" + platform +
				"\nGeneration:\t\t" + generation;
	}

	/**
	 * Method: toFile
	 * @return String to right out to a file
	 * Description: Creates a string to write out to a file and later read back in
	 */
	public String toFile() {
		return "Console," +
				getName() + "," +
				getInventory() + "," +
				getGameId() + "," +
				platform + "," +
				generation;
	}
}
