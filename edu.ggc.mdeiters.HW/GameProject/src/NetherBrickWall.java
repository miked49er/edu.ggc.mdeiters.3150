/**
 * Class: NetherBrickWall
 * @author Mike Deiters
 * @version 1.0
 * November 30, 2015
 * ITEC 3150-01
 *
 * Description: Nether Brick Wall obstical
 *
 * Purpose: Create a Nether Brick Wall
 */
public class NetherBrickWall extends Sprite {

    public NetherBrickWall() {
        super();
        setImage("assets/obsticals/netherbrick-wall.png");
        setWidth(24);
        setHeight(48);
        setVisible(true);
        setHarmful(true);
    }
}
