package Graphics;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Simulation {

    public static final int size = 100; // size of the main intersection square that determines lane width
    public double carSpeed = 1;
    public double pedSpeed = 1;

    private final GraphicsContext gc;

    private Boolean endSim = false;

    public Simulation(GraphicsContext gc){
        this.gc = gc;
        //this.intersection = new Intersection(gc);
        setUp(); // set up roads and connect to intersection
    }


    // Redraws the intersection on each animation loop a
    // and all the traffic in it's new positions
    //
    public void drawTraffic(){

    }


    public Boolean updateSpots(){
        return true;
    }


    // Spawns a new car on a random start lane with a
    // random destination lane
    //
    public synchronized void spawnCar(Boolean emergency){

    }

    public void addPedSpeed(Boolean b) {
        if (b) pedSpeed += 0.1; else pedSpeed -= 0.2;
    }

    public void addCarSpeed(Boolean b) {
        if (b) carSpeed += 0.1; else carSpeed -= 0.1;
    }


    // Show ugly end pop up
    //
    private void showEnd(){
        gc.setStroke(Paint.valueOf("#4f4f4f"));
        gc.strokeText("Resetting sim...", gc.getCanvas().getWidth() * .70 , 50, 250);
    }



    // Draws the initial setup with no traffic
    //
    private void drawInterSection() {
        gc.setFill(Paint.valueOf("#33334d"));
        //intersection.draw();
        if (endSim) showEnd();
    }


    // Creates new Intersection with four connecting roads,
    // and draws the start of the simulation
    //
    private void setUp(){

    }


}
