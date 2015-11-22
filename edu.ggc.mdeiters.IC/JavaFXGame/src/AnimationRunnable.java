import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

/**
 * Class: AnimationRunnable
 * @author Mike Deiters
 * @version 1.0
 * November 21, 2015
 * ITEC 3150-01
 *
 * Description: Animate the player
 *
 * Purpose: Create a thread to animate the player
 */
public class AnimationRunnable implements Runnable {

    private final long startNanoTime = System.nanoTime();

    private long timeBetweenMoves;
    private Game game;
    private GraphicsContext gc;
    private Sprite player;

    /**
     * Constructor: AnimationRunnable
     * @param game Game
     */
    public AnimationRunnable( Game game, long timeBetweenMoves ) {
        this.timeBetweenMoves = timeBetweenMoves;
        this.game = game;
        this.gc = game.getGc();
        this.player = game.getPlayer();
    }

    /**
     * Method: run
     * Description: Handle the player animation
     */
    public void run() {

        try {

            while ( true ) {

                Thread.sleep(timeBetweenMoves);
                double t = System.nanoTime() - startNanoTime / 1000000000;

                player.render(gc, t);
            }
        }
        catch ( InterruptedException ie ) {
            // Thread interrupted
        }
    }

//    /**
//     * Method: handle
//     * @param now long
//     * Description: Handle the player animation
//     */
//    public void handle( long now ) {
//        double t = ( now - startNanoTime ) / 100000000.0;
//
//        player.render(gc, t);
//    }
}
