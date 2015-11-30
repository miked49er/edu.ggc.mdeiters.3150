/**
 * Class: GameOverException
 * @author Mike Deiters
 * @version 1.0
 * November 26, 2015
 * ITEC 3150-01
 *
 * Description: Exception to end the game
 *
 * Purpose: End the game
 */
public class GameOverException extends Exception {

    /**
     * Constructor: GameOverException
     */
    public GameOverException() {
    }

    /**
     * Constructor: GameOverException
     * @param message
     */
    public GameOverException( String message ) {
        super(message);
    }
}
