/**
 * Class: CactusTall
 * @author Mike Deiters
 * @version 1.0
 * November 30, 2015
 * ITEC 3150-01
 *
 * Description: Tall Cactus obstical
 *
 * Purpose: Create a Tall Cactus
 */
public class CactusTall extends Sprite {

    public CactusTall() {
        super();
        setImage("assets/obsticals/cactus-tall.png");
        setWidth(28);
        setHeight(64);
        setVisible(true);
        setHarmful(true);
        setGround(false);
    }
}
