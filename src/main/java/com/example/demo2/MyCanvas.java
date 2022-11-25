package com.example.demo2;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MyCanvas {
    int xCanvasSize = 512;
    int yCanvasSize = 512;
    GraphicsContext gc;

    public MyCanvas(GraphicsContext g, int xcs, int ycs, Color beige) {
        gc = g;
        xCanvasSize = xcs;
        yCanvasSize = ycs;
    }

    public void showdrone(double x, double y, double rad) {
        Image droneimg;
        try {
            droneimg = new Image(new FileInputStream("C:\\Users\\callu\\OneDrive\\Documents\\drone\\drone.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        gc.drawImage(droneimg, x-rad, y-rad, 63, 34);
    }

    public void showenemydrone(double x, double y, double rad) {
        Image droneimg;
        try {
            droneimg = new Image(new FileInputStream("C:\\Users\\callu\\OneDrive\\Documents\\edrone\\edrone.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        gc.drawImage(droneimg, x-rad, y-rad, 63, 34);
    }

}
