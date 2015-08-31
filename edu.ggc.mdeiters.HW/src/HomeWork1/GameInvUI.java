package HomeWork1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Class: GameInvUI
 * @author Mike Deiters
 * @version 1.0
 * August 25, 2015
 * ITEC 3150-01
 *
 * Description: My Game Inventory UI
 *
 * Purpose: Create the user interface for the game inventory application
 */
public class GameInvUI extends Application {

	// Variables

	private final int WIDTH = 900;
	private final int HEIGHT = 725;
	private final int OPTIONS_WIDTH = 450;
	private final int OPTIONS_HEIGHT = 305;
	private final int NEW_GAME_WIDTH = 370;
	private final int NEW_GAME_HEIGHT = 410;
	private final int BUTTON_WIDTH = 100;
	private final int BUTTON_HEIGHT = 30;
	private final Insets DEFAULT = new Insets(10, 10, 10, 10);

	// Main Scene

	private final ToggleGroup rbGroup = new ToggleGroup();
	private BorderPane pane;
	private Scene scene;
	private Stage mainStage;
	private FlowPane gamesGrid;
	private ScrollPane gridScroll;
	private BorderPane optionsGridPane;
	private GridPane optionsGrid;
	private Label errorLbl;
	private ArrayList< GameView > gameViewList;
	private ArrayList< Game > gameList;
	private Button newGameBtn;
	private Button searchBtn;
	private Button saveBtn;

	// Menus

	private Button exitBtn;
	private BorderPane gameOptions;
	private BorderPane gameDescription;
	private Scene gameViewScene;
	private Scene gameOptionsScene;
	private Stage optionsStage;
	private TextField searchField;
	private Label gameLbl;
	private Button deleteBtn;
	private Button searchMenuBtn;
	private BorderPane searchPane;
	private BorderPane gameDescriptionPane;

	// New Game

	private Scene newGameScene;
	private Stage newGameStage;
	private GridPane newGameGridPane;
	private RadioButton pcBtn;
	private RadioButton consoleBtn;
	private RadioButton mobileBtn;
	private Button submit;
	private Label gameNameLbl;
	private Label gameInventoryLbl;
	private Label gameParam1Lbl;
	private Label gameParam2Lbl;
	private Label newGameError;
	private TextField gameNameField;
	private TextField gameInventoryField;
	private TextField gameParam1Field;
	private TextField gameParam2Field;

	// Classes

	private GameCollect gameCollect;
	private Game gameSelected;

	/**
	 * Method: collectGames
	 * @return void
	 * Description: Reads in all of the game from the file
	 */
	public void collectGames() {

		ReadFile rf = new ReadFile();

		try {

			// Locates the gameList file and reads it in to an arraylist

			this.gameCollect = new GameCollect();
			rf.readFile(this.gameCollect.getGameFile());

			// Retrieve the arraylist of the game information

			ArrayList< String > list = rf.getFileList();

			for ( int i = 0; i < list.size(); i++ ) { // Loop through the list and parse the data into game data

				String gameStream = list.get(i);

				String system = gameStream.substring(0, gameStream.indexOf(','));
				gameStream = gameStream.substring(gameStream.indexOf(',') + 1);

				String name = gameStream.substring(0, gameStream.indexOf(','));
				gameStream = gameStream.substring(gameStream.indexOf(',') + 1);

				String inventory = gameStream.substring(0, gameStream.indexOf(','));
				gameStream = gameStream.substring(gameStream.indexOf(',') + 1);

				String id = gameStream.substring(0, gameStream.indexOf(','));
				gameStream = gameStream.substring(gameStream.indexOf(',') + 1);

				String param1 = gameStream.substring(0, gameStream.indexOf(','));
				gameStream = gameStream.substring(gameStream.indexOf(',') + 1);

				String param2 = gameStream;

				switch ( system ) { // Create the new game and add it to the gameList based on the game system

					case "PC": // PC Games

						this.gameList.add(new PC(name, Integer.parseInt(inventory), Integer.parseInt(id), Integer.parseInt(param1), Integer.parseInt(param2)));
						break;

					case "Console": // Console Games

						this.gameList.add(new Console(name, Integer.parseInt(inventory), Integer.parseInt(id), param1, param2));
						break;

					case "Mobile": // Mobile Games

						this.gameList.add(new Mobile(name, Integer.parseInt(inventory), Integer.parseInt(id), param1, Double.parseDouble(param2)));
						break;

					default: // Default show an error message

						errorLbl.setText("Game information is missing.");

						// Set i to the size of list so the loop will end due to an error

						i = list.size();
				}
			}
		}
		catch ( FileMissingException fme ) { // File can not be found

			errorLbl.setText(fme.getMessage());
		}
		catch ( NullPointerException npe ) { // Do nothing of null

		}
		catch ( StringIndexOutOfBoundsException sib ) { // Data is corrupted if information is missing

			errorLbl.setText("Game Data is corrupted.");
		}

		refreshPage();
	}

