package com.example.demo2;

import java.io.*;
import java.security.cert.X509CertSelector;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.*;
import static javafx.scene.paint.Color.BEIGE;

public class DroneInterface extends Application {
    private MyCanvas mc;
    private AnimationTimer timer;
    private VBox rtPane;
    private DroneArena arena;

    public void drawWorld () {
        Stop[] stops = new Stop[] { new Stop(0, INDIGO), new Stop(1, BLUE)};
        LinearGradient lngnt = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
        mc.gc.setFill(lngnt);
        mc.gc.fillRect(0, 0, 2.5*mc.xCanvasSize, 2.5*mc.yCanvasSize);
        arena.drawArena(mc);
    }
    private HBox setButtons() {

        Alert S = new Alert(AlertType.NONE);
        Button btnStart = new Button("Start"); // begins and displays a message
        btnStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timer.start();
                S.setAlertType(AlertType.INFORMATION);
                S.setContentText("Drone Animation Started!");
                S.show();
                drawWorld();
            }
        });

        Alert P = new Alert(AlertType.NONE); // pauses
        Button btnStop = new Button("Pause");
        btnStop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timer.stop();
                P.setAlertType(AlertType.INFORMATION);
                P.setContentText("Drone Animation Paused!");
                P.show();
            }
        });

        Alert a = new Alert(AlertType.NONE); // button to add a drone
        Button btnAdd = new Button("Add Drone");
        final int[] droneid = {1};
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String toString3 = ("Drone ID %s\t" + droneid[0]);
                Label V = new Label(toString3);
                System.out.printf("\tDrone ID: %s", droneid[0]);
                rtPane.getChildren().add(V);
                droneid[0]++;
                a.setAlertType(AlertType.INFORMATION);
                a.setContentText("Drone Added");
                arena.addDrone();
                drawWorld();
            }
        });

      Button btnplr = new Button("Clear Drones"); // clears the arena
        btnplr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (Drone allDrone : arena.allDrones) {
                    arena.allDrones.clear();
                drawWorld();
            }}
        });

        Alert E = new Alert(AlertType.NONE); // add obstacle button, collisions need working on
        Button btnAddEnemy = new Button("Add Moving Obstacle");
        btnAddEnemy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                arena.addEDrone();
                drawWorld();
            }
        });

        Button btnAddObs = new Button("Add Obstacle");
        btnAddObs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                arena.addEnDrone();
                drawWorld();
            }
        });


        Button button2 = new Button(); // exit button
        button2.setText("Exit");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            } // exits
        });

        btnStart.setTextFill(PURPLE); //setting the button colours
        btnStop.setTextFill(PURPLE);
        btnStop.setTextFill(PURPLE);
        btnAdd.setTextFill(PURPLE);
        btnplr.setTextFill(PURPLE);
        btnAddEnemy.setTextFill(PURPLE);
        button2.setTextFill(PURPLE);
        btnAddObs.setTextFill(PURPLE);

        return new HBox(btnStart, btnStop, btnAdd, btnAddEnemy, button2, btnplr, btnAddObs); // returns the buttons to the canvas
    }

    public void drawStatus() {
        rtPane.getChildren().clear();
        ArrayList<String> allBs = arena.describeAll();
        for (String s : allBs) {
            Label l = new Label(s);
            rtPane.getChildren().add(l);
        }
    }

     @Override
    public void start(Stage primaryStage) throws Exception { // main loop

        primaryStage.setTitle("Drone Simulator");
        BorderPane bp = new BorderPane();
        Group root = new Group();
        Canvas canvas = new Canvas( 900, 500);
        root.getChildren().add( canvas );
        bp.setRight(root);
        mc = new MyCanvas(canvas.getGraphicsContext2D(), 400, 500, BEIGE);
        arena = new DroneArena(900, 500);

        timer = new AnimationTimer() {
            public void handle(long currentNanoTime) { // timer
                arena.checkDrones();
                arena.AdjustDrone();
                drawWorld();
                drawStatus();
            }
        };

        Alert X = new Alert(AlertType.NONE);
        Menu filemenu = new Menu("File");
        MenuItem About = new MenuItem("About");
        About.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                X.setAlertType(AlertType.INFORMATION);
                X.setContentText("Drone Simulation made by Callum Apps (30010641)");
                X.show();
            }
        });

        MenuBar menuBar = new MenuBar();
            Menu savemenu = new Menu("Save");
            savemenu.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    ResourceManager.export();
                }
            });

            MenuItem mExport = new MenuItem("Export");
            mExport.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                }
            });

            MenuItem mImg = new MenuItem("Screen Image");
            mImg.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    ResourceManager.export();
                }
            });

        menuBar.getMenus().addAll(filemenu);
        filemenu.getItems().addAll(About, mExport, mImg);

            Menu mTest = new Menu("Save/Load");

            MenuItem mTest1 = new MenuItem("Save");
            mExport.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    ResourceManager.export();
                }
            });
            MenuItem mTest2 = new MenuItem("Load");
            mExport.setOnAction(actionEvent -> {ResourceManager.importGame();
            });

            mTest.getItems().addAll(mTest1, mTest2);
            menuBar.getMenus().addAll(filemenu, mTest);

        rtPane = new VBox();
        bp.setTop(menuBar);
        bp.setPadding(new Insets(5, 75, 75, 5));
        bp.setLeft(rtPane);

        Button button2 = new Button();
        button2.setText("start");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stop[] stops = new Stop[] { new Stop(0, INDIGO), new Stop(1, ORANGE)};
                LinearGradient lngnt = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
                bp.setBottom(setButtons());
                bp.setBackground(Background.fill(LIGHTGREY));
                drawWorld();
            }
        });

        Group buttons = new Group(button2);
        bp.setBottom(buttons);
        Scene scene = new Scene(bp, 1200, 600);
        bp.prefHeightProperty().bind(scene.heightProperty());
        bp.prefWidthProperty().bind(scene.widthProperty());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);			// launch the GUI
    }
}