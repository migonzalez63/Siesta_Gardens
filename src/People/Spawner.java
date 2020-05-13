package People;

import javafx.animation.AnimationTimer;
import java.util.ArrayList;
import java.util.List;

public class Spawner{
    private int spawnRate;
    private int time = 32400; //about 9 AM
    private List<Integer> spawnTimes, boardTimes;
    private AnimationTimer timePassage;

    public Spawner(int spawnRate){
        this.spawnRate = spawnRate;
        this.spawnTimes = new ArrayList<>();
        this.boardTimes = new ArrayList<>();
        startTime();
    }

    /**
     * Begins the timer and adding of guests to queue.
     */
    public void startTime(){
        timePassage = new AnimationTimer() {
            @Override
            public void handle(long now) {
                incrementTime(spawnGuests(spawnRate));
            }
        };
        timePassage.start();
    }

    /**
     * Clears the lists maintaining guest times and restarts the clock.
     */
    public void resetTime(){
        if(timePassage==null) return;
        timePassage.stop();
        System.out.println(message());
        clearGuests();
        time = 32400;
        startTime();
    }

    /**
     * Change spawn rate
     * @param spawnRate Int of avg number of people arriving.
     */
    public void setSpawnRate(int spawnRate){
        this.spawnRate = spawnRate;
    }

    /**
     * returns String denoting current statistics.
     * @return
     */
    public synchronized String message(){
        int queue = leftInQueue();
        int hours = avgWaitTime()/3600;
        int minutes = (avgWaitTime()%3600)/60;

        return "average wait time "+String.format("%02d",
                hours)+
                ":"+ String.format("%02d",minutes)+" those left in queue " +
                "is "+boardTimes.size()+"/"+queue;
    }


    /**
     * Used when sim is reset to clear arrived list.
     */
    private void clearGuests(){
        this.spawnTimes = new ArrayList<>();
        this.boardTimes = new ArrayList<>();
    }

    public synchronized int getTime(){
        return time;
    }

    /**
     * Determines how many have not ridden yet.
     * @return
     */
    private synchronized int leftInQueue(){
        return spawnTimes.size() - boardTimes.size();
    }

    private synchronized void incrementTime(int passengerArrival){
        time += 60;
        int i = 0;
        while(i != passengerArrival){
            spawnTimes.add(time);
            i++;
        }

    }

    public synchronized void setWaitTime(int n){
        int i = 0;
        while(i!=n){
            boardTimes.add(time);
            i++;
        }
    }

    private synchronized int avgWaitTime(){
        int weight = 0;
        if(boardTimes.size() == 0) return weight;
        if(spawnTimes.size() >= boardTimes.size()){
            for(int i = 0;i<boardTimes.size();i++){
                weight += (boardTimes.get(i) - spawnTimes.get(i));
            }
            return weight/boardTimes.size();
        }else{
            for(int i = 0;i<spawnTimes.size();i++){
                weight += (boardTimes.get(i) - spawnTimes.get(i));
            }
            return weight/spawnTimes.size();
        }
    }


    /**
     * Generate random numbers with poisson distribution. Currently just
     * generates the number but will eventually be maintaining a list.
     * @param meanArr Average number of guests that should be arriving.
     * @return int denoting number of guess arriving.
     */
    private int spawnGuests(int meanArr) {
        double l = Math.exp(-spawnRate);
        double p = 1;
        int k = 0;

        while(p > l){
            k++;
            p *= Math.random();
        }
        return k;
    }

}