	/**
	 * Method: saveGames
	 * @return void
	 * Description: Write out the games to a file
	 */
	public void saveGames() {

		try {

			ArrayList< String > gameStringList = new ArrayList< String >();

			for ( int i = 0; i < gameList.size(); i++ ) { // Adds all of the gameList game's toFile to gameStringList

				gameStringList.add(gameList.get(i).toFile());
			}

			// Write the games out to the file

			WriteFile wf = new WriteFile(gameStringList, gameCollect.getGameFile());

		}
		catch ( FileMissingException fme ) { // File to right to is missing

			errorLbl.setText(fme.getMessage());
		}
		catch ( NullPointerException npe ) { // Do nothing if null

		}
	}

	/**
	 * Method: refreshPage
	 * @return void
	 * Description: Unload all of the GaveViews from the gameGrid
	 * 		Create new the GameViews from the games in gameList
	 * 		Load the GameViews to the gameGrid
	 */
	public void refreshPage() {

		// Clear GameViews

		gameViewList.clear();
		gamesGrid.getChildren().clear();

		for ( int i = 0; i < gameList.size(); i++ ) { // Loop through gameList to create new GameViews

			Game gameS = gameList.get(i);

			// Create the new GameView
			// Add GameView to the gamesGrid

			gameViewList.add(new GameView(gameS));
			gamesGrid.getChildren().add(gameViewList.get(i).getPane());

			gameViewList.get(i).getPane().setOnMouseClicked(new EventHandler< MouseEvent >() { // Set and eventHandler to the GameView to load the description of the game
				@Override
				public void handle( MouseEvent event ) {

					openGame(gameS);
					searchField.setText("Search by Game ID");

					optionsStage.setScene(gameViewScene);
					optionsStage.setTitle("Game Description");
					optionsStage.show();

				}
			});
		}
	}

	/**
	 * Method: openGame
	 * @param game Game to open in the description View
	 */
	public void openGame( Game game ) {

		try {

			// Set the gameSelected to the game to be viewed

			gameSelected = game;

			// Create a temp GameView to get the game's coverArt
			GameView gameSelectedV = new GameView(game);
			ImageView coverArt = gameSelectedV.getCoverArt();

			gameDescriptionPane.setLeft(coverArt);
			gameDescriptionPane.setRight(gameLbl);

			// Retrieve the Game's toString for the information about the game

			gameLbl.setText(game.toString());
		}
		catch ( FileMissingException fme ) { // Do nothing if file is missing

		}
	}

	/**
	 * Method: searchGames
	 * @param gameId int of a gameId to search for
	 * @return game Game that the user was looking for
	 */
	public Game searchGames( int gameId ) {

		// Create a null Game to return if the search doesn't find a game with a matching Id
		Game game = null;

		// Loop through gameList to see if any of the games in the list of a matching gameId

		for ( int i = 0; i < gameList.size(); i++ ) {

			if ( gameList.get(i).getGameId() == gameId ) { // If gameId matches then set game to the game that matches

				game = gameList.get(i);
			}
		}

		return game;
	}

