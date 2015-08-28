package HomeWork1;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.awt.*;

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
	private Game game;
	private BorderPane pane;
	private GameCollect gameCollect;

	/**
	 * Constructor: GameView
	 * @throws FileMissingException
	 */
	public GameView() throws FileMissingException {
		this.gameCollect = new GameCollect(System.getProperty("os.name"));
//		this.coverArt = new ImageView(gameCollect.findGame(null));
		this.gameName = new Label();
		this.pane = new BorderPane();
	}

	/**
	 * Constructor: GameView
	 * @param game Game to be displayed
	 * @throws FileMissingException
	 * Description: Set up a borderpane for the game to be displayed in
	 */
	public GameView( Game game ) throws FileMissingException {
		this.gameCollect = new GameCollect(System.getProperty("os.name"));
		this.coverArt = new ImageView(gameCollect.findGame(game));
		this.gameName = new Label(game.getName());
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
		this.pane = new BorderPane();
	}

	public ImageView getCoverArt() {

		return coverArt;
	}

	public void setCoverArt( ImageView coverArt ) {

		this.coverArt = coverArt;

	}

	public void setCoverArt( String coverArt ) {

		this.coverArt = new ImageView(coverArt);
	}

	public Label getGameName() {
		return gameName;

	}

	public void setGameName( Label gameName ) {
		this.gameName = gameName;

	}

	public Game getGame() {
		return game;

	}

	public void setGame( Game game ) {
		this.game = game;

	}

	public BorderPane getPane() {

		this.coverArt.setFitWidth(225);
		this.coverArt.setFitHeight(300);
		this.gameName.setStyle("-fx-font-size:18; -fx-padding:10");

		this.pane.setTop(this.coverArt);
		this.pane.setCenter(this.gameName);
		this.pane.setMaxHeight(100);
		this.pane.setMaxWidth(50);

		return pane;
	}

	public void setPane( BorderPane pane ) {
		this.pane = pane;

	}
}
