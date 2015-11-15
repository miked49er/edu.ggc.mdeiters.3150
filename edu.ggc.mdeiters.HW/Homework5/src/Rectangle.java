/**
 * Class: Rectangle
 * @author Mike Deiters
 * @version 1.0
 * November 13, 2015
 * ITEC 3150-01
 *
 * Description: Adds a new property to the rectangle
 *
 * Purpose: Add a distance property the the rectangle
 */
public class Rectangle extends javafx.scene.shape.Rectangle {

    private int distance;

    /**
     * Constructor: Rectangle
     * @param width double
     * @param height double
     * @param distance int
     */
    public Rectangle( double width, double height, int distance ) {
        super(width, height);
        this.distance = distance;
    }

    /**
     * Method: getDistance
     * @return distance int
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Method: setDistance
     * @param distance int
     */
    public void setDistance( int distance ) {
        this.distance = distance;
    }

    /**
     * Method: setPosition
     * @param x int
     * @param y int
     */
    public void setPosition( int x, int y ) {

        setX(x);

        // Align rectangles at the bottom

        setY(y - getHeight());
    }
}
