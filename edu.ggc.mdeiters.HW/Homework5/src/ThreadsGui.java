import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Class: ThreadsGui
 * @author Mike Deiters
 * @version 1.0
 * November 13, 2015
 * ITEC 3150-01
 *
 * Description: Gui with 2 threads moving objects around
 *
 * Purpose: Setup the Gui
 */
public class ThreadsGui extends Application {

    private final int WIDTH = 600;
    private final int HEIGHT = 400;

    private Stage mainStage;
    private Scene primaryScene;
    private Pane pane;
    private CircleRunnable circle;
    private Thread thread1;

    @Override
    public void start( Stage primaryStage ) throws Exception {

        circle = new CircleRunnable(120,52,25,80,WIDTH,HEIGHT);
        thread1 = new Thread(circle);
        pane = new Pane();
        pane.getChildren().add(circle.getCircle());

        thread1.start();

        primaryScene = new Scene(pane,WIDTH,HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(primaryScene);
        mainStage.setTitle("Thread Movement");
        mainStage.show();

        mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle( WindowEvent event ) {

                thread1.interrupt();
                mainStage.close();
                System.exit(0);
            }
        });
    }
}
