package People;

import Primary.Direction;

import java.util.*;

public class Guest {
    private int x;
    private int y;
    private int retX;
    private int retY;
    public int speed;

    //    left parking
//    gc.fillRect(140,195,
//            55,60);
//    // Right Parking Space
//        gc.fillRect(375,195,
//                55,60);
//    // Top Parking Space
//        gc.fillRect(250,100,
//                60,55);

    public Guest(int x, int y, int speed, int retX, int retY){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.retX = retX;
        this.retY = retY;

    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Used for random walking but need to limit area of walking still.
     */
    public void observationWalk() {
        Random random = new Random();
        List<Direction> dirs =
                new ArrayList<Direction>(EnumSet.allOf(Direction.class));
        switch (dirs.get(random.nextInt(4))) {
            case NORTH:
                this.y -= speed;
                break;
            case SOUTH:
                this.y += speed;
                break;
            case EAST:
                this.x += speed;
                break;
            case WEST:
                this.x -= speed;
                break;
        }
    }

    /**
     * Here the guest returns back to the car, in reality this could be used
     * for unboarding.
     * @param retX Return point x
     * @param retY Return point y
     * @return boolean determining if at return point.
     */
    public boolean walkToPoint(int retX, int retY){
        if(atRetPoint()) return true;
        if(x<retX) x++;
        if(x>retX) x--;
        if(y>retY) y--;
        if(y<retY) y++;

        return false;
    }

    public void unboardWalk(){

    }

    /**
     * Checks if guest has arrived at spawn point.
     * @return
     */
    public boolean atRetPoint(){
        return x == retX && y == retY;
    }

}
