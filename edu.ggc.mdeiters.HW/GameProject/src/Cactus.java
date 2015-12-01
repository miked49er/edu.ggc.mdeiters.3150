/**
 * Class: Cactus
 * @author Mike Deiters
 * @version 1.0
 * November 30, 2015
 * ITEC 3150-01
 *
 * Description: Cactus obstical
 *
 * Purpose: Create a Cactus
 */
public class Cactus extends Sprite {

    public Cactus() {
        super();
        setImage("assets/obsticals/cactus.png");
        setWidth(28);
        setHeight(32);
        setVisible(true);
        setHarmful(true);
        setGround(false);
    }
}
