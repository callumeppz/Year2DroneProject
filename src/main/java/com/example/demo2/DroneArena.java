
package com.example.demo2;

import java.util.ArrayList;
import java.util.Random;

public class DroneArena {
    double xSize, ySize;
    private ArrayList<EnemyDrone> allEDrones;
    private ArrayList<Drone> allDrones;
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

    public void checkDrones() {
        for (FirstDrone b : allDrones) b.checkdrone(this);
    }

    public void checkDrones2() {
        for (FirstDrone b : allEDrones) b.checkdrone(this);
    }

    public void AdjustDrone() {
        for (FirstDrone b : allDrones) b.adjustdrone();
        for (FirstDrone b : allEDrones) b.adjustdrone();
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

    /**public double Checkhitting(double x, double y, double rad, double ang, int[] notID) {
     double ans = ang;
     if (Drone.getId() != notID && Drone.hitting(x, y)) {
     ang = 180 * Math.atan2(y - Drone.y, x - Drone.x) / Math.PI;
     }
     if (EnemyDrone.getId() != notID && EnemyDrone.hitting(x, y)) {
     ang = 180 * Math.atan2(y - EnemyDrone.y, x - EnemyDrone.x) / Math.PI;
     }
     return ans;
     }
     **/

    public Object addDrone() {
        allDrones.add(new Drone(rand.nextInt(700), rand.nextInt(400), 10, 55, 5));

        return null;
    }

    public void addEDrone() {
        allEDrones.add(new EnemyDrone(rand.nextInt(700), rand.nextInt(400), 10, 55, 5));

    }
    public void easymode() {
        addDrone();
    }

    public boolean checkHit(FirstDrone Drone) {
        boolean ans = false;
        for (FirstDrone b : allDrones)
            if (b instanceof Drone && b.hitting(Drone)) ans = true;
        return ans;
    }

    }
