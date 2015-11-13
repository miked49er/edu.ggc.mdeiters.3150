import javafx.application.Platform;
import javafx.scene.shape.Circle;

/**
 * Class: CircleRunnable
 * @author Mike Deiters
 * @version 1.0
 * November 13, 2015
 * ITEC 3150-01
 *
 * Description: Runnable that bounces a circle around the stage
 *
 * Purpose: Control moving a circle around the stage
 */
public class CircleRunnable implements Runnable {

    private double x;
    private double y;
    private double radius;
    private Circle circle;
    private int timeBetweenMoves;
    private int stageWidth;
    private int stageHeight;
    private boolean upLeft;
    private boolean upRight;
    private boolean downLeft;
    private boolean downRight;

    /**
     * Constructor: CircleRunnable
     * @param x
     * @param y
     * @param radius
     * @param timeBetweenMoves
     * @param stageWidth
     * @param stageHeight
     */
    public CircleRunnable( double x, double y, double radius, int timeBetweenMoves, int stageWidth, int stageHeight ) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.timeBetweenMoves = timeBetweenMoves;
        this.stageWidth = stageWidth;
        this.stageHeight = stageHeight;
        this.upLeft = false;
        this.upRight = false;
        this.downLeft = false;
        this.downRight = true;

        this.circle = new Circle(this.x, this.y, this.radius);
    }

    /**
     * Method: getX
     * @return x double
     */
    public double getX() {
        return x;
    }

    /**
     * Method: setX
     * @param x double
     */
    public void setX( double x ) {
        this.x = x;
        this.circle.setCenterX(this.x);
    }

    /**
     * Method: getY
     * @return y double
     */
    public double getY() {
        return y;
    }

    /**
     * Method: setY
     * @param y double
     */
    public void setY( double y ) {
        this.y = y;
        this.circle.setCenterY(this.y);
    }

    /**
     * Method: getRadius
     * @return radius double
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Method: setRadius
     * @param radius double
     */
    public void setRadius( double radius ) {
        this.radius = radius;
        this.circle.setRadius(this.radius);
    }

    /**
     * Method: getTimeBetweenMoves
     * @return timeBetweenMoves int
     */
    public int getTimeBetweenMoves() {
        return timeBetweenMoves;
    }

    /**
     * Method: setTimeBetweenMoves
     * @param timeBetweenMoves int
     */
    public void setTimeBetweenMoves( int timeBetweenMoves ) {
        this.timeBetweenMoves = timeBetweenMoves;
    }

    /**
     * Method: getCircle
     * @return circle Shape
     */
    public Circle getCircle() {
        return circle;
    }

    /**
     * Method: getStageWidth
     * @return stageWidth int
     */
    public int getStageWidth() {
        return stageWidth;
    }

    /**
     * Method: setStageWidth
     * @param stageWidth int
     */
    public void setStageWidth( int stageWidth ) {
        this.stageWidth = stageWidth;
    }

    /**
     * Method: getStageHeight
     * @return stageHeight int
     */
    public int getStageHeight() {
        return stageHeight;
    }

    /**
     * Method: setStageHeight
     * @param stageHeight int
     */
    public void setStageHeight( int stageHeight ) {
        this.stageHeight = stageHeight;
    }

    /**
     * Method: run
     * Description: Move the circle
     */
    public void run() {

        try {

            int sLeft = 0;
            int sRight = stageWidth;
            int sTop = 0;
            int sBottom = stageHeight;

            while ( true ) {
//            for (int i=0; i<200; i++) {

                Thread.sleep(timeBetweenMoves);

                if ( upLeft ) {

                    this.x -= 5;
                    this.y -= 5;

                    if ( this.x - radius < sLeft ) {

                        this.upLeft = false;
                        this.upRight = true;
                        this.downLeft = false;
                        this.downRight = false;
//                        this.y += ( sLeft - this.x );
                        this.x = sLeft + radius;
                    }
                    if ( this.y - radius < sTop ) {

                        this.upLeft = false;
                        this.upRight = false;
                        this.downLeft = true;
                        this.downRight = false;
//                        this.x += ( sTop - this.y );
                        this.y = sTop + radius;
                    }
                }
                else if ( upRight ) {

                    this.x += 5;
                    this.y -= 5;

                    if ( this.x + radius > sRight ) {

                        this.upLeft = true;
                        this.upRight = false;
                        this.downLeft = false;
                        this.downRight = false;
//                        this.y += ( this.x - sRight );
                        this.x = sRight - radius;
                    }
                    if ( this.y - radius < sTop ) {

                        this.upLeft = false;
                        this.upRight = false;
                        this.downLeft = false;
                        this.downRight = true;
//                        this.x -= ( sTop - this.y );
                        this.y = sTop + radius;
                    }
                }
                else if ( downLeft ) {

                    this.x -= 5;
                    this.y += 5;

                    if ( this.x - radius < sLeft ) {

                        this.upLeft = false;
                        this.upRight = false;
                        this.downLeft = false;
                        this.downRight = true;
//                        this.y -= ( sLeft - this.x );
                        this.x = sLeft + radius;
                    }
                    if ( this.y + radius > sBottom ) {

                        this.upLeft = true;
                        this.upRight = false;
                        this.downLeft = false;
                        this.downRight = false;
//                        this.x += ( this.y - sBottom );
                        this.y = sBottom - radius;
                    }
                }
                else if ( downRight ) {

                    this.x += 5;
                    this.y += 5;

                    if ( this.x + radius > sRight ) {

                        this.upLeft = false;
                        this.upRight = false;
                        this.downLeft = true;
                        this.downRight = false;
//                        this.y -= ( sRight - this.x );
                        this.x = sRight - radius;
                    }
                    if ( this.y + radius > sBottom ) {

                        this.upLeft = false;
                        this.upRight = true;
                        this.downLeft = false;
                        this.downRight = false;
//                        this.x -= ( sBottom - this.y );
                        this.y = sBottom - radius;
                    }
                }

//                System.out.println("X: " + x + " Y: " + y);
                // Shcedule a javafx position update

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

//                        System.out.println("Move");
                        circle.setCenterX(x);
                        circle.setCenterY(y);
                    }
                });
            }
        }
        catch ( InterruptedException ex ) {
            // do nothing just stop moving the shape
        }
    }
}
