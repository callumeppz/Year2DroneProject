package com.example.demo2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class MyCanvas {
    int xCanvasSize;
    int yCanvasSize;
    GraphicsContext gc;
    Random rand = new Random();

    public MyCanvas(GraphicsContext g, int xcs, int ycs, Color beige) {
        gc = g;
        xCanvasSize = xcs;
        yCanvasSize = ycs;

    }

    public void showdrone(double x, double y, double rad) {
        Image droneimg;
        try {
            droneimg = new Image(new FileInputStream("src/main/resources/drone.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        gc.drawImage(droneimg, x-rad, y-rad, 63, 34);
    }

    public void showenemydrone(double x, double y, double rad) {
        gc.setFill(Color.BLACK);
        gc.fillArc(x-rad, y-rad, 100, 100, 0, 360, ArcType.ROUND);
    }

    public void showplayer(double x, double y, double rad) {
        Image droneimg;
        try {
            droneimg = new Image(new FileInputStream("C:\\Users\\callu\\OneDrive\\Documents\\drone\\drone.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        gc.drawImage(droneimg, x-rad, y-rad, 63, 34);
    }

}
