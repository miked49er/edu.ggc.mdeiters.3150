package JavaFX;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * Class: PeopleUI
 * @author Mike Deiters
 * @version 1.0
 * October 13, 2015
 * ITEC 3150-01
 *
 * Description: Design the Contact Dialog
 *
 * Purpose: Build the Contact Dialog
 */
public class PeopleUI {

    private final int HEIGHT = 400;
    private final int WIDTH = 750;
    private final int BUTTON_WIDTH = 150;
    private final int BUTTON_HEIGHT = 30;

    private PeopleWriter writer;
    private NewPersonView newPerson;
    private PersonViewList viewList;

    private Scene primaryScene;
    private Stage mainStage;
    private BorderPane pane;
    private FlowPane sidePane;

    private Button newPersonBtn;
    private Button viewPeopleBtn;

    /**
     * Constructor: PeopleUI
     * @param write PeopleWriter
     * @param newPersonView NewPersonView
     * @param personViewList PersonViewList
     * @param mainStage Stage
     * Description: Setup the stage
     */
    public PeopleUI( PeopleWriter write, NewPersonView newPersonView, PersonViewList personViewList, Stage mainStage ) throws Exception {

        this.writer = write;
        this.newPerson = newPersonView;
        this.viewList = personViewList;
        this.mainStage = mainStage;
        basics();
        setupStage();
    }

    /**
     * Method: setupStage
     * Description: Setup how the stage looks
     */
    private void setupStage() {

        // Button widths, heights, and style

        newPersonBtn.setMinWidth(BUTTON_WIDTH);
        newPersonBtn.setMinHeight(BUTTON_HEIGHT);
        newPersonBtn.setStyle("-fx-background-color:#fff"); // White
        viewPeopleBtn.setMinWidth(BUTTON_WIDTH);
        viewPeopleBtn.setMinHeight(BUTTON_HEIGHT);
        viewPeopleBtn.setStyle("-fx-background-color:#fff"); // White

        viewList.setBackground("#fff"); // White
        newPerson.setBackground("#fff"); // White

        // Create the sidePane

        sidePane.setMaxWidth(200);
        sidePane.setMinHeight(HEIGHT);
        sidePane.setVgap(15);
        sidePane.setPadding(new Insets(15, 25, 15, 25));
        sidePane.setStyle("-fx-background-color:#E60000"); // Dark Red
        sidePane.getChildren().add(0, viewPeopleBtn);
        sidePane.getChildren().add(1, newPersonBtn);

        pane.setLeft(sidePane);

        if ( viewList.getPeople().isEmpty() ) { // If the hashset is empty then display newPerson

            pane.setCenter(newPerson.getPane());
        }
        else { // Otherwise display viewList

            pane.setCenter(viewList.getPane());
        }
    }

    /**
     * Method: basics
     * Description: Initiate all of the variables
     */
    public void basics() throws Exception {

        this.pane = new BorderPane();
        this.sidePane = new FlowPane();
        this.newPersonBtn = new Button("New Person");
        this.viewPeopleBtn = new Button("View People");

        this.primaryScene = new Scene(pane, WIDTH, HEIGHT);
        this.mainStage.setScene(primaryScene);
        this.mainStage.setMinWidth(WIDTH);
        this.mainStage.setMinHeight(HEIGHT);
        this.mainStage.setTitle("Contacts");
        this.mainStage.show();
    }

    /**
     * Method: getNewPersonBtn
     * @return newPersonBtn Button
     */
    public Button getNewPersonBtn() {

        return newPersonBtn;
    }

    /**
     * Method: getViewPeopleBtn
     * @return viewPeopleBtn Button
     */
    public Button getViewPeopleBtn() {

        return viewPeopleBtn;
    }

    /**
     * Method: getPane
     * @return pane BorderPane
     */
    public BorderPane getPane() {

        return pane;
    }
}
