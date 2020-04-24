package People;

import java.util.ArrayList;
import java.util.List;

public class Spawner implements Runnable{
    private int spawnRate;
    private List<Guest> arrived;

    public Spawner(int spawnRate){
        this.spawnRate = spawnRate;
        arrived = new ArrayList<>();
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
//            spawnRate -=5;
        }
    }

    public static void main(String[] args){
        Thread t = new Thread(new Spawner(20));
        t.start();
    }
}
