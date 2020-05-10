package Graphics.Grounds;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public class ParkGrounds {

    private final GraphicsContext gc;
    private final Canvas canvas;

    public ParkGrounds(GraphicsContext gc, Canvas canvas){
        this.gc = gc;
        this.canvas=canvas;
    }

    public void drawGrounds(boolean emergency){
        int bargeSize=60;
        int ticketingAreaSize=40;
        int outsideCarBorder=50;
        int carLineArea=30;
        double normalLineWidth=gc.getLineWidth();


        //Largest rectangle
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.rgb(201,201,201));
        gc.strokeRect(0,0,canvas.getWidth(),canvas.getHeight());
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());

        //Barge
        gc.setFill(Color.rgb(51,204,255));
        gc.strokeRect(0, canvas.getHeight()-bargeSize, canvas.getWidth(), bargeSize);
        gc.fillRect(0, canvas.getHeight()-bargeSize, canvas.getWidth(), bargeSize);



        //Largest enclosure with gates
        double enclosureX = outsideCarBorder;
        double enclosureY = outsideCarBorder;
        double enclosureWidth = canvas.getWidth()-(2*outsideCarBorder);
        double enclosureHeight =canvas.getHeight()-outsideCarBorder-bargeSize-ticketingAreaSize;

        //Largest enclosure
        gc.setLineWidth(3);
        gc.setFill(Color.rgb(204,255,153));
        gc.fillRect(enclosureX, enclosureY, enclosureWidth, enclosureHeight);
        gc.strokeRect(enclosureX, enclosureY, enclosureWidth, enclosureHeight);
        gc.setLineWidth(normalLineWidth);


        //Animal Enclosure
        gc.setLineWidth(3);
        gc.setStroke(Color.BLACK);
        gc.strokeOval(enclosureX + enclosureWidth/4, enclosureY+ enclosureHeight/8,enclosureWidth*0.50,enclosureHeight * 0.75);
        gc.setLineWidth(normalLineWidth);

        //Automatic Gates for Largest enclosure
        //Switch the color (and size of the line of gate) depending on gate enacted or not
        if (emergency){
            gc.setLineWidth(normalLineWidth);
            gc.setStroke(Color.rgb(201,201,201));
        }
        else {
            gc.setLineWidth(6);
            gc.setStroke(Color.RED);
        }


        //Oval Car Path
        gc.setFill(Color.rgb(201,201,201));
        gc.fillOval(outsideCarBorder+40, outsideCarBorder+20,
                canvas.getWidth()-(2*outsideCarBorder)-20-40,
                canvas.getHeight()-outsideCarBorder-bargeSize-20);

        //Horizontal Grey Rectangle Car Path
        gc.setFill(Color.rgb(201,201,201));
        gc.fillRect(outsideCarBorder,outsideCarBorder+(canvas.getHeight()-outsideCarBorder-bargeSize-ticketingAreaSize)/3,
                canvas.getWidth()-2*outsideCarBorder, 60);

        //Vertical Grey Rectangle Car Path
        gc.fillRect(outsideCarBorder+(canvas.getWidth()-outsideCarBorder-bargeSize-ticketingAreaSize)/2,outsideCarBorder,
                60,canvas.getHeight()-outsideCarBorder-bargeSize-20);

        //Left gate
        gc.strokeLine(outsideCarBorder,outsideCarBorder+(canvas.getHeight()-outsideCarBorder-bargeSize-ticketingAreaSize)/2,
                outsideCarBorder, outsideCarBorder+(canvas.getHeight()-outsideCarBorder-bargeSize-ticketingAreaSize)/3);
        // Top Gate
        gc.strokeLine(outsideCarBorder+(canvas.getWidth()-outsideCarBorder-bargeSize-ticketingAreaSize)/2,outsideCarBorder,
                outsideCarBorder+(canvas.getWidth()-outsideCarBorder-bargeSize-ticketingAreaSize)/2+80, outsideCarBorder);
        //Right Gate
        gc.strokeLine(canvas.getWidth()-(2*outsideCarBorder)+outsideCarBorder,outsideCarBorder+(canvas.getHeight()-outsideCarBorder-bargeSize-ticketingAreaSize)/3,
                canvas.getWidth()-(2*outsideCarBorder)+outsideCarBorder, outsideCarBorder+(canvas.getHeight()-outsideCarBorder-bargeSize-ticketingAreaSize)/2);
        gc.setStroke(Color.BLACK);
        //Grassy Oval
        gc.setFill(Color.rgb(230,255,204));
        gc.fillOval(outsideCarBorder+80, outsideCarBorder+50,
                canvas.getWidth()-(2*outsideCarBorder)-140,
                300);


        //Oval Animal Enclosure
        gc.setFill(Color.rgb(0,138,0));
        gc.fillOval(outsideCarBorder+150, outsideCarBorder+110,
                canvas.getWidth()-(2*outsideCarBorder)-280,
                200);
        gc.setLineWidth(4);
        gc.strokeOval(outsideCarBorder+150, outsideCarBorder+110,
                canvas.getWidth()-(2*outsideCarBorder)-280,
                200);

        //Ticketing Area/Wait area
        gc.setFill(Color.POWDERBLUE);
        gc.strokeRect(40,canvas.getHeight()-bargeSize-ticketingAreaSize,canvas.getWidth()-60,
                ticketingAreaSize);
        gc.fillRect(40,canvas.getHeight()-bargeSize-ticketingAreaSize,canvas.getWidth()-60,
                ticketingAreaSize);
        //Car line area
        gc.setFill(Color.DIMGREY);
        gc.fillRect(outsideCarBorder,canvas.getWidth()-bargeSize-ticketingAreaSize-carLineArea,
                canvas.getWidth()-(2*outsideCarBorder),carLineArea);

        // Left Parking Space
        gc.setFill(Color.rgb(100, 118, 135));
        gc.fillRect(140,195,
                55,60);
        // Right Parking Space
        gc.fillRect(375,195,
                55,60);
        // Top Parking Space
        gc.fillRect(250,100,
                60,55);



        if (emergency){
            Image boatImage = new Image("/images/boat.jpg");
            gc.drawImage(boatImage,0,canvas.getHeight()-55, 100, 50);
            gc.drawImage(boatImage,canvas.getWidth()-100,canvas.getHeight()-55, 100, 50);
        }
    }

    public Rectangle getDinoEnclosure(){
        int bargeSize=60;
        int ticketingAreaSize=40;
        int outsideCarBorder=50;
        //Largest enclosure with gates
        double enclosureX = outsideCarBorder;
        double enclosureY = outsideCarBorder;
        double enclosureWidth = canvas.getWidth()-(2*outsideCarBorder);
        double enclosureHeight =canvas.getHeight()-outsideCarBorder-bargeSize-ticketingAreaSize;
        //lotta magic numbers here
        int offset = 10;
        return new Rectangle(enclosureX + enclosureWidth*.38+ offset, enclosureY+ enclosureHeight/3 + 20,enclosureWidth*0.25,enclosureHeight * 0.25);


    }
}
