import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Class: TODO
 * @author Mike Deiters
 * @version 1.0
 * November 18, 2015
 * ITEC 3150-01
 *
 * Description: TODO
 *
 * Purpose: TODO
 */
public class AnimationPrac extends Application {

    @Override
    public void start( Stage primaryStage ) throws Exception {

        primaryStage.setTitle("Timeline Example");
        Group root = new Group();
        Scene theScene = new Scene(root);
        primaryStage.setScene(theScene);

        Canvas canvas = new Canvas(512,512);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        AnimatedImage cards = new AnimatedImage();
        Image cardBack = new Image("images/Card_Back.png");
        Image ace = new Image("images/Spades_Ace.png");
        Image[] imageArray = {cardBack,ace};
        cards.setFrames(imageArray);
        cards.setDuration(20);

        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            @Override
            public void handle( long now ) {
                double t = (now - startNanoTime) / 1000000000.0;

                double x = 232 + 128 * Math.cos(t);
                double y = 232 + 128 * Math.sin(t);

                // background image clears canvas
                gc.drawImage(cardBack, 0,0);
                gc.drawImage(ace, x, y);
                gc.drawImage(cardBack, 196,196);
                gc.drawImage(cards.getFrame(t), 450, 25);
            }
        }.start();

        primaryStage.show();
    }
}
