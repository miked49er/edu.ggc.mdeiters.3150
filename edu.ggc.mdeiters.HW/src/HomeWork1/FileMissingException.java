package HomeWork1;

/** Class: FileMissingException
 * @author Mike Deiters
 * @version 1.0
 * August 25, 2015
 * ITEC 3150-01
 *
 *
 * This will accept an error message to display to the user
 *
 * Purpose is create a new exception that will be thrown instead of FileNotFoundException
 *
 */
public class FileMissingException extends RuntimeException {

	public FileMissingException() {

		super();
	}

	/**
	 * @param arg0
	 */
	public FileMissingException( String arg0 ) {

		super(arg0);
	}

}
