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
    private com.example.demo2.Drone[] Drone;

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
        gc.clearRect(0, 0, xCanvasSize, yCanvasSize);        // clear canvas
    }

public void healthbar(double x, double y, double health, double healthbar) {
        if (health <= 10) {
            gc.setFill(Color.BROWN);
            gc.fillRoundRect(x - 50, y - 50, 100, 10, 1,2);
        }
    if (health <= 100) {
        gc.setFill(Color.GREEN);
    }
    gc.fillRoundRect(x-50,y-50, healthbar, 10, 1,1);
    gc.fillText(String.format("%.of HP", health), x - 50, y -35);
}
    public void showdrone(double x, double y, double rad) {
        Image droneimg;
        try {
            droneimg = new Image(new FileInputStream("src/main/resources/drone.png")); // adds the image saved in resources to the screen
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        gc.drawImage(droneimg, x - rad, y - rad, 100, 55);
    }

    /**
     *
     * @param x
     * @param y
     * @param rad
     */

    public void showbetadrone(double x, double y, double rad) {
        gc.setFill(Color.WHITE);
        gc.fillArc(x - rad, y - rad, 30, 30, 0, 360, ArcType.ROUND);
    }
    public void showenemydrone(double x, double y, double rad) {
        gc.setFill(Color.GREY);
        gc.fillArc(x - rad, y - rad, 50, 50, 0, 360, ArcType.ROUND);
    }
}
