/**
 * Class: NetherBrick
 * @author Mike Deiters
 * @version 1.0
 * November 30, 2015
 * ITEC 3150-01
 *
 * Description: Nether Brick obstical
 *
 * Purpose: Create a Nether Brick
 */
public class NetherBrick extends Sprite {

    public NetherBrick() {
        super();
        setImage("assets/obsticals/netherbrick.png");
        setWidth(24);
        setHeight(24);
        setVisible(true);
        setHarmful(true);
    }
}