	/**
	 * Method: btnActions
	 * @return void
	 * Description: Handle all of the button actions
	 */
	public void btnActions() {

		// Displays the newGame dialog

		newGameBtn.setOnAction(new EventHandler< ActionEvent >() {
			@Override
			public void handle( ActionEvent event ) {

				newGame();
			}
		});

		// Displays the search/gameDescription dialog

		searchBtn.setOnAction(new EventHandler< ActionEvent >() {
			@Override
			public void handle( ActionEvent event ) {

				searchField.setText("Search by Game ID");
				optionsStage.setScene(gameViewScene);
				optionsStage.setTitle("Search");
				optionsStage.show();
			}
		});

		// Saves the games

		saveBtn.setOnAction(new EventHandler< ActionEvent >() {
			@Override
			public void handle( ActionEvent event ) {

				saveGames();
			}
		});

		// Saves the games and closes all stages

		exitBtn.setOnAction(new EventHandler< ActionEvent >() {
			@Override
			public void handle( ActionEvent event ) {

				saveGames();
				newGameStage.close();
				optionsStage.close();
				mainStage.close();
				Platform.exit();
			}
		});

		// Calls the search function with the data the user entered via a Mouse Click

		searchMenuBtn.setOnAction(new EventHandler< ActionEvent >() {
			@Override
			public void handle( ActionEvent event ) {

				try {

					int gameId = Integer.parseInt(searchField.getText());
					openGame(searchGames(gameId));
				}
				catch ( NumberFormatException nfe ) { // Prompts the user to enter a number

					searchField.setText("Invalid Game ID");
				}
				catch ( NullPointerException npe ) { // Prompts the user to enter a number

					searchField.setText("Invalid Game ID");
				}
			}
		});

		// Calls the search function with the data the user entered via pressing the ENTER key

		searchField.setOnKeyPressed(new EventHandler< KeyEvent >() {
			@Override
			public void handle( KeyEvent event ) {

				if ( event.getCode() == KeyCode.ENTER ) { // Checks to see if the ENTER key has been pressed

					try {

						int gameId = Integer.parseInt(searchField.getText());
						openGame(searchGames(gameId));
					}
					catch ( NumberFormatException nfe ) { // Prompts the user to enter a number

						searchField.setText("Invalid Game ID");
					}
					catch ( NullPointerException npe ) { // Prompts the user to enter a number

						searchField.setText("Invalid Game ID");
					}
				}
			}
		});

		// Deletes the selected game

		deleteBtn.setOnAction(new EventHandler< ActionEvent >() {
			@Override
			public void handle( ActionEvent event ) {

				// Alert to confirm the deletion of the game

				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Delete Confirmation");
				alert.setHeaderText("Are you sure you want to delete this?");
				alert.setContentText("This will pernamently delete this game.");

				Optional< ButtonType > result = alert.showAndWait();

				if ( result.get() == ButtonType.OK ) {

					// If the user presses ok

					boolean gameFound = false;

					// Loop through gameList until the game to delete is found
					// Once found it deletes the game from gameList

					for ( int i = 0; !gameFound && i < gameList.size(); i++ ) {

						if ( gameList.get(i).getGameId() == gameSelected.getGameId() ) {

							gameFound = true;
							gameList.remove(i);
							refreshPage();
							optionsStage.close();
						}
					}
				}
				else {

					// If the user pressed cancel or closed the dialog
					// Do nothing

				}
			}
		});

		// Using the user entered data it attempts to create a new game

		submit.setOnAction(new EventHandler< ActionEvent >() {
			@Override
			public void handle( ActionEvent event ) {

				// Clears the errror entry

				newGameError.setText("");

				try {

					Game game = null;

					// Parses the user data into variables

					String name = gameNameField.getText();
					int inv = Integer.parseInt(gameInventoryField.getText());
					String param1 = gameParam1Field.getText();
					String param2 = gameParam2Field.getText();

					// Default GameID values

					int pcID = 1000;
					int consoleID = 2000;
					int mobileID = 3000;

					for ( int i = 0; i < gameList.size(); i++ ) { // Update the GameID's to be the highest current GameID in each category

						int tempID = gameList.get(i).getGameId();

						if ( tempID >= 1000 && tempID < 2000 ) { // PC GameID Range

							if ( tempID > pcID ) {

								pcID = tempID;
							}
						}
						else if ( tempID >= 2000 && tempID < 3000 ) { // Console GameID Range

							if ( tempID > consoleID ) {

								consoleID = tempID;
							}
						}
						else if ( tempID >= 3000 && tempID < 4000 ) { // Mobile GameID Range

							if ( tempID > mobileID ) {

								mobileID = tempID;
							}
						}

					}

					// Increment all GameIDs up one

					pcID++;
					consoleID++;
					mobileID++;

					if ( rbGroup.getSelectedToggle() == pcBtn ) { // Create a new PC Game

						game = new PC(name, inv, pcID, Integer.parseInt(param1), Integer.parseInt(param2));

					}
					else if ( rbGroup.getSelectedToggle() == consoleBtn ) { // Create a new Console Game

						game = new Console(name, inv, consoleID, param1, param2);

					}
					else if ( rbGroup.getSelectedToggle() == mobileBtn ) { // Create a new Mobile Game

						game = new Mobile(name, inv, mobileID, param1, Double.parseDouble(param2));

					}

					boolean gameExists = false;

					for ( int i = 0; i < gameList.size(); i++ ) { // Checks to see if the game already exists on this platform

						if ( name.equalsIgnoreCase(gameList.get(i).getName()) ) { // Checks for the same name

							if ( game.getSystem().equals(gameList.get(i).getSystem()) ) { // If they have the same name then check if same platform

								// Set gameExists to true;

								gameExists = true;
							}
						}
					}

					if ( gameExists ) { // IF the game already exists then display an error

						newGameError.setText("This game already exists\non this platform.");
					}
					else if ( !gameExists ) { // If the game doesn't already exist then add it to gameList

						gameList.add(game);

						// Refresh gamesGrid and close the newGame Dialog
						refreshPage();
						newGameStage.close();
					}
				}
				catch ( NumberFormatException nfe ) { // Prompt the user to fill in all information

					newGameError.setText("Please check your\ninformation");
				}
				catch ( NullPointerException npe ) { // Prompt the user to fill in all information

					newGameError.setText("Please enter all\ninformation");
				}

			}
		});
	}

