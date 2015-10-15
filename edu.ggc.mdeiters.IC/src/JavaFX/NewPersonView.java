package JavaFX;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

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

    private final int TEXT_HEIGHT = 30;
    private final int TEXT_WIDTH = 150;
    private final int BUTTON_HEIGHT = 30;
    private final int BUTTON_WIDTH = 100;

    private GridPane pane;
    private BorderPane main;
    private BorderPane buttons;
    private Button okBtn;
    private Button cancelBtn;
    private Label title;
    private Label errorLbl;
    private Label firstName;
    private Label lastName;
    private Label id;
    private Label city;
    private TextField firstField;
    private TextField lastField;
    private TextField idField;
    private TextField cityField;

    /**
     * Constructor: NewPersonView
     */
    public NewPersonView() {

        this.pane = new GridPane();
        this.main = new BorderPane();
        this.buttons = new BorderPane();
        this.okBtn = new Button("OK");
        this.cancelBtn = new Button("Cancel");
        this.title = new Label("Add a new Person");
        this.errorLbl = new Label();
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

    /**
     * Method: getPane
     * @return main BorderPane
     */
    public BorderPane getPane() {

        return main;
    }

    /**
     * Method: getOkBtn
     * @return okBtn Button
     */
    public Button getOkBtn() {

        return okBtn;
    }

    /**
     * Method: getCancelBtn
     * @return cancelBtn
     */
    public Button getCancelBtn() {

        return cancelBtn;
    }

    /**
     * Method: getTitle
     * @return title Label
     */
    public Label getTitle() {

        return title;
    }

    public void setErrorText(String str) {

        errorLbl.setText(str);
    }

    /**
     * Method: getFirstField
     * @return String firstField text entry
     */
    public String getFirstField() {

        return firstField.getText();
    }

    /**
     * Method: getLastField
     * @return String lastField text entry
     */
    public String getLastField() {

        return lastField.getText();
    }

    /**
     * Method: getIdField
     * @return String idField text entry
     */
    public String getIdField() {

        return idField.getText();
    }

    /**
     * Method: getCityField
     * @return String cityField text entry
     */
    public String getCityField() {

        return cityField.getText();
    }

    /**
     * Method: setBackground
     * @param color Background
     */
    public void setBackground( Color color ) {

        main.setStyle("-fx-background-color:" + color);
    }

    /**
     * Method: setupPane
     * Description: Setup how the dialog looks
     */
    private void setupPane() {

        pane.setVgap(10);
        pane.setHgap(20);

        okBtn.setMinWidth(BUTTON_WIDTH);
        okBtn.setMinHeight(BUTTON_HEIGHT);
        cancelBtn.setMinWidth(BUTTON_WIDTH);
        cancelBtn.setMinHeight(BUTTON_HEIGHT);

        firstField.setMinWidth(TEXT_WIDTH);
        firstField.setMinHeight(TEXT_HEIGHT);
        lastField.setMinWidth(TEXT_WIDTH);
        lastField.setMinHeight(TEXT_HEIGHT);
        idField.setMinWidth(TEXT_WIDTH);
        idField.setMinHeight(TEXT_HEIGHT);
        cityField.setMinWidth(TEXT_WIDTH);
        cityField.setMinHeight(TEXT_HEIGHT);

        pane.add(firstName, 0, 0);
        pane.add(firstField, 1, 0);
        pane.add(lastName, 0, 1);
        pane.add(lastField, 1, 1);
        pane.add(id, 0, 2);
        pane.add(idField, 1, 2);
        pane.add(city, 0, 3);
        pane.add(cityField, 1, 3);
        pane.add(errorLbl, 1, 4);

        buttons.setLeft(cancelBtn);
        buttons.setRight(okBtn);

        title.setPadding(new Insets(0,0,10,0));

        main.setTop(title);
        main.setCenter(pane);
        main.setBottom(buttons);
        main.setPadding(new Insets(15));
    }

    /**
     * Method: clear
     * Description: Clear the text fields
     */
    public void clear() {

        firstField.setText("");
        lastField.setText("");
        idField.setText("");
        cityField.setText("");
        errorLbl.setText("");
    }
}
