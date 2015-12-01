/**
 * Class: Nether
 * @author Mike Deiters
 * @version 1.0
 * November 30, 2015
 * ITEC 3150-01
 *
 * Description: Nether Ground
 *
 * Purpose: What the player will run on
 */
public class Nether extends Sprite {

    public Nether() {
        super();
        setImage("assets/background/nether.png");
        setWidth(382);
        setHeight(96);
        setVisible(true);
        setSolid(true);
        setGround(true);
    }
}
