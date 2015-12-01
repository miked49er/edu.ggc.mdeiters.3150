import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;

/**
 * Class: Player
 * @author Mike Deiters
 * @version 1.0
 * November 22, 2015
 * ITEC 3150-01
 *
 * Description: Human player
 *
 * Purpose: Add functionality to the player
 */
public class Player extends Sprite {

    private AnimatedImage animation;
    private Image jumpingImage;
    private Image slidingImage;

    private Media jumpingMedia;
    private Media slidingMedia;
    private MediaPlayer jumpSound;
    private MediaPlayer slidingSound;

    private boolean jumping;
    private boolean sliding;
    private int lives;
    private double normalWidth;
    private double normalHeight;
    private double slidingWidth;
    private double slidingHeight;

    /**
     * Constructor: Player
     * @param animation AnimatedImage
     * @param jumpingImage Image
     * @param slidingImage Image
     */
    public Player( AnimatedImage animation, Image jumpingImage, Image slidingImage ) {
        super();
        this.animation = animation;
        this.jumpingImage = jumpingImage;
        this.slidingImage = slidingImage;
        setImage(animation.getFirstFrame());
        this.jumping = false;
        this.sliding = false;
    }

    /**
     * Constructor: Player
     * @param images Image[]
     * @param duration double
     * @param jumpingImage Image
     * @param slidingImage Image
     */
    public Player( Image[] images, double duration, Image jumpingImage, Image slidingImage ) {
        super();
        this.animation = new AnimatedImage(images, duration);
        this.jumpingImage = jumpingImage;
        this.slidingImage = slidingImage;
        setImage(animation.getFirstFrame());
        this.jumping = false;
        this.sliding = false;
    }

    /**
     * Constructor: Player
     * @param animation AnimatedImage
     * @param jumpingImage Image
     * @param slidingImage Image
     * @param width double
     * @param height double
     */
    public Player( AnimatedImage animation, Image jumpingImage, Image slidingImage, double width, double height ) {
        super(animation.getFirstFrame(), width, height);
        this.animation = animation;
        this.jumpingImage = jumpingImage;
        this.slidingImage = slidingImage;
        this.jumping = false;
        this.sliding = false;
        this.normalWidth = width;
        this.normalHeight = height;
    }

    /**
     * Constructor: Player
     * @param images Image[]
     * @param duration double
     * @param jumpingImage Image
     * @param slidingImage Image
     * @param width double
     * @param height double
     */
    public Player( Image[] images, double duration, Image jumpingImage, Image slidingImage, double width, double height ) {
        super(images[ 0 ], width, height);
        this.animation = new AnimatedImage(images, duration);
        this.jumpingImage = jumpingImage;
        this.slidingImage = slidingImage;
        this.jumping = false;
        this.sliding = false;
        this.normalHeight = height;
    }

    /**
     * Method: getNormalWidth
     * @return normalWidth double
     */
    public double getNormalWidth() {
        return normalWidth;
    }

    /**
     * Method: setNormalWidth
     * @param normalWidth double
     */
    public void setNormalWidth( double normalWidth ) {
        this.normalWidth = normalWidth;
    }

    /**
     * Method: getNormalHeight
     * @return normalHeight double
     */
    public double getNormalHeight() {
        return normalHeight;
    }

    /**
     * Method: setNormalHeight
     * @param normalHeight double
     * Description: Set the normalHeight and the height
     */
    public void setNormalHeight( double normalHeight ) {
        this.normalHeight = normalHeight;
        this.setHeight(normalHeight);
    }

    /**
     * Method: getSlidingWidth
     * @return slidingWidth double
     */
    public double getSlidingWidth() {
        return slidingWidth;
    }

    /**
     * Method: setSlidingWidth
     * @param slidingWidth double
     */
    public void setSlidingWidth( double slidingWidth ) {
        this.slidingWidth = slidingWidth;
    }

    /**
     * Method: getSlidingHeight
     * @return slidingHeight double
     */
    public double getSlidingHeight() {
        return slidingHeight;
    }

    /**
     * Method: setSlidingHeight
     * @param slidingHeight double
     */
    public void setSlidingHeight( double slidingHeight ) {
        this.slidingHeight = slidingHeight;
    }

    /**
     * Method: getAnimation
     * @return animation AnimatedImage
     */
    public AnimatedImage getAnimation() {

        return animation;
    }

    /**
     * Method: setAnimation
     * @param animation AnimatedImage
     */
    public void setAnimation( AnimatedImage animation ) {

        this.animation = animation;
    }

    /**
     * Method: setAnimation
     * @param frames Image[]
     * @param duration double
     */
    public void setAnimation( Image[] frames, double duration ) {

        this.animation = new AnimatedImage(frames, duration);
    }

    /**
     * Method: getLives
     * @return lives int
     */
    public int getLives() {
        return lives;
    }

    /**
     * Method: setLives
     * @param lives int
     */
    public void setLives( int lives ) {
        this.lives = lives;
    }

    /**
     * Method: addALife
     * Description: Add a Life
     */
    public void addALife() {
        this.lives++;
    }

    /**
     * Method: removeALife
     * Description: Lose a Life
     */
    public void loseALife() {
        this.lives--;
    }

