import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;

/**
 * Class: Actions
 * @author Mike Deiters
 * @version 1.0
 * October 14, 2015
 * ITEC 3150-01
 *
 * Description: Create the eventhandlers
 *
 * Purpose: Perform the actions for the Application
 */
public class Actions extends Application {

    private final String saveFile = "data.dat";

    private PersonReader reader;
    private PeopleWriter write;
    private NewPersonView newPersonView;
    private PersonViewList personViewList;
    private PeopleUI ui;
    private HashSet< Person > people;
    private Stage mainStage;

    @Override
    public void start( Stage primaryStage ) throws Exception {

        this.reader = new PersonReader();
        this.write = new PeopleWriter();
        this.newPersonView = new NewPersonView();
        this.personViewList = new PersonViewList();
        this.mainStage = new Stage();

        this.people = new HashSet<>();

        try {

            // Read the file into a hashset

            people = reader.readPeople(saveFile);

            if (!people.isEmpty()) {

                personViewList.setPeople(people);
            }

        }
        catch ( ClassNotFoundException cnf ) { // Print Message

            System.out.println(cnf.getMessage());
        }
        catch ( FileNotFoundException fnf ) { // Print Message

            System.out.println(fnf.getMessage() + "\nOr First Application Run.");
        }
        catch ( NullPointerException npe ) {

            System.out.println("File doesn't exist or First Application Run");
        }
        catch ( IOException ioe ) { // Print Message

            System.out.println(ioe.getMessage());
        }

        this.ui = new PeopleUI(newPersonView, personViewList, mainStage);
        stageClose();

        buttons();
        fields();
        columnEdit();
    }

    /**
     * Method: save
     * Description: Save the contents of the hashset to a file
     */
    private void save() {

        if ( !people.isEmpty() ) {

            try {

                write.writeOut(people, saveFile);
            }
            catch ( IOException ioe ) {

                System.out.println(ioe.getMessage());
            }
        }
    }

    /**
     * Method: buttons
     * Description: Create all of the button actions
     */
    private void buttons() {

        // View People Button

        ui.getViewPeopleBtn().setOnAction(new EventHandler< ActionEvent >() {
            @Override
            public void handle( ActionEvent event ) {

                // Change the view to the Person List View

                ui.getPane().setCenter(personViewList.getPane());
            }
        });

        // New Person Button

        ui.getNewPersonBtn().setOnAction(new EventHandler< ActionEvent >() {
            @Override
            public void handle( ActionEvent event ) {

                // Change the view to the New Person Dialog

                newPersonView.clear();
                ui.getNewPeopleStage().show();
            }
        });

        // Save Button

        personViewList.getSaveBtn().setOnAction(new EventHandler< ActionEvent >() {
            @Override
            public void handle( ActionEvent event ) {

                // Save the People

                save();
            }
        });

        // Ok Button

        newPersonView.getOkBtn().setOnAction(new EventHandler< ActionEvent >() {
            @Override
            public void handle( ActionEvent event ) {

                createPerson();
            }
        });

        // Cancel Button

        newPersonView.getCancelBtn().setOnAction(new EventHandler< ActionEvent >() {
            @Override
            public void handle( ActionEvent event ) {

                // Clear the text fields

                newPersonView.clear();
            }
        });
    }

