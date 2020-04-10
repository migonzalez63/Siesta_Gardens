package Graphics.Grounds;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ParkGrounds {

    private final GraphicsContext gc;
    private final Canvas canvas;

    public ParkGrounds(GraphicsContext gc, Canvas canvas){
        this.gc = gc;
        this.canvas=canvas;
    }

    public void drawGrounds(){
        int bargeSize=60;
        int ticketingAreaSize=40;
        int outsideCarBorder=50;
        int carLineArea=30;
        double normalLineWidth=gc.getLineWidth();

        //Largest rectangle
        gc.setFill(Color.DIMGREY);
        gc.strokeRect(0,0,canvas.getWidth(),canvas.getHeight());
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());

        //Barge
        gc.setFill(Color.rgb(51,204,255));
        gc.strokeRect(0, canvas.getHeight()-bargeSize, canvas.getWidth(), bargeSize);
        gc.fillRect(0, canvas.getHeight()-bargeSize, canvas.getWidth(), bargeSize);

        //Ticketing Area/Wait area
        gc.setFill(Color.POWDERBLUE);
        gc.strokeRect(40,canvas.getHeight()-bargeSize-ticketingAreaSize,canvas.getWidth()-60,
                ticketingAreaSize);
        gc.fillRect(40,canvas.getHeight()-bargeSize-ticketingAreaSize,canvas.getWidth()-60,
                ticketingAreaSize);

        //Largest enclosure with gates
        gc.setLineWidth(3);
        gc.setFill(Color.LIGHTGREEN);
        gc.fillRect(outsideCarBorder,outsideCarBorder,canvas.getWidth()-(2*outsideCarBorder),
                canvas.getHeight()-outsideCarBorder-bargeSize-ticketingAreaSize);
        gc.strokeRect(outsideCarBorder,outsideCarBorder,canvas.getWidth()-(2*outsideCarBorder),
                canvas.getHeight()-outsideCarBorder-bargeSize-ticketingAreaSize);
        gc.setLineWidth(normalLineWidth);

        //Car line area
        gc.setFill(Color.DIMGREY);
        gc.fillRect(outsideCarBorder,canvas.getWidth()-bargeSize-ticketingAreaSize-carLineArea,
                canvas.getWidth()-(2*outsideCarBorder),carLineArea);

    }
}