    /**
     * Method: getJumpingImage
     * @return jumpingImage Image
     */
    public Image getJumpingImage() {
        return jumpingImage;
    }

    /**
     * Method: setJumpingImage
     * @param jumpingImage Image
     */
    public void setJumpingImage( Image jumpingImage ) {
        this.jumpingImage = jumpingImage;
    }

    /**
     * Method: getSlidingImage
     * @return slidingImage Image
     */
    public Image getSlidingImage() {
        return slidingImage;
    }

    /**
     * Method: setSlidingImage
     * @param slidingImage Image
     */
    public void setSlidingImage( Image slidingImage ) {
        this.slidingImage = slidingImage;
    }

    /**
     * Method: getJumpingMedia
     * @return jumpingMedia Media
     */
    public Media getJumpingMedia() {
        return jumpingMedia;
    }

    /**
     * Method: setJumpingMedia
     * @param jumpingMedia jumpingMedia
     * Description: Create a jumping sound player
     */
    public void setJumpingMedia( Media jumpingMedia ) {
        this.jumpingMedia = jumpingMedia;
        this.jumpSound = new MediaPlayer(this.jumpingMedia);
    }

    /**
     * Method: setJumpingMedia
     * @param str String
     * Description: Create a jumping sound player
     */
    public void setJumpingMedia( String str ) {
        URL path = Player.class.getResource(str);
        File soundFile = new File(path.getPath());
        this.jumpingMedia = new Media(soundFile.toURI().toString());
        this.jumpSound = new MediaPlayer(this.jumpingMedia);
    }

    /**
     * Method: setJumpingMedia
     * @param file File
     * Description: Create a jumping sound player
     */
    public void setJumpingMedia( File file ) {

        File soundFile = file;
        this.jumpingMedia = new Media(soundFile.toURI().toString());
        this.jumpSound = new MediaPlayer(this.jumpingMedia);
    }

    /**
     * Method: getSlidingMedia
     * @return slidingMedia Media
     */
    public Media getSlidingMedia() {
        return slidingMedia;
    }

    /**
     * Method: setSlidingMedia
     * @param slidingMedia Media
     * Description: Create a sliding sound player
     */
    public void setSlidingMedia( Media slidingMedia ) {
        this.slidingMedia = slidingMedia;
        this.slidingSound = new MediaPlayer(this.slidingMedia);
    }

    /**
     * Method: setSlidingMedia
     * @param str String
     * Description: Create a sliding sound player
     */
    public void setSlidingMedia( String str ) {
        URL path = Player.class.getResource(str);
        File soundFile = new File(path.getPath());
        this.slidingMedia = new Media(soundFile.toURI().toString());
        this.slidingSound = new MediaPlayer(this.slidingMedia);
    }

    /**
     * Method: setSlidingMedia
     * @param file File
     * Description: Create a sliding sound player
     */
    public void setSlidingMedia( File file) {

        File soundFile = file;
        this.slidingMedia = new Media(soundFile.toURI().toString());
        this.slidingSound = new MediaPlayer(this.slidingMedia);
    }

    /**
     * Method: isJumping
     * @return jumping boolean
     */
    public boolean isJumping() {
        return jumping;
    }

    /**
     * Method: setJumping
     * @param jumping boolean
     */
    public void setJumping( boolean jumping ) {
        this.jumping = jumping;

        if ( !jumping && jumpSound != null ) {

            jumpSound.stop();
        }
    }

    /**
     * Method: isSliding
     * @return sliding boolean
     */
    public boolean isSliding() {
        return sliding;
    }

    /**
     * Method: setSliding
     * @param sliding boolean
     */
    public void setSliding( boolean sliding ) {
        this.sliding = sliding;

        if ( !sliding && slidingSound != null ) {

            slidingSound.stop();
        }
    }

    /**
     * Method: move
     * @param velocity double
     * Description: Add horizontal velocity
     */
    public void move( double velocity ) {

        addVelocity(velocity, 0);
    }

    /**
     * Method: jump
     * @param velocity double
     * Description: Add vertical velocity
     *                 set jumping to true
     */
    public void jump( double velocity ) {

        jumping = true;
        setImage(jumpingImage);
        addVelocity(0, velocity);

        if ( jumpSound != null ) {

            jumpSound.play();
        }
    }

    /**
     * Method: slide
     * Description: Set sliding to true
     *                  decrease height
     */
    public void slide() {

        sliding = true;
        setImage(slidingImage);

        if ( slidingWidth != 0 ) {

            setWidth(slidingWidth);
        }
        if ( slidingHeight != 0 ) {

            setHeight(slidingHeight);
        }

        if ( slidingSound != null ) {

            slidingSound.play();
        }
    }

    /**
     * Method: render
     * @param gc GraphicsContext
     * @param time double
     * Description: Redraw the image on the canvas
     */
    public void render( GraphicsContext gc, double time ) {

        if ( jumping ) {

            render(gc);
        }
        else if ( sliding ) {

            render(gc);
        }
        else {

            gc.drawImage(animation.getFrame(time), getPositionX(), getPositionY());
        }
    }
}
