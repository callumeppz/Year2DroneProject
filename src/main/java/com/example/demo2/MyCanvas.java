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
    /**
     * @return xcanvas size
     */
    public int getXCanvasSize() {
        return xCanvasSize;
    }
    /**
     * @return ycanvas size
     */
    public int getYCanvasSize() {
        return yCanvasSize;
    }
    /**
     * clear the canvas
     */
    public void clearCanvas() {
        gc.clearRect(0,  0,  xCanvasSize,  yCanvasSize);		// clear canvas
    }



    public void showdrone(double x, double y, double rad) {
        Image droneimg;
        try {
            droneimg = new Image(new FileInputStream("src/main/resources/drone.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        gc.drawImage(droneimg, x-rad, y-rad, 100, 55);
    }

    public void showenemydrone(double x, double y, double rad) {
        gc.setFill(Color.GREY);
        gc.fillArc(x-rad, y-rad, 50, 50, 0, 360, ArcType.ROUND);
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
