/**
 * Class: Snow
 * @author Mike Deiters
 * @version 1.0
 * November 30, 2015
 * ITEC 3150-01
 *
 * Description: Snow Ground
 *
 * Purpose: What the player will run on
 */
public class Snow extends Sprite {

    public Snow() {
        super();
        setImage("assets/background/snow.png");
        setWidth(382);
        setHeight(96);
        setVisible(true);
        setSolid(true);
        setGround(true);
    }
}
