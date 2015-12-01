/**
 * Class: Mesa
 * @author Mike Deiters
 * @version 1.0
 * November 30, 2015
 * ITEC 3150-01
 *
 * Description: Mesa Ground
 *
 * Purpose: What the player will run on
 */
public class Mesa extends Sprite {

    public Mesa() {
        super();
        setImage("assets/background/mesa.png");
        setWidth(382);
        setHeight(96);
        setVisible(true);
        setSolid(true);
        setGround(true);
    }
}
