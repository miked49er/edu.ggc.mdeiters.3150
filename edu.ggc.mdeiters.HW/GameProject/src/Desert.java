/**
 * Class: Desert
 * @author Mike Deiters
 * @version 1.0
 * November 30, 2015
 * ITEC 3150-01
 *
 * Description: Desert Ground
 *
 * Purpose: What the player will run on
 */
public class Desert extends Sprite {

    public Desert() {
        super();
        setImage("assets/background/desert.png");
        setWidth(382);
        setHeight(96);
        setVisible(true);
        setSolid(true);
        setGround(true);
    }
}
