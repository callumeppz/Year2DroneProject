
package com.example.demo2;

import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Random;

public abstract class FirstDrone {
    public double x, y, rad;

    FirstDrone(double ix, double iy, double ir) {
        x = ix;
        y = iy;
        rad = ir;
    }
    final int[] droneid = {1};

    public void drawdrone(MyCanvas mc) {
        mc.showdrone(x, y, rad);
    }
    public void drawdrone2(MyCanvas mc) {
        mc.showenemydrone(x, y, rad);
    }
    public String toString() {
        String toString3 = ("Drone ID:\t" + droneid);
        Label V = new Label(toString3);
        droneid[0]++;
        return toString3 + " at " + Math.round(x) + ", " + Math.round(y);
    }
    protected abstract void checkdrone(DroneArena b);
    protected abstract void adjustdrone();
    public boolean hitting(double ox, double oy, double or, int i) {
        return (ox-x)*(ox-x) + (oy-y)*(oy-y) < (or/2+i/2)*(or/2+i/2);
    }
}



