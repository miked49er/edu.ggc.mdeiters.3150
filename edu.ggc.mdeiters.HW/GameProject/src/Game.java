import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class: Game
 * @author Mike Deiters
 * @version 1.0
 * November 22, 2015
 * ITEC 3150-01
 *
 * Description: Scene setup for games
 *
 * Purpose: Game template
 */
public abstract class Game extends Scene {

    private ArrayList<KeyCode> input;
    private Group root;
    private Canvas canvas;
    private GraphicsContext gc;
    private Font font;
    private Lock gameLock;

    private Player player;
    private ArrayList<Sprite> sprites;
    private double score;
    private int highScore;
    private boolean gameOver;
    private Media gameOverMedia;
    private MediaPlayer gameOverSound;
    private Media bgMedia;
    private MediaPlayer bgMusic;

    /**
     *  Constructor: Game
     * @param width int
     * @param height int
     * @param root Group
     * @param player Player
     */
    public Game( int width, int height, Group root, Player player ) {
        super(root);
        this.input = new ArrayList<>();
        this.root = root;
        this.canvas = new Canvas(width, height);
        this.root.getChildren().add(canvas);
        this.gc = canvas.getGraphicsContext2D();
        this.font = Font.font("Helvetica", FontWeight.BOLD, 24);
        this.gc.setFont(font);
        this.gc.setStroke(Color.BLACK);
        this.gc.setLineWidth(1);
        this.gameLock = new ReentrantLock();
        this.player = player;
        this.sprites = new ArrayList<>();
        this.score = 0;
        this.highScore = 0;
        actions();
        this.gameOver = false;
    }

    /**
     * Method: getInput
     * @return input ArrayList<KeyCode>
     */
    public ArrayList<KeyCode> getInput() {

        gameLock.lock();
        ArrayList<KeyCode> input = this.input;
        gameLock.unlock();
        return input;
    }

    /**
     * Method: addKeyCode
     * @param code KeyCode
     * Description: Add code to input
     */
    public void addKeyCode( KeyCode code ) {

        input.add(code);
    }

    /**
     * Method: removeKeyCode
     * @param code KeyCode
     * Description: Remove code from input
     */
    public void removeKeyCode( KeyCode code ) {

        input.remove(code);
    }

    /**
     * Method: getRoot
     * @return root Group
     */
    public Group getGroup() {
        return root;
    }

    /**
     * Method: getCanvas
     * @return canvas Canvas
     */
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * Method: getGc
     * @return gc GraphicsContext
     */
    public GraphicsContext getGc() {

        gameLock.lock();
        GraphicsContext gc = this.gc;
        gameLock.unlock();
        return gc;
    }

    /**
     * Method: getFont
     * @return font Font
     */
    public Font getFont() {
        return font;
    }

    /**
     * Method: setFont
     * @param font Font
     * Description: Set the game font
     */
    public void setFont( Font font ) {

        this.font = font;
        this.gc.setFont(font);
    }

    /**
     * Method: setStroke
     * @param color Color
     * @param strokeWidth int
     * Description: Set the stroke style
     */
    public void setStroke( Color color, int strokeWidth ) {

        this.gc.setStroke(color);
        this.gc.setLineWidth(strokeWidth);
    }

    /**
     * Method: getPlayer
     * @return player Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Method: getSprites
     * @return sprites ArrayList<Sprite>
     */
    public ArrayList<Sprite> getSprites() {
        return sprites;
    }

    /**
     * Method: addSprite
     * @param s Sprite
     * Description: Add s to sprites
     */
    public void addSprite( Sprite s ) {

        sprites.add(s);
    }

    /**
     * Method: removeSprite
     * @param s Sprite
     * Description: Remove s from sprites
     */
    public void removeSprite( Sprite s ) {

        sprites.remove(s);
    }

    /**
     * Method: clearSprite
     * Description: clearSprites
     */
    public void clearSprites() {

        sprites.clear();
    }

