package HomeWork1;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * Class: GameView
 * @author Mike Deiters
 * @version 1.0
 * August 27, 2015
 * ITEC 3150-01
 *
 * Description: Game output display
 *
 * Purpose: Display the games
 */
public class GameView {

	private ImageView coverArt;
	private Label gameName;
	private Label system;
	private Game game;
	private BorderPane lblPane;
	private BorderPane pane;
	private GameCollect gameCollect;

	/**
	 * Constructor: GameView
	 * @throws FileMissingException
	 */
	public GameView() throws FileMissingException {
		this.gameCollect = new GameCollect();
		this.coverArt = new ImageView(gameCollect.findGame(null));
		this.gameName = new Label();
		this.system = new Label();
		this.lblPane = new BorderPane();
		this.pane = new BorderPane();
	}

	/**
	 * Constructor: GameView
	 * @param game Game to be displayed
	 * @throws FileMissingException
	 * Description: Set up a borderpane for the game to be displayed in
	 */
	public GameView( Game game ) throws FileMissingException {
		this.gameCollect = new GameCollect();
		this.coverArt = new ImageView(gameCollect.findGame(game));
		this.gameName = new Label(game.getName());
		this.system = new Label(game.getSystem());
		this.lblPane = new BorderPane();
		this.pane = new BorderPane();
	}

	/**
	 * Constructor: GameView
	 * @param coverArt String of the game cover art location
	 * @param game Game to be displayed
	 * @throws FileMissingException
	 * Description: Set up a borderpane for the game to be displayed in
	 */
	public GameView( String coverArt, Game game ) throws FileMissingException {
		this.coverArt = new ImageView(coverArt);
		this.game = game;
		this.gameName = new Label(game.getName());
		this.system = new Label(game.getSystem());
		this.lblPane = new BorderPane();
		this.pane = new BorderPane();
	}

	/**
	 * Constructor: GameView
	 * @param coverArt ImageView of the game cover art
	 * @param game Game to be displayed
	 * @throws FileMissingException
	 * Description: Set up a borderpane for the game to be displayed in
	 */
	public GameView( ImageView coverArt, Game game ) throws FileMissingException {
		this.coverArt = coverArt;
		this.game = game;
		this.gameName = new Label(game.getName());
		this.system = new Label(game.getSystem());
		this.lblPane = new BorderPane();
		this.pane = new BorderPane();
	}

	/**
	 * Method: getCoverArt
	 * @return coverArt ImageView of the coverArt
	 */
	public ImageView getCoverArt() {

		this.coverArt.setFitWidth(150);
		this.coverArt.setFitHeight(200);
		return coverArt;
	}

	/**
	 * Method: setCoverArt
	 * @param coverArt ImageView of the coverArt
	 */
	public void setCoverArt( ImageView coverArt ) {

		this.coverArt = coverArt;

	}

	/**
	 * Method: setCoverArt
	 * @param coverArt String of the coverArt file
	 */
	public void setCoverArt( String coverArt ) {

		this.coverArt = new ImageView(coverArt);
	}

	/**
	 * Method: getGameName
	 * @return gameName Label of the game name
	 */
	public Label getGameName() {
		return gameName;

	}

	/**
	 * Method: setGameName
	 * @param gameName Label of the coverArt
	 */
	public void setGameName( Label gameName ) {
		this.gameName = gameName;

	}

	/**
	 * Method: getGame
	 * @return game Game to be viewed
	 */
	public Game getGame() {
		return game;

	}

	/**
	 * Method: setGame
	 * @param game Game to be viewed
	 */
	public void setGame( Game game ) {
		this.game = game;

	}

	/**
	 * Method: getPane
	 * @return pane BorderPane to view the game on
	 * Description: Sets up the the borderPane for the game to be viewed on
	 */
	public BorderPane getPane() {

		this.coverArt.setFitWidth(170);
		this.coverArt.setFitHeight(225);
		this.gameName.setStyle("-fx-font-size:18; -fx-padding:10");
		this.lblPane.setTop(this.gameName);
		this.lblPane.setBottom(this.system);
		this.lblPane.setMaxHeight(50);
		this.lblPane.setAlignment(this.gameName, Pos.TOP_CENTER);
		this.lblPane.setAlignment(this.system, Pos.BOTTOM_CENTER);

		this.pane.setTop(this.coverArt);
		this.pane.setCenter(this.lblPane);
		this.pane.setMaxHeight(100);
		this.pane.setMaxWidth(50);

		return pane;
	}

	/**
	 * Method: setPane
	 * @param pane BorderPane to view the game on
	 */
	public void setPane( BorderPane pane ) {
		this.pane = pane;

	}
}
