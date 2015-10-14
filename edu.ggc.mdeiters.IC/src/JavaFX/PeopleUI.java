package JavaFX;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;
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

    private final int HEIGHT = 300;
    private final int WIDTH = 500;
    private final int BUTTON_WIDTH = 150;
    private final int BUTTON_HEIGHT = 30;

    private PersonReader reader;
    private PeopleWriter writer;
    private NewPersonView newPerson;
    private PersonViewList viewList;
    private File saveFile;
    private HashSet<Person> people;

    private Scene primaryScene;
    private Stage mainStage;
    private BorderPane pane;
    private FlowPane sidePane;

    private Button newPersonBtn;
    private Button viewPeople;

    private void setupStage() {

//        try {
//
//            reader.setFile(saveFile);
//            writer.setFile(saveFile);
//
//            // Read the file into a hashset
//
//            people = reader.readPeople();
//        }
//        catch ( ClassNotFoundException cnf ) { // Print Message
//
//            System.out.println(cnf.getMessage());
//        }
//        catch ( FileNotFoundException fnf ) { // Print Message
//
//            System.out.println(fnf.getMessage());
//        }
//        catch ( IOException ioe ) { // Print Message
//
//            System.out.println(ioe.getMessage());
//        }

        // Button widths and heights

        newPersonBtn.setMinWidth(BUTTON_WIDTH);
        newPersonBtn.setMinHeight(BUTTON_HEIGHT);
        viewPeople.setMinWidth(BUTTON_WIDTH);
        viewPeople.setMinHeight(BUTTON_HEIGHT);

        // Create the sidePane

        sidePane.setMaxWidth(200);
        sidePane.setMinHeight(HEIGHT);
        sidePane.setVgap(15);
        sidePane.setPadding(new Insets(15,25,15,25));
        sidePane.setStyle("-fx-background-color:rgb(230,230,230)");
        sidePane.getChildren().add(0,viewPeople);
        sidePane.getChildren().add(1,newPersonBtn);

        pane.setLeft(sidePane);

        if ( people.isEmpty() ) { // If the hashset is empty then display newPerson

            pane.setRight(newPerson.getPane());
        }
        else { // Otherwise display viewList

            pane.setRight(viewList.getPane());
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
        this.sidePane = new FlowPane();
        this.newPersonBtn = new Button("New Person");
        this.viewPeople = new Button("View People");

        this.reader = new PersonReader();
        this.writer = new PeopleWriter();
        this.newPerson = new NewPersonView();
        this.viewList = new PersonViewList();
        this.saveFile = new File(getFile());
        this.people = new HashSet<>();

        setupStage();

        this.primaryScene = new Scene(pane, WIDTH, HEIGHT);
        this.mainStage = new Stage();
        this.mainStage.setScene(primaryScene);
        this.mainStage.setMinWidth(WIDTH);
        this.mainStage.setMinHeight(HEIGHT);
        this.mainStage.setTitle("Contacts");
        this.mainStage.show();
    }
}
