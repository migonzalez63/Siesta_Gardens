package Primary;

import Graphics.Grounds.GuestGraphic;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Deals with the drawing of the guests and related objects. Here is where
 * controller can interact with guests events.
 */
public class GuestHandling {
    private List<GuestGraphic> leftViewing;
    private List<GuestGraphic> rightViewing;
    private List<GuestGraphic> topViewing;
    private GuestGraphic newSpawn,unboardSpawn;
    private GraphicsContext gc;
    private final int carMax = 10;
    private Point leftParking = new Point(140,214);
    private Point rightParking = new Point(423,214);
    private Point topParking = new Point(270,100);
    private Point spawn = new Point(280,443);
    private Point boardSpawn = new Point(290, spawn.y);
    private Point exit = new Point(210,spawn.y);
    private AnimationTimer leftPark, rightPark, topPark, spawnPark, unboardPark;
    private BeaconHandling bh;


    public GuestHandling(GraphicsContext gc){
        this.gc = gc;
        this.leftViewing = new ArrayList<>();
        this.topViewing = new ArrayList<>();
        this.rightViewing = new ArrayList<>();
        this.newSpawn = new GuestGraphic(gc, boardSpawn.x, boardSpawn.y,
                "spawn");
        this.unboardSpawn = new GuestGraphic(gc, spawn.x, spawn.y,"exit");
        initialize();
    }



    /**
     * Handles drawing of guest walking around left observation area. Invoke
     * this when car parks in left spot.
     */
    public void startDrawingLeftObservation(){
        leftPark = new  AnimationTimer(){
            @Override
            public void handle(long now){
                drawLeft();
            }
        };
        leftPark.start();
    }

    /**
     * Handles drawing of guest walking around right observation area. Invoke
     * this when car parks at right spot.
     */
    public void startDrawingRightObservation(){
        rightPark = new  AnimationTimer(){
            @Override
            public void handle(long now){
                drawRight();
            }
        };
        rightPark.start();
    }

    /**
     * Handles drawing of guest walking around top observation area. Invoke
     * this when car parks in top spot.
     */
    public void startDrawingTopObservation(){
       topPark = new AnimationTimer(){
            @Override
            public void handle(long now){
                drawTop();
            }
        };
       topPark.start();
    }

    /**
     * Handles returning guests to the vehicles. 4 inputs: "left" returns the
     * left parking area guests, "right" returns the right parking area
     * guests, "top" returns the top parking area guests, and anything else
     * will be deemed an emergency and return all guests to the vehicles.
     * @param area Parking area where guests need to return to vehicle.
     */
    public void returnGuestsToVehicles(String area){
        if(allClear()) return;
        else if(area.equals("left")) leftPark.stop();
        else if(area.equals("right")) rightPark.stop();
        else if(area.equals("top")) topPark.stop();
        else{
            if(leftPark!= null)leftPark.stop();
            if(rightPark!=null)rightPark.stop();
            if(topPark!=null)topPark.stop();
        }

        new AnimationTimer(){
            @Override
            public void handle(long now){
                if(area.equals("left")){
                    returnLeft();
                    if(readyToDespawn(leftViewing)) this.stop();
                }
                else if(area.equals("right")){
                    returnRight();
                    if(readyToDespawn(rightViewing)) this.stop();
                }
                else if(area.equals("top")){
                    returnTop();
                    if(readyToDespawn(topViewing)) this.stop();
                }
                else{
                    returnLeft();
                    returnRight();
                    returnTop();
                    if(allClear())this.stop();
                }
            }
        }.start();
    }

    /**
     * Draws people boarding the vehicle at the spawn point. Invoke this when
     * car is at boarding area.
     */
    public void startSpawning(){
//        trackTimeBoarding();
       spawnPark = new AnimationTimer(){
            int x = 0;
            @Override
            public void handle(long now) {
                newSpawn.walkToPointDraw(spawn.x,spawn.y);
                if(newSpawn.readytoDespawn()){
                    newSpawn.resetGuestLoc(boardSpawn.x, boardSpawn.y);
                    clearBoarding();
                    x++;
                }
                if(x>=10)this.stop();
            }
        };
       spawnPark.start();
    }

