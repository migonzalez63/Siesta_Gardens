package Primary;

import Graphics.Grounds.Beacon;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class BeaconHandling {
    private List<Beacon> beacons;
    private GraphicsContext gc;

    public BeaconHandling(GraphicsContext gc) {
        this.gc = gc;
        beacons = new ArrayList<>();
        initialize();
    }

    private void initialize(){
        beacons.add(new Beacon(gc, 100, 120));
        beacons.add(new Beacon(gc, 460,120));
        beacons.add(new Beacon(gc, 460, 400));
        beacons.add(new Beacon(gc, 100,400));
    }

    public void drawNormal(){
        for(Beacon b: beacons){
            b.drawBeacon();
        }
    }


    public void drawEmergency(){
        for(Beacon b:beacons){
            b.drawEmergency();
        }
    }


}
