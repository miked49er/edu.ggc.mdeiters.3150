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

        while ( stage < 800 ) {

            int rec = (int) ( Math.random() * 34 ) % 4;
            int distance = (int) ( Math.random() * 10000 ) % 300;
            distance += 100;

            switch ( rec ) {
                case SMALL:
                    temp.add(new Rectangle(10, 50, distance));
                    stage += 10;
                    break;
                case MEDIUM:
                    temp.add(new Rectangle(10, 70, distance));
                    stage += 10;
                    break;
                case LARGE:
                    temp.add(new Rectangle(20, 50, distance));
                    stage += 20;
                    break;
                case XL:
                    temp.add(new Rectangle(30, 50, distance));
                    stage += 30;
                    break;
                default:
                    temp.add(new Rectangle(10, 50, 50));
                    stage += 10;
                    break;
            }

            stage += distance;
        }

        int x = stageWidth;

        for ( int i = 0; i < temp.size(); i++ ) {

            x += temp.get(i).getDistance();
            temp.get(i).setPosition(x, y);
            getChildren().add(temp.get(i));
            recs.add(temp.get(i));
            x += temp.get(i).getWidth();
//            System.out.println("" + x);
        }
    }

    @Override
    public void run() {

        try {

            while ( true ) {
//            for ( int j = 0; j < 2; j++ ) {
                Thread.sleep(timeBetweenMoves);

                if ( recs.size() < 20 ) {

                    generateStage();
                }

                for ( int i = 0; i < recs.size(); i++ ) {


                    Rectangle temp = recs.get(i);
                    temp.setX(temp.getX() - 5);

//                    if ( temp.getX() + temp.getWidth() < 0 ) {
//
//                        recs.remove(recs.get(i));
//                        getChildren().remove(temp);
//                    }
                }
//                System.out.println(recs.get(0).getX());
            }
        }
        catch ( InterruptedException ex ) {
            // do nothing just stop moving the shape
        }
    }
}
