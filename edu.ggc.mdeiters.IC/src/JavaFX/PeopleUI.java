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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;

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
    private final int BUTTON_WIDTH = 150;
    private final int BUTTON_HEIGHT = 40;

    private PersonReader reader;
    private PeopleWriter writer;
    private File saveFile;
    private HashSet< Person > people;

    private Scene primaryScene;
    private Stage mainStage;
    private BorderPane pane;
    private Pane sidePane;

    private Button newPersonBtn;
    private Button viewPeople;

    private void setupStage() {

        try {

            reader.setFile(saveFile);
            writer.setFile(saveFile);

            // Read the file into a hashset

            people = reader.readPeople();

            // Button widths and heights

            newPersonBtn.setMinWidth(BUTTON_WIDTH);
            newPersonBtn.setMinHeight(BUTTON_HEIGHT);
            viewPeople.setMinWidth(BUTTON_WIDTH);
            viewPeople.setMinHeight(BUTTON_HEIGHT);

            sidePane.setMaxWidth(200);
            sidePane.getChildren().addAll(viewPeople, newPersonBtn);

        }
        catch ( ClassNotFoundException cnf ) { // Print Message

            System.out.println(cnf.getMessage());
        }
        catch ( FileNotFoundException fnf ) { // Print Message

            System.out.println(fnf.getMessage());
        }
        catch ( IOException ioe ) { // Print Message

            System.out.println(ioe.getMessage());
        }
    }

    private String getFile() {

        String OS = System.getProperty("os.name");

        if ( OS.startsWith("Windows") ) {

            return "JavaFX\\data.dat";
        }
        else {

            return "JavaFX/data.dat";
        }
    }

    public void start( Stage primaryStage ) throws Exception {

        this.pane = new BorderPane();
        this.sidePane = new Pane();
        this.newPersonBtn = new Button("New Person");
        this.viewPeople = new Button("View People");

        this.reader = new PersonReader();
        this.writer = new PeopleWriter();
        this.saveFile = new File(getFile());
        this.people = new HashSet<>();

        setupStage();

        this.primaryScene = new Scene(pane, WIDTH, HEIGHT);
        this.mainStage = new Stage();
        this.mainStage.setMinWidth(WIDTH);
        this.mainStage.setMinHeight(HEIGHT);
        this.mainStage.setTitle("Contacts");
        this.mainStage.show();
    }
}
