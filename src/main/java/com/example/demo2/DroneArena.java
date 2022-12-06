
package com.example.demo2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class DroneArena {
    double xSize, ySize;
    private ArrayList<EnemyDrone> allEDrones;
    public ArrayList<Drone> allDrones;
    Random rand = new Random();

    DroneArena(double xS, double yS) {
        xSize = xS;
        ySize = yS;
        allDrones = new ArrayList<Drone>();
        allDrones.add(new Drone(rand.nextInt(700), rand.nextInt(400), 10, 55, 10));
        allEDrones = new ArrayList<EnemyDrone>();
    }

    public void drawArena(MyCanvas mc) {
        for (FirstDrone b : allDrones) b.drawdrone(mc);
    }

    public void drawArena2(MyCanvas mc) {
        for (FirstDrone b : allEDrones) b.drawdrone2(mc);
    }


    public void AdjustDrone() {
        for (FirstDrone b : allDrones) {
            b.adjustdrone();
        }
        }
    public void checkDrones() {
        for (FirstDrone Drone : allDrones) Drone.checkdrone(this);
    }



    public ArrayList<String> describeAll() {
        ArrayList<String> ans = new ArrayList<String>();
        for (FirstDrone b : allDrones) ans.add(b.toString());
        return ans;
    }
    public Object addDrone() {
        allDrones.add(new Drone(rand.nextInt(700), rand.nextInt(400), 10, 55, 5));
        return null;
    }
    public void addEDrone() {
        allEDrones.add(new EnemyDrone(rand.nextInt(700), rand.nextInt(400), 10, 55, 5));

    }
    public double checkDroneAngle(double x, double y, double rad, double ang, FirstDrone droneo) {
        double ans = ang;
        if (allDrones.size() >= 2)
        {
            for (Drone Drone1 : allDrones)
            {
                    if (Drone1 != droneo && Drone1.hitting(x, y, 63, 34)) {
                        ang = 180 * Math.atan2(y - Drone1.y, x - Drone1.x) / Math.PI;
                    }
                }
        }
        if (x > xSize - rad || x < rad) {
            ang = 180 - ang;
        }

        if (y > ySize - rad || y < rad ) {
            ang = -ang;
        }
        return ang;

    }
}


