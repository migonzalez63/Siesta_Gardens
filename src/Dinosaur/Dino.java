package Dinosaur;

import Primary.Direction;
import javafx.geometry.Bounds;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Dino {

    private int x;
    private int y;
    private int speed;
    private int size;
    private boolean isContained= true;
    private Rectangle walkingArea;
    private Direction lastDirection;
    private int directionSteps;

    public Dino(Rectangle walkingArea, int speed,int size){
        this.walkingArea = walkingArea;
        this.speed = speed;
        this.size = size;
        this.directionSteps=0;
        this.lastDirection=Direction.EAST;
        this.x =(int) (walkingArea.getX() + walkingArea.getWidth()/3);
        this.y =(int) (walkingArea.getY() + walkingArea.getHeight()/3);

    }

    public void randomWalk(){
        int originalX = this.x;
        int originalY = this.y;
        if (directionSteps==9){
            Random random = new Random();
            Direction[] directions = Direction.values();
            Direction direction = directions[random.nextInt(directions.length)];
            this.lastDirection=direction;
            switch (direction){
                case NORTH:
                    if(withinBounds(originalX, originalY-speed)) this.y -= speed;
                    break;
                case EAST:
                    if(withinBounds(originalX+speed, originalY)) this.x += speed;
                    break;
                case NORTHEAST:
                    if (withinBounds(originalX, originalY-speed) && withinBounds(originalX+speed, originalY)){
                        this.y-=speed;
                        this.x +=speed;
                    }
                    break;
                case SOUTH:
                    if(withinBounds(originalX, originalY+speed)) this.y += speed;
                    break;
                case SOUTHEAST:
                    if (withinBounds(originalX, originalY+speed) && withinBounds(originalX+speed, originalY)){
                        this.y+=speed;
                        this.x+=speed;
                    }
                    break;
                case WEST:
                    if(withinBounds(originalX-speed, originalY)) this.x -= speed;
                    break;
                case SOUTHWEST:
                    if (withinBounds(originalX, originalY+speed) && withinBounds(originalX-speed, originalY)){
                        this.y+=speed;
                        this.x-=speed;
                    }
                    break;
                case NORTHWEST:
                    if (withinBounds(originalX, originalY-speed) && withinBounds(originalX-speed, originalY)){
                        this.x-=speed;
                        this.y-=speed;
                    }
                    break;
            }
            directionSteps=0;
        }
        else {
            switch (lastDirection){
                case NORTH:
                    if(withinBounds(originalX, originalY-speed)) this.y -= speed;
                    break;
                case EAST:
                    if(withinBounds(originalX+speed, originalY)) this.x += speed;
                    break;
                case NORTHEAST:
                    if (withinBounds(originalX, originalY-speed) && withinBounds(originalX+speed, originalY)){
                        this.y-=speed;
                        this.x +=speed;
                    }
                    break;
                case SOUTH:
                    if(withinBounds(originalX, originalY+speed)) this.y += speed;
                    break;
                case SOUTHEAST:
                    if (withinBounds(originalX, originalY+speed) && withinBounds(originalX+speed, originalY)){
                        this.y+=speed;
                        this.x+=speed;
                    }
                    break;
                case WEST:
                    if(withinBounds(originalX-speed, originalY)) this.x -= speed;
                    break;
                case SOUTHWEST:
                    if (withinBounds(originalX, originalY+speed) && withinBounds(originalX-speed, originalY)){
                        this.y+=speed;
                        this.x-=speed;
                    }
                    break;
                case NORTHWEST:
                    if (withinBounds(originalX, originalY-speed) && withinBounds(originalX-speed, originalY)){
                        this.x-=speed;
                        this.y-=speed;
                    }
                    break;
            }
            directionSteps++;
        }
    }
    private  boolean withinBounds(int newX, int newY){


        if(!isContained) return  false;
        //checks whether the point is within the rectange inside the animal enclosure
        int dinoBoundsX = newX + size;
        int dinoBoundsY = newY + size;
        double maxX = walkingArea.getX() + walkingArea.getWidth();
        double maxY = walkingArea.getY() + walkingArea.getHeight();
        double minX = walkingArea.getX()+size;
        double minY = walkingArea.getY()+size;
        return  (dinoBoundsX > minX && dinoBoundsY > minY && dinoBoundsX < maxX && dinoBoundsY < maxY );
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void free() {
        this.isContained = false;
    }
    public void reset() {
        this.isContained = false;
        this.x =(int) (walkingArea.getX() + walkingArea.getWidth()/3);
        this.y =(int) (walkingArea.getY() + walkingArea.getHeight()/3);

    }

}
