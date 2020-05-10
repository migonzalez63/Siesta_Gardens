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
        guest = new Guest(x,y, 1, x, y);
    }

    /**
     * Draws the guest in a observation area.
     */
    public void observationDraw(){
        guest.observationWalk();
        reInit();
    }

    /**
     * Used to draw at the park entrance.
     */
    public void initialSpawn(){
        gc.setFill(Color.ORANGE);
        gc.fillOval(guest.getX(),guest.getY(),size,size);
    }


    public void despawn(){
        gc.setFill(Color.DIMGREY);
        gc.fillOval(guest.getX(),guest.getY(),size,size);
    }

    /**
     * Draws the guests walking back to spawn point.
     * @param x OG spawn point.
     * @param y OG spawn point
     * @return Boolean denoting if it has arrived at spawn point.
     */
    public boolean walkToPointDraw(int x, int y){
        boolean ret = guest.walkToPoint(x, y);
        reInit();
        return ret;
    }

    public void unboardDraw(){

    }

    /**
     * Used to determine if a guest is at its spawn point already.
     * @return Boolean if guest is at spawn point.
     */
    public boolean readytoDespawn(){
        return guest.atRetPoint();
    }

    /**
     * Draws the guest again.
     */
    private void reInit(){
        gc.setFill(Color.ORANGE);
        gc.fillOval(guest.getX(),guest.getY(),size,size);
    }

}