    /**
     * Method: fields
     * Description: Create all of the TextField actions
     */
    private void fields() {

        // firstField

        newPersonView.getFirstField().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle( KeyEvent event ) {

                if (event.getCode() == KeyCode.ENTER) {

                    createPerson();
                }
            }
        });

        // lastField

        newPersonView.getLastField().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle( KeyEvent event ) {

                if ( event.getCode() == KeyCode.ENTER ) {

                    createPerson();
                }
            }
        });

        // idField

        newPersonView.getIdField().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle( KeyEvent event ) {

                if ( event.getCode() == KeyCode.ENTER ) {

                    createPerson();
                }
            }
        });

        // cityField

        newPersonView.getCityField().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle( KeyEvent event ) {

                if ( event.getCode() == KeyCode.ENTER ) {

                    createPerson();
                }
            }
        });
    }

    /**
     * Method: columnEdit
     * Description: Create all of the column editing actions
     */
    private void columnEdit() {

        // firstCol

        personViewList.getFirstCol().setCellFactory(TextFieldTableCell.forTableColumn());
        personViewList.getFirstCol().setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle( TableColumn.CellEditEvent<Person, String> event ) {

                event.getTableView().getItems().get(event.getTablePosition().getRow()).setFirstName(event.getNewValue());
            }
        });

        // lastCol

        personViewList.getLastCol().setCellFactory(TextFieldTableCell.forTableColumn());
        personViewList.getLastCol().setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle( TableColumn.CellEditEvent<Person, String> event ) {

                event.getTableView().getItems().get(event.getTablePosition().getRow()).setLastName(event.getNewValue());
            }
        });

        // idCol

        personViewList.getIdCol().setCellFactory(TextFieldTableCell.forTableColumn());
        personViewList.getIdCol().setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, Integer>>() {
            @Override
            public void handle( TableColumn.CellEditEvent<Person, Integer> event ) {

                try {

                    event.getTableView().getItems().get(event.getTablePosition().getRow()).setIdNum(Integer.parseInt(String.valueOf(event.getNewValue())));
                    personViewList.setErrorText("");
                }
                catch ( NumberFormatException nfe ) { // Reprompt the user

                    personViewList.setErrorText("Please enter a number in \nthe ID field.");
                }
            }
        });

        // cityCol

        personViewList.getCityCol().setCellFactory(TextFieldTableCell.forTableColumn());
        personViewList.getCityCol().setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle( TableColumn.CellEditEvent<Person, String> event ) {

                event.getTableView().getItems().get(event.getTablePosition().getRow()).setCity(event.getNewValue());
            }
        });
    }

    /**
     * Method: stageClose
     * Description: Handle what happens when the stage is closed
     */
    private void stageClose() {

        // mainStage

        ui.getMainStage().setOnCloseRequest(new EventHandler< WindowEvent >() {
            @Override
            public void handle( WindowEvent event ) {

                save();
                ui.getMainStage().close();
                ui.getNewPeopleStage().close();
                Platform.exit();
            }
        });

        // newPeopleStage

        ui.getNewPeopleStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle( WindowEvent event ) {

                newPersonView.clear();
                ui.getNewPeopleStage().close();
            }
        });
    }

    /**
     * Method: getFile
     * @return String of the file location
     * Description: Returns the file location
     */
    private String getFile() {

        URL path = PeopleUI.class.getResource("data.dat");
        return path.getPath();
    }

    /**
     * Method: createPerson
     * Description: Create a new Person
     */
    private void createPerson() {

        // Check to see if the content in the fields is valid

        try {

            // If any of the fields are empty then throw an error

            if ( newPersonView.getFirstField().getText().isEmpty() || newPersonView.getLastField().getText().isEmpty() || newPersonView.getIdField().getText().isEmpty() || newPersonView.getCityField().getText().isEmpty() ) {

                throw new EmptyFieldsException("Please fill out all fields");
            }

            // Attempt to create the person fields

            String first = newPersonView.getFirstField().getText();
            String last = newPersonView.getLastField().getText();
            int id = Integer.parseInt(newPersonView.getIdField().getText());
            String city = newPersonView.getCityField().getText();

            // Create a new Person

            Person person = new Person();

            person.setFirstName(first);
            person.setLastName(last);
            person.setIdNum(id);
            person.setCity(city);

            for ( Person element: people ) { // Check for identical id numbers

                if (person.getIdNum() == element.getIdNum()) { // Throw an error if id number is not unique

                    throw new EmptyFieldsException("Please enter an unique id");
                }
            }

            // Add person to people

            people.add(person);

            // Reset the UI hashset

            personViewList.setPeople(people);

            newPersonView.clear();
            ui.getNewPeopleStage().close();
        }
        catch ( EmptyFieldsException efe ) {

            newPersonView.setErrorText(efe.getMessage());
        }
        catch ( NumberFormatException nfe ) { // Reprompt the user

            newPersonView.setErrorText("Please enter a number in \nthe ID field.");
        }
    }
}
