import javafx.application.Platform;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

/**
 * Class: RandomGenRunnable
 * @author Mike Deiters
 * @version 1.0
 * November 13, 2015
 * ITEC 3150-01
 *
 * Description: Randomly generate different width rectangles at different distances apart
 *
 * Purpose: Create a Runnable for the background
 */
public class RandomGenRunnable extends Pane implements Runnable {

    private final int SMALL = 0;
    private final int MEDIUM = 1;
    private final int LARGE = 2;
    private final int XL = 3;

    private ArrayList< Rectangle > recs;
    private int stageWidth;
    private int y;
    private int timeBetweenMoves;

    /**
     * Method: ReandomGenRunnable
     * @param stageWidth int
     * @param y int
     * @param timeBetweenMoves int
     */
    public RandomGenRunnable( int stageWidth, int y, int timeBetweenMoves ) {
        this.recs = new ArrayList<>();
        this.stageWidth = stageWidth;
        this.y = y;
        this.timeBetweenMoves = timeBetweenMoves;
        generateStage();
    }

    /**
     * Method: generateStage
     * Description: generate the next 500 coordinates of the stage
     */
    private void generateStage() {

        int stage = 0;
        ArrayList< Rectangle > temp = new ArrayList<>();

        while ( stage < 800 ) { // Until stage is larger than 800

            // Randomly generate a rectangle
            // Randomly generate a distance less than 300 then add 100

            int rec = (int) ( Math.random() * 34 ) % 4;
            int distance = (int) ( Math.random() * 10000 ) % 300;
            distance += 100;

            switch ( rec ) { // Select the rectangle size and add the rectangle width to stage
                case SMALL: // Small rectangle
                    temp.add(new Rectangle(10, 50, distance));
                    stage += 10;
                    break;
                case MEDIUM: // Medium rectangle
                    temp.add(new Rectangle(10, 70, distance));
                    stage += 10;
                    break;
                case LARGE: // Large rectangle
                    temp.add(new Rectangle(20, 50, distance));
                    stage += 20;
                    break;
                case XL: // XL rectangle
                    temp.add(new Rectangle(30, 50, distance));
                    stage += 30;
                    break;
                default: // Small rectangle
                    temp.add(new Rectangle(10, 50, 50));
                    stage += 10;
                    break;
            }

            // Add distance to stage

            stage += distance;
        }

        int x = stageWidth;

        for ( int i = 0; i < temp.size(); i++ ) {

            // Add the distance of temp to x

            x += temp.get(i).getDistance();

            // Set temp's position

            temp.get(i).setPosition(x, y);

            // Add temp to recs and add temp's width to x

            recs.add(temp.get(i));
            x += temp.get(i).getWidth();
        }

        // Add all of temp to the pane

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                getChildren().addAll(temp);
            }
        });
    }

    /**
     * Method: run
     * Description: Generate and Move the rectangles to the left
     */
    public void run() {

        try {

            while ( true ) {

                Thread.sleep(timeBetweenMoves);

                for ( int i = 0; i < recs.size(); i++ ) { // Move all the rectangles to the left

                    Rectangle temp = recs.get(i);
                    temp.setX(temp.getX() - 5);

                    if ( temp.getX() + temp.getWidth() < 0 ) { // Remove any rectangles that are no longer in the stage

                        recs.remove(recs.get(i));

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {

                                getChildren().remove(temp);
                            }
                        });
                    }

                    if ( temp == recs.get(recs.size() - 1) && temp.getX() + temp.getWidth() < stageWidth ) { // Generate more rectangles when the last rectangle is completely on the stage

                        generateStage();
                    }
                }
            }
        }
        catch ( InterruptedException ex ) {
            // do nothing just stop moving the shape
        }
    }
}
