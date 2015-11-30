import javafx.scene.input.KeyCode;
import sun.misc.Signal;

/**
 * Class: GameLoopRunnable
 * @author Mike Deiters
 * @version 1.0
 * November 19, 2015
 * ITEC 2110
 *
 * Description: Update the game
 *
 * Purpose: Create a runnable thread to update the game
 */
public class GameLoopRunnable implements Runnable {

    private long timeBetweenMoves;
    private Game game;

    /**
     * Constructor: GameLoopRunnable
     * @param game EndlessRunner
     */
    public GameLoopRunnable( Game game, long timeBetweenMoves ) {

        this.game = game;
        this.timeBetweenMoves = timeBetweenMoves;
    }

    /**
     * Method: run
     * Description: refresh the game
     */
    public void run() {

        try {

            while ( true ) {

                Thread.sleep(timeBetweenMoves);

                game.updateGame();

                game.render(timeBetweenMoves);

                if ( game.isGameOver() ) {

                    throw new InterruptedException();
                }
            }
        }
        catch ( InterruptedException ie ) {

            game.gameOver();
        }
    }
}
