package Graphics.Grounds;

import People.Guest;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class GuestGraphic {
    private Guest guest;
    private GraphicsContext gc;
    private final int size = 6;
    String area;

    public GuestGraphic(GraphicsContext gc,int x, int y, String area){
        this.gc = gc;
        this.area = area;
        if(area.equals("spawn")) guest = new Guest(x,y,2,280,443);
        else if(area.equals("exit")) guest = new Guest(x,y,2,210,443);
        else guest = new Guest(x,y, 1, x, y);
    }

    public GuestGraphic(GraphicsContext gc,int x, int y){
        this.gc = gc;
        guest = new Guest(x,y, 1, x, y);
    }

    public Guest getGuest() {
        return guest;
    }

    /**
     * Draws the guest in a observation area.
     */
    public void observationDraw(){
        guest.observationWalk(area);
        reInit();
    }

    /**
     * Used to rest the guests location.
     * @param x x coordinate
     * @param y y coordinate
     */
    public void resetGuestLoc(int x, int y) {
        guest.setX(x);
        guest.setY(y);
    }

    /**
     * Used to draw at the park entrance.
     */
    public void initialSpawn(){
        gc.setFill(Color.ORANGE);
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