	/**
	 * Method: radioBtnActions
	 * @return void
	 * Description: Radio button controls
	 */
	public void radioBtnActions() {

		// Control what is displayed based on which radio button is pressed

		rbGroup.selectedToggleProperty().addListener(new ChangeListener< Toggle >() {
			@Override
			public void changed( ObservableValue< ? extends Toggle > observable, Toggle oldValue, Toggle newValue ) {

				if ( rbGroup.getSelectedToggle() == pcBtn ) { // PC Radio Button

					// Set the text prompts of the two gameParam fields

					gameParam1Lbl.setText("Minimum Ram\nRequired: ");
					gameParam2Lbl.setText("Minium Disk Space\nRequired: ");

					// Make everything visible

					gameNameLbl.setVisible(true);
					gameNameField.setVisible(true);
					gameInventoryLbl.setVisible(true);
					gameInventoryField.setVisible(true);
					gameParam1Lbl.setVisible(true);
					gameParam1Field.setVisible(true);
					gameParam2Lbl.setVisible(true);
					gameParam2Field.setVisible(true);
					submit.setVisible(true);

					// Clear all textFields of text

					newGameError.setText("");
					gameNameField.setText("");
					gameInventoryField.setText("");
					gameParam1Field.setText("");
					gameParam2Field.setText("");
				}
				else if ( rbGroup.getSelectedToggle() == consoleBtn ) { // Console Radio Button

					// Set the text prompts of the two gameParam fields

					gameParam1Lbl.setText("Console System: ");
					gameParam2Lbl.setText("Generation: ");

					// Make everything visible

					gameNameLbl.setVisible(true);
					gameNameField.setVisible(true);
					gameInventoryLbl.setVisible(true);
					gameInventoryField.setVisible(true);
					gameParam1Lbl.setVisible(true);
					gameParam1Field.setVisible(true);
					gameParam2Lbl.setVisible(true);
					gameParam2Field.setVisible(true);
					submit.setVisible(true);

					// Clear all textFields of text

					newGameError.setText("");
					gameNameField.setText("");
					gameInventoryField.setText("");
					gameParam1Field.setText("");
					gameParam2Field.setText("");
				}
				else if ( rbGroup.getSelectedToggle() == mobileBtn ) { // Mobile Radio Button

					// Set the text prompts of the two gameParam fields

					gameParam1Lbl.setText("Device Type: ");
					gameParam2Lbl.setText("Devive Verison: ");

					// Make everything visible

					gameNameLbl.setVisible(true);
					gameNameField.setVisible(true);
					gameInventoryLbl.setVisible(true);
					gameInventoryField.setVisible(true);
					gameParam1Lbl.setVisible(true);
					gameParam1Field.setVisible(true);
					gameParam2Lbl.setVisible(true);
					gameParam2Field.setVisible(true);
					submit.setVisible(true);

					// Clear all textFields of text

					newGameError.setText("");
					gameNameField.setText("");
					gameInventoryField.setText("");
					gameParam1Field.setText("");
					gameParam2Field.setText("");
				}
			}
		});
	}

