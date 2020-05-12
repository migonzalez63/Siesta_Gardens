package Primary;

import Graphics.Grounds.CarGraphic;
import Graphics.Grounds.DinoGraphic;
import Graphics.Grounds.ParkGrounds;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends Application {


    /**
     * This is the primary controller for the program. This will create the intersection, create the Graphics, and give the
     * intersection to the TestTCS program for them to run. This will also call run() on the TestTCS code.
     */

    @Override
    public void start(Stage primaryStage) {
        AtomicBoolean parkEmergency= new AtomicBoolean(false);


        // Setup border pane with HBox of two buttons along the top and
        // canvas to display the simulation in the center, also initialize
        // the controller with something to draw on
        BorderPane root = new BorderPane();
        Canvas canvas = new Canvas(550, 550);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        GuestHandling gh = new GuestHandling(gc);
        VBox controlBox = new VBox(20);
        HBox speedBox = new HBox(10);
        VBox pedSpeedBox = new VBox(10);
        VBox carSpeedBox = new VBox(10);

        controlBox.setPadding(new Insets(0,5,0,5));
        speedBox.setPadding(new Insets(0,0,20,0));
        controlBox.setPrefSize(300, 550);
        controlBox.setStyle("-fx-background-color: #e0e0e0;");

        Label resultLabel = new Label("");
        resultLabel.setPrefSize(150, 50);

        Button malfunctionModeButton= new Button("⚠Emergency Mode");
        Button resetButton = new Button("Reset");
        malfunctionModeButton.setStyle("-fx-background-color: red;-fx-text-fill: white; -fx-font: 14px Calibri; -fx-border-width: 1;-fx-border-color: black;");

       resetButton.setStyle("-fx-background-color: #4775d1;-fx-text-fill: white; -fx-font: 14px Calibri;");


        malfunctionModeButton.setPrefSize(160, 30);
        resetButton.setPrefSize(160, 30);

        // Handle button press actions

        malfunctionModeButton.setOnMousePressed(e -> {
            parkEmergency.set(true);
            gh.interruptSpawning();
        });


        resetButton.setOnMousePressed(e -> {
            parkEmergency.set(false);
            //controller.reset();
        });


        // Setup the scene

        speedBox.getChildren().addAll(pedSpeedBox, carSpeedBox);
        controlBox.getChildren().addAll( malfunctionModeButton, resetButton, resultLabel, speedBox);
        root.setRight(controlBox);
        root.setLeft(canvas);

        Scene scene = new Scene(root, 720, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Siesta Garden Control System: Testbed");
//        List<Guest>
        ParkGrounds parkground = new ParkGrounds(gc, canvas);

        Rectangle dinoEnclosure = parkground.getDinoEnclosure();
        DinoGraphic dino = new DinoGraphic(gc,dinoEnclosure,3,50);

        CarGraphic car = new CarGraphic(gc,260,250,900,900, 170);
        // So this is where I plan to spawn the guests. x = 280, y = 443, So
        // just a bit above it with y = 440 maybe 439 is best.
//        GuestHandling gh = new GuestHandling(gc);
        parkground.drawGrounds(parkEmergency.get());

        primaryStage.show();
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                parkground.drawGrounds(parkEmergency.get());
                dino.drawDinosaur();
                car.drawCar();
            }
        }.start();
         primaryStage.setOnCloseRequest(event -> {
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
