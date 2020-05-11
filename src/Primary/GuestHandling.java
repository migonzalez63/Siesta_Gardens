package Primary;

import Graphics.Grounds.GuestGraphic;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GuestHandling {
    private List<GuestGraphic> leftViewing;
    private List<GuestGraphic> rightViewing;
    private List<GuestGraphic> topViewing;
    private GraphicsContext gc;
    private final int carMax = 10;
    private Point leftParking = new Point(140,214);
    private Point rightParking = new Point(423,214);
    private Point topParking = new Point(270,100);

    public GuestHandling(GraphicsContext gc){
        this.gc = gc;
        this.leftViewing = new ArrayList<>();
        this.topViewing = new ArrayList<>();
        this.rightViewing = new ArrayList<>();
        initialize();
    }

    /**
     * draws the guests in the left parking area.
     */
    public void drawLeft(){
        for(GuestGraphic g: leftViewing){
            g.observationDraw();
        }
    }

    /**
     * draws the guests in the right parking area.
     */
    public void drawRight(){
        for(GuestGraphic g: rightViewing){
            g.observationDraw();
        }
    }

    /**
     * draws the guests in the top parking area.
     */
    public void drawTop(){
        for(GuestGraphic g: topViewing){
            g.observationDraw();
        }
    }

    /**
     * Makes the guests in the left parking area return to the spawn point.
     */
    public void returnLeft(){
        for(GuestGraphic g: leftViewing){
            g.walkToPointDraw(leftParking.x,leftParking.y);
            if(readyToDespawn(leftViewing)) redrawLeftParking();
        }
    }

    /**
     * Makes the guests in the right parking area return to their spawn point.
     */
    public void returnRight(){
        for(GuestGraphic g: rightViewing){
            g.walkToPointDraw(rightParking.x,rightParking.y);
            if(readyToDespawn(rightViewing)) redrawRightParking();
        }
    }

    /**
     * Makes the guests in the top parking area return to their spawn point.
     */
    public void returnTop(){
        for(GuestGraphic g: topViewing){
            g.walkToPointDraw(topParking.x,topParking.y);
            if(readyToDespawn(topViewing)) redrawTopParking();
        }
    }

    /**
     * Used to clear out the left parking space after guests returned to
     * spawn point
     */
    private void redrawLeftParking(){
        gc.setFill(Color.rgb(100, 118, 135));
        gc.fillRect(140,195,
                55,60);
    }

    /**
     * Used to clear out the right parking space after guests returned to
     * spawn point
     */
    private void redrawRightParking(){
        gc.setFill(Color.rgb(100, 118, 135));

        gc.fillRect(375,195,
                55,60);
    }

    /**
     * Used to clear out the top parking space after guests returned to
     * spawn point
     */
    private void redrawTopParking(){
        gc.setFill(Color.rgb(100, 118, 135));
        gc.fillRect(250,100,
                60,55);
    }

    /**
     * draws all the guests in the parking areas. Won't draw them physically
     * until draw_ method is called.
     */
    private void initialize(){
        for(int i = 0;i < carMax;i++){
            leftViewing.add(new GuestGraphic(gc, leftParking.x,leftParking.y, "left"));
            rightViewing.add(new GuestGraphic(gc,rightParking.x,
                    rightParking.y, "right"));
            topViewing.add(new GuestGraphic(gc, topParking.x,topParking.y, "top"));
        }
    }

    /**
     * Checks to see if all the guests in a list is ready to be despawned.
     * @param list Guests at parking area
     * @return Boolean denoting whether all the guests in a parking area is
     * ready to be drawn out.
     */
    private boolean readyToDespawn(List<GuestGraphic> list){
        for(GuestGraphic l: list){
            if(!l.readytoDespawn()) return false;
        }
        return true;
    }
}
