import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class: Game
 * @author Mike Deiters
 * @version 1.0
 * November 19, 2015
 * ITEC 2110
 *
 * Description: Setup a stage
 *
 * Purpose: Build the game
 */
public class Game extends Scene {

    private final int WIDTH = 512;
    private final int HEIGHT = 512;

    private ArrayList< KeyCode > input;
    private Group root;
    private Canvas canvas;
    private GraphicsContext gc;
    private Font font;
    private Lock gameLock;

    private Sprite player;
    private ArrayList< Sprite > sprites;
    private int score;
    private SpriteTester tester;

    /**
     * Constructor: Game
     */
    public Game( Group root, Sprite player, ArrayList< Sprite > sprites , SpriteTester tester) {
        super(root);
        this.input = new ArrayList<>();
        this.root = root;
        this.canvas = new Canvas(WIDTH, HEIGHT);
        this.root.getChildren().add(canvas);
        this.gc = canvas.getGraphicsContext2D();
        this.font = Font.font("Helvetica", FontWeight.BOLD, 24);
        this.gc.setFont(font);
        this.gc.setStroke(Color.BLACK);
        this.gc.setLineWidth(1);
        this.gameLock = new ReentrantLock();
        this.player = player;
        this.sprites = sprites;
        this.score = 0;
        this.tester = tester;
        actions();
    }

    /**
     * Method: getInput
     * @return input ArrayList<KeyCode>
     */
    public ArrayList< KeyCode > getInput() {

        gameLock.lock();
        ArrayList< KeyCode > input = this.input;
        gameLock.unlock();
        return input;
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
     * Method: getPlayer
     * @return player Sprite
     */
    public Sprite getPlayer() {
        return player;
    }

    /**
     * Method: getSprites
     * @return sprites ArrayList<Sprite>
     */
    public ArrayList< Sprite > getSprites() {
        return sprites;
    }

    /**
     * Method: getScore
     * @return score int
     */
    public int getScore() {
        return score;
    }

    /**
     * Method: actions
     * Description: Create event handlers
     */
    private void actions() {

        this.setOnKeyPressed(new EventHandler< KeyEvent >() {
            @Override
            public void handle( KeyEvent event ) {

                if ( !input.contains(event.getCode()) ) {

                    input.add(event.getCode());
                }
            }
        });

        this.setOnKeyReleased(new EventHandler< KeyEvent >() {
            @Override
            public void handle( KeyEvent event ) {

                if ( input.contains(event.getCode()) ) {

                    input.remove(event.getCode());
                }
            }
        });
    }

    /**
     * Method: updateGame
     * Description: Update the sprites in the game
     */
    public void updateGame() {

        // game logic

        player.setVelocityX(0);
        if ( input.contains(KeyCode.LEFT) ) {
            player.addVelocity(-150, 0);
        }
        if ( input.contains(KeyCode.RIGHT) ) {
            player.addVelocity(150, 0);
        }
        if ( input.contains(KeyCode.UP) && player.getVelocityY() == 0 ) {
            player.addVelocity(0, -150);
        }
        if ( input.contains(KeyCode.DOWN) ) {
            player.addVelocity(0, 50);
        }

        // collision detection

        Iterator< Sprite > aceIter = sprites.iterator();
        while ( aceIter.hasNext() ) {
            Sprite ace = aceIter.next();
            if ( player.intersects(ace) ) {
                aceIter.remove();
                score++;
            }
        }
    }

    /**
     * Method: render
     * @param time double
     * Description: Render and display the game
     */
    public void render( double time ) {

        player.update(time);

        // Scene border detection

        if ( player.getPositionX() < 0 ) {
            player.setPositionX(0);
            player.setVelocityX(0);
        }
        if ( player.getPositionY() < 0 ) {
            player.setPositionY(0);
            player.setVelocityY(0);
        }
        if ( player.getPositionX() + player.getWidth() > canvas.getWidth() ) {
            player.setPositionX(canvas.getWidth() - player.getWidth());
            player.setVelocityX(0);
        }
        if ( player.getPositionY() + player.getHeight() > canvas.getHeight() ) {
            player.setPositionY(canvas.getHeight() - player.getHeight());
            player.setVelocityY(0);
        }

        gc.clearRect(0, 0, this.getWidth(), this.getHeight());

        if ( player.getImage() != null ) {

            player.render(gc);
        }
        else if ( player.getAnimation() != null ) {

        }

        for ( Sprite sprite : sprites ) {
            sprite.update(time);
            sprite.render(gc);
        }

        String pointsText = "Score: " + score;
        gc.fillText(pointsText, 360, 36);
        gc.strokeText(pointsText, 360, 36);
    }
}
