package Graphics.Grounds;

import Car.Car;
import Primary.GuestHandling;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class CarGraphic {
    private Car car;
    private GraphicsContext gc;
    private GuestHandling gh;
    private int circleWidth,circleHeight = 150;
    private int movingRadius;
    private int xOffset;
    private int yOffset;
    private Thread carThread;
    public CarGraphic(GraphicsContext gc, int xOffset, int yOffset, int unitX
            , int unitY, int movingRadius, GuestHandling gh){
        this.gh = gh;
        this.gc = gc;
        this.movingRadius = movingRadius;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.car = new Car(true,true,unitX,unitY,1,gh);
        this.carThread =new Thread(car);
        carThread.start();
    }



    public void drawCar(){
//        car.beginRoute();
        gc.setFill(Color.GRAY);
        Image carImage = new Image("/images/carVertical.png");


        //Keep the car going in radial motion in normal operations
        //store the cartesian points in case of emergency
        if(!car.isEmergency()) {
            gc.drawImage(carImage,xOffset + car.getX() * this.movingRadius,yOffset + car.getY() * this.movingRadius,20,30);
           // gc.fillRect(xOffset + car.getX() * this.movingRadius, yOffset + car.getY() * this.movingRadius, 20, 20);
            car.setCartesianPoints(xOffset + car.getX() * this.movingRadius,yOffset + car.getY() * this.movingRadius);
            Image alarmImage = new Image("/images/alarmOff.png");
            gc.drawImage(alarmImage,xOffset + car.getX() * this.movingRadius+15,yOffset + car.getY() * this.movingRadius+15,20,20);
        }else {
            //if there is an emergency, draw the regular x and y which are now
            // in cartesian form
            gc.drawImage(carImage,car.getX(),car.getY(),20,30);
            Image alarmImage = new Image("/images/alarmOn.png");
            gc.drawImage(alarmImage,car.getX()+15,car.getY()+15,20,20);

//            gc.fillRect(car.getX(),car.getY(), 20, 20);
//            car.setCartesianPoints( car.getX() , car.getY());

        }
    }

    public void reset() {
        car = new Car(true,true,0,-1,1,gh);
        this.carThread =new Thread(car);
        carThread.start();
    }

    public Car getCar() {
        return car;
    }
}
