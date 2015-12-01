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
public class LargeObstical extends Sprite {

    public LargeObstical() {
        super();
        setImage("assets/obsticals/log-pole.png");
        setWidth(24);
        setHeight(48);
        setVisible(true);
        setSolid(false);
        setHarmful(true);
    }
}