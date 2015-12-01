/**
 * Class: LogStack
 * @author Mike Deiters
 * @version 1.0
 * November 30, 2015
 * ITEC 3150-01
 *
 * Description: Stack of Logs obstical
 *
 * Purpose: Create a Stack of Logs
 */
public class LogStack extends Sprite {

    public LogStack() {
        super();
        setImage("assets/obsticals/log-stack.png");
        setWidth(48);
        setHeight(48);
        setVisible(true);
        setHarmful(true);
    }
}
