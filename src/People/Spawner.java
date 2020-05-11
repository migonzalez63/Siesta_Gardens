package People;

import Graphics.Grounds.GuestGraphic;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class Spawner{
    private int spawnRate;
    private GraphicsContext gc;
    private List<GuestGraphic> totalArrived;
    private List<GuestGraphic> currentQueue;

    public Spawner(int spawnRate, GraphicsContext gc){
        this.spawnRate = spawnRate;
        this.totalArrived = new ArrayList<>();
        this.currentQueue = new ArrayList<>();
        this.gc = gc;
    }

    public void setSpawnRate(int spawnRate){
        this.spawnRate = spawnRate;
    }

    /**
     * Add guest to arrived list.
     * @param guest New guest to add to arrived list.
     */
    private void addGuest(GuestGraphic guest){
        this.totalArrived.add(guest);
    }

    /**
     * Used when sim is reset to clear arrived list.
     */
    public void clearGuests(){
        this.totalArrived = new ArrayList<>();
        this.currentQueue = new ArrayList<>();
    }


    /**
     * Generate random numbers with poisson distribution. Currently just
     * generates the number but will eventually be maintaining a list.
     * @param meanArr Average number of guests that should be arriving.
     * @return int denoting number of guess arriving.
     */
    public void spawnGuests() {
        double l = Math.exp(-spawnRate);
        double p = 1;
        int k = 0;

        while(p > l){
            k++;
            p *= Math.random();
        }

        for(int i =0; i<k;i++){
            //at the spawn point
            addGuest(new GuestGraphic(gc,280,443));
        }
//        return k;
    }

}