    /**
     * Method: getScore
     * @return score double
     */
    public double getScore() {
        return score;
    }

    /**
     * Method: addScore
     * @param score int
     * Description: Add score to this.score
     */
    public void addScore( double score ) {
        this.score += score;

        if ( this.score > highScore ) {
            setHighScore((int) this.score);
        }
    }

    /**
     * Method: clearScore
     * Description: Reset the score
     */
    public void clearScore() {

        this.score = 0;
    }

    /**
     * Method: getHighScore
     * @return highScore int
     */
    public int getHighScore() {
        return highScore;
    }

    /**
     * Method: setHighScore
     * @param highScore int
     */
    public void setHighScore( int highScore ) {
        this.highScore = highScore;
    }

    /**
     * MethodL getGameOverSound
     * @return gameOverMedia Media
     */
    public Media getGameOverSound() {
        return gameOverMedia;
    }

    /**
     * Method: setGameOverSound
     * @param str String
     * Description: Create a new game over sound player
     */
    public void setGameOverSound( String str ) {

        URL path = Game.class.getResource(str);
        File soundFile = new File(path.getPath());
        this.gameOverMedia = new Media(soundFile.toURI().toString());
        this.gameOverSound = new MediaPlayer(gameOverMedia);
    }

    /**
     * Method: setGameOverSound
     * @param gameOverMedia Media
     */
    public void setGameOverSound( Media gameOverMedia ) {
        this.gameOverMedia = gameOverMedia;
    }

    /**
     * Method: playGameOverSound
     * Description: Play the game over sound
     */
    public void playGameOverSound() {

        if ( gameOverSound != null ) {

            gameOverSound.play();
        }
    }

    /**
     * Method: stopGameOverSound
     * Description: Stop the game over sound
     */
    public void stopGameOverSound() {

        if ( gameOverSound != null ) {

            gameOverSound.stop();
        }
    }

    /**
     * Method: getbgMusic
     * @return bgMedia Media
     */
    public Media getbgMusic() {

        return bgMedia;
    }

    /**
     * Method: setBgMusic
     * @param bgMedia Media
     * Description: Create a background music player
     */
    public void setBgMusic( Media bgMedia ) {

        this.bgMedia = bgMedia;
        this.bgMusic = new MediaPlayer(bgMedia);
    }

    /**
     * Method: setBgMusic
     * @param str String
     * Description: Create a background music player
     */
    public void setBgMusic( String str ) {

        URL path = Game.class.getResource(str);
        File soundFile = new File(path.getPath());
        this.bgMedia = new Media(soundFile.toURI().toString());
        this.bgMusic = new MediaPlayer(bgMedia);
    }

    /**
     * Method: playMusic
     * @param play boolean
     * Description: Play bgMusic if true
     *             Stop bgMusic if false
     */
    public void playMusic( boolean play ) {

        if ( play && bgMusic != null ) {

            bgMusic.play();
        }
        else if ( !play && bgMusic != null ) {

            bgMusic.stop();
        }
    }

    /**
     * Method: isGameOver
     * @return gameOver boolean
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Method: setGameOver
     * @param gameOver boolean
     */
    public void setGameOver( boolean gameOver ) {
        this.gameOver = gameOver;
    }

    /**
     * Method: gameOver
     * Description: Display a game over message and play the game over sound if it exists
     */
    public abstract void gameOver();

    /**
     * Method: actions
     * Description: Create event handlers
     */
    public abstract void actions();

    /**
     * Method: updateGame
     * @param time double
     * @throws InterruptedException
     * Description: Update the sprites in the game
     */
    public abstract void updateGame( double time ) throws InterruptedException;

    /**
     * Method: render
     * @param time double
     * @throws InterruptedException
     * Description: Render and display the game
     */
    public abstract void render( double time ) throws InterruptedException;

    /**
     * Method: reset
     * Description: Reset the game
     */
    public abstract void reset();
}
