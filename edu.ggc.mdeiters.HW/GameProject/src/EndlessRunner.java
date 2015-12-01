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

    private final int GRASS = 1;
    private final int SNOW = 2;
    private final int DESERT = 3;
    private final int MESA = 4;
    private final int NETHER = 5;

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
     * @param toGenerate int Number of cordinates to generate
     * @param biome int the background to determine which obsitcals are being generated
     * Description: Generate toGenerate number of cordinates of obsticals
     */
    private void generateObsticals( int toGenerate, int biome ) {

        int stage = 0;
        double x = this.getCanvas().getWidth();
        double y = getSprites().get(0).getPositionY();

        while ( stage < toGenerate ) {

            int obstical;

            if ( getScore() < 400 ) {

                obstical = (int) ( Math.random() * 256 ) % 5;
            }
            else {

                obstical = (int) ( Math.random() * 256 ) % 10;
            }
            int distance = (int) ( Math.random() * 342 ) % 400 + 200;
            x += distance;
            stage += distance;
            Sprite sprite;

            switch ( obstical ) {

                case 0: // Block

                    switch ( biome ) {

                        case GRASS:
                            sprite = new Log();
                            break;
                        case SNOW:
                            sprite = new Log();
                            break;
                        case DESERT:
                            sprite = new Cactus();
                            break;
                        case MESA:
                            sprite = new Cactus();
                            break;
                        case NETHER:
                            sprite = new NetherBrick();
                            break;
                        default: // Grass
                            sprite = new Log();
                            break;
                    }
                    sprite.setPositionY(y - sprite.getHeight());
                    break;
                case 1: // Block

                    switch ( biome ) {

                        case GRASS:
                            sprite = new Log();
                            break;
                        case SNOW:
                            sprite = new Log();
                            break;
                        case DESERT:
                            sprite = new Cactus();
                            break;
                        case MESA:
                            sprite = new Cactus();
                            break;
                        case NETHER:
                            sprite = new NetherBrick();
                            break;
                        default: // Grass
                            sprite = new Log();
                            break;
                    }
                    sprite.setPositionY(y - sprite.getHeight());
                    break;
                case 2: // Block

                    switch ( biome ) {

                        case GRASS:
                            sprite = new Log();
                            break;
                        case SNOW:
                            sprite = new Log();
                            break;
                        case DESERT:
                            sprite = new Cactus();
                            break;
                        case MESA:
                            sprite = new Cactus();
                            break;
                        case NETHER:
                            sprite = new NetherBrick();
                            break;
                        default: // Grass
                            sprite = new Log();
                            break;
                    }
                    sprite.setPositionY(y - sprite.getHeight());
                    break;
                case 3: // Wall

                    switch ( biome ) {

                        case GRASS:
                            sprite = new LogTree();
                            break;
                        case SNOW:
                            sprite = new LogTree();
                            break;
                        case DESERT:
                            sprite = new CactusTall();
                            break;
                        case MESA:
                            sprite = new CactusTall();
                            break;
                        case NETHER:
                            sprite = new NetherBrickWall();
                            break;
                        default: // Grass
                            sprite = new LogTree();
                            break;
                    }
                    sprite.setPositionY(y - sprite.getHeight());
                    break;
                case 4: // Wall

                    switch ( biome ) {

                        case GRASS:
                            sprite = new LogTree();
                            break;
                        case SNOW:
                            sprite = new LogTree();
                            break;
                        case DESERT:
                            sprite = new CactusTall();
                            break;
                        case MESA:
                            sprite = new CactusTall();
                            break;
                        case NETHER:
                            sprite = new NetherBrickWall();
                            break;
                        default: // Grass
                            sprite = new LogTree();
                            break;
                    }
                    sprite.setPositionY(y - sprite.getHeight());
                    break;
                case 5: // Stack

                    switch ( biome ) {

                        case GRASS:
                            sprite = new LogStack();
                            break;
                        case SNOW:
                            sprite = new LogStack();
                            break;
                        case DESERT:
                            sprite = new CactusTall();
                            break;
                        case MESA:
                            sprite = new CactusTall();
                            break;
                        case NETHER:
                            sprite = new NetherBrickStack();
                            break;
                        default: // Grass
                            sprite = new LogStack();
                            break;
                    }
                    sprite.setPositionY(y - sprite.getHeight());
                    break;
                case 6: // Floating Block

                    switch ( biome ) {

                        case GRASS:
                            sprite = new Log();
                            break;
                        case SNOW:
                            sprite = new Log();
                            break;
                        case DESERT:
                            sprite = new Cactus();
                            break;
                        case MESA:
                            sprite = new Cactus();
                            break;
                        case NETHER:
                            sprite = new NetherBrick();
                            break;
                        default: // Grass
                            sprite = new Log();
                            break;
                    }
                    sprite.setPositionY(y - ( sprite.getHeight() * 2 ));
                    break;
                case 7: // Floating Block

                    switch ( biome ) {

                        case GRASS:
                            sprite = new Log();
                            break;
                        case SNOW:
                            sprite = new Log();
                            break;
                        case DESERT:
                            sprite = new Cactus();
                            break;
                        case MESA:
                            sprite = new Cactus();
                            break;
                        case NETHER:
                            sprite = new NetherBrick();
                            break;
                        default: // Grass
                            sprite = new Log();
                            break;
                    }
                    sprite.setPositionY(y - ( sprite.getHeight() * 2 ));
                    break;
                case 8: // High Floating Block

                    switch ( biome ) {

                        case GRASS:
                            sprite = new Log();
                            break;
                        case SNOW:
                            sprite = new Log();
                            break;
                        case DESERT:
                            sprite = new Cactus();
                            break;
                        case MESA:
                            sprite = new Cactus();
                            break;
                        case NETHER:
                            sprite = new NetherBrick();
                            break;
                        default: // Grass
                            sprite = new Log();
                            break;
                    }
                    sprite.setPositionY(y - ( sprite.getHeight() * 3 ));
                    break;
                case 9: // High Floating Block

                    switch ( biome ) {

                        case GRASS:
                            sprite = new Log();
                            break;
                        case SNOW:
                            sprite = new Log();
                            break;
                        case DESERT:
                            sprite = new Cactus();
                            break;
                        case MESA:
                            sprite = new Cactus();
                            break;
                        case NETHER:
                            sprite = new NetherBrick();
                            break;
                        default: // Grass
                            sprite = new Log();
                            break;
                    }
                    sprite.setPositionY(y - ( sprite.getHeight() * 3 ));
                    break;
                default: // Block

                    switch ( biome ) {

                        case GRASS:
                            sprite = new Log();
                            break;
                        case SNOW:
                            sprite = new Log();
                            break;
                        case DESERT:
                            sprite = new Cactus();
                            break;
                        case MESA:
                            sprite = new Cactus();
                            break;
                        case NETHER:
                            sprite = new NetherBrick();
                            break;
                        default: // Grass
                            sprite = new Log();
                            break;
                    }
                    sprite.setPositionY(y - sprite.getHeight());
                    break;
            }

            sprite.setPositionX(x);

            if ( biome == DESERT || biome == MESA ) {

                sprite.setPositionY(y - sprite.getHeight());
            }

            stage += sprite.getWidth();

            if ( stage < toGenerate ) {

                addSprite(sprite);
            }
        }
    }

    /**
     * Method: generateStage
     * Description: Generate the stage assets
     */
    public void generateStage( boolean firstGenerate ) {

        int stage = 0;
        int biome = 0;

        if ( getScore() > 200 ) {

            stage = (int) ( getScore() / 200 ) % 5;
        }

        int toGenerate = 0;
        double x = getCanvas().getWidth();
        double y = getCanvas().getHeight();

        if ( firstGenerate ) {

            double initX = 0;
            while ( initX < getCanvas().getWidth() ) {

                Grass grass = new Grass();
                grass.setPositionX(initX);
                grass.setPositionY(y - grass.getHeight());

                initX += grass.getWidth();

                addSprite(grass);
            }
        }

        for ( int i = 0; i < 3; i++ ) {

            Sprite background = null;

            switch ( stage ) {
                case 0:
                    background = new Grass();
                    biome = 1;
                    break;
                case 1:
                    background = new Snow();
                    biome = 2;
                    break;
                case 2:
                    background = new Desert();
                    biome = 3;
                    break;
                case 3:
                    background = new Mesa();
                    biome = 4;
                    break;
                case 4:
                    background = new Nether();
                    biome = 5;
                    break;
            }

            background.setPositionX(x);
            background.setPositionY(y - background.getHeight());

            x += background.getWidth();
            toGenerate += background.getWidth();

            addSprite(background);
        }

        generateObsticals(toGenerate, biome);
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

        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
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

        this.setOnKeyReleased(new EventHandler<KeyEvent>() {
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
    public void updateGame( double time ) throws InterruptedException {

        // game logic

        if ( ( getInput().contains(KeyCode.UP) || getInput().contains(KeyCode.SPACE) ) && !getPlayer().isJumping() ) {

            getPlayer().jump(-105);
        }
        if ( getInput().contains(KeyCode.DOWN) && !getPlayer().isJumping() ) {

            getPlayer().slide();
        }
        if ( getInput().contains(KeyCode.F1) ) {

            pause = true;
        }
//        if ( getPlayer().getPositionY() + getPlayer().getHeight() == this.getHeight() ) {
//
//            getPlayer().setJumping(false);
//        }
        if ( !getInput().contains(KeyCode.DOWN) ) {

            getPlayer().setSliding(false);
            getPlayer().setWidth(getPlayer().getNormalWidth());
            getPlayer().setHeight(getPlayer().getNormalHeight());
        }

        getPlayer().update(time);

        // collision detection

        for ( int i = 0; i < getSprites().size(); i++ ) {

            Sprite sprite = getSprites().get(i);

            if ( getPlayer().intersects(sprite) ) {

                if ( sprite.isSolid() ) {

                    getPlayer().setJumping(false);
                    getPlayer().setPositionY(sprite.getPositionY() - getPlayer().getHeight());
                    getPlayer().setVelocityY(0);
                }

                if ( sprite.isHarmful() ) {

                    setGameOver(true);
                    throw new InterruptedException();
                }
            }

            addScore(.05);

            if ( getScore() >= 2000 ) {

                sprite.setVelocityX(-120);
            }
            else if ( getScore() >= 1500 ) {

                sprite.setVelocityX(-100);
            }
            else if ( getScore() >= 1000 ) {

                sprite.setVelocityX(-90);
            }
            else if ( getScore() >= 600 ) {

                sprite.setVelocityX(-80);
            }
            else if ( getScore() >= 300 ) {

                sprite.setVelocityX(-70);
            }
            else {

                sprite.setVelocityX(-60);
            }

            if ( i == getSprites().size() - 1 ) {

                if ( sprite.getPositionX() + sprite.getWidth() < this.getWidth() ) {

                    generateStage(false);
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

            getGc().clearRect(0, 0, this.getWidth(), this.getHeight());

            for ( Sprite sprite : getSprites() ) {
                sprite.update(time);
                sprite.render(getGc());
            }

            String highScoreTxt = "High Score: " + getHighScore();
            String scoreTxt = "Score: " + (int) getScore();
            placeText(highScoreTxt, this.getWidth() - 170, 20);
            placeText(scoreTxt, this.getWidth() - 123, 40);
        }
        else {

            String prompt = "Pause";
            String message = "Press the space bar or the up arrow to jump\n" +
                    "over the obsticals, and press down arrow\n" +
                    "to slide under obsitcals.";
            String escape = "Press ESC to exit";
            placeText(prompt, ( this.getWidth() / 2 ) - 50, 40);
            placeText(message, 80, 70);
            placeText(escape, 10, 40);

            throw new InterruptedException();
        }
    }

    /**
     * Method: gameOver
     * Description: Display a game over message and play the game over sound if it exists
     */
    public void gameOver() {

        if ( isGameOver() ) {

            String prompt = "Game Over";
            String message = "Press the space bar or the up arrow to start over";
            placeText(prompt, ( this.getWidth() / 2 ) - 50, 50);
            placeText(message, 80, 70);
            playGameOverSound();
            playMusic(false);
        }
    }

    /**
     * Method: reset
     * Description: Reset the game
     */
    public void reset() {

        clearSprites();
        clearScore();
        generateStage(true);
        setGameOver(false);
        stopGameOverSound();
        playMusic(true);
        launcher.restartThreads();
    }
}
