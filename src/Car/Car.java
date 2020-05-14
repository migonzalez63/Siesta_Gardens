package Car;
import Primary.Direction;
import Primary.GuestHandling;
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

public class Car implements Runnable{

    private boolean locked;
    private boolean moving;
    private double x;
    private double y;
    private double cartesianX;
    private double cartesianY;
    private int speed;
    private int numPassengers;
    private int numPassengersPresent;
    private boolean emergency = false;
    private Direction parkingArea;
    private GuestHandling gh;
    private String lastRode;
    public Car(boolean locked, boolean moving, double x, double y, int speed,
               GuestHandling gh) {
        this.locked = locked;
        this.moving = moving;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.gh = gh;
        this.lastRode = "";
        this.parkingArea = Direction.SOUTH;
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

    // Set the emergency to be true
    // switch coordinate systems
    public void setEmergency(){
        this.emergency = true;
        this.y = cartesianY;
        this.x = cartesianX;
    }
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    //moves the car in a circle around the enclosure
    public void beginRoute(){
        //the car is locked and all passengers are in it
        if(this.isMoving() && this.isLocked() && !this.emergency) {
            double angle = Math.atan2(this.getY(), this.getX());
            if (angle < 0) {
                angle += 2 * Math.PI;
            }
            //System.out.println("x = " + this.getX() + ", y = " + this.getY() + ", theta = " + angle);
            //update angle
            angle -= Math.PI / 100;
            this.setX(Math.cos(angle));
            this.setY(Math.sin(angle));


            //Observation Areas in counter-clockwise order
            //First Observation Area
            int maxRightRange = 432;
            int minRightRange = 420;
            if(Math.floor(cartesianX) >= minRightRange && Math.floor(cartesianX) <= maxRightRange && !lastRode.equals("right")){
//            if((Math.floor(cartesianX) == Math.floor(429.9058495295867)  || Math.floor(cartesianY) == Math.floor(255.08435103741374)) && lastRode != "right"){
                //stop car and unload passengers

                this.parkingArea = Direction.EAST;
                lastRode = "right";
//                isParkedRight = true;
                gh.startDrawingRightObservation();
                pause(5000);
//                isParkedRight = false;
                gh.returnGuestsToVehicles("right");
                pause();
                pause(2000);
            }
            //Second Observation Area
            int maxTopRange = 83;
            int minTopRange = 77;
            if(Math.floor(cartesianY) >= minTopRange && Math.floor(cartesianY)<=maxTopRange && !lastRode.equals("top")){
//            if(this.getY() < -.999999 && this.getX() > -.0000001 && this.getX() < .0000001){
                //stop car and unload passengers
                this.parkingArea = Direction.NORTH;
//                isParkedTop = true;
                lastRode = "top";
                gh.startDrawingTopObservation();
                pause(5000);
//                isParkedTop = false;
                gh.returnGuestsToVehicles("top");
                pause();
                pause(2000);
//                setEmergency();

            }
            //Third Observation Area
            int maxLeftRange = 96;
            int minLeftRange = 87;
            if(Math.floor(cartesianX) >= minLeftRange && Math.floor(cartesianX) <= maxLeftRange && !lastRode.equals("left")){
                //stop car and unload passengers
                this.parkingArea = Direction.WEST;
                lastRode = "left";
                gh.startDrawingLeftObservation();
                pause(5000);
                gh.returnGuestsToVehicles("left");
                pause();
                pause(2000);
            }
            //South parking Area/passenger area
            int maxSpawnRange = 425;
            int minSpawnRange = 405;
            if(Math.floor(cartesianY) >= minSpawnRange && Math.floor(cartesianY) <= maxSpawnRange && !lastRode.equals("spawn"))
            if(this.getY() > .999999 && this.getX() > -.0000001 && this.getX() < .0000001){
                //stop car and unload passengers
//                System.out.println("Boarding car x = "+cartesianX +" y = "+ cartesianY);
                this.parkingArea = Direction.SOUTH;
                lastRode = "spawn";
                gh.startSpawning();
                pause(8000);
                pause(1500);
            }

        }else{
             goEmergencyRoute(this.parkingArea);
        }
    }

    private void pause(){
        this.stopCar();
        //unload the pedestrians around here
        try {
            Thread.sleep(3000);
        }catch (Exception e){
            System.err.println(e);
        }
        this.locked = true;
        this.moving = true;
    }

    private void pause(int waitTime){
        this.stopCar();
        //unload the pedestrians around here
        try {
            Thread.sleep(waitTime);
        }catch (Exception e){
            System.err.println(e);
        }
        this.locked = true;
        this.moving = true;
    }
    @Override
    public void run(){
        while(true){
            try {
                //necessary to slow the car down.
                Thread.sleep(10);
            }catch (Exception e){
                System.err.println(e);
            }
            beginRoute();

        }
    }


    private void goEmergencyRoute(Direction parkingArea){

        switch (parkingArea) {
            case NORTH:
                emergencyRouteNorth();
                break;
            case WEST:
                emergencyRouteWest();
                break;
            case EAST:
                emergencyRouteEast();
                break;
        }

    }
    //will replace with actual variables here in a minute
    private void emergencyRouteWest(){
        // Go Up to be parralel with the gate
        if (this.getY() >= 200 && this.getX() >= 10) {
            this.y -= 1;
            System.out.println(this.getY());
            // Go to the left to exit
        } else if (this.getX() >= 10) {
            this.x -= 1;
        } else {
            //Go down to the Pier
            if (this.getY() <= 450) {
                this.y += 1;
            }
        }
    }
    //will replace with actual variables here in a minute
    private void emergencyRouteEast(){
        // Go Up to be parallel with the gate
        if (this.getY() >= 200 && this.getX() <= 520) {
            this.y -= 1;
            //Go to the exit Gate to the right
        } else if (this.getX() <= 520) {
            this.x += 1;
        } else {
            //Down to the pier
            if (this.getY() <= 450) {
                this.y += 1;
            }
        }
    }
    //will replace with actual variables here in a minute
    private void emergencyRouteNorth(){
        // Up to exit the gate
        if (this.getY() >= 20 && this.getX() <= 520) {
            this.y -= 1;
            System.out.println(this.getY());
            //head right to right end of the enclosure
        } else if (this.getX() <= 520) {
            this.x += 1;
        } else {
            //down to pier
            if (this.getY() <= 450) {
                this.y += 1;
            }
        }
    }

    //sets the cartesian points of the car from its
    // regular radial path
    public void setCartesianPoints(double x, double y) {
        this.cartesianX = x;
        this.cartesianY = y;
    }

    //gets emergency
    public boolean isEmergency() {
        return emergency;
    }

    public double getCartesianX() {
        return cartesianX;
    }

    public double getCartesianY() {
        return cartesianY;
    }
}
