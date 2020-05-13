package Primary;

import Car.Car;
import Dinosaur.Dino;
import Graphics.Grounds.CarGraphic;
import Graphics.Grounds.DinoGraphic;
import Graphics.Grounds.GuestGraphic;
import Graphics.Grounds.ParkGrounds;
import People.Guest;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    /**
     * This is the primary controller for the program. This will create the intersection, create the Graphics, and give the
     * intersection to the TestTCS program for them to run. This will also call run() on the TestTCS code.
     */

    @Override
    public void start(Stage primaryStage) {


        // Setup border pane with HBox of two buttons along the top and
        // canvas to display the simulation in the center, also initialize
        // the controller with something to draw on
        BorderPane root = new BorderPane();
        Canvas canvas = new Canvas(550, 550);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        GuestHandling gh = new GuestHandling(gc);
        ParkGrounds parkground = new ParkGrounds(gc, canvas);
        Rectangle dinoEnclosure = parkground.getDinoEnclosure();
        DinoGraphic dinoGraphic = new DinoGraphic(gc,dinoEnclosure,3,50);
        Dino dino = dinoGraphic.getDino();
        CarGraphic car = new CarGraphic(gc,260,250,0,-1, 170);
        CarGraphic car1 = new CarGraphic(gc,260,250,1,0, 170);
        CarGraphic car2 = new CarGraphic(gc,260,250,0,1, 170);

//        CarGraphic car1 = new CarGraphic(gc,260,250,900,900, 170);
//        CarGraphic car2 = new CarGraphic(gc,330,0,900,900, 170);

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

        Label controlLabel = new Label("Modes:\n\n");
        controlLabel.setPrefSize(150, 100);

        Button rushButton = new Button("\uD83C\uDF1E Rush Hour");
        Button heavyButton = new Button("☀ Heavy Visitor Traffic");
        Button moderateButton = new Button("☀ Moderate Visitor Traffic");
        Button malfunctionModeButton= new Button("⚠Emergency Mode");
        Button spawnCarButton = new Button("Spawn Car");
        Button spawnEmergencyButton = new Button("Spawn Emergency");
        Button spawnPedButton = new Button("Spawn Pedestrian");
        Button resetButton = new Button("Reset");

        Label pedLabel = new Label("Ped Speed");
        pedLabel.setFont(new Font("Serif", 12));
        Label pedSpeedVal = new Label("1");
        pedSpeedVal.setFont(new Font("Serif", 12));
        Button walkFaster = new Button("\u2191");
        Button walkSlower = new Button("\u2193");

        Label carLabel = new Label("Car Speed");
        carLabel.setFont(new Font("Serif", 12));
        Label carSpeedVal = new Label("1");
        carSpeedVal.setFont(new Font("Serif", 12));
        Button driveFaster = new Button("\u2191");
        Button driveSlower = new Button("\u2193");




        rushButton.setStyle("-fx-background-color: #f2740b;-fx-text-fill: white; -fx-font: 14px Calibri;-fx-border-width: 1;-fx-border-color: #e36700;");
        malfunctionModeButton.setStyle("-fx-background-color: red;-fx-text-fill: white; -fx-font: 14px Calibri; -fx-border-width: 1;-fx-border-color: black;");
        heavyButton.setStyle("-fx-background-color: #f2740b;-fx-text-fill: white; -fx-font: 14px Calibri; -fx-border-width: 1;-fx-border-color: #e36700;");
        moderateButton.setStyle("-fx-background-color: #f2740b;-fx-text-fill: white; -fx-font: 14px Calibri;-fx-border-width: 1;-fx-border-color: #e36700;");

        spawnCarButton.setStyle("-fx-background-color: #ffffff;-fx-text-fill: #1f3d7a; -fx-border-radius: 2; -fx-border-width: 1; -fx-border-color: #1f3d7a; -fx-font: 13px Calibri;");
        spawnEmergencyButton.setStyle("-fx-background-color: #ffffff;-fx-text-fill: #1f3d7a; -fx-border-radius: 2; -fx-border-width: 1; -fx-border-color: #1f3d7a; -fx-font: 13px Calibri;");
        spawnPedButton.setStyle("-fx-background-color: #ffffff;-fx-text-fill: #1f3d7a; -fx-border-radius: 2; -fx-border-width: 1; -fx-border-color: #1f3d7a; -fx-font: 13px Calibri;");

        resetButton.setStyle("-fx-background-color: #4775d1;-fx-text-fill: white; -fx-font: 14px Calibri;");
        walkFaster.setStyle("-fx-background-color: #4775d1;-fx-text-fill: white; -fx-font: 14px Calibri;");
        walkSlower.setStyle("-fx-background-color: #4775d1;-fx-text-fill: white; -fx-font: 14px Calibri;");
        driveFaster.setStyle("-fx-background-color: #4775d1;-fx-text-fill: white; -fx-font: 14px Calibri;");
        driveSlower.setStyle("-fx-background-color: #4775d1;-fx-text-fill: white; -fx-font: 14px Calibri;");


        rushButton.setPrefSize(160, 30);
        malfunctionModeButton.setPrefSize(160, 30);
        heavyButton.setPrefSize(160, 30);
        moderateButton.setPrefSize(160, 30);
        spawnCarButton.setPrefSize(160, 30);
        spawnEmergencyButton.setPrefSize(160, 30);
        spawnPedButton.setPrefSize(160, 30);
        resetButton.setPrefSize(160, 30);

        //Controller controller = new Controller(gc);
        //controller.start();

        // Handle button press actions
        rushButton.setOnMousePressed(e -> {
            //uncomment this if you want to see people spawn
//            gh.startDrawingLeftObservation();
//            gh.startDrawingRightObservation();
//            gh.startDrawingTopObservation();
        });
        heavyButton.setOnMousePressed(e -> {
            //controller.heavyMode(controlLabel);
            //DayNight.DAY.setDay(true);
            //controller.setTICSMode(TICSModes.DayMode);
        });
        moderateButton.setOnMousePressed(e -> {
            //controller.moderateMode(controlLabel);
            // DayNight.DAY.setDay(true);
            //controller.setTICSMode(TICSModes.DayMode);
        });

        malfunctionModeButton.setOnMousePressed(e -> {
            //controller.malfunctionMode(controlLabel);
//            DayNight.DAY.setDay(true);
            //controller.setTICSMode(TICSModes.MalfunctionMode);
            gh.returnGuestsToVehicles("all");
            gh.interruptSpawning();
            dino.free();
            car.getCar().setEmergency();
            car1.getCar().setEmergency();
            car2.getCar().setEmergency();
        });


        resetButton.setOnMousePressed(e -> {
            //controller.reset();
            carSpeedVal.setText("1");
            pedSpeedVal.setText("1");
            gh.resetAllGuests();
            dino.reset();
        });


        // Setup the scene
        carSpeedBox.getChildren().addAll(carLabel, carSpeedVal, driveFaster, driveSlower);
        pedSpeedBox.getChildren().addAll(pedLabel, pedSpeedVal, walkFaster, walkSlower);
        speedBox.getChildren().addAll(pedSpeedBox, carSpeedBox);
        controlBox.getChildren().addAll(controlLabel, rushButton, heavyButton, moderateButton,malfunctionModeButton, spawnCarButton, spawnEmergencyButton, spawnPedButton, resetButton, resultLabel, speedBox);
        root.setRight(controlBox);
        root.setLeft(canvas);

        Scene scene = new Scene(root, 720, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Siesta Garden Control System: Testbed");
//        List<Guest>
        // So this is where I plan to spawn the guests. x = 280, y = 443, So
        // just a bit above it with y = 440 maybe 439 is best.
//        GuestHandling gh = new GuestHandling(gc);
        parkground.drawGrounds(true);

        primaryStage.show();
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                parkground.drawGrounds(true);
                dinoGraphic.drawDinosaur();
                car.drawCar();
                car1.drawCar();
                car2.drawCar();
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
