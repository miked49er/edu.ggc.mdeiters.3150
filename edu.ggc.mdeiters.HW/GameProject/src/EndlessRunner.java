import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
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
 * Class: EndlessRunner
 * @author Mike Deiters
 * @version 1.0
 * November 19, 2015
 * ITEC 2110
 *
 * Description: Setup a stage
 *
 * Purpose: Build the endless runner game
 */
public class EndlessRunner extends Game {

    private GameLauncher launcher;
    private boolean pause;

    /**
     * Constructor: EndlessRunner
     * @param width int
     * @param height int
     * @param root Group
     * @param player Player
     */
    public EndlessRunner( int width, int height, Group root, Player player, GameLauncher launcher ) {
        super(width, height, root, player);
        setFont(Font.font("Helvetica", FontWeight.LIGHT, 18));
        this.launcher = launcher;
        this.pause = false;
    }

    /**
     * Method: generateObsticals
     * Description: Generate 800 cordinates of obsticals
     */
    public void generateObsticals() {

        int stage = 0;
        double x = this.getCanvas().getWidth();
        double y = this.getCanvas().getHeight();

        while ( stage < 800 ) {

            int obstical;

            if ( getScore() < 400 ) {

                obstical = (int) ( Math.random() * 256 ) % 6;
            }
            else {

                obstical = (int) ( Math.random() * 256 ) % 10;
            }
            int distance = (int) ( Math.random() * 342 ) % 500 + 200;
            x += distance;
            stage += distance;
            Sprite sprite;

            switch ( obstical ) {
                case 0: // Small Obstical
                    sprite = new SmallObstical();
                    sprite.setPositionY(y - sprite.getHeight());
                    break;
                case 1: // Small Obstical
                    sprite = new SmallObstical();
                    sprite.setPositionY(y - sprite.getHeight());
                    break;
                case 2: // Small Obstical
                    sprite = new SmallObstical();
                    sprite.setPositionY(y - sprite.getHeight());
                    break;
                case 3: // Small Obstical
                    sprite = new SmallObstical();
                    sprite.setPositionY(y - sprite.getHeight());
                    break;
                case 4: // Large Obstical
                    sprite = new LargeObstical();
                    sprite.setPositionY(y - sprite.getHeight());
                    break;
                case 5: // Large Obstical
                    sprite = new LargeObstical();
                    sprite.setPositionY(y - sprite.getHeight());
                    break;
                case 6: // Medium Flying Obstical
                    sprite = new SmallObstical();
                    sprite.setPositionY(y - ( sprite.getHeight() * 2 ));
                    break;
                case 7: // Medium Flying Obstical
                    sprite = new SmallObstical();
                    sprite.setPositionY(y - ( sprite.getHeight() * 2 ));
                    break;
                case 8: // High Flying Obstical
                    sprite = new SmallObstical();
                    sprite.setPositionY(y - ( sprite.getHeight() * 3 ));
                    break;
                case 9: // High Flying Obstical
                    sprite = new SmallObstical();
                    sprite.setPositionY(y - ( sprite.getHeight() * 3 ));
                    break;
                default: // Small Obstical
                    sprite = new SmallObstical();
                    sprite.setPositionY(y - sprite.getHeight());
                    break;
            }

            sprite.setPositionX(x);
            ;
            stage += sprite.getWidth();
            addSprite(sprite);
        }
    }

    /**
     * Method: placeText
     * @param str String
     * @param x double
     * @param y double
     * Description: Draw text on the canvas
     */
    public void placeText( String str, double x, double y ) {

        getGc().fillText(str, x, y);
        getGc().strokeText(str, x, y);
    }

