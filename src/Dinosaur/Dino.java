package Dinosaur;

import Primary.Direction;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Dino {

    private int x;
    private int y;
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;
    private int speed;
    private Rectangle walkingArea;

    public Dino(Rectangle walkingArea, int speed,int x, int y){
        this.walkingArea = walkingArea;
        this.speed = speed;
        this.maxX = (int) (walkingArea.getX() + walkingArea.getWidth());
        this.maxY = (int) (walkingArea.getX() + walkingArea.getWidth());
        this.minX = (int) walkingArea.getX();
        this.minY = (int) walkingArea.getY();
//        this.x = maxX/2;
//        this.y = maxY/2;
//        this.x =(int) (walkingArea.getX() + walkingArea.getWidth()/2);
//        this.y =(int) (walkingArea.getY() + walkingArea.getHeight()/2);
        this.x = x;
        this.y = y;

    }

    public void randomWalk(){
        Random random = new Random();
        Direction[] directions = Direction.values();
        Direction direction = directions[random.nextInt(directions.length)];
        switch (direction){
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

    private boolean withinWalkingArea(int newX, int newY){
        return (newX <= maxX && newY <= maxY && newX >= minX && newY >= minY);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