    /**
     * Draws people exiting the vehicle at the spawn point. Invoke this first
     * before startSpawning() if we plan on doing unboarding.
     */
    public void startUnboarding(){
        unboardPark = new AnimationTimer() {
            int x = 0;
            @Override
            public void handle(long now) {
                unboardSpawn.walkToPointDraw(exit.x, exit.y);
                if(unboardSpawn.readytoDespawn()){
                    unboardSpawn.resetGuestLoc(spawn.x, spawn.y);
                    clearExit();
                    x++;
                }
                if(x>=10)this.stop();
            }
        };
        unboardPark.start();
    }

    /**
     * Used to stop the spawning of guest for boarding process, should be
     * used for emergency mode.
     */
    public void interruptSpawning(){
        if (spawnPark!=null){
            spawnPark.stop();
        }
        newSpawn.resetGuestLoc(boardSpawn.x, boardSpawn.y);
        clearBoarding();
    }

    /**
     * Used when reset button is pressed. Resets all guests to default
     * starting points and stops all timers drawing them.
     */
    public void resetAllGuests(){
        interruptSpawning();
        if(leftPark!=null){
            leftPark.stop();
            redrawLeftParking();
            for(GuestGraphic g: leftViewing){
                g.resetGuestLoc(leftParking.x, leftParking.y);
            }
        }
        if(rightPark!=null){
            rightPark.stop();
            redrawRightParking();
            for(GuestGraphic g: rightViewing){
                g.resetGuestLoc(rightParking.x, rightParking.y);
            }
        }
        if(topPark!=null){
            topPark.stop();
            redrawTopParking();
            for(GuestGraphic g: topViewing){
                g.resetGuestLoc(topParking.x, topParking.y);
            }
        }
    }


    /************************************************
     * Private methods here
     ************************************************/

    /**
     * draws the guests in the left parking area.
     */
    private void drawLeft(){
        for(GuestGraphic g: leftViewing){
            g.observationDraw();
        }
    }

    /**
     * draws the guests in the right parking area.
     */
    private void drawRight(){
        for(GuestGraphic g: rightViewing){
            g.observationDraw();
        }
    }

    /**
     * draws the guests in the top parking area.
     */
    private void drawTop(){
        for(GuestGraphic g: topViewing){
            g.observationDraw();
        }
    }

    /**
     * Makes the guests in the left parking area return to the spawn point.
     */
    private void returnLeft(){
        for(GuestGraphic g: leftViewing){
            g.walkToPointDraw(leftParking.x,leftParking.y);
            if(readyToDespawn(leftViewing)) redrawLeftParking();
        }
    }

    /**
     * Makes the guests in the right parking area return to their spawn point.
     */
    private void returnRight(){
        for(GuestGraphic g: rightViewing){
            g.walkToPointDraw(rightParking.x,rightParking.y);
            if(readyToDespawn(rightViewing)) redrawRightParking();
        }
    }

    /**
     * Makes the guests in the top parking area return to their spawn point.
     */
    private void returnTop(){
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
//            spawnPoint.add(new GuestGraphic(gc,350,443, "spawn"));
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

    /**
     * Clears the last passenger drawn boarding.
     */
    private void clearBoarding(){
        gc.setFill(Color.DIMGREY);
        int x = newSpawn.getGuest().getX();
        int y = newSpawn.getGuest().getY();
        gc.fillRect(x, y,6,6);
    }

    /**
     * clears the last passenger drawn exiting.
     */
    private void clearExit(){
        gc.setFill(Color.DIMGREY);
        gc.fillRect(210,443,6,6);
    }

    /**
     * Checks if guests at all parking areas are ready to be despawned.
     * @return Boolean if guests are at respectful spawn points.
     */
    private boolean allClear(){
        return readyToDespawn(leftViewing) && readyToDespawn(rightViewing) && readyToDespawn(topViewing);
    }
}
