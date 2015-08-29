package HomeWork1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
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

	private final int WIDTH = 800;
	private final int HEIGHT = 800;
	private final int OPTIONS_WIDTH = 375;
	private final int OPTIONS_HEIGHT = 305;
	private final int BUTTON_WIDTH = 100;
	private final int BUTTON_HEIGHT = 30;
	private final Insets DEFAULT = new Insets(10, 10, 10, 10);

	// Main Scene

	private BorderPane pane;
	private Scene scene;
	private Stage mainStage;

	private FlowPane gamesGrid;
	private BorderPane optionsGridPane;
	private GridPane optionsGrid;
	private Label errorLbl;
	private ArrayList< GameView > gameViewList;
	private ArrayList< Game > gameList;

	private Button newGameBtn;
	private Button searchBtn;
	private Button saveBtn;
	private Button exitBtn;

	// Menus

	private BorderPane gameOptions;
	private BorderPane gameDescription;
	private Scene gameOptionsScene;
	private Scene gameViewScene;
	private Stage optionsStage;

	private TextField searchField;
	private Label gameLbl;
	private Button deleteBtn;
	private Button searchMenuBtn;
	private BorderPane searchPane;
	private BorderPane gameDescriptionPane;

	// Classes

	private GameCollect gameCollect;
	private GameView gameSelected;

	/**
	 * Method: collectGames
	 * @return void
	 * Description: Reads in all of the game from the file
	 */
	public void collectGames() {

		ReadFile rf = new ReadFile();
		try {

			this.gameSelected = new GameView();
			this.gameCollect = new GameCollect(System.getProperty("os.name"));
			rf.readFile(this.gameCollect.getGameFile());
		}
		catch ( FileMissingException fme ) {

			errorLbl.setText(fme.getMessage());
		}
		catch ( NullPointerException npe ) {

			System.out.print(npe.getMessage());
		}

		ArrayList< String > list = rf.getFileList();
		for ( int i = 0; i < list.size(); i++ ) {
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

			switch ( system ) {
				case "PC":

					this.gameList.add(new PC(name, Integer.parseInt(inventory), Integer.parseInt(id), Integer.parseInt(param1), Integer.parseInt(param2)));
					break;

				case "Console":

					this.gameList.add(new Console(name, Integer.parseInt(inventory), Integer.parseInt(id), param1, param2));
					break;

				case "Mobile":

					this.gameList.add(new Mobile(name, Integer.parseInt(inventory), Integer.parseInt(id), param1, Double.parseDouble(param2)));
					break;

				default:

					errorLbl.setText("Game information is missing.");

					// Set i to the size of list so the loop will end due to an error

					i = list.size();
			}
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

			for ( int i = 0; i < gameList.size(); i++ ) {

				gameStringList.add(gameList.get(i).toFile());
			}

			WriteFile wf = new WriteFile(gameStringList, gameCollect.getGameFile());

		}
		catch ( FileMissingException fme ) {

			errorLbl.setText(fme.getMessage());
		}
		catch ( NullPointerException npe ) {

		}
	}

	public void refreshPage() {

		gameViewList.clear();

		for ( int i = 0; i < gameList.size(); i++ ) {

			Game gameS = gameList.get(i);
			gameViewList.add(new GameView(gameS));
			gamesGrid.getChildren().add(gameViewList.get(i).getPane());

			gameViewList.get(i).getPane().setOnMouseClicked(new EventHandler< MouseEvent >() {
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

	public void openGame( Game game ) {

		gameSelected = new GameView(game);
		ImageView coverArt = gameSelected.getCoverArt();
		gameDescriptionPane.setLeft(coverArt);
		gameDescriptionPane.setRight(gameLbl);

		gameLbl.setText(game.toString());
	}

	public Game searchGames( int gameId ) {

		Game game = null;

		for ( int i = 0; i < gameList.size(); i++ ) {

			if ( gameList.get(i).getGameId() == gameId ) {

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

		newGameBtn.setOnAction(new EventHandler< ActionEvent >() {
			@Override
			public void handle( ActionEvent event ) {

			}
		});

		searchBtn.setOnAction(new EventHandler< ActionEvent >() {
			@Override
			public void handle( ActionEvent event ) {

				searchField.setText("Search by Game ID");
				optionsStage.setScene(gameViewScene);
				optionsStage.setTitle("Search");
				optionsStage.show();
			}
		});

		saveBtn.setOnAction(new EventHandler< ActionEvent >() {
			@Override
			public void handle( ActionEvent event ) {

				saveGames();
			}
		});

		exitBtn.setOnAction(new EventHandler< ActionEvent >() {
			@Override
			public void handle( ActionEvent event ) {

				saveGames();
				mainStage.close();
				Platform.exit();
			}
		});

		searchMenuBtn.setOnAction(new EventHandler< ActionEvent >() {
			@Override
			public void handle( ActionEvent event ) {

				try {

					int gameId = Integer.parseInt(searchField.getText());
					openGame(searchGames(gameId));
				}
				catch ( NumberFormatException nfe ) {

					searchField.setText("Invalid Game ID");
				}
				catch ( NullPointerException npe ) {

					searchField.setText("Invalid Game ID");
				}
			}
		});

		deleteBtn.setOnAction(new EventHandler< ActionEvent >() {
			@Override
			public void handle( ActionEvent event ) {

				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Delete Confirmation");
				alert.setHeaderText("Are you sure you want to delete this?");
				alert.setContentText("This will pernamently delete this game.");

				Optional< ButtonType > result = alert.showAndWait();
				if ( result.get() == ButtonType.OK ) {

					// If the user presses ok

				}
				else {

					// If the user pressed cancel or closed the dialog

				}
			}
		});
	}

	public void optionsSetup() {

		this.deleteBtn = new Button("Delete");
		this.gameLbl = new Label();
		this.searchField = new TextField("Search by Game ID");
		this.searchField.setMinHeight(30);
		this.searchField.setMinWidth(OPTIONS_WIDTH - BUTTON_WIDTH - 30);
		this.searchMenuBtn = new Button("Search");
		this.searchMenuBtn.setMinWidth(BUTTON_WIDTH);
		this.searchMenuBtn.setMinHeight(BUTTON_HEIGHT);
		this.searchPane = new BorderPane();

		this.searchPane.setLeft(searchField);
		this.searchPane.setRight(searchMenuBtn);

		this.gameDescriptionPane = new BorderPane();
		this.gameDescriptionPane.setPadding(new Insets(15));

		this.gameDescription.setTop(searchPane);
		this.gameDescription.setCenter(gameDescriptionPane);
		this.gameDescription.setLeft(gameLbl);

//		searchField.setStyle("-fx-padding:15");
		gameLbl.setStyle("-fx-padding:15");

		this.gameDescription.setBottom(deleteBtn);
	}

	public void startOptions() {

		this.gameOptions = new BorderPane();
		this.gameOptions.setPadding(DEFAULT);
		this.gameOptionsScene = new Scene(gameOptions, OPTIONS_WIDTH, OPTIONS_HEIGHT);

		this.gameDescription = new BorderPane();
		this.gameDescription.setPadding(DEFAULT);
		this.gameViewScene = new Scene(gameDescription, OPTIONS_WIDTH, OPTIONS_HEIGHT);

		this.optionsStage = new Stage();
		this.optionsStage.setMinWidth(OPTIONS_WIDTH);
		this.optionsStage.setMinHeight(OPTIONS_HEIGHT);

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

		// Main Screen

		this.gamesGrid = new FlowPane();
		this.gamesGrid.setPadding(new Insets(15));
		this.gamesGrid.setHgap(30);
		this.gamesGrid.setVgap(30);

		this.optionsGridPane = new BorderPane();
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

		collectGames();
		this.pane.setTop(optionsGridPane);
		this.pane.setCenter(gamesGrid);
		this.pane.setBottom(errorLbl);

		startOptions();

		btnActions();
	}

	/**
	 * Method: start
	 * @param args
	 * @throws Exception
	 * Description: Sets up the basic parts of the applications
	 */
	public void start( Stage args ) throws Exception {

		pane = new BorderPane();
		pane.setPadding(DEFAULT);
		scene = new Scene(pane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(scene);
		mainStage.setMinWidth(WIDTH);
		mainStage.setMinHeight(HEIGHT);
		mainStage.setTitle("My Game Inventory");
		mainStage.show();

		mainStage.setOnCloseRequest(new EventHandler< WindowEvent >() {
			@Override
			public void handle( WindowEvent event ) {

				saveGames();
				mainStage.close();
				Platform.exit();
			}
		});

		setupStage();
	}
}
