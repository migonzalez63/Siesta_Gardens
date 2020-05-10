package Graphics.Grounds;
import Dinosaur.Dino;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

public class DinoGraphic {
    private Dino dino;
    private GraphicsContext gc;
    private int size;
    public DinoGraphic(GraphicsContext gc, Rectangle walkingArea,int speed,int size){
        this.gc = gc;
        this.dino = new Dino(walkingArea,speed,size);
        this.size = size;
    }

    public void drawDinosaur(){
        dino.randomWalk();
        Image dinoImage = new Image("/images/dinosaur.png");
        gc.drawImage(dinoImage,dino.getX(),dino.getY(),size,size);
    }

    public Dino getDino() {
        return dino;
    }
}
