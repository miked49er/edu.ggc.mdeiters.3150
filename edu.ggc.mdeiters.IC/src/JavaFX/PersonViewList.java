package JavaFX;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Class: PersonViewList
 * @author Mike Deiters
 * @version 1.0
 * October 14, 2015
 * ITEC 3150-01
 *
 * Description: PersonView Display
 *
 * Purpose: Create and display PersonView Objects
 */
public class PersonViewList {

    private HashSet<Person> people;
    private BorderPane main;
    private BorderPane buttons;
    private ScrollPane scroller;
    private FlowPane flowPane;
    private Button saveBtn;

    /**
     * Constructor: PersonViewList
     */
    public PersonViewList() {

        this.people = new HashSet<>();
        this.main = new BorderPane();
        this.buttons = new BorderPane();
        this.scroller = new ScrollPane();
        this.flowPane = new FlowPane();
        this.saveBtn = new Button();

        setupPane();
    }

    /**
     * Constructor: PersonViewList
     * @param people HashSet of Person
     */
    public PersonViewList( HashSet<Person> people ) {

        this.people = people;
        this.main = new BorderPane();
        this.buttons = new BorderPane();
        this.scroller = new ScrollPane();
        this.flowPane = new FlowPane();
        this.saveBtn = new Button();

        setupPane();
    }

    /**
     * Method: getPane
     * @return main BorderPane
     */
    public BorderPane getPane() {

        return main;
    }

    /**
     * Method: getSaveBtn
     * @return saveBtn Button
     */
    public Button getSaveBtn() {

        return saveBtn;
    }

    /**
     * Method: setPeople
     * @param people HashSet of Person
     */
    public void setPeople( HashSet<Person> people ) {

        this.people = people;
        refresh();
    }

    /**
     * Method: setBackground
     * @param color Color to make the pane background
     */
    public void setBackground(Color color) {

        main.setStyle("-fx-background-color:" + color);
    }

    /**
     * Method: setupPane
     * Description: Setup the view
     */
    private void setupPane() {

        saveBtn.setMinWidth(100);
        saveBtn.setMinHeight(40);

        buttons.setRight(saveBtn);

        scroller.setStyle("-fx-background-color:transparent");
        scroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroller.setFitToWidth(true);
        scroller.setContent(flowPane);

        main.setTop(buttons);
        main.setBottom(scroller);
        main.setPadding(new Insets(15));
    }

    /**
     * Method: refresh
     * Description: Removes and Readds the PersonViews
     */
    private void refresh() {

        // Clear the Flow Pane

        flowPane.getChildren().removeAll();

        // Creates a new PersonView with person
        // Then add the new PersonView to flowPane

        for ( Person person : people ) {

            flowPane.getChildren().add(new PersonView(person).getPane());
        }
    }
}