	/**
	 * Method: newGame
	 * @return void
	 * Description: Display the newGame Dialog
	 */
	public void newGame() {

		// Make sure none of the radio buttons are selected

		rbGroup.selectToggle(null);

		// Turn visibility off for everything except the radio buttons

		gameNameLbl.setVisible(false);
		gameNameField.setVisible(false);
		gameInventoryLbl.setVisible(false);
		gameInventoryField.setVisible(false);
		gameParam1Lbl.setVisible(false);
		gameParam1Field.setVisible(false);
		gameParam2Lbl.setVisible(false);
		gameParam2Field.setVisible(false);
		submit.setVisible(false);

		// Clear the textFields of text

		gameNameField.setText("");
		gameInventoryField.setText("");
		gameParam1Field.setText("");
		gameParam2Field.setText("");

		newGameStage.show();
	}

	/**
	 * Method: newGameSetup
	 * @return void
	 * Description: Sets up the layout of the newGame Dialog
	 */
	public void newGameSetup() {

		// Radio Button Assignments and Grouping

		this.pcBtn = new RadioButton("PC");
		this.pcBtn.setToggleGroup(rbGroup);
		this.consoleBtn = new RadioButton("Console");
		this.consoleBtn.setToggleGroup(rbGroup);
		this.mobileBtn = new RadioButton("Mobile");
		this.mobileBtn.setToggleGroup(rbGroup);

		this.submit = new Button("Submit");
		this.submit.setMinWidth(BUTTON_WIDTH);
		this.submit.setMinHeight(BUTTON_HEIGHT);

		this.gameNameLbl = new Label("Name: ");
		this.gameInventoryLbl = new Label("Inventory: ");
		this.gameParam1Lbl = new Label();
		this.gameParam2Lbl = new Label();
		this.newGameError = new Label();

		this.gameNameField = new TextField();
		this.gameNameField.setMinWidth(this.newGameStage.getWidth() - 150);

		this.gameInventoryField = new TextField();
		this.gameInventoryField.setMinWidth(this.newGameStage.getWidth() - 150);

		this.gameParam1Field = new TextField();
		this.gameParam1Field.setMinWidth(this.newGameStage.getWidth() - 150);

		this.gameParam2Field = new TextField();
		this.gameParam2Field.setMinWidth(this.newGameStage.getWidth() - 150);

		// Placements on newGameGridPane
		this.newGameGridPane.add(pcBtn, 0, 0);
		this.newGameGridPane.add(consoleBtn, 0, 1);
		this.newGameGridPane.add(mobileBtn, 0, 2);
		this.newGameGridPane.add(gameNameLbl, 0, 3);
		this.newGameGridPane.add(gameNameField, 1, 3);
		this.newGameGridPane.add(gameInventoryLbl, 0, 4);
		this.newGameGridPane.add(gameInventoryField, 1, 4);
		this.newGameGridPane.add(gameParam1Lbl, 0, 5);
		this.newGameGridPane.add(gameParam1Field, 1, 5);
		this.newGameGridPane.add(gameParam2Lbl, 0, 6);
		this.newGameGridPane.add(gameParam2Field, 1, 6);
		this.newGameGridPane.add(submit, 0, 7);
		this.newGameGridPane.add(newGameError, 1, 8);
	}

