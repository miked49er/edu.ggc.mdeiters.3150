/**
 * Class: SmallObstical
 * @author Mike Deiters
 * @version 1.0
 * November 26, 2015
 * ITEC 2110
 *
 * Description: Small obsitcal
 *
 * Purpose: Create a small obsitical
 */
public class SmallObstical extends Sprite {

    public SmallObstical() {
        super();
        setImage("assets/obsticals/log.png");
        setWidth(24);
        setHeight(24);
        setVisible(true);
        setSolid(false);
        setHarmful(true);
    }
}
