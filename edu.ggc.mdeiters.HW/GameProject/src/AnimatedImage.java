import javafx.scene.image.Image;

/**
 * Class: AnimatedImage
 * @author Mike Deiters
 * @version 1.0
 * November 18, 2015
 * ITEC 2110
 *
 * Description: Frame by frame animation
 *
 * Purpose: Create a frame by frame animation
 */
public class AnimatedImage {

    private Image[] frames;
    private double duration;

    /**
     * Constructor: AnimatedImage
     */
    public AnimatedImage() {
    }

    /**
     * Constructor: AnimatedImage
     * @param frames Image[]
     * @param duration double
     */
    public AnimatedImage( Image[] frames, double duration ) {
        this.frames = frames;
        this.duration = duration;
    }

    /**
     * Method: getFrames
     * @return frames Image[]
     */
    public Image[] getFrames() {
        return frames;
    }

    /**
     * Method: getFirstFrames
     * @return frames[0] Image
     */
    public Image getFirstFrame() {

        return frames[0];
    }

    /**
     * Method: setFrames
     * @param frames Image[]
     */
    public void setFrames( Image[] frames ) {
        this.frames = frames;
    }

    /**
     * Method: getDuration
     * @return duration double
     */
    public double getDuration() {
        return duration;
    }

    /**
     * Method: setDuration
     * @param duration double
     */
    public void setDuration( double duration ) {
        this.duration = duration;
    }

    /**
     * Method: getFrame
     * @param time double
     * @return frames[index] Image
     */
    public Image getFrame( double time ) {

        int index = (int) ( ( time % ( frames.length * duration ) ) / duration );
        return frames[ index ];
    }
}