	/**
	 * Method: startNewGame
	 * @return void
	 * Description: newGame Dialog Basics
	 */
	public void startNewGame() {

		// Creating the GridPane

		this.newGameGridPane = new GridPane();
		this.newGameGridPane.setPadding(DEFAULT);
		this.newGameGridPane.setHgap(20);
		this.newGameGridPane.setVgap(20);

		this.newGameScene = new Scene(newGameGridPane, NEW_GAME_WIDTH, NEW_GAME_HEIGHT);

		// Stage Setup

		this.newGameStage = new Stage();
		this.newGameStage.setScene(newGameScene);
		this.newGameStage.setMinWidth(NEW_GAME_WIDTH);
		this.newGameStage.setMinHeight(NEW_GAME_HEIGHT);
		this.newGameStage.setTitle("New Game");

		// Window Closing Handler

		this.newGameStage.setOnCloseRequest(new EventHandler< WindowEvent >() {
			@Override
			public void handle( WindowEvent event ) {

				refreshPage();
				newGameStage.close();
			}
		});

		newGameSetup();
	}

	/**
	 * Method: optionsSetup
	 * @return void
	 * Description: Sets up the Options Dialog layout
	 */
	public void optionsSetup() {

		// No game is selected

		this.gameSelected = null;

		this.deleteBtn = new Button("Delete");
		this.gameLbl = new Label();

		// Label and Prompt the search field
		this.searchField = new TextField("Search by Game ID");
		this.searchField.setMinWidth(OPTIONS_WIDTH - BUTTON_WIDTH - 30);
		this.searchMenuBtn = new Button("Search");
		this.searchMenuBtn.setMinWidth(BUTTON_WIDTH);
		this.searchPane = new BorderPane();

		this.searchPane.setLeft(searchField);
		this.searchPane.setRight(searchMenuBtn);

		this.gameDescriptionPane = new BorderPane();
		this.gameDescriptionPane.setPadding(new Insets(15));

		this.gameDescription.setTop(searchPane);
		this.gameDescription.setCenter(gameDescriptionPane);
		this.gameDescription.setLeft(gameLbl);

		gameLbl.setStyle("-fx-padding:15");

		this.gameDescription.setBottom(deleteBtn);
	}

	/**
	 * Method: startOptions
	 * @return void
	 * Description: Options Dialog Basics
	 */
	public void startOptions() {

		// Creating the BorderPanes

		this.gameOptions = new BorderPane();
		this.gameOptions.setPadding(DEFAULT);
		this.gameOptionsScene = new Scene(gameOptions, OPTIONS_WIDTH, OPTIONS_HEIGHT);

		this.gameDescription = new BorderPane();
		this.gameDescription.setPadding(DEFAULT);
		this.gameViewScene = new Scene(gameDescription, OPTIONS_WIDTH, OPTIONS_HEIGHT);

		// Setup the Stage

		this.optionsStage = new Stage();
		this.optionsStage.setMinWidth(OPTIONS_WIDTH);
		this.optionsStage.setMinHeight(OPTIONS_HEIGHT);

		// Window Closing Handler

		this.optionsStage.setOnCloseRequest(new EventHandler< WindowEvent >() {
			@Override
			public void handle( WindowEvent event ) {

				optionsStage.close();
				refreshPage();
			}
		});

		optionsSetup();
	}

