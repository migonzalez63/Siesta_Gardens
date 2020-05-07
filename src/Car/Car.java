package Car;
import javafx.scene.shape.Rectangle;
import org.w3c.dom.css.Rect;

public class Car {

    private boolean locked;
    private boolean moving;
    private Rectangle movingArea;
    private double x;
    private double y;
    private int speed;

    public Car(boolean locked, boolean moving, Rectangle movingArea, double x, double y, int speed) {
        this.locked = locked;
        this.moving = moving;
        this.movingArea = movingArea;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public boolean isMoving() { return moving; }
    public void setMoving(boolean moving) { this.moving = moving; }
    public boolean isLocked() { return locked; }
    public void setLocked(boolean locked) { this.locked = locked; }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    //moves the car in a circle around the enclosure
    public void beginRoute(){
        double angle = Math.atan2(this.getY(),this.getX());
        if(angle < 0){
            angle += 2*Math.PI;
        }
        //System.out.println("x = " + this.getX() + ", y = " + this.getY() + ", theta = " + angle);
        //update angle
        angle += Math.PI/100; //todo change this to relate to speed attribute
        this.setX(Math.cos(angle));
        this.setY(Math.sin(angle));
    }
}
