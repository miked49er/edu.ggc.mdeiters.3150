import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Class: Sprite
 * @author Mike Deiters
 * @version 1.0
 * November 18, 2015
 * ITEC 2110
 *
 * Description: Game Sprite
 *
 * Purpose: Create a sprite
 */
public class Sprite {

    private Image image;
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;
    private double gravity;

    private boolean visible;
    private boolean solid;
    private boolean harmful;
    private boolean ground;

    /**
     * Constructor: Sprite
     */
    public Sprite() {
        this.visible = true;
        this.solid = false;
        this.harmful = false;
        this.ground = false;
    }

    /**
     * Constructor: Sprite
     * @param image Image
     * @param width double
     * @param height double
     */
    public Sprite( Image image, double width, double height ) {
        this.image = image;
        this.velocityX = 0;
        this.velocityY = 0;
        this.width = width;
        this.height = height;
        this.visible = true;
        this.solid = false;
        this.harmful = false;
        this.ground = false;
    }

    /**
     * Constructor: Sprite
     * @param image Image
     * @param positionX double
     * @param positionY double
     * @param width double
     * @param height double
     */
    public Sprite( Image image, double positionX, double positionY, double width, double height ) {
        this.image = image;
        this.positionX = positionX;
        this.positionY = positionY;
        this.velocityX = 0;
        this.velocityY = 0;
        this.width = width;
        this.height = height;
        this.visible = true;
        this.solid = false;
        this.harmful = false;
        this.ground = false;
    }

    /**
     * Constructor: Sprite
     * @param image Image
     * @param positionX double
     * @param positionY double
     * @param velocityX double
     * @param velocityY double
     * @param width double
     * @param height double
     */
    public Sprite( Image image, double positionX, double positionY, double velocityX, double velocityY, double width, double height ) {
        this.image = image;
        this.positionX = positionX;
        this.positionY = positionY;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.width = width;
        this.height = height;
        this.visible = true;
        this.solid = false;
        this.harmful = false;
        this.ground = false;
    }

    /**
     * Method: getImage
     * @return image Image
     */
    public Image getImage() {
        return image;
    }

    /**
     * Method: setImage
     * @param str String of the Image location
     */
    public void setImage( String str ) {

        this.image = new Image(str);
    }

    /**
     * Method: setImage
     * @param image Image
     */
    public void setImage( Image image ) {
        this.image = image;
    }

    /**
     * Method: getPositionX
     * @return positionX double
     */
    public double getPositionX() {
        return positionX;
    }

    /**
     * Method: setPositionX
     * @param positionX double
     */
    public void setPositionX( double positionX ) {
        this.positionX = positionX;
    }

    /**
     * Method: getPositionY
     * @return double
     */
    public double getPositionY() {
        return positionY;
    }

    /**
     * Method: setPositionY
     * @param positionY double
     */
    public void setPositionY( double positionY ) {
        this.positionY = positionY;
    }

    /**
     * Method: setPosition
     * @param x double
     * @param y double
     * Description: set the x and y position
     */
    public void setPosition( double x, double y ) {

        this.positionX = x;
        this.positionY = y;
    }

    /**
     * Method: getVelocityX
     * @return velocityX double
     */
    public double getVelocityX() {
        return velocityX;
    }

    /**
     * Method: velocityX
     * @param velocityX double
     */
    public void setVelocityX( double velocityX ) {
        this.velocityX = velocityX;
    }

    /**
     * Method: getVelocityY
     * @return velocityY
     */
    public double getVelocityY() {
        return velocityY;
    }

    /**
     * Method: setVelocityY
     * @param velocityY double
     */
    public void setVelocityY( double velocityY ) {
        this.velocityY = velocityY;
    }

    /**
     * Method: setVelocity
     * @param x double
     * @param y double
     * Description: set the x and y velocities
     */
    public void setVelocity( double x, double y ) {

        this.velocityX = x;
        this.velocityY = y;
    }

    public void addVelocity( double x, double y ) {

        this.velocityX += x;
        this.velocityY += y;
    }

    /**
     * Method: getGravity
     * @return gravity double
     */
    public double getGravity() {
        return gravity;
    }

    /**
     * Method: setGravity
     * @param gravity double
     */
    public void setGravity( double gravity ) {
        this.gravity = gravity;
    }

    /**
     * Method: getWidth
     * @return width double
     */
    public double getWidth() {
        return width;
    }

    /**
     * Method: setWidth
     * @param width double
     */
    public void setWidth( double width ) {
        this.width = width;
    }

    /**
     * Method: getHeight
     * @return height double
     */
    public double getHeight() {
        return height;
    }

    /**
     * Method: setHeight
     * @param height double
     */
    public void setHeight( double height ) {
        this.height = height;
    }

    /**
     * Method: isVisible
     * @return visible boolean
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Method: setVisible
     * @param visible boolean
     */
    public void setVisible( boolean visible ) {
        this.visible = visible;
    }

    /**
     * Method: isSolid
     * @return solid boolean
     */
    public boolean isSolid() {
        return solid;
    }

    /**
     * Method: setSolid
     * @param solid boolean
     */
    public void setSolid( boolean solid ) {
        this.solid = solid;
    }

    /**
     * Method: isHarmful
     * @return harmful boolean
     */
    public boolean isHarmful() {
        return harmful;
    }

    /**
     * Method; setHarmful
     * @param harmful boolean
     */
    public void setHarmful( boolean harmful ) {
        this.harmful = harmful;
    }

    /**
     * Method: isGround
     * @return ground boolean
     */
    public boolean isGround() {
        return ground;
    }

    /**
     * Method: setGround
     * @param ground booleans
     */
    public void setGround( boolean ground ) {
        this.ground = ground;
    }

    /**
     * Method: update
     * @param time double
     * Description: update positionX and positionY by adding the x and y velocity multiplied by time
     */
    public void update( double time ) {

        time = time / 250;
        positionX += velocityX * time;
        positionY += velocityY * time;
        velocityY += gravity * time;
    }

    /**
     * Method: render
     * @param gc GraphicsContext
     * Description: Redraw the image on the canvas
     */
    public void render( GraphicsContext gc ) {

        if ( visible ) {

            gc.drawImage(image, positionX, positionY);
        }
    }

    /**
     * Method: getBoundary
     * @return boundaries of the Sprite Rectangle2D
     */
    public Rectangle2D getBoundary() {

        return new Rectangle2D(positionX, positionY, width, height);
    }

    /**
     * Method: intersects
     * @param s Sprite
     * @return boolean true if the sprite intersects with s
     */
    public boolean intersects( Sprite s ) {

        return s.getBoundary().intersects(this.getBoundary());
    }
}
