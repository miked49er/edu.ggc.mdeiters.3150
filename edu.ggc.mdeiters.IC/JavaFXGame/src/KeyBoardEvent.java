import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Class: KeyBoardEvent
 * @author Mike Deiters
 * @version 1.0
 * November 18, 2015
 * ITEC 3150-01
 *
 * Description: TODO
 *
 * Purpose: TODO
 */
public class KeyBoardEvent extends Application {

    @Override
    public void start( Stage stage ) throws Exception {

        stage.setTitle("Keyboard Example");

        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        Canvas canvas = new Canvas(512 - 64, 256);
        root.getChildren().add(canvas);

        ArrayList< String > input = new ArrayList<>();

        scene.setOnKeyPressed(new EventHandler< KeyEvent >() {
            @Override
            public void handle( KeyEvent event ) {
                String code = event.getCode().toString();

                // only add once... prevent duplicates
                if ( !input.contains(code) ) {
                    input.add(code);
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler< KeyEvent >() {
            @Override
            public void handle( KeyEvent event ) {
                String code = event.getCode().toString();
                input.remove(code);
            }
        });

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Image card = new Image("images/Card_Back.png");
        Image left = new Image("images/twit.png", 200, 200, true, true);
        Image right = new Image("images/mobile-nations.png", 200, 200, true, true);

        new AnimationTimer() {
            @Override
            public void handle( long now ) {
                // Clear the canvas
                gc.clearRect(0, 0, 512, 512);

                if ( input.contains("LEFT") ) {
                    gc.drawImage(left, 64, 64);
                }
                else {
                    gc.drawImage(card, 64, 64);
                }
                if ( input.contains("RIGHT") ) {
                    gc.drawImage(right, 256, 64);
                }
                else {
                    gc.drawImage(card, 256, 64);
                }
            }
        }.start();

        stage.show();
    }
}
