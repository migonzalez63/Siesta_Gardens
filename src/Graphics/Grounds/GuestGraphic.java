package Graphics.Grounds;

import People.Guest;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GuestGraphic {
    private Guest guest;
    private GraphicsContext gc;
    private final int size = 6;

    public GuestGraphic(GraphicsContext gc,int x, int y){
        this.gc = gc;
        guest = new Guest(x,y, 1);
    }


    public void observationDraw(){
        guest.observationWalk();
        gc.setFill(Color.ORANGE);
        gc.fillOval(guest.getX(),guest.getY(),size,size);

    }

    public void initialSpawn(){
        gc.setFill(Color.ORANGE);
        gc.fillOval(guest.getX(),guest.getY(),size,size);
    }


    public void despawn(){
        gc.setFill(Color.DIMGREY);
    }

    public void unboardingSpawn(){

    }
}
