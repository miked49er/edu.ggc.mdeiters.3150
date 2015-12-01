/**
 * Class: Log
 * @author Mike Deiters
 * @version 1.0
 * November 30, 2015
 * ITEC 3150-01
 *
 * Description: Log obstical
 *
 * Purpose: Create a Log
 */
public class Log extends Sprite {

    public Log() {
        super();
        setImage("assets/obsticals/log.png");
        setWidth(24);
        setHeight(24);
        setVisible(true);
        setHarmful(true);
    }
}