    /**
     * Method: actions
     * Description: Create event handlers
     */
    public void actions() {

        this.setOnKeyPressed(new EventHandler< KeyEvent >() {
            @Override
            public void handle( KeyEvent event ) {

                if ( !getInput().contains(event.getCode()) ) {

                    addKeyCode(event.getCode());

                    if ( getInput().contains(KeyCode.ESCAPE) ) {

                        pause = false;
                        reset();
                    }
                    if ( event.getCode() == KeyCode.UP || event.getCode() == KeyCode.SPACE ) {

                        if ( isGameOver() ) {

                            reset();
                        }
                    }
                }
            }
        });

        this.setOnKeyReleased(new EventHandler< KeyEvent >() {
            @Override
            public void handle( KeyEvent event ) {

                if ( getInput().contains(event.getCode()) ) {

                    removeKeyCode(event.getCode());
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

        if ( ( getInput().contains(KeyCode.UP) || getInput().contains(KeyCode.SPACE) ) && !getPlayer().isJumping() ) {

            getPlayer().jump(-80);
        }
        if ( getInput().contains(KeyCode.DOWN) && !getPlayer().isJumping() ) {

            getPlayer().slide();
        }
        if ( getInput().contains(KeyCode.F1) ) {

            pause = true;
        }
        if ( getPlayer().getPositionY() + getPlayer().getHeight() == this.getHeight() ) {

            getPlayer().setJumping(false);
        }
        if ( !getInput().contains(KeyCode.DOWN) ) {

            getPlayer().setSliding(false);
            getPlayer().setHeight(getPlayer().getNormalHeight());
        }

        // collision detection

        for ( int i = 0; i < getSprites().size(); i++ ) {

            Sprite sprite = getSprites().get(i);

            if ( sprite.isSolid() ) {

                if ( getPlayer().intersects(sprite) && sprite.isHarmful() ) {

                    setGameOver(true);
                }
            }

            addScore(.05);

            if ( getScore() >= 2000 ) {

                sprite.setVelocity(-120, 0);
            }
            else if ( getScore() >= 1500 ) {

                sprite.setVelocity(-100, 0);
            }
            else if ( getScore() >= 1000 ) {

                sprite.setVelocity(-90, 0);
            }
            else if ( getScore() >= 600 ) {

                sprite.setVelocity(-80, 0);
            }
            else if ( getScore() >= 300 ) {

                sprite.setVelocity(-70, 0);
            }
            else {

                sprite.setVelocity(-60, 0);
            }

            if ( i == getSprites().size() - 1 ) {

                if ( sprite.getPositionX() + sprite.getWidth() < this.getWidth() ) {

                    generateObsticals();
                }
            }
        }

    }

    /**
     * Method: render
     * @param time double
     * @throws InterruptedException
     * Description: Render and display the game
     */
    public void render( double time ) throws InterruptedException {

        if ( !pause ) {

            getPlayer().update(time);

            // Scene border detection

            if ( getPlayer().getPositionY() < 0 ) {
                getPlayer().setPositionY(0);
                getPlayer().setVelocityY(0);
            }
            if ( getPlayer().getPositionY() + getPlayer().getHeight() > getCanvas().getHeight() ) {
                getPlayer().setPositionY(getCanvas().getHeight() - getPlayer().getHeight());
                getPlayer().setVelocityY(0);
            }

            getGc().clearRect(0, 0, this.getWidth(), this.getHeight());

            for ( Sprite sprite : getSprites() ) {
                sprite.update(time);
                sprite.render(getGc());
            }

            String highScoreTxt = "High Score: " + getHighScore();
            String scoreTxt = "Score: " + (int) getScore();
            placeText(highScoreTxt, this.getWidth() - 150, 20);
            placeText(scoreTxt, this.getWidth() - 103, 40);
        }
        else {

            String prompt = "Pause";
            String message = "Press the space bar or the up arrow to jump\n" +
                    "over the obsticals, and press down arrow\n" +
                    "to slide under obsitcals.";
            String escape = "Press ESC to exit";
            placeText(prompt, ( this.getWidth() / 2 ) - 50, 70);
            placeText(message, 80, 110);
            placeText(escape, 170, 200);

            throw new InterruptedException();
        }
    }

    /**
     * Method: gameOver
     * Description: Display a game over message and play the game over sound if it exists
     */
    public void gameOver() {

        String prompt = "Game Over";
        String message = "Press the space bar or the up arrow to start over";
        placeText(prompt, ( this.getWidth() / 2 ) - 50, 70);
        placeText(message, 80, 110);
        playGameOverSound();
        playMusic(false);
    }

    /**
     * Method: reset
     * Description: Reset the game
     */
    public void reset() {

        clearSprites();
        clearScore();
        generateObsticals();
        setGameOver(false);
        stopGameOverSound();
        playMusic(true);
        launcher.restartThreads();
    }
}
