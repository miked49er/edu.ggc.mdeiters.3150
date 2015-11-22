import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
public class MouseEvent extends Application {

    @Override
    public void start( Stage stage ) throws Exception {

        stage.setTitle("Click the Target!");

        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        Canvas canvas = new Canvas(500, 500);

        root.getChildren().add(canvas);

        Circle targetData = new Circle(100, 100, 32);
        IntValue points = new IntValue(0);

        scene.setOnMouseClicked(new EventHandler< javafx.scene.input.MouseEvent >() {
            @Override
            public void handle( javafx.scene.input.MouseEvent event ) {
                if ( targetData.contains(event.getX(), event.getY()) ) {

                    double x = 50 + 400 * Math.random();
                    double y = 50 + 400 * Math.random();
                    targetData.setCenterX(x);
                    targetData.setCenterY(y);
                    points.value++;
                }
                else {
                    points.value = 0;
                }
            }
        });

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Font font = Font.font("Helvetica", FontWeight.BOLD, 24);
        gc.setFont(font);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);

        Image bullseye = new Image("images/Card_Back.png");

        new AnimationTimer() {
            @Override
            public void handle( long now ) {

                // Clear the canvas
                gc.setFill(new Color(0.85,0.85,1.0,1.0));
                gc.fillRect(0,0,512,512);

                gc.drawImage(bullseye,
                        targetData.getCenterX() - targetData.getRadius(),
                        targetData.getCenterY() - targetData.getRadius()
                );

                gc.setFill(Color.BLUE);

                String pointsText = "Points: " + points.value;
                gc.fillText(pointsText,360,36);
                gc.strokeText(pointsText, 360,36);
            }
        }.start();

        stage.show();

    }
}
