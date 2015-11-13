import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * Class: FXProblem
 * @author Mike Deiters
 * @version 1.0
 * November 12, 2015
 * ITEC 3150-01
 *
 * Description: TODO
 *
 * Purpose: TODO
 */
public class FXProblem extends Application {

    private final int WIDTH = 500;
    private final int HEIGHT = 400;

    private Stage mainStage;
    private Scene primaryScene;
    private BorderPane pane;
    private FlowPane flowPane;
    private RadioButton redBtn;
    private RadioButton yellowBtn;
    private RadioButton purpleBtn;
    private final ToggleGroup radioGroup = new ToggleGroup();
    private Label colorTxt;

    private void actions() {

        radioGroup.selectedToggleProperty().addListener(new ChangeListener< Toggle >() {
            @Override
            public void changed( ObservableValue< ? extends Toggle > observable, Toggle oldValue, Toggle newValue ) {

                if (radioGroup.getSelectedToggle() == redBtn) {

                    colorTxt.setStyle("-fx-text-fill: red");
                } else if (radioGroup.getSelectedToggle() == yellowBtn) {
                    colorTxt.setStyle("-fx-text-fill: yellow");
                } else if (radioGroup.getSelectedToggle() == purpleBtn) {
                    colorTxt.setStyle("-fx-text-fill: purple");
                }
            }
        });
    }

    private void setupPane() {

        pane.setRight(flowPane);
        pane.setLeft(colorTxt);
        flowPane.getChildren().addAll(redBtn, yellowBtn, purpleBtn);
        flowPane.setPadding(new Insets(10));

        colorTxt.setPadding(new Insets(100,50,100,50));



        actions();
    }

    @Override
    public void start( Stage primaryStage ) throws Exception {

        pane = new BorderPane();
        flowPane = new FlowPane();
        flowPane.setVgap(40);
        flowPane.setMaxHeight(400);
        flowPane.setMaxWidth(100);
        flowPane.setStyle("-fx-border-color: green");

        redBtn = new RadioButton("Red");
        redBtn.setToggleGroup(radioGroup);
        yellowBtn = new RadioButton("Yellow");
        yellowBtn.setToggleGroup(radioGroup);
        purpleBtn = new RadioButton("Purple");
        purpleBtn.setToggleGroup(radioGroup);
        colorTxt = new Label("JavaFX Programming");

        primaryScene = new Scene(pane, WIDTH, HEIGHT);
        primaryStage = new Stage();
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("JavaFX Problem");
        primaryStage.show();

        setupPane();
    }
}
