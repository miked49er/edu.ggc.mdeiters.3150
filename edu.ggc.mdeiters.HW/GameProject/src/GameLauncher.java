import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Class: Launcher
 * @author Mike Deiters
 * @version 1.0
 * November 26, 2015
 * ITEC 3150-01
 *
 * Description: Start the endless runner game
 *
 * Purpose: setup and start the game
 */
public class GameLauncher extends Application {

    private final int WIDTH = 600;
    private final int HEIGHT = 250;

    private Stage stage;
    private EndlessRunner endlessRunner;
    private Player player;
    private Group root;
    private GameLoopRunnable gameLoop;
    private AnimationRunnable animationLoop;

    private Image[] frames;
    private Image jumpingImage;
    private Image slidingImage;

    public void restartThreads() {

        try {

            Thread gameThread = new Thread(gameLoop);
            Thread animationThread = new Thread(animationLoop);
            gameThread.start();
            animationThread.start();
        }
        catch ( Exception e ) {

            e.printStackTrace();
        }
    }

    @Override
    public void start( Stage primaryStage ) throws Exception {

        try {
            frames = new Image[] {
                    new Image("assets/player/player-run-1.png"),
                    new Image("assets/player/player-run-2.png"),
                    new Image("assets/player/player-run-3.png"),
                    new Image("assets/player/player-run-4.png"),
            };
            jumpingImage = new Image("assets/player/player-jump.png");
            slidingImage = new Image("assets/player/player-slide.png");

            player = new Player(frames, .75, jumpingImage, slidingImage);
            player.setNormalWidth(16);
            player.setNormalHeight(32);
            player.setSlidingWidth(9);
            player.setSlidingHeight(24);
            player.setPositionY(HEIGHT - player.getHeight());
            player.setPositionX(50);
            player.setPositionY(100);
            player.setGravity(70);
            player.setJumpingMedia("assets/sounds/arcade-chirp-08.wav");
            player.setSlidingMedia("assets/sounds/arcade-movement-01.wav");

            root = new Group();
            endlessRunner = new EndlessRunner(WIDTH, HEIGHT, root, player, this);
            endlessRunner.setBgMusic("assets/sounds/lost-in-the-echos-instrumental.mp3");
            endlessRunner.setGameOverSound("assets/sounds/game-over.wav");
            endlessRunner.generateStage(true);
            endlessRunner.playMusic(true);

            gameLoop = new GameLoopRunnable(endlessRunner, 50);
            animationLoop = new AnimationRunnable(endlessRunner, 46);

            Thread gameThread = new Thread(gameLoop);
            Thread animationThread = new Thread(animationLoop);
            gameThread.start();
            animationThread.start();

            stage = new Stage();
            stage.setTitle("Endless Runner");
            stage.setScene(endlessRunner);
            stage.show();

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle( WindowEvent event ) {
                    gameThread.interrupt();
                    animationThread.interrupt();
                    endlessRunner.playMusic(false);
                    stage.close();
                }
            });
        }
        catch ( Exception e ) {

            e.printStackTrace();
        }
    }
}
