package HomeWork1;

/**
 * Class: Mobile
 * @author Mike Deiters
 * @version 1.0
 * August 25, 2015
 * ITEC 3150-01
 *
 * Description: Specific type of Game system parameters
 *
 * Purpose: Adds the deviceType and sdk fields to the game for mobile
 */
public class Mobile extends Game {

	private String deviceType;
	private double sdk;

	/**
	 * Constructor: Mobile
	 *
	 */
	public Mobile() {
		super();
	}

	/**
	 * Constructor: Mobile
	 * @param name
	 * @param inventory
	 * @param gameId
	 * @param deviceType
	 * @param sdk
	 */
	public Mobile( String name, int inventory, int gameId, String deviceType, double sdk ) {
		super(name, inventory, gameId);
		this.deviceType = deviceType;
		this.sdk = sdk;
	}

	/**
	 * Method: getDeviceType
	 * @return deviceType String phone platform
	 */
	public String getDeviceType() {

		return deviceType;
	}

	/**
	 * Method: setDeviceType
	 * @param deviceType String phone platform
	 * @return void
	 */
	public void setDeviceType( String deviceType ) {

		this.deviceType = deviceType;
	}

	/**
	 * Method: getSdk
	 * @return sdk double platform version number
	 */
	public double getSdk() {

		return sdk;
	}

	/**
	 * Method; setSdk
	 * @param sdk double platform version number
	 * @return void
	 */
	public void setSdk( double sdk ) {

		this.sdk = sdk;
	}

	/**
	 * Method: getSystem
	 * @return String of the type of system
	 */
	@Override
	public String getSystem() {
		return deviceType;
	}

	/**
	 * Method: toString
	 * @return String
	 * Description: Exporting the child's class info into a string to be read by the user
	 */
	@Override
	public String toString() {
		return "" + getName() +
				"\nGame Inventory: " + getInventory() +
				"\nGame ID; " + getGameId() +
				"\nDevice Type: " + deviceType +
				"\nDevice Verison: " + sdk;
	}

	/**
	 * Method: toFile
	 * @return String to right out to a file
	 * Description: Creates a string to write out to a file and later read back in
	 */
	public String toFile() {

		return "Mobile," +
				getName() + "," +
				getInventory() + "," +
				getGameId() + "," +
				deviceType + "," +
				sdk;
	}
}
