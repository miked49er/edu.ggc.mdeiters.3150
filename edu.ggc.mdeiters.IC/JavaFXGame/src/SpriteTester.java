import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;

/**
 * Class: SpriteTester
 * @author Mike Deiters
 * @version 1.0
 * November 18, 2015
 * ITEC 3150-01
 *
 * Description: Start a game
 *
 * Purpose: Create a game where you collect the cards
 */
public class SpriteTester extends Application {

    private ArrayList< Sprite > aceList;

    public void generateCards() {

        for ( int i = 0; i < 100; i++ ) {

            Sprite ace = new Sprite();
            ace.setImage("image/Spades_Ace.png");
            double px = 350 * Math.random() % 512;
            double py = Math.random() * -10000;
            ace.setPosition(px, py);
            ace.setGravity(.3);
            ace.setWidth(71);
            ace.setHeight(96);
            aceList.add(ace);
        }
    }

    /**
     * Method: start
     * @param stage Stage
     * @throws Exception
     * Description: Setup the Game
     */
    public void start( Stage stage ) throws Exception {

        try {
            Image back = new Image("image/Card_Back.png");
            Image back1 = new Image("image/Spades_Ace.png");
            Image[] imageArray = { back, back1 };

            Sprite deck = new Sprite();
            deck.setAnimation(imageArray, .5);
            deck.setWidth(71);
            deck.setHeight(96);
            deck.setPosition(50, 200);
            deck.setGravity(85);

            this.aceList = new ArrayList<>();
            generateCards();

            Group root = new Group();
            Game game = new Game(root, deck, aceList, this);

            GameLoopRunnable loop = new GameLoopRunnable(game, 50);
            Thread thread = new Thread(loop);
            thread.start();

            AnimationRunnable anima = new AnimationRunnable(game, 25);
            Thread thread2 = new Thread(anima);
            thread2.start();

            game.getPlayer().setVelocity(0, 0);
            game.updateGame();
            game.render(1);

            stage.setTitle("Sprite Tester");
            stage.setScene(game);
            stage.setMinWidth(game.getWidth());
            stage.setMinHeight(game.getHeight());
            stage.show();

            stage.setOnCloseRequest(new EventHandler< WindowEvent >() {
                @Override
                public void handle( WindowEvent event ) {
                    thread.interrupt();
                    thread2.interrupt();
                    stage.close();
                }
            });
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
