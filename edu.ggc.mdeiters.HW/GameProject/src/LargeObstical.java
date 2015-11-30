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
        setImage("image/Hearts_King.png");
        setWidth(30);
        setHeight(60);
        setVisible(true);
        setSolid(true);
        setHarmful(true);
    }
}
