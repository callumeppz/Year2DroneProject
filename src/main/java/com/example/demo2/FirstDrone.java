
package com.example.demo2;

import javafx.scene.control.Label;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public abstract class FirstDrone implements Serializable {
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

    /**
     * save info of drone on each line of string
     */

    public String toString() {
        String toString3 = ("Drone ID:\t" + droneid);
        Label V = new Label(toString3);
        droneid[0]++;
       return toString3 + " at " + Math.round(x) + ", " + Math.round(y);
    }

    public boolean isHere(double x, double y)
    {
        //include size of entity
        double top = y+80;
        double bottom = y-80;
        double left = x-80;
        double right = x+80;
        //check if there
        if((x < right && y > left) && (x < top && y > bottom))
        {
            return true;
        }

        return false;
    }


    protected abstract void checkdrone(DroneArena b);
    protected abstract void adjustdrone();
    public boolean hitting(double ox, double oy, double or, int i) {
        return (ox-x)*(ox-x) + (oy-y)*(oy-y) < (or/2+i/2)*(or/2+i/2);
    }
}



