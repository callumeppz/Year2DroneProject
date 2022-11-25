
package com.example.demo2;

public abstract class FirstDrone {
    protected double x, y, rad;

    static int ballCounter = 0;


    FirstDrone() {
        this(100, 100, 10);
    }

    FirstDrone(double ix, double iy, double ir) {
        x = ix;
        y = iy;
        rad = ir;
        final int[] DroneID = {0};

    }

    public double getX() { return x; }

    public double getY() { return y; }

    public double getRad() { return rad; }

    public void setXY(double nx, double ny) {
        x = nx;
        y = ny;
    }

    public int[] getID() {return droneid; }
    int[] droneid = {0};
    public void drawdrone(MyCanvas mc) {
        mc.showdrone(x, y, rad);
    }
    protected String getStrType() {
        droneid[0]++;
            return "Drone:" + droneid;
    }

    public String toString() {
        return getStrType()+" at "+Math.round(x)+", "+Math.round(y);

    }

    protected abstract void checkBall(DroneArena b);

    protected abstract void adjustBall();

    public boolean hitting(double ox, double oy, double or) {
        return (ox-x)*(ox-x) + (oy-y)*(oy-y) < (or+rad)*(or+rad);
    }


    public boolean hitting (FirstDrone oFirstDrone) {
        return hitting(oFirstDrone.getX(), oFirstDrone.getY(), oFirstDrone.getRad());
    }
}