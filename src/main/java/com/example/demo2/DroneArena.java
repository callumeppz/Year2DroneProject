
package com.example.demo2;

import java.util.ArrayList;

public class DroneArena {
    double xSize, ySize;
    private ArrayList<Drone> allDrones;

    DroneArena() {
        this(500, 400);
    }

    DroneArena(double xS, double yS){
        xSize = xS;
        ySize = yS;
        allDrones = new ArrayList<Drone>();
        allDrones.add(new Drone(xS/2, yS/2, 10, 55, 10));

    }
    public void drawArena(MyCanvas mc) {
        for (FirstDrone b : allDrones) b.drawdrone(mc);
    }
    public void checkDrones() {
        for (FirstDrone b : allDrones) b.checkBall(this);
    }
    public void AdjustDrone() {
        for (FirstDrone b : allDrones) b.adjustBall();
    }
    public ArrayList<String> describeAll() {
        ArrayList<String> ans = new ArrayList<String>();
        for (FirstDrone b : allDrones) ans.add(b.toString());
        return ans;
    }
    public double CheckBallAngle(double x, double y, double rad, double ang, int[] notID) {
        double ans = ang;
        if (x < rad || x > xSize - rad) ans = 180 - ans;
        if (y < rad || y > ySize - rad) ans = -ans;
        return ans;
    }
    public void addDrone() {
        allDrones.add(new Drone(xSize/2, ySize/2, 10, 55, 5));
    }
}