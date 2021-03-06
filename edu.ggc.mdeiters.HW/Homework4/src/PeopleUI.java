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

    private NewPersonView newPerson;
    private NewPersonView editPerson;
    private PersonViewList viewList;

    private Scene primaryScene;
    private Scene newPeopleScene;
    private Scene editPeopleScene;
    private Stage mainStage;
    private Stage newPeopleStage;
    private Stage editPeopleStage;
    private BorderPane pane;
    private FlowPane sidePane;

    private Button newPersonBtn;

    /**
     * Constructor: PeopleUI
     * @param newPersonView NewPersonView
     * @param personViewList PersonViewList
     * @param mainStage Stage
     * Description: Setup the stage
     */
    public PeopleUI(NewPersonView newPersonView, NewPersonView editPersonView, PersonViewList personViewList, Stage mainStage) throws Exception {

        this.newPerson = newPersonView;
        this.editPerson = editPersonView;
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

        viewList.setBackground("#fff"); // White
        newPerson.setBackground("#fff"); // White

        // Create the sidePane

        sidePane.setMaxWidth(200);
        sidePane.setMinHeight(HEIGHT);
        sidePane.setVgap(15);
        sidePane.setPadding(new Insets(15, 25, 15, 25));
        sidePane.setStyle("-fx-background-color:#E60000"); // Dark Red
        sidePane.getChildren().add(0, newPersonBtn);

        pane.setLeft(sidePane);
        pane.setCenter(viewList.getPane());
//        if ( viewList.getPeople().isEmpty() ) { // If the hashset is empty then display newPerson
//
//            pane.setCenter(newPerson.getPane());
//        }
//        else { // Otherwise display viewList
//
//            pane.setCenter(viewList.getPane());
//        }
    }

    /**
     * Method: basics
     * Description: Initiate all of the variables
     */
    public void basics() throws Exception {

        this.pane = new BorderPane();
        this.sidePane = new FlowPane();
        this.newPersonBtn = new Button("New Person");

        this.primaryScene = new Scene(pane, WIDTH, HEIGHT);
        this.mainStage.setScene(primaryScene);
        this.mainStage.setMinWidth(WIDTH);
        this.mainStage.setMinHeight(HEIGHT);
        this.mainStage.setTitle("Contacts");
        this.mainStage.show();

        this.newPeopleScene = new Scene(newPerson.getPane(), WIDTH/2, HEIGHT);
        this.newPeopleStage = new Stage();
        this.newPeopleStage.setScene(newPeopleScene);
        this.newPeopleStage.setMinWidth(WIDTH / 2);
        this.newPeopleStage.setMinHeight(HEIGHT);
        this.newPeopleStage.setTitle("New Person");

        this.editPerson.getTitle().setText("Edit the person");
        this.editPeopleScene = new Scene(editPerson.getPane(), WIDTH/2, HEIGHT);
        this.editPeopleStage = new Stage();
        this.editPeopleStage.setScene(editPeopleScene);
        this.editPeopleStage.setMinWidth(WIDTH/2);
        this.editPeopleStage.setMinHeight(HEIGHT);
        this.editPeopleStage.setTitle("Edit Person");
    }

    /**
     * Method: getNewPersonBtn
     * @return newPersonBtn Button
     */
    public Button getNewPersonBtn() {

        return newPersonBtn;
    }

    /**
     * Method: getPane
     * @return pane BorderPane
     */
    public BorderPane getPane() {

        return pane;
    }

    /**
     * Method: getMainStage
     * @return mainStage Stage
     */
    public Stage getMainStage() {

        return mainStage;
    }

    /**
     * Method: getNewPeopleStage
     * @return newPeopleStage Stage
     */
    public Stage getNewPeopleStage() {

        return newPeopleStage;
    }

    /**
     * Method: getEditPeopleStage
     * @return editPeopleStage
     */
    public Stage getEditPeopleStage() {

        return editPeopleStage;
    }
}
