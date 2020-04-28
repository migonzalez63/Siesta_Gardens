package Graphics.Grounds;
import Dinosaur.Dino;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DinoGraphic {
    private Dino dino;
    private Rectangle drawingArea;
    private GraphicsContext gc;
    private int ovalWidth,ovalHeight = 100;
    public DinoGraphic(GraphicsContext gc, int posX, int posY, int width, int height){
        this.gc = gc;
        this.drawingArea = new Rectangle(posX,posY,width,height);
        this.dino = new Dino(drawingArea,1,posX,posY);
    }

    public void drawDinosaur(){
        dino.randomWalk();
        gc.setFill(Color.DARKGREEN);
        gc.fillOval(dino.getX(),dino.getY(),50,50);
    }



}
