package JavaFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Class: PeopleUI
 * @author Mike Deiters
 * @version 1.0
 * October 13, 2015
 * ITEC 3150-01
 *
 * Description: TODO
 *
 * Purpose: TODO
 */
public class PeopleUI extends Application {

    private final int HEIGHT = 400;
    private final int WIDTH = 600;

    private PersonReader reader;
    private PeopleWriter writer;

    private Scene primaryScene;
    private Stage mainStage;
    private BorderPane pane;

    private Button newPersonBtn;
    private Button saveBtn;



    public void start( Stage primaryStage ) throws Exception {

        this.pane = new BorderPane();
        this.newPersonBtn = new Button();
        this.saveBtn = new Button();

        this.primaryScene = new Scene(pane, WIDTH, HEIGHT);
        this.mainStage = new Stage();
        this.mainStage.setMinWidth(WIDTH);
        this.mainStage.setMinHeight(HEIGHT);
        this.mainStage.setTitle("Contacts");
    }
}
