package People;

import Primary.Direction;

import java.util.*;

public class Guest {
    private int x;
    private int y;
    private int retX;
    private int retY;
    public int speed;

    public Guest(int x, int y, int speed,int retX, int retY){
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
    public void observationWalk(String area) {
        int areaWidth, areaHeight, minX, minY, maxX, maxY;

        generateRandomWalk();

        switch (area) {
            case "top":
                areaWidth = 60;
                areaHeight = 55;

                minX = 250;
                minY =  100;
                maxX = minX + areaWidth;
                maxY = minY + areaHeight;

                correctGuestWalk(minX, minY, maxX, maxY);

                return;
            case "left":
                areaWidth = 55;
                areaHeight = 60;

                minX = 140;
                minY =  195;
                maxX = minX + areaWidth;
                maxY = minY + areaHeight;

                correctGuestWalk(minX, minY, maxX, maxY);

                return;

            case "right":
                areaWidth = 55;
                areaHeight = 60;

                minX = 375;
                minY = 195;
                maxX = minX + areaWidth;
                maxY = minY + areaHeight;

                correctGuestWalk(minX, minY, maxX, maxY);
        }

    }

    /**
     * Corrects the pathing of the guest walks if they go out of the bounds of the parking area
     * @param minX
     * @param minY
     * @param maxX
     * @param maxY
     */
    private void correctGuestWalk(int minX, int minY, int maxX, int maxY) {
        if(this.x > maxX) {
            this.x = maxX - 10;
        } else if(this.x < minX) {
            this.x = minX + 10;
        }

        if(this.y > maxY) {
            this.y = maxY - 10;
        } else if(this.y < minY) {
            this.y = minY + 10;
        }
    }

    /**
     * Generates random walking for the pedestrian
     */
    public void generateRandomWalk() {
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
