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
    private final int HEIGHT = 300;

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
                    new Image("image/Spades_2.png"),
                    new Image("image/Spades_3.png"),
                    new Image("image/Spades_4.png"),
                    new Image("image/Spades_5.png"),
                    new Image("image/Spades_6.png"),
                    new Image("image/Spades_7.png"),
                    new Image("image/Spades_8.png"),
                    new Image("image/Spades_9.png"),
                    new Image("image/Spades_10.png"),
                    new Image("image/Spades_Jack.png"),
                    new Image("image/Spades_Queen.png")
            };
            jumpingImage = new Image("image/Spades_King.png");
            slidingImage = new Image("image/Spades_Ace.png");

            player = new Player(frames, .75, jumpingImage, slidingImage);
            player.setWidth(71);
            player.setNormalHeight(60);
            player.setPositionY(HEIGHT - player.getHeight());
            player.setPositionX(50);
            player.setGravity(90);
            player.setJumpingMedia("sounds/arcade-chirp-08.wav");
            player.setSlidingMedia("sounds/arcade-movement-01.wav");

            root = new Group();
            endlessRunner = new EndlessRunner(WIDTH, HEIGHT, root, player, this);
            endlessRunner.setBgMusic("sounds/lost-in-the-echos-instrumental.mp3");
            endlessRunner.setGameOverSound("sounds/game-over.wav");
            endlessRunner.generateObsticals();
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

            stage.setOnCloseRequest(new EventHandler< WindowEvent >() {
                @Override
                public void handle( WindowEvent event ) {
                    gameThread.interrupt();
                    animationThread.interrupt();
                    stage.close();
                }
            });
        }
        catch ( Exception e ) {

            e.printStackTrace();
        }
    }
}