	/**
	 * Method: setupStage
	 * @return void
	 * Description: Sets up the stage
	 */
	public void setupStage() {

		this.gameList = new ArrayList< Game >();

		// Game Display Area

		this.gridScroll = new ScrollPane();
		this.gridScroll.setStyle("-fx-background-color:transparent");
		this.gridScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		this.gridScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		this.gridScroll.setFitToWidth(true);

		this.gamesGrid = new FlowPane();
		this.gamesGrid.setPadding(new Insets(15, 50, 15, 50));
		this.gamesGrid.setHgap(30);
		this.gamesGrid.setVgap(30);
		this.gamesGrid.setPrefWrapLength(WIDTH - 50);

		// Control Bar

		this.optionsGridPane = new BorderPane();
		this.optionsGridPane.setPadding(DEFAULT);
		this.optionsGrid = new GridPane();

		this.newGameBtn = new Button("New Game");
		this.newGameBtn.setMaxWidth(BUTTON_WIDTH);
		this.newGameBtn.setMinHeight(BUTTON_HEIGHT);

		this.searchBtn = new Button("Search");
		this.searchBtn.setMaxWidth(BUTTON_WIDTH);
		this.searchBtn.setMinHeight(BUTTON_HEIGHT);

		this.saveBtn = new Button("Save");
		this.saveBtn.setMaxWidth(BUTTON_WIDTH);
		this.saveBtn.setMinHeight(BUTTON_HEIGHT);

		this.exitBtn = new Button("Exit");
		this.exitBtn.setMaxWidth(BUTTON_WIDTH);
		this.exitBtn.setMinHeight(BUTTON_HEIGHT);

		// Control Bar Setup
		this.optionsGridPane.setLeft(this.optionsGrid);
		this.optionsGridPane.setRight(this.exitBtn);
		this.optionsGrid.setHgap(10);
		this.optionsGrid.setVgap(10);
		this.optionsGrid.setMaxWidth(( WIDTH - 20 ) - BUTTON_WIDTH);
		this.optionsGrid.setPadding(new Insets(0, 0, 10, 0));
		this.optionsGrid.add(newGameBtn, 0, 0);
		this.optionsGrid.add(searchBtn, 1, 0);
		this.optionsGrid.add(saveBtn, 2, 0);

		this.errorLbl = new Label();
		this.gameViewList = new ArrayList< GameView >();

		this.gridScroll.setContent(gamesGrid);

		collectGames();
		this.pane.setTop(optionsGridPane);
		this.pane.setCenter(gridScroll);
		this.pane.setBottom(errorLbl);

		startOptions();
		startNewGame();

		btnActions();
		radioBtnActions();
	}

	/**
	 * Method: start
	 * @param args
	 * @throws Exception
	 * Description: Game Display Basics
	 */
	public void start( Stage args ) throws Exception {

		// Creating the BorderPane

		pane = new BorderPane();
		scene = new Scene(pane, WIDTH, HEIGHT);

		// Setup the Stage
		mainStage = new Stage();
		mainStage.setScene(scene);
		mainStage.setMinWidth(WIDTH);
		mainStage.setMinHeight(HEIGHT);
		mainStage.setTitle("My Game Inventory");
		mainStage.show();

		// Window Closing Handler

		mainStage.setOnCloseRequest(new EventHandler< WindowEvent >() {
			@Override
			public void handle( WindowEvent event ) {

				saveGames();
				newGameStage.close();
				optionsStage.close();
				mainStage.close();
				Platform.exit();
			}
		});

		setupStage();
	}
}
