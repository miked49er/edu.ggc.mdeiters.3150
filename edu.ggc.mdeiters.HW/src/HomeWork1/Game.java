package HomeWork1;

/**
 * Class: Game
 * @author Mike Deiters
 * @version 1.0
 * August 25, 2015
 * ITEC 3150-01
 *
 * Description: The parent class for different types of games
 *
 * Purpose: Set up the basics of games
 */
public abstract class Game {

	private String name;
	private int inventory;
	private int gameId;

	/**
	 * Constructor: Game
	 *
	 */
	public Game() {

	}

	/**
	 * Constructor: Game
	 * @param name
	 * @param inventory
	 * @param gameId
	 */
	public Game( String name, int inventory, int gameId ) {
		this.name = name;
		this.inventory = inventory;
		this.gameId = gameId;
	}

	/**
	 * Method: getName
	 * @return name String of the game name
	 */
	public String getName() {

		return name;
	}

	/**
	 * Method: setName
	 * @param name String of the game name
	 * @return void
	 */
	public void setName( String name ) {

		this.name = name;
	}

	/**
	 * Method: getInventory
	 * @return inventory Int, the number of copies of the game they have
	 */
	public int getInventory() {

		return inventory;
	}

	/**
	 * Method: setInventory
	 * @param inventory Int, the number of copies of the game they have
	 */
	public void setInventory( int inventory ) {

		this.inventory = inventory;
	}

	/**
	 * Method: getGameId
	 * @return gameId Int, the unique id of the game
	 */
	public int getGameId() {

		return gameId;
	}

	/**
	 * Method: setGameId
	 * @param gameId Int, the unique id of the game
	 * @return void
	 */
	public void setGameId( int gameId ) {

		this.gameId = gameId;
	}

	/**
	 * Method: toFile
	 * @return String
	 * Description: Exporting the child's class info to a string
	 */
	public abstract String toFile();
}
