package Graphics.Grounds;

import Car.Car;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CarGraphic {
    private Car car;
    private Rectangle drawingArea;
    private GraphicsContext gc;
    private int circleWidth,circleHeight = 150;
    private int movingRadius;
    private int xOffset;
    private int yOffset;

    public CarGraphic(GraphicsContext gc, int xOffset, int yOffset, int width, int height, int movingRadius){
        this.gc = gc;
        this.drawingArea = new Rectangle(100,100,width,height); //todo remove drawingArea
        this.movingRadius = movingRadius;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.car = new Car(false,false,drawingArea,0,-1,1);
    }

    public void drawCar(){
        car.beginRoute();
        gc.setFill(Color.GRAY);
//        if(car.getX() > 0){
//            if(car.getY() > 0){
//                //x and y > 0
//                gc.fillRect(xOffset + car.getX() * this.movingRadius,yOffset - car.getY() * this.movingRadius,20,20);
//            }
//            else{
//                //x > 0 and y < 0
//                gc.fillRect(xOffset + car.getX() * this.movingRadius,yOffset + car.getY() * this.movingRadius,20,20);
//
//            }
//        }
//        else{
//            if(car.getY() > 0){
//                //x < 0 and y > 0
//                gc.fillRect(xOffset - car.getX() * this.movingRadius,yOffset - car.getY() * this.movingRadius,20,20);
//            }
//            else{
//                //x and y < 0
//                gc.fillRect(xOffset - car.getX() * this.movingRadius,yOffset + car.getY() * this.movingRadius,20,20);
//            }
//        }
        gc.fillRect(xOffset + car.getX() * this.movingRadius,yOffset + car.getY() * this.movingRadius,20,20);

    }
}
