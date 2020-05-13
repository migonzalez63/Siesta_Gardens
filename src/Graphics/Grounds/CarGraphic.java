package Graphics.Grounds;

import Car.Car;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CarGraphic {
    private Car car;
    private GraphicsContext gc;
    private int circleWidth,circleHeight = 150;
    private int movingRadius;
    private int xOffset;
    private int yOffset;
    private Thread carThread;
    public CarGraphic(GraphicsContext gc, int xOffset, int yOffset, int unitX, int unitY, int movingRadius){
        this.gc = gc;
        this.movingRadius = movingRadius;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.car = new Car(true,true,unitX,unitY,1);
        this.carThread =new Thread(car);
        carThread.start();
    }

    public void drawCar(){
//        car.beginRoute();
        gc.setFill(Color.GRAY);

        //Keep the car going in radial motion in normal operations
        //store the cartesian points in case of emergency
        if(!car.isEmergency()) {
            gc.fillRect(xOffset + car.getX() * this.movingRadius, yOffset + car.getY() * this.movingRadius, 20, 20);
            car.setCartesianPoints(xOffset + car.getX() * this.movingRadius,yOffset + car.getY() * this.movingRadius);

        }else {
            //if there is an emergency, draw the regular x and y which are now
            // in cartesian form
            gc.fillRect(car.getX(),car.getY(), 20, 20);
            car.setCartesianPoints( car.getX() , car.getY());

        }
    }

    public Car getCar() {
        return car;
    }
}
