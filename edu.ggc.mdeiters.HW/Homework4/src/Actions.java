import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;

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
    private NewPersonView editPersonView;
    private PersonViewList personViewList;
    private PeopleUI ui;
    private ArrayList< Person > people;
    private Stage mainStage;
    private Person editPerson;

    /**
     * Method: start
     * @param primaryStage
     * @throws Exception
     * Description: Setup the application
     */
    public void start( Stage primaryStage ) throws Exception {

        this.reader = new PersonReader();
        this.write = new PeopleWriter();
        this.newPersonView = new NewPersonView();
        this.editPersonView = new NewPersonView();
        this.personViewList = new PersonViewList();
        this.mainStage = new Stage();

        this.editPerson = null;

        this.people = new ArrayList<>();

        try {

            // Read the file into a arraylist

            people = reader.readPeople(saveFile);

            if ( !people.isEmpty() ) {

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

        this.ui = new PeopleUI(newPersonView, editPersonView, personViewList, mainStage);
        stageClose();

        buttons();
        fields();
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

        // New Person Button

        ui.getNewPersonBtn().setOnAction(new EventHandler< ActionEvent >() {
            @Override
            public void handle( ActionEvent event ) {

                // Change the view to the New Person Dialog
                // Request Focus to the firstField

                newPersonView.clear();
                newPersonView.getFirstField().requestFocus();
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

        // Edit Button

        personViewList.getEditBtn().setOnAction(new EventHandler< ActionEvent >() {
            @Override
            public void handle( ActionEvent event ) {

                // Edit the Person

                editPeople();
            }
        });

        // Delete Button

        personViewList.getDeleteBtn().setOnAction(new EventHandler< ActionEvent >() {
            @Override
            public void handle( ActionEvent event ) {

                // Delete the Person

                deletePerson();
            }
        });

        /*************************************************************
         *                  New Person View                          *
         *************************************************************/

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
                ui.getNewPeopleStage().close();
            }
        });

        /*************************************************************
         *                  Edit Person View                         *
         *************************************************************/

        // Ok Button

        editPersonView.getOkBtn().setOnAction(new EventHandler< ActionEvent >() {
            @Override
            public void handle( ActionEvent event ) {

                editPerson();
            }
        });

        // Cancel Button

        editPersonView.getCancelBtn().setOnAction(new EventHandler< ActionEvent >() {
            @Override
            public void handle( ActionEvent event ) {

                // Clear the text fields

                editPersonView.clear();
                editPerson = null;
                ui.getEditPeopleStage().close();
            }
        });
    }

    /**
     * Method: fields
     * Description: Create all of the TextField actions
     */
    private void fields() {

        /*************************************************************
         *                  New Person View                          *
         *************************************************************/

        // firstField

        newPersonView.getFirstField().setOnKeyPressed(new EventHandler< KeyEvent >() {
            @Override
            public void handle( KeyEvent event ) {

                if ( event.getCode() == KeyCode.ENTER ) {

                    createPerson();
                }
            }
        });

        // lastField

        newPersonView.getLastField().setOnKeyPressed(new EventHandler< KeyEvent >() {
            @Override
            public void handle( KeyEvent event ) {

                if ( event.getCode() == KeyCode.ENTER ) {

                    createPerson();
                }
            }
        });

        // idField

        newPersonView.getIdField().setOnKeyPressed(new EventHandler< KeyEvent >() {
            @Override
            public void handle( KeyEvent event ) {

                if ( event.getCode() == KeyCode.ENTER ) {

                    createPerson();
                }
            }
        });

        // cityField

        newPersonView.getCityField().setOnKeyPressed(new EventHandler< KeyEvent >() {
            @Override
            public void handle( KeyEvent event ) {

                if ( event.getCode() == KeyCode.ENTER ) {

                    createPerson();
                }
            }
        });

        /*************************************************************
         *                  Edit Person View                         *
         *************************************************************/

        // firstField

        editPersonView.getFirstField().setOnKeyPressed(new EventHandler< KeyEvent >() {
            @Override
            public void handle( KeyEvent event ) {

                if ( event.getCode() == KeyCode.ENTER ) {

                    editPerson();
                }
            }
        });

        // lastField

        editPersonView.getLastField().setOnKeyPressed(new EventHandler< KeyEvent >() {
            @Override
            public void handle( KeyEvent event ) {

                if ( event.getCode() == KeyCode.ENTER ) {

                    editPerson();
                }
            }
        });

        // idField

        editPersonView.getIdField().setOnKeyPressed(new EventHandler< KeyEvent >() {
            @Override
            public void handle( KeyEvent event ) {

                if ( event.getCode() == KeyCode.ENTER ) {

                    editPerson();
                }
            }
        });

        // cityField

        editPersonView.getCityField().setOnKeyPressed(new EventHandler< KeyEvent >() {
            @Override
            public void handle( KeyEvent event ) {

                if ( event.getCode() == KeyCode.ENTER ) {

                    editPerson();
                }
            }
        });
    }

    /**
     * Method: editPeople
     * Description: Setup the editPerson stage
     */
    public void editPeople() {

        this.editPerson = personViewList.getTableView().getSelectionModel().getSelectedItem();

        if ( editPerson != null ) {

            // Populate the textfields
            // Request the focus to the firstfield

            personViewList.setErrorText("");
            editPersonView.getFirstField().setText(editPerson.getFirstName());
            editPersonView.getLastField().setText(editPerson.getLastName());
            editPersonView.getIdField().setText("" + editPerson.getIdNum());
            editPersonView.getCityField().setText(editPerson.getCity());

            editPersonView.getFirstField().requestFocus();
            ui.getEditPeopleStage().show();
        }
        else { // Person not selected so show an error

            personViewList.setErrorText("Please select a person to edit");
        }
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
                ui.getEditPeopleStage().close();
                Platform.exit();
            }
        });

        // newPeopleStage

        ui.getNewPeopleStage().setOnCloseRequest(new EventHandler< WindowEvent >() {
            @Override
            public void handle( WindowEvent event ) {

                newPersonView.clear();
                ui.getNewPeopleStage().close();
            }
        });

        ui.getEditPeopleStage().setOnCloseRequest(new EventHandler< WindowEvent >() {
            @Override
            public void handle( WindowEvent event ) {

                editPersonView.clear();
                ui.getEditPeopleStage().close();
            }
        });
    }

    /**
     * Method: createPerson
     * Description: Create a new Person
     */
    private void createPerson() {

        // Check to see if the content in the fields is valid

        try {

            // If any of the fields are empty then throw an error

            if ( newPersonView.getFirstField().getText().isEmpty() ||
                    newPersonView.getLastField().getText().isEmpty() ||
                    newPersonView.getIdField().getText().isEmpty() ||
                    newPersonView.getCityField().getText().isEmpty() ) {

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

            for ( Person element : people ) { // Check for identical id numbers

                if ( person.getIdNum() == element.getIdNum() ) { // Throw an error if id number is not unique

                    throw new EmptyFieldsException("Please enter an unique id");
                }
            }

            // Add person to people

            people.add(person);

            // Reset the UI arraylist

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

    /**
     * Method: editPerson
     * Description: Create a new Person
     */
    private void editPerson() {

        // Check to see if the content in the fields is valid

        try {

            // If any of the fields are empty then throw an error

            if ( editPersonView.getFirstField().getText().isEmpty() ||
                    editPersonView.getLastField().getText().isEmpty() ||
                    editPersonView.getIdField().getText().isEmpty() ||
                    editPersonView.getCityField().getText().isEmpty() ) {

                throw new EmptyFieldsException("Please fill out all fields");
            }

            // Attempt to create the person fields

            String first = editPersonView.getFirstField().getText();
            String last = editPersonView.getLastField().getText();
            int id = Integer.parseInt(editPersonView.getIdField().getText());
            String city = editPersonView.getCityField().getText();

            for ( int i = 0; i < people.size(); i++ ) { // Check for identical id numbers

                if ( id == people.get(i).getIdNum() && id != editPerson.getIdNum() ) { // Throw an error if id number is not unique

                    throw new EmptyFieldsException("Please enter an unique id");
                }
                else if ( editPerson == people.get(i) ) {

                    // Update the Person

                    this.people.get(i).setFirstName(first);
                    this.people.get(i).setLastName(last);
                    this.people.get(i).setIdNum(id);
                    this.people.get(i).setCity(city);

                    editPerson = null;
                }
            }

            // Reset the UI arraylist

            personViewList.setPeople(people);

            editPersonView.clear();
            ui.getEditPeopleStage().close();
        }
        catch ( EmptyFieldsException efe ) {

            editPersonView.setErrorText(efe.getMessage());
        }
        catch ( NumberFormatException nfe ) { // Reprompt the user

            editPersonView.setErrorText("Please enter a number in \nthe ID field.");
        }
    }

    private void deletePerson() {

        Person tempDelete = personViewList.getTableView().getSelectionModel().getSelectedItem();

        // Alert to confirm the deletion of the person

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText("Are you sure you want to delete this?");
        alert.setContentText("This will pernamently delete this person.");

        Optional< ButtonType > result = alert.showAndWait();

        if ( result.get() == ButtonType.OK ) {

            // If the user presses ok

            boolean personFound = false;

            // Loop through people until the person to delete is found
            // Once found it deletes the person from people

            for ( int i = 0; !personFound && i < people.size(); i++ ) {

                if ( people.get(i).getIdNum() == tempDelete.getIdNum() ) {

                    personFound = true;
                    people.remove(i);
                    personViewList.setPeople(people);
                }
            }
        }
        else {

            // If the user pressed cancel or closed the dialog
            // Do nothing

        }
    }
}
