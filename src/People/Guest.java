package People;

import Primary.Direction;

import java.util.*;

/**
 * Logic for the guests walking and underlying components are here.
 */
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

    public void setRetX(int retX) {
        this.retX = retX;
    }

    public void setRetY(int retY) {
        this.retY = retY;
    }

    /**
     * Used for random walking and making sure they do not go out of bounds.
     */
    public void observationWalk(String area) {
        int areaWidth, areaHeight, minX, minY, maxX, maxY;

        // Generates random walk for the guest
        generateRandomWalk(area);

        // Depending on the area, we will define its bounds and correct the pathing if necessary
        switch (area) {
            case "top":
                areaWidth = 60;
                areaHeight = 55;

                minX = 250;
                minY =  95;
                maxX = minX + areaWidth;
                maxY = minY + areaHeight;

                correctGuestWalk(minX, minY, maxX, maxY);

                return;
            case "left":
                areaWidth = 50;
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

                minX = 380;
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
            this.x = maxX - 3;
        } else if(this.x < minX) {
            this.x = minX + 3;
        }

        if(this.y > maxY) {
            this.y = maxY - 3;
        } else if(this.y < minY) {
            this.y = minY + 3;
        }
    }

    /**
     * Generates random walking for the pedestrian
     */
    private void generateRandomWalk(String area) {
        Random random = new Random();
        String[] directions = {"north","south","east","west"};
        switch (directions[(random.nextInt(4))]) {
            case "north":
                this.y -= speed;
                break;
            case "south":
                if(area.equals("top"))this.y += speed+1;
                else this.y += speed;
                break;
            case "east":
                if(area.equals("left"))this.x += speed+1;
                else this.x += speed;
                break;
            case "west":
                if(area.equals("right"))this.x -= speed+1;
                else this.x -= speed;
                break;
        }
    }

    /**
     * Use this method to draw guests walking back to a certain points.
     * @param retX Return point x
     * @param retY Return point y
     * @return boolean determining if at return point.
     */
    public boolean walkToPoint(int retX, int retY){
        if(atRetPoint()) return true;
        if (x < retX) x+=speed;
        if (x > retX) x-=speed;
        if (y > retY) y-=speed;
        if (y < retY) y+=speed;
        return false;
    }


    /**
     * Checks if guest has arrived at spawn point.
     * @return
     */
    public boolean atRetPoint(){
        return x == retX && y == retY;
    }

}
