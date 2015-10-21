import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

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

    private final int columnWidth = 50;
    private HashSet<Person> people;
    private BorderPane main;
    private BorderPane buttons;
    private TableView<Person> tableView;
    private Button saveBtn;

    /**
     * Constructor: PersonViewList
     */
    public PersonViewList() {

        this.people = new HashSet<>();
        this.main = new BorderPane();
        this.buttons = new BorderPane();
        this.tableView = new TableView<>();
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
        this.tableView = new TableView<>();
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
        tableView.setItems(getObservableList());
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
    public void setBackground(String color) {

        main.setStyle("-fx-background-color:" + color);
    }

    /**
     * Method: setupPane
     * Description: Setup the view
     */
    private void setupPane() {

        saveBtn.setMinWidth(100);
        saveBtn.setMinHeight(30);
        saveBtn.setStyle("-fx-background-color:#e6e6e6"); // Off White

        buttons.setRight(saveBtn);
        buttons.setPadding(new Insets(0, 0, 10, 0));

        // Assign the people to the listview

//        tableView.setItems(getObservableList());
        tableView.autosize();
        tableView.setMaxHeight(325);

        TableColumn firstName = new TableColumn("First Name");
        TableColumn lastName = new TableColumn("Last Name");
        TableColumn id = new TableColumn("ID");
        TableColumn city = new TableColumn("City");

        tableView.getColumns().addAll(firstName, lastName, id, city);
        tableView.resizeColumn(firstName, columnWidth);
        tableView.resizeColumn(lastName, columnWidth);
        tableView.resizeColumn(id, columnWidth);
        tableView.resizeColumn(city, columnWidth);

        main.setTop(buttons);
        main.setBottom(tableView);
        main.setPadding(new Insets(15));
    }

    /**
     * Method: getOvservableList
     * @return list ObservableList
     * Description: Creates an ObservableList from people
     */
    private ObservableList<Person> getObservableList() {

        ArrayList<Person> array = new ArrayList<>();

        for (Person person : people) { // Add all of the people in the hashset to the arraylist

            array.add(person);
        }
        ObservableList<Person> list = FXCollections.observableArrayList(array);

        return list;
    }
}
