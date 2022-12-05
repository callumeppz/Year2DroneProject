
package com.example.demo2;

import java.util.Random;

public abstract class FirstDrone {
    protected double x, y, rad;


    FirstDrone(int ix, Random iy, double ir) {
        this(100, 100, 10);
    }

    FirstDrone(double ix, double iy, double ir) {
        x = ix;
        y = iy;
        rad = ir;
        final int[] DroneID = {0};

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRad() {
        return rad;
    }

    public int[] getID() {
        return droneid;
    }
    public static int[] droneid = {0};

    public void drawdrone(MyCanvas mc) {
        mc.showdrone(x, y, rad);
    }

    public void drawdrone2(MyCanvas mc) {
        mc.showenemydrone(x, y, rad);
    }

    public void drawdrone3(MyCanvas mc) {
        mc.showplayer(x, y, rad);
    }

    protected String getStrType() {
        droneid[0]++;
        return "Drone:" + droneid;
    }

    public String toString() {
        return getStrType()  + " at " + Math.round(x) + ", " + Math.round(y) + " facing " + Direction.RandomDirec();

    }

    protected abstract void checkdrone(DroneArena b);

    protected abstract void adjustdrone();

    public boolean hitting(double ox, double oy, double or) {
        return (ox-x)*(ox-x) + (oy-y)*(oy-y) < (or+rad)*(or+rad);
    }
    public boolean hitting (FirstDrone Drone) {
        return hitting(Drone.getX(), Drone.getY(), Drone.getRad());
    }

}


