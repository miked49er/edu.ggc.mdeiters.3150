package JavaFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
    private ListView<Person> listView;
    private Button saveBtn;

    /**
     * Constructor: PersonViewList
     */
    public PersonViewList() {

        this.people = new HashSet<>();
        this.main = new BorderPane();
        this.buttons = new BorderPane();
        this.listView = new ListView<>();
        this.saveBtn = new Button("Save");

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
        this.listView = new ListView<>();
        this.saveBtn = new Button("Save");

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
        listView.setItems(getObservableList());
    }

    /**
     * Method: getPeople
     * @return people HashSet of Person
     */
    public HashSet< Person > getPeople() {

        return people;
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
        saveBtn.setMinHeight(30);

        buttons.setRight(saveBtn);
        buttons.setPadding(new Insets(0,0,10,0));

        listView.setItems(getObservableList());
        listView.autosize();
        listView.setMaxHeight(325);

        main.setTop(buttons);
        main.setBottom(listView);
        main.setPadding(new Insets(15));
    }

    /**
     * Method: getOvservableList
     * @return list ObservableList
     * Description: Creates an ObservableList from people
     */
    private ObservableList<Person> getObservableList() {

        ArrayList<Person> array = new ArrayList<>();

        for (Person person : people) {

            array.add(person);
        }
        ObservableList<Person> list = FXCollections.observableArrayList(array);

        return list;
    }
}
