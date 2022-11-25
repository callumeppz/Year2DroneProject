
package com.example.demo2;


import java.util.Random;

public class Drone extends FirstDrone {
    double DroneAngle, DroneSpeed;
    public Drone(Random ix, Random iy, double ir, double ia, double is) {
        super(ix, iy, ir);
        DroneAngle = ia;
        DroneSpeed = is;
    }
    @Override
    protected void checkdrone(DroneArena b) {
        DroneAngle = b.CheckBallAngle(x, y, rad, DroneAngle, droneid);
    }
    @Override
    protected void adjustdrone() {
        double radAngle = DroneAngle*Math.PI/180;
        x += DroneSpeed * Math.cos(radAngle);
        y += DroneSpeed * Math.sin(radAngle);
    }
    protected String getStrType() {
        return "Drone";
    }

}