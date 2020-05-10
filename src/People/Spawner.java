package People;

import Graphics.Grounds.GuestGraphic;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class Spawner implements Runnable{
    private int spawnRate;
    private GraphicsContext gc;
    private List<Guest> arrived;

    public Spawner(int spawnRate, GraphicsContext gc){
        this.spawnRate = spawnRate;
        arrived = new ArrayList<>();
        this.gc = gc;
    }

    public void setSpawnRate(int spawnRate){
        this.spawnRate = spawnRate;
    }

    /**
     * Add guest to arrived list.
     * @param guest New guest to add to arrived list.
     */
    private void addGuest(Guest guest){
        arrived.add(guest);
    }

    /**
     * Used when sim is reset to clear arrived list.
     */
    public void clearGuests(){
        arrived = new ArrayList<>();
    }

    public void drawGuests(int x){
        for(int i = 0;i<x;i++){
            GuestGraphic guest = new GuestGraphic(gc, 100,100);

        }
    }

    /**
     * Generate random numbers with poisson distribution. Currently just
     * generates the number but will eventually be maintaining a list.
     * @param meanArr Average number of guests that should be arriving.
     * @return int denoting number of guess arriving.
     */
    public static int spawnGuests(double meanArr) {
        double l = Math.exp(-meanArr);
        double p = 1;
        int k = 0;

        while(p > l){
            k++;
            p *= Math.random();
        }
        return k;
    }

    @Override
    public void run(){
        while(spawnRate >= 0){
            System.out.println(spawnGuests(spawnRate));
            try{
                Thread.sleep(1000);
            }catch(InterruptedException i){
                i.printStackTrace();
            }
        }
    }

}
