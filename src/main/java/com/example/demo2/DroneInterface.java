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
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
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



    public void drawWorld () { // used to draw the coloured box the drones spawn in (arena)
        Stop[] stops = new Stop[] { new Stop(0, INDIGO), new Stop(1, BLACK)}; // sets a gradient colour
        LinearGradient lngnt = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
        mc.gc.setFill(lngnt); // fills the box
        mc.gc.fillRect(0, 0, 3*mc.xCanvasSize, 3*mc.yCanvasSize); // set to 3 times the canvas size
        arena.drawArena(mc); // draws to the screen
    }

    ////////////////////////////
    //          buttons       //
    ////////////////////////////

    private HBox setButtons() { // horizontal box for the buttons to be placed in
        Alert S = new Alert(AlertType.NONE);
        Button btnStart = new Button("Start"); // begins and displays a message
        btnStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timer.start(); // begins the timer
                S.setAlertType(AlertType.INFORMATION);
                S.setContentText("Drone Animation Started!");
                S.show(); // message shown
                drawWorld();
            }
        });

        Alert P = new Alert(AlertType.NONE); // pauses
        Button btnStop = new Button("Pause");
        btnStop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timer.stop(); // stops/pauses the timer
                P.setAlertType(AlertType.INFORMATION);
                P.setContentText("Drone Animation Paused!");
                P.show(); // shows a message to the screen to let the user know
            }
        });

        Alert a = new Alert(AlertType.NONE); // button to add a drone // alerts the user
        Button btnAdd = new Button("Add Drone");
        final int[] droneid = {1};
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String toString3 = ("Drone ID %s\t" + droneid[0]);
                Label V = new Label(toString3);
                rtPane.getChildren().add(V);
                droneid[0]++; // when button is pressed droneID increases
                arena.addDrone(); // adds the drone to the arena
                drawWorld(); // adds drone to canvas
            }
        });

        Button btnclr = new Button("Remove\nDrone"); // removes a drone
        btnclr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (arena.allDrones.size() > 1) { // if there is more than 1 drones on the screen, do this
                    arena.allDrones.remove(1); // removes the second drone in the set (leaving drone 0)
                }
                }
        });
        Button btnobsclr = new Button("Remove Moving\nAsteroid"); // removes a moving asteroid
        btnobsclr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (arena.allEDrones.size() >= 1) { // if there is 1 or more asteroids on the screen, do this
                    arena.allEDrones.remove(0); // removes an asteroid from the array
                }
            }
        });
        Button btnmobsclr = new Button("Remove\nAsteroid"); // removes a asteroid
        btnmobsclr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (arena.allEnDrones.size() >= 1) { // if there is 1 or more asteroids on the screen, do this
                    arena.allEnDrones.remove(0); // removes an asteroid from the array
                }
            }
        });

        Alert E = new Alert(AlertType.NONE); // add obstacle button
        Button btnAddEnemy = new Button("Add Moving\nAsteroid");
        btnAddEnemy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                arena.addEDrone(); // adds a moving asteroid
                drawWorld(); // adds entitiy to te canvas
            }
        });


        Button btnAI = new Button("Drone 0\nManual"); // used to set a drone to manual movement
        btnAI.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Drone player = arena.allDrones.get(0); // gets the first drone currently spawned
                player.isplayer = true; // starts the player movement function
            }
        });
        Button btnAIoff = new Button("Drone 0\nAutomatic"); // automates drone 0
        btnAIoff.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Drone player = arena.allDrones.get(0); // gets the first drone currently spawned
                player.isplayer = false; // sets is player to false, causing automation physics to function
            }
        });


        Button btnSave = new Button("Save"); // save button
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                export();
            } // saves the current state of the canvas to a txt file
        });


        Button btnImport = new Button("load"); // load button
        btnImport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                importGame();
            } // used to load a previous save, stored as txt
        });

        Button btnAddEn = new Button("Asteroid\nStrike"); // button used to start a asteroid strike
        final Timer[] timer = {new Timer()};

        btnAddEn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timer[0] = new Timer(); // creates a timer
                timer[0].schedule(new TimerTask() {
                    @Override
                    public void run() {
                        arena.addMeteorDrone();
                    }
                }, 0, 400); // timer spawns an asteroid every 400 milliseconds
                drawWorld(); // draws asteroids to the canvas
            }
        });
        Button btnStpEn = new Button("Stop Asteroid\nStrike"); // stops the asteroid strike
        btnStpEn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    timer[0].cancel(); // cancels the timer
                    drawWorld(); // removes asteroids
                }
        });


        Button btnAddObs = new Button("Add\nAsteroid");
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

        btnStart.setMinSize(85,60); // setting buttons sizes
        btnStop.setMinSize(85,60);
        btnclr.setMinSize(85,60);
        btnAddObs.setMinSize(85,60);
        btnStpEn.setMinSize(85,60);
        btnAdd.setMinSize(85,60);
        btnAddEnemy.setMinSize(85,60);
        button2.setMinSize(85,60);
        btnAddEn.setMinSize(85,60);
        btnAddEnemy.setMinSize(85,60);
        btnobsclr.setMinSize(85,60);
        btnmobsclr.setMinSize(85,60);
        btnSave.setMinSize(85,60);
        btnImport.setMinSize(85,60);
        btnAI.setMinSize(85,60);
        btnAIoff.setMinSize(85,60);



        btnStart.setStyle("-fx-text-fill: purple;"+
                "-fx-background-color: Grey;"+
                "-fx-font: normal bold 10px 'Lucida Console';");
        btnStop.setStyle("-fx-text-fill: purple;"+
                "-fx-background-color: Grey;"+
                "-fx-font: normal bold 10px 'Lucida Console';");
        btnAdd.setStyle("-fx-text-fill: purple;"+
                "-fx-background-color: Grey;"+
                "-fx-font: normal bold 10px 'Lucida Console';");
        btnAddEnemy.setStyle("-fx-text-fill: purple;"+
                "-fx-background-color: Grey;"+
                "-fx-font: normal bold 10px 'Lucida Console';");
        btnAddObs.setStyle("-fx-text-fill: purple;"+
                "-fx-background-color: Grey;"+
                "-fx-font: normal bold 10px 'Lucida Console';");
        btnAddEn.setStyle("-fx-text-fill: purple;"+
                "-fx-background-color: Grey;"+
                "-fx-font: normal bold 10px 'Lucida Console';");
        btnclr.setStyle("-fx-text-fill: purple;"+
                "-fx-background-color: Grey;"+
                "-fx-font: normal bold 10px 'Lucida Console';");
        btnmobsclr.setStyle("-fx-text-fill: purple;"+
                "-fx-background-color: Grey;"+
                "-fx-font: normal bold 10px 'Lucida Console';");
        btnobsclr.setStyle("-fx-text-fill: purple;"+
                "-fx-background-color: Grey;"+
                "-fx-font: normal bold 10px 'Lucida Console';");
        button2.setStyle("-fx-text-fill: purple;"+
                "-fx-background-color: Grey;"+
                "-fx-font: normal bold 10px 'Lucida Console';");
        btnSave.setStyle("-fx-text-fill: purple;"+
                "-fx-background-color: Grey;"+
                "-fx-font: normal bold 10px 'Lucida Console';");
        btnImport.setStyle("-fx-text-fill: purple;"+
                "-fx-background-color: Grey;"+
                "-fx-font: normal bold 10px 'Lucida Console';");
        btnAI.setStyle("-fx-text-fill: purple;"+
                "-fx-background-color: Grey;"+
                "-fx-font: normal bold 10px 'Lucida Console';");
        btnAIoff.setStyle("-fx-text-fill: purple;"+
                "-fx-background-color: Grey;"+
                "-fx-font: normal bold 10px 'Lucida Console';");
        btnStpEn.setStyle("-fx-text-fill: purple;"+
                "-fx-background-color: Grey;"+
                "-fx-font: normal bold 10px 'Lucida Console';");

        /**
         * all buttons on the scene are declared here
         */

        return new HBox(btnStart, btnStop, btnAdd, btnAddEnemy, btnAddObs, btnAddEn, btnStpEn, btnclr, btnmobsclr, btnobsclr, button2, btnSave, btnImport, btnAI, btnAIoff); // returns the buttons to the canvas
    } // adds all the buttons to a hbox which is then used in borderpane

    public void drawStatus() {
        rtPane.getChildren().clear();
        ArrayList<String> allBs = arena.describeAll();
        for (String s : allBs) {
            Label l = new Label(s);
            rtPane.getChildren().add(l);
        }
    }

    ////////////////////////////
    //          Start loop    //
    ////////////////////////////
     @Override
    public void start(Stage primaryStage) throws Exception { // main loop

        primaryStage.setTitle("Space Drone Simulator (30010641)");
        BorderPane bp = new BorderPane();
        Scene scene = new Scene(bp, 1500, 800);
        Group root = new Group();
        Canvas canvas = new Canvas( 1100, 600);
        root.getChildren().add( canvas );
        bp.setLeft(root);
        mc = new MyCanvas(canvas.getGraphicsContext2D(), 700, 600, PURPLE);
        arena = new DroneArena(1050, 600);


         ////////////////////////////
         //          timer         //
         ////////////////////////////

        timer = new AnimationTimer() {
            public void handle(long currentNanoTime) { // timer
                arena.checkDrones();
                arena.AdjustDrone();
                userMove(scene);
                drawWorld();
                drawStatus();

            }
        };
         /**
          * animation timer used for all movement and collision
          */


        Alert X = new Alert(AlertType.INFORMATION); //Popups created for menubar

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
         Alert I = new Alert(AlertType.INFORMATION);
         MenuItem Info = new MenuItem("Info");
         Info.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {
                 I.setAlertType(AlertType.INFORMATION);
                 I.setContentText("Add more than one drone to turn on collision damage with the Asteroids.");
                 I.show();
             }
         });

         ////////////////////////////
         //          GUI           //
         ////////////////////////////

        MenuBar menuBar = new MenuBar();
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

        filemenu.getItems().addAll(About, Info, mExport, mImport);

            menuBar.getMenus().addAll(filemenu);

        rtPane = new VBox();
        bp.setTop(menuBar);
        bp.setPadding(new Insets(0, 20, 20, 0));
        bp.setRight(rtPane);

        Button start = new Button();
        start.setText("start");
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stop[] stops = new Stop[] { new Stop(0, INDIGO), new Stop(1, ORANGE)};
                LinearGradient lngnt = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
                bp.setBottom(setButtons());
                bp.setBackground(Background.fill(MEDIUMPURPLE));
                start.setVisible(false);
                drawWorld();
            }
        });
         /**
          * button used to start the simulation from the main menu
          */

         Button load = new Button();
         load.setText("load");
         load.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {
                importGame();
                load.setVisible(false);

             }
         });

         Button exit = new Button();
         exit.setText("exit");
         exit.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {
                 System.exit(0);
             }
         });



        Group buttons = new Group(start, load); // starting screen edited using css
        start.setMinSize(150,30);
        load.setMinSize(150,30);
        exit.setMinSize(150,30);
        start.setStyle("-fx-text-fill: purple;"+
                "-fx-background-color: Grey;"+
                "-fx-font: normal bold 25px 'Lucida Console';");
         load.setStyle("-fx-text-fill: purple;"+
                 "-fx-background-color: Grey;"+
                 "-fx-font: normal bold 25px 'Lucida Console';");
         exit.setStyle("-fx-text-fill: purple;"+
                 "-fx-background-color: Grey;"+
                 "-fx-font: normal bold 25px 'Lucida Console';");
        HBox hbox = new HBox();
        hbox.getChildren().add(start);
        hbox.getChildren().add(load);
        hbox.getChildren().add(exit);
        bp.setBottom(hbox);

        bp.prefHeightProperty().bind(scene.heightProperty());
        bp.prefWidthProperty().bind(scene.widthProperty());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * sets the scene and the borderpane with all buttons and entities
     * @param scene
     */

    public void userMove(Scene scene) { // custom movement of a single drone using keyboard keys
        Drone player = arena.allDrones.get(0);

        scene.setOnKeyPressed((KeyEvent event) -> {
            System.out.println(event.getText());
            switch (event.getText()){
                case "w":
                    player.y-=10; // when player presses W drone moves 10
                    break;
                case "a":
                    player.x-=10;
                    break;
                case "d":
                    player.x+=10;
                    break;
                case "s":
                    player.y+=10;
                    break;

            }
        });
    }

    /**
     * usermove function is used for manual player movement and is activated by a button.
     */

    static void export() { // saves/exports the simulation
        try {
            FileOutputStream outFile = new FileOutputStream("/Users/callumapps/Desktop/drone/drone.txt"); // saves to this specific path name
            ObjectOutputStream outStream = new ObjectOutputStream(outFile);
            outStream.writeObject(arena);
            outStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * export/import function used for saving the current state of the arena as a txt in my documents, then being
     * able to reload the state.
     */
    public static void importGame() { // imports/loads
        File inFile = new File("/Users/callumapps/Desktop/drone/drone.txt"); // loads from this specific path name
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