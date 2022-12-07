package com.example.demo2;

import java.io.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.*;
import static javafx.scene.paint.Color.BEIGE;

public class DroneInterface extends Application {
    private MyCanvas mc;
    private AnimationTimer timer;
    private VBox rtPane;
    private static DroneArena arena;

    public void drawWorld () {
        Stop[] stops = new Stop[] { new Stop(0, INDIGO), new Stop(1, BLACK)};
        LinearGradient lngnt = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
        mc.gc.setFill(lngnt);
        mc.gc.fillRect(15, 0, 3*mc.xCanvasSize, 3*mc.yCanvasSize);
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
                rtPane.getChildren().add(V);
                droneid[0]++;
                arena.addDrone();
                drawWorld();
            }
        });


        Button btnclr = new Button("Remove Drone"); // removes a drone
        btnclr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               arena.allDrones.remove(0);
                }
        });
        Button btnobsclr = new Button("Remove Moving\nAsteroid"); // removes a drone
        btnobsclr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                arena.allEDrones.remove(0);
            }
        });
        Button btnmobsclr = new Button("Remove Asteroid"); // removes a drone
        btnmobsclr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                arena.allEnDrones.remove(0);
            }
        });

        Alert E = new Alert(AlertType.NONE); // add obstacle button
        Button btnAddEnemy = new Button("Add Moving\nObstacle");
        btnAddEnemy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                arena.addEDrone();
                drawWorld();
            }
        });


        Button btnSave = new Button("Save");
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                export();
            }
        });


        Button btnImport = new Button("load");
        btnImport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                importGame();
            }
        });

        Button btnAddEn = new Button("Meteor Strike");
        final Timer[] timer = {new Timer()};

        btnAddEn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timer[0] = new Timer();
                timer[0].schedule(new TimerTask() {
                    @Override
                    public void run() {
                        arena.addMeteorDrone();
                    }
                }, 0, 400);
                drawWorld();
            }
        });
        Button btnStpEn = new Button("Stop Meteor\nStrike");
        btnStpEn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    timer[0].cancel();
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

        btnStart.setMinSize(100,60); // setting buttons sizes
        btnStop.setMinSize(100,60);
        btnclr.setMinSize(100,60);
        btnAddObs.setMinSize(100,60);
        btnStpEn.setMinSize(100,60);
        btnAdd.setMinSize(100,60);
        btnAddEnemy.setMinSize(100,60);
        button2.setMinSize(100,60);
        btnAddEn.setMinSize(100,60);
        btnAddEnemy.setMinSize(100,60);
        btnobsclr.setMinSize(100,60);
        btnmobsclr.setMinSize(100,60);
        btnSave.setMinSize(100,60);
        btnImport.setMinSize(100,60);

        btnStart.setTextFill(PURPLE); //setting the button colours
        btnStop.setTextFill(PURPLE);
        btnAdd.setTextFill(PURPLE);
        btnAddEnemy.setTextFill(PURPLE);
        button2.setTextFill(PURPLE);
        btnAddObs.setTextFill(PURPLE);
        btnclr.setTextFill(PURPLE);
        btnAddObs.setTextFill(PURPLE);
        btnStpEn.setTextFill(PURPLE);
        btnAddEn.setTextFill(PURPLE);
        btnmobsclr.setTextFill(PURPLE);
        btnobsclr.setTextFill(PURPLE);

        return new HBox(btnStart, btnStop, btnAdd, btnAddEnemy, btnAddObs, btnAddEn, btnStpEn, btnclr, btnmobsclr, btnobsclr, button2, btnSave, btnImport); // returns the buttons to the canvas
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

        primaryStage.setTitle("Space Drone Simulator (30010641)");
        BorderPane bp = new BorderPane();
        Group root = new Group();
        Canvas canvas = new Canvas( 1100, 600);
        root.getChildren().add( canvas );
        bp.setLeft(root);
        mc = new MyCanvas(canvas.getGraphicsContext2D(), 700, 600, BEIGE);
        arena = new DroneArena(1050, 600);

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
                X.setContentText("Drone Simulation space version made by Callum Apps (30010641)");
                X.show();
            }
        });

        MenuBar menuBar = new MenuBar();
            Menu savemenu = new Menu("Save");
            MenuItem mExport = new MenuItem("Export");
            mExport.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    export();
                }
            });

         MenuItem mImport= new MenuItem("Import");
         mExport.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {
                 importGame();
             }
         });

        filemenu.getItems().addAll(About, mExport, mImport);

            Menu mTest = new Menu("Save/Load");

            MenuItem mTest1 = new MenuItem("Save");
            mExport.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    export();
                }
            });
            MenuItem mTest2 = new MenuItem("Load");
            mImport.setOnAction(actionEvent -> {importGame();
            });

            mTest.getItems().addAll(mTest1, mTest2);
            menuBar.getMenus().addAll(filemenu, mTest);

        rtPane = new VBox();
        bp.setTop(menuBar);
        bp.setPadding(new Insets(0, 20, 20, 0));
        bp.setRight(rtPane);

        Button button2 = new Button();
        button2.setText("start");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stop[] stops = new Stop[] { new Stop(0, INDIGO), new Stop(1, ORANGE)};
                LinearGradient lngnt = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
                bp.setBottom(setButtons());
                bp.setBackground(Background.fill(MEDIUMPURPLE));
                drawWorld();
            }
        });

        Group buttons = new Group(button2);
        bp.setBottom(buttons);
        Scene scene = new Scene(bp, 1500, 800);
        bp.prefHeightProperty().bind(scene.heightProperty());
        bp.prefWidthProperty().bind(scene.widthProperty());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);			// launch the GUI
    }


    static void export() { // saves/exports the simulation
        try {
            FileOutputStream outFile = new FileOutputStream("C:/callu/Drone/test.txt");
            ObjectOutputStream outStream = new ObjectOutputStream(outFile);
            outStream.writeObject(arena);
            outStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void importGame() { // imports/loads
        File inFile = new File("C:/callu/Drone/test.txt");
        try {
            FileInputStream inStream = new FileInputStream(inFile);
            ObjectInputStream inObjectStream = new ObjectInputStream(inStream);
            arena = (DroneArena) inObjectStream.readObject();
            inObjectStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}