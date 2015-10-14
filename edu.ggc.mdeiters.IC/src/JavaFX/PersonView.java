package JavaFX;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Class: PersonView
 * @author Mike Deiters
 * @version 1.0
 * October 14, 2015
 * ITEC 3150-01
 *
 * Description: Person object display
 *
 * Purpose: Create a display for the Person objects
 */
public class PersonView {

    private Pane pane;
    private Label name;
    private Label id;
    private Label city;
    private Person person;

    /**
     * Constructor: PersonView
     */
    public PersonView() {

        this.pane = new Pane();
        this.name = new Label();
        this.id = new Label();
        this.city = new Label();
        this.person = new Person();
    }

    /**
     * Constructor: PersonView
     * @param person Person to create a view of
     */
    public PersonView( Person person ) {

        this.pane = new Pane();
        this.name = new Label();
        this.id = new Label();
        this.city = new Label();
        this.person = person;
    }

    /**
     * Method: setPerson
     * @param person Person to create a view of
     * @return void
     */
    public void setPerson( Person person ) {

        this.person = person;
    }

    /**
     * Method: getPane
     * @return Pane to display Person content
     */
    public Pane getPane() {

        setupPane();
        return pane;
    }

    /**
     * Method: setupPane
     * Description: Setup the Person view
     */
    private void setupPane() {

        name.setText(person.getFirstName() + " " + person.getLastName());
        id.setText(person.getIdNum() + "");
        city.setText(person.getCity());
        pane.getChildren().add(name);
        pane.getChildren().add(id);
        pane.getChildren().add(city);
    }
}
