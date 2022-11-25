/**
 *
 */
package com.example.demo2;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.BEIGE;

public class DroneInterface extends Application {
    private MyCanvas mc;
    private AnimationTimer timer;
    private VBox rtPane;
    private DroneArena arena;



    private HBox setButtons() {
        Button btnStart = new Button("Start");
        btnStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timer.start();
            }
        });

        Button btnStop = new Button("Pause");
        btnStop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timer.stop();
            }
        });
        Alert a = new Alert(AlertType.NONE);
        Button btnAdd = new Button("Add Drone");
        final int[] droneid = {0};
        // now button for stop
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String toString3 = ("Drone ID %s\t" + droneid[0]);
                Label V = new Label(toString3);
                System.out.printf("\tDrone ID: %s" , droneid[0]);
                rtPane.getChildren().add(V);
                droneid[0]++;
                a.setAlertType(AlertType.INFORMATION);
                a.setContentText("Drone Added");
                arena.addDrone();
                drawWorld();
            }
        });
        return new HBox(new Label("Start: "), btnStart, btnStop, new Label("Add: "), btnAdd);
    }


    public void drawWorld () {
        arena.drawArena(mc);
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
        Canvas canvas = new Canvas( 1000, 500);
        root.getChildren().add( canvas );
        bp.setRight(root);

        mc = new MyCanvas(canvas.getGraphicsContext2D(), 400, 500, BEIGE);

        arena = new DroneArena(1000, 500);
        drawWorld();

        timer = new AnimationTimer() {
            public void handle(long currentNanoTime) {
                arena.checkDrones();
                arena.AdjustDrone();
                drawWorld();
                drawStatus();
            }
        };

        Menu filemenu = new Menu("File");
        filemenu.getItems().add(new MenuItem("New Arena"));
        filemenu.getItems().add(new MenuItem("Add Drone"));
        filemenu.getItems().add(new MenuItem("Display Drone locations"));
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
        rtPane = new VBox();
        bp.setTop(menuBar);
        bp.setPadding(new Insets(5, 75, 75, 5));
        bp.setLeft(rtPane);

        bp.setTop(setButtons());

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