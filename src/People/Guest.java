package People;

import Primary.Direction;

import java.util.*;

public class Guest {
    private int x;
    private int y;
    public int speed;

    public Guest(int x, int y, int speed){
        this.x = x;
        this.y = y;
        this.speed = speed;
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

    public void observationWalk(){
        Random random = new Random();
        List<Direction> dirs =
                new ArrayList<Direction>(EnumSet.allOf(Direction.class));
        switch (dirs.get(random.nextInt(4))){
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

    public void boardingWalk(){

    }

    public void unboardingWalk(){

    }

}
