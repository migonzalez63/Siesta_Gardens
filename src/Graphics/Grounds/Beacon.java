package Graphics.Grounds;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Beacon{
    private GraphicsContext gc;
    private int x;
    private int y;
    private final int size = 7;

    public Beacon(GraphicsContext gc,int x, int y){
        this.gc = gc;
        this.x = x;
        this.y = y;
    }


    public void drawBeacon(){
        gc.setFill(Color.BLACK);
        gc.fillRect(x,y,size,size);
    }

    public void drawEmergency(){
        gc.setFill(Color.RED);
        gc.fillRect(x,y,size,size);
    }

}
