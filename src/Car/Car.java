package Car;
import javafx.scene.shape.Rectangle;
import org.w3c.dom.css.Rect;
/*todo:
    aspects of the cars to figure out:
        emergency mode
            how do we preempt car motion during emergency mode?
                use hard coded routes that start from each observation area and end at the barge
                Case 1) Car is already stopped at observation area
                    send car along the proper hard coded route
                Case 2) Car is already moving
                    send car too the next observation area
                    then send it along the proper hard coded route to the barge
        loading and unloading at the beginning and end of each trip
        stopping and starting to/from each observation area
            add global variable for the time each car will spend stopped at the observation areas
            use a timer to stop the car at the observation area for the amount of time we decided above
            Start the car's movement to the next observation area after the time is up and all passengers are in the car

    add fields for the number of passengers assigned to the car and the number of passengers in the car
    remove speed attribute and just have one predetermined speed
 */

public class Car {

    private boolean locked;
    private boolean moving;
    private double x;
    private double y;
    private int speed;
    private int numPassengers;
    private int numPassengersPresent;

    public Car(boolean locked, boolean moving, double x, double y, int speed) {
        this.locked = locked;
        this.moving = moving;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public boolean isMoving() { return moving; }

    public void setMoving(boolean moving) { this.moving = moving; }

    public boolean isLocked() { return locked; }
    public void setLocked(boolean locked) { this.locked = locked; }
    public double getX() { return x; }
    public double getY() { return y; }

    //stops the car
    public void stopCar(){
        this.setMoving(false);
        this.setLocked(false);
    }

    //checks if the car is locked before switching its state to moving
    public void startCar(){
        if(this.isLocked()) {
            this.setMoving(true);
        }
        else{
            this.setMoving(false);
        }
    }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    //moves the car in a circle around the enclosure
    public void beginRoute(){
        //the car is locked and all passengers are in it
        if(this.isMoving() && this.isLocked()) {
            double angle = Math.atan2(this.getY(), this.getX());
            if (angle < 0) {
                angle += 2 * Math.PI;
            }
            //System.out.println("x = " + this.getX() + ", y = " + this.getY() + ", theta = " + angle);
            //update angle
            angle -= Math.PI / 100;
            this.setX(Math.cos(angle));
            this.setY(Math.sin(angle));

            //check to see if we've reached the first observation area and stop the car
            if(this.getX() > .999999 && this.getY() > -.0000001 && this.getY() < .0000001){
                //stop car and unload passengers
                this.stopCar();
                System.out.println("x: " + this.getX() + ", y: " + this.getY() + " , moving: " + this.isMoving() + ", locked: " + this.isLocked());
            }
            //check to see if we've reached the second observation area and stop the car
            if(this.getY() > .999999 && this.getX() > -.0000001 && this.getX() < .0000001){
                //stop car and unload passengers
                this.stopCar();
                System.out.println("x: " + this.getX() + ", y: " + this.getY() + " , moving: " + this.isMoving() + ", locked: " + this.isLocked());
            }
            //check to see if we've reached the third observation area and stop the car
            if(this.getX() < -.999999 && this.getY() > -.0000001 && this.getY() < .0000001){
                //stop car and unload passengers
                this.stopCar();
                System.out.println("x: " + this.getX() + ", y: " + this.getY() + " , moving: " + this.isMoving() + ", locked: " + this.isLocked());
            }
        }
    }
}
