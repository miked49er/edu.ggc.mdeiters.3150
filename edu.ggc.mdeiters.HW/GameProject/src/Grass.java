/**
 * Class: Grass
 * @author Mike Deiters
 * @version 1.0
 * November 30, 2015
 * ITEC 3150-01
 *
 * Description: Grass Ground
 *
 * Purpose: What the player will run on
 */
public class Grass extends Sprite {

    public Grass() {
        super();
        setImage("assets/background/grass.png");
        setWidth(382);
        setHeight(96);
        setVisible(true);
        setSolid(true);
        setGround(true);
    }
}
