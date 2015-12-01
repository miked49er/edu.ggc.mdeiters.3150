/**
 * Class: NetherBrickStack
 * @author Mike Deiters
 * @version 1.0
 * November 30, 2015
 * ITEC 3150-01
 *
 * Description: Nether Brick Stack obstical
 *
 * Purpose: Create a Nether Brick Stack
 */
public class NetherBrickStack extends Sprite {

    public NetherBrickStack() {
        super();
        setImage("assets/obsticals/netherbrick-stack.png");
        setWidth(48);
        setHeight(48);
        setVisible(true);
        setHarmful(true);
    }
}
