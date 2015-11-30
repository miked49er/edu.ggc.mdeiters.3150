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
        setImage("image/Hearts_2.png");
        setWidth(20);
        setHeight(30);
        setVisible(true);
        setSolid(true);
        setHarmful(true);
    }
}
