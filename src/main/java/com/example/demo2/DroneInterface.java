package com.example.demo2;

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
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static javafx.scene.paint.Color.BEIGE;

public class DroneInterface extends Application {
    private MyCanvas mc;
    private AnimationTimer timer;
    private VBox rtPane;
    private DroneArena arena;

    public void drawWorld () {
        mc.gc.setFill(Color.WHITE);
        mc.gc.fillRect(0, 0, 2.5*mc.xCanvasSize, 2.5*mc.yCanvasSize);
        arena.drawArena(mc);
    }


    private HBox setButtons() {


        Alert S = new Alert(AlertType.NONE);
        Button btnStart = new Button("Start");
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
        Alert P = new Alert(AlertType.NONE);
        btnStart.setTextFill(Color.BLUEVIOLET);
        Button btnStop = new Button("Pause");
        btnStop.setTextFill(Color.BLUEVIOLET);
        btnStop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timer.stop();
                P.setAlertType(AlertType.INFORMATION);
                P.setContentText("Drone Animation Paused!");
                P.show();
            }
        });
        Alert a = new Alert(AlertType.NONE);
        Button btnAdd = new Button("Add Drone");
        btnAdd.setTextFill(Color.BLUEVIOLET);
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

        Alert c = new Alert(AlertType.NONE);
        Button btnlv1 = new Button("Easy Mode");
        btnlv1.setTextFill(Color.GREEN);
        btnlv1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                arena.easymode();
                arena.addEDrone();
                drawWorld();
            }
        });

        Button btnlv2 = new Button("Hard Mode");
        btnlv2.setTextFill(Color.RED);
        btnlv2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (int i = 0; i < 4; ++i) {
                    arena.Hardmode();
                }
                drawWorld();
            }
        });

        Button btnplr = new Button("Easy Mode");
        btnplr.setTextFill(Color.GREEN);
        btnplr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                drawWorld();
            }
        });


        Alert E = new Alert(AlertType.NONE);
        Button btnAddEnemy = new Button("Add Obstacle");
        btnAddEnemy.setTextFill(Color.RED);
        final int[] Edroneid = {0};
        btnAddEnemy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String toString3 = ("Enemy Drone ID %s\t" + Edroneid[0]);
                Label V = new Label(toString3);
                System.out.printf("\tEnemy Drone ID: %s", Edroneid[0]);
                rtPane.getChildren().add(V);
                Edroneid[0]++;
                E.setAlertType(AlertType.INFORMATION);
                E.setContentText("Enemy Drone Added");
                arena.addEDrone();
                E.show();
                drawWorld();
            }
        });
        Button button2 = new Button();
        button2.setText("Exit");


        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        Menu filemenu = new Menu("File");
        filemenu.getItems().add(new MenuItem("About"));
        filemenu.getItems().add(new MenuItem("Help"));
        MenuBar menuBar = new MenuBar();
        menuBar.setTranslateX(0);
        menuBar.setTranslateY(0);
        menuBar.getMenus().addAll(filemenu);

        Menu savemenu = new Menu("Save");
        savemenu.getItems().add(new MenuItem("Save Arena"));
        MenuBar savebar = new MenuBar();
        savebar.setTranslateX(50);
        savebar.setTranslateY(0);
        menuBar.getMenus().addAll(savemenu);

        return new HBox(new Label("Start: "), btnStart, btnStop, new Label("Add: "), btnAdd, btnAddEnemy, btnlv1, btnlv2, menuBar, button2);
    }


    public void drawWorld2 () {
        arena.drawArena2(mc);

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
    public void start(Stage primaryStage) throws Exception {



        primaryStage.setTitle("Drone Simulator");
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10, 20, 10, 20));
        Group root = new Group();
        Canvas canvas = new Canvas( 900, 500);
        root.getChildren().add( canvas );
        bp.setRight(root);
        mc = new MyCanvas(canvas.getGraphicsContext2D(), 400, 500, BEIGE);
        arena = new DroneArena(900, 500);
        drawWorld2();

        timer = new AnimationTimer() {
            public void handle(long currentNanoTime) {
                arena.checkDrones();
                arena.checkDrones2();
                arena.AdjustDrone();
                drawWorld();
                drawWorld2();
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
        filemenu.getItems().addAll(About);

        MenuBar menuBar = new MenuBar();
        Menu savemenu = new Menu("Save");
        savemenu.getItems().add(new MenuItem("Save Arena"));

        menuBar.getMenus().addAll(savemenu, filemenu);
        rtPane = new VBox();
        bp.setBottom(menuBar);
        bp.setPadding(new Insets(5, 75, 75, 5));
        bp.setLeft(rtPane);

        Button button2 = new Button();
        button2.setText("start");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bp.setTop(setButtons());
                drawWorld();
                drawWorld2();
            }
        });

        bp.setTop(button2);

        Scene scene = new Scene(bp, 1200, 600, BEIGE);
        bp.prefHeightProperty().bind(scene.heightProperty());
        bp.prefWidthProperty().bind(scene.widthProperty());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);			// launch the GUI

    }

}