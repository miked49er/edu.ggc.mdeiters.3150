package JavaFX;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * Class: NewPersonView
 * @author Mike Deiters
 * @version 1.0
 * October 14, 2015
 * ITEC 3150-01
 *
 * Description: New Person Dialog
 *
 * Purpose: Design the new person dialog
 */
public class NewPersonView {

    private GridPane pane;
    private BorderPane main;
    private BorderPane buttons;
    private Button okBtn;
    private Button cancelBtn;
    private Label title;
    private Label firstName;
    private Label lastName;
    private Label id;
    private Label city;
    private TextField firstField;
    private TextField lastField;
    private TextField idField;
    private TextField cityField;
    private Person person;

    public NewPersonView() {

        this.pane = new GridPane();
        this.main = new BorderPane();
        this.buttons = new BorderPane();
        this.okBtn = new Button("OK");
        this.cancelBtn = new Button("Cancel");
        this.title = new Label("Add a new Person");
        this.firstName = new Label("First Name");
        this.lastName = new Label("Last Name");
        this.id = new Label("Numeric ID");
        this.city = new Label("City");
        this.firstField = new TextField();
        this.lastField = new TextField();
        this.idField = new TextField();
        this.cityField = new TextField();

        setupPane();
    }

    public BorderPane getPane() {

        return main;
    }

    public Person getPerson() {

        return person;
    }

    private void setupPane() {

        pane.setVgap(10);
        pane.setHgap(20);

        okBtn.setMinWidth(100);
        okBtn.setMinHeight(40);
        cancelBtn.setMinWidth(100);
        cancelBtn.setMinHeight(40);

        firstField.setMinWidth(150);
        firstField.setMinHeight(40);
        lastField.setMinWidth(150);
        lastField.setMinHeight(40);
        idField.setMinWidth(150);
        idField.setMinHeight(40);
        cityField.setMinWidth(150);
        cityField.setMinHeight(40);

        pane.add(firstName, 0, 0);
        pane.add(firstField, 1, 0);
        pane.add(lastName, 0, 1);
        pane.add(lastField, 1, 1);
        pane.add(id, 0, 2);
        pane.add(idField, 1, 2);
        pane.add(city, 0, 3);
        pane.add(cityField, 1,3);

        buttons.setLeft(cancelBtn);
        buttons.setRight(okBtn);

        main.setTop(title);
        main.setCenter(pane);
        main.setBottom(buttons);
    }
}
