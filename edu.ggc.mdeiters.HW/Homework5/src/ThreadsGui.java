import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
    private BorderPane primaryPane;
    private Pane pane;
    private CircleRunnable circle;
    private RandomGenRunnable rand;
    private Thread thread1;
    private Thread thread2;

    /**
     * Method: start
     * @param primaryStage
     * @throws Exception
     * Description: Setup the stage and start the treads
     */
    public void start( Stage primaryStage ) throws Exception {

        circle = new CircleRunnable(120, 52, 25, 80, WIDTH, HEIGHT - 100);
        thread1 = new Thread(circle);
        pane = new Pane();

        // Add circle to pane
        // Add a 5px black bottom border to pane
        // Set the min height to the stage height - 98

        pane.getChildren().add(circle.getCircle());
        pane.setStyle("-fx-border-color: black; -fx-bottom-border: 5px");
        pane.setMinHeight(HEIGHT - 98);

        rand = new RandomGenRunnable(WIDTH, 80, 100);
        thread2 = new Thread(rand);

        // Place pane and rand on the borderpane

        primaryPane = new BorderPane();
        primaryPane.setTop(pane);
        primaryPane.setBottom(rand);

        // Start the treads

        thread1.start();
        thread2.start();

        primaryScene = new Scene(primaryPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setMaxWidth(WIDTH);
        mainStage.setScene(primaryScene);
        mainStage.setTitle("Thread Movement");
        mainStage.show();

        // When the stage is closed

        mainStage.setOnCloseRequest(new EventHandler< WindowEvent >() {
            @Override
            public void handle( WindowEvent event ) {

                // Interrupt the treads

                thread1.interrupt();
                thread2.interrupt();

                // Close the stage and exit

                mainStage.close();
                System.exit(0);
            }
        });
    }
}
